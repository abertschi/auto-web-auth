package org.autowebauth.client.fx.mvcprovider;

import javax.enterprise.inject.spi.BeanManager;

import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.core.api.provider.BeanProvider;

/**
 * Use the apache deltaspike project to stay implementation independently. This
 * class provides JSR299 CDI functionality.
 * 
 * @author Andrin Bertschi
 * @since 01.12.2013
 * @see http://deltaspike.apache.org/documentation.html#module-overview
 */
public class DiManager
{
   private static DiManager INSTANCE = null;

   /**
    * Apache deltaspike Cdi Wrapper.
    */
   protected CdiContainer cdiContainer;

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
    * 
    * @see {@link DiManager#shutDown()}
    */
   public void startUp()
   {
      if (this.cdiContainer != null)
      {
         throw new IllegalAccessError("Container already started");
      }
      this.cdiContainer = CdiContainerLoader.getCdiContainer();
      this.cdiContainer.boot();
      this.cdiContainer.getContextControl().startContexts();
   }

   /**
    * Boot CDI container and get managed class.
    * 
    * @param clazz
    *           Class to lookup
    */
   public <CLASS> CLASS startUp(Class<CLASS> clazz)
   {
      startUp();
      return lookUp(clazz);
   }

   /**
    * Lookup class by type.
    * 
    * @param clazz
    */
   public <CLASS> CLASS lookUp(Class<CLASS> clazz)
   {
      return BeanProvider.getContextualReference(clazz);
   }

   /**
    * Shutdown DI container.
    */
   public void shutDown()
   {
      this.cdiContainer.shutdown();
   }

   /**
    * Get the beanmanager
    */
   public BeanManager getBeanManager()
   {
      return this.cdiContainer.getBeanManager();
   }

   public static void main(String[] args)
   {
      System.out.println("Simple main appl. Only for testing purposes");
      DiManager.getInstance().startUp();
   }

}
