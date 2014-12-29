package be.dimi.iracing.scheduler.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


/**
 * Created by Dimitri on 27/12/2014.
 */
public class LocalController{

    @FXML
    private Label localLabel;

    @FXML
    protected void initialize(){

    }

    public void setText(String t){
        localLabel.setText(t);
    }

    public Label getLocalLabel() {
        return localLabel;
    }

}
