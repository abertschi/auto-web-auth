package org.autowebauth.commons;

public class PlatformUtil
{
   private static final String OS = System.getProperty("os.name").toLowerCase();

   private PlatformUtil()
   {
      throw new UnsupportedOperationException("Instanciation not allowed");
   }

   public static boolean isWindows()
   {
      return (OS.indexOf("win") >= 0);
   }

   public static boolean isMac()
   {
      return (OS.indexOf("mac") >= 0);
   }

   public static boolean isUnix()
   {
      return (OS.indexOf("nux") >= 0);
   }

}
