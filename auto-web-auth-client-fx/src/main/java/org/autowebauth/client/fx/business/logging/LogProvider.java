package org.autowebauth.client.fx.business.logging;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CDI Log producer.
 * 
 * @author Andrin Bertschi
 */
@ApplicationScoped
public class LogProvider
{
   @Produces
   public Logger produceLogger(InjectionPoint ip)
   {
      return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
   }

}
