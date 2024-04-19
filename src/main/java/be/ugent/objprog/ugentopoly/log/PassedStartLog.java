package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;

public class PassedStartLog extends Log {

    public PassedStartLog(Player player) {
        super(String.format(PropertyReader.getInstance().get("log.passed_start"), player.getName(), Settings.getMoneyUnit()+Settings.getInstance().getStartBonus()));
    }
}
