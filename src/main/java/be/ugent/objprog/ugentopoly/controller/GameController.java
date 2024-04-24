package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.log.Log;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
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
    private TableView<Log> logTableView;

    @FXML
    private TableColumn<Log, String> messageColumn;

    @FXML
    private TableColumn<Log, String> timestampColumn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane tileInfoPane;

    private Game game;

    public void initializeGame(List<Player> players, PropertyReader propertyReader, Settings settings) {
        game = new Game(players, rootPane, tileInfoPane, logbookRoot, propertyReader, settings);
        logTableView.setItems(game.getLogBook().getEntries());
        game.startGame(rootPane);
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
        game.toggleLogbookVisibility();
    }
}