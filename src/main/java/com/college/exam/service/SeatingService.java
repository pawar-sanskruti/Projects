package com.college.exam.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.exam.dto.SeatingViewDTO;
import com.college.exam.entity.*;
import com.college.exam.model.Student;
import com.college.exam.repository.*;

@Service
public class SeatingService {

    @Autowired private StudentRepository studentRepo;
    @Autowired private SeatingRepository seatingRepo;
    @Autowired private ClassRepository classRepo;
    @Autowired private ExamRepository examRepo;
    @Autowired private SubjectRepository subjectRepo;
    @Autowired private HallRepository hallRepo;

    // =========================================================
    // ✅ COMMON SORT (FIXES CS1, CS10 ISSUE)
    // =========================================================
    private void sortStudents(List<Student> students) {
        students.sort(Comparator.comparingInt(s -> {
            try {
                return Integer.parseInt(s.getRollNo().replaceAll("\\D", ""));
            } catch (Exception e) {
                return Integer.MAX_VALUE;
            }
        }));
    }

    // =========================================================
    // ✅ SINGLE CLASS SEATING
    // =========================================================
    public void generateSingleClassSeating(int classId, int examId, int subjectId, int hallId) {

        List<Student> students = studentRepo.findByClassEntity_ClassId(classId);

        if (students.isEmpty()) {
            throw new RuntimeException("No students found!");
        }

        sortStudents(students);

        Exam exam = examRepo.findById(examId).orElseThrow();
        Subject subject = subjectRepo.findById(subjectId).orElseThrow();
        ClassEntity cls = classRepo.findById(classId).orElseThrow();
        ExamHall hall = hallRepo.findById(hallId).orElseThrow();

        seatingRepo.deleteByExam_ExamIdAndHall_HallId(examId, hallId);

        int index = 0;

        for (int i = 1; i <= hall.getCapacity(); i++) {

            SeatingArrangement seat = new SeatingArrangement();

            seat.setExam(exam);
            seat.setSubject(subject);
            seat.setClassEntity(cls);
            seat.setHall(hall);

            if (index < students.size()) {
                seat.setStudent(students.get(index++));
            } else {
                seat.setStudent(null); // empty seat
            }

            seat.setSeatNo(hall.getHallName() + "-R" + i);

            seatingRepo.save(seat);
        }

        System.out.println("✅ Single class seating generated!");
    }

    // =========================================================
    // ✅ TWO CLASS BLOCK SEATING
    // (First Class1 → Then Class2)
    // =========================================================
    public void generateTwoClassSeating(
            int class1Id, int subject1Id,
            int class2Id, int subject2Id,
            int examId, int hallId
    ) {

        List<Student> class1 = studentRepo.findByClassEntity_ClassId(class1Id);
        List<Student> class2 = studentRepo.findByClassEntity_ClassId(class2Id);

        if (class1.isEmpty() && class2.isEmpty()) {
            throw new RuntimeException("No students found in both classes!");
        }

        sortStudents(class1);
        sortStudents(class2);

        Exam exam = examRepo.findById(examId).orElseThrow();

        Subject subject1 = subjectRepo.findById(subject1Id).orElseThrow();
        Subject subject2 = subjectRepo.findById(subject2Id).orElseThrow();

        ClassEntity cls1 = classRepo.findById(class1Id).orElseThrow();
        ClassEntity cls2 = classRepo.findById(class2Id).orElseThrow();

        ExamHall hall = hallRepo.findById(hallId).orElseThrow();

        seatingRepo.deleteByExam_ExamIdAndHall_HallId(examId, hallId);

        int capacity = hall.getCapacity();

        int index1 = 0;
        int index2 = 0;

        for (int i = 1; i <= capacity; i++) {

            SeatingArrangement seat = new SeatingArrangement();

            seat.setExam(exam);
            seat.setHall(hall);

            // ✅ FIRST fill Class 1 completely
            if (index1 < class1.size()) {

                seat.setStudent(class1.get(index1++));
                seat.setClassEntity(cls1);
                seat.setSubject(subject1);

            }
            // ✅ THEN start Class 2
            else if (index2 < class2.size()) {

                seat.setStudent(class2.get(index2++));
                seat.setClassEntity(cls2);
                seat.setSubject(subject2);

            }
            // ✅ Remaining seats empty
            else {
                seat.setStudent(null);
            }

            seat.setSeatNo(hall.getHallName() + "-R" + i);

            seatingRepo.save(seat);
        }

        if (index2 < class2.size()) {
            System.out.println("⚠️ Not enough seats for class 2!");
        }

        System.out.println("✅ Two-class seating generated!");
    }

    // =========================================================
    // ✅ VIEW BY CLASS
    // =========================================================
    public List<SeatingViewDTO> getSeatingByClass(Integer classId) {
        return seatingRepo.findByClassId(classId);
    }

    // =========================================================
    // ✅ ADMIN VIEW
    // =========================================================
    public List<SeatingArrangement> getSeatingByExam(int examId) {
        return seatingRepo.findByExam_ExamId(examId);
    }
}


/*
package com.college.exam.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.exam.dto.SeatingViewDTO;
import com.college.exam.entity.ClassEntity;
import com.college.exam.entity.Exam;
import com.college.exam.entity.ExamHall;
import com.college.exam.entity.SeatingArrangement;
import com.college.exam.entity.Subject;
import com.college.exam.model.Student;
import com.college.exam.repository.ClassRepository;
import com.college.exam.repository.ExamRepository;
import com.college.exam.repository.HallRepository;
import com.college.exam.repository.SeatingRepository;
import com.college.exam.repository.StudentRepository;
import com.college.exam.repository.SubjectRepository;

@Service
public class SeatingService {

    @Autowired private StudentRepository studentRepo;
    @Autowired private SeatingRepository seatingRepo;
    @Autowired private ClassRepository classRepo;
    @Autowired private ExamRepository examRepo;
    @Autowired private SubjectRepository subjectRepo;
    @Autowired private HallRepository hallRepo;

    public void generateSeating(int classId, int examId, int subjectId, int hallId) {

        // ✅ Fetch students
        List<Student> students = studentRepo.findByClassEntity_ClassId(classId);

        if (students.isEmpty()) {
            throw new RuntimeException("No students found for selected class!");
        }

        // ✅ SORT students by roll number (VERY IMPORTANT)
        students.sort(Comparator.comparing(Student::getRollNo));

        // ✅ Fetch other data
        Exam exam = examRepo.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        ClassEntity cls = classRepo.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        ExamHall hall = hallRepo.findById(hallId)
                .orElseThrow(() -> new RuntimeException("Hall not found"));

        int capacity = hall.getCapacity();
        String hallName = hall.getHallName();

        // ✅ Delete ONLY this exam + hall data (SAFE)
        seatingRepo.deleteByExam_ExamIdAndHall_HallId(examId, hallId);

        int studentIndex = 0;

        // ✅ Loop FULL capacity
        for (int seatNumber = 1; seatNumber <= capacity; seatNumber++) {

            SeatingArrangement seat = new SeatingArrangement();

            seat.setExam(exam);
            seat.setSubject(subject);
            seat.setClassEntity(cls);
            seat.setHall(hall);

            // ✅ Assign student OR empty
            if (studentIndex < students.size()) {
                seat.setStudent(students.get(studentIndex++));
            } else {
                seat.setStudent(null); // EMPTY seat
            }

            // ✅ CORRECT Seat Format (as per your sheet)
            String seatNo = hallName + " - R " + seatNumber;
            seat.setSeatNo(seatNo);

            seatingRepo.save(seat);
        }

        System.out.println("Seating generated successfully with EMPTY seats!");
    }
    public List<SeatingViewDTO> getSeatingByClass(Integer classId) {
        return seatingRepo.findByClassId(classId);
    }
    // ✅ OPTIONAL (Admin View)
    public List<SeatingArrangement> getSeatingByExam(int examId) {
        return seatingRepo.findByExam_ExamId(examId);
    }

    
}

*/

   