package org.autowebauth.core.network.factory;

import org.autowebauth.core.api.network.NetworkFactory;
import org.autowebauth.core.api.network.exept.NetworkProvidementNotSupportedException;
import org.autowebauth.core.api.network.provider.NetworkProvider;
import org.autowebauth.core.network.winos.WinOsNetworkProvider;

/**
 * Factory to obtain wireless network provider.
 * 
 * @author abertschi
 * @since 15.11.2013
 * 
 */
public class DefaultNetworkFactoryImpl implements NetworkFactory
{

   private static final String OS = System.getProperty("os.name").toLowerCase();

   /**
    * Factory class
    */
   public DefaultNetworkFactoryImpl()
   {
   }

   /**
    * Get operating system dependent {@link NetworkProvider}
    * 
    * @return see description
    */
   @Override
   public NetworkProvider getProvider() throws NetworkProvidementNotSupportedException
   {
      NetworkProvider provider = null;
      if (isWindows())
      {
         provider = new WinOsNetworkProvider();
      }
      else
      {
         throw new NetworkProvidementNotSupportedException(
               "Your operating system isn't supported yet");
      }
      return provider;
   }

   @Override
   public NetworkProvider getProviderByClass(Class<? extends NetworkProvider> clazz)
   {
      return null;

   }

   private static boolean isWindows()
   {
      return (OS.indexOf("win") >= 0);
   }

   private static boolean isMac()
   {
      return (OS.indexOf("mac") >= 0);
   }

   private static boolean isUnix()
   {
      return (OS.indexOf("nux") >= 0);
   }
}
