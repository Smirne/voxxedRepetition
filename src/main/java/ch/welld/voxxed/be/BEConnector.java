package ch.welld.voxxed.be;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * This class must be used to retrieve the connection to the BE. This clas can
 * be used to connect to different facades and used with different Communication
 * libs.
 * 
 * @author michele.giacobazzi
 * 
 */
public class BEConnector {

	/** String for single connection type. */
	public static String CONN_TYPE_SINGLE = "single";
	/** String for clustered connection type. */
	public static String CONN_TYPE_CLUSTERED = "clustered";

	/** String for clustered BE Connection. */
	private String clusterConnectionString = null;
	/** String for single BE Connection. */
	private String singleConnectionString = null;
	/** Type of connection to the BE: could be clustered or single server */
	private String connectionType = null;
	/** Name of cluster partition to call **/
	private String partitionName = null;

	/** Context for BE connection. */
	private Context ctx = null;
	/** Properties for BE connection. */
	private Properties properties = null;

	/** Map of facades instance. */
	private Map<String, Object> facadesMap = new HashMap<String, Object>();

	/**
	 * Default constructor. The constructor takes two connection String and the
	 * connection type. According to the connectionType, a different connection
	 * string is used. ATTENTION: this constructor does not set the
	 * partitionName. This may lead to a different jboss instance answering if
	 * the main one is down.
	 * 
	 * @param clusterConnectionString
	 *            String for cluster connection
	 * @param singleConnectionString
	 *            String for single connection
	 * @param connectionType
	 *            Type of connection. Use "clustered" for clustered connection.
	 * @throws NamingException
	 */
	public BEConnector(String clusterConnectionString,
			String singleConnectionString, String connectionType)
			throws NamingException {
		this(clusterConnectionString, singleConnectionString, null, connectionType);
	}

	/**
	 * Constructor including partition name The constructor takes two connection
	 * String, the partition name and the connection type. According to the
	 * connectionType, a different connection string is used. The partition name
	 * assures that only jboss instance with the given partition name will
	 * answer the calls.
	 * 
	 * @param clusterConnectionString
	 *            String for cluster connection
	 * @param singleConnectionString
	 *            String for single connection
	 * @param partitionName
	 *            String with partition name
	 * @param connectionType
	 *            Type of connection. Use "clustered" for clustered connection.
	 * @throws NamingException
	 */
	public BEConnector(String clusterConnectionString,
			String singleConnectionString, String partitionName, String connectionType)
			throws NamingException {

		this.clusterConnectionString = clusterConnectionString;
		this.singleConnectionString = singleConnectionString;
		this.partitionName = partitionName;
		this.connectionType = connectionType;
		initConnectionBE();
	}

	/**
	 * Method to initialize connection with JBoss server. The Context is created
	 * with the needed properties, ready for use.
	 * 
	 * @return true if connection is successful
	 * @throws NamingException
	 */
	private synchronized boolean initConnectionBE() throws NamingException {
		//[..]
		//DO Your BE THINGS: open the connection...
		return true;
	}

	/**
	 * Method for releasing the connection.
	 * 
	 * @return true if connection released successfully.
	 * @throws NamingException
	 *             exception arises.
	 */
	public boolean release() throws NamingException {
		//[..]
		//Release all you need to release.
		return true;
	}


	// --------------------------------------------------------------
	// Getters and setters
	// --------------------------------------------------------------

	/**
	 * Retrieve the requested facad instance.
	 * 
	 * @param lookup
	 *            Lookup string tu use (ex: "MyProjectFacade/remote").
	 * @return instance of the facade.
	 * @throws NamingException
	 */
	public Object getFacade(String lookup) throws NamingException {

		// add to map if present.
		if (facadesMap.get(lookup) == null) {
			facadesMap.put(lookup, ctx.lookup(lookup));
		}

		return facadesMap.get(lookup);
	}
	


	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @param clusterConnectionString
	 *            the clusterConnectionString to set
	 */
	public void setClusterConnectionString(String clusterConnectionString) {
		this.clusterConnectionString = clusterConnectionString;
	}

	/**
	 * @return the clusterConnectionString
	 */
	public String getClusterConnectionString() {
		return clusterConnectionString;
	}

	/**
	 * @param singleConnectionString
	 *            the singleConnectionString to set
	 */
	public void setSingleConnectionString(String singleConnectionString) {
		this.singleConnectionString = singleConnectionString;
	}

	/**
	 * @return the singleConnectionString
	 */
	public String getSingleConnectionString() {
		return singleConnectionString;
	}

	public String getPartitionName() {
		return partitionName;
	}

	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
	}
	
	

}
