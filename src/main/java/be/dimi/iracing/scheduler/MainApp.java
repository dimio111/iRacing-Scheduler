package be.dimi.iracing.scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.PropertyConfigurator;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainApp extends Application {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("files/log4j.properties");
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        LOG.info("Starting iRacing Scheduler " + new SimpleDateFormat("mm/dd/yyyy").format(new Date()));
        String fxmlFile = "/fxml/racing-app.fxml";
        LOG.debug("Using fxml: " + fxmlFile);

        FXMLLoader loader = new FXMLLoader();
        URL location = getClass (  ) . getResource ( fxmlFile ) ;
        loader.setLocation(location);
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        LOG.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 650, 475);

        stage.setMaxHeight(490);
        stage.setMaxWidth(650);
        stage.setResizable(false);
        stage.setTitle("iRacing Scheduler");
        stage.setScene(scene);
        stage.show();

        LOG.debug("JFX scene loaded");
    }
}
