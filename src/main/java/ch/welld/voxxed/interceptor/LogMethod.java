package ch.welld.voxxed.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//Annotation for log interceptor.
@InterceptorBinding
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface LogMethod {
}
