package training360.matematikusok;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = BirthDayValidator.class)
public @interface IsValidBirthDay {

    String message() default "Not valid birthday";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
