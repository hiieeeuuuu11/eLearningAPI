package com.example.CourseApp.repository;

import com.example.CourseApp.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Integer> {

    AppUser getAppUserByUsername(String username);

}
