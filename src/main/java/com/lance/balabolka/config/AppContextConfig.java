package com.lance.balabolka.config;

import com.lance.balabolka.controller.Screen1Controller;
import com.lance.balabolka.controller.Screen2Controller;
import com.lance.balabolka.controller.Screen3Controller;
import com.lance.balabolka.controller.ScreensController;
import com.lance.balabolka.scope.ScreenScope;
import com.lance.balabolka.service.BalabolkaService;
import com.lance.balabolka.service.BalabolkaServiceImpl;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppContextConfig extends AbstractSpringConfiguration {

    @Bean
    public CustomScopeConfigurer getCustomScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        final Map<String, Object> scopeMap = new HashMap<>();
        scopeMap.put("screen", screenScope());
        configurer.setScopes(scopeMap);
        return configurer;
    }

    @Bean
    public ScreenScope screenScope() {
        return new ScreenScope();
    }

    @Bean
    public Screen1Controller screen1Controller() {
        Screen1Controller screen1Controller = new Screen1Controller();
        screen1Controller.setScreensController(getBean(ScreensController.class));
        return screen1Controller;
    }

    @Bean
    public Screen2Controller screen2Controller() {
        Screen2Controller screen2Controller = new Screen2Controller();
        screen2Controller.setScreensController(getBean(ScreensController.class));
        return screen2Controller;
    }

    @Bean
    public Screen3Controller screen3Controller() {
        Screen3Controller screen3Controller = new Screen3Controller();
        screen3Controller.setScreensController(getBean(ScreensController.class));
        return screen3Controller;
    }

    @Bean
    public ScreensController screensController() {
        ScreensController screensController = new ScreensController();
        screensController.setScreenScope(getBean(ScreenScope.class));
        return screensController;
    }

    @Bean
    public BalabolkaService balabolkaService() {
        return new BalabolkaServiceImpl();
    }

}
