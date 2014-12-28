package be.dimi.iracing.scheduler.controllers;

import be.dimi.iracing.scheduler.race.RaceModel;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * Created by Dimitri on 28/12/2014.
 */
public class LabelViewController {

    @FXML
    private Label laps;
    @FXML
    private Label alarmSet;
    @FXML
    private Label timeUntilRace;
    @FXML
    private Label roadType;

    public void setLaps(final String lapsText) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                laps.setText(lapsText);
            }
        });
    }

    public void setAlarmSet(final String alarmSetText) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                alarmSet.setText(alarmSetText);
            }
        });
    }

    public void setTimeUntilRace(final String timeUntilRaceText) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                timeUntilRace.setText(timeUntilRaceText);
            }
        });
    }

    public void setRoadType(final String roadTypeText) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                roadType.setText(roadTypeText);
            }
        });
    }

    private void setAll(RaceModel raceModel) {
        setLaps(String.valueOf(raceModel.getLaps()));
        if (raceModel.isAlarmSet()) {
            setAlarmSet("Yes");
        } else {
            setAlarmSet("No");
        }
        setRoadType(raceModel.getTrackType().toString());
    }

    public void initialize(TableView<RaceModel> data) {
        data.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RaceModel>() {
            @Override
            public void changed(ObservableValue observableValue, RaceModel oldValue, RaceModel newValue) {
                if (newValue != null) {
                    setAll(newValue);
                }
            }
        });
    }
}
