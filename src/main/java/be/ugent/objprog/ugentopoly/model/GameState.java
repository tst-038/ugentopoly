package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.logic.GameOverListener;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Board board;
    private final List<GameOverListener> gameOverListeners = new ArrayList<>();

    public GameState(Game game) {
        this.board = new Board();
        this.board.init(game);
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