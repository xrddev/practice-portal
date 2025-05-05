package xrddev.practiceportal.config;

public final class SessionKeys {
    public static final String ANNOUNCEMENTS = "ANNOUNCEMENTS";
    public static final String PASSWORD = "PASSWORD";
    public static final String EMAIL = "EMAIL";
    public static final String ROLES = "ROLES";
    public static final String DEPARTMENTS = "DEPARTMENTS";
    public static final String SKILLS = "SKILLS";
    public static final String INTERESTS = "INTERESTS";

    private SessionKeys() throws Exception {
        throw new Exception("This is a utility class and cannot be instantiated");
    }
}
