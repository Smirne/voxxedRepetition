package ch.welld.voxxed.interceptor;


import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import ch.welld.voxxed.ExampleDAO;
import ch.welld.voxxed.LogUtility;
import ch.welld.voxxed.OperatorNotFoundException;
import ch.welld.voxxed.be.BEConnector;
import ch.welld.voxxed.be.ConnUtility;
import ch.welld.voxxed.be.VoxxedFacade;



@Interceptor
@BeConnection
public class ConnectionInterceptor {
	
	private static final Logger logger = LogUtility.getLogger(ConnectionInterceptor.class);
@AroundInvoke
public Object injectFacade(InvocationContext ctx) throws Exception {
	BEConnector connector = null;
	try {
		connector = ConnUtility.getBEConnector();
		VoxxedFacade service = ConnUtility.getVoxxedFacade(connector);
        ctx.getParameters()[0] = service;
        Object b = ctx.proceed();
        return b;
	} catch(OperatorNotFoundException oE){
		logger.warn("Operator not found " , oE);
		return null; 
	}catch (Exception e) {
		logger.warn("Error in be call", e);
		return null;
	} finally {
		ConnUtility.releaseBEConnector(connector);
	}
}
}