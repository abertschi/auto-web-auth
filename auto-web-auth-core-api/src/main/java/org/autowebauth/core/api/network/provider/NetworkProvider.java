package org.autowebauth.core.api.network.provider;

import java.util.List;

import org.autowebauth.core.api.network.provider.conn.Connection;
import org.autowebauth.core.api.network.provider.conn.NetworkListener;

/**
 * 
 * @author Andrin Bertschi
 * 
 */
public interface NetworkProvider
{
    /**
     * Get information about underlining operating system.
     */
    HostInfo getHost();

    /**
     * Register a listener to get information about connection activities.
     */
    void registerListener(NetworkListener cl);

    /**
     * Get active connection
     * 
     * @return null if no connection active.
     */
    Connection getConnection();

    /**
     * Get a list of all available connections
     * 
     * @return see description.
     */
    List<Connection> getConnections();

    void connect(Connection c);

    void disconnect(Connection c);

    void disconnect();
}
