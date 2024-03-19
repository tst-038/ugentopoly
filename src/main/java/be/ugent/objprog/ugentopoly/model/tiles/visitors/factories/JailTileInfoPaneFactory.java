package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.tiles.JailTile;
import be.ugent.objprog.ugentopoly.model.tiles.StartTile;
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

public class JailTileInfoPaneFactory implements TileInfoPaneFactory {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        if(tile.getType() != TileType.JAIL) {
            throw new IllegalArgumentException("Tile must be of type "+TileType.JAIL);
        }

        JailTile jailTile = (JailTile) tile;
        AnchorPane tileInfoPane = new AnchorPane();

        ImageView jailImage = createStartImage();
        Label title = createTitleLabel(jailTile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(jailImage, title, info);

        return tileInfoPane;
    }

    private ImageView createStartImage() {
        ImageView startImage = new ImageView(new Image(Ugentopoly.class.getResourceAsStream("assets/start.png")));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        AnchorPane.setTopAnchor(startImage, 5.0);
        AnchorPane.setLeftAnchor(startImage, 37.5);
        AnchorPane.setRightAnchor(startImage, 37.5);
        return startImage;
    }

    private Label createTitleLabel(JailTile tile) {
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
        Label info = new Label("You are in jail, you can't do anything until you roll a double or pay X$");
        info.setFont(Font.font("System", FontWeight.NORMAL, 12));
        info.setWrapText(true);
        info.setAlignment(Pos.CENTER);
        info.setTextAlignment(TextAlignment.CENTER);
        info.setTextFill(Color.WHITE);
        info.setPrefWidth(140.0);
        AnchorPane.setLeftAnchor(info, 5.0);
        AnchorPane.setRightAnchor(info, 5.0);
        AnchorPane.setTopAnchor(info, 120.);
        AnchorPane.setBottomAnchor(info, 5.0);
        return info;
    }


}
