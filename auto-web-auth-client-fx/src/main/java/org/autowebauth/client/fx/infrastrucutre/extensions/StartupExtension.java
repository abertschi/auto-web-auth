package org.autowebauth.client.fx.infrastrucutre.extensions;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessBean;

import org.autowebauth.client.fx.infrastrucutre.extensions.annotation.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartupExtension implements Extension
{
   private final Set<Bean<?>> startupBeans = new LinkedHashSet<Bean<?>>();

   private final Logger log = LoggerFactory.getLogger(getClass());

   public StartupExtension()
   {
      this.log.info("Extension loaded!");
   }

   /**
    * The container fires an event for each enabled bean, interceptor or
    * decorator deployed in a bean archive, before registering the
    * javax.enterprise.inject.spi.Bean object.
    * 
    * Scan beans for {@link Startup} annotation.
    * 
    * @param beanEvent
    *           event
    */
   <T> void processBean(@Observes ProcessBean<T> beanEvent)
   {
      this.log.info("{} is processing Bean before registering the Bean object", beanEvent
            .getAnnotated().getClass().getName());
      if (beanEvent.getAnnotated().isAnnotationPresent(Startup.class)
            && beanEvent.getAnnotated().isAnnotationPresent(ApplicationScoped.class))
      {
         this.startupBeans.add(beanEvent.getBean());
      }
   }

   /**
    * The event type of the third event fired by the container after it has
    * validated that there are no deployment problems and before creating
    * contexts or processing requests.
    * Initialize all Beans annotated with {@link Startup}.
    */
   <T> void AfterDeploymentValidation(@Observes AfterDeploymentValidation afterDeployment,
         BeanManager bm)
   {
      this.log.info("AfterDeploymentValidation Event was received");
      for (Bean<?> bean : this.startupBeans)
      {
         Object o = bm.getReference(bean, bean.getBeanClass(), bm.createCreationalContext(bean));
         o.toString(); // Init bean
      }
   }
}
