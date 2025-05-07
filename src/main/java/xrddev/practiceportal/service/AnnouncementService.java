package xrddev.practiceportal.service;

import org.springframework.stereotype.Service;
import xrddev.practiceportal.model.Announcement;
import xrddev.practiceportal.repository.AnnouncementRepository;

import java.util.List;

@Service
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }
}