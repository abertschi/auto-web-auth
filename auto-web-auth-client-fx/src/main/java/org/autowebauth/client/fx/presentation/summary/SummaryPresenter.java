package org.autowebauth.client.fx.presentation.summary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.autowebauth.client.fx.business.network.annotation.WlanConnected;
import org.autowebauth.client.fx.business.profile.boundary.ProfileService;
import org.autowebauth.client.fx.presentation.createprofile.CreateprofileView;
import org.autowebauth.client.fx.presentation.modifyprofile.ModifyprofileView;
import org.autowebauth.core.api.network.provider.ConnectionEvent;
import org.slf4j.Logger;

public class SummaryPresenter
{
   @Inject
   private Logger log;
   
   @FXML
   private Button startButton;
   
   @FXML
   private Button stopButton;
   
   @Inject
   ProfileService profileService;
   
   @Inject
   Stage primaryStage;
   
   @PostConstruct
   public void init()
   {
   }
   
   /**
    * Get network activities from os layer
    */
   void onNetworkChanges(@Observes @WlanConnected ConnectionEvent event)
   {
   }
   
   /**
    * Start automated network login attends
    */
   @FXML
   void onStartTrackingAction(ActionEvent event)
   {
      
   }
   
   /**
    * Stop automated network login attends
    */
   @FXML
   void onStopTrackingAction(ActionEvent event)
   {
      
   }
   
   /**
    * Jump to 'createProfile' Screen
    */
   @FXML
   void onNewProfileAction(ActionEvent event)
   {
      CreateprofileView createProfile = new CreateprofileView();
      Scene newScene = new Scene(createProfile.getRoot()));
      Scene oldScene = this.primaryStage.getScene();
      
      StackPane stackPane = new StackPane();
      stackPane.getChildren().add(newScene.getRoot());
      stackPane.getChildren().add(oldScene.getRoot());
      this.primaryStage.setTitle("Create your profile");
   }
   
   /**
    * Jump to 'modifyProfile' Screen
    */
   @FXML
   void onModifyProfileAction(ActionEvent event)
   {
      ModifyprofileView modiyProfile = new ModifyprofileView();
      this.primaryStage.setScene(new Scene(modiyProfile.getRoot()));
      this.primaryStage.setTitle("Modify your profile");
   }
   
}
