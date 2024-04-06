package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private static Bank instance;
    private Map<Player, Integer> balances;

    private Bank() {
        balances = new HashMap<>();
    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void initializeBalances(List<Player> players) {
        for (Player player : players) {
            balances.put(player, 1500); // Starting balance
        }
    }

    public void addMoney(Player player, int amount) {
        int currentBalance = balances.get(player);
        balances.put(player, currentBalance + amount);
    }

    public void subtractMoney(Player player, int amount) throws InsufficientFundsException {
        int currentBalance = balances.get(player);
        if (currentBalance < amount) {
            throw new InsufficientFundsException("Insufficient funds for player " + player.getName());
        }
        balances.put(player, currentBalance - amount);
    }

    public void transferMoney(Player fromPlayer, Player toPlayer, int amount) throws InsufficientFundsException {
        subtractMoney(fromPlayer, amount);
        addMoney(toPlayer, amount);
    }

    public int getBalance(Player player) {
        return balances.get(player);
    }
}
