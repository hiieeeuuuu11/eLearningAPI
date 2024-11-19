package com.example.CourseApp.repository;

import com.example.CourseApp.entity.course.Chapter;
import com.example.CourseApp.entity.course.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    OrderItem findByOrderId(int orderId);

    boolean existsByOrderId(int orderId);
}
