package org.autowebauth.client.fx.business.registration.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * User {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@Entity
public class User
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   @NotNull
   @NotBlank
   private String name;

   @NotNull
   @NotBlank
   private String password;

   public User()
   {
      this.name = "";
      this.password = "";
   }

   public User(String name)
   {
      this.name = name;
      this.password = "";
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getPassword()
   {
      return this.password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public long getId()
   {
      return this.id;
   }

   public void setId(long id)
   {
      this.id = id;
   }
}