package org.autowebauth.client.fx.infrastrucutre.screen;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import org.autowebauth.client.fx.infrastrucutre.di.DiManager;
import org.autowebauth.client.fx.infrastrucutre.mvp.AbstractView;
import org.autowebauth.client.fx.infrastrucutre.screen.lifecycle.CommunicationContext;
import org.autowebauth.client.fx.infrastrucutre.screen.lifecycle.OnViewCreate;
import org.autowebauth.client.fx.infrastrucutre.screen.lifecycle.OnViewPause;
import org.autowebauth.client.fx.infrastrucutre.screen.lifecycle.OnViewRelease;
import org.autowebauth.client.fx.infrastrucutre.screen.lifecycle.ViewState;
import org.slf4j.Logger;

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
 * <b>Lifecycle</b>
 * View gets created: OnViewCreated method is called
 * View gets released: OnViewReleased method is called
 * View gets paused: OnViewPaused method is called
 * View gets resumed: OnViewResumed method is called
 * 
 * @author Andrin Bertschi
 *
 */
@ApplicationScoped
public class ScreenContext
{
   
   @Inject
   Logger log;
   
   @Inject
   private Stage primaryStage;
   
   /**
    * CDI managed singelton for static access
    */
   private static ScreenContext INSTANCE;
   
   /**
    * Stack with shown views.
    */
   private Stack<ViewEntry> viewStack;
   
   // --------------------------------------
   // Constructors
   // --------------------------------------
   
   ScreenContext() // for CDI only!
   {
      this.viewStack = new Stack<ViewEntry>();
   }
   
   /**
    * Get instance of {@link ScreenContext}.
    */
   public static ScreenContext current()
   {
      if (INSTANCE == null)
      {
         INSTANCE = (ScreenContext) DiManager.getInstance().lookup(ScreenContext.class);
      }
      return INSTANCE;
   }
   
   // --------------------------------------
   // business methods
   // --------------------------------------
   
   public Stage show(AbstractView view)
   {
      return show(view, null);
   }
   
   /**
    * Show given view on top of screen.
    * {@link ScreenContext#release(Class)}
    * @return Used stage
    */
   public Stage show(AbstractView view, CommunicationContext communication)
   {
      // Pause active view
      if (this.viewStack.size() > 0)
      {
         ViewEntry activeView = this.viewStack.peek();
         pauseView(activeView, communication);
         activeView.setState(ViewState.PAUSED);
      }
      
      // Show new view
      ViewEntry newView = createViewEntry(view);
      showView(newView, communication);
      this.viewStack.push(newView);
      newView.setState(ViewState.CREATED);
      
      return primaryStage;
   }
   
   public <T extends AbstractView> AbstractView release(Class<T> viewType)
   {
      return release(viewType, null);
   }
   
   /**
    * Release a view from top of screen.
    * @return released view
    */
   public <T extends AbstractView> AbstractView release(Class<T> viewType,
         CommunicationContext communication)
   {
      // Release active view
      ViewEntry viewToRelease = this.viewStack.pop();
      releaseView(viewToRelease, communication);
      viewToRelease.setState(ViewState.RELEASED);
      
      ViewEntry viewToResume = null;
      // Resume paused view
      if (this.viewStack.size() > 0)
      {
         viewToResume = this.viewStack.peek();
         resumeView(viewToResume, communication);
         viewToRelease.setState(ViewState.RESUMED);
      }
      
      return viewToResume != null ? viewToResume.getView() : null;
   }
   
   public <T extends AbstractView> Stage load(Class<T> view, CommunicationContext context)
   {
      throw new UnsupportedOperationException("not yet implemented");
      // return load(view, context);
   }
   
   /**
    * Create and load new view.
    * @return created view
    */
   public <T extends AbstractView> Stage load(Class<T> view)
   {
      return load(view, null);
   }
   
   // --------------------------------------
   // private section
   // --------------------------------------
   
   private void releaseView(ViewEntry viewToRelease, CommunicationContext communication)
   {
      this.log.info("Showing view {}", viewToRelease.getView().getClass().getName());
      
      callAnnotatedMethod(viewToRelease.getView().getPresenter(), OnViewRelease.class,
            communication);
   }
   
   private void showView(ViewEntry newViewEntry, CommunicationContext communication)
   {
      this.log.info("Showing view {}", newViewEntry.getView().getClass().getName());
      
      // Make new view visible
      applyViewOnStage(newViewEntry);
      
      callAnnotatedMethod(newViewEntry.getView().getPresenter(), OnViewCreate.class, communication);
   }
   
   private void pauseView(ViewEntry activeView, CommunicationContext communication)
   {
      this.log.info("Showing view {}", activeView.getView().getClass().getName());
      
      callAnnotatedMethod(activeView.getView().getPresenter(), OnViewPause.class, communication);
   }
   
   private void resumeView(ViewEntry viewToResume, CommunicationContext communication)
   {
      this.log.info("Showing view {}", viewToResume.getView().getClass().getName());
      
      applyViewOnStage(viewToResume);
      
      callAnnotatedMethod(viewToResume.getView().getPresenter(), OnViewRelease.class, communication);
   }
   
   private void applyViewOnStage(ViewEntry viewEntry)
   {
      this.primaryStage.setScene(viewEntry.getScene());
      this.primaryStage.setTitle(viewEntry.getView().getTitle());
   }
   
   private ViewEntry createViewEntry(AbstractView view)
   {
      Scene scene = new Scene(view.getRoot());
      return new ViewEntry(view, scene);
   }
   
   private <T extends Annotation> void callAnnotatedMethod(Object object,
         Class<T> methodIdentification)
   {
      callAnnotatedMethod(object, methodIdentification, null);
   }
   
   /**
    * Call a method on an object.
    * 
    * @param object object on which a method should be called.
    * @param methodIdentification Annotation which identifies method to call.
    * @param validArgument Argument, which should be passed to method, if possible
    */
   private <T extends Annotation> void callAnnotatedMethod(Object object,
         Class<T> methodIdentification, Object validArgument)
   {
      try
      {
         // Find method annotated with given Annotation
         for (Method m : object.getClass().getDeclaredMethods())
         {
            System.out.println(m.getName());
            if (m.isAnnotationPresent(methodIdentification))
            {
               if (!m.isAccessible())
               {
                  m.setAccessible(true);
               }
               int parameterIndex = 0;
               if (validArgument != null)
               {
                  parameterIndex = getParameterIndex(m, validArgument.getClass());
               }
               Object[] methodParams = fillObjectArrayWithParameter(m.getParameterCount(),
                     validArgument, parameterIndex);
               m.invoke(object, methodParams);
            }
         }
      }
      catch (IllegalAccessException e)
      {
         e.printStackTrace();
      }
      catch (IllegalArgumentException e)
      {
         e.printStackTrace();
      }
      catch (InvocationTargetException e)
      {
         e.printStackTrace();
      }
   }
   
   /**
    * Initialize an array with null values exept for given {@code parameter} on index {@code parameterIndex}.
    * 
    * @param arraySize size of array
    * @param parameter Object to fill on given index
    * @param parameterIndex index for given object
    * @return see discription.
    */
   private Object[] fillObjectArrayWithParameter(int arraySize, Object parameter, int parameterIndex)
   {
      final int parameterCount = arraySize;
      final Object[] parms = new Object[parameterCount];
      for (int i = 0; i < parameterCount; i++)
      {
         if (i == parameterIndex)
         {
            parms[i] = parameter;
         }
         else {
            parms[i] = null;
         }
      }
      return parms;
   }
   
   /**
    * Get first parameterIndex on which given Class {@code parameter} is declared.
    * @param method Method to use
    * @param parameter Class which index is requested in parameterlist.
    * @return index
    */
   private int getParameterIndex(Method method, Class<?> parameter)
   {
      final int notFound = -1;
      int communicationContextIndex = notFound;
      int index = 0;
      for (Class<?> clazz : method.getParameterTypes())
      {
         if (clazz == parameter)
         {
            communicationContextIndex = index;
         }
         index++;
      }
      return communicationContextIndex;
   }
   
   // --------------------------------------
   // draft, animation stuff
   // --------------------------------------
   
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
   
   // --------------------------------------
   // inner class
   // --------------------------------------
   
   /**
    * ViewEntry for view stack.
    */
   class ViewEntry
   {
      private AbstractView view;
      
      private Scene scene;
      
      private ViewState state;
      
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

      public ViewState getState()
      {
         return state;
      }

      public void setState(ViewState state)
      {
         this.state = state;
      }
   }
   
}
