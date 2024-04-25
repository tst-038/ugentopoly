package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.logic.DiceHandler;
import be.ugent.objprog.ugentopoly.logic.TurnHandler;
import be.ugent.objprog.ugentopoly.model.*;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.UIUpdater;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Game {
    private final List<Player> players;
    private final UIUpdater uiUpdater;
    private final PlayerManager playerManager;
    private final BoardManager boardManager;
    private final TurnHandler turnHandler;
    private final DiceHandler diceHandler;
    private final GameLogBook logBook;
    private final TileInfoPaneManager tileInfoPaneManager;
    private final GameState gameState;
    private final Bank bank;
    private final Settings settings;
    private final PropertyReader propertyReader;
    private final DeckManager deckManager;

    public Game(List<Player> players, AnchorPane rootPane, AnchorPane tileInfoPane, Node logBookRoot, Ugentopoly ugentopoly){
        this.settings = ugentopoly.getSettings();
        this.propertyReader = ugentopoly.getPropertyReader();
        this.players = players;
        this.deckManager = new DeckManager(this);
        this.tileInfoPaneManager = new TileInfoPaneManager(tileInfoPane, this);
        this.diceHandler = new DiceHandler(this);
        this.uiUpdater = new UIUpdater(rootPane, this);
        this.gameState = new GameState(this);
        this.boardManager = new BoardManager(gameState.getBoard(), uiUpdater, tileInfoPaneManager);
        this.playerManager = new PlayerManager(this, uiUpdater, rootPane);
        GameOverController gameOverController = new GameOverController(ugentopoly, this);
        this.turnHandler = new TurnHandler(this, playerManager, gameOverController);
        this.logBook = new GameLogBook(logBookRoot);
        this.bank = new Bank(this);

        for (Player player : players) {
            player.setGame(this);
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
        logBook.toggleLogbookVisibility();
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

    public GameLogBook getLogBook() {
        return logBook;
    }

    public Settings getSettings() {
        return settings;
    }

    public PropertyReader getPropertyreader() {
        return propertyReader;
    }

    public DeckManager getDeckManager() {
        return deckManager;
    }
}