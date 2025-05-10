package xrddev.practiceportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import xrddev.practiceportal.config.ModelAttributes;
import xrddev.practiceportal.service.api.AnnouncementService;

@Controller
public class LandingPageController {

    private final AnnouncementService announcementService;

    public LandingPageController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(ModelAttributes.ANNOUNCEMENTS,announcementService.getAllAnnouncements());
        return "index";
    }
}