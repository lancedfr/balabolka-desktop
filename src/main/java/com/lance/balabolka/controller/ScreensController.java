package com.lance.balabolka.controller;

import com.lance.balabolka.scope.ScreenScope;
import com.lance.balabolka.scope.ScreenScoped;
import com.lance.balabolka.util.FXMLUtils;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScreensController implements ApplicationContextAware {

    private final Map<String, BaseScreenController> screens = Collections.synchronizedMap(new HashMap<>());
    private ScreenScope screenScope;
    private ApplicationContext applicationContext;
    private Stage stage;
    private String currentScreenId;

    public void init(Stage stage) {
        this.stage = stage;
        Group root = new Group();
        this.stage.setScene(new Scene(root));

    }

    public void loadScreen(String fxml) {
        BaseScreenController oldScreenController = this.getCurrentController();
        try {

            Class controllerClass = FXMLUtils.getControllerClass(fxml);
            final BaseScreenController fxmlController = (BaseScreenController) applicationContext.getBean(controllerClass);

            if (this.screens.get(fxmlController.getScreenId()) == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
                loader.setControllerFactory(aClass -> fxmlController);
                Parent root = loader.load();
                fxmlController.setRoot(root);
                this.screens.put(fxmlController.getScreenId(), fxmlController);
            }

            this.currentScreenId = fxmlController.getScreenId();
            swapScreen(getCurrentController().getRoot());
            if (oldScreenController != null) {
                if (oldScreenController.getClass().isAnnotationPresent(ScreenScoped.class)) {
                    this.screens.remove(oldScreenController.getScreenId());
                    this.screenScope.remove(oldScreenController.getScreenId());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void swapScreen(final Parent root) {
        final Group rootGroup = getScreenRoot();
        final DoubleProperty opacity = rootGroup.opacityProperty();
        if (!isScreenEmpty()) {
            Timeline fade = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                    new KeyFrame(new Duration(250), t -> {
                        rootGroup.getChildren().remove(0);

                        rootGroup.getChildren().add(0, root);
                        Timeline fadeIn = new Timeline(
                                new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                new KeyFrame(new Duration(350), new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                    }, new KeyValue(opacity, 0.0)));
            fade.play();
        } else {
            opacity.set(0.0);
            rootGroup.getChildren().add(0, root);
            Timeline fadeIn = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                    new KeyFrame(new Duration(350), new KeyValue(opacity, 1.0)));
            fadeIn.play();
        }

        if (!this.stage.isShowing()) {
            this.stage.show();
        }
    }

    private Group getScreenRoot() {
        return (Group) this.stage.getScene().getRoot();
    }

    private boolean isScreenEmpty() {
        return getScreenRoot().getChildren().isEmpty();
    }

    public BaseScreenController getCurrentController() {
        return screens.get(getCurrentScreenId());
    }

    public String getCurrentScreenId() {
        return currentScreenId;
    }

    public void setScreenScope(ScreenScope screenScope) {
        this.screenScope = screenScope;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
