package ch.welld.voxxed;

import ch.welld.voxxed.be.VoxxedFacade;
import ch.welld.voxxed.data.VoxxedOperator;

public abstract class BeFunction<T> implements BeFunctionInterface<T>{
	
	private String token;
	
	public BeFunction(String myToken) {
		super();
		this.token = myToken;
	}
	
	public String getToken(){
		return token;
	}

	public abstract T execute(VoxxedOperator operator, VoxxedFacade service);

}
