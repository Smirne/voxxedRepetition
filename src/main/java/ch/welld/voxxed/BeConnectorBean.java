package ch.welld.voxxed;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;

import ch.welld.voxxed.be.BEConnector;
import ch.welld.voxxed.be.ConnUtility;
import ch.welld.voxxed.be.VoxxedFacade;


@RequestScoped
@Lock(LockType.READ)
public class BeConnectorBean implements Serializable{
	
	/** Serializable*/
	private static final long serialVersionUID = -6782787395082630653L;

	private static final Logger LOGGER = Logger.getLogger(BeConnectorBean.class.getName());
	
	private BEConnector connector;

	
	@PostConstruct
	public void initBEConnection(){
		LOGGER.info("POSTACTIVATE Creating the BE connector");
		connector = ConnUtility.getBEConnector();
	}
	
	
	@PreDestroy
	public void release(){
		LOGGER.info("RELEASE Releasing the BE connector");
		ConnUtility.releaseBEConnector(connector);

	}
	
	public VoxxedFacade getService() throws NamingException{
			return ConnUtility.getVoxxedFacade(connector);
	}

}
