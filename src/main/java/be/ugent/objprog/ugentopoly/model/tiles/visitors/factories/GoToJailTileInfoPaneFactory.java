package be.ugent.objprog.ugentopoly.model.tiles.visitors.factories;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.GoToJailTile;
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

public class GoToJailTileInfoPaneFactory implements TileInfoPaneFactory {
    @Override
    public AnchorPane createTileInfoPane(Tile tile) {
        if(tile.getType() != TileType.GO_TO_JAIL) {
            throw new IllegalArgumentException("Tile must be of type "+TileType.GO_TO_JAIL);
        }

        GoToJailTile goToJailTile = (GoToJailTile) tile;
        AnchorPane tileInfoPane = new AnchorPane();

        ImageView goToJailImage = createStartImage();
        Label title = createTitleLabel(goToJailTile);
        Label info = createInfoLabel();

        tileInfoPane.getChildren().addAll(goToJailImage, title, info);

        return tileInfoPane;
    }

    private ImageView createStartImage() {
        ImageView startImage = new ImageView(new Image(Ugentopoly.class.getResourceAsStream("assets/go_to_jail.png")));
        startImage.setFitHeight(75.0);
        startImage.setFitWidth(75.0);
        AnchorPane.setTopAnchor(startImage, 5.0);
        AnchorPane.setLeftAnchor(startImage, 37.5);
        AnchorPane.setRightAnchor(startImage, 37.5);
        return startImage;
    }

    private Label createTitleLabel(GoToJailTile tile) {
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
        Label info = new Label("When you land on this tile, you will be sent to jail.");
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
