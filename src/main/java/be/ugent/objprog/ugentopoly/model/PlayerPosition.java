package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.PlayerMoveLog;
import be.ugent.objprog.ugentopoly.logic.PositionListener;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerPosition {
    private Player player;
    private int position;
    private List<PositionListener> listeners;

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
        int m = GameState.getInstance().getBoard().getTiles().size();
        newPosition = ((newPosition % m) + m) % m;
        Board board = GameState.getInstance().getBoard();
        Visitable current = board.getTileByPosition(newPosition);
        GameLogBook.getInstance().addEntry(new PlayerMoveLog(this.player, current));
        this.position = newPosition;
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
