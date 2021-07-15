package training360.matematikusok;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PrimeValidator implements ConstraintValidator<IsPrime, Integer> {
    @Override
    public void initialize(IsPrime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(value); i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}
