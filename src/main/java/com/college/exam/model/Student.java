package com.college.exam.model;

import jakarta.persistence.*;
import com.college.exam.entity.ClassEntity;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "roll_no", unique = true, nullable = false)
    private String rollNo;

    @Column(nullable = false)
    private String name;

    private String password;
    
    @Column(name = "admission_status")
    private String admissionStatus;
    
    
    private String division;
    private String gender;

    @Column(name = "library_id")
    private Integer libraryId;

    // ✅ CORRECT RELATION
    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    // Getters & Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    
    public String getAdmissionStatus() {
        return admissionStatus;
    }

    // Setter
    public void setAdmissionStatus(String admissionStatus) {
        this.admissionStatus = admissionStatus;
    }
    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getLibraryId() { return libraryId; }
    public void setLibraryId(Integer libraryId) { this.libraryId = libraryId; }

    public ClassEntity getClassEntity() { return classEntity; }
    public void setClassEntity(ClassEntity classEntity) { this.classEntity = classEntity; }
	
}








