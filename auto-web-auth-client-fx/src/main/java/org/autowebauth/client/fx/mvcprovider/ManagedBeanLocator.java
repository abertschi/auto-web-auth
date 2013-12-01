package org.autowebauth.client.fx.mvcprovider;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.core.api.provider.BeanProvider;

/**
 * Use the apache deltaspike project to stay implementation independently.
 * This class provides JSR299 CDI functionality.
 * 
 * @author abertschi
 * @since 01.12.2013

 * @see http://deltaspike.apache.org/documentation.html#module-overview
 */
public class ManagedBeanLocator {
	
	private static ManagedBeanLocator INSTANCE = null;
	
	protected CdiContainer cdiContainer;
	
	private ManagedBeanLocator() {
	}

	public static ManagedBeanLocator getInstance() {
		if (INSTANCE == null) {
			// TODO apply a method to instantiate INSTANCe only when it is actually needed. Not because its statically loaded.
			INSTANCE = new ManagedBeanLocator();
		}
		return INSTANCE;
	}
	
	public void startUp() {
		if (cdiContainer != null) {
			throw new IllegalAccessError("Container already started");
		}
		this.cdiContainer = CdiContainerLoader.getCdiContainer();
		cdiContainer.boot();
	}
	
	public <CLASS> CLASS startUp(Class<CLASS> clazz) {
		startUp();
		return lookUp(clazz);
	}
	
	public <CLASS> CLASS lookUp(Class<CLASS> clazz) {
		return BeanProvider.getContextualReference(clazz);
	}
	
	public void shutDown() {
		this.cdiContainer.shutdown();
	}
}
