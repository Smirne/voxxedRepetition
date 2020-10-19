package ch.welld.voxxed;


import org.apache.log4j.Logger;

import ch.welld.voxxed.be.BEConnector;
import ch.welld.voxxed.be.ConnUtility;
import ch.welld.voxxed.be.VoxxedFacade;
import ch.welld.voxxed.data.VoxxedOperator;


public class BeMethodExecutor {
	
	private LoginManager loginManager = LoginManager.getInstance();
	
	private static final Logger logger = Logger.getLogger(BeMethodExecutor.class);
	
	public Object executeMethod(BeMethodCall call){
		BEConnector connector = null;
		Object result = null;
		//1 Get the operator
		VoxxedOperator operator = loginManager.getOperator(call.getToken());
		if (operator == null) {
			logger.warn("invalid operator token " + call.getToken()); 
			return null;
		}
		try {
			//2 estabilish connection and get remote service facade
			connector = ConnUtility.getBEConnector();
			VoxxedFacade service = ConnUtility.getVoxxedFacade(connector);
			
			//3 Copy the param array, set the operator as first param and execute.
		    Object[] params = new Object[call.getParams().length + 1];
		    System.arraycopy(call.getParams(), 0, params, 1, call.getParams().length);
		    params[0] = operator;
			
			call.getMethod().invoke(service, params);
		
		} catch (Exception e) {
			//4 log and handle exception
			logger.error("Error in retrieving objectType1 for user" + call.getParams(), e);
			result = null;
		} finally {
			//5 release the connection
			ConnUtility.releaseBEConnector(connector);
		}
		// 6 audit the action (log)
		logger.info(call.getMethod().getName() + " executed with params " + call.getParams() 
				+ " and result " + result);
		return result;
  
	}

}
