package org.autowebauth.core.network.winos;

import java.net.ConnectException;
import java.util.List;

import org.autowebauth.core.api.network.provider.Connection;
import org.autowebauth.core.api.network.provider.HostInformation;
import org.autowebauth.core.api.network.provider.NetworkListener;
import org.autowebauth.core.api.network.provider.NetworkProvider;

public class WinOsNetworkProvider implements NetworkProvider {

	public HostInformation getHost() {
		return null;
	}

	public void registerListener(NetworkListener cl) {
	}

	public Connection getEstabilishedConnection() {
		return null;
	}

	public List getConnections() {
		return null;
	}

	public void connect(Connection c) throws ConnectException {
	}

}
