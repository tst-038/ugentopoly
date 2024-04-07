package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;

public class DiceRolledLog extends Log {

    protected DiceRolledLog(Player thrower, int die1, int die2) {
        super(thrower.getName() + " rolde " + die1 + " + " + die2 + " dobbelstenen.");
    }
}
