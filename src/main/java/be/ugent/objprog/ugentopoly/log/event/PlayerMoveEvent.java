package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.behaviour.IVisitable;
import be.ugent.objprog.ugentopoly.model.player.Player;

public class PlayerMoveEvent extends Event {

    public PlayerMoveEvent(Player player, IVisitable current) {
        super(String.format(player.getGameManager().getPropertyreader().get("log.player_move"), player.getName(), current.getName()));
    }
}
