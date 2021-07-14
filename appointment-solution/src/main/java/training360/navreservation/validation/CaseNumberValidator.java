package training360.navreservation.validation;

import org.springframework.beans.factory.annotation.Autowired;
import training360.navreservation.service.ReservationService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CaseNumberValidator implements ConstraintValidator<CaseNumber, String> {

    @Autowired
    ReservationService service;

    @Override
    public void initialize(CaseNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return service.getTypes().stream()
                .anyMatch(type -> type.getId().equals(s));
    }
}
