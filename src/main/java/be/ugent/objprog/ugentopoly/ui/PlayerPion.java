package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.controller.GameController;
import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.logic.GameState;
import be.ugent.objprog.ugentopoly.model.Player;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Objects;

public class PlayerPion extends ImageView {
    public Pane pionContainer;
    private final Player player;

    public PlayerPion(Player player) {
        this.player = player;
        Image pionImage = new Image(Objects.requireNonNull(ResourceLoader.loadResource("assets/token" + (player.getId()+1)+ ".png")));
        setImage(pionImage);
        setFitWidth(25);
        setPreserveRatio(true);
    }

    public void initialize(){
        pionContainer = (Pane) GameState.getInstance().getRootpane().lookup("#_"+player.getPosition()).lookup("#pionContainer");
        pionContainer.getChildren().add(this);
        pionContainer.toFront();
    }


    // TODO not used
    public Player getPlayer() {
        return player;
    }

    public void movePion(int position) {
        // Get the current pionContainer
        Pane currentPionContainer = pionContainer;

        // Get the target pionContainer based on the position
        String tileId = "#_" + position;
        pionContainer = (Pane) GameState.getInstance().getRootpane().lookup(tileId).lookup("#pionContainer");
        pionContainer.toFront();
        pionContainer.getChildren().add(this);
        currentPionContainer.getChildren().remove(this);
    }
}