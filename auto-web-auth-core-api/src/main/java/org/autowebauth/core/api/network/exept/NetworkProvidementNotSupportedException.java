package org.autowebauth.core.api.network.exept;

import org.autowebauth.core.api.network.provider.HostInformation;

public class NetworkProvidementNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HostInformation host;

	public NetworkProvidementNotSupportedException(String reason) {
		super(reason);
	}

	public NetworkProvidementNotSupportedException(String reason,
			HostInformation host) {
		super(reason);
		this.host = host; // TODO: integration host information
	}

	public HostInformation getHost() {
		return host;
	}

	public void setHost(HostInformation host) {
		this.host = host;
	}
}
