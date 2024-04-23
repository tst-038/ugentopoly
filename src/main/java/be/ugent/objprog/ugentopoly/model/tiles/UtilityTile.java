package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.RentPaidLog;
import be.ugent.objprog.ugentopoly.logic.DiceHandler;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;
import be.ugent.objprog.ugentopoly.model.interfaces.Rentable;
import be.ugent.objprog.ugentopoly.model.tiles.visitors.TileVisitor;
import be.ugent.objprog.ugentopoly.ui.TileInfoPaneManager;
import be.ugent.objprog.ugentopoly.ui.interfaces.ImageUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdatable;
import be.ugent.objprog.ugentopoly.ui.interfaces.UIUpdateVisitor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class UtilityTile extends Tile implements UIUpdatable, ImageUpdatable, Buyable, Rentable  {

    private final int price;
    private Player owner;

    public UtilityTile(String id, int position, int cost, Game game) {
        super(id, position, TileType.UTILITY, game);
        this.price = cost;
    }

    public Image getImage() {
        return new Image(Objects.requireNonNull(Ugentopoly.class.getResourceAsStream("assets/" + getId().replaceAll("tile.", "") + ".png")));
    }

    @Override
    public void accept(TileVisitor visitor, boolean onVisit) {
        visitor.visit(this, onVisit);
    }

    @Override
    public void acceptUIUpdate(UIUpdateVisitor visitor, Node tileNode, Pane rootPane) {
        visitor.visit(this, tileNode, rootPane);
    }

    @Override
    public void updateImage(Node tileNode) {
        ImageView imageView = (ImageView) tileNode.lookup("ImageView");
        imageView.setImage(getImage());
    }

    @Override
    public void updateUI(Node tileNode, Pane rootPane) {
        updateImage(tileNode);
    }

    @Override
    public void onVisit(Player player) {
        if(owner == player){
            return;
        }

        TileInfoPaneManager tileInfoPaneManager = game.getTileInfoPaneManager();
        tileInfoPaneManager.showTileInfo(this, true);
        AnchorPane pane = tileInfoPaneManager.getTileInfoPane();

        if (owner != null) {
            pane.lookup("#pay-rent-button").setOnMouseClicked(event -> {
                payRent(player, game.getBank());
                tileInfoPaneManager.setPaneClosableAndHide();
            });
        } else {
            Button buy = (Button) pane.lookup("#buy-button");
            buy.setOnMouseClicked(event -> {
                buy(player, game.getBank(), game.getUIUpdater());
                player.getInventory().addOwnedUtility();
                tileInfoPaneManager.setPaneClosableAndHide();
            });
            buy.setDisable(!game.getBank().hasSufficientBalance(player, getPrice()));
            pane.lookup("#close-button").setOnMouseClicked(event -> {
                tileInfoPaneManager.setPaneClosableAndHide();
            });
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getRent() {
        return game.getDiceHandler().getLastRoll().stream().mapToInt(Integer::intValue).sum() * (owner.getInventory().getOwnedUtilities() == 1 ? 4 :10);
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public void payRent(Player player, Bank bank) {
            try {
                bank.transfer(player, getOwner(), getRent(), TransactionPriority.HIGH);
                GameLogBook.getInstance().addEntry(new RentPaidLog(player, this));
            } catch (InsufficientFundsException ignored) {
            }
        }
}
