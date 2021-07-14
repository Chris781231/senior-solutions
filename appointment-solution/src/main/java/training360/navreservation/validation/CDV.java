package training360.navreservation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = CdvValidator.class)
public @interface CDV {

    String message() default "Not valid TaxId by CDV";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int length() default 10;
}
