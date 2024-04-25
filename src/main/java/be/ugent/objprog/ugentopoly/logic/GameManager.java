package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.ui.manager.BoardManager;
import be.ugent.objprog.ugentopoly.controller.GameOverController;
import be.ugent.objprog.ugentopoly.ui.manager.PlayerManager;
import be.ugent.objprog.ugentopoly.data.reader.PropertyReader;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.logic.handler.DiceHandler;
import be.ugent.objprog.ugentopoly.model.*;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.ui.manager.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.manager.UIUpdater;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class GameManager {
    private final List<Player> players;
    private final UIUpdater uiUpdater;
    private final PlayerManager playerManager;
    private final BoardManager boardManager;
    private final TurnManager turnManager;
    private final DiceHandler diceHandler;
    private final GameLogBook logBook;
    private final TileInfoPaneManager tileInfoPaneManager;
    private final GameState gameState;
    private final Bank bank;
    private final Settings settings;
    private final PropertyReader propertyReader;
    private final CardDeckManager cardDeckManager;

    public GameManager(List<Player> players, AnchorPane rootPane, AnchorPane tileInfoPane, Node logBookRoot, Ugentopoly ugentopoly){
        this.settings = ugentopoly.getSettings();
        this.propertyReader = ugentopoly.getPropertyReader();
        this.players = players;
        this.cardDeckManager = new CardDeckManager(this);
        this.tileInfoPaneManager = new TileInfoPaneManager(tileInfoPane, this);
        this.diceHandler = new DiceHandler(this);
        this.uiUpdater = new UIUpdater(rootPane, this);
        this.gameState = new GameState(this);
        this.boardManager = new BoardManager(gameState.getBoard(), uiUpdater, tileInfoPaneManager);
        this.playerManager = new PlayerManager(this, uiUpdater, rootPane);
        GameOverController gameOverController = new GameOverController(ugentopoly, this);
        this.turnManager = new TurnManager(this, gameOverController);
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
        turnManager.initialize();
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

    public TurnManager getTurnHandler() {
        return turnManager;
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

    public CardDeckManager getDeckManager() {
        return cardDeckManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}