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
import javafx.scene.layout.Pane;

import java.util.List;

public class GameController {
    @FXML
    private Group logbookRoot;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane tileInfoPane;

    //TODO maybe remove these unneeded variables as they are all singletons
    private BoardManager boardManager;
    private PlayerManager playerManager;
    private LogbookManager logbookManager;
    private UIUpdater uiUpdater;
    private GameState gameState;

    // TODO Refractor to use getrootpane and gettileinfopane...
    public void initializeGame(List<Player> players) {
        gameState = GameState.getInstance(rootPane);
        uiUpdater = UIUpdater.getInstance(rootPane);
        boardManager = BoardManager.getInstance(rootPane, uiUpdater, tileInfoPane);
        playerManager = PlayerManager.getInstance(players, rootPane, uiUpdater);
        logbookManager = LogbookManager.getInstance(logbookRoot);

        boardManager.initializeBoard();
        playerManager.initializePlayers();
        Bank.getInstance().initializeBalances(players);

        TurnHandler.getInstance(playerManager).startGame();
    }

    @FXML
    private void handleLogbookButtonClicked() {
        logbookManager.toggleLogbookVisibility();
    }
}