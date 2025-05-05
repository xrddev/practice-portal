package xrddev.practiceportal.config;

public final class ModelAttributes {
    public static final String ANNOUNCEMENTS = "ANNOUNCEMENTS";
    public static final String ROLES = "ROLES";
    public static final String DEPARTMENTS = "DEPARTMENTS";
    public static final String SKILLS = "SKILLS";
    public static final String INTERESTS = "INTERESTS";

    private ModelAttributes() throws Exception {
        throw new Exception("This is a utility class and cannot be instantiated");
    }
}
