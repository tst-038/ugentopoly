package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.dice.DicePanel;
import be.ugent.objprog.ugentopoly.log.DiceRolledLog;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.model.Player;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class DiceHandler {
    private final DicePanel dice;
    private final List<DiceRolledListener> listeners;
    private List<Integer> lastRoll;

    public DiceHandler() {
        System.setProperty("dice.testing", "true");
        this.dice = new DicePanel();
        this.listeners = new ArrayList<>();
    }

    public Node getDiceDialog() {
        return dice;
    }

    public void addDiceRolledListener(DiceRolledListener listener) {
        listeners.add(listener);
    }

    public void rollDice(Player player) {
        dice.roll(rolls -> {
            lastRoll = rolls;
            GameLogBook.getInstance().addEntry(new DiceRolledLog(player, rolls.getFirst(), rolls.getLast()));
            notifyDiceRolledListeners(player, rolls);
        });
    }

    private void notifyDiceRolledListeners(Player player, List<Integer> rolls) {
        System.out.println("DiceHandler: Notifying listeners" + listeners);
        for (DiceRolledListener listener : listeners) {
            listener.onDiceRolled(player, rolls);
        }
    }

    public List<Integer> getLastRoll() {
        return lastRoll;
    }
}