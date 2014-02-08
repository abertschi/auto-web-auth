package org.autowebauth.client.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.autowebauth.client.fx.mvcprovider.DiManager;
import org.autowebauth.client.fx.presentation.summary.SummaryPresenter;
import org.autowebauth.client.fx.presentation.summary.SummaryView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class
 * 
 * @author Andrin Bertschi
 */
public class App extends Application
{

   private Logger log = LoggerFactory.getLogger(App.class);

   @Override
   public void start(Stage stage) throws Exception
   {
      this.log.info("Starting main application...");
      DiManager.getInstance().startUp();

      SummaryView summary = new SummaryView();
      SummaryPresenter presenter = (SummaryPresenter) summary.getPresenter();
      Scene scene = new Scene(summary.getView());
      stage.setTitle("Auto-Web-Auth");
      final String uri = getClass().getResource("app.css").toExternalForm();
      scene.getStylesheets().add(uri);
      stage.setScene(scene);
      stage.show();
   }

   @Override
   public void stop() throws Exception
   {
      DiManager.getInstance().shutDown();
      this.log.info("Stopping main application...");
   }

   public static void main(String[] args)
   {
      Application.launch(args);
   }
}
