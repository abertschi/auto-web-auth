package org.autowebauth.client.fx.business.configuration.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity for configurations.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@Entity
public class Configuration
{

   public static final String ID = "configs";

   @Id
   private String id = ID;

   @ElementCollection
   private Map<String, String> values;

   public Configuration()
   {
      this.values = new HashMap<>();
   }

   public String put(String key, String value)
   {
      return this.values.put(key, value);
   }

   public String get(String key)
   {
      return this.values.get(key);
   }

   public void remove(String key)
   {
      this.values.remove(key);
   }

}
