package xrddev.practiceportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticePortalApplication {
	public static void main(String[] args) {
		var context = SpringApplication.run(PracticePortalApplication.class, args);

		//Add announcements to the home page.
		var announcementService = context.getBean(xrddev.practiceportal.service.AnnouncementService.class);
		announcementService.addSampleAnnouncement();
	}
}