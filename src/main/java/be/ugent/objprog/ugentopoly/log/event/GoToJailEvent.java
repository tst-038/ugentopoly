package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;

public class GoToJailEvent extends Event {

    public GoToJailEvent(Player player) {
        super(String.format(player.getGameManager().getPropertyreader().get("log.gotojail"), player.getName()));
    }
}
