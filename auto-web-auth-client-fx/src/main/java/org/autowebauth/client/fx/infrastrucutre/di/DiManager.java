package org.autowebauth.client.fx.infrastrucutre.di;

import javax.enterprise.inject.spi.BeanManager;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * Starts, provides and shuts down
 * {@code Context and Dependency Injection Container}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (01.12.2013)
 */
public class DiManager
{
   private static DiManager INSTANCE = null;

   /**
    * Weld
    */
   protected Weld weld;

   /**
    * Weld-Container
    */
   protected WeldContainer weldContainer;

   private DiManager()
   {
   }

   /**
    * Get instance of DiManager
    */
   public static DiManager getInstance()
   {
      if (INSTANCE == null)
      {
         INSTANCE = new DiManager();
      }
      return INSTANCE;
   }

   /**
    * Boot CDI container.
    */
   public void startUp()
   {
      if (this.weld != null)
      {
         throw new IllegalAccessError("Container already started");
      }
      this.weld = new Weld();
      this.weldContainer = this.weld.initialize();
   }

   /**
    * Shutdown DI container.
    */
   public void shutDown()
   {
      this.weld.shutdown();
   }

   /**
    * Get the beanmanager
    */
   public BeanManager getBeanManager()
   {
      return this.weldContainer.getBeanManager();
   }

   @SuppressWarnings("unchecked")
   public Object lookup(final Class<?> clazz)
   {
      return this.weldContainer.instance().select(clazz).get();
   }

   public static void main(String[] args)
   {
      System.out.println("Simple main appl. Only for testing purposes");
      DiManager.getInstance().startUp();
   }

}
