package com.college.exam.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.exam.entity.Admin;
import com.college.exam.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repo;

    public boolean validateLogin(String username, String password) {
        Admin admin = repo.findByUsername(username);
        return admin != null && admin.getPassword().equals(password);
    }
}