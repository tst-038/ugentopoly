package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;

public class JackpotClaimedEvent extends Event {

    public JackpotClaimedEvent(Player player, int amount) {
        super(String.format(player.getGameManager().getPropertyreader().get("log.jackpot_claimed"), player.getName(), amount));
    }
}
