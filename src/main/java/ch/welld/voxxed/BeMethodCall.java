package ch.welld.voxxed;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class BeMethodCall{
	
	//Token. To be provided.
	private String token;
	
	private Method method;
	
	private Object[] params;


	public BeMethodCall(Method method, String token, Object[] params) {
		super();
		this.token = token;
	}
	
	
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Method getMethod() {
		return method;
	}


	public void setMethod(Method method) {
		this.method = method;
	}


	public Object[] getParams() {
		return params;
	}


	public void setParams(Object[] params) {
		this.params = params;
	}

}
