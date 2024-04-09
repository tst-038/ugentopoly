package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.dice.Dice;
import be.ugent.objprog.ugentopoly.log.DiceRolledLog;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.model.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.List;

public class DiceHandler {

    private static DiceHandler instance;
    private final Dice dice;
    private List<DiceRolledListener> listeners;

    private DiceHandler() {
        this.dice = new Dice();
        this.listeners = new ArrayList<>();
    }

    public static DiceHandler getInstance() {
        if (instance == null) {
            instance = new DiceHandler();
        }
        return instance;
    }

    public void addDiceRolledListener(DiceRolledListener listener) {
        listeners.add(listener);
    }

    private void waitForDiceToStopRolling() {
        while (dice.isRolling()) {
            try {
                Thread.sleep(100); // Wait for a short interval before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void rollDice(Player player) {
        dice.roll(event -> {
            waitForDiceToStopRolling();
            List<Integer> rolls = dice.getLastRoll();
            GameLogBook.getInstance().addEntry(new DiceRolledLog(player, rolls.getFirst(), rolls.getLast()));
            notifyDiceRolledListeners(player, rolls);
        });
    }

    private void notifyDiceRolledListeners(Player player, List<Integer> rolls) {
        for (DiceRolledListener listener : listeners) {
            listener.onDiceRolled(player, rolls);
        }
    }


    // I wanted to add a method bringToFront to bring the diceStage to the front, but I couldn't find a way to do it
    // I used reflections to acces the stage object, but I just couldn't work around the dice module not being open to the ugentopoly module
}
