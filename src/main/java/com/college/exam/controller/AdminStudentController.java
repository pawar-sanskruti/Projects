package com.college.exam.controller;

import com.college.exam.entity.ClassEntity;
import com.college.exam.model.Student;
import com.college.exam.repository.StudentRepository;
import com.college.exam.repository.ClassRepository;
import com.college.exam.service.CSVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminStudentController {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private CSVService csvService; // ✅ MISSING SERVICE ADDED

    // ✅ Show Add Student Form
    @GetMapping("/add-student")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("classList", classRepository.findAll());
        return "admin/add-student";
    }

    // ✅ Save Student
    @PostMapping("/save-student")
    public String saveStudent(@ModelAttribute Student student,
                              RedirectAttributes redirectAttributes) {

        try {
            // ✅ NULL CHECK (important)
            if (student.getClassEntity() == null || 
                student.getClassEntity().getClassId() == 0) {

                throw new RuntimeException("Please select a class");
            }

            // ✅ Fetch class from DB
            ClassEntity cls = classRepository.findById(
                    student.getClassEntity().getClassId()
            ).orElseThrow(() -> new RuntimeException("Class not found"));

            student.setClassEntity(cls);

            studentRepo.save(student);

            redirectAttributes.addFlashAttribute("success", "Student added successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/add-student";
    }

    // ✅ Upload CSV
    @PostMapping("/students/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Please select a CSV file");
            }

            csvService.saveStudentsFromCSV(file);

            redirectAttributes.addFlashAttribute("success", "CSV Uploaded Successfully!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/add-student"; // ✅ redirect fixed
    }
}