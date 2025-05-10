package xrddev.practiceportal.model.period;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Περίοδος υποβολής αιτήσεων.
 */
@Entity
@Table(name = "application_period")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ApplicationPeriod extends AbstractPeriod {
    // Δεν χρειάζεται επιπλέον κώδικας –
    // όλα τα πεδία και οι μέθοδοι κληρονομούνται από το AbstractPeriod.
}