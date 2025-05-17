package xrddev.practiceportal.service.annoucment;

import java.util.List;
import xrddev.practiceportal.model.announcement.Announcement;

public interface AnnouncementService {
    List<Announcement> getAllAnnouncements();
}