package org.autowebauth.core.network.winos;

import org.autowebauth.core.api.network.NetworkFactory;

public class SimpleTest
{
   private static final String NETWORK_FACTORY_IMPL = "org.autowebauth.core.network.DefaultNetworkFactoryImpl";

   public static void main(String[] args)
   {
      NetworkFactory factoryWrapper = null;
      try
      {
         Class c = Class.forName("java.lang.String");
         // factoryWrapper = (NetworkFactory)
         Class cf = Class.forName("org.autowebauth.core.network.factory.DefaultNetworkFactoryImpl");
      }
      catch (ClassNotFoundException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      System.out.println(factoryWrapper);
   }
}
