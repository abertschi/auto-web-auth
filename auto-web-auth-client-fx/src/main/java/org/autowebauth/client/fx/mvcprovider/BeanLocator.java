package org.autowebauth.client.fx.mvcprovider;

/**
 * @author abertschi
 * @since 29.11.2013
 */
@Deprecated
public class BeanLocator // dont use anymore. If required, do by your own
{

   private BeanLocator()
   {
      throw new UnsupportedOperationException("No instances permitted");
   }

   public <TYPE> TYPE instanciateByClass(Class<TYPE> clazz)
   {
      TYPE object = null;
      try
      {
         object = clazz.newInstance();
      }
      catch (InstantiationException e)
      {
         throw new IllegalArgumentException("Not able to instanciate class", e);
      }
      catch (IllegalAccessException e)
      {
         throw new IllegalArgumentException("Class not correctly accessable", e);
      }
      return object;
   }

}
