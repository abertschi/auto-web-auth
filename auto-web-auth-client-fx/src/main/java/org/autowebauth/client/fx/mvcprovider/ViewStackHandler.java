package org.autowebauth.client.fx.mvcprovider;

import java.util.Stack;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

@ApplicationScoped
@Deprecated
/**
 * Use {@link ScreenContext} instead.
 *
 */
public class ViewStackHandler
{
   @Inject
   private Stage primaryStage;
   
   private final Stack<ViewEntry> viewStack;
   
   private ViewEntry activeView;
   
   ViewStackHandler()
   {
      this.viewStack = new Stack<ViewEntry>();
   }
   
   public static ViewStackHandler getInstance()
   {
      return (ViewStackHandler) DiManager.getInstance().lookup(ViewStackHandler.class);
   }
   
   public void enqueue(Stage stage, Scene scene, AbstractView view)
   {
      if (this.activeView != null) {
         suspendView(this.activeView);
      }
   }
   
   private void applyScene(AbstractView view)
   {
//      this.primaryStage.setScene(scene);
//      this.primaryS tage.show();
   }

   void suspendView(ViewEntry viewEntry)
   {
      this.viewStack.add(viewEntry);
   }
   
   void suspendView(AbstractView view) {
      this.viewStack.add(new ViewEntry(view, null));
   }

   /**
    * Release currently shown view.
    */
   public void release(Stage stage)
   {
      CDI.current().destroy(this.activeView);
      
   }
   
//   private void applyNewScene(SceneConfiguration config)
//   {
//      this.activeView = new AbstractMap.SimpleEntry<AbstractView, Scene>(config.getView(),
//            config.getScene());
//      getStage().setScene(config.getScene());
//   }
   
//   private void pauseCurrentlyShownScene()
//   {
//      if (this.activeView != null)
//      {
//         // Store current view in stack
//         this.viewStack.push(this.activeView);
//         
//         // Set view in state paused
//         for (Method m : this.activeView.getKey().getClass().getMethods())
//         {
//            if (m.isAnnotationPresent(ViewPaused.class))
//            {
//               try
//               {
//                  m.invoke(this.activeView.getKey());
//                  break;
//               }
//               catch (IllegalAccessException e)
//               {
//                  e.printStackTrace();
//               }
//               catch (java.lang.IllegalArgumentException e)
//               {
//                  e.printStackTrace();
//               }
//               catch (InvocationTargetException e)
//               {
//                  e.printStackTrace();
//               }
//            }
//         }
//      }
//   }
   
   @Deprecated
   private class ViewEntry
   {
      private Stage stage;
      
      private AbstractView view;
      
      public ViewEntry(AbstractView view, Stage stage) {
         this.view = view;
         this.stage = stage;
      }
      
      public Stage getStage()
      {
         return stage;
      }
      
      public void setStage(Stage stage)
      {
         this.stage = stage;
      }
      
      public AbstractView getView()
      {
         return view;
      }
      
      public void setView(AbstractView view)
      {
         this.view = view;
      }
   }
}
