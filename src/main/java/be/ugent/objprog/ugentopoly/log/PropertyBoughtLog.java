package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;

public class PropertyBoughtLog extends Log {

    public PropertyBoughtLog(Player buyer, Buyable buyableProperty) {
        super(String.format(PropertyReader.getInstance().get("log.property_bought"), buyer.getName(), buyableProperty.getName(), Settings.getMoneyUnit() + buyableProperty.getPrice()));
    }
}
