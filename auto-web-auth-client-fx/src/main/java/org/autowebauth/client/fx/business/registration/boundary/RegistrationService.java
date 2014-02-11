package org.autowebauth.client.fx.business.registration.boundary;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.autowebauth.client.fx.business.registration.entity.Registration;

/**
 * {@code Boundary} for {@link Registration} {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@ApplicationScoped
public class RegistrationService
{

   @Inject
   private EntityManagerFactory emf;

   private EntityManager em;

   private EntityTransaction et;

   @PostConstruct
   public void create()
   {
      this.em = this.emf.createEntityManager();
      this.et = this.em.getTransaction();
   }

   @PreDestroy
   public void destroy()
   {
      this.em.close();
      this.emf.close();
   }

   public List<Registration> getAll()
   {
      return null;
   }

   public Registration save(Registration r)
   {
      return r;
   }

   public void remove(Registration r)
   {
   }

}
