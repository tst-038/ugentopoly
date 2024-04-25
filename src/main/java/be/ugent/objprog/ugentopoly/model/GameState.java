package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.logic.listener.GameOverListener;
import be.ugent.objprog.ugentopoly.model.board.Board;
import be.ugent.objprog.ugentopoly.model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Board board;
    private final List<GameOverListener> gameOverListeners = new ArrayList<>();

    public GameState(GameManager gameManager) {
        this.board = new Board();
        this.board.init(gameManager);
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