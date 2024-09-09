package com.example.CourseApp.service.user;

import com.example.CourseApp.dto.request.PermissionRequest;
import com.example.CourseApp.model.user.Permission;
import com.example.CourseApp.model.user.Role;
import com.example.CourseApp.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public Permission addPermission(PermissionRequest permissionRequest){
        Permission permission = Permission.builder().build();
        permission.setDescription(permissionRequest.description());
        return permissionRepository.save(permission);
    }


}
