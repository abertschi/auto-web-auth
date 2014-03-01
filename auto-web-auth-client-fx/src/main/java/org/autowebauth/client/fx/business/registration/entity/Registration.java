package org.autowebauth.client.fx.business.registration.entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Access(AccessType.PROPERTY)
public class Registration
{
   private LongProperty idProperty;
   
   private ObjectProperty<Profile> profileProperty;
   
   private StringProperty ssidProperty;
   
   private BooleanProperty autoConnectIfAvailableProperty;
   
   private ObjectProperty<User> userProperty;
   
   public Registration()
   {
      this.idProperty = new SimpleLongProperty();
      this.profileProperty = new SimpleObjectProperty<Profile>();
      this.ssidProperty = new SimpleStringProperty();
      this.autoConnectIfAvailableProperty = new SimpleBooleanProperty(true);
      this.userProperty = new SimpleObjectProperty<User>();
   }
   
   public Registration(String ssid)
   {
      this();
      this.ssidProperty.set(ssid);
   }
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public long getId()
   {
      return idProperty.get();
   }
   
   public void setId(long idProperty)
   {
      this.idProperty.set(idProperty);
   }
   
   public LongProperty idProperty()
   {
      return idProperty;
   }
   
   @ManyToOne
   @NotNull
   @JoinColumn(nullable = false)
   public Profile getProfile()
   {
      return this.profileProperty.get();
   }
   
   public void setProfile(Profile profile)
   {
      this.profileProperty.set(profile);
   }
   
   public ObjectProperty<Profile> profileProperty()
   {
      return profileProperty;
   }
   
   @NotNull
   @NotBlank
   public String getSsid()
   {
      return this.ssidProperty.get();
   }
   
   public void setSsid(String ssid)
   {
      this.ssidProperty.set(ssid);
   }
   
   public StringProperty ssidProperty()
   {
      return ssidProperty;
   }
   
   public boolean isAutoConnectIfAvailable()
   {
      return this.autoConnectIfAvailableProperty.get();
   }
   
   public void setAutoConnectIfAvailable(boolean autoConnectIfAvailable)
   {
      this.autoConnectIfAvailableProperty.set(autoConnectIfAvailable);
   }
   
   public BooleanProperty autoConnectIfAvailableProperty()
   {
      return autoConnectIfAvailableProperty;
   }
   
   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn()
   public User getUser()
   {
      return this.userProperty.get();
   }
   
   public void setUser(User u)
   {
      this.userProperty.set(u);
   }
   
   public ObjectProperty<User> userProperty()
   {
      return userProperty;
   }
   
}
