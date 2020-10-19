package ch.welld.voxxed;

import ch.welld.voxxed.data.VoxxedOperator;

public class LoginManager {
	
	   private static LoginManager instance = null;
	   
	   protected LoginManager() {
	      // Exists only to defeat instantiation.
	   }
	   public static LoginManager getInstance() {
	      if(instance == null) {
	         instance = new LoginManager();
	      }
	      return instance;
	   }

	   public VoxxedOperator getOperator(String token){
		   return new VoxxedOperator();
	   }
	   
	   public VoxxedOperator getOperatorWithException(String token) 
			   throws OperatorNotFoundException{
		   if (token ==null){
			   throw new OperatorNotFoundException();
		   }else{
			   return new VoxxedOperator();
		   }
		   
	   }
	   
}
