package org.autowebauth.core.api.network.provider;


/**
 * Listener to observe connection events.
 * 
 * @author abertschi
 *
 */
public interface NetworkListener {

	void onConnectionActivity(ConnectionEvent event);
}
