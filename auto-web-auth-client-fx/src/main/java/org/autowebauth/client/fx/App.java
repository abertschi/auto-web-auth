package org.autowebauth.client.fx;

import javafx.stage.Stage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.autowebauth.client.fx.infrastrucutre.mvp.StartupStage;
import org.autowebauth.client.fx.infrastrucutre.screen.ScreenContext;
import org.autowebauth.client.fx.infrastrucutre.screen.lifecycle.CommunicationContext;
import org.autowebauth.client.fx.presentation.view.summary.SummaryView;
import org.slf4j.Logger;

@ApplicationScoped
public class App
{
    @Inject
    private Logger log;

    private Stage stage;

    public void start(@Observes @StartupStage Stage stage)
    {
        this.log.info("Stage event received");

        this.stage = stage;
        showSummaryView();
    }

    @Produces
    public Stage produceStage(InjectionPoint ip)
    {
        return this.stage;
    }

    private void showSummaryView()
    {
        SummaryView summary = new SummaryView();
        final String uri = getClass().getResource("app.css").toExternalForm();
        CommunicationContext c = new CommunicationContext(this.getClass());
        ScreenContext.current().show(summary, c);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.getScene().getStylesheets().add(uri);
        stage.show();
    }

}
