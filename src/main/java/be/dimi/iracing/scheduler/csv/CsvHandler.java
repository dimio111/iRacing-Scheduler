package be.dimi.iracing.scheduler.csv;

import au.com.bytecode.opencsv.CSVReader;
import be.dimi.iracing.scheduler.dropbox.Dropbox;
import be.dimi.iracing.scheduler.race.RaceModel;
import be.dimi.iracing.scheduler.race.TrackType;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;

import java.io.*;
import java.util.*;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class CsvHandler {

    public List<RaceModel> handleOnlineCsv(String fileName) {
        TrackType trackType = resolveTracktype(fileName);
        List<RaceModel> returnRaceModelList = new ArrayList<>();

        try (ByteArrayOutputStream file = new ByteArrayOutputStream()) {

            DbxEntry.File downloadedFile = Dropbox.authenticate().getFile("/"+fileName+".csv", null, file);

            CSVReader reader = new CSVReader((new InputStreamReader(new ByteArrayInputStream(file.toByteArray()))));
            List<RaceModel> myEntries2 = parseList(reader.readAll(), trackType);

            returnRaceModelList.addAll(myEntries2);

        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }

        return returnRaceModelList;
    }

    private List<RaceModel> parseList(List<String[]> entries, TrackType trackType) {
        Date now = new Date();
        Date future = getFutureDate(now);

        List<RaceModel> racingEntry = new ArrayList<>();
        entries.remove(0);
        for (String[] str : entries) {
            RaceModel raceModel = new RaceModel.Builder().date(str[0] + str[1]).series(str[2]).track(str[3]).laps(str[4]).trackType(trackType).build();

            if(!raceModel.getDate().before(now) && !raceModel.getDate().after(future)) {
                racingEntry.add(raceModel);
            }
        }
        return racingEntry;
    }

    private TrackType resolveTracktype(String filename){
        if("RoadSeries".equals(filename)){
            return TrackType.ROAD;
        }else{
            return TrackType.OVAL;
        }
    }

    private Date getFutureDate(Date now){
        Calendar future = Calendar.getInstance();
        future.setTime(now);
        future.add(Calendar.HOUR, 2);
        return future.getTime();
    }
}
