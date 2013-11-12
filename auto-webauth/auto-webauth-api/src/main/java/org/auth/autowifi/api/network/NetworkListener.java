package org.auth.autowifi.api.network;

/**
 * Listener to observe connection events.
 * 
 * @author abertschi
 *
 */
public interface NetworkListener {

	void onEstablished(Connection event);
}
