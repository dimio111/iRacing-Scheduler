package be.dimi.iracing.scheduler.tasks;

import be.dimi.iracing.scheduler.manager.ControllerManager;

import java.util.TimerTask;

import static be.dimi.iracing.scheduler.contants.Constants.*;

/**
 * Created by Dimitri on 29/12/2014.
 */
public class ClearMessage extends TimerTask {

    String message;

    public ClearMessage(String message){
        this.message = message;
    }

    @Override
    public void run() {
        ControllerManager controllerManager = ControllerManager.getControllerManager();
        if(MESSAGE_1.equals(message)){
            controllerManager.getBottomViewController().setMessage1("");
        }else if(MESSAGE_2.equals(message)){
            controllerManager.getBottomViewController().setMessage2("");
        }
    }
}
