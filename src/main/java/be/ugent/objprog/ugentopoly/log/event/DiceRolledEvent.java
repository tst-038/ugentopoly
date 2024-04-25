package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;

public class DiceRolledEvent extends Event {

    public DiceRolledEvent(Player thrower, int die1, int die2) {
        super(String.format(thrower.getGameManager().getPropertyreader().get("log.dice_rolled"), thrower.getName(), die1, die2));
    }
}
