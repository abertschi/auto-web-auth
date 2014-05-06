package org.autowebauth.client.fx;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.enterprise.util.AnnotationLiteral;

import org.autowebauth.client.fx.infrastrucutre.di.DiManager;
import org.autowebauth.client.fx.infrastrucutre.mvp.StartupStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class
 * 
 * @author Andrin Bertschi
 */
public class JavaFxLauncher extends Application
{
    private Logger log = LoggerFactory.getLogger(JavaFxLauncher.class);

    @Override
    public void start(Stage stage) throws Exception
    {
        // Boot Context and Dependency Injection Container
        DiManager.getInstance().startUp();

        // Fire Startup Event with primary stage.
        DiManager.getInstance().getBeanManager()
            .fireEvent(stage, new AnnotationLiteral<StartupStage>() {});
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
