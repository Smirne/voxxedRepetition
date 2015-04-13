package ch.welld.voxxed;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Utility to retrieve and manage loggers. Uses log4j Logger class.
 * We could build an interface to allow different implementation.
 */
public final class LogUtility {

	/** Loggers map. */
	private static final Map<Class, Logger> loggerTable;

	/** True if the logger has been configured with an external file. */
	private static boolean configured = false;

	/** Initialize table and log in a static block. */
	static {
		loggerTable = new Hashtable<Class, Logger>();
	}

	/**
	 * Private constructor for utility class.
	 */
	private LogUtility() {

	}

	/**
	 * Optional: configure the logger with a specific Log4j.properties file.
	 * The file should be external. This method should be called only once per
	 * application lifetime. Further configuration attempts will be ignored.
	 * If this method is never called, log4j will look for the configuration file
	 * in the default folders.
	 * 
	 * @param fileName
	 *        full path of the file with logger configuration.
	 * @return true if the configuration is ok or has already been done.
	 */
	public static boolean configureLogger(String fileName) {
		if (fileName != null) {
			synchronized (LogUtility.class) {
				if (!configured) {
					// Check if file exists and configure
					if (new File(fileName).exists()) {
						PropertyConfigurator.configure(fileName);
						configured = true;
						return true;
					} else {
						logError("Logger configuration file does not exist: " + fileName);
						return false;
					}
				} else {
					logDebug("Logger is already configured. Ignoring " + fileName);
					return true;
				}
			}
		} else {
			logError("Trying to configure log with null file");
			return false;
		}
	}

	/**
	 * Returns a logger object, ready for logging.
	 * Logger will be named according to the class.
	 * Loggers are singleton according to the class: they are created only if they do not exist yet.
	 * 
	 * @param myClass
	 *        Class that is requesting a logger
	 * @return Logger - logger object
	 */
	public static Logger getLogger(Class myClass) {
		Logger logger = loggerTable.get(myClass);
		if (logger == null) {
			logger = Logger.getLogger(myClass);
			loggerTable.put(myClass, logger);
		}
		return logger;
	}
	
	/**
	 * Just build the logger when it's needed.
	 * @param toLog String to log at error level.
	 */
	public static void logError(String toLog){
		LogUtility.getLogger(LogUtility.class).error(toLog);
	}
	
	/**
	 * Just build the logger when it's needed.
	 * 
	 * @param toLog
	 *String to log at debug level.
	 */
	public static void logDebug(String toLog){
		LogUtility.getLogger(LogUtility.class).debug(toLog);
	}

}
