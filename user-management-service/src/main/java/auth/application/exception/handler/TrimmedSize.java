
package auth.application.exception.handler;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = TrimmedSizeValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TrimmedSize {
    String message() default "string input does not match required length";

    Class<?>[] groups() default {};

    int min() default 1;
    
    int max() default 1;

    Class<? extends Payload>[] payload() default {};
 
}

