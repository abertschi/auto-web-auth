package org.autowebauth.client.fx.presentation.summary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.autowebauth.client.fx.business.network.annotation.WlanConnected;
import org.autowebauth.client.fx.business.profile.boundary.ProfileService;
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

   @PostConstruct
   public void init()
   {
      this.log.debug("init!");

   }

   void onNetworkChanges(@Observes @WlanConnected ConnectionEvent event)
   {
      this.log.info("reciving Network-Activity {}", event.getConnection().getName());
   }

}
