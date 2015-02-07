package be.dimi.iracing.scheduler.controllers;

import be.dimi.iracing.scheduler.race.RaceModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dimitri on 28/12/2014.
 */
public class LabelViewController {

    TableView<RaceModel> raceModelTableView;

    @FXML
    private Label laps;
    @FXML
    private Label track;
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

    public void setTrack(final String trackText) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                track.setText(trackText);
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

    public void setTimeUntilRace(final Date raceDate) {
        final DateFormat format = new SimpleDateFormat("HH:mm:ss");
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Date timeUntilRade = new Date(raceDate.getTime()-System.currentTimeMillis());
                timeUntilRace.setText(format.format(timeUntilRade));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setAll(RaceModel raceModel) {
        if(raceModelTableView.getSelectionModel().getSelectedItem().equals(raceModel)){
            setLaps(String.valueOf(raceModel.getLaps()));
            if (raceModel.isAlarmSet()) {
                setAlarmSet("Yes");
            } else {
                setAlarmSet("No");
            }
            setTrack(raceModel.getTrack());
            setRoadType(raceModel.getTrackType().toString());
            setTimeUntilRace(raceModel.getDate());
        }
    }

    public void initialize(TableView<RaceModel> data) {
        raceModelTableView = data;

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
