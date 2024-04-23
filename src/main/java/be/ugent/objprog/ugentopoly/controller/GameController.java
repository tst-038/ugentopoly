package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.model.Player;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class GameController {
    @FXML
    private Group logbookRoot;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane tileInfoPane;

    private Game game;

    public void initializeGame(List<Player> players) {
        game = new Game(players, rootPane, tileInfoPane, logbookRoot);
        game.startGame(rootPane);
    }

    @FXML
    private void handleLogbookButtonClicked() {
        game.toggleLogbookVisibility();
    }
}