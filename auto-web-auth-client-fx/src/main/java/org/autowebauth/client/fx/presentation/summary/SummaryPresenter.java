package org.autowebauth.client.fx.presentation.summary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javax.inject.Inject;

import org.autowebauth.client.fx.business.profile.boundary.ProfileService;

public class SummaryPresenter implements Initializable {

	@FXML
	Button startButton;

	@FXML
	Button stopButton;
	
	@Inject
	ProfileService profileService;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
