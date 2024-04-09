package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.Player;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private static GameState instance;
    private final Board board;
    private final Pane rootpane;
    private List<GameOverListener> gameOverListeners = new ArrayList<>();

    private GameState(Pane rootpane) {
        this.board =  new Board();
        this.board.init();
        this.rootpane = rootpane;
    }

    public static GameState getInstance(Pane rootpane) {
        if (instance == null) {
            instance = new GameState(rootpane);
        }
        return instance;
    }

    public static GameState getInstance() {
        return instance;
    }

    public void addGameOverListener(GameOverListener listener) {
        gameOverListeners.add(listener);
    }

    public Pane getRootpane() {
        return rootpane;
    }

    public Board getBoard() {
        return board;
    }

    public void notifyGameOverListeners(Player player) {
        for (GameOverListener listener : gameOverListeners) {
            listener.onGameOver(player);
        }
    }
}