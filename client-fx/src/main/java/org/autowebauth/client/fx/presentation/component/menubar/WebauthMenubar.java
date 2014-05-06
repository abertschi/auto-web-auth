package org.autowebauth.client.fx.presentation.component.menubar;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;

/**
 * Global Webauth Menubar component.
 * 
 * @author abertschi
 *
 */
public class WebauthMenubar extends MenuBar
{
   public WebauthMenubar() {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("webauthmenubar.fxml"));
      fxmlLoader.setRoot(this);
      fxmlLoader.setController(this);
      try
      {
         fxmlLoader.load();
      }
      catch (IOException e)
      {
         throw new RuntimeException(e);
      }
   }
   
   public static void main(String[] args)
   {
      new WebauthMenubar();
   }
}
