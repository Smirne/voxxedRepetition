package ch.welld.voxxed.be;

import java.util.Map;

import ch.welld.voxxed.data.Octopus;
import ch.welld.voxxed.data.Squid;

public interface VoxxedFacade {
	
	Squid findSquidByCriteria(Object operator, Map<String, Object> criteria);
	
	Octopus findOctopusByCriteria(Object operator, Map<String, Object> criteria);
	
	Octopus findOctopusById(Object operator, long id);

	
	Squid findAllSquid(Object operator);
	
	Squid findSquidById(Object operator, String id);


}
