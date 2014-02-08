package org.autowebauth.client.fx.business.profile.boundary;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.autowebauth.client.fx.business.profile.entity.Profile;

@ApplicationScoped
public class ProfileService
{
   @Inject
   private EntityManagerFactory emf;

   private EntityManager em;

   @PostConstruct
   public void create()
   {
      this.em = this.emf.createEntityManager();
   }

   @PreDestroy
   public void destroy()
   {
      this.em.close();
      this.emf.close();
   }

   public List<Profile> getAll()
   {
      List<Profile> profiles = this.em.createQuery("SELECT * FROM Profile p", Profile.class)
            .getResultList();
      return profiles;
   }

   public void save(Profile p)
   {
      this.em.getTransaction().begin();
      if (getProfile(p.getId()) == null)
      {
         this.em.persist(p);
      }
      else
      {
         this.em.merge(p);
      }
      this.em.getTransaction().commit();
   }

   public void remove(Profile p)
   {
      this.em.getTransaction().begin();
      this.em.remove(p);
      this.em.getTransaction().commit();
   }

   Profile getProfile(long id)
   {
      return this.em.find(Profile.class, id);
   }

}
