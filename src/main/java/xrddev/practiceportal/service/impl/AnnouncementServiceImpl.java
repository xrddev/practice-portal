package xrddev.practiceportal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import xrddev.practiceportal.model.announcement.Announcement;
import xrddev.practiceportal.repository.api.AnnouncementRepository;
import xrddev.practiceportal.service.api.AnnouncementService;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }
}