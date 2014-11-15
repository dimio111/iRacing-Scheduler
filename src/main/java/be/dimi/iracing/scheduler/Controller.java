package be.dimi.iracing.scheduler;

import be.dimi.iracing.scheduler.model.Clock;
import be.dimi.iracing.scheduler.model.RacingList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Controller
{
    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @FXML private Label gmtLabel;
    @FXML private ListView<String> scheduleList;
    private RacingList racingList = new RacingList();


    @FXML
    protected void initialize(){
        Clock.showClock(gmtLabel);
        racingList.fillList(scheduleList);
    }

    public void refresh(){
        racingList.refresh(scheduleList);
    }
}
