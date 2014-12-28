package be.dimi.iracing.scheduler.controllers;

import be.dimi.iracing.scheduler.model.Clock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    public void setMessage1(final String text){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                message1.setText(text);
            }
        });
    }

    public void setMessage2(final String text){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                message2.setText(text);
            }
        });
    }

    public void refresh(){

    }

    public void alarm(){

    }
}
