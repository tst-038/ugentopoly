package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.ChestTile;
import be.ugent.objprog.ugentopoly.model.tiles.FreeParkingTile;
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

public class ChestTileInfoPaneFactory implements TileInfoPaneFactory {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        if(tile.getType() != TileType.CHEST) {
            throw new IllegalArgumentException("Tile must be of type "+TileType.CHEST);
        }

        ChestTile chestTile = (ChestTile) tile;
        AnchorPane tileInfoPane = new AnchorPane();

        ImageView chestTileImage = createChestImage();
        Label title = createTitleLabel(chestTile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(chestTileImage, title, info);

        return tileInfoPane;
    }

    private ImageView createChestImage() {
        ImageView startImage = new ImageView(new Image(Ugentopoly.class.getResourceAsStream("assets/chest.png")));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        AnchorPane.setTopAnchor(startImage, 5.0);
        AnchorPane.setLeftAnchor(startImage, 37.5);
        AnchorPane.setRightAnchor(startImage, 37.5);
        return startImage;
    }

    private Label createTitleLabel(ChestTile tile) {
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

    private Label createInfoLabel() {
        // TODO ask if we can add fields in the configuration files, if allowed remove hardcoded values
        Label info = new Label("Draw a card from the chest");
        info.setFont(Font.font("System", FontWeight.NORMAL, 12));
        info.setWrapText(true);
        info.setAlignment(Pos.CENTER);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setTextFill(Color.WHITE);
        info.setPrefWidth(140.0);
        AnchorPane.setLeftAnchor(info, 5.0);
        AnchorPane.setRightAnchor(info, 5.0);
        AnchorPane.setTopAnchor(info, 140.);
        AnchorPane.setBottomAnchor(info, 5.0);
        return info;
    }


}

