package com.college.exam.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.college.exam.entity.ClassEntity;
import com.college.exam.model.Student;
import com.college.exam.repository.ClassRepository;
import com.college.exam.repository.StudentRepository;

@Service
public class CSVService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    public void saveStudentsFromCSV(MultipartFile file) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;

        br.readLine(); // skip header

        int success = 0;
        int failed = 0;

        while ((line = br.readLine()) != null) {

            try {
                String[] data = line.split(",");

                // ✅ Accept 7 OR 8 columns
                if (data.length < 7) {
                    System.out.println("Skipped (invalid columns): " + line);
                    failed++;
                    continue;
                }

                String rollNo = data[0].trim();
                String name = data[1].trim();
                String admissionStatus = data[2].trim();
                String division = data[3].trim();
                String gender = data[4].trim();
                Integer libraryId = Integer.parseInt(data[5].trim());
                String className = data[6].trim();

                // ✅ Password handling
                String password;
                if (data.length >= 8 && !data[7].trim().isEmpty()) {
                    password = data[7].trim();
                } else {
                    password = "1234"; // default password
                }

                // ✅ Find class
                ClassEntity classEntity = classRepository.findByClassName(className);

                if (classEntity == null) {
                    System.out.println("Class not found: " + className);
                    failed++;
                    continue;
                }

                // ✅ Create student
                Student student = new Student();
                student.setRollNo(rollNo);
                student.setName(name);
                student.setPassword(password);
                student.setAdmissionStatus(admissionStatus);
                student.setDivision(division);
                student.setGender(gender);
                student.setLibraryId(libraryId);
                student.setClassEntity(classEntity);

                studentRepository.save(student);
                success++;

            } catch (Exception e) {
                System.out.println("Error in row: " + line);
                e.printStackTrace();
                failed++;
            }
        }

        br.close();

        System.out.println("✅ Upload Completed: " + success + " success, " + failed + " failed");
    }
}

/*
package com.college.exam.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.college.exam.entity.ClassEntity;
import com.college.exam.model.Student;
import com.college.exam.repository.ClassRepository;
import com.college.exam.repository.StudentRepository;

@Service
public class CSVService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    public int saveStudentsFromCSV(MultipartFile file) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        int count = 0;

        br.readLine(); // ✅ skip header

        while ((line = br.readLine()) != null) {

            try {
                String[] data = line.split(",");

                // ✅ Ensure 8 columns
                if (data.length < 8) {
                    System.out.println("Skipped (invalid columns): " + line);
                    continue;
                }

                String rollNo = data[0].trim();
                String name = data[1].trim();
                String status = data[2].trim();
                String division = data[3].trim();
                String gender = data[4].trim();
                String libraryStr = data[5].trim();
                String className = data[6].trim();
                String password = data[7].trim();

                // ✅ Required check
                if (rollNo.isEmpty() || name.isEmpty() || className.isEmpty()) {
                    System.out.println("Skipped (missing data): " + line);
                    continue;
                }

                // ✅ Fix scientific format
                if (rollNo.contains("E")) {
                    rollNo = String.format("%.0f", Double.parseDouble(rollNo));
                }

                // ✅ Clean class name
                className = className.replaceAll("\\s+", " ").trim();

                // ✅ Get class
                ClassEntity classEntity = classRepository.findByClassName(className);

                if (classEntity == null) {
                    System.out.println("Class not found: " + className);
                    continue;
                }

                // ✅ Skip duplicate
                if (studentRepository.findByRollNo(rollNo).isPresent()) {
                    System.out.println("Duplicate skipped: " + rollNo);
                    continue;
                }

                // ✅ Library ID
                Integer libraryId = null;
                if (!libraryStr.isEmpty()) {
                    libraryId = Integer.parseInt(libraryStr);
                }

                // ✅ Default password
                if (password.isEmpty()) {
                    password = rollNo;
                }

                // ✅ Save student
                Student student = new Student();
                student.setRollNo(rollNo);
                student.setName(name);
                student.setPassword(password);
                student.setAdmissionStatus(status);
                student.setDivision(division);
                student.setGender(gender);
                student.setLibraryId(libraryId);
                student.setClassEntity(classEntity);

                studentRepository.save(student);
                count++;

            } catch (Exception e) {
                System.out.println("Error row: " + line);
                System.out.println("Reason: " + e.getMessage());
            }
        }

        br.close();
        return count;
    }
}*/









































/*package com.college.exam.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.college.exam.entity.ClassEntity;
import com.college.exam.model.Student;
import com.college.exam.repository.ClassRepository;
import com.college.exam.repository.StudentRepository;

@Service
public class CSVService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    public int saveStudentsFromCSV(MultipartFile file) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;

        int count = 0;

        br.readLine(); // skip header

        while ((line = br.readLine()) != null) {

            try {
                String[] data = line.split(",");

                // ✅ Validate column count
                if (data.length < 8) {
                    System.out.println("Invalid row skipped: " + line);
                    continue;
                }

                String rollNo = data[0].trim();
                String name = data[1].trim();
                String password = data[2].trim();
                String admissionStatus = data[3].trim();
                String division = data[4].trim();
                String gender = data[5].trim();

                // ✅ Safe libraryId parsing
                Integer libraryId = null;
                if (data[6] != null && !data[6].trim().isEmpty()) {
                    libraryId = Integer.parseInt(data[6].trim());
                }

                String className = data[7].trim();

                // ✅ Convert class_name → class_id
                ClassEntity classEntity = classRepository.findByClassName(className);

                if (classEntity == null) {
                    throw new RuntimeException("Class not found: " + className);
                }

                // ✅ Skip duplicate roll_no
                if (studentRepository.findByRollNo(rollNo).isPresent()) {
                    System.out.println("Duplicate skipped: " + rollNo);
                    continue;
                }

                // ✅ Create student
                Student student = new Student();
                student.setRollNo(rollNo);
                student.setName(name);

                // default password if empty
                student.setPassword(password.isEmpty() ? rollNo : password);

                student.setAdmissionStatus(admissionStatus);
                student.setDivision(division);
                student.setGender(gender);
                student.setLibraryId(libraryId);

                student.setClassEntity(classEntity);

                studentRepository.save(student);
                count++;

            } catch (Exception e) {
                // ✅ Skip bad rows (DO NOT CRASH)
                System.out.println("Error in row: " + line);
                System.out.println("Reason: " + e.getMessage());
            }
        }

        br.close();

        return count; // return total inserted
    }
}*/