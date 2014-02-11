package org.autowebauth.client.fx.presentation.summary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.autowebauth.client.fx.business.network.annotation.WlanConnected;
import org.autowebauth.client.fx.business.profile.boundary.ProfileService;
import org.autowebauth.client.fx.mvcprovider.AbstractPresenter;
import org.autowebauth.client.fx.presentation.about.AboutView;
import org.autowebauth.core.api.network.provider.ConnectionEvent;
import org.slf4j.Logger;

public class SummaryPresenter extends AbstractPresenter implements Initializable
{
   @Inject
   private Logger log;

   @FXML
   private Button startButton;

   @FXML
   private Button stopButton;

   @Inject
   ProfileService profileService;

   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      this.startButton.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            AboutView about = new AboutView();
            about.getView();
            SummaryPresenter.this.log.info("action event ");
         }
      });
   }

   @PostConstruct
   public void init()
   {
   }

   void onNetworkChanges(@Observes @WlanConnected ConnectionEvent event)
   {
      this.log.info("reciving Network-Activity {}", event.getConnection().getName());
   }

}
