package org.autowebauth.client.fx.business.profile.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Profile {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@Entity
public class Profile
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   @NotNull
   @NotBlank
   private String name;

   @NotNull
   @NotBlank
   private String targetUrl;

   @NotNull
   @NotBlank
   private String actionType;

   @ElementCollection
   private Map<String, String> properties;

   public Profile()
   {
   }

   public Profile(String name)
   {
      this.name = name;
      this.targetUrl = "";
      this.actionType = "";
      this.properties = new HashMap<String, String>();
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getTargetUrl()
   {
      return this.targetUrl;
   }

   public void setTargetUrl(String targetUrl)
   {
      this.targetUrl = targetUrl;
   }

   public String getActionType()
   {
      return this.actionType;
   }

   public void setActionType(String actionType)
   {
      this.actionType = actionType;
   }

   public Map<String, String> getProperties()
   {
      return this.properties;
   }

   public void setProperties(Map<String, String> properties)
   {
      this.properties = properties;
   }

   public long getId()
   {
      return this.id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   @Override
   public String toString()
   {
      return "Profile [id=" + this.id + ", name=" + this.name + ", targetUrl=" + this.targetUrl
            + ", actionType=" + this.actionType + ", properties=" + this.properties + "]";
   }

}
