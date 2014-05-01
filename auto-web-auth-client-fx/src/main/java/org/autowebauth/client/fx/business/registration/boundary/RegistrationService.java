package org.autowebauth.client.fx.business.registration.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.autowebauth.client.fx.business.registration.entity.Registration;

/**
 * {@code Boundary} for {@link Registration} {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@ApplicationScoped
public class RegistrationService {

    @Inject
    private EntityManagerFactory emf;

    private EntityManager em;

    @PostConstruct
    void create() {
	this.em = this.emf.createEntityManager();
    }

    @PreDestroy
    void destroy() {
	this.em.close();
	this.emf.close();
    }

    public List<Registration> getAll() {
	List<Registration> regists = null;
	regists = this.em.createQuery("SELECT r FROM Registration r", Registration.class)
		.getResultList();
	if (regists == null) {
	    regists = new ArrayList<Registration>();
	}
	return regists;
    }

    public void save(Registration r) {
	this.em.getTransaction().begin();
	this.em.persist(r);
	this.em.getTransaction().commit();
    }

    public void remove(Registration r) {
	this.em.getTransaction().begin();
	this.em.remove(r);
	this.em.getTransaction().commit();
    }

}
