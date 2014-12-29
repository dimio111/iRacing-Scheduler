package be.dimi.iracing.scheduler.controllers;

import be.dimi.iracing.scheduler.manager.ControllerManager;
import be.dimi.iracing.scheduler.model.Clock;
import be.dimi.iracing.scheduler.race.RaceModel;
import be.dimi.iracing.scheduler.tasks.ClearMessage;
import be.dimi.iracing.scheduler.tasks.SetRaceListTask;
import be.dimi.iracing.scheduler.tasks.RacingTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.Timer;

import static be.dimi.iracing.scheduler.contants.Constants.*;

/**
 * Created by Dimitri on 28/12/2014.
 */
public class BottomViewController {

    @FXML
    private Label localTime;
    @FXML
    private Label message1;
    @FXML
    private Label message2;

    @FXML
    protected void initialize() {
        Clock.showLocalClock(localTime);
    }

    public void setMessage1(final String text) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                message1.setText(text);
            }
        });
    }

    public void setMessage2(final String text) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                message2.setText(text);
            }
        });
    }

    public String setMessage(String text) {
        if (message1.getText() != null && message1.getText().equals("")) {
            setMessage1(text);
            return MESSAGE_1;
        } else if (message2.getText() != null && message2.getText().equals("")) {
            setMessage2(text);
            return MESSAGE_2;
        } else {
            return "";
        }
    }

    public Label getMessage1() {
        return message1;
    }

    public Label getMessage2() {
        return message2;
    }

    public void refresh() {
        ControllerManager controllerManager = ControllerManager.getControllerManager();
        Thread fillListThread = new Thread(new SetRaceListTask(controllerManager.getTableViewController().getRaceData()));
        fillListThread.setDaemon(true);
        fillListThread.start();
    }

    public void alarm() {
        ControllerManager controllerManager = ControllerManager.getControllerManager();

        RaceModel race = controllerManager.getTableViewController()
                .getTableView().getSelectionModel().getSelectedItem();

        Timer timer = new Timer();
        timer.schedule(new RacingTimer(), race.getDate());

        race.setAlarmSet(true);

        String message = setMessage("Alarm set");
        timer.schedule(new ClearMessage(message), 5000);

        controllerManager.getLabelViewController().setAll(race);
    }
}
