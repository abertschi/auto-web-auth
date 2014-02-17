package org.autowebauth.client.fx.mvcprovider;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneConfiguration
{
   private Scene scene;

   private Stage stage;

   private AbstractView view;

   public SceneConfiguration(Stage stage, Scene scene, AbstractView view)
   {
      this.stage = stage;
      this.scene = scene;
      this.view = view;
   }

   public Scene getScene()
   {
      return this.scene;
   }

   public AbstractView getView()
   {
      return this.view;
   }

   public Stage getStage()
   {
      return this.stage;
   }

}
