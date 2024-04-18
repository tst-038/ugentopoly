package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.LogbookManager;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
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
        UIUpdater uiUpdater = UIUpdater.getInstance(rootPane);
        TileInfoPaneManager tileInfoPaneManager = TileInfoPaneManager.getInstance(tileInfoPane);
        BoardManager boardManager = BoardManager.getInstance(uiUpdater, tileInfoPaneManager);
        PlayerManager playerManager = PlayerManager.getInstance(players, uiUpdater, rootPane);
        LogbookManager logbookManager = LogbookManager.getInstance(logbookRoot);

        uiUpdater.initializeDices();
        boardManager.initializeBoard(rootPane);
        playerManager.initializePlayers(rootPane);
        Bank.getInstance().initializeBalances(players);

        TurnHandler.getInstance(playerManager).startGame();
    }

    @FXML
    private void handleLogbookButtonClicked() {
        LogbookManager.getInstance(logbookRoot).toggleLogbookVisibility();
    }
}