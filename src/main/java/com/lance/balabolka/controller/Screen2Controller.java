package com.lance.balabolka.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Screen2Controller extends BaseScreenController implements Initializable {

    @FXML
    private void handleScreen1ButtonAction(ActionEvent event) {
        this.screensController.loadScreen("/fxml/Screen1.fxml");
    }

    @FXML
    private void handleScreen3ButtonAction(ActionEvent event) {
        this.screensController.loadScreen("/fxml/Screen3.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.screenName.setText("Screen2");
    }

    @Override
    public void setScreensController(ScreensController screensController) {
        super.screensController = screensController;
    }

}
