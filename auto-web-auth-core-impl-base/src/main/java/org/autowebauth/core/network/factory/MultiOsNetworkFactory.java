package org.autowebauth.core.network.factory;

import org.autowebauth.core.api.network.NetworkFactory;
import org.autowebauth.core.api.network.exept.NetworkProvidementNotSupportedException;
import org.autowebauth.core.api.network.provider.NetworkProvider;
import org.autowebauth.core.network.winos.WinOsNetworkProvider;

/**
 * Factory to obtain wireless network provider.
 * 
 * @author Andrin Bertschi
 * @since 15.11.2013
 * 
 */
public class MultiOsNetworkFactory implements NetworkFactory
{

    private static final String OS = System.getProperty("os.name")
            .toLowerCase();

    public MultiOsNetworkFactory()
    {
    }

    /**
     * Get operating system dependent {@link NetworkProvider}
     */
    @Override
    public NetworkProvider getProvider()
            throws NetworkProvidementNotSupportedException
    {
        NetworkProvider provider = null;
        if (isWindows())
        {
            provider = new WinOsNetworkProvider();
        }
        else
        {
            throw new NetworkProvidementNotSupportedException(
                    "Your operating system isn't supported yet");
        }
        return provider;
    }

    @Override
    public NetworkProvider getProvider(Class<? extends NetworkProvider> type)
    {
        return null;
    }

    private static boolean isWindows()
    {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isMac()
    {
        return (OS.indexOf("mac") >= 0);
    }

    private static boolean isUnix()
    {
        return (OS.indexOf("nux") >= 0);
    }

}
