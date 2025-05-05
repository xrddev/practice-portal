package xrddev.practiceportal.config;

public final class SessionAttribute {
    public static final String PASSWORD = "PASSWORD";
    public static final String EMAIL = "EMAIL";

    private SessionAttribute() throws Exception {
        throw new Exception("This is a utility class and cannot be instantiated");
    }
}
