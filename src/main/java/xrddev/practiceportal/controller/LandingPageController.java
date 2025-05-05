package xrddev.practiceportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xrddev.practiceportal.config.SessionKeys;
import xrddev.practiceportal.repository.AnnouncementRepository;
import xrddev.practiceportal.service.AnnouncementService;

@Controller
public class LandingPageController {

    private final AnnouncementService announcementService;

    public LandingPageController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(SessionKeys.ANNOUNCEMENTS,announcementService.getAllAnnouncements());
        return "index";
    }
}