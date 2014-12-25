package be.dimi.iracing.scheduler.csv;

import au.com.bytecode.opencsv.CSVReader;
import be.dimi.iracing.scheduler.model.RacingList;
import be.dimi.iracing.scheduler.race.RaceModel;
import be.dimi.iracing.scheduler.race.TrackType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class CsvHandler {

    public static void handleCsv() {
        File csvFileFolder = new File("files");
csvFileFolder.mkdir();
        if (csvFileFolder.isDirectory() && csvFileFolder.listFiles() != null) {
            for (File csvFile : csvFileFolder.listFiles()) {
                if(csvFile.getName().startsWith("OvalSeries") || csvFile.getName().startsWith("RoadSeries")){
                    TrackType trackType = csvFile.getName().startsWith("OvalSeries") ? TrackType.OVAL : TrackType.ROAD;
                    try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
                        List<RaceModel> myEntries2 = parseList(reader.readAll(), trackType);
                        RacingList.addToRacingList(myEntries2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static List<RaceModel> parseList(List<String[]> entries, TrackType trackType) {
        List<RaceModel> racingEntry = new ArrayList<RaceModel>();
        entries.remove(0);
        for (String[] str : entries) {
            racingEntry.add(new RaceModel.Builder().date(str[0] + str[1]).series(str[2]).track(str[3]).laps(str[4]).trackType(trackType).build());
        }
        return racingEntry;
    }
}
