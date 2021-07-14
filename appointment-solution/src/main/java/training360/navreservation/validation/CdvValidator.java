package training360.navreservation.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CdvValidator implements ConstraintValidator<CDV, String> {

    private int length;

    @Override
    public void initialize(CDV constraintAnnotation) {
        length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return length == 10 &&
                isValidCDV(s);
    }

    private boolean isValidCDV(String s) {
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            sum += Integer.parseInt(String.valueOf(s.charAt(i))) * (i + 1);
        }
        return sum % 11 == Integer.parseInt(String.valueOf(s.charAt(9)));
    }
}
