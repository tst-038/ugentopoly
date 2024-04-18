package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.dice.DiceDialog;
import be.ugent.objprog.dice.DicePanel;
import be.ugent.objprog.ugentopoly.log.DiceRolledLog;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.model.Player;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class DiceHandler {

    private static DiceHandler instance;
    private final DicePanel dice;
    private final List<DiceRolledListener> listeners;
    private List<Integer> lastRoll;

    private DiceHandler() {
        System.setProperty("dice.testing", "true");
        this.dice = new DicePanel();
        this.listeners = new ArrayList<>();
    }

    public static DiceHandler getInstance() {
        if (instance == null) {
            instance = new DiceHandler();
        }
        return instance;
    }

    public Node getDiceDialog(){
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
        for (DiceRolledListener listener : listeners) {
            listener.onDiceRolled(player, rolls);
        }
    }

    //TODO see if this can work without.
    public List<Integer> getLastRoll(){
        return lastRoll;
    }


    // I wanted to add a method bringToFront to bring the diceStage to the front, but I couldn't find a way to do it
    // I used reflections to acces the stage object, but I just couldn't work around the dice module not being open to the ugentopoly module
}
