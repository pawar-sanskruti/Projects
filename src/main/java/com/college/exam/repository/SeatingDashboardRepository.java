package com.college.exam.repository;

import com.college.exam.entity.SeatingArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatingDashboardRepository extends JpaRepository<SeatingArrangement, Integer> {

    @Query(value = """
        SELECT 
            e.exam_name,
            e.exam_date,
            sub.subject_name,
            h.hall_name
        FROM seating_arrangement sa
        JOIN exam e ON sa.exam_id = e.exam_id
        JOIN subject sub ON sa.subject_id = sub.subject_id
        JOIN exam_hall h ON sa.hall_id = h.hall_id
        ORDER BY sa.seating_id DESC
        LIMIT 1
        
    """, nativeQuery = true)
    List<Object[]> getRecentSeating();
}