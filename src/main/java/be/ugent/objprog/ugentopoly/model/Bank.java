package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.logic.GameState;

import java.util.List;

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

    public void transferMoney(Player fromPlayer, Player toPlayer, int amount, TransactionPriority priority) throws InsufficientFundsException {
        if (priority == TransactionPriority.HIGH) {
            try {
                subtractMoney(fromPlayer, amount);
                addMoney(toPlayer, amount);
            } catch (InsufficientFundsException e) {
                // If the transaction is high priority (e.g., paying rent) and there are insufficient funds,
                // transfer all remaining money and notify game over listeners
                int remainingMoney = fromPlayer.getBalance();
                subtractMoney(fromPlayer, remainingMoney);
                addMoney(toPlayer, remainingMoney);
                GameState.getInstance().notifyGameOverListeners(fromPlayer);
                throw e;
            }
        } else {
            // For low-priority transactions, simply throw the InsufficientFundsException
            subtractMoney(fromPlayer, amount);
            addMoney(toPlayer, amount);
        }
    }
}
