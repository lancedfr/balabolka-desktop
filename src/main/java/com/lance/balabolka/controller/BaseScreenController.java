package com.lance.balabolka.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.springframework.beans.factory.BeanNameAware;

public abstract class BaseScreenController implements Initializable, BeanNameAware {

    private String screenId;
    protected ScreensController screensController;
    private Parent root;
    @FXML
    protected Label screenName;

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    @Override
    public void setBeanName(String name) {
        this.screenId = name;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public abstract void setScreensController(ScreensController screensController);

    public ScreensController getScreensController() {
        if (screensController == null) {

        }
        return screensController;
    }
}
