package org.auth.autowifi.api.network;

/**
 * Listener to observe connection events.
 * 
 * @author abertschi
 *
 */
public interface NetworkListener {

	void onConnectionActivity(ConnectionEvent event);
}
