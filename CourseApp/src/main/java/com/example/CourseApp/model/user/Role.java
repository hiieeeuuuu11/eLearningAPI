package com.example.CourseApp.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
@Entity
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "role",referencedColumnName = "id")
    @JsonManagedReference
    @ToString.Exclude
    List<AppUser> users;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Permission> permissions;

}
