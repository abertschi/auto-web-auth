package org.autowebauth.core.api.network.provider.conn;

/**
 * Listener to observe connection events.
 * 
 * @author Andrin Bertschi
 * 
 */
public interface NetworkListener
{
    void onConnectionActivity(ConnectionEvent event);
}
