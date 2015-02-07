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
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Date;

/**
 * Created by Dimitri on 28/12/2014.
 */
public class LabelViewController {
    private static final PeriodFormatter dateFormat =
            new PeriodFormatterBuilder()
                    .minimumPrintedDigits(2)
                    .printZeroAlways()
                    .appendHours()
                    .minimumPrintedDigits(2)
                    .appendSeparator(":")
                    .appendMinutes()
                    .minimumPrintedDigits(2)
                    .appendSeparator(":")
                    .appendSeconds()
                    .toFormatter();

    TableView<RaceModel> raceModelTableView;

    private Timeline timeline = null;

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

    public void setRoadType(final String roadTypeText) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                roadType.setText(roadTypeText);
            }
        });
    }

    public void setTimeUntilRace(final Date raceDate) {
        final DateTime raceTime = new DateTime(raceDate.getTime());
        final Timeline newTimeLine = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Period period = new Period(new DateTime(), raceTime);
                timeUntilRace.setText(period.toString(dateFormat));
            }
        }));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        setTimeline(newTimeLine);
    }

    private void clearTimeline() {
        if (this.timeline != null) {
            this.timeline.stop();
            this.timeline = null;
        }
    }

    public void setTimeline(Timeline timeline) {
        clearTimeline();
        this.timeline = timeline;
        timeline.play();
    }

    public void setAll(RaceModel raceModel) {
        if (raceModelTableView.getSelectionModel().getSelectedItem().equals(raceModel)) {
            setTimeUntilRace(raceModel.getDate());
            setLaps(String.valueOf(raceModel.getLaps()));
            if (raceModel.isAlarmSet()) {
                setAlarmSet("Yes");
            } else {
                setAlarmSet("No");
            }
            setTrack(raceModel.getTrack());
            setRoadType(raceModel.getTrackType().toString());
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
