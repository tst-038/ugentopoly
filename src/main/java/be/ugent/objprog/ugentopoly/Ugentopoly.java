package be.ugent.objprog.ugentopoly;

import be.ugent.objprog.ugentopoly.controller.GameController;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.exceptions.ui.UIInitializationException;
import be.ugent.objprog.ugentopoly.model.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class Ugentopoly extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //TODO
    // Overpoort info panel
    // Kans info panel
    // Utility info panel
    // remove the rent grid and go back to one price
    // refractor info panel classes
    // add animations

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/game_board.fxml"));
            Parent root = loader.load();

            GameController gameController = loader.getController();
            Board board = new Board();
            board.init();
            gameController.initializeBoard(board);

            Scene scene = new Scene(root, 845, 845);
            primaryStage.setTitle("Ugentopoly");
            primaryStage.setResizable(false);
            setIcon(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            throw new UIInitializationException("Failed to initialize the UI", e);
        }
    }

    private void setIcon(Stage stage) {
        Path iconPath = Path.of("assets/logo.png");
        try (InputStream iconStream = ResourceLoader.loadResource(iconPath.toString())) {
            Image icon = new Image(iconStream);
            stage.getIcons().add(icon);
        } catch (IOException e) {
            throw new UIInitializationException("Failed to set the icon", e);
        }
    }
}