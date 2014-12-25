package be.dimi.iracing.scheduler.save;

import be.dimi.iracing.scheduler.model.RacingList;
import be.dimi.iracing.scheduler.race.RaceModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitri on 23/11/2014.
 */
public class ListSaver {

    public static void saveList() throws IOException {
        try(OutputStream os = new FileOutputStream("IracingSave.data");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(os);){

            for(RaceModel rm : RacingList.getStoredListView().getItems()){
                objectOutputStream.writeObject(rm);
            }
        }
    }

    public static void readList() throws IOException {
        List<RaceModel> raceList = new ArrayList<>();
        File f = new File("IracingSave.data");
        if(f.exists() && !f.isDirectory()) {
            try (InputStream os = new FileInputStream(f);

                 ObjectInputStream objectInputStream = new ObjectInputStream(os)) {
                try {
                    while (true) {
                        try {
                            raceList.add((RaceModel) objectInputStream.readObject());
                        } catch (EOFException e) {
                            break;
                        }
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        RacingList.addToRacingList(raceList);
    }
}
