package com.example.CourseApp.entity.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import software.amazon.ion.Decimal;

@Table(name = "order_items")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    Order order;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    Course course;

    int price;
}