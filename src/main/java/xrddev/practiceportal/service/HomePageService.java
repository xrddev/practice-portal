package xrddev.practiceportal.service;

import org.springframework.stereotype.Service;

@Service
public class HomePageService {

    public String getWelcomeMessage(){
        return "Welcome to Practice Portal!";
    }

    public String getPageTitle(){
        return "Practice Portal";
    }


}
