package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.exceptions.UgentopolyException;
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

    public boolean withdraw(Player player, int amount) {
        int currentBalance = player.getBalance();
        if (currentBalance < amount) {
            return false;
        }
        player.setBalance(currentBalance - amount);
        return true;
    }

    public boolean transfer(Player fromPlayer, Player toPlayer, int amount, TransactionPriority priority) {
        if (withdraw(fromPlayer, amount)) {
            deposit(toPlayer, amount);
            return true;
        } else {
            if (priority == TransactionPriority.HIGH) {
                handleInsufficientFunds(fromPlayer);
            }
            return false;
        }
    }

    public void transferToJackpot(Player player, int amount) {
            if(withdraw(player, amount)) {
                jackpotBalance.set(jackpotBalance.get() + amount);
            }else {
                handleInsufficientFunds(player);
            }
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
        withdraw(player, remainingBalance);
        game.getGameState().notifyGameOverListeners(player);
    }
}