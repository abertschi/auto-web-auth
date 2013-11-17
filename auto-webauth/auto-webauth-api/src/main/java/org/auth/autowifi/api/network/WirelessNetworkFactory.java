package org.auth.autowifi.api.network;

import org.auth.autowifi.api.network.exept.NetworkProvidementNotSupportedException;
import org.auth.autowifi.wifi.winos.WinOsNetworkProvider;

/**
 * Factory to obtain wireless network provider.
 * 
 * @author abertschi
 * @since 15.11.2013
 *
 */
public class WirelessNetworkFactory {
	
	private static final String OS = System.getProperty("os.name").toLowerCase();
	
	/**
	 * Factory class
	 */
	private WirelessNetworkFactory() {
	}

	/**
	 * Get operating system dependent {@link NetworkProvider}
	 * @return see description
	 */
	public static NetworkProvider getNetwork() throws NetworkProvidementNotSupportedException {
		NetworkProvider provider = null;
		if (isWindows()) {
			provider = new WinOsNetworkProvider();
		}
		else {
			throw new NetworkProvidementNotSupportedException("Your operating system isn't supported yet");
		}
		return provider;
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
