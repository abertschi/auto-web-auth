package org.autowebauth.client.fx.business.logging;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.Marker;

@Decorator
public abstract class AppLogger implements Logger
{

   @Inject
   @Delegate
   private Logger logger;

   public AppLogger()
   {
      // TODO Auto-generated constructor stub
   }

   @Override
   public String getName()
   {
      // TODO Auto-generated method stub
      return this.logger.getName();
   }

   @Override
   public boolean isTraceEnabled()
   {
      // TODO Auto-generated method stub
      return this.logger.isTraceEnabled();
   }

   @Override
   public void trace(String msg)
   {
      // TODO Auto-generated method stub
      this.logger.trace(msg);
   }

   @Override
   public void trace(String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.trace(format, arg);
   }

   @Override
   public void trace(String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.trace(format, arg1, arg2);
   }

   @Override
   public void trace(String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.trace(format, arguments);
   }

   @Override
   public void trace(String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.trace(msg, t);
   }

   @Override
   public boolean isTraceEnabled(Marker marker)
   {
      // TODO Auto-generated method stub
      return this.logger.isTraceEnabled(marker);
   }

   @Override
   public void trace(Marker marker, String msg)
   {
      // TODO Auto-generated method stub
      this.logger.trace(marker, msg);
   }

   @Override
   public void trace(Marker marker, String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.trace(marker, format, arg);
   }

   @Override
   public void trace(Marker marker, String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.trace(marker, format, arg1, arg2);
   }

   @Override
   public void trace(Marker marker, String format, Object... argArray)
   {
      // TODO Auto-generated method stub
      this.logger.trace(marker, format, argArray);
   }

   @Override
   public void trace(Marker marker, String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.trace(marker, msg, t);
   }

   @Override
   public boolean isDebugEnabled()
   {
      // TODO Auto-generated method stub
      return this.logger.isDebugEnabled();
   }

   @Override
   public void debug(String msg)
   {
      // TODO Auto-generated method stub
      this.logger.debug(msg);
   }

   @Override
   public void debug(String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.debug(format, arg);
   }

   @Override
   public void debug(String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.debug(format, arg1, arg2);
   }

   @Override
   public void debug(String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.debug(format, arguments);
   }

   @Override
   public void debug(String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.debug(msg, t);
   }

   @Override
   public boolean isDebugEnabled(Marker marker)
   {
      // TODO Auto-generated method stub
      return this.logger.isDebugEnabled(marker);
   }

   @Override
   public void debug(Marker marker, String msg)
   {
      // TODO Auto-generated method stub
      this.logger.debug(marker, msg);
   }

   @Override
   public void debug(Marker marker, String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.debug(marker, format, arg);
   }

   @Override
   public void debug(Marker marker, String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.debug(marker, format, arg1, arg2);
   }

   @Override
   public void debug(Marker marker, String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.debug(marker, format, arguments);
   }

   @Override
   public void debug(Marker marker, String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.debug(marker, msg, t);
   }

   @Override
   public boolean isInfoEnabled()
   {
      // TODO Auto-generated method stub
      return this.logger.isInfoEnabled();
   }

   @Override
   public void info(String msg)
   {
      // TODO Auto-generated method stub
      this.logger.info(msg);
   }

   @Override
   public void info(String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.info(format, arg);
   }

   @Override
   public void info(String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.info(format, arg1, arg2);
   }

   @Override
   public void info(String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.info(format, arguments);
   }

   @Override
   public void info(String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.info(msg, t);
   }

   @Override
   public boolean isInfoEnabled(Marker marker)
   {
      // TODO Auto-generated method stub
      return this.logger.isInfoEnabled(marker);
   }

   @Override
   public void info(Marker marker, String msg)
   {
      // TODO Auto-generated method stub
      this.logger.info(marker, msg);
   }

   @Override
   public void info(Marker marker, String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.info(marker, format, arg);
   }

   @Override
   public void info(Marker marker, String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.info(marker, format, arg1, arg2);
   }

   @Override
   public void info(Marker marker, String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.info(marker, format, arguments);
   }

   @Override
   public void info(Marker marker, String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.info(marker, msg, t);
   }

   @Override
   public boolean isWarnEnabled()
   {
      // TODO Auto-generated method stub
      return this.logger.isWarnEnabled();
   }

   @Override
   public void warn(String msg)
   {
      // TODO Auto-generated method stub
      this.logger.warn(msg);
   }

   @Override
   public void warn(String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.warn(format, arg);
   }

   @Override
   public void warn(String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.warn(format, arguments);
   }

   @Override
   public void warn(String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.warn(format, arg1, arg2);
   }

   @Override
   public void warn(String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.warn(msg, t);
   }

   @Override
   public boolean isWarnEnabled(Marker marker)
   {
      // TODO Auto-generated method stub
      return this.logger.isWarnEnabled(marker);
   }

   @Override
   public void warn(Marker marker, String msg)
   {
      // TODO Auto-generated method stub
      this.logger.warn(marker, msg);
   }

   @Override
   public void warn(Marker marker, String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.warn(marker, format, arg);
   }

   @Override
   public void warn(Marker marker, String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.warn(marker, format, arg1, arg2);
   }

   @Override
   public void warn(Marker marker, String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.warn(marker, format, arguments);
   }

   @Override
   public void warn(Marker marker, String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.warn(marker, msg, t);
   }

   @Override
   public boolean isErrorEnabled()
   {
      // TODO Auto-generated method stub
      return this.logger.isErrorEnabled();
   }

   @Override
   public void error(String msg)
   {
      // TODO Auto-generated method stub
      this.logger.error(msg);
   }

   @Override
   public void error(String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.error(format, arg);
   }

   @Override
   public void error(String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.error(format, arg1, arg2);
   }

   @Override
   public void error(String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.error(format, arguments);
   }

   @Override
   public void error(String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.error(msg, t);
   }

   @Override
   public boolean isErrorEnabled(Marker marker)
   {
      // TODO Auto-generated method stub
      return this.logger.isErrorEnabled(marker);
   }

   @Override
   public void error(Marker marker, String msg)
   {
      // TODO Auto-generated method stub
      this.logger.error(marker, msg);
   }

   @Override
   public void error(Marker marker, String format, Object arg)
   {
      // TODO Auto-generated method stub
      this.logger.error(marker, format, arg);
   }

   @Override
   public void error(Marker marker, String format, Object arg1, Object arg2)
   {
      // TODO Auto-generated method stub
      this.logger.error(marker, format, arg1, arg2);
   }

   @Override
   public void error(Marker marker, String format, Object... arguments)
   {
      // TODO Auto-generated method stub
      this.logger.error(marker, format, arguments);
   }

   @Override
   public void error(Marker marker, String msg, Throwable t)
   {
      // TODO Auto-generated method stub
      this.logger.error(marker, msg, t);
   }

}
