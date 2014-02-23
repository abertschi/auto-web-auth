package org.autowebauth.client.fx.presentation.createprofile;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.autowebauth.client.fx.business.network.boundary.NetworkEventDispatcher;
import org.autowebauth.client.fx.business.registration.boundary.RegistrationService;
import org.autowebauth.client.fx.business.registration.entity.Registration;
import org.autowebauth.client.fx.mvcprovider.screen.ScreenContext;
import org.autowebauth.core.api.network.provider.Connection;

public class CreateprofilePresenter implements Initializable
{
   
   @Inject
   private Stage stage;
   
   @Inject
   RegistrationService registrationBoundary;
   
   @Inject
   NetworkEventDispatcher networkEvents;
   
   @FXML
   private ComboBox<Registration> cbRegistrationSelection;
   
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
      // Initialize all items of combobox with registrations from boundary.
      this.cbRegistrationSelection.setItems(FXCollections.observableList(this.registrationBoundary.getAll()));
      
     
      
   }
   
   @FXML
   private void onSearchNetworks(ActionEvent event) {
      List<Connection> connections = networkEvents.getNetworkProvider().getConnections();
      if (connections != null) {
         
      }
      
   }
   
   /**
    * Action: Save the profile.
    */
   @FXML
   void onProfileCreated(ActionEvent event)
   {
      ScreenContext.current().release(CreateprofileView.class);
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
