package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;

import java.util.Set;

public class PassedStartLog extends Log {

    public PassedStartLog(Player player) {
        super(player.getName() + " ontving " + Settings.getMoneyUnit() + Settings.getInstance().getStartBonus() + " voor het passeren van start.");
    }
}
