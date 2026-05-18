package com.college.exam.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SeatingViewDTO {

    private String seat_no;
    private String roll_no;

    private String exam_name;
    private LocalDate exam_date;
    private String session;

    private String subject_name;
    private String subject_code;

    private String class_name;
    private String hall_name;

    private LocalTime start_time;
    private LocalTime end_time;

    private Integer semester;

    // ✅ Constructor (IMPORTANT for JPQL query)
    public SeatingViewDTO(String seat_no,
                          String roll_no,
                          String exam_name,
                          LocalDate exam_date,
                          String session,
                          String subject_name,
                          String subject_code,
                          String class_name,
                          String hall_name,
                          LocalTime start_time,
                          LocalTime end_time,
                          Integer semester) {

        this.seat_no = seat_no;
        this.roll_no = roll_no;
        this.exam_name = exam_name;
        this.exam_date = exam_date;
        this.session = session;
        this.subject_name = subject_name;
        this.subject_code = subject_code;
        this.class_name = class_name;
        this.hall_name = hall_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.semester = semester;
    }

    // ✅ Getters & Setters

    public String getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public LocalDate getExam_date() {
        return exam_date;
    }

    public void setExam_date(LocalDate exam_date) {
        this.exam_date = exam_date;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}