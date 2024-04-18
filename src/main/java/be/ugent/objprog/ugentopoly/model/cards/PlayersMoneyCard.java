package be.ugent.objprog.ugentopoly.model.cards;

import be.ugent.objprog.ugentopoly.controller.PlayerManager;
import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;

import java.util.Optional;

public class PlayersMoneyCard extends Card {
    private int amount;

    public PlayersMoneyCard(String id, int amount) {
        super(id, String.format(PropertyReader.getInstance().get("card.players_money_card"), amount), CardType.PLAYERS_MONEY);
        this.amount = amount;
    }

    @Override
    public void execute(Player player) {
        PlayerManager.getInstance().getPlayers().forEach(p -> {
            if (!p.equals(player)) {
                try{
                    Bank.getInstance().transferMoney(p, Optional.of(player), amount, TransactionPriority.HIGH);
                } catch (InsufficientFundsException ignored) {}
            }
        });
        player.removeCard(this);
    }
}
