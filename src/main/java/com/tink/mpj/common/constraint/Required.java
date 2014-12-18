/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.common.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;

/**
 *
 * @author shearer
 */
@Documented
@Size(min=1)
@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {

    String name() default "Input";
    String message() default "{com.tink.mpj.common.constraint.Required.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}