package org.autowebauth.commons.configuration;

import java.util.Map;

interface ConfigurationProvider {

	Map<String,String> getConfiguration();
	
}
