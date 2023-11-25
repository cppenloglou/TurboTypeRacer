package com.gui;

import com.game.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class LeaderBoardScreenController {
    @FXML
    private ImageView goBackButton;
    @FXML
    private TabPane tabPane;
    private Stage stage;
    private Parent root;

    @FXML
    void returnToMain(MouseEvent event) throws IOException {
        if(LoginManager.getCurrentPlayer()!=null){
            StartScreenController.sceneGenerator(stage,root,"MainScreen-view.fxml", event, "Main Screen");
        } else {
            StartScreenController.sceneGenerator(stage,root,"StartScreen-view.fxml", event, "Start Screen");
        }
    }

    @FXML
    void initialize() {
        assert tabPane != null : "fx:id=\"TabPane\" was not injected: check your FXML file 'LeaderBoardScreen-view.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'EditProfileScreen-view.fxml'.";

        initializeTabPane();
    }

    public void initializeTabPane(){
        for(Level level : LevelManager.getLevelDatabase()){
            Tab tab = new Tab("Level " + level.getLevelNum());
            tab.setClosable(false);

            TableView<PlayerData> tableView = createTable();
            fillTable(tableView, level);

            if(!tableView.getItems().isEmpty()){
                tableView.getColumns().get(2).setSortType(TableColumn.SortType.DESCENDING);
                tableView.getSortOrder().add(tableView.getColumns().get(2));
                tableView.sort();

                tab.setContent(tableView);
                tabPane.getTabs().add(tab);
            }
        }
        tabPane.getTabs().remove(0);
    }
    public TableView<PlayerData> createTable(){
        TableView<PlayerData> tableView = new TableView<>();
        tableView.setEditable(false);

        TableColumn<PlayerData, ImageView> iconColumn = new TableColumn<>("Icon");
        TableColumn<PlayerData, String> playerNameColumn = new TableColumn<>("Player");
        TableColumn<PlayerData, Integer> winsColumn = new TableColumn<>("Wins");

        iconColumn.setCellValueFactory(new PropertyValueFactory<>("Icon"));
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("Player"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("Wins"));

        iconColumn.setReorderable(false);
        playerNameColumn.setReorderable(false);
        winsColumn.setReorderable(false);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        tableView.getColumns().add(iconColumn);
        tableView.getColumns().add(playerNameColumn);
        tableView.getColumns().add(winsColumn);

        return tableView;
    }

    public void fillTable(TableView<PlayerData> tableView, Level level){
        ObservableList<PlayerData> data = FXCollections.observableArrayList();
        for(Player p : LoginManager.getPlayerDatabase()){
            if(p.getScoreMap().containsKey(level)){
                ImageView icon = new ImageView(new Image(p.getProfileImage()));

                double ratio = Math.min(Screen.getPrimary().getBounds().getHeight() / icon.getImage().getHeight(), Screen.getPrimary().getBounds().getWidth() / icon.getImage().getWidth());
                icon.setFitHeight(icon.getImage().getHeight()*ratio*0.05);
                icon.setFitWidth(icon.getImage().getWidth()*ratio*0.05);

                data.add(new PlayerData(icon, p.getName(), p.getScore(level)));
            }
        }

        tableView.setItems(data);
    }

}
