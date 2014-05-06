package org.autowebauth.client.fx.infrastrucutre.screen.lifecycle;

import java.util.HashMap;
import java.util.Map;

public class CommunicationContext
{
   private Map<String, Object> context;
   
   private Class<?> senderClass;
   
   public CommunicationContext(Class<?> communicatorClass, Map<String, Object> communication)
   {
      this.senderClass = communicatorClass;
      this.context = communication;
   }
   
   public CommunicationContext(Class<?> communicatorClass) {
      this.senderClass = communicatorClass;
      this.context = new HashMap<String, Object>();
   }
   
   public Class<?> getSenderClass()
   {
      return senderClass;
   }
   
   public void setSenderClass(Class<?> senderClass)
   {
      this.senderClass = senderClass;
   }
   
   public Map<String, Object> getContext()
   {
      return context;
   }
   
}
