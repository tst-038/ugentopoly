package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;

public class DiceRolledLog extends Log {

    public DiceRolledLog(Player thrower, int die1, int die2) {
        super(String.format(thrower.getGame().getPropertyreader().get("log.dice_rolled"), thrower.getName(), die1, die2));
    }
}
