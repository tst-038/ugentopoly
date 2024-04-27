package be.ugent.objprog.ugentopoly.model.player;

import be.ugent.objprog.ugentopoly.log.event.PlayerMoveEvent;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.logic.listener.PositionListener;
import be.ugent.objprog.ugentopoly.model.behaviour.IVisitable;
import be.ugent.objprog.ugentopoly.model.board.Board;
import be.ugent.objprog.ugentopoly.model.tile.Tile;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerPosition {
    private final Player player;
    private int position;
    private final List<PositionListener> listeners;

    public PlayerPosition(Player player) {
        this.player = player;
        this.position = 0;
        this.listeners = new CopyOnWriteArrayList<>();
    }

    public void addListener(PositionListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PositionListener listener) {
        listeners.remove(listener);
    }

    public int getPos() {
        return position;
    }

    public void updatePosition(int newPosition) {
        GameManager gameManager = player.getGameManager();
        Board board = gameManager.getGameState().getBoard();
        int m = board.getTiles().size();
        newPosition = ((newPosition % m) + m) % m;
        IVisitable current = board.getTileByPosition(newPosition);
        gameManager.getLogBook().addEntry(new PlayerMoveEvent(this.player, current));
        this.position = newPosition;
        Tile landedTile = board.getTileByPosition(position);
        landedTile.onVisit(player);
        notifyListeners();
    }

    public void setInitialPosition(int position) {
        this.position = position;
    }

    private void notifyListeners() {
        for (PositionListener listener : listeners) {
            listener.onPositionChanged(position);
        }
    }
}
