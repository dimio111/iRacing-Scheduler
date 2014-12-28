package be.dimi.iracing.scheduler.manager;

import be.dimi.iracing.scheduler.MainController;
import be.dimi.iracing.scheduler.controllers.BottomViewController;
import be.dimi.iracing.scheduler.controllers.LabelViewController;
import be.dimi.iracing.scheduler.controllers.TableViewController;

/**
 * Created by Dimitri on 28/12/2014.
 */
public class ControllerManager {

    private static ControllerManager controllerManager;

    private ControllerManager(){
    }

    private BottomViewController bottomViewController;
    private LabelViewController labelViewController;
    private TableViewController tableViewController;
    private MainController mainController;

    public BottomViewController getBottomViewController() {
        return bottomViewController;
    }

    public LabelViewController getLabelViewController() {
        return labelViewController;
    }

    public TableViewController getTableViewController() {
        return tableViewController;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        this.bottomViewController = mainController.getBottomViewController();
        this.labelViewController = mainController.getLabelViewController();
        this.tableViewController = mainController.getTableViewController();
        controllerManager = this;
    }

    public static ControllerManager getControllerManager(){
        return controllerManager != null ? controllerManager : new ControllerManager();
    }
}
