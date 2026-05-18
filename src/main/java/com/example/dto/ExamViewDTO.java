package com.example.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExamViewDTO {

    private String examName;
    private LocalDate examDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String session;

    private String subjectName;
    private String subjectCode;
    private String hallName;

    // ✅ Default Constructor (Required)
    public ExamViewDTO() {
    }

    // ✅ Parameterized Constructor
    public ExamViewDTO(String examName, LocalDate examDate,
                       LocalTime startTime, LocalTime endTime,
                       String session,
                       String subjectName, String subjectCode,
                       String hallName) {

        this.examName = examName;
        this.examDate = examDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.session = session;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.hallName = hallName;
    }

    // ✅ GETTERS

    public String getExamName() {
        return examName;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getSession() {
        return session;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getHallName() {
        return hallName;
    }

    // ✅ SETTERS

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
}