package be.ugent.objprog.ugentopoly.model.tiles.visitors;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import be.ugent.objprog.ugentopoly.model.tiles.TaxTile;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TileInfoPaneUpdater implements TileVisitor {
    private AnchorPane tileInfoPane;

    public TileInfoPaneUpdater(AnchorPane tileInfoPane) {
        this.tileInfoPane = tileInfoPane;
    }

    public void updateStreetTileInfo(StreetTile tile) {
        tileInfoPane.setVisible(true);
        tileInfoPane.getChildren().clear();

        // Create the UI components
        AnchorPane infoColor = new AnchorPane();
        infoColor.setStyle("-fx-background-color: " + tile.getArea().getColor() + "; -fx-border-width: 0 0 2 0; -fx-border-color: black;");
        infoColor.setPrefHeight(50.0);
        infoColor.setPrefWidth(150.0);
        AnchorPane.setTopAnchor(infoColor, 0.0);
        AnchorPane.setLeftAnchor(infoColor, 0.0);
        AnchorPane.setRightAnchor(infoColor, 0.0);

        Label infoTitle = new Label(tile.getName());
        infoTitle.setAlignment(Pos.CENTER);
        infoTitle.setFont(Font.font("System Bold", 20));
        infoTitle.setTextFill(Color.WHITE);
        AnchorPane.setLeftAnchor(infoTitle, 0.0);
        AnchorPane.setRightAnchor(infoTitle, 0.0);
        infoColor.getChildren().add(infoTitle);

        Label rentLabel = new Label("Rent :");
        rentLabel.setFont(Font.font("System Bold", 12));
        rentLabel.setTextFill(Color.WHITE);
        AnchorPane.setLeftAnchor(rentLabel, 5.0);
        AnchorPane.setTopAnchor(rentLabel, 71.0);

        Label[] rents = new Label[5];
        for (int i = 0; i < 5; i++) {
            rents[i] = new Label(tile.getAllRents()[i] + "$");
            rents[i].setAlignment(Pos.CENTER_RIGHT);
            rents[i].setFont(Font.font("System Bold", 12));
            rents[i].setTextFill(Color.WHITE);
            AnchorPane.setRightAnchor(rents[i], 2.0);
            AnchorPane.setTopAnchor(rents[i], 71.0 + (i * 18.0));
        }

        Label priceLabel = new Label("Price :");
        priceLabel.setFont(Font.font("System Bold", 12));
        priceLabel.setTextFill(Color.WHITE);
        AnchorPane.setLeftAnchor(priceLabel, 5.0);
        AnchorPane.setTopAnchor(priceLabel, 161.0);

        Label price = new Label(tile.getPrice() + "$");
        price.setAlignment(Pos.CENTER_RIGHT);
        price.setFont(Font.font("System Bold", 12));
        price.setTextFill(Color.WHITE);
        AnchorPane.setRightAnchor(price, 2.0);
        AnchorPane.setTopAnchor(price, 161.0);

        Label ownerLabel = new Label("Current Owner :");
        ownerLabel.setAlignment(Pos.CENTER);
        ownerLabel.setFont(Font.font("System Bold", 12));
        ownerLabel.setTextFill(Color.WHITE);
        AnchorPane.setBottomAnchor(ownerLabel, 40.0);
        AnchorPane.setLeftAnchor(ownerLabel, 0.0);
        AnchorPane.setRightAnchor(ownerLabel, 0.0);

        Label owner = new Label(tile.getOwner() != null ? tile.getOwner().getName() : "<None>");
        owner.setAlignment(Pos.CENTER);
        owner.setFont(Font.font("System Bold", 12));
        owner.setTextFill(Color.WHITE);
        AnchorPane.setBottomAnchor(owner, 25.0);
        AnchorPane.setLeftAnchor(owner, 0.0);
        AnchorPane.setRightAnchor(owner, 0.0);

        // Add the components to the tileInfoPane
        tileInfoPane.getChildren().addAll(infoColor, rentLabel, priceLabel, price, ownerLabel, owner);
        tileInfoPane.getChildren().addAll(rents);
    }

    public void updateTaxTileInfo(TaxTile tile) {
        tileInfoPane.setVisible(true);
        tileInfoPane.getChildren().clear();

        // Create the UI components
        ImageView taxImage = new ImageView(new Image(Ugentopoly.class.getResourceAsStream("assets/tax.png")));
        taxImage.setFitHeight(75.0);
        taxImage.setFitWidth(75.0);
        AnchorPane.setTopAnchor(taxImage, 5.);
        AnchorPane.setLeftAnchor(taxImage, 75/2.);
        AnchorPane.setRightAnchor(taxImage, 75/2.);

        Label rentLabel = new Label(tile.getName());
        rentLabel.setFont(Font.font("System Bold", 20));
        rentLabel.setWrapText(true);
        rentLabel.setAlignment(Pos.CENTER);
        rentLabel.setTextAlignment(TextAlignment.CENTER);
        rentLabel.setTextFill(Color.WHITE);
        AnchorPane.setLeftAnchor(rentLabel, 5.0);
        AnchorPane.setRightAnchor(rentLabel, 5.0);
        AnchorPane.setTopAnchor(rentLabel, 80.);
        AnchorPane.setBottomAnchor(rentLabel, 5.0);


        // Add the components to the tileInfoPane
        tileInfoPane.getChildren().addAll(taxImage, rentLabel);
    }

    @Override
    public void visit(StreetTile tile) {
        if(tileInfoPane.isVisible()){
            tileInfoPane.setVisible(false);
        }else{
            updateStreetTileInfo(tile);
        }
    }

    @Override
    public void visit(TaxTile tile) {
        if(tileInfoPane.isVisible()){
            tileInfoPane.setVisible(false);
        }else{
            updateTaxTileInfo(tile);
        }
    }
}