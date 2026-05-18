package com.college.exam.entity;

import com.college.exam.model.Student;

import jakarta.persistence.*;
@Entity
@Table(name = "seating_arrangement")
public class SeatingArrangement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatingId;

 /*   @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;*/
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private ExamHall hall;

    private String seatNo;
    
    public int getSeatingId() {
        return seatingId;
    }

    public void setSeatingId(int seatingId) {
        this.seatingId = seatingId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public ExamHall getHall() {
        return hall;
    }

    public void setHall(ExamHall hall) {
        this.hall = hall;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
}
/*
@Entity
@Table(name = "seating_arrangement")
public class SeatingArrangement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;

    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "hall_id")
    private Integer hallId;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "row_no")
    private Integer rowNo;

    @Column(name = "column_no")
    private Integer columnNo;

   

    public SeatingArrangement() {
    }

    public SeatingArrangement(Integer examId, Integer hallId,
                              Integer studentId, String seatNumber,
                              Integer rowNo, Integer columnNo) {
        this.examId = examId;
        this.hallId = hallId;
        this.studentId = studentId;
        this.seatNumber = seatNumber;
        this.rowNo = rowNo;
        this.columnNo = columnNo;
    }

    

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public Integer getColumnNo() {
        return columnNo;
    }

    public void setColumnNo(Integer columnNo) {
        this.columnNo = columnNo;
    }
}*/