package xrddev.practiceportal.model.period;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Table(name = "evaluation_period")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EvaluationPeriod extends AbstractPeriod {
}