package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Visitable;

public class PlayerMoveLog extends Log {

    public PlayerMoveLog(Player player, Visitable current) {
        super(String.format(PropertyReader.getInstance().get("log.player_move"), player.getName(), current.getName()));
    }
}
