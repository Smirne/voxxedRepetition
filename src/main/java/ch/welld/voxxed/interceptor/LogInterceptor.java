package ch.welld.voxxed.interceptor;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


@Interceptor
@LogMethod  //binding the interceptor here. now any method annotated with @Logit would be intercepted by logMethodEntry
public class LogInterceptor {
	
	private static final Logger LOGGER = Logger.getLogger(LogInterceptor.class.getName());
    
	@AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
    	//Execute the method
        Object b = ctx.proceed();
        //Print method name and params.
        LOGGER.info(ctx.getMethod().getName() + " executed with params " + ctx.getParameters());
        return b;
    }
}