package be.dimi.iracing.scheduler.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class Clock {

    public static void showClock(final Label gmtLabel){
        final DateFormat format = new SimpleDateFormat("HH:mm.ss");
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Calendar cal = Calendar.getInstance();
                gmtLabel.setText(format.format(cal.getTime()));
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
