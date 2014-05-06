package org.autowebauth.client.fx.business.registration.entity;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User {@code entity}.
 * 
 * @author Andrin Bertschi
 * @since 1.0 (2014-02-14)
 * 
 */
@Entity
@Access(AccessType.PROPERTY)
public class User
{
    private LongProperty idProperty;

    private StringProperty nameProperty;

    private StringProperty passwordProperty;

    public User()
    {
        this.idProperty = new SimpleLongProperty();
        this.nameProperty = new SimpleStringProperty();
        this.passwordProperty = new SimpleStringProperty();
    }

    public User(String name)
    {
        this();
        this.nameProperty.set(name);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId()
    {
        return this.idProperty.get();
    }

    public void setId(Long id)
    {
        this.idProperty.set(id);
    }

    public LongProperty idProperty()
    {
        return idProperty;
    }

    @NotEmpty
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
        return nameProperty;
    }

    public String getPassword()
    {
        return this.passwordProperty.get();
    }

    public void setPassword(String password)
    {
        this.passwordProperty.set(password);
    }

    public StringProperty passwordProperty()
    {
        return passwordProperty;
    }

    @Override
    public String toString()
    {
        return "User [idProperty=" + idProperty + ", nameProperty="
                + nameProperty
                + ", passwordProperty=" + passwordProperty + "]";
    }

}