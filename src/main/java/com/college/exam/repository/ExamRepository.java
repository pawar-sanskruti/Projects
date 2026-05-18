package com.college.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.exam.entity.Exam;


public interface ExamRepository extends JpaRepository<Exam, Integer> {

    // ✅ Find exams by date
    List<Exam> findByExamDate(java.time.LocalDate examDate);

    // ✅ Find exams by session (Morning/Afternoon)
    List<Exam> findBySession(String session);

}
/*

package com.college.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.college.exam.entity.Exam;
import com.college.exam.model.Department;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
          default List<Exam> findByDepartment_DeptId(int deptId) {
		// TODO Auto-generated method stub
		return null;
	}

          
          
	}
*/