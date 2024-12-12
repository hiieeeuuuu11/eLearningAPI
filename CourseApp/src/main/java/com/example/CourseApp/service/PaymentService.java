package com.example.CourseApp.service;

import com.example.CourseApp.config.VNPAYConfig;
import com.example.CourseApp.entity.course.*;
import com.example.CourseApp.exceptions.BadRequestException;
import com.example.CourseApp.exceptions.ObjectNotFoundException;
import com.example.CourseApp.repository.*;
import com.example.CourseApp.share.enums.PaymentStatus;
import com.example.CourseApp.share.enums.ResponseStatusCodeConst;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.ion.Decimal;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private LearnerRepository learnerRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private EnrollmentsRepository enrollmentsRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private TPAService tpaService;

    private BigDecimal calculateTotalPrice(List<Integer> courseID) {
        List<Course> courses = courseRepository.findAllById(courseID);

        BigDecimal totalPrice = courses.stream()
                .map(course -> BigDecimal.valueOf(course.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPrice;

    }
    public Integer reserveSeats(Integer userId , List<Integer> courseIds){
        Order order = new Order();
        if (!learnerRepository.existsByUserId(userId)) {
            throw new BadRequestException("Learner không tồn tại với userID: " + userId);
        }
        Learner learner = learnerRepository.findByUserId(userId);
        order.setLearner(learner);
        order.setTotalAmount(calculateTotalPrice(courseIds).intValue());
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        List<OrderItem> orderItems = courseIds.stream().map(courseId -> {
            Course course = courseRepository.findById(courseId).orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND));
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setCourse(course);
            orderItem.setPrice(course.getPrice());
            return orderItem;
        }).toList();
        orderItemRepository.saveAll(orderItems);
        return order.getId();
    }
    public Map<String, String> submitOrder(HttpServletRequest request, int id) {
        Order order = orderRepository.findById(id).orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.NO_ORDER_FOUND));
        int orderTotal = order.getTotalAmount()*100;
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setPaymentStatus(PaymentStatus.pending);
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = createOrder(request, orderTotal, String.valueOf(payment.getId()), baseUrl);
        Map<String, String> response = new HashMap<>();
        response.put("paymentUrl", vnpayUrl);
        return response;
    }

    public Map<String, Object> paymentCompleted(HttpServletRequest request) {
        int paymentStatus = orderReturn(request);
        // Lấy thông tin từ request
        String paymentId = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        // Chuẩn bị dữ liệu trả về
        Map<String, Object> response = new HashMap<>();
        response.put("totalPrice", totalPrice);
        response.put("paymentTime", paymentTime);
        response.put("transactionId", transactionId);
        Payment payment = paymentRepository.findById(Integer.valueOf(paymentId)).orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.NO_PAYMENT_FOUND));
        Order order=orderRepository.findById(payment.getOrder().getId()).orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.NO_ORDER_FOUND));
        Learner learner = learnerRepository.findById(order.getLearner().getId()).orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.NO_LEARNER_FOUND));
        int userId = learner.getUser().getId();
        if (!providerRepository.existsByUserId(learner.getUser().getId())) {
            throw new BadRequestException("Provider không tồn tại với userID: " + userId);
        }
        int providerID = providerRepository.findByUserId(userId).getId();
        if (!orderItemRepository.existsByOrderId(order.getId())) {
            throw new BadRequestException("Course không tồn tại với providerId: " + providerID);
        }
        Integer courseId = orderItemRepository.findByOrderId(order.getId()).getCourse().getId();
        Course course = courseRepository.findById(courseId).orElseThrow(()->new ObjectNotFoundException(ResponseStatusCodeConst.NO_COURSE_FOUND));
        response.put("courseId",course.getId());
        if (paymentStatus == 1) {
            response.put("message", "Payment Success");
            response.put("status", "success");
            payment.setPaymentStatus(PaymentStatus.completed);
            Enrollment enrollment = new Enrollment();
            enrollment.setLearner(learner);
            enrollment.setCourse(course);
            enrollment.setEnrollment_date(LocalDateTime.now());
            enrollmentsRepository.save(enrollment);
            tpaService.enroll(course.getId(), learner.getUser().getId());
        } else {
            response.put("message", "Payment Failed");
            response.put("status", "failed");
            payment.setPaymentStatus(PaymentStatus.failed);
        }
        payment.setPaymentDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return response;
    }

    public String createOrder(HttpServletRequest request, int amount, String orderInfor, String urlReturn) {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = VNPAYConfig.getRandomNumber(8);
        String vnp_IpAddr = VNPAYConfig.getIpAddress(request);
        String vnp_TmnCode = VNPAYConfig.vnp_TmnCode;
        String orderType = "order-type";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfor);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        urlReturn += VNPAYConfig.vnp_Returnurl;
        vnp_Params.put("vnp_ReturnUrl", urlReturn);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 5);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String salt = VNPAYConfig.vnp_HashSecret;
        String vnp_SecureHash = VNPAYConfig.hmacSHA512(salt, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPAYConfig.vnp_PayUrl + "?" + queryUrl;

        return paymentUrl;
    }

    public int orderReturn(HttpServletRequest request) {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = params.nextElement();
            String fieldValue = request.getParameter(fieldName);
            if (fieldValue != null && fieldValue.length() > 0) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = VNPAYConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            return "00".equals(request.getParameter("vnp_TransactionStatus")) ? 1 : 0;
        } else {
            return -1;
        }
    }



}
