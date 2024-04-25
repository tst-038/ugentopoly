package be.ugent.objprog.ugentopoly.model;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.log.event.JackpotClaimedEvent;
import be.ugent.objprog.ugentopoly.model.player.Player;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public class Bank {
    private final GameManager gameManager;
    private final SimpleIntegerProperty jackpotBalance;

    public Bank(GameManager gameManager) {
        this.gameManager = gameManager;
        this.jackpotBalance = new SimpleIntegerProperty(0);
    }

    public void initializeBalances(List<Player> players) {
        int startingBalance = gameManager.getSettings().getStartingBalance();
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
        gameManager.getLogBook().addEntry(new JackpotClaimedEvent(player, jackpotBalance.get()));
        deposit(player, jackpotBalance.get());
        jackpotBalance.set(0);
    }

    public IntegerProperty getJackpotBalanceProperty() {
        return jackpotBalance;
    }

    private void handleInsufficientFunds(Player player) {
        int remainingBalance = player.getBalance();
        withdraw(player, remainingBalance);
        gameManager.getGameState().notifyGameOverListeners(player);
    }
}