package com.example.CourseApp.dto.request;

import com.example.CourseApp.model.user.Permission;

import java.util.List;

public record RoleRequest(String description, List<Permission> permissions) {
}
