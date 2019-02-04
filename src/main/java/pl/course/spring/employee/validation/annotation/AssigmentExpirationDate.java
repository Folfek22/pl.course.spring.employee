package pl.course.spring.employee.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.course.spring.employee.validation.AssigmentExpirationDateValidator;


@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AssigmentExpirationDateValidator.class)
public @interface AssigmentExpirationDate {
	String message() default "Przekroczono termin ważności projektu";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
