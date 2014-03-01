package org.autowebauth.client.fx.presentation.createprofile;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.jboss.weld.exceptions.UnsupportedOperationException;

public class ValidatorUtil
{
   
   private ValidatorUtil() {
      throw new UnsupportedOperationException("No instanciation permitted");
   }
   
   public static Validator getDefault() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      return factory.getValidator();
   }
   
}
