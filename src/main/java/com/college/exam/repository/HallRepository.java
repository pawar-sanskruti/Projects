package com.college.exam.repository;

import com.college.exam.entity.ExamHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HallRepository extends JpaRepository<ExamHall, Integer> {
	@Query(value = "SELECT hall_id FROM exam_hall LIMIT 1", nativeQuery = true)
    Integer findFirstHallId();
}