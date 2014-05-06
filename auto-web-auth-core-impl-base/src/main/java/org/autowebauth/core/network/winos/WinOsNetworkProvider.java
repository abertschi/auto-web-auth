package org.autowebauth.core.network.winos;

import java.util.ArrayList;
import java.util.List;

import org.autowebauth.core.api.network.provider.HostInfo;
import org.autowebauth.core.api.network.provider.NetworkProvider;
import org.autowebauth.core.api.network.provider.conn.Connection;
import org.autowebauth.core.api.network.provider.conn.ConnectionAction;
import org.autowebauth.core.api.network.provider.conn.ConnectionEvent;
import org.autowebauth.core.api.network.provider.conn.NetworkListener;

public class WinOsNetworkProvider implements NetworkProvider
{

    protected List<NetworkListener> listeners;

    public WinOsNetworkProvider()
    {
        this.listeners = new ArrayList<NetworkListener>();
        tmp();
    }

    @Override
    public HostInfo getHost()
    {
        return null;
    }

    @Override
    public void registerListener(NetworkListener cl)
    {
        this.listeners.add(cl);
    }

    @Override
    public Connection getConnection()
    {
        return null;
    }

    @Override
    public List getConnections()
    {
        return null;
    }

    @Override
    public void connect(Connection c)
    {
    }

    // just test
    private void tmp()
    {
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    ConnectionEvent e = new ConnectionEvent(
                        ConnectionAction.CONNECTED, new Connection()
                        {
                            @Override
                            public String getName()
                            {
                                return "Sample connection: "
                                    + System.currentTimeMillis();
                            }
                        });
                    for (NetworkListener nl : WinOsNetworkProvider.this.listeners)
                    {
                        nl.onConnectionActivity(e);
                    }
                    try
                    {
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    @Override
    public void disconnect(Connection c)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void disconnect()
    {
        // TODO Auto-generated method stub

    }
}
