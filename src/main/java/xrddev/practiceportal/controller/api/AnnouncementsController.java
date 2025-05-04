package xrddev.practiceportal.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.model.Announcement;
import xrddev.practiceportal.repository.AnnouncementRepository;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementsController {

    private final AnnouncementRepository announcementRepository;
    private static final String API_TOKEN = "my-secure-api-token"; // Σταθερό token

    public AnnouncementsController(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAnnouncements(
            @RequestHeader(value = "X-API-TOKEN", required = false) String token) {
        // Έλεγχος αν το token είναι έγκυρο
        if (!API_TOKEN.equals(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: Invalid or missing API token");
        }

        // Επιστροφή όλων των ανακοινώσεων αν το token είναι σωστό
        List<Announcement> announcements = announcementRepository.findAll();
        return ResponseEntity.ok(announcements);
    }

    @PostMapping
    public ResponseEntity<?> addAnnouncement(
            @RequestHeader(value = "X-API-TOKEN", required = false) String token,
            @RequestBody Announcement announcement) {
        // Έλεγχος token πριν αποθηκεύσουμε μια νέα ανακοίνωση
        if (!API_TOKEN.equals(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: Invalid or missing API token");
        }

        // Αποθήκευση της ανακοίνωσης
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAnnouncement);
    }
}