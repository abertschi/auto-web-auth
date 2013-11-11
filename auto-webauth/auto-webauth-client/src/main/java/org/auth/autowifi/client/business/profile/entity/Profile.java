package org.auth.autowifi.client.business.profile.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Profile {
	
	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String targetUrl;
	
	@NotNull
	@NotBlank
	private String actionType;
	
	private Map<String, String> properties;
	
	public Profile(String name) {
		this.name = name;
		this.targetUrl = "";
		this.actionType = "";
		this.properties = new HashMap<String, String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}
