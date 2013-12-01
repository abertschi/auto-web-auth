package org.autowebauth.client.fx.business.network.boundary;



import javafx.event.Event;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.autowebauth.core.api.network.AutoWebAuth;
import org.autowebauth.core.api.network.provider.ConnectionAction;
import org.autowebauth.core.api.network.provider.ConnectionEvent;
import org.autowebauth.core.api.network.provider.NetworkListener;
import org.autowebauth.core.api.network.provider.NetworkProvider;

@ApplicationScoped
public class NetworkObserver implements NetworkListener{
	
	NetworkProvider networkProvider;
	
	@Inject Event events; // TODO
	
	public NetworkObserver() {
	}
	
	@PostConstruct
	public void init() {
		this.networkProvider = AutoWebAuth.getFactory().getProvider();
		networkProvider.registerListener(this);
	}

	@Override
	public void onConnectionActivity(ConnectionEvent event) {
		if (event.getAction() == ConnectionAction.CONNECTED) {
		}
		else if (event.getAction() == ConnectionAction.DISCONNECTED) {
		}
		
	}

	public NetworkProvider getNetworkProvider() {
		return networkProvider;
	}

	public void setNetworkProvider(NetworkProvider networkProvider) {
		this.networkProvider = networkProvider;
	}
}
