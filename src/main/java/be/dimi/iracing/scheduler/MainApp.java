package be.dimi.iracing.scheduler;

import be.dimi.iracing.scheduler.csv.CsvHandler;
import be.dimi.iracing.scheduler.save.ListSaver;
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

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private Stage stage = null;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        log.info("Starting iRacing Scheduler " + new SimpleDateFormat("mm/dd/yyyy").format(new Date()));

        String fxmlFile = "/fxml/racing-app.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 650, 475);
//        scene.getStylesheets().add("/styles/styles.css");

       // setMenus(scene, stage);
        stage.setMaxHeight(490);
        stage.setMaxWidth(650);
        stage.setResizable(false);
        stage.setTitle("iRacing Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    private void setMenus(Scene scene, Stage link) {
        MenuBar menuBar = new MenuBar();
        menuBar.setMinWidth(660);
        // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        //MenuItem saveItem = new MenuItem("Save");
        setFileOpenAction(openItem);
        menuFile.getItems().addAll(openItem);
        menuBar.getMenus().addAll(menuFile);
        ((AnchorPane) scene.getRoot()).getChildren().addAll(menuBar);
    }

    private void setFileOpenAction(MenuItem menuItem){
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                String currentDir = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator;

                FileChooserBuilder fcb = FileChooserBuilder.create();
                FileChooser fc = fcb.title("Open Dialog").initialDirectory(new File(currentDir)).build();
                File selectedFile = fc.showOpenDialog(stage);
                if(selectedFile != null){
                    CsvHandler.handleNewCsv(selectedFile.getPath());
                }
            }
        });
    }

    private void setFileSaveAction(MenuItem menuItem){
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                try {
                    ListSaver.saveList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
