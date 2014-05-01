package org.autowebauth.client.fx.business.profile.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.autowebauth.client.fx.business.profile.entity.Profile;
import org.autowebauth.client.fx.infrastrucutre.di.DiManager;

/**
 * {@code Boundary} for {@link Profile} {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@ApplicationScoped
public class ProfileService {
    
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

    public List<Profile> getAll() {
	List<Profile> profiles = this.em.createQuery("SELECT p FROM Profile p", Profile.class)
		.getResultList();
	if (profiles == null) {
	    profiles = new ArrayList<Profile>();
	}
	return profiles;
    }

    public void save(Profile p) {
	this.em.getTransaction().begin();
	if (p.getId() == null | p.getId() == 0) {
	    this.em.persist(p);
	} else {
	    this.em.merge(p);
	}
	this.em.getTransaction().commit();
    }

    public void remove(Profile p) {
	this.em.getTransaction().begin();
	this.em.remove(p);
	this.em.getTransaction().commit();
    }

    Profile getProfile(long id) {
	return this.em.find(Profile.class, id);
    }

    public static void main(String[] args) {
	DiManager.getInstance().startUp();
	ProfileService s = (ProfileService) DiManager.getInstance().lookup(ProfileService.class);
	Profile p = new Profile();
	p.setName("BBW BMS 1");
	p.setAction("GET");
	p.setTargetUrl("http://www.google.ch");
	s.save(p);
	DiManager.getInstance().shutDown();
    }

}
