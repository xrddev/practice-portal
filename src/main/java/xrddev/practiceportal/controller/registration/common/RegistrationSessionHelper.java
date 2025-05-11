package xrddev.practiceportal.controller.registration.common;

import jakarta.servlet.http.HttpSession;
import xrddev.practiceportal.config.SessionAttribute;

public abstract class RegistrationSessionHelper {
    protected void clearSession(HttpSession session) {
        session.removeAttribute(SessionAttribute.EMAIL);
        session.removeAttribute(SessionAttribute.PASSWORD);
    }

    protected String getEmail(HttpSession session) {
        return (String) session.getAttribute(SessionAttribute.EMAIL);
    }

    protected String getPassword(HttpSession session) {
        return (String) session.getAttribute(SessionAttribute.PASSWORD);
    }
}
