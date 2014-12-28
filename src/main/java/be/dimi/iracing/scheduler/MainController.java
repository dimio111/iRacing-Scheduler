package be.dimi.iracing.scheduler;

import be.dimi.iracing.scheduler.controllers.BottomViewController;
import be.dimi.iracing.scheduler.controllers.LabelViewController;
import be.dimi.iracing.scheduler.controllers.TableViewController;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    //The id in the fxml is localLabel, but the injection here needs to be localLabelController
    @FXML private BottomViewController bottomViewController;
    @FXML private LabelViewController labelViewController;
    @FXML private TableViewController tableViewController;

    public BottomViewController getBottomViewController() {
        return bottomViewController;
    }

    public LabelViewController getLabelViewController() {
        return labelViewController;
    }

    public TableViewController getTableViewController() {
        return tableViewController;
    }


    public void alarm(){
//        long timezoneAlteredTime = scheduleList.getSelectionModel().getSelectedItem().getDate().getTime() + Calendar.getInstance().getTimeZone().getRawOffset();
//        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/London"));
//        cal.setTimeInMillis(timezoneAlteredTime);
//
//        Timer timer = new Timer();
//        timer.schedule(new RacingTimer(), cal.getTime());
//
//        String fxmlFile = "/fxml/popups/alarm-set.fxml";
//        FXMLLoader loader = new FXMLLoader();
//        Parent rootNode = null;
//        try {
//            rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        log.debug("Showing JFX scene");
//        Scene scene = new Scene(rootNode, 350, 170);
//        Stage stage = new Stage();
//
//        stage.setResizable(false);
//        stage.setScene(scene);
//        stage.show();
    }

}
