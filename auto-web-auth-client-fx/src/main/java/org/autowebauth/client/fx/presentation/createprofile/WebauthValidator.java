package org.autowebauth.client.fx.presentation.createprofile;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@ApplicationScoped
public class WebauthValidator
{
   private ValidatorFactory factory;
   
   private Validator validator;
   
   public WebauthValidator()
   {
      this.factory = Validation.buildDefaultValidatorFactory();
      this.validator = factory.getValidator();
   }
   
   /**
    * Apply beanvalidation to given object.
    * @return Set of constrain violations.
    */
   public Set<ConstraintViolation<Object>> validate(Object o)
   {
      Set<ConstraintViolation<Object>> constraintViolations = this.validator.validate(o);
      return constraintViolations;
   }
   
}
