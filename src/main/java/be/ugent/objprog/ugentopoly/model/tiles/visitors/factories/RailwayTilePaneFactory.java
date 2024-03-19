package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class RailwayTilePaneFactory implements TileInfoPaneFactory {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        if(tile.getType() != TileType.RAILWAY) {
            throw new IllegalArgumentException("Tile must be of type "+TileType.RAILWAY);
        }

        RailwayTile railwayTile = (RailwayTile) tile;
        AnchorPane tileInfoPane = new AnchorPane();

        ImageView railwayImage = createStartImage();
        Label title = createTitleLabel(railwayTile);
        Label priceLabel = createLabel("Price :", 12, Color.WHITE, 5.0, 161.0);
        Label price = createPriceLabel(railwayTile);

        tileInfoPane.getChildren().addAll(railwayImage, title, price, priceLabel);

        return tileInfoPane;
    }

    private ImageView createStartImage() {
        ImageView startImage = new ImageView(new Image(Ugentopoly.class.getResourceAsStream("assets/railway.png")));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        AnchorPane.setTopAnchor(startImage, 5.0);
        AnchorPane.setLeftAnchor(startImage, 37.5);
        AnchorPane.setRightAnchor(startImage, 37.5);
        return startImage;
    }

    private Label createPriceLabel(RailwayTile tile) {
        Label price = new Label(Settings.getMoneyUnit() + tile.getPrice());
        price.setAlignment(Pos.CENTER_RIGHT);
        price.setFont(Font.font("System", FontWeight.BOLD, 12));
        price.setTextFill(Color.WHITE);
        AnchorPane.setRightAnchor(price, 2.0);
        AnchorPane.setTopAnchor(price, 161.0);
        return price;
    }

    private Label createTitleLabel(RailwayTile tile) {
        Label info = new Label(tile.getName());
        info.setFont(Font.font("System", FontWeight.BOLD, 20));
        info.setWrapText(true);
        info.setAlignment(Pos.CENTER);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setTextFill(Color.WHITE);
        info.setPrefWidth(140.0);
        AnchorPane.setLeftAnchor(info, 5.0);
        AnchorPane.setRightAnchor(info, 5.0);
        AnchorPane.setTopAnchor(info, 80.0);
        AnchorPane.setBottomAnchor(info, 35.0);
        return info;
    }
}
