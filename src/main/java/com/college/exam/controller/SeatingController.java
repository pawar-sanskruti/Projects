package com.college.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.college.exam.service.*;

import com.college.exam.entity.ClassEntity;
import com.college.exam.entity.Exam;
import com.college.exam.entity.ExamHall;
import com.college.exam.entity.SeatingArrangement;
import com.college.exam.entity.Subject;
import com.college.exam.repository.*;

@Controller
@RequestMapping("/admin")
public class SeatingController {

    @Autowired private SeatingService seatingService;
    @Autowired private ExamRepository examRepo;
    @Autowired private ClassRepository classRepo;
    @Autowired private SubjectRepository subjectRepo;
    @Autowired private HallRepository hallRepo;
    @Autowired
    private SeatingRepository seatingRepo;


    // Load Page
    @GetMapping("/generate-seating")
    public String loadPage(Model model) {

        model.addAttribute("exams", examRepo.findAll());
        model.addAttribute("classes", classRepo.findAll());
        model.addAttribute("subjects", subjectRepo.findAll());
        model.addAttribute("halls", hallRepo.findAll());

        return "admin/generate-seating";
    }

    
    
    @PostMapping("/generate-seating")
    public String generateSeating(
            @RequestParam int examId,
            @RequestParam int hallId,

            @RequestParam int class1Id,
            @RequestParam int subject1Id,

            @RequestParam(required = false) Integer class2Id,
            @RequestParam(required = false) Integer subject2Id
    ) {

        // ✅ If second class NOT selected → single class seating
        if (class2Id == null || subject2Id == null) {

            seatingService.generateSingleClassSeating(
                    class1Id, examId, subject1Id, hallId
            );

        } else {
            // ✅ Two class block seating
            seatingService.generateTwoClassSeating(
                    class1Id, subject1Id,
                    class2Id, subject2Id,
                    examId, hallId
            );
        }

        return "redirect:/admin/generate-seating?success";
    }
    
   
    @GetMapping("/view-seating")
    public String viewSeating(@RequestParam(required = false) Integer examId,
                              @RequestParam(required = false) Integer hallId,
                              Model model) {

        if (examId == null) {
            model.addAttribute("error", "Please select an exam first!");
            model.addAttribute("exams", examRepo.findAll());
            model.addAttribute("halls", hallRepo.findAll());
            return "admin/select-seating";
        }

        if (hallId == null) {
            hallId = hallRepo.findFirstHallId();
        }

        List<Map<String, Object>> seatingList =
                seatingRepo.getSeatingData(examId, hallId);

        if (seatingList.isEmpty()) {
            model.addAttribute("error", "No seating arrangement found!");
            return "admin/view-seating";
        }

        // ✅ Extract Class 1 & Class 2 manually
        String class1 = null;
        String class2 = null;

        for (Map<String, Object> row : seatingList) {
            String cls = (String) row.get("class_name");

            if (class1 == null) {
                class1 = cls;
            } else if (!class1.equals(cls)) {
                class2 = cls;
                break;
            }
        }

        model.addAttribute("seatingList", seatingList);
        model.addAttribute("class1", class1);
        model.addAttribute("class2", class2);
        model.addAttribute("examId", examId);
        model.addAttribute("hallId", hallId);

        return "admin/view-seating";
    }
}