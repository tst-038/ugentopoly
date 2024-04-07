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
        logBook.addEntry(new PlayerMoveLog(new Player("Tristan", Color.BEIGE), new StreetTile("2", 10, 50, new Area("5", "blue", 55), 5), new ChanceTile("4", 15)));

        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        logTableView.setItems(logBook.getEntries());
        logTableView.getChildrenUnmodifiable().forEach(node -> {
            if (node instanceof ScrollPane scrollPane){
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            }
        });
    }

    public void showLogbook() {
        logbookRoot.setVisible(true);
    }

    @FXML
    private void hideLogbook() {
        logBook.addEntry(new PlayerMoveLog(new Player("Tristan", Color.BEIGE), new StreetTile("2", 10, 50, new Area("5", "blue", 55), 5), new ChanceTile("4", 15)));
        logBook.addEntry(new PlayerMoveLog(new Player("Tristan", Color.BEIGE), new StreetTile("2", 10, 50, new Area("5", "blue", 55), 5), new ChanceTile("4", 15)));
        logBook.addEntry(new PlayerMoveLog(new Player("Tristan", Color.BEIGE), new StreetTile("2", 10, 50, new Area("5", "blue", 55), 5), new ChanceTile("4", 15)));
        logBook.addEntry(new PlayerMoveLog(new Player("Tristan", Color.BEIGE), new StreetTile("2", 10, 50, new Area("5", "blue", 55), 5), new ChanceTile("4", 15)));
        logbookRoot.setVisible(false);
    }
}
