package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;

public class JackpotClaimedLog extends Log {

    public JackpotClaimedLog(String playerName, int amount) {
        super(String.format(PropertyReader.getInstance().get("log.jackpot_claimed"), playerName, amount));
    }
}
