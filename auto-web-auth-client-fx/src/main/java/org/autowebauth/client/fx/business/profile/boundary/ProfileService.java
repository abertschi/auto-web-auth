package org.autowebauth.client.fx.business.profile.boundary;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.autowebauth.client.fx.business.profile.entity.Profile;

public class ProfileService {
	
	private EntityManager em;
	private EntityManagerFactory emf;
	private EntityTransaction et;
	
	@PostConstruct
	public void create() {
		this.emf = Persistence.createEntityManagerFactory("PUnit");
		this.em = this.emf.createEntityManager();
		this.et = this.em.getTransaction();
	}
	
	@PreDestroy
	public void destroy() {
		this.em.close();
		this.emf.close();
	}
	
	public List<Profile> getAll() {
		return null;
	}
	
	public Profile save(Profile p) {
		return p;
	}
	
	public void remove(Profile p) {
	}

}
