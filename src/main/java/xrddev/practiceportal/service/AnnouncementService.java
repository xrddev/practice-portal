package xrddev.practiceportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xrddev.practiceportal.model.Announcement;
import xrddev.practiceportal.repository.AnnouncementRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll(); // Επιστροφή όλων των ανακοινώσεων
    }

    public void addSampleAnnouncement() {
        //Latest announcements on top
        Announcement announcement = new Announcement();
        announcement.setMessage("Internship 2024-2025: Call for Expression of Interest - ENDS 26.06.2025 (12:00 noon)");
        announcement.setDatePosted(LocalDate.of(2025, 5, 15));
        announcementRepository.save(announcement);

        announcement = new Announcement();
        announcement.setMessage("The internship application period is about to start. Stay tuned for more information!");
        announcement.setDatePosted(LocalDate.of(2025, 4, 20));
        announcementRepository.save(announcement);

        System.out.println("All announcements added");
    }
}