package com.college.exam.controller;

import com.college.exam.entity.ExamHall;
import com.college.exam.repository.HallRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class HallController {

    @Autowired
    private HallRepository hallRepo;

    // open add form
    @GetMapping("/add-hall")
    public String showForm(Model model) {
        model.addAttribute("hall", new ExamHall());
        return "admin/add-hall";
    }

    // save hall
    @PostMapping("/save-hall")
    public String saveHall(@ModelAttribute ExamHall hall) {

        // ❌ REMOVE old calculation
        // hall.setCapacity(hall.getTotalRows() * hall.getTotalColumns());

        // ✔ Just save directly
        hallRepo.save(hall);

        return "redirect:/admin/view-halls";
    }

    // list halls
    @GetMapping("/view-halls")
    public String viewHalls(Model model) {
        model.addAttribute("halls", hallRepo.findAll());
        model.addAttribute("totalHalls", hallRepo.count());
        return "admin/view-halls";
    }

    // delete hall
    @GetMapping("/delete-hall/{id}")
    public String deleteHall(@PathVariable Integer id) {
        hallRepo.deleteById(id);
        return "redirect:/admin/view-halls";
    }
}