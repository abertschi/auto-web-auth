package org.autowebauth.core.api.network;

/**
 * Factory to obtain wireless network provider.
 * 
 * @author abertschi
 * @since 15.11.2013
 * 
 */
public class AutoWebAuth
{

    /**
     * Factory class
     */
    private AutoWebAuth()
    {
        throw new UnsupportedOperationException("No instances permitted");
    }

    /**
     * Get default factory.
     * 
     * @return see description
     */
    public static NetworkFactory getFactory()
    {
        return getDefaultFactory();
    }

    private static NetworkFactory getDefaultFactory()
    {
        return new DefaultNetworkFactory();
    }

    public NetworkFactory getFactory(Class<? extends NetworkFactory> clazz)
    {
        NetworkFactory factory = null;
        try
        {
            return (NetworkFactory) clazz.newInstance();
        } catch (InstantiationException e)
        {
            new IllegalArgumentException(
                    "Not able to instanciate given network factory", e);
        } catch (IllegalAccessException e)
        {
            new IllegalArgumentException(e);
        }
        return factory;
    }
}
