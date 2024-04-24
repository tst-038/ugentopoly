package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;

public class PlayerMoveLog extends Log {

    public PlayerMoveLog(Player player, Visitable current) {
        super(String.format(player.getGame().getPropertyreader().get("log.player_move"), player.getName(), current.getName()));
    }
}
