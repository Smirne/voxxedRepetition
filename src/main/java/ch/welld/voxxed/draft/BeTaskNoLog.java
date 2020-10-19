package ch.welld.voxxed.draft;

import ch.welld.voxxed.draft.BeTask;

public abstract class BeTaskNoLog<T> extends BeTask<T>{

	public BeTaskNoLog(String token) {
		super(token);
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getMethodName() {
		return "MethodName";
	}

	@Override
	public String getParamsDescription() {
		return "MethodParams";
	}
	
	

}
