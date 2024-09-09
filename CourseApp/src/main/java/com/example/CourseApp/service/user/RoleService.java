package com.example.CourseApp.service.user;

import com.example.CourseApp.dto.request.RoleRequest;
import com.example.CourseApp.model.user.Role;
import com.example.CourseApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role addRole(RoleRequest request){
        Role role = Role.builder().build();
        role.setDescription(request.description());
        role.setPermissions(request.permissions());
        return roleRepository.save(role);
    }


}
