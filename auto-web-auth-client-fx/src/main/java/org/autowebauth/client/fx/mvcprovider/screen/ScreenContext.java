package org.autowebauth.client.fx.mvcprovider.screen;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.autowebauth.client.fx.mvcprovider.AbstractView;
import org.autowebauth.client.fx.mvcprovider.DiManager;
import org.jboss.weld.exceptions.UnsupportedOperationException;

/**
 * Context to handle screen flows.
 *
 *<b>Basic usage</b>
 * 
 * <pre>
 * 
 * // Create a scene and apply it to the stage.
 * CreateprofileView createProfile = new CreateprofileView();
 * ScreenContext.current().show(createProfile);
 *     
 * // Release currently shown scene and load last shown
 * ScreenContext.current().release(createProfile);
 *  
 * </pre>
 * 
 * @author abertschi
 *
 */
@ApplicationScoped
public class ScreenContext
{
   private static ScreenContext instance;
   
   @Inject
   private Stage primaryStage;
   
   private Stack<ViewEntry> viewStack;
   
   ScreenContext()
   { // for CDI only!
      this.viewStack = new Stack<ViewEntry>();
   }
   
   public static ScreenContext current()
   {
      if (instance == null)
      {
         instance = (ScreenContext) DiManager.getInstance().lookup(ScreenContext.class);
      }
      return instance;
   }
   
   public Stage show(AbstractView view)
   {
      Scene scene = new Scene(view.getRoot());
      ViewEntry newView = new ViewEntry(view, scene);
      Stage returnedStage = null;
      if (this.viewStack.size() > 0)
      {
         // Apply a transition animation
         // sceneTransistion(this.viewStack.peek().getScene(),
         // newView.getScene());
         // transistionStage(this.viewStack.peek(), entry, 2000);
      }

      prepareStage(newView);
      this.viewStack.push(newView);
      
      return primaryStage;
   }
   
   public <T extends AbstractView> Stage load(Class<T> view)
   {
      throw new UnsupportedOperationException("not yet implemented");
      
   }
   
   public <T extends AbstractView> AbstractView release(Class<T> viewType)
   {
      ViewEntry current = null;
      if (this.viewStack.size() > 0)
      {
         current = this.viewStack.pop();
      }
      
      if (this.viewStack.size() > 0)
      {
         // Load last one in stack
         prepareStage(this.viewStack.peek());
      }
      
      // Return released view
      return current != null ? current.getView() : null;
   }
   
   Stage prepareStage(ViewEntry entry)
   {
      this.primaryStage.setScene(entry.getScene());
      this.primaryStage.setTitle(entry.getView().getTitle());
      return this.primaryStage;
   }
   
   void transistionStage(ViewEntry oldEntry, ViewEntry newEntry, int speed)
   {
      final Double oldHeight = oldEntry.getScene().getHeight();
      final Double oldWidth = oldEntry.getScene().getWidth();
      
      final Double newHeight = newEntry.getScene().getHeight();
      final Double newWidth = newEntry.getScene().getWidth();
      
      final int repeat = 100;
      final Double partialWidth = (newWidth - oldWidth) / repeat;
      final Double partialHeight = (newHeight - oldHeight) / repeat;
      
      System.out.println("Width before: " + oldWidth);
      System.out.println("partialWIdth: " + partialWidth);
      Timer transistionTimer = new Timer();
      transistionTimer.scheduleAtFixedRate(new TimerTask()
      {
         int i = 0;
         
         @Override
         public void run()
         {
            System.out.println("Inside timer loop: " + i + "(" + primaryStage.getWidth()
                  + partialWidth + ")");
            
            if (i < repeat)
            {
               primaryStage.setWidth(oldWidth + partialWidth);
               primaryStage.setHeight(oldHeight + partialHeight);
            }
            else
            {
               this.cancel();
            }
            i++;
         }
      }, 0, 10);
      System.out.println("Width after: " + newWidth);
      
   }
   
   void sceneTransistion(Scene oldScene, Scene newScene)
   {
      StackPane stackPane = new StackPane();
      stackPane.getChildren().add(oldScene.getRoot());
      primaryStage.setScene(new Scene(stackPane));
      Timeline fadeOut = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(oldScene
            .getRoot().opacityProperty(), 1.0)), new KeyFrame(Duration.seconds(3), new KeyValue(
            oldScene.getRoot().opacityProperty(), 0.0)));
      
      Timeline fadeIn = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(newScene
            .getRoot().opacityProperty(), 0.0)), new KeyFrame(Duration.seconds(3), new KeyValue(
            newScene.getRoot().opacityProperty(), 1.0)));
      
      fadeOut.play();
      
      stackPane.getChildren().remove(0);
      stackPane.getChildren().add(newScene.getRoot());
      
      fadeIn.play();
   }
   
   class ViewEntry
   {
      private AbstractView view;
      
      private Scene scene;
      
      public ViewEntry(AbstractView view, Scene scene)
      {
         this.view = view;
         this.scene = scene;
      }
      
      public AbstractView getView()
      {
         return view;
      }
      
      public void setView(AbstractView view)
      {
         this.view = view;
      }
      
      public Scene getScene()
      {
         return scene;
      }
      
      public void setScene(Scene scene)
      {
         this.scene = scene;
      }
   }
   
}
