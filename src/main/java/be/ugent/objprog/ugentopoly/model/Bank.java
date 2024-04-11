package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.logic.GameState;

import java.util.List;
import java.util.Optional;

public class Bank {

    private static Optional<Bank> instance = Optional.empty();

    private Bank() {
    }

    public static Bank getInstance() {
        return instance.orElseGet(() -> {
            instance = Optional.of(new Bank());
            return instance.get();
        });
    }

    public void initializeBalances(List<Player> players) {
        int startingBalance = Settings.getInstance().getStartingBalance();
        players.forEach(player -> player.setBalance(startingBalance));
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

    public void transferMoney(Player fromPlayer, Optional<Player> toPlayer, int amount, TransactionPriority priority) throws InsufficientFundsException {
        if (priority == TransactionPriority.HIGH) {
            handleHighPriorityTransaction(fromPlayer, toPlayer, amount);
        } else {
            handleLowPriorityTransaction(fromPlayer, toPlayer, amount);
        }
    }

    private void handleHighPriorityTransaction(Player fromPlayer, Optional<Player> toPlayer, int amount) {
        try {
            subtractMoney(fromPlayer, amount);
            toPlayer.ifPresent(player -> addMoney(player, amount));
        } catch (InsufficientFundsException e) {
            handleInsufficientFunds(fromPlayer, toPlayer);
            GameState.getInstance().notifyGameOverListeners(fromPlayer);
        }
    }

    private void handleLowPriorityTransaction(Player fromPlayer, Optional<Player> toPlayer, int amount) throws InsufficientFundsException {
        subtractMoney(fromPlayer, amount);
        toPlayer.ifPresent(player -> addMoney(player, amount));
    }

    private void handleInsufficientFunds(Player fromPlayer, Optional<Player> toPlayer) {
        int remainingMoney = fromPlayer.getBalance();
        try {
            subtractMoney(fromPlayer, remainingMoney);
            toPlayer.ifPresent(player -> addMoney(player, remainingMoney));
        } catch (InsufficientFundsException ignored) {
        }
    }
}