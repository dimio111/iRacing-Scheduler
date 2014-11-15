package be.dimi.iracing.scheduler;

import be.dimi.iracing.scheduler.model.Clock;
import be.dimi.iracing.scheduler.model.RacingList;
import be.dimi.iracing.scheduler.race.RaceModel;
import be.dimi.iracing.scheduler.timer.RacingTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

public class Controller
{
    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @FXML private Label gmtLabel;
    @FXML private Label localLabel;
    @FXML private ListView<RaceModel> scheduleList;

    @FXML
    protected void initialize(){
        Clock.showClock(gmtLabel);
        Clock.showClock(localLabel);
        RacingList.fillList(scheduleList);
    }

    public void refresh(){
        RacingList.refresh(scheduleList);
    }

    public void alarm(){
        Date date = scheduleList.getSelectionModel().getSelectedItem().getDate();

        //Now create the time and schedule it
        Timer timer = new Timer();

        //Use this if you want to execute it once
        timer.schedule(new RacingTimer(), date);

        String fxmlFile = "/fxml/alarm-set.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = null;
        try {
            rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 350, 170);
        Stage stage = new Stage();

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public ListView<RaceModel> getListView(){
       return scheduleList;
    }

}
