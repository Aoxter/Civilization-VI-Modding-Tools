package com.github.aoxter.civ6moddingtools;

import com.github.aoxter.civ6moddingtools.ui.config.FxmlView;
import com.github.aoxter.civ6moddingtools.ui.config.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class Civilization6ModdingToolsFxApplication extends Application {
    private static Stage stage;

    private ConfigurableApplicationContext applicationContext;
    private StageManager stageManager;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(Civilization6ModdingToolsApplication.class).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        stage.close();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stageManager = applicationContext.getBean(StageManager.class, primaryStage);
        showWelcomeScene();
    }

    private void showWelcomeScene() {
        stageManager.switchScene(FxmlView.WELCOME);
    }
}
