package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private static Bank instance;

    private Bank() {
    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void initializeBalances(List<Player> players) {
        for (Player player : players) {
            player.setBalance(Settings.getInstance().getStartingBalance());
        }
    }

    public void addMoney(Player player, int amount) {
        player.setBalance(player.getBalance() + amount);
    }

    public void subtractMoney(Player player, int amount) throws InsufficientFundsException {
        int currentBalance = player.getBalance();
        if (currentBalance < amount) {
            throw new InsufficientFundsException("Insufficient funds for player " + player.getName());
        }
        player.setBalance(currentBalance - amount);
    }

    public void transferMoney(Player fromPlayer, Player toPlayer, int amount) throws InsufficientFundsException {
        subtractMoney(fromPlayer, amount);
        addMoney(toPlayer, amount);
    }
}
