package org.auth.autowifi.api.network;

import org.auth.autowifi.api.network.exept.NetworkProvidementNotSupportedException;

/**
 * Factory to obtain wireless network provider.
 * 
 * @author abertschi
 *
 */
public class WirelessNetworkFactory {
	
	/**
	 * Factory class
	 */
	private WirelessNetworkFactory() {
	}

	/**
	 * Get operating system dependent {@link NetworkProvider}
	 * @return see description
	 */
	public static NetworkProvider getNetwork() throws NetworkProvidementNotSupportedException{
		return null;
	}
	
}
