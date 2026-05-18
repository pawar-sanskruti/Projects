package com.college.exam.dto;

import java.time.LocalTime;

public class SeatViewDTO {

    private String rollNo;
    private String studentName;
    private String className;
    private String examName;
    private String session;
    private LocalTime startTime;
    private LocalTime endTime;
    private String subjectName;
    private String subjectCode;
    private String hallName;
    private String seatNo;

    // ✅ Constructor MUST MATCH QUERY EXACTLY
    public SeatViewDTO(String rollNo, String studentName, String className,
                       String examName, String session, LocalTime startTime,
                       LocalTime endTime, String subjectName, String subjectCode,
                       String hallName, String seatNo) {

        this.rollNo = rollNo;
        this.studentName = studentName;
        this.className = className;
        this.examName = examName;
        this.session = session;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.hallName = hallName;
        this.seatNo = seatNo;
    }

    // Getters & Setters

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getExamName() { return examName; }
    public void setExamName(String examName) { this.examName = examName; }

    public String getSession() { return session; }
    public void setSession(String session) { this.session = session; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }

    public String getHallName() { return hallName; }
    public void setHallName(String hallName) { this.hallName = hallName; }

    public String getSeatNo() { return seatNo; }
    public void setSeatNo(String seatNo) { this.seatNo = seatNo; }
}