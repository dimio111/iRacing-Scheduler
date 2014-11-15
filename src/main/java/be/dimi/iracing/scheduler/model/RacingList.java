package be.dimi.iracing.scheduler.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class RacingList {

    private ObservableList<String> list = FXCollections.observableArrayList();

    public void fillList(ListView<String> listView){
        list.add("test");
        list.add("test2");
        list.add("test3");
        listView.setItems(list);
    }

    public void refresh(ListView<String> listView){
        list.remove(0);
        listView.setItems(list);
    }

}
