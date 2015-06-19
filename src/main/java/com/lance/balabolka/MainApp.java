package com.lance.balabolka;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import com.lance.balabolka.config.AppContextConfig;
import com.lance.balabolka.controller.ScreensController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        printLogbackInternalStatus();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContextConfig.class);
        ScreensController screensController = context.getBean(ScreensController.class);
        screensController.init(stage);
        screensController.loadScreen("/fxml/Screen1.fxml");
    }

    private void printLogbackInternalStatus() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }

}
