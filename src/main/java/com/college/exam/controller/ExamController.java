
package com.college.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.college.exam.entity.Exam;
import com.college.exam.repository.ExamRepository;

@Controller
@RequestMapping("/admin")
public class ExamController {

    @Autowired
    private ExamRepository examRepo;

    // ✅ SHOW EXAM FORM
    @GetMapping("/schedule-exam")
    public String showExamForm(Model model) {
        model.addAttribute("exam", new Exam());
        return "admin/schedule-exam";
    }

    // ✅ SAVE EXAM
    @PostMapping("/save-exam")
    public String saveExam(@ModelAttribute Exam exam, Model model) {

        // Basic Validation
        if (exam.getExamName() == null || exam.getExamName().isEmpty()) {
            model.addAttribute("error", "Exam name is required!");
            return "admin/schedule-exam";
        }

        if (exam.getStartTime().isAfter(exam.getEndTime())) {
            model.addAttribute("error", "Start time must be before end time!");
            return "admin/schedule-exam";
        }

        // Save to DB
        examRepo.save(exam);

        // Success message
        model.addAttribute("success", "Exam scheduled successfully!");

        return "admin/schedule-exam";
    }
}
/*
package com.college.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.college.exam.entity.Exam;
import com.college.exam.repository.ExamRepository;
import com.college.exam.repository.DepartmentRepository;

@Controller
@RequestMapping("/admin")   // base path
public class ExamController {

    @Autowired
    private ExamRepository examRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    
    @GetMapping("/schedule-exam")
    public String showExamForm(Model model) {
        model.addAttribute("exam", new Exam());
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/schedule-exam";   // templates/admin/schedule-exam.html
    }

    
    @PostMapping("/save-exam")
    public String saveExam(@ModelAttribute Exam exam) {
        examRepo.save(exam);
        return "redirect:/admin/dashboard";
    }

    
    
}*/























































