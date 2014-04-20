package org.autowebauth.core.api.network;

import org.autowebauth.core.api.network.provider.NetworkProvider;
/**
 * Network factory interface
 * 
 * @author abertschi
 * @since 29.11.2013
 *
 */
public interface NetworkFactory {
	
	NetworkProvider getProvider();
	
}
