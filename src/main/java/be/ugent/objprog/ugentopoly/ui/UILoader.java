package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.controller.GameController;
import be.ugent.objprog.ugentopoly.controller.StartController;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.exceptions.ui.UIInitializationException;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.ui.animations.MoneyAnimation;
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
        this.propertyReader = ugentopoly.getPropertyReader();
        this.settings = ugentopoly.getSettings();
    }

    public void loadGameBoard(List<Player> players) {
        try {
            FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("/view/ui/game_board.fxml"));
            Parent root = loader.load();

            GameController gameController = loader.getController();
            gameController.initializeGame(players, ugentopoly);

            Scene scene = new Scene(root, 845, 845);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new UIInitializationException("Failed to load the game board", e);
        }
    }

    public void loadStartWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("/view/ui/start_window.fxml"));
            StartController controller = new StartController(ugentopoly, propertyReader, settings);
            loader.setController(controller);
            Parent root = loader.load();
            controller.updateFields();
            if (root instanceof Pane rootPane) {
                MoneyAnimation moneyAnimation = new MoneyAnimation(-1);
                moneyAnimation.play(rootPane, 20);
            }
            Scene scene = new Scene(root, 845, 845);
            primaryStage.setTitle("Ugentopoly");
            primaryStage.setResizable(false);
            setIcon();
            primaryStage.setScene(scene);
            primaryStage.show();
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