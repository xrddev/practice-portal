package xrddev.practiceportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {

    @GetMapping("/")
    public String home(Model model) {
        // Τίτλος της σελίδας
        model.addAttribute("pageTitle", "Αρχική Σελίδα");

        // Μήνυμα καλωσορίσματος
        model.addAttribute("welcomeMessage", "Καλώς ήρθες στο Practice Portal!");

        // Ονομασία του Thymeleaf template που θα επιστρέψουμε
        return "index"; // Στον φάκελο templates, αναμένουμε αρχείο: index.html
    }
}