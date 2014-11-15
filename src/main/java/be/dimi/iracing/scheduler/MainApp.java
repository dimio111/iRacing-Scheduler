package be.dimi.iracing.scheduler;

import be.dimi.iracing.scheduler.csv.CsvHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/racing-app.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 650, 475);
//        scene.getStylesheets().add("/styles/styles.css");

        setMenus(scene, stage);
//        stage.setMaxHeight(600);
//        stage.setMaxWidth(400);
        stage.setResizable(false);
        stage.setTitle("Iracing time planner");
        stage.setScene(scene);
        stage.show();
    }

    private void setMenus(Scene scene, final Stage link) {
        MenuBar menuBar = new MenuBar();
        menuBar.setMinWidth(660);
        // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        menuFile.getItems().add(openItem);

        openItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                String currentDir = System.getProperty("user.dir") + File.separator;

                FileChooserBuilder fcb = FileChooserBuilder.create();
                FileChooser fc = fcb.title("Open Dialog").initialDirectory(new File(currentDir)).build();
                File selectedFile = fc.showOpenDialog(link);
                CsvHandler.handleNewCsv(selectedFile.getPath());
            }
        });

        menuBar.getMenus().addAll(menuFile);
        ((AnchorPane) scene.getRoot()).getChildren().addAll(menuBar);
    }


}
