package org.autowebauth.client.fx.business.profile.entity;

import java.util.Map;

import javafx.beans.property.LongProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableMap;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.jboss.weld.exceptions.UnsupportedOperationException;

/**
 * Profile {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@Entity
@Access(AccessType.PROPERTY)
public class Profile
{
   
   private LongProperty idProperty;
   
   private StringProperty nameProperty;
   
   private StringProperty targetUrlProperty;
   
   private StringProperty actionProperty;
   
   /**
    * Form parameters
    */
   private MapProperty<String, String> parameterProperty;
   
   public Profile()
   {
      this.idProperty = new SimpleLongProperty();
      this.targetUrlProperty = new SimpleStringProperty();
      this.actionProperty = new SimpleStringProperty();
      this.parameterProperty = new SimpleMapProperty<String, String>();
      this.nameProperty = new SimpleStringProperty();
   }
   
   public Profile(String name)
   {
      this();
      this.nameProperty.set(name);
   }
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public long getId()
   {
      return this.idProperty.get();
   }
   
   public void setId(long id)
   {
      this.idProperty.set(id);
   }
   
   public LongProperty idProperty()
   {
      return this.idProperty;
   }
   
   @NotNull
   @NotBlank
   public String getName()
   {
      return this.nameProperty.get();
   }
   
   public void setName(String name)
   {
      this.nameProperty.set(name);
   }
   
   public StringProperty nameProperty()
   {
      return this.nameProperty;
   }
   
   @NotNull
   @NotBlank
   public String getTargetUrl()
   {
      return this.targetUrlProperty.get();
   }
   
   public void setTargetUrl(String targetUrl)
   {
      this.targetUrlProperty.set(targetUrl);
   }
   
   public StringProperty targetUrlProperty()
   {
      return this.targetUrlProperty;
   }
   
   @NotNull
   @NotBlank
   public String getAction()
   {
      return this.actionProperty.get();
   }
   
   public void setAction(String action)
   {
      this.actionProperty.set(action);
   }
   
   public StringProperty actionProperty()
   {
      return this.actionProperty;
   }
   
   @ElementCollection
   public Map<String, String> getProperties()
   {
      return this.parameterProperty.get();
   }
   
   public void setProperties(Map<String, String> properties)
   {
      this.parameterProperty.set((ObservableMap<String, String>) properties); // TODO
      throw new UnsupportedOperationException("TODO: Check: is operation implementation legal?");
   }
   
   public MapProperty<String, String> parameterProperty()
   {
      return this.parameterProperty;
   }
   
   @Override
   public String toString()
   {
      return "Profile [idProperty=" + idProperty + ", nameProperty=" + nameProperty
            + ", targetUrlProperty=" + targetUrlProperty + ", actionProperty=" + actionProperty
            + ", parameterProperty=" + parameterProperty + "]";
   }
   
}
