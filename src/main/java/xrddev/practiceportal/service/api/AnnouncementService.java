package xrddev.practiceportal.service.api;

import java.util.List;
import xrddev.practiceportal.model.announcement.Announcement;

public interface AnnouncementService {
    List<Announcement> getAllAnnouncements();
}