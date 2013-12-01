package org.autowebauth.client.fx;

import javafx.application.Application;
import javafx.stage.Stage;

import org.autowebauth.core.api.network.AutoWebAuth;
import org.autowebauth.core.api.network.exept.NetworkProvidementNotSupportedException;
import org.autowebauth.core.api.network.provider.NetworkProvider;

public class App extends Application{

	public void start(Stage arg0) throws Exception {
		try {
			NetworkProvider networkProvider = AutoWebAuth.getFactory().getProvider();
		}
		catch(NetworkProvidementNotSupportedException exept){
			System.out.println("Sorry, your os is not supported, yet");
		}
	}

}
