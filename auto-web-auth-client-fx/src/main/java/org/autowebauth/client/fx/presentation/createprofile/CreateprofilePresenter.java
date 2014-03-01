package org.autowebauth.client.fx.presentation.createprofile;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.autowebauth.client.fx.business.network.boundary.NetworkEventDispatcher;
import org.autowebauth.client.fx.business.profile.boundary.ProfileService;
import org.autowebauth.client.fx.business.profile.entity.Profile;
import org.autowebauth.client.fx.business.registration.boundary.RegistrationService;
import org.autowebauth.client.fx.business.registration.entity.Registration;
import org.autowebauth.client.fx.business.registration.entity.User;
import org.autowebauth.client.fx.mvcprovider.screen.ScreenContext;
import org.autowebauth.core.api.network.provider.Connection;

public class CreateprofilePresenter implements Initializable
{
   // -----------------------
   // services
   // -----------------------
   
   @Inject
   Stage stage;
   
   @Inject
   RegistrationService registrationBoundary;
   
   @Inject
   ProfileService profieBoundary;
   
   @Inject
   NetworkEventDispatcher networkEvents;
   
   @Inject
   WebauthValidator validator;
   
   /**
    * The new registration, the user creates.
    */
   private Registration registration;
   
   // -----------------------
   // UI
   // -----------------------

   @FXML
   private ComboBox<Profile> cbRegistrationSelection;
   
   @FXML
   private TextField tfNetwork;
   
   @FXML
   private Button btSearchNetworks;
   
   @FXML
   private TextField tfUsername;
   
   @FXML
   private TextField tfPassword;
   
   @FXML
   private CheckBox loginIfConnected;
   
   @PostConstruct
   public void cdiInit()
   {
      this.stage.getScene().getWindow().setOnCloseRequest(new OnProfileAbortHandler());
   }
   
   /**
    * Initialize injected FXML components.
    */
   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      this.registration = new Registration();
      bindRegistration(registration);
      
      Platform.runLater(new Runnable()
      {
         @Override
         public void run()
         {
            cbRegistrationSelection.setItems(FXCollections.observableList(profieBoundary.getAll()));
         }
      });
   }
   
   private void bindRegistration(Registration regist)
   {
      User user = new User();
      regist.setUser(user);
      Bindings.bindBidirectional(this.tfUsername.textProperty(), user.nameProperty());
      Bindings.bindBidirectional(this.tfPassword.textProperty(), user.passwordProperty());
      Bindings.bindBidirectional(this.tfNetwork.textProperty(), regist.ssidProperty());
      Bindings.bindBidirectional(this.cbRegistrationSelection.valueProperty(), regist.profileProperty());
      Bindings.bindBidirectional(this.loginIfConnected.selectedProperty(), regist.autoConnectIfAvailableProperty());
   }
   
   @FXML
   private void onSearchNetworks(ActionEvent event)
   {
      List<Connection> connections = networkEvents.getNetworkProvider().getConnections();
      if (connections != null)
      {
      }
   }
   
   private void validateRegistration(Registration regist)
   {
      validate(regist.getProfile(), this.cbRegistrationSelection);
      validate(regist.getUser().getName(), this.tfUsername);
      validate(regist.getUser().getPassword(), this.tfPassword);
      validate(regist.getSsid(), this.tfNetwork);
   }
   
   private void validate(Object source, Control widget)
   {
      Validator valid = ValidatorUtil.getDefault();
      Set<ConstraintViolation<Object>> violations = valid.validate(source);
      if (!violations.isEmpty())
      {
         widget.setTooltip(createToolTip(violations));
      }
   }
   
   private <T extends Object> Tooltip createToolTip(Set<ConstraintViolation<T>> violations)
   {
      Tooltip tip = new Tooltip("Upps!");
      for (ConstraintViolation<T> c : violations)
      {
         tip.setText(tip.getText() + "\n" + c.getMessage());
      }
      return tip;
   }
   
   /**
    * Action: Save the profile.
    */
   @FXML
   void onProfileCreated(ActionEvent event)
   {
      validateRegistration(this.registration);
      Set<ConstraintViolation<Object>> errors = this.validator.validate(this.registration);
      if (errors == null || errors.size() == 0)
      {
         ScreenContext.current().release(CreateprofileView.class);
      }
      else
      {
         // Validation errors
         for (ConstraintViolation<Object> vio : errors)
         {
            System.out.println(vio.getMessage());
         }
      }
   }
   
   /**
    * Action: Abort creation.
    */
   class OnProfileAbortHandler implements EventHandler<javafx.stage.WindowEvent>
   {
      @Override
      public void handle(javafx.stage.WindowEvent event)
      {
         // make sure, not to affect other views by this listener
         // after current view was released
         stage.getScene().getWindow().setOnCloseRequest(null);
         
         ScreenContext.current().release(CreateprofileView.class);
         event.consume();
      }
   }
}
