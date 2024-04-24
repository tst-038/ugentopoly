package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;

public class PassedStartLog extends Log {

    public PassedStartLog(Player player) {
        super(String.format(player.getGame().getPropertyreader().get("log.passed_start"), player.getName(), player.getGame().getSettings().getMoneyUnit() + player.getGame().getSettings().getStartBonus()));
    }
}
