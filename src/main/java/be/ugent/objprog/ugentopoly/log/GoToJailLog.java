package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;

public class GoToJailLog extends Log {

    public GoToJailLog(Player player) {
        super(String.format(player.getGame().getPropertyreader().get("log.gotojail"), player.getName()));
    }
}
