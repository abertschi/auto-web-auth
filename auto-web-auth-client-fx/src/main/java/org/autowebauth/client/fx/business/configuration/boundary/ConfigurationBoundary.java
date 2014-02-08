package org.autowebauth.client.fx.business.configuration.boundary;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.autowebauth.client.fx.business.configuration.entity.Configuration;
import org.slf4j.Logger;

@ApplicationScoped
public class ConfigurationBoundary
{

   @Inject
   private EntityManager em;

   @Inject
   private Logger log;

   @PostConstruct
   void postConstruct()
   {
   }

   @PreDestroy
   void shutdown()
   {
      this.em.close();
   }

   public Configuration loadConfiguration()
   {
      Configuration c = this.em.find(Configuration.class, Configuration.ID);
      if (c == null)
      {
         c = loadDefaults();
      }
      return c;
   }

   public void save(Configuration c)
   {
      // this.em.getTransaction().begin();
      // this.em.merge(c);
      // this.em.getTransaction().commit();
   }

   private Configuration loadDefaults()
   {
      this.log.info("Default configurations are loaded");

      Configuration defaults = new Configuration();
      defaults.put("port", "2001");
      defaults.put("username", "username");
      defaults.put("password", "password");

      // this.em.getTransaction().begin();
      // this.em.persist(defaults);
      // this.em.getTransaction().commit();

      return defaults;
   }

}
