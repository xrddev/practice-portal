package xrddev.practiceportal.service.annoucment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import xrddev.practiceportal.model.announcement.Announcement;
import xrddev.practiceportal.repository.AnnouncementRepository;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }
}