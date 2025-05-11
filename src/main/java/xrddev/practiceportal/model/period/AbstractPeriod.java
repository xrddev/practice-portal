package xrddev.practiceportal.model.period;

import jakarta.persistence.*;
import lombok.Data;
import xrddev.practiceportal.model.enums.PeriodStatus;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class AbstractPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_mode", nullable = false)
    protected PeriodStatus statusMode = PeriodStatus.AUTOMATIC;

    @Column(name = "start_time", nullable = false)
    protected LocalDateTime startTime;

    @Column(name = "end_time")
    protected LocalDateTime endTime;

    @Transient
    public boolean isOpen() {
        return switch (statusMode) {
            case OPEN      -> true;
            case CLOSED    -> false;
            case AUTOMATIC -> {
                LocalDateTime now = LocalDateTime.now();
                boolean afterStart = startTime == null || !now.isBefore(startTime);
                boolean beforeEnd  = endTime   == null || !now.isAfter(endTime);
                yield afterStart && beforeEnd;
            }
        };
    }

    /** Κλείνει χειροκίνητα την περίοδο (ορίζει CLOSED). */
    public void closePeriodManually() {
        this.statusMode = PeriodStatus.CLOSED;
    }

    /** Ανοίγει χειροκίνητα την περίοδο (ορίζει OPEN). */
    public void openPeriodManually() {
        this.statusMode = PeriodStatus.OPEN;
    }

    /** Επαναφέρει την αυτόματη λειτουργία (AUTOMATIC). */
    public void setPeriodAutomatic() {
        this.statusMode = PeriodStatus.AUTOMATIC;
    }
}