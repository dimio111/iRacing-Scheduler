package be.dimi.iracing.scheduler.controllers;

import be.dimi.iracing.scheduler.race.RaceModel;
import be.dimi.iracing.scheduler.tasks.SetRaceListTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by Dimitri on 28/12/2014.
 */
public class TableViewController {

    private ObservableList<RaceModel> raceData = FXCollections.observableArrayList();

    @FXML
    private TableView<RaceModel> raceTableView;
    @FXML
    private TableColumn<RaceModel, String> dateColumn;
    @FXML
    private TableColumn<RaceModel, String> hourColumn;
    @FXML
    private TableColumn<RaceModel, String> raceColumn;

    @FXML
    public void initialize() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("raceDay"));
        hourColumn.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("raceHour"));
        raceColumn.setCellValueFactory(new PropertyValueFactory<RaceModel, String>("series"));
        raceTableView.setItems(raceData);
    }

    public void addRaceDataToList(List<RaceModel> raceDateList) {
        raceData.addAll(raceDateList);
    }

    public void clearRaceData() {
        raceData.clear();
    }

    public ObservableList<RaceModel> getRaceData() {
        return raceData;
    }

    public TableView<RaceModel> getTableView() {
        return raceTableView;
    }
}
