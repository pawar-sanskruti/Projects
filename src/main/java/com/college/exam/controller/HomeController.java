package com.college.exam.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // loads home.html
    }   
        


    
/*
 * @GetMapping("/admin-login")
        public String adminLogin() {
            return "admin-login";
            
        @GetMapping("/student-login")
        public String studentLogin() {
            return "student-login";
        }

        @GetMapping("/faculty-login")
        public String facultyLogin() {
            return "faculty-login";
    }*/
}