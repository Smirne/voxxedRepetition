package ch.welld.voxxed;


import org.apache.log4j.Logger;

import ch.welld.voxxed.be.BEConnector;
import ch.welld.voxxed.be.ConnUtility;
import ch.welld.voxxed.be.VoxxedFacade;
import ch.welld.voxxed.data.VoxxedOperator;


public class BeFunctionExecutor {
	
	private LoginManager loginManager = LoginManager.getInstance();
	
	private static final Logger logger = Logger.getLogger(BeFunctionExecutor.class);
	
public <T> T executeFunction(BeFunction<T> call){
	BEConnector connector = null;
	T result = null;
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
		
		//3 Execute the code!
		call.execute(operator, service);
	
	} catch (Exception e) {
		//4 log and handle exception
		logger.error("Error in function call", e);
		result = null;
	} finally {
		//5 release the connection
		ConnUtility.releaseBEConnector(connector);
	}
	// cannot log
		
		return result;
  
	}

}
