package be.ugent.objprog.ugentopoly.ui;

import be.ugent.objprog.ugentopoly.controller.Game;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class JackpotLabelBinder {
    private final AnchorPane rootPane;
    private final Game game;

    public JackpotLabelBinder(AnchorPane rootPane, Game game) {
        this.rootPane = rootPane;
        this.game = game;
    }

    public void bindJackpot() {
        Label jackpotLabel = getJackpotLabel();
        bindJackpotLabelText(jackpotLabel);
    }

    private Label getJackpotLabel() {
        return (Label) rootPane.lookup("#jackpot");
    }

    private void bindJackpotLabelText(Label jackpotLabel) {
        jackpotLabel.textProperty().bind(
                game.getBank().getJackpotBalanceProperty().asString(
                        game.getSettings().getMoneyUnit() + "%d"
                )
        );
    }
}
