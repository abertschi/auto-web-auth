package org.autowebauth.core.api.network.exept;

import org.autowebauth.core.api.network.provider.HostInfo;

public class NetworkProvidementNotSupportedException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private HostInfo host;

    public NetworkProvidementNotSupportedException(String reason)
    {
        super(reason);
    }

    public NetworkProvidementNotSupportedException(String reason, HostInfo host)
    {
        super(reason);
        this.host = host; // TODO: integration host information
    }

    public HostInfo getHost()
    {
        return host;
    }

    public void setHost(HostInfo host)
    {
        this.host = host;
    }
}
