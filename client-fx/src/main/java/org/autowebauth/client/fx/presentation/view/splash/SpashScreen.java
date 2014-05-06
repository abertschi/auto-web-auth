package org.autowebauth.client.fx.presentation.view.splash;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SpashScreen extends Preloader
{
   private ProgressBar progressBar;
   
   private Stage stage;
   
   @Override
   public void start(Stage stage) throws Exception
   {
      this.stage = stage;
      stage.setScene(createShlashscreenScene());
      stage.show();
   }
   
   private Scene createShlashscreenScene()
   {
      this.progressBar = new ProgressBar();
      BorderPane pane = new BorderPane();
      pane.setCenter(progressBar);
      return new Scene(pane, 300, 150);
   }

   @Override
   public void handleProgressNotification(ProgressNotification pn)
   {
      this.progressBar.setProgress(pn.getProgress());
   }
   
   @Override
   public void handleStateChangeNotification(StateChangeNotification evt)
   {
      if (evt.getType() == StateChangeNotification.Type.BEFORE_START)
      {
         this.stage.hide();
      }
   }
   
}
