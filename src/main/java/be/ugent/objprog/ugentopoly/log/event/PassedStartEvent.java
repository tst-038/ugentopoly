package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;

public class PassedStartEvent extends Event {

    public PassedStartEvent(Player player) {
        super(String.format(player.getGameManager().getPropertyreader().get("log.passed_start"), player.getName(), player.getGameManager().getSettings().getMoneyUnit() + player.getGameManager().getSettings().getStartBonus()));
    }
}
