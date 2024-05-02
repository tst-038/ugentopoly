package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.Ugentopoly;
import be.ugent.objprog.ugentopoly.log.event.Event;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class GameController {
    @FXML
    private Group logbookRoot;

    @FXML
    private TableView<Event> logTableView;

    @FXML
    private TableColumn<Event, String> messageColumn;

    @FXML
    private TableColumn<Event, String> timestampColumn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane tileInfoPane;

    private GameManager gameManager;

    public void initializeGame(List<Player> players, Ugentopoly ugentopoly){
        gameManager = new GameManager(players, rootPane, tileInfoPane, logbookRoot, logTableView, ugentopoly);
        logTableView.setItems(gameManager.getLogBook().getEntries());
        gameManager.startGame(rootPane);
    }

    @FXML
    private void initialize() {
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        logTableView.getChildrenUnmodifiable().forEach(node -> {
            if (node instanceof ScrollPane scrollPane) {
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            }
        });
    }

    @FXML
    private void hideLogbook() {
        logbookRoot.setVisible(false);
    }

    @FXML
    private void handleLogbookButtonClicked() {
        gameManager.toggleLogbookVisibility();
    }
}