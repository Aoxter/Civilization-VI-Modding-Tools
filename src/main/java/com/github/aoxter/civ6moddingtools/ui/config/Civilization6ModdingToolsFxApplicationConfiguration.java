package com.github.aoxter.civ6moddingtools.ui.config;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

@Configuration
public class Civilization6ModdingToolsFxApplicationConfiguration {
    private final FxmlLoader fxmlLoader;
    private final String applicationTitle;

    public Civilization6ModdingToolsFxApplicationConfiguration(FxmlLoader fxmlLoader, @Value("${application.title}") String applicationTitle) {
        this.fxmlLoader = fxmlLoader;
        this.applicationTitle = applicationTitle;
    }

    @Bean
    @Lazy
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(stage, fxmlLoader, applicationTitle);
    }
}
