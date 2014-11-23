package be.dimi.iracing.scheduler.model;

import be.dimi.iracing.scheduler.race.RaceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class RacingList {

    private static ObservableList<RaceModel> observableList = FXCollections.observableArrayList();
    private static ListView<RaceModel> storedListView;

    public static void fillList(ListView<RaceModel> listView){
        Collections.sort(observableList);
        listView.setItems(observableList);
        storedListView = listView;
    }

    public static void refresh(){
        filterList();
        storedListView.setItems(observableList);
    }

    public static void addToRacingList(List<RaceModel> list){
        observableList.addAll(list);
        filterList();
        Collections.sort(observableList);
        storedListView.setItems(observableList);
    }

    public static void filterList(){
        List<RaceModel> toBeRemoved = new ArrayList<>();
        Date date = new Date();
        for(RaceModel raceModel : observableList){
           if(raceModel.getDate().before(date)){
               toBeRemoved.add(raceModel);
           }
        }
        observableList.removeAll(toBeRemoved);
    }

    public static ListView<RaceModel> getStoredListView() {
        return storedListView;
    }
}
