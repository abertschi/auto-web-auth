package org.autowebauth.core.api.network;

import org.autowebauth.core.api.network.provider.NetworkProvider;

/**
 * Wrapper class for default implementation of network factory.
 * 
 * @author abertschi
 * 
 */
public class DefaultNetworkFactory implements NetworkFactory {

	private static final String NETWORK_FACTORY_IMPL = "org.autowebauth.core.network.DefaultNetworkFactoryImpl";

	NetworkFactory factoryWrapper;

	public DefaultNetworkFactory() {
		createFactoryImpl();
	}

	@Override
	public NetworkProvider getProvider() {
		// delegate to impl
		return factoryWrapper.getProvider();
	}

	@Override
	public NetworkProvider getProviderByClass(
			Class<? extends NetworkProvider> clazz) {
		// delegate to impl
		return factoryWrapper.getProviderByClass(clazz);
	}

	private void createFactoryImpl() {
		try {
			factoryWrapper = (NetworkFactory) Class.forName(
					NETWORK_FACTORY_IMPL).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException(
					"Instanciation of DefaultNetworkFactory not possible", e);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(
					"DefaultNetworkFactory implementation not found", e);
		}
	}
}
