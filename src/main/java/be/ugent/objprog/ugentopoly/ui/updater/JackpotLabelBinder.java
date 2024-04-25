package be.ugent.objprog.ugentopoly.ui.updater;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class JackpotLabelBinder {
    private final AnchorPane rootPane;
    private final GameManager gameManager;

    public JackpotLabelBinder(AnchorPane rootPane, GameManager gameManager) {
        this.rootPane = rootPane;
        this.gameManager = gameManager;
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
                gameManager.getBank().getJackpotBalanceProperty().asString(
                        gameManager.getSettings().getMoneyUnit() + "%d"
                )
        );
    }
}
