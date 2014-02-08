package org.autowebauth.client.fx.business.database;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.autowebauth.client.fx.business.configuration.control.Configuration;
import org.autowebauth.client.fx.mvcprovider.extensions.annotation.Startup;
import org.h2.tools.Server;
import org.slf4j.Logger;

@Startup
@ApplicationScoped
public class ServerRunner
{
   @Inject
   private Logger log;

   @Inject
   @Configuration("port")
   private String port;

   @Inject
   @Configuration("path")
   private String path;

   private Server server;

   @PostConstruct
   public void boot()
   {
      this.server = createServer();
      try
      {
         this.server.start();
         this.log.info("Local database server is starting ...");
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
   }

   @PreDestroy
   public void shutdown()
   {
      this.server.shutdown();
   }

   private Server createServer()
   {
      try
      {
         return Server.createTcpServer(getServerArgs());
      }
      catch (SQLException e)
      {
         this.log.error("Server not able to get created {}", e.getLocalizedMessage());
      }
      return null;
   }

   private String[] getServerArgs()
   {
      String[] args = new String[2];
      args[0] = "-tcpPort";
      args[1] = this.port;

      return args;
   }
}
