package org.auth.autowifi.client.business.registration.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class User {

	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	private String password;
	
	public User() {
		this.name = "";
		this.password = "";
	}
	
	public User(String name) {
		this.name = name;
		this.password = "";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}