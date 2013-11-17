package org.auth.autowifi.api.network;

public class ConnectionEvent {

	private Connection connection;
	
	private ConnectionAction action;
	
	public ConnectionEvent(ConnectionAction action, Connection connection) {
		this.action = action;
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public ConnectionAction getAction() {
		return action;
	}

	public void setAction(ConnectionAction action) {
		this.action = action;
	}
}
