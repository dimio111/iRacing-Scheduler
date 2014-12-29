package be.dimi.iracing.scheduler;

import be.dimi.iracing.scheduler.controllers.BottomViewController;
import be.dimi.iracing.scheduler.manager.ControllerManager;
import be.dimi.iracing.scheduler.tasks.ClearMessage;
import be.dimi.iracing.scheduler.tasks.SetRaceListTask;
import be.dimi.iracing.scheduler.version.Version;
import be.dimi.iracing.scheduler.version.VersionHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class MainApp extends Application {
    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MainApp.class);
    private static final Version VERSION = new Version("0.2.1", "");

    private ControllerManager controllerManager = ControllerManager.getControllerManager();

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("files/log4j.properties");
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        LOG.info("Starting iRacing Scheduler " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        FXMLLoader loader = new FXMLLoader();

        loadGui(stage, loader);

        setControllerManager((MainController) loader.getController());

        initializeRaces();

        initializeLabeView();

        checkVersion();
    }

    private void loadGui(Stage stage, FXMLLoader loader) throws Exception {
        String fxmlFile = "/fxml/newest-design.fxml";
        LOG.debug("Using fxml: " + fxmlFile);

        URL location = getClass().getResource(fxmlFile);
        loader.setLocation(location);
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        LOG.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);

        stage.setTitle("iRacing Scheduler");
        stage.setScene(scene);
        stage.show();
        LOG.debug("JFX scene loaded");
    }

    private void setControllerManager(MainController mainController) {
        controllerManager.setMainController(mainController);
    }

    private void initializeRaces() {
        Thread fillListThread = new Thread(new SetRaceListTask(controllerManager.getTableViewController().getRaceData()));
        fillListThread.setDaemon(true);
        fillListThread.start();
    }

    private void initializeLabeView() {
        controllerManager.getLabelViewController().initialize(controllerManager.getTableViewController().getTableView());
    }

    private void checkVersion() {
        VersionHandler versionHandler = new VersionHandler();
        Version latestVersion = versionHandler.checkForNewVersion(VERSION);
        BottomViewController bottomViewController = controllerManager.getBottomViewController();

        bottomViewController.setMessage2(latestVersion.getMessage());

        Timer timer = new Timer();
        timer.schedule(new ClearMessage("message2"), 10000);

    }
}
