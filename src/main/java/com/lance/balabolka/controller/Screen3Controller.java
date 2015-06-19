package com.lance.balabolka.controller;

import com.lance.balabolka.scope.ScreenScoped;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

@ScreenScoped
public class Screen3Controller extends BaseScreenController implements Initializable {

    private final StringProperty textA = new SimpleStringProperty("");
    @FXML
    private TextField textField;
    @FXML
    private Label labelA;

    @FXML
    private void handleScreen1ButtonAction(ActionEvent event) {
        this.screensController.loadScreen("/fxml/Screen1.fxml");
    }

    @FXML
    private void handleScreen2ButtonAction(ActionEvent event) {
        this.screensController.loadScreen("/fxml/Screen2.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.textProperty().bindBidirectional(this.textA);
        labelA.textProperty().bindBidirectional(this.textA);
    }

    public void clearLabel() {
        this.labelA.setText("...");
    }

    @Override
    public void setScreensController(ScreensController screensController) {
        super.screensController = screensController;
    }

}
