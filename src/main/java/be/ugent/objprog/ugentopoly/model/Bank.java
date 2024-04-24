package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.log.JackpotClaimedLog;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class Bank {
    private final Game game;
    private final SimpleIntegerProperty jackpotBalance;

    public Bank(Game game) {
        this.game = game;
        this.jackpotBalance = new SimpleIntegerProperty(0);
    }

    public void initializeBalances(List<Player> players) {
        int startingBalance = game.getSettings().getStartingBalance();
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
            game.getGameState().notifyGameOverListeners(fromPlayer);
        }
    }

    private void handleLowPriorityTransaction(Player fromPlayer, Player toPlayer, int amount) throws InsufficientFundsException {
        withdraw(fromPlayer, amount);
        deposit(toPlayer, amount);
    }

    public boolean hasInsufficientBalance(Player player, int amount) {
        return player.getBalance() < amount;
    }


    public void claimJackpot(Player player) {
        game.getLogBook().addEntry(new JackpotClaimedLog(player, jackpotBalance.get()));
        deposit(player, jackpotBalance.get());
        jackpotBalance.set(0);
    }

    public IntegerProperty getJackpotBalanceProperty() {
        return jackpotBalance;
    }

    private void handleInsufficientFunds(Player player) {
        int remainingBalance = player.getBalance();
        try {
            withdraw(player, remainingBalance);
        } catch (InsufficientFundsException e) {
            // This should never happen
            throw new RuntimeException("Player has insufficient funds, but should have enough to withdraw remaining balance");
        } finally {
            game.getGameState().notifyGameOverListeners(player);
        }
    }
}