package org.autowebauth.client.fx.mvcprovider;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract view representation of {@code MVP (Model-View-Presenter)} pattern.
 * This class should be used for javaFX MVP proposes. {@code AbstractView} acts
 * as a
 * passive view. It is single class of access and initializes and provides its
 * corresponding presenter by itself.<br />
 * <b>Example of usage</b>
 * 
 * <pre>
 * ConcreteView view = new ConcreteView(); // see SettingView in the example below.
 * ConcretePresenter presenter = (ConcretePresenter) view.getPresenter();
 * 
 * // business logic ...
 * presenter.loadSetting(s: Setting)
 * </pre>
 * 
 * In order to work the following conventions have to be used. </br>
 * <b>Naming conventions</b>
 * 
 * <pre>
 * conventions for subject 'setting':
 * conventional-name: setting
 * name of view: SettingView
 * name of presenter: SettingPresenter
 * name of fxml: setting.fxml
 * name of css: setting.css
 * 
 * <pre>
 * 
 * @author Andrin Bertschi
 * @since 01.12.2013
 */
public abstract class AbstractView
{
   private Logger log = LoggerFactory.getLogger(getClass());

   /**
    * Suffix for all views
    */
   public static final String DEFAULT_SUFFIX = "view";

   /**
    * JavaFX FXML Loader
    */
   protected FXMLLoader loader;

   protected Object presenter;

   /**
    * Abstract view representation in MVP pattern.
    */
   public AbstractView()
   {
      this.log.debug("Initializing View {}", getClass().getName());
      this.init(getClass(), getFXMLName());
   }

   /**
    * Initialize view
    * 
    * @param clazz
    *           concrete type of view
    * @param fxmlName
    */
   private void init(Class<? extends AbstractView> clazz, String fxmlName)
   {
      final URL location = clazz.getResource(fxmlName);
      this.loader = new FXMLLoader();
      this.loader.setLocation(location);
      this.loader.setControllerFactory(new Callback<Class<?>, Object>()
      {
         @Override
         public Object call(Class<?> bean)
         {
            return DiManager.getInstance().lookUp(bean);
         }
      });
      try
      {
         this.loader.setRoot(this.loader.load());
      }
      catch (IOException e)
      {
         this.log.error(stacktrace2String(e.getCause()));
         throw new IllegalStateException("Not able to create " + fxmlName);
      }

      // TODO: add CSS
   }

   /**
    * Get conventional name of view.
    */
   String getConventionalName()
   {
      // f.e. settingview
      String className = this.getClass().getSimpleName().toLowerCase();
      // remove view suffix
      return removeSuffix(className);
   }

   String getConventionalName(String suffix)
   {
      return getConventionalName() + suffix;
   }

   String removeSuffix(String clazz)
   {
      if (!clazz.endsWith(DEFAULT_SUFFIX))
      {
         return clazz;
      }
      int endIndex = clazz.lastIndexOf(DEFAULT_SUFFIX);
      return clazz.substring(0, endIndex);
   }

   /**
    * Get root parent of javafx scene graph.
    * 
    * @return root scene
    */
   public Parent getView()
   {
      Parent p = this.loader.getRoot();
      return p;
   }

   /**
    * Get corresponding presenter
    * 
    * @return presenter
    */
   public Object getPresenter()
   {
      if (this.presenter == null)
      {
         this.presenter = this.loader.getController();
         if (this.presenter instanceof AbstractPresenter)
         {
            ((AbstractPresenter) this.presenter).setView(this);
         }
      }
      return this.presenter;
   }

   /**
    * Get filename of javafx xml (fxml)
    * 
    * @return fxml file
    */
   public String getFXMLName()
   {
      return getConventionalName(".fxml");
   }

   /**
    * Get filename of CSS file
    * 
    * @return css file
    */
   public String getCSSName()
   {
      return getConventionalName(".css");
   }

   private String stacktrace2String(Throwable t)
   {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      t.printStackTrace(pw);
      return sw.toString();
   }

}
