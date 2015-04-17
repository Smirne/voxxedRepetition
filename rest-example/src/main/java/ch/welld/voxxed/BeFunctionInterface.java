package ch.welld.voxxed;

import ch.welld.voxxed.be.VoxxedFacade;
import ch.welld.voxxed.data.VoxxedOperator;

@FunctionalInterface
public interface BeFunctionInterface<T> {
	
	T execute(VoxxedOperator operator, VoxxedFacade service);

}
