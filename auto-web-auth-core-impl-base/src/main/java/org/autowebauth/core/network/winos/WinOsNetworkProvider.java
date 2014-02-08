package org.autowebauth.core.network.winos;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.autowebauth.core.api.network.provider.Connection;
import org.autowebauth.core.api.network.provider.ConnectionAction;
import org.autowebauth.core.api.network.provider.ConnectionEvent;
import org.autowebauth.core.api.network.provider.HostInformation;
import org.autowebauth.core.api.network.provider.NetworkListener;
import org.autowebauth.core.api.network.provider.NetworkProvider;

public class WinOsNetworkProvider implements NetworkProvider
{

   protected List<NetworkListener> listeners;

   public WinOsNetworkProvider()
   {
      this.listeners = new ArrayList<NetworkListener>();
      tmp();
   }

   @Override
   public HostInformation getHost()
   {
      return null;
   }

   @Override
   public void registerListener(NetworkListener cl)
   {
      this.listeners.add(cl);
   }

   @Override
   public Connection getEstabilishedConnection()
   {
      return null;
   }

   @Override
   public List getConnections()
   {
      return null;
   }

   @Override
   public void connect(Connection c) throws ConnectException
   {
   }

   private void tmp()
   {
      Thread t = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            while (true)
            {
               ConnectionEvent e = new ConnectionEvent(ConnectionAction.CONNECTED, new Connection()
               {
                  @Override
                  public String getName()
                  {
                     return "Sample connection: " + System.currentTimeMillis();
                  }
               });
               for (NetworkListener nl : WinOsNetworkProvider.this.listeners)
               {
                  nl.onConnectionActivity(e);
               }
               try
               {
                  Thread.sleep(5000);
               }
               catch (InterruptedException ex)
               {
                  ex.printStackTrace();
               }
            }
         }
      });
      t.start();
   }
}
