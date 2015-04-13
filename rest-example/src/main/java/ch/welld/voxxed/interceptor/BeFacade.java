package ch.welld.voxxed.interceptor;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;


@InterceptorBinding
@Target({ PARAMETER })
@Retention(RUNTIME)
public @interface BeFacade {
	
}