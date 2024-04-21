package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Settings;

public class TaxPaidLog extends Log {

    public TaxPaidLog(String player, int amount) {
            super(String.format(PropertyReader.getInstance().get("log.tax_paid"), player, Settings.getMoneyUnit() + amount));
        }
}
