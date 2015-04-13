package ch.welld.voxxed.draft;

import java.util.concurrent.Callable;

import ch.welld.voxxed.be.VoxxedFacade;
import ch.welld.voxxed.data.VoxxedOperator;

public abstract class BeTask<T> implements Callable<T> {
	
	//Token. To be provided.
	private String token;
	
	//Facade. Injected by BeCaller.
	private VoxxedFacade service;
	//Operator. Injected by BeCaller.
	private VoxxedOperator operator;

	
	public BeTask(String token) {
		super();
		this.token = token;
	}
	
	// Needed for auditing
	public abstract String getMethodName();
	
	// Needed for auditing
	public abstract String getParamsDescription();


	public VoxxedFacade getService() {
		return service;
	}

	public void setService(VoxxedFacade service) {
		this.service = service;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public VoxxedOperator getOperator() {
		return operator;
	}

	public void setOperator(VoxxedOperator operator) {
		this.operator = operator;
	}

}
