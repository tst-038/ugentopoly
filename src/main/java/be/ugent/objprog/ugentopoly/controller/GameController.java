package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.logic.BoardManager;
import be.ugent.objprog.ugentopoly.logic.GameState;
import be.ugent.objprog.ugentopoly.logic.PlayerManager;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.LogbookManager;
import be.ugent.objprog.ugentopoly.ui.util.UIUpdater;
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

    private BoardManager boardManager;
    private PlayerManager playerManager;
    private LogbookManager logbookManager;
    private UIUpdater uiUpdater;

    public void initializeGame(Board board, List<Player> players) {
        uiUpdater = UIUpdater.getInstance(rootPane);
        boardManager = BoardManager.getInstance(board, rootPane, uiUpdater, tileInfoPane);
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