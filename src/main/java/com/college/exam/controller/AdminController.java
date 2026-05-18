package com.college.exam.controller;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.college.exam.model.Student;
import com.college.exam.repository.*;
import com.college.exam.service.CSVService;
import com.college.exam.service.SeatingDashboardService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private ExamRepository examRepository;
    
    @Autowired
    private SeatingDashboardService seatingService;
    
    @Autowired
    private CSVService csvService;
    @Autowired
    private SeatingRepository seatingRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "admin/admin-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        if (username.equals("admin") && password.equals("admin123")) {
            session.setAttribute("admin", true);
            return "redirect:/admin/dashboard";
        }

        return "admin/admin-login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute("studentCount", studentRepository.count());
        model.addAttribute("hallCount", hallRepository.count());
        model.addAttribute("examCount", examRepository.count());
        
        
        model.addAttribute("recentSeating", seatingService.getRecentSeating());



        return "admin/admin-dashboard";
    }
    
    // ✅ ADD THIS

    @GetMapping("/students")
    public String viewStudents(Model model) {

        // ✅ Get all students
        List<Student> students = studentRepository.findAll();

        // ✅ Efficient query (avoid loading full seating table)
        List<Long> seatedStudentIds = seatingRepository.findStudentIdsWithSeating();

        model.addAttribute("students", students);
        model.addAttribute("seatedIds", seatedStudentIds);

        return "admin/view-students";
    }
    

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }
/*
    // ✅ CSV UPLOAD
    @PostMapping("/students/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {

        try {
            csvService.saveStudentsFromCSV(file);
            redirectAttributes.addFlashAttribute("success", "CSV Uploaded Successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/dashboard";
    }*/
}