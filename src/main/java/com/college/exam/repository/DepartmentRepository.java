package com.college.exam.repository;

import com.college.exam.entity.Exam;
import com.college.exam.model.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
}