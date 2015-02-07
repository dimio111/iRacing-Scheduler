package be.dimi.iracing.scheduler.tasks;

import be.dimi.iracing.scheduler.csv.CsvHandler;
import be.dimi.iracing.scheduler.manager.ControllerManager;
import be.dimi.iracing.scheduler.race.RaceModel;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Dimitri on 29/12/2014.
 */
public class SetRaceTableTask implements Runnable{
    private ObservableList<RaceModel> raceData;

    public SetRaceTableTask(ObservableList<RaceModel> raceData) {
        this.raceData = raceData;
    }

    @Override
    public void run() {
        ControllerManager controllerManager = ControllerManager.getControllerManager();
        String message = controllerManager.getBottomViewController().setMessage("Loading races");

        CsvHandler csvHandler = new CsvHandler();
        List<RaceModel> roadSeries = csvHandler.handleOnlineCsv("RoadSeries");
        List<RaceModel> ovalSeries = csvHandler.handleOnlineCsv("OvalSeries");
        if(roadSeries != null || ovalSeries != null){
            raceData.clear();

            if(roadSeries != null){
                raceData.addAll(roadSeries);
            }
            if(ovalSeries != null){
                raceData.addAll(ovalSeries);
            }
        }
        new ClearMessage(message).run();
    }
}
