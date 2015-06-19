package com.lance.balabolka.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Screen1Controller extends BaseScreenController implements Initializable {

    @FXML
    private TextField textField;

    @FXML
    private void handleScreen2ButtonAction(ActionEvent event) throws IOException {
        //this.text = textField.getText();
        String file = Screen1Controller.class.getResource("/balabolka_console/balabolka_console.exe").getFile();
        ProcessBuilder processBuilder =  new ProcessBuilder(file,"-n Microsoft Hazel Desktop","-c", "-d d:\\rex\\rules.rex", "-d d:\\dic\\rules.dic");
        processBuilder.start();
        this.screensController.loadScreen("/fxml/Screen2.fxml");
    }

    @FXML
    private void handleScreen3ButtonAction(ActionEvent event) {
        //this.text = textField.getText();
        this.screensController.loadScreen("/fxml/Screen3.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.screenName.setText("Screen1");
    }

    @Override
    public void setScreensController(ScreensController screensController) {
        super.screensController = screensController;
    }
}
