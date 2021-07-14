package locations.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = CoordinateValidator.class)
public @interface Coordinate {

    String message() default "Not valid coordinate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payLoad() default {};

    Type type() default Type.LAT;
}
