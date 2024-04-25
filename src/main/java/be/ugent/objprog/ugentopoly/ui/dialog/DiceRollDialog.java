package be.ugent.objprog.ugentopoly.ui.dialog;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class DiceRollDialog {
    private final AnchorPane rootPane;

    public DiceRollDialog(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public void initializeDices(Node diceDialog) {
        AnchorPane dicePane = getDicePane();
        addDiceDialogToPane(dicePane, diceDialog);
        setDiceDialogAnchors(diceDialog);
    }

    private AnchorPane getDicePane() {
        return (AnchorPane) rootPane.lookup("#dicePane");
    }

    private void addDiceDialogToPane(AnchorPane dicePane, Node diceDialog) {
        dicePane.getChildren().add(diceDialog);
    }

    private void setDiceDialogAnchors(Node diceDialog) {
        AnchorPane.setTopAnchor(diceDialog, 0.0);
        AnchorPane.setLeftAnchor(diceDialog, 0.0);
        AnchorPane.setRightAnchor(diceDialog, 0.0);
        AnchorPane.setBottomAnchor(diceDialog, 0.0);
    }
}