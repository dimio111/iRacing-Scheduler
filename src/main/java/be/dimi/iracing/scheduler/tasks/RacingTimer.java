package be.dimi.iracing.scheduler.tasks;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class RacingTimer extends TimerTask {

    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            public void run() {
                final JDialog jDialog = new JDialog();
                String fxmlFile = "/fxml/popups/race-time.fxml";
                FXMLLoader loader = new FXMLLoader();
                Parent rootNode = null;
                try {
                    rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(rootNode, 300, 175);
                JFXPanel jfxpanel = new JFXPanel();
                jfxpanel.setScene(scene);
                jDialog.add(jfxpanel);
                jDialog.setAlwaysOnTop(true);
                jDialog.setVisible(true);
                jDialog.setSize(300,170);
                jDialog.setResizable(false);

            }
        });
    }
}
