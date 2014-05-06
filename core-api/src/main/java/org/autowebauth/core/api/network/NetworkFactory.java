package org.autowebauth.core.api.network;

import java.lang.reflect.Type;

import org.autowebauth.core.api.network.provider.NetworkProvider;

/**
 * Network factory interface
 * 
 * @author Andrin Bertschi
 * @since 29.11.2013
 * 
 */
public interface NetworkFactory
{

    NetworkProvider getProvider();
    
    NetworkProvider getProvider(Class<? extends NetworkProvider> type);
    
}
