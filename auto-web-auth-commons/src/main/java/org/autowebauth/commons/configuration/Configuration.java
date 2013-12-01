package org.autowebauth.commons.configuration;

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
	
//	@Produces
	public String getString(){
		return null;
	}
	
//	@Produces
	public int getInteger(){
		return -1;
	}
	
//	@Produces
	public String getEntry(String key){
		return null;
	}
	
	public void removeEntry(String key){
	}
	
	public String addEntry(String key, String value) {
		return null;
	}
	
}
