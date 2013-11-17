package org.auth.autowifi.client.mvcprovider;

public class BeanLocator {

	public <TYPE> TYPE lookUp(Class<TYPE> clazz) {
		return clazz.newInstance();
	}
	
}
