package com.example.CourseApp.entity.course;

import com.example.CourseApp.share.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "providers")
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Provider {

    @Id
    int id;

    String name;

    String description;

    String website;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
