package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.Optional;

public class UtilityTile extends Tile implements UIUpdatable, ImageUpdatable, Buyable, Rentable  {

    private final int price;
    private Player owner;

    public UtilityTile(String id, int position, int cost) {
        super(id, position, TileType.UTILITY);
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
        TileInfoPaneManager.getInstance().showTileInfo(this, true);
        AnchorPane pane = TileInfoPaneManager.getInstance().getTileInfoPane();

        if (owner != null) {
            pane.lookup("#pay-rent-button").setOnMouseClicked(event -> {
                payRent(player);
                TileInfoPaneManager.getInstance().setPaneClosableAndHide();
            });
        } else {
            pane.lookup("#buy-button").setOnMouseClicked(event -> {
                buy(player);
                player.addOwnedUtility();
                TileInfoPaneManager.getInstance().setPaneClosableAndHide();
            });
            pane.lookup("#close-button").setOnMouseClicked(event -> {
                TileInfoPaneManager.getInstance().setPaneClosableAndHide();
            });
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getRent() {
        return DiceHandler.getInstance().getLastRoll().stream().mapToInt(Integer::intValue).sum()*owner.getOwnedUtility() == 1 ? 4 :10;
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
    public void payRent(Player player) {
            try {
                Bank.getInstance().transferMoney(player, Optional.of(getOwner()), getRent(), TransactionPriority.HIGH);
            } catch (InsufficientFundsException ignored) {
            }
        }
}
