package org.auth.autowifi.api.network.exept;


public class NetworkProvidementNotSupportedException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public NetworkProvidementNotSupportedException(String reason) {
		super(reason);
	}
}
