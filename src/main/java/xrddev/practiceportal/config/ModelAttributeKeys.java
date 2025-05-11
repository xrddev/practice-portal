package xrddev.practiceportal.config;

public final class ModelAttributeKeys {
    public static final String ANNOUNCEMENTS = "ANNOUNCEMENTS";
    public static final String ROLES = "ROLES";
    public static final String DEPARTMENTS = "DEPARTMENTS";
    public static final String SKILLS = "SKILLS";
    public static final String INTERESTS = "INTERESTS";
    public static final String INTERNSHIP_POSITION_CREATE_DTO = "INTERNSHIP_POSITION_CREATE_DTO";
    public static final String INTERNSHIP_POSITION_DASHBOARD_DTO = "INTERNSHIP_POSITION_DASHBOARD_DTO";
    public static final String COMPANY_DASHBOARD_DTO = "COMPANY_DASHBOARD_DTO";
    public static final String MATCHED_STUDENTS_DTO = "MATCHED_STUDENTS_DTO";
    public static final String PROFESSOR_DASHBOARD_DTO = "PROFESSOR_DASHBOARD_DTO";
    public static final String COMPANY_DASHBOARD_DTO_LIST = "COMPANY_DASHBOARD_DTO_LIST";
    private ModelAttributeKeys() throws Exception {
        throw new Exception("This is a utility class and cannot be instantiated");
    }
}
