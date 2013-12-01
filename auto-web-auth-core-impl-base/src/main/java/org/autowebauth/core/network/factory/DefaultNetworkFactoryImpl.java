package org.autowebauth.core.network.factory;

import org.autowebauth.core.api.network.exept.NetworkProvidementNotSupportedException;
import org.autowebauth.core.api.network.provider.NetworkProvider;
import org.autowebauth.core.network.winos.WinOsNetworkProvider;

/**
 * Factory to obtain wireless network provider.
 * 
 * @author abertschi
 * @since 15.11.2013
 *
 */
public class DefaultNetworkFactoryImpl  {
	
	private static final String OS = System.getProperty("os.name").toLowerCase();
	
	/**
	 * Factory class
	 */
	private DefaultNetworkFactoryImpl() {
		throw new UnsupportedOperationException("No instances permitted");
	}

	/**
	 * Get operating system dependent {@link NetworkProvider}
	 * @return see description
	 */
	public NetworkProvider getProvider() throws NetworkProvidementNotSupportedException {
		NetworkProvider provider = null;
		if (isWindows()) {
			provider = new WinOsNetworkProvider();
		}
		else {
			throw new NetworkProvidementNotSupportedException("Your operating system isn't supported yet");
		}
		return provider;
	}
	
	public NetworkProvider getProviderByClass (Class<? extends NetworkProvider> clazz) {
		return null;
		
	}
	
	private static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	private static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	private static boolean isUnix() {
		return (OS.indexOf("nux") >= 0);
	}
}
