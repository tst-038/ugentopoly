package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.behaviour.IVisitable;

public class PlayerMoveEvent extends Event {

    public PlayerMoveEvent(Player player, IVisitable current) {
        super(String.format(player.getGameManager().getPropertyreader().get("log.player_move"), player.getName(), current.getName()));
    }
}
