package xrddev.practiceportal.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Data Transfer Object representing a User, used for transferring user-related data
 * between layers without exposing sensitive information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;         // User identifier
    private String username; // Username of the user
    private String email;    // User's email address
}