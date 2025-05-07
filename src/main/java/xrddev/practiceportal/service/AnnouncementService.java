package xrddev.practiceportal.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import xrddev.practiceportal.model.Announcement;
import xrddev.practiceportal.repository.AnnouncementRepository;

import java.io.IOException;
import java.util.List;

@Component
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper mapper;

    public AnnouncementService(AnnouncementRepository announcementRepository,
                               ResourceLoader resourceLoader) {
        this.announcementRepository = announcementRepository;
        this.resourceLoader = resourceLoader;
        // ObjectMapper με υποστήριξη LocalDate
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    @PostConstruct
    private void loadAnnouncementsFromJson() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:announcements.json");
        List<Announcement> announcements = mapper.readValue(
                resource.getInputStream(),
                new TypeReference<>() {});
        announcementRepository.saveAll(announcements);
    }

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }
}