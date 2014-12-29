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
}
