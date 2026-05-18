package com.college.exam.repository;

import com.college.exam.dto.ExamViewDTO;
import com.college.exam.dto.SeatViewDTO;
import com.college.exam.dto.SeatingViewDTO;
import com.college.exam.entity.SeatingArrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SeatingRepository extends JpaRepository<SeatingArrangement, Integer> {

    List<SeatingArrangement> findByExam_ExamId(Integer examId);

    boolean existsByExam_ExamId(Integer examId);

    void deleteByExam_ExamId(Integer examId);
    /*
    @Query(value = """
    	    SELECT 
    	        sa.seating_id,
    	        s.roll_no,
    	        s.name AS student_name,
    	        c.class_name,
    	        e.exam_name,
    	        e.session, 
    	        e.exam_date,
    	        e.start_time,
    	        e.end_time,
    	        sub.subject_name,
    	        sub.subject_code,
    	        sub.semester,
    	        h.hall_name,
    	        sa.seat_no

    	    FROM seating_arrangement sa

    	    JOIN student s ON sa.student_id = s.student_id
    	    JOIN class c ON sa.class_id = c.class_id
    	    JOIN exam e ON sa.exam_id = e.exam_id
    	    JOIN subject sub ON sa.subject_id = sub.subject_id
    	    JOIN exam_hall h ON sa.hall_id = h.hall_id

    	    WHERE sa.exam_id = :examId 
    	    AND sa.hall_id = :hallId
    	    
    	    ORDER BY sa.seating_id ASC;

    	    ORDER BY CAST(SUBSTRING_INDEX(sa.seat_no, 'L', -1) AS UNSIGNED)
    	    
    	    """, nativeQuery = true)
    	    List<Map<String, Object>> getSeatingData(@Param("examId") int examId,
    	                                             @Param("hallId") int hallId);

	void deleteByExam_ExamIdAndHall_HallId(int examId, int hallId);
*/
    @Query(value = """
    		SELECT 
    		    sa.seat_no,
    		    s.roll_no,
    		    c.class_name,
    		    sub.subject_name,
    		    sub.subject_code,
    		    sub.semester,
    		    e.exam_name,
    		    e.exam_date,
    		    e.start_time,
    		    e.end_time,
    		    h.hall_name
    		FROM seating_arrangement sa
    		LEFT JOIN student s ON sa.student_id = s.student_id
    		LEFT JOIN class c ON sa.class_id = c.class_id
    		LEFT JOIN subject sub ON sa.subject_id = sub.subject_id
    		LEFT JOIN exam e ON sa.exam_id = e.exam_id
    		LEFT JOIN exam_hall h ON sa.hall_id = h.hall_id
    		WHERE sa.exam_id = :examId 
    		AND sa.hall_id = :hallId
    		ORDER BY CAST(SUBSTRING_INDEX(sa.seat_no, 'R', -1) AS UNSIGNED)
    		""", nativeQuery = true)
    		List<Map<String, Object>> getSeatingData(@Param("examId") int examId,
    		                                         @Param("hallId") int hallId);
	//List<ExamViewDTO> getStudentExams(int studentId);
	
	@Query("""
		    SELECT new com.college.exam.dto.ExamViewDTO(
		        e.examName,
		        e.examDate,
		        e.startTime,
		        e.endTime,
		        e.session,
		        sub.subjectName,
		        sub.subjectCode,
		        h.hallName
		    )
		    FROM SeatingArrangement sa
		    JOIN sa.exam e
		    JOIN sa.subject sub
		    JOIN sa.hall h
		    WHERE sa.student.id = :studentId
		    ORDER BY sa.student.rollNo ASC
		""")
		List<ExamViewDTO> getStudentExams(@Param("studentId") int studentId);
	
	
	
	@Query("""
			SELECT new com.college.exam.dto.SeatingViewDTO(
			    s.seatNo,
			    st.rollNo,
			    e.examName,
			    e.examDate,
			    e.session,
			    sub.subjectName,
			    sub.subjectCode,
			    c.className,
			    h.hallName,
			    e.startTime,
			    e.endTime,
			    sub.semester
			)
			FROM SeatingArrangement s
			JOIN s.student st
			JOIN s.exam e
			JOIN s.subject sub
			JOIN s.classEntity c
			JOIN s.hall h
			WHERE c.classId = :classId
			ORDER BY s.seatNo
			""")
			List<SeatingViewDTO> findByClassId(@Param("classId") Integer classId);

	void deleteByExam_ExamIdAndHall_HallId(int examId, int hallId);
	
	@Query("SELECT DISTINCT s.student.id FROM SeatingArrangement s")
	List<Long> findStudentIdsWithSeating();
}

	/*
	@Query("""
			SELECT new com.college.exam.dto.SeatingViewDTO(
			    s.seatNo,
			    st.rollNo,
			    e.examName,
			    e.examDate,
			    e.session,
			    sub.subjectName,
			    sub.subjectCode,
			    c.className,
			    h.hallName,
			    e.startTime,
			    e.endTime,
			    sub.semester
			)
			FROM SeatingArrangement s
			JOIN s.student st
			JOIN s.exam e
			JOIN s.subject sub
			JOIN s.classEntity c
			JOIN s.hall h
			WHERE st.rollNo = :rollNo
			""")
			List<SeatingViewDTO> findByRollNo(@Param("rollNo") String rollNo);
	 
}*/
    
    

