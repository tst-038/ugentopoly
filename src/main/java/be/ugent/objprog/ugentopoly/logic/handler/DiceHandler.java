package be.ugent.objprog.ugentopoly.logic.handler;

import be.ugent.objprog.dice.DicePanel;
import be.ugent.objprog.ugentopoly.log.event.DiceRolledEvent;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.logic.listener.DiceRolledListener;
import be.ugent.objprog.ugentopoly.model.player.Player;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class DiceHandler {
    private final GameManager gameManager;
    private final DicePanel dice;
    private final List<DiceRolledListener> listeners;
    private List<Integer> lastRoll;

    public DiceHandler(GameManager gameManager) {
        this.gameManager = gameManager;
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
            gameManager.getLogBook().addEntry(new DiceRolledEvent(player, rolls.getFirst(), rolls.getLast()));
            notifyDiceRolledListeners(player, rolls);
        });
    }

    private void notifyDiceRolledListeners(Player player, List<Integer> rolls) {
        for (DiceRolledListener listener : listeners) {
            listener.onDiceRolled(player, rolls);
        }
    }

    public List<Integer> getLastRoll() {
        return lastRoll;
    }
}