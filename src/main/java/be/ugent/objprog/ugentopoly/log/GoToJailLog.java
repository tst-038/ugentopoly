package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;

public class GoToJailLog extends Log {

    public GoToJailLog(String playerName) {
        super(String.format(PropertyReader.getInstance().get("log.gotojail"), playerName));
    }
}
