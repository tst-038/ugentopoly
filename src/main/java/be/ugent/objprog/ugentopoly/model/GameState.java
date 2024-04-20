package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.logic.GameOverListener;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private static GameState instance;
    private final Board board;
    private final List<GameOverListener> gameOverListeners = new ArrayList<>();

    private GameState() {
        this.board = new Board();
        this.board.init();
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void addGameOverListener(GameOverListener listener) {
        gameOverListeners.add(listener);
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