package org.autowebauth.client.fx.business.registration.boundary;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.autowebauth.client.fx.business.registration.entity.Registration;

@ApplicationScoped
public class RegistrationService {
	
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
	
	public List<Registration> getAll() {
		return null;
	}
	
	public Registration save(Registration r) {
		return r;
	}
	
	public void remove(Registration r) {
	}

}
