package com.example.highroller.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * A custom JSR-303 Constraint Annotation for doing declarative validation
 */

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DiceProbabilityValidator.class)
@Documented
public @interface DiceProbabilities {
    String message() default "Invalid Dice Probabilties";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}