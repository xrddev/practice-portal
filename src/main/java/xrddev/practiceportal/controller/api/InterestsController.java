package xrddev.practiceportal.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xrddev.practiceportal.model.enums.Interests;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InterestsController {
    private static final String API_TOKEN = "my-secure-api-token";

    @GetMapping
    public ResponseEntity<List<String>> getAllInterests(
            @RequestHeader(value = "X-API-TOKEN", required = false) String token) {
        // Έλεγχος αν το token είναι έγκυρο
        if (!API_TOKEN.equals(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(null);
        }

        // Επιστροφή ενδιαφερόντων
        List<String> interests = Arrays.stream(Interests.values())
                .map(Enum::name)
                .toList();

        return ResponseEntity.ok(interests);
    }
}