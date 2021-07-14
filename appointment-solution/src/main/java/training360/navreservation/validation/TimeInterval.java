package training360.navreservation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TimeIntervalValidator.class)
public @interface TimeInterval {

    String message() default "Not valid time interval";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
