package be.dimi.iracing.scheduler.csv;

import au.com.bytecode.opencsv.CSVReader;
import be.dimi.iracing.scheduler.model.RacingList;
import be.dimi.iracing.scheduler.race.RaceModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class CsvHandler {

    public static void handleNewCsv(String path){
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            List<String[]> myEntries = reader.readAll();

            List<RaceModel> myEntries2 =  parseList(myEntries);

            RacingList.addToRacingList(myEntries2);

            System.out.println(myEntries);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<RaceModel> parseList(List<String[]> entrys){
        List<RaceModel> racingEntry = new ArrayList<RaceModel>();
        entrys.remove(0);
        for(String[] str : entrys){
            racingEntry.add(new RaceModel.Builder().date(str[0]+str[1]).series(str[2]).track(str[3]).laps(str[4]).build());
        }
        return racingEntry;
    }
}
