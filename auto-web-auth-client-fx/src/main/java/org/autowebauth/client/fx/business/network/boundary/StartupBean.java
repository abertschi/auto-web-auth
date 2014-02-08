package org.autowebauth.client.fx.business.network.boundary;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.autowebauth.client.fx.mvcprovider.extensions.annotation.Startup;
import org.slf4j.Logger;

/**
 * Just to demo the Startup Extension
 * 
 * @author abertschi
 * 
 */
@Startup
@ApplicationScoped
public class StartupBean
{

   @Inject
   private Logger log;

   @PostConstruct
   void postConstruct()
   {
      this.log.info("HELLO FROM @Startup Bean");
   }
}
