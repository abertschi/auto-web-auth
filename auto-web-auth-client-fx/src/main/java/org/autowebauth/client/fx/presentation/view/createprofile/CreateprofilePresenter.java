package org.autowebauth.client.fx.presentation.view.createprofile;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;

import org.autowebauth.client.fx.business.network.boundary.NetworkEventDispatcher;
import org.autowebauth.client.fx.business.profile.boundary.ProfileService;
import org.autowebauth.client.fx.business.profile.entity.Profile;
import org.autowebauth.client.fx.business.registration.boundary.RegistrationService;
import org.autowebauth.client.fx.business.registration.entity.Registration;
import org.autowebauth.client.fx.business.registration.entity.User;
import org.autowebauth.client.fx.business.validation.WebauthValidator;
import org.autowebauth.client.fx.infrastrucutre.screen.ScreenContext;
import org.autowebauth.core.api.network.provider.conn.Connection;
import org.slf4j.Logger;

public class CreateprofilePresenter implements Initializable
{
   
   // --------------------------------------
   // services
   // --------------------------------------
   
   @Inject
   Logger log;
   
   @Inject
   Stage stage;
   
   @Inject
   RegistrationService registrationBoundary;
   
   @Inject
   ProfileService profieBoundary;
   
   @Inject
   NetworkEventDispatcher networkEvents;
   
   /**
    * The new registration, the user creates.
    */
   private Registration registration;
   
   // --------------------------------------
   // UI
   // --------------------------------------
   
   @FXML
   private Label lbSelectRegistration;
   
   @FXML
   private Label lbPassword;
   
   @FXML
   private Label lbSelectNetwork;
   
   @FXML
   private Label lbUsername;
   
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
   
   // --------------------------------------
   // methods
   // --------------------------------------
   
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
      Bindings.bindBidirectional(this.cbRegistrationSelection.valueProperty(),regist.profileProperty());
      Bindings.bindBidirectional(this.loginIfConnected.selectedProperty(),regist.autoConnectIfAvailableProperty());
   }
   
   private Set<ConstraintViolation<Object>> validateRegistration(Registration regist)
   {
      WebauthValidator v = new WebauthValidator();
//      v.validateAndColor(regist.getProfile(), this.lbSelectRegistration);
      v.validateAndColor(regist.getUser().getName(), this.lbUsername);
      v.validateAndColor(regist.getUser().getPassword(), this.lbPassword);
      v.validateAndColor(regist.getSsid(), this.lbSelectNetwork);
      
      this.log.info("Validation constraints occurred: {}", v.getOccurredViolationConstraints());
      return v.getOccurredViolationConstraints();
   }
   
   // --------------------------------------
   // actions
   // --------------------------------------
   
   /**
    * Action: search networks
    */
   @FXML
   void onSearchNetworks(ActionEvent event)
   {
      List<Connection> connections = networkEvents.getNetworkProvider().getConnections();
      if (connections != null)
      {
      }
   }
   
   /**
    * Action: Save the profile.
    */
   @FXML
   void onProfileCreated(ActionEvent event)
   {
      Set<ConstraintViolation<Object>> violations = validateRegistration(this.registration);
      if (violations == null || violations.size() == 0)
      {
         this.registrationBoundary.save(this.registration);
         ScreenContext.current().release(CreateprofileView.class);
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
