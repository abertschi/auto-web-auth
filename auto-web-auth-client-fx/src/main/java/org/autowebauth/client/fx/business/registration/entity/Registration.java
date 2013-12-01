package org.autowebauth.client.fx.business.registration.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.autowebauth.client.fx.business.profile.entity.Profile;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Registration {

	@ManyToOne
	@NotNull
	private Profile profile;
	
	@NotNull
	@NotBlank
	private String ssid;
	
	private boolean autoConnectIfAvailable;
	
	@OneToOne
	private User user;
	
	public Registration() {
		this.profile = null;
		this.ssid = "";
		this.autoConnectIfAvailable = false;
		this.user = null;
	}
	
	public Registration(String ssid) {
		this();
		this.ssid = ssid;
	}
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public boolean isAutoConnectIfAvailable() {
		return autoConnectIfAvailable;
	}
	public void setAutoConnectIfAvailable(boolean autoConnectIfAvailable) {
		this.autoConnectIfAvailable = autoConnectIfAvailable;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
