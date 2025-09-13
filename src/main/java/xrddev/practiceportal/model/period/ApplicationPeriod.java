package xrddev.practiceportal.model.period;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "application_period")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ApplicationPeriod extends AbstractPeriod {

}