package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.ugentopoly.model.Board;
import be.ugent.objprog.ugentopoly.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private static GameState instance;
    private final Board board;
    private final List<Player> players;
    private List<GameOverListener> gameOverListeners = new ArrayList<>();

    private GameState() {
        board = new Board();
        players = new ArrayList<>();
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

    public List<Player> getPlayers() {
        return players;
    }

    public void notifyGameOverListeners(Player player) {
        for (GameOverListener listener : gameOverListeners) {
            listener.onGameOver(player);
        }
    }
}