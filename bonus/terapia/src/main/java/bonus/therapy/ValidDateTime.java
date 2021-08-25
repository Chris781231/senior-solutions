package bonus.therapy;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = DateTimeValidator.class)
public @interface ValidDateTime {

    String message() default "A időpontnak későbbinek kell lenni, mint az aktuális időpont";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
