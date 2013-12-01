package org.autowebauth.client.fx.mvcprovider;

public class BeanLocator {

	public <TYPE> TYPE lookUp(Class<TYPE> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
