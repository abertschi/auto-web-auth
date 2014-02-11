package org.autowebauth.client.fx.business.database;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.autowebauth.client.fx.AppConstants;
import org.autowebauth.client.fx.business.configuration.control.Configurator;

/**
 * Produces {@code EntityManager} and {@code EntityManagerFactory}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@ApplicationScoped
public class DatabaseResources
{

   private EntityManagerFactory factory;

   private List<EntityManager> openedManagers;

   @Inject
   Configurator configurator;

   @PostConstruct
   public void init()
   {
      this.openedManagers = new LinkedList<EntityManager>();
      this.factory = Persistence.createEntityManagerFactory(AppConstants.JPA_PERST_UNIT,
            getConfigs());
   }

   @Produces
   public EntityManagerFactory getFactory()
   {
      return this.factory;
   }

   @Produces
   public EntityManager getManager()
   {
      EntityManager newManager = this.factory.createEntityManager();
      this.openedManagers.add(newManager);
      return newManager;
   }

   @PreDestroy
   public void cleanup()
   {
      // Make sure, all created managers are closed
      for (EntityManager em : this.openedManagers)
      {
         if (em.isOpen())
         {
            em.close();
         }
      }
      if (this.factory.isOpen())
      {
         this.factory.close();
      }
   }

   public Properties getConfigs()
   {
      // Starts embedded database
      Properties props = new Properties();
      props.setProperty("hibernate.show_sql", "true");
      props.setProperty("hibernate.format_sql", "true");
      props.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
      props.setProperty("hibernate.connection.url", "jdbc:h2:~/test2");
      props.setProperty("hibernate.connection.username", "sa");
      props.setProperty("hibernate.connection.password", "");
      props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
      props.setProperty("hibernate.hbm2ddl.auto", "update");

      return props;

   }

}
