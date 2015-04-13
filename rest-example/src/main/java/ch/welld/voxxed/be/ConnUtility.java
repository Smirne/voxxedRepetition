package ch.welld.voxxed.be;


import javax.naming.NamingException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Utility for handling the connection to the BE.
 * 
 * @author michele.giacobazzi
 */
public class ConnUtility {

	/** Logger for this class. */
	private static final Logger LOG = Logger.getLogger(ConnUtility.class);


	/**
	 * Get the connector helper to the BE.
	 * 
	 * @return The be connector.
	 */
	public static BEConnector getBEConnector() {
		LOG.info("Creating a new BEConnector");
		BEConnector connector = null;
		try {
			connector = new BEConnector(
					"ecarchbe:1200",
					"ecarchbe:1200",
					"ecarDev",
					"clustered"
					);

		} catch (NamingException e) {
			LOG.warn("Could not connect to BE", e);
		}

		return connector;
	}

	
	/**
	 * Retrieve the GUIFacade from the existing connector.
	 * 
	 * @param connector
	 *        - connector to use.
	 * @return GUIFacadeInterface interface for communication with BE.
	 * @throws NamingException
	 *         - Exception in retrieving guiFacade
	 */
	public static VoxxedFacade getVoxxedFacade(BEConnector connector)
			throws NamingException {
		return new VoxxedFacadeImpl();
	}
	

	/**
	 * Releases the BE connector. The connector might be null.
	 * 
	 * @param connector
	 *        The be connector to be released.
	 */
	public static void releaseBEConnector(BEConnector connector) {
		if (connector != null) {
			try {
				connector.release();
			} catch (NamingException e) {
				LOG.error("error in releasing connector: " + e);
			}
		}
	}

}
