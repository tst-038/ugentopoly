package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.logic.DiceHandler;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.GameState;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.LogbookManager;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.UIUpdater;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Game {
    private final List<Player> players;
    private final UIUpdater uiUpdater;
    private final PlayerManager playerManager;
    private final BoardManager boardManager;
    private final TurnHandler turnHandler;
    private final DiceHandler diceHandler;
    private final LogbookManager logbookManager;
    private final TileInfoPaneManager tileInfoPaneManager;
    private final GameState gameState;
    private final Bank bank;

    public Game(List<Player> players, AnchorPane rootPane, AnchorPane tileInfoPane, Group logbookRoot) {
        this.players = players;
        this.tileInfoPaneManager = new TileInfoPaneManager(tileInfoPane, this);
        this.diceHandler = new DiceHandler();
        this.uiUpdater = new UIUpdater(rootPane, this);
        this.gameState = new GameState(this);
        this.boardManager = new BoardManager(gameState.getBoard(), uiUpdater, tileInfoPaneManager);
        this.playerManager = new PlayerManager(this, uiUpdater, rootPane);
        GameOverController gameOverController = new GameOverController();
        gameOverController.setGame(this);
        this.turnHandler = new TurnHandler(this, playerManager, gameOverController);
        this.logbookManager = new LogbookManager(logbookRoot);
        this.bank = new Bank(this);

        for (Player player : players) {
            player.getPosition().setBoard(gameState.getBoard());
        }

        uiUpdater.initializeDices(diceHandler.getDiceDialog());
    }

    public void startGame(AnchorPane rootPane) {
        boardManager.initializeBoard(rootPane);
        playerManager.initializePlayers(rootPane);
        bank.initializeBalances(playerManager.getPlayers());
        turnHandler.startGame();
    }

    public void toggleLogbookVisibility() {
        logbookManager.toggleLogbookVisibility();
    }

    public TileInfoPaneManager getTileInfoPaneManager() {
        return tileInfoPaneManager;
    }

    public DiceHandler getDiceHandler() {
        return diceHandler;
    }

    public TurnHandler getTurnHandler() {
        return turnHandler;
    }

    public Bank getBank() {
        return bank;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public UIUpdater getUIUpdater() {
        return uiUpdater;
    }
}