package org.auth.autowifi.wifi.winos;

import java.net.ConnectException;
import java.util.List;

import org.auth.autowifi.api.network.Connection;
import org.auth.autowifi.api.network.HostInformation;
import org.auth.autowifi.api.network.NetworkListener;
import org.auth.autowifi.api.network.NetworkProvider;

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
