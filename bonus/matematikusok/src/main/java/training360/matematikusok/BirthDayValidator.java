package training360.matematikusok;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class BirthDayValidator implements ConstraintValidator<IsValidBirthDay, LocalDate> {
    @Override
    public void initialize(IsValidBirthDay constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext constraintValidatorContext) {
        return value.isBefore(LocalDate.now());
    }
}
