package training360.navreservation.validation;

import training360.navreservation.entity.FreeTimeInterval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TimeIntervalValidator implements ConstraintValidator<TimeInterval, FreeTimeInterval> {

    @Override
    public void initialize(TimeInterval constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(FreeTimeInterval freeTimeInterval, ConstraintValidatorContext constraintValidatorContext) {
        return freeTimeInterval.getStart().isAfter(LocalDateTime.now()) &&
                freeTimeInterval.getEnd().isAfter(freeTimeInterval.getStart());
    }
}
