package org.autowebauth.client.fx;

import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.autowebauth.client.fx.mvcprovider.StartupStage;
import org.autowebauth.client.fx.mvcprovider.screen.ScreenContext;
import org.autowebauth.client.fx.presentation.summary.SummaryView;
import org.slf4j.Logger;

@ApplicationScoped
public class App
{
   @Inject
   private Logger log;
   
   private Stage stage;
   
   public void start(@Observes @StartupStage Stage stage)
   {
      this.log.info("Stage event received");
      
      this.stage = stage;
      showSummaryView();
   }
   
   @Produces
   public Stage produceStage(InjectionPoint ip)
   {
      return this.stage;
   }
   
   private void showSummaryView()
   {
      SummaryView summary = new SummaryView();
      final String uri = getClass().getResource("app.css").toExternalForm();
      ScreenContext.current().show(summary);
      stage.getScene().getStylesheets().add(uri);
      stage.show();
   }
   
}
