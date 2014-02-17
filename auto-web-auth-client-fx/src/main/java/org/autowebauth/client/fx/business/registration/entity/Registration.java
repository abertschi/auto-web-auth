package org.autowebauth.client.fx.business.registration.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.autowebauth.client.fx.business.profile.entity.Profile;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Registration {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@Entity
public class Registration
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   // @ManyToOne
   @NotNull
   // @JoinColumn(nullable = false)
   private Profile profile;

   @NotNull
   @NotBlank
   private String ssid;

   private boolean autoConnectIfAvailable;

   // @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
   // orphanRemoval = true)
   // @JoinColumn(nullable = false)
   private User user;

   public Registration()
   {
      this.profile = null;
      this.ssid = "";
      this.autoConnectIfAvailable = false;
      this.user = null;
   }

   public Registration(String ssid)
   {
      this();
      this.ssid = ssid;
   }

   public Profile getProfile()
   {
      return this.profile;
   }

   public void setProfile(Profile profile)
   {
      this.profile = profile;
   }

   public String getSsid()
   {
      return this.ssid;
   }

   public void setSsid(String ssid)
   {
      this.ssid = ssid;
   }

   public boolean isAutoConnectIfAvailable()
   {
      return this.autoConnectIfAvailable;
   }

   public void setAutoConnectIfAvailable(boolean autoConnectIfAvailable)
   {
      this.autoConnectIfAvailable = autoConnectIfAvailable;
   }

   public User getUser()
   {
      return this.user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

}
