package be.ugent.objprog.ugentopoly.logic;

import be.ugent.objprog.dice.Dice;
import be.ugent.objprog.ugentopoly.log.DiceRolledLog;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.model.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class DiceHandler {

    private static DiceHandler instance;
    private final Dice dice;

    private DiceHandler() {
        this.dice = new Dice();
    }

    public static DiceHandler getInstance() {
        if (instance == null) {
            instance = new DiceHandler();
        }
        return instance;
    }

    public List<Integer> rollDice(EventHandler<ActionEvent> callback) {
        return dice.roll(callback);
    }

    public List<Integer> rollDice(Player player) {
        List<Integer> rolls = dice.roll();
        GameLogBook.getInstance().addEntry(new DiceRolledLog(player, rolls.getFirst(), rolls.getLast()));
        return rolls;
    }

   // I wanted to add a method bringToFront to bring the diceStage to the front, but I couldn't find a way to do it
    // I used reflections to acces the stage object, but I just couldn't work around the dice module not being open to the ugentopoly module
}
