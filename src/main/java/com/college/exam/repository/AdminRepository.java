package com.college.exam.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.college.exam.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);
}