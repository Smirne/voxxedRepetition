package ch.welld.voxxed.draft;


import org.apache.log4j.Logger;

import ch.welld.voxxed.LoginManager;
import ch.welld.voxxed.be.BEConnector;
import ch.welld.voxxed.be.ConnUtility;
import ch.welld.voxxed.be.VoxxedFacade;
import ch.welld.voxxed.data.VoxxedOperator;


public class BeTaskExecutor {
	
	private LoginManager loginManager = LoginManager.getInstance();
	
	private static final Logger logger = Logger.getLogger(BeTaskExecutor.class);
	
	public <T> T executeBeCall(BeTask<T> task){
		BEConnector connector = null;
		T result = null;
		//1 Get the operator
		VoxxedOperator operator = loginManager.getOperator(task.getToken());
		if (operator == null) {
			logger.warn("invalid operator token " + task.getToken()); 
			return null;
		}
		try {
			//2 estabilish connection and get remote service facade
			connector = ConnUtility.getBEConnector();
			VoxxedFacade service = ConnUtility.getVoxxedFacade(connector);
			//3 Set variables to task and run!
			task.setService(service);
			task.setOperator(operator);
			return task.call();
		
		} catch (Exception e) {
			//4 log and handle exception
			logger.error("Error in retrieving objectType1 for user" + task.getToken(), e);
			result = null;
		} finally {
			//5 release the connection
			ConnUtility.releaseBEConnector(connector);
		}
		// 6 audit the action (log)
		logger.info(task.getMethodName() +  "executed with params " + task.getParamsDescription() 
				+ " and result " + result);
		return result;
  
	}

}
