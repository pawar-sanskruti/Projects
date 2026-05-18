package com.college.exam.dto;

public class SeatingDashboardDTO {

    private String examName;
    private String examDate;
    private String subjectName;
    private String hallName;

    // ✅ Default Constructor (IMPORTANT)
    public SeatingDashboardDTO() {
    }

    // ✅ Parameterized Constructor
    public SeatingDashboardDTO(String examName, String examDate, String subjectName, String hallName) {
        this.examName = examName;
        this.examDate = examDate;
        this.subjectName = subjectName;
        this.hallName = hallName;
    }

    // ✅ Getters
    public String getExamName() {
        return examName;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getHallName() {
        return hallName;
    }

    // ✅ Setters
    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
}