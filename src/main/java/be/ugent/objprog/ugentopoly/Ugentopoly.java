package be.ugent.objprog.ugentopoly;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.data.readers.SettingsReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.ui.UILoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

public class Ugentopoly extends Application {
    private Stage primaryStage;
    private final PropertyReader propertyReader = new PropertyReader("ugentopoly.properties");
    private final Settings settings = new SettingsReader().readSettings(propertyReader);
    private final UILoader uiLoader = new UILoader(this);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showStartWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showStartWindow() {
        uiLoader.loadStartWindow();
    }

    public void startGame(List<Player> players) {
        uiLoader.loadGameBoard(players);
    }

    public Settings getSettings() {
        return settings;
    }

    public PropertyReader getPropertyReader() {
        return propertyReader;
    }
}