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

/**
 * {@code Boundary} for {@link Profile} {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
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
//      List<Profile> profiles = this.em.createQuery("SELECT * FROM Profile p", Profile.class)
//            .getResultList();
      List<Profile> returned = new ArrayList<Profile>();
      System.out.println("GET ALL PROFILE REQUESTED");
      for (int i = 0; i< 5; i++){
         Profile p = new Profile();
         p.setName("name: " + i);
         returned.add(p);
      }
      try
      {
         Thread.sleep(5000);
      }
      catch (InterruptedException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("GET ALL PROFILE RETURNED");
      return returned;
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
