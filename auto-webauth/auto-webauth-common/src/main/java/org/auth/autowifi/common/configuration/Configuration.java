package org.auth.autowifi.common.configuration;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class Configuration {
	
	private Map<String,String> configurations;
	
	@Inject
	private ConfigurationProvider configurationProvider;

	@PostConstruct
	public void createConfigurations() {
	}
	
	public String getString(){
		return null;
	}
	
	public int getInteger(){
		return -1;
	}
	
	public String getEntry(String key){
		return null;
	}
	
	public void removeEntry(String key){
	}
	
	public String addEntry(String key, String value) {
		return null;
	}
	
}
