package be.ugent.objprog.ugentopoly.ui.manager;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.controller.GameController;
import be.ugent.objprog.ugentopoly.controller.StartController;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.reader.PropertyReader;
import be.ugent.objprog.ugentopoly.exception.ui.UIInitializationException;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.ui.animation.MoneyRainAnimation;
import be.ugent.objprog.ugentopoly.ui.animation.SceneTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UILoader {
    private final Ugentopoly ugentopoly;
    private final Stage primaryStage;
    private final PropertyReader propertyReader;
    private final Settings settings;

    public UILoader(Ugentopoly ugentopoly) {
        this.ugentopoly = ugentopoly;
        this.primaryStage = ugentopoly.getPrimaryStage();
        primaryStage.setMaxWidth(845);
        this.propertyReader = ugentopoly.getPropertyReader();
        this.settings = ugentopoly.getSettings();
    }

    private void transitionToScene(Scene newScene) {
        SceneTransition sceneTransition = new SceneTransition(primaryStage);
        sceneTransition.transitionToScene(newScene);
    }

    public void loadGameBoard(List<Player> players) {
        try {
            FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/game_board.fxml"));
            Parent root = loader.load();

            GameController gameController = loader.getController();
            gameController.initializeGame(players, ugentopoly);

            Scene scene = new Scene(root, 845, 845);
            transitionToScene(scene);
        } catch (IOException e) {
            throw new UIInitializationException("Failed to load the gameManager board", e);
        }
    }

    public void loadStartWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/start_window.fxml"));
            StartController controller = new StartController(ugentopoly, propertyReader, settings);
            loader.setController(controller);
            Parent root = loader.load();
            controller.updateFields();
            if (root instanceof Pane rootPane) {
                MoneyRainAnimation moneyAnimation = new MoneyRainAnimation(-1);
                moneyAnimation.play(rootPane, 20);
            }
            Scene scene = new Scene(root, 845, 845);
            primaryStage.setTitle("Ugentopoly");
            primaryStage.setResizable(false);
            setIcon();
            if(primaryStage.getScene() != null) {
                transitionToScene(scene);
            } else {
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        } catch (IOException e) {
            throw new UIInitializationException("Failed to load the start window", e);
        }
    }

    private void setIcon() {
        try (InputStream iconStream = ResourceLoader.loadResource("assets/logo.png")) {
            Image icon = new Image(iconStream);
            primaryStage.getIcons().add(icon);
        } catch (IOException e) {
            throw new UIInitializationException("Failed to set the icon", e);
        }
    }
}