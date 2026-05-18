package com.college.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.exam.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    // Optional custom methods (if needed later)

    Subject findBySubjectCode(String subjectCode);

}