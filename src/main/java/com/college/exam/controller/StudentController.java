



/*

package com.college.exam.controller;

import com.college.exam.model.Student;
import com.college.exam.repository.SeatingRepository;
import com.college.exam.repository.StudentRepository;
import com.college.exam.service.ExamService;
import com.college.exam.dto.ExamViewDTO;
import com.college.exam.dto.SeatViewDTO;
import com.college.exam.dto.SeatingViewDTO;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;   // ✅ CORRECT IMPORT
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // ✅ ADD THIS (MISSING)
    @Autowired
    private ExamService examService;

    // ================= LOGIN PAGE =================
    @GetMapping("/login")
    public String loginPage() {
        return "student/student-login";
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String login(
            @RequestParam String rollNo,
            @RequestParam(required = false) String password,
            HttpSession session,
            Model model) {

        Optional<Student> studentOpt = studentRepository.findByRollNo(rollNo);

        if (studentOpt.isPresent()) {

            Student student = studentOpt.get();

            if (password == null || password.isEmpty()) {
                password = student.getRollNo();
            }

            if (student.getPassword() != null &&
                student.getPassword().equals(password)) {

                session.setAttribute("student", student);
                return "redirect:/student/dashboard";
            }
        }

        model.addAttribute("error", "Invalid Roll Number or Password");
        return "student/student-login";
    }

    // ================= DASHBOARD =================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/student/login";
        }

        model.addAttribute("student", student);
        return "student/student-dashboard";
    }

    // ================= VIEW EXAMS =================
    @GetMapping("/exams")
    public String viewExams(HttpSession session, Model model) {

        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/student/login";
        }

        List<ExamViewDTO> exams =
                examService.getStudentExams(student.getId());  // ✅ FIXED

        model.addAttribute("exams", exams);

        model.addAttribute("today", LocalDate.now());

        return "student/view-exams";
    }
    
    
    
    
    @Autowired
    private SeatingRepository seatingRepo;
    
    
    @GetMapping("/seat")
    public String viewSeat(Model model, HttpSession session) {

        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/student/login";
        }

        List<SeatViewDTO> seats = seatingRepo.getStudentSeat(student.getId());

        model.addAttribute("seats", seats);

        return "student/view-seat";
    }
    
    
    @GetMapping("/seat")
    public String viewSeat(Model model, HttpSession session) {

        // ✅ Get logged-in student safely
        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/login";
        }

        // ✅ Fetch seating data
        List<SeatingViewDTO> seatingList =
                seatingService.getSeatingByStudent(student.getRollNo());

        // ✅ Prevent NULL issues
        if (seatingList == null || seatingList.isEmpty()) {
            model.addAttribute("message", "No seating assigned yet!");
        } else {
            model.addAttribute("seatingList", seatingList);
        }

        return "student/view-seating"; // make sure file exists
    }
    

    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }
}


*/
package com.college.exam.controller;

import com.college.exam.model.Student;
import com.college.exam.repository.StudentRepository;
import com.college.exam.service.ExamService;
import com.college.exam.service.SeatingService;
import com.college.exam.dto.ExamViewDTO;
import com.college.exam.dto.SeatingViewDTO;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamService examService;

    // ✅ FIXED: added missing service
    @Autowired
    private SeatingService seatingService;

    // ================= LOGIN PAGE =================
    @GetMapping("/login")
    public String loginPage() {
        return "student/student-login";
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String login(
            @RequestParam String rollNo,
            @RequestParam(required = false) String password,
            HttpSession session,
            Model model) {

        Optional<Student> studentOpt = studentRepository.findByRollNo(rollNo);

        if (studentOpt.isPresent()) {

            Student student = studentOpt.get();

            if (password == null || password.isEmpty()) {
                password = student.getRollNo();
            }

            if (student.getPassword() != null &&
                student.getPassword().equals(password)) {

                session.setAttribute("student", student);
                return "redirect:/student/dashboard";
            }
        }

        model.addAttribute("error", "Invalid Roll Number or Password");
        return "student/student-login";
    }

    // ================= DASHBOARD =================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/student/login";
        }

        model.addAttribute("student", student);
        return "student/student-dashboard";
    }

    // ================= VIEW EXAMS =================
    @GetMapping("/exams")
    public String viewExams(HttpSession session, Model model) {

        Student student = (Student) session.getAttribute("student");

        // ✅ FIXED: session check
        if (student == null) {
            return "redirect:/student/login";
        }

        List<ExamViewDTO> exams =
                examService.getStudentExams(student.getId());

        model.addAttribute("exams", exams);
        model.addAttribute("today", LocalDate.now());

        return "student/view-exams";
    }

    // ================= VIEW SEAT =================
    
    
    @GetMapping("/seat")
    public String viewSeat(Model model, HttpSession session) {

        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/student/login";
        }

        // ✅ Use classId instead of rollNo
        List<SeatingViewDTO> seatingList =
                seatingService.getSeatingByClass(student.getClassEntity().getClassId());

        model.addAttribute("seatingList", seatingList);

        return "student/view-seating";
    }
    
    
    @GetMapping("/change-password")
    public String showChangePasswordPage(HttpSession session) {

        if (session.getAttribute("student") == null) {
            return "redirect:/student/login";
        }

        return "student/change-password"; 
    }
   
    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            HttpSession session,
            Model model) {

        Student student = (Student) session.getAttribute("student");

        if (student == null) {
            return "redirect:/student/login";
        }

        // ✅ Null-safe password check
        if (student.getPassword() == null || 
            !student.getPassword().equals(currentPassword)) {

            model.addAttribute("error", "Current password is incorrect!");
            return "change-password";
        }

        // ✅ Match check
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "change-password";
        }

        // ✅ Prevent same password
        if (currentPassword.equals(newPassword)) {
            model.addAttribute("error", "New password cannot be same as old password!");
            return "change-password";
        }

        // ✅ Update password
        student.setPassword(newPassword);
        studentRepository.save(student);

        // ✅ Update session (important)
        session.setAttribute("student", student);

        model.addAttribute("success", "Password updated successfully!");

        return "student/change-password"; 
    }
    // ================= LOGOUT =================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/student/login";
    }
}





