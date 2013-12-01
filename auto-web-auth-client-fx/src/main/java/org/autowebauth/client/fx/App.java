package org.autowebauth.client.fx;

import javafx.application.Application;
import javafx.stage.Stage;

import org.autowebauth.client.fx.mvcprovider.ManagedBeanLocator;

public class App extends Application{

	public void start(Stage arg0) throws Exception {
		ManagedBeanLocator.getInstance().startUp();
	}
	
	// TODO onClose
	// ManagedBeanLocator.getInstance.shutDown();
}
