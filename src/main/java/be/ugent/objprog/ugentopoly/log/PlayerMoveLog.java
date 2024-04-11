package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;

public class PlayerMoveLog extends Log {
    public PlayerMoveLog(Player player, Visitable old, Visitable current) {
        super(player.getName() + " bewoog zich " +  (current.getPosition() - old.getPosition() + 40) % 40 + " stappen. (Nieuwe positie: " + current.getName());
    }
}
