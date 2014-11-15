package be.dimi.iracing.scheduler.model;

import be.dimi.iracing.scheduler.race.RaceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.*;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class RacingList {

    private static ObservableList<RaceModel> observableList = FXCollections.observableArrayList();
    private static List<RaceModel> racingSet = new ArrayList<RaceModel>();
    private static ListView<RaceModel> storedListView;

    public static void fillList(ListView<RaceModel> listView){
        observableList.addAll(racingSet);
        Collections.sort(observableList);
        listView.setItems(observableList);
        storedListView = listView;
    }

    public static void refresh(ListView<RaceModel> listView){
        filterSet();
        storedListView.setItems(observableList);
    }

    public static void addToRacingList(List<RaceModel> list){
        racingSet.addAll(list);
        filterSet();
        observableList.addAll(racingSet);
        Collections.sort(observableList);
        storedListView.setItems(observableList);
    }

    public static void filterSet(){
        List<RaceModel> toBeRemoved = new ArrayList<RaceModel>();
        Date date = new Date();
        for(RaceModel raceModel : racingSet){
           if(raceModel.getDate().before(date)){
               toBeRemoved.add(raceModel);
           }
        }
        racingSet.removeAll(toBeRemoved);
    }
}
