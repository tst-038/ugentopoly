package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.JackpotClaimedLog;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;
import java.util.Optional;

public class Bank {

    private static Optional<Bank> instance = Optional.empty();
    private SimpleIntegerProperty jackpotBalance;

    private Bank() {
        this.jackpotBalance = new SimpleIntegerProperty(0);
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

    public void deposit(Player player, int amount) {
        player.setBalance(player.getBalance() + amount);
    }

    public void withdraw(Player player, int amount) throws InsufficientFundsException {
        int currentBalance = player.getBalance();
        if (currentBalance < amount) {
            throw new InsufficientFundsException("Insufficient funds for player " + player.getName());
        }
        player.setBalance(currentBalance - amount);
    }

    public void transfer(Player fromPlayer, Player toPlayer, int amount, TransactionPriority priority) throws InsufficientFundsException {
        if (priority == TransactionPriority.HIGH) {
            handleHighPriorityTransaction(fromPlayer, toPlayer, amount);
        } else {
            handleLowPriorityTransaction(fromPlayer, toPlayer, amount);
        }
    }

    public void transferToJackpot(Player player, int amount) {
        try {
            withdraw(player, amount);
            jackpotBalance.set(jackpotBalance.get() + amount);
        } catch (InsufficientFundsException e) {
            handleInsufficientFunds(player);
        }
    }

    private void handleHighPriorityTransaction(Player fromPlayer, Player toPlayer, int amount) {
        try {
            withdraw(fromPlayer, amount);
            deposit(toPlayer, amount);
        } catch (InsufficientFundsException e) {
            handleInsufficientFunds(fromPlayer);
            GameState.getInstance().notifyGameOverListeners(fromPlayer);
        }
    }

    private void handleLowPriorityTransaction(Player fromPlayer, Player toPlayer, int amount) throws InsufficientFundsException {
        withdraw(fromPlayer, amount);
        deposit(toPlayer, amount);
    }

    public boolean hasSufficientBalance(Player player, int amount) {
        return player.getBalance() >= amount;
    }


    public void claimJackpot(Player player) {
        GameLogBook.getInstance().addEntry(new JackpotClaimedLog(player.getName(), jackpotBalance.get()));
        deposit(player, jackpotBalance.get());
        jackpotBalance.set(0);
    }

    public IntegerProperty getJackpotBalanceProperty() {
        return jackpotBalance;
    }

    public int getJackpotBalance() {
        return jackpotBalance.get();
    }

    private void handleInsufficientFunds(Player player) {
        int remainingBalance = player.getBalance();
        try {
            withdraw(player, remainingBalance);
        } catch (InsufficientFundsException e) {
            // This should never happen
            throw new RuntimeException("Player has insufficient funds, but should have enough to withdraw remaining balance");
        }finally {
            GameState.getInstance().notifyGameOverListeners(player);
        }
    }
}