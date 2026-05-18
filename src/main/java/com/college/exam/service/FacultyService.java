package com.college.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.college.exam.entity.Faculty;
import com.college.exam.repository.FacultyRepository;

@Service
public class FacultyService {

    @Autowired
    FacultyRepository repo;

    public Faculty login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }
}