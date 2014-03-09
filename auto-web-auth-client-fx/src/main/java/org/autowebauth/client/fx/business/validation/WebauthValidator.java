package org.autowebauth.client.fx.business.validation;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.control.Control;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.ConstraintDescriptor;

public class WebauthValidator
{
   
   private Set<ConstraintViolation<Object>> violations;
   
   public WebauthValidator()
   {
      this.violations = new HashSet<ConstraintViolation<Object>>();
   }
   
   public static Validator getDefault()
   {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      return factory.getValidator();
   }
   
   /**
    * Validate object
    * @return Set of constrain violations.
    */
   public static Set<ConstraintViolation<Object>> validate(Object o)
   {
      return getDefault().validate(o);
   }
   
   /**
    * Validates an object {@code source} and colors the responding widget, if constraintViolations occurs.
    * @param source object to be validated
    * @param widget widget to be colored, if errors occurred.
    */
   public Set<ConstraintViolation<Object>> validateAndColor(Object source, Control widget)
   {
      Set<ConstraintViolation<Object>> currentViolations = new HashSet<ConstraintViolation<Object>>();
      if (source == null)
      {
         colorWidget(widget);
         // TODO: Beanvalidation impl. thorws exception when source is null
         this.violations.add(new ConstraintViolationStub());
      }
      else
      {
         currentViolations = WebauthValidator.validate(source);
         if (!currentViolations.isEmpty())
         {
            colorWidget(widget);
         }
      }
      this.violations.addAll(currentViolations);
      return currentViolations;
   }
   
   public Set<ConstraintViolation<Object>> getOccurredViolationConstraints()
   {
      return this.violations;
   }
   
   public void cleanViolationConstraints()
   {
      this.violations = new HashSet<ConstraintViolation<Object>>();
   }
   
   // ---------------------------------------
   // private section
   // ---------------------------------------
   
   private void colorWidget(Control widget)
   {
      widget.setStyle("-fx-font-weight: bold; -fx-text-fill: red;");
   }
   
   // --------------------------------------
   // inner classes
   // --------------------------------------
   
   /**
    * Bug, hibernate validator throws exception, when field to be validated is null.
    * To count the field in this case as well, this class is used as the violation.
    */
   private class ConstraintViolationStub implements ConstraintViolation<Object>
   {
      
      @Override
      public String getMessage()
      {
         return "Darf nicht leer sein";
      }
      
      @Override
      public String getMessageTemplate()
      {
         return null;
      }
      
      @Override
      public Object getRootBean()
      {
         return null;
      }
      
      @Override
      public Class getRootBeanClass()
      {
         return null;
      }
      
      @Override
      public Object getLeafBean()
      {
         return null;
      }
      
      @Override
      public Object[] getExecutableParameters()
      {
         return null;
      }
      
      @Override
      public Object getExecutableReturnValue()
      {
         return null;
      }
      
      @Override
      public Path getPropertyPath()
      {
         return null;
      }
      
      @Override
      public Object getInvalidValue()
      {
         return null;
      }
      
      @Override
      public ConstraintDescriptor<?> getConstraintDescriptor()
      {
         return null;
      }
      
      @Override
      public <U> U unwrap(Class<U> type)
      {
         return null;
      }
      
   }
   
}
