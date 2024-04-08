package be.ugent.objprog.ugentopoly.controller;

import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.Log;
import be.ugent.objprog.ugentopoly.log.PlayerMoveLog;
import be.ugent.objprog.ugentopoly.model.Area;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.tiles.ChanceTile;
import be.ugent.objprog.ugentopoly.model.tiles.StreetTile;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class LogBookController {

    @FXML
    private Group logbookRoot;

    @FXML
    private TableView<Log> logTableView;

    @FXML
    private TableColumn<Log, String> messageColumn;

    @FXML
    private TableColumn<Log, String> timestampColumn;

    private GameLogBook logBook;

    public void initialize() {
        logBook = GameLogBook.getInstance();

        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        logTableView.setItems(logBook.getEntries());
        logTableView.getChildrenUnmodifiable().forEach(node -> {
            if (node instanceof ScrollPane scrollPane){
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            }
        });
    }

    @FXML
    private void hideLogbook() {
        logbookRoot.setVisible(false);
    }
}
