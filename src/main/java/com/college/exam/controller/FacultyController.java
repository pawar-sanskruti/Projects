package com.college.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.college.exam.entity.Faculty;
import com.college.exam.service.FacultyService;

@Controller
@RequestMapping("/faculty")   // ✅ base path for faculty
public class FacultyController {

    @Autowired
    private FacultyService service;

    // Show login page
    @GetMapping("/login")
    public String showLogin() {
        return "faculty/faculty-login";   // templates/faculty/faculty-login.html
    }

    // Process login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        Faculty faculty = service.login(username, password);

        if (faculty != null) {
            model.addAttribute("faculty", faculty);
            return "redirect:/faculty/dashboard";   // ✅ redirect after login
        }

        model.addAttribute("error", "Invalid Username or Password");
        return "faculty/faculty-login";
    }

    // Dashboard page
    @GetMapping("/dashboard")
    public String dashboard() {
        return "faculty/faculty-dashboard";  // templates/faculty/faculty-dashboard.html
    }
}












































/*package com.college.exam.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.college.exam.entity.Faculty;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @GetMapping("/login")
    public String loginPage() {
        return "faculty/faculty-login";
    }
    
    

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        if (username.equals("faculty") && password.equals("123")) {
            session.setAttribute("faculty", true);
            return "redirect:/faculty/dashboard";
        }

        return "faculty/faculty-login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("faculty") == null) {
            return "redirect:/faculty/login";
        }
        return "faculty/faculty-dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/faculty/login";
    }
}
*/











































/*package com.college.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.college.exam.entity.Faculty;
import com.college.exam.service.FacultyService;

@Controller
public class FacultyController {

    @Autowired
    FacultyService service;

    @GetMapping("/faculty-login")
    public String showLogin() {
        return "faculty-login";
    }

    @PostMapping("/faculty-login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        Faculty faculty = service.login(username, password);

        if(faculty != null) {
            model.addAttribute("faculty", faculty);
            return "faculty-dashboard";
        }

        model.addAttribute("error", "Invalid Username or Password");
        return "faculty-login";
    }
    
    @GetMapping("/faculty/dashboard")
    public String dashboard() {
        return "faculty-dashboard";
    }
}*/