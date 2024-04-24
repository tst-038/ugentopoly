package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;

public class JackpotClaimedLog extends Log {

    public JackpotClaimedLog(Player player, int amount) {
        super(String.format(player.getGame().getPropertyreader().get("log.jackpot_claimed"), player.getName(), amount));
    }
}
