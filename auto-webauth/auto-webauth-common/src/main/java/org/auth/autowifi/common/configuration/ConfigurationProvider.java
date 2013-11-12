package org.auth.autowifi.common.configuration;

import java.util.Map;

interface ConfigurationProvider {

	Map<String,String> getConfiguration();
	
}
