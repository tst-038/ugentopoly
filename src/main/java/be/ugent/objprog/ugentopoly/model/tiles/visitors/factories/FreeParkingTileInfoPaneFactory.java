package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.FreeParkingTile;
import be.ugent.objprog.ugentopoly.model.tiles.JailTile;
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

public class FreeParkingTileInfoPaneFactory implements TileInfoPaneFactory {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        if(tile.getType() != TileType.FREE_PARKING) {
            throw new IllegalArgumentException("Tile must be of type "+TileType.FREE_PARKING);
        }

        FreeParkingTile freeParkingTile = (FreeParkingTile) tile;
        AnchorPane tileInfoPane = new AnchorPane();

        ImageView freeParkingImage = createStartImage();
        Label title = createTitleLabel(freeParkingTile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(freeParkingImage, title, info);

        return tileInfoPane;
    }

    private ImageView createStartImage() {
        ImageView startImage = new ImageView(new Image(Ugentopoly.class.getResourceAsStream("assets/free_parking.png")));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        AnchorPane.setTopAnchor(startImage, 5.0);
        AnchorPane.setLeftAnchor(startImage, 37.5);
        AnchorPane.setRightAnchor(startImage, 37.5);
        return startImage;
    }

    private Label createTitleLabel(FreeParkingTile tile) {
        Label info = new Label(tile.getName());
        info.setFont(Font.font("System", FontWeight.BOLD, 20));
        info.setWrapText(true);
        info.setAlignment(Pos.CENTER);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setTextFill(Color.WHITE);
        info.setPrefWidth(140.0);
        AnchorPane.setLeftAnchor(info, 5.0);
        AnchorPane.setRightAnchor(info, 5.0);
        AnchorPane.setTopAnchor(info, 60.0);
        AnchorPane.setBottomAnchor(info, 35.0);
        return info;
    }

    private Label createInfoLabel() {
        // TODO ask if we can add fields in the configuration files, if allowed remove hardcoded values
        Label info = new Label("You are in the Free Parking tile. You can rest here and enjoy the view. You will not be charged for resting here.");
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
