package com.tink.mpj.common.logging;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * InterceptorBinding
 * @author shearer
 */
@InterceptorBinding
//@Target({METHOD, TYPE}) // method using life-cycle callback can only use interceptor with Target{Type} 
@Target(TYPE)
@Retention(RUNTIME)
public @interface Loggable {
}
