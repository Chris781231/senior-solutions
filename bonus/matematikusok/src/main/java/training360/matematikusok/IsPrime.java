package training360.matematikusok;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = PrimeValidator.class)
public @interface IsPrime {

    String message() default "Not valid prime";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
