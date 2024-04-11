package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.logic.BoardManager;
import be.ugent.objprog.ugentopoly.logic.GameState;
import be.ugent.objprog.ugentopoly.logic.PlayerManager;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.LogbookManager;
import be.ugent.objprog.ugentopoly.ui.UIUpdater;
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

    public void initializeGame(List<Player> players) {
        GameState.getInstance(rootPane);
        UIUpdater.getInstance(rootPane);
        BoardManager.getInstance(rootPane, UIUpdater.getInstance(rootPane), tileInfoPane);
        PlayerManager.getInstance(players, rootPane, UIUpdater.getInstance(rootPane));
        LogbookManager.getInstance(logbookRoot);

        BoardManager.getInstance(rootPane, UIUpdater.getInstance(rootPane), tileInfoPane).initializeBoard();
        PlayerManager.getInstance(players, rootPane, UIUpdater.getInstance(rootPane)).initializePlayers();
        Bank.getInstance().initializeBalances(players);

        TurnHandler.getInstance(PlayerManager.getInstance(players, rootPane, UIUpdater.getInstance(rootPane))).startGame();
    }

    @FXML
    private void handleLogbookButtonClicked() {
        LogbookManager.getInstance(logbookRoot).toggleLogbookVisibility();
    }
}