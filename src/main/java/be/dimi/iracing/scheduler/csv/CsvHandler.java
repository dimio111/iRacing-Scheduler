package be.dimi.iracing.scheduler.csv;

import au.com.bytecode.opencsv.CSVReader;
import be.dimi.iracing.scheduler.model.RacingList;
import be.dimi.iracing.scheduler.race.RaceModel;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class CsvHandler {

    public static void handleNewCsv(String path){
        try (CSVReader reader = new CSVReader(new FileReader(path))){
            List<RaceModel> myEntries2 =  parseList(reader.readAll());
            RacingList.addToRacingList(myEntries2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<RaceModel> parseList(List<String[]> entries){
        List<RaceModel> racingEntry = new ArrayList<RaceModel>();
        entries.remove(0);
        for(String[] str : entries){
            racingEntry.add(new RaceModel.Builder().date(str[0]+str[1]).series(str[2]).track(str[3]).laps(str[4]).build());
        }
        return racingEntry;
    }
}
