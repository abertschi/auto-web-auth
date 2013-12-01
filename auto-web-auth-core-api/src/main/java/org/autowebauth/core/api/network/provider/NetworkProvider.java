package org.autowebauth.core.api.network.provider;

import java.net.ConnectException;
import java.util.List;

/**
 * 
 * @author abertschi
 *
 */
public interface NetworkProvider {
	
	/**
	 * Get information about underlining operating system.
	 * @return see description.
	 */
	HostInformation getHost();
	
	/**
	 * Register a listener to get information about connection activities.
	 * @param cl
	 */
	void registerListener(NetworkListener cl);
	
	/**
	 * Get current connected connection
	 * @return null if no connection estabilished.
	 */
	Connection getEstabilishedConnection();
	
	
	/**
	 * Get a list of all available connections
	 * @return see description.
	 */
	List<Connection> getConnections();
	
	void connect(Connection c) throws ConnectException;
}
