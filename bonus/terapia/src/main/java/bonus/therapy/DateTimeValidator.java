package bonus.therapy;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateTimeValidator implements ConstraintValidator<ValidDateTime, LocalDateTime> {

    @Override
    public void initialize(ValidDateTime constraint) {
        ConstraintValidator.super.initialize(constraint);
    }

    @Override
    public boolean isValid(LocalDateTime dateTime, ConstraintValidatorContext constraintValidatorContext) {
        boolean isAfter = dateTime.isAfter(LocalDateTime.now());
        System.out.println(isAfter);
        return isAfter;
    }
}
