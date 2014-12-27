package be.dimi.iracing.scheduler;

import be.dimi.iracing.scheduler.controllers.LocalController;
import be.dimi.iracing.scheduler.csv.CsvHandler;
import be.dimi.iracing.scheduler.model.Clock;
import be.dimi.iracing.scheduler.model.RacingList;
import be.dimi.iracing.scheduler.race.RaceModel;
import be.dimi.iracing.scheduler.timer.RacingTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    //The id in the fxml is localLabel, but the injection here needs to be localLabelController
    @FXML private LocalController localLabelController;
    @FXML private ListView<RaceModel> scheduleList;


    @FXML
    protected void initialize(){
        Clock.showLocalClock(localLabelController.getLocalLabel());
        //CsvHandler.handleCsv();
        CsvHandler.handleOnlineCsv();
        RacingList.fillList(scheduleList);
//        try {
//            ListSaver.readList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void refresh(){
        CsvHandler.handleOnlineCsv();
       // CsvHandler.handleCsv();
        RacingList.refresh();
    }

    public void alarm(){

        long timezoneAlteredTime = scheduleList.getSelectionModel().getSelectedItem().getDate().getTime() + Calendar.getInstance().getTimeZone().getRawOffset();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/London"));
        cal.setTimeInMillis(timezoneAlteredTime);

        Timer timer = new Timer();
        timer.schedule(new RacingTimer(), cal.getTime());

        String fxmlFile = "/fxml/popups/alarm-set.fxml";
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

    public LocalController getLocalController(){
        return localLabelController;
    }

    public void setText(String t){
        //localLabel.setText(t);
    }

}
