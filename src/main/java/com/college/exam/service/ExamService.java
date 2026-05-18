/*package com.college.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.exam.DTO.ExamViewDTO;
import com.college.exam.repository.SeatingRepository;

@Service
public class ExamService {
	
	
	    @Autowired
	    private SeatingRepository seatingRepo;

	    
	}*/

package com.college.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.exam.dto.ExamViewDTO;
import com.college.exam.repository.SeatingRepository;

@Service
public class ExamService {

    @Autowired
    private SeatingRepository seatingRepo;

    public List<ExamViewDTO> getStudentExams(int studentId) {
        return seatingRepo.getStudentExams(studentId);
    }
}


