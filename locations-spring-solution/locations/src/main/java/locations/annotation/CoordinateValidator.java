package locations.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateValidator implements ConstraintValidator<Coordinate, Double> {

    private Type type;

    @Override
    public void initialize(Coordinate constraintAnnotation) {
        type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return type == Type.LAT ? value > -90 && value < 90 : value > -180 && value < 180;
    }
}
