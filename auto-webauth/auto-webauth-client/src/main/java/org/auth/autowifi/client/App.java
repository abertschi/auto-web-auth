package org.auth.autowifi.client;

import org.auth.autowifi.api.network.NetworkProvider;
import org.auth.autowifi.api.network.WirelessNetworkFactory;
import org.auth.autowifi.api.network.exept.NetworkProvidementNotSupportedException;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

	public void start(Stage arg0) throws Exception {
		try {
			NetworkProvider networkProvider = WirelessNetworkFactory.getNetwork();
		}
		catch(NetworkProvidementNotSupportedException exept){
			System.out.println("Sorry, your os is not supported, yet");
		}
	}

}
