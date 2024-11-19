package com.example.CourseApp.controller;

import com.amazonaws.Response;
import com.example.CourseApp.dto.response.OrderRequestDTO;
import com.example.CourseApp.entity.course.Order;
import com.example.CourseApp.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/booking")
    public ResponseEntity<?> bookTickets(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        Integer ticket = paymentService.reserveSeats(
                orderRequestDTO.getUserId(),
                orderRequestDTO.getCourseId()
        );
        return ResponseEntity.ok(ticket);
    }
    @PostMapping("/submitOrder")
    public Map<String, String> submitOrder(@RequestParam("id") int id,
                                           HttpServletRequest request) {
        return paymentService.submitOrder(request, id);
    }

    @GetMapping("/vnpay-payment-return")
    public void paymentCompleted(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> result = paymentService.paymentCompleted(request);
        Integer courseId = (Integer) result.get("courseId");

        response.sendRedirect("http://127.0.0.1:5500/course-details.html?id="+courseId);
    }
}
