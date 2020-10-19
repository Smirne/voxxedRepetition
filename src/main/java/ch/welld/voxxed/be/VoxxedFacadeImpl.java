package ch.welld.voxxed.be;

import java.util.Map;

import org.apache.log4j.Logger;

import ch.welld.voxxed.BeTaskExecutor;
import ch.welld.voxxed.data.Octopus;
import ch.welld.voxxed.data.Squid;

public class VoxxedFacadeImpl implements VoxxedFacade {
	
	private static final Logger logger = Logger.getLogger(VoxxedFacadeImpl.class);


	@Override
	public Squid findSquidByCriteria(Object operator,
			Map<String, Object> criteria) {
		logger.debug("findSquidByCriteria called");
		return null;
	}

	@Override
	public Octopus findOctopusByCriteria(Object operator,
			Map<String, Object> criteria) {
		logger.debug("findOctopusByCriteria called");

		return null;
	}

	@Override
	public Squid findAllSquid(Object operator) {
		logger.debug("findAllSquid called");
		return null;
	}

	@Override
	public Squid findSquidById(Object operator, String id) {
		logger.debug("findSquidById called");
		return null;
	}

	@Override
	public Octopus findOctopusById(Object operator, long id) {
		logger.debug("findOctopusById called");
		return null;
	}

}
