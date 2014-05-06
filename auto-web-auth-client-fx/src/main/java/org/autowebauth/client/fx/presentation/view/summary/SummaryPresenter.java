package org.autowebauth.client.fx.presentation.view.summary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.autowebauth.client.fx.business.network.annotation.WlanConnected;
import org.autowebauth.client.fx.business.registration.boundary.RegistrationService;
import org.autowebauth.client.fx.business.registration.entity.Registration;
import org.autowebauth.client.fx.infrastrucutre.screen.ScreenContext;
import org.autowebauth.client.fx.infrastrucutre.screen.lifecycle.CommunicationContext;
import org.autowebauth.client.fx.infrastrucutre.screen.lifecycle.OnViewResume;
import org.autowebauth.client.fx.presentation.view.createprofile.CreateprofileView;
import org.autowebauth.client.fx.presentation.view.modifyprofile.ModifyprofileView;
import org.autowebauth.client.fx.presentation.view.settings.SettingsView;
import org.autowebauth.core.api.network.provider.conn.ConnectionEvent;
import org.slf4j.Logger;

public class SummaryPresenter implements Initializable
{

    @Inject
    private Logger log;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @Inject
    RegistrationService registrationService;

    @Inject
    Stage primaryStage;

    @FXML
    private ListView<Registration> registrationList;

    @FXML
    private Button btModifyRegistration;

    @PostConstruct
    public void init()
    {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loadRegistrations();
        this.registrationList.selectionModelProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (registrationList.getItems().size() == 0)
                {
                    btModifyRegistration.setDisable(true);
                }
                else
                {
                    btModifyRegistration.setDisable(false);
                }
            });
    }

    private void loadRegistrations()
    {
        this.registrationList.setItems(null);
        this.registrationList.setItems(FXCollections
            .observableList(this.registrationService.getAll()));
    }

    @OnViewResume
    void onResume(CommunicationContext c)
    {
        loadRegistrations();
    }

    /**
     * Get network activities from os layer
     */
    void onNetworkChanges(@Observes @WlanConnected ConnectionEvent event)
    {
    }

    /**
     * Start automated network login attends
     */
    @FXML
    void onStartTrackingAction(ActionEvent event)
    {

    }

    /**
     * Stop automated network login attends
     */
    @FXML
    void onStopTrackingAction(ActionEvent event)
    {

    }

    /**
     * Jump to 'createProfile' Screen
     */
    @FXML
    void onNewProfileAction(ActionEvent event)
    {
        CreateprofileView createProfile = new CreateprofileView();
        ScreenContext.current().show(createProfile);
    }

    /**
     * Jump to 'modifyProfile' Screen
     */
    @FXML
    void onModifyProfileAction(ActionEvent event)
    {
        ModifyprofileView modiyProfile = new ModifyprofileView();
        ScreenContext.current().show(modiyProfile);
    }
}
