package com.college.exam.repository;

import com.college.exam.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByRollNo(String rollNo);

    // ✅ THIS IS IMPORTANT
    List<Student> findByClassEntity_ClassId(int classId);
}