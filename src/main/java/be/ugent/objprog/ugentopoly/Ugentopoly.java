package be.ugent.objprog.ugentopoly;

import be.ugent.objprog.ugentopoly.controller.GameController;
import be.ugent.objprog.ugentopoly.model.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Ugentopoly extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Ugentopoly.class.getResource("view/game_board.fxml"));
        Parent root = loader.load();

        GameController gameController = loader.getController();
        Board board = new Board();
        board.init();
        gameController.setBoard(board);

        Scene scene = new Scene(root, 845, 845);
        primaryStage.setTitle("Ugentopoly");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}