package org.autowebauth.client.fx.presentation.modifyprofile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.autowebauth.client.fx.mvcprovider.screen.ScreenContext;

public class ModifyprofilePresenter
{
   
   @Inject
   private Stage stage;
   
   /**
    * Action Button was pressed to save modifications
    */
   @FXML
   void onProfileSaved(ActionEvent event) {
      ScreenContext.current().release(ModifyprofileView.class);
   }
   
   /**
    * Action Button was pressed to remove profile.
    */
   @FXML
   void onProfileRemoved(ActionEvent event) {
      ScreenContext.current().release(ModifyprofileView.class);
   }

   @PostConstruct
   public void cdiInit() {
      this.stage.getScene().getWindow().setOnCloseRequest(new OnProfileAbortHandler());
   }
   
   /**
    * View was abort.
    */
   class OnProfileAbortHandler implements EventHandler<javafx.stage.WindowEvent> {

      @Override
      public void handle(javafx.stage.WindowEvent event)
      {
         // make sure, not to affect other views by this listener
         // after current view was released
         stage.getScene().getWindow().setOnCloseRequest(null);
         
         ScreenContext.current().release(ModifyprofileView.class);
         event.consume();
         
      }
      
   }
}
