package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;
import be.ugent.objprog.ugentopoly.model.tiles.Tile;
import be.ugent.objprog.ugentopoly.model.tiles.TileType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class TaxTileInfoPaneFactory implements TileInfoPaneFactory {

    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        if(tile.getType() != TileType.TAX) {
            throw new IllegalArgumentException("Tile must be of type "+TileType.TAX);
        }
        TaxTile taxTile = (TaxTile) tile;
        AnchorPane tileInfoPane = new AnchorPane();

        ImageView taxImage = createTaxImage();
        Label reason = createReasonLabel(taxTile);
        Label price = createTaxPriceLabel(taxTile);

        tileInfoPane.getChildren().addAll(taxImage, reason, price);

        return tileInfoPane;
    }

    private ImageView createTaxImage() {
        ImageView taxImage = new ImageView(new Image(Ugentopoly.class.getResourceAsStream("assets/tax.png")));
        taxImage.setFitHeight(75.0);
        taxImage.setFitWidth(75.0);
        AnchorPane.setTopAnchor(taxImage, 5.0);
        AnchorPane.setLeftAnchor(taxImage, 37.5);
        AnchorPane.setRightAnchor(taxImage, 37.5);
        return taxImage;
    }

    private Label createReasonLabel(TaxTile tile) {
        Label reason = new Label(tile.getName());
        reason.setFont(Font.font("System", FontWeight.BOLD, 20));
        reason.setWrapText(true);
        reason.setAlignment(Pos.CENTER);
        reason.setTextAlignment(TextAlignment.CENTER);
        reason.setTextFill(Color.WHITE);
        reason.setPrefWidth(140.0);
        AnchorPane.setLeftAnchor(reason, 5.0);
        AnchorPane.setRightAnchor(reason, 5.0);
        AnchorPane.setTopAnchor(reason, 85.0);
        AnchorPane.setBottomAnchor(reason, 35.0);
        return reason;
    }

    private Label createTaxPriceLabel(TaxTile tile) {
        Label price = new Label(Settings.getMoneyUnit() + tile.getAmount());
        price.setFont(Font.font("System", FontWeight.BOLD, 12));
        price.setWrapText(true);
        price.setAlignment(Pos.CENTER);
        price.setTextAlignment(TextAlignment.CENTER);
        price.setTextFill(Color.WHITE);
        price.setPrefWidth(140.0);
        AnchorPane.setLeftAnchor(price, 5.0);
        AnchorPane.setRightAnchor(price, 5.0);
        AnchorPane.setBottomAnchor(price, 5.0);
        return price;
    }


}
