package be.ugent.objprog.ugentopoly.log;

public class TaxPaidLog extends Log {
        public TaxPaidLog(String player, int amount) {
            super(player + " betaalde " + amount + " belasting.");
        }
}
