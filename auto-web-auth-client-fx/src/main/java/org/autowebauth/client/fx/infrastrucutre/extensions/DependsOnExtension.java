package org.autowebauth.client.fx.infrastrucutre.extensions;

import javax.enterprise.inject.spi.Extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependsOnExtension implements Extension
{
   // TODO

   private final Logger log = LoggerFactory.getLogger(getClass());

   public DependsOnExtension()
   {
      this.log.info("Extension loaded!");
   }
}
