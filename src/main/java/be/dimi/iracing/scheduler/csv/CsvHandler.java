package be.dimi.iracing.scheduler.csv;

import au.com.bytecode.opencsv.CSVReader;
import be.dimi.iracing.scheduler.dropbox.Dropbox;
import be.dimi.iracing.scheduler.race.RaceModel;
import be.dimi.iracing.scheduler.race.TrackType;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class CsvHandler {

    public List<RaceModel> handleOnlineCsv(String fileName) {
        TrackType trackType = resolveTracktype(fileName);
        List<RaceModel> returnRaceModelList = new ArrayList<>();

        try (ByteArrayOutputStream file = new ByteArrayOutputStream()) {

            DbxEntry.File downloadedFile = Dropbox.authenticate().getFile("/" + fileName + ".csv", null, file);

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
            if (passesDate(str, now, future)) {
                racingEntry.add(new RaceModel.Builder().date(str[0] + str[1]).series(str[2]).track(str[3]).laps(str[4]).trackType(trackType).build());
            }
        }
        return racingEntry;
    }

    private TrackType resolveTracktype(String filename) {
        if ("RoadSeries".equals(filename)) {
            return TrackType.ROAD;
        } else {
            return TrackType.OVAL;
        }
    }

    private Date getFutureDate(Date now) {
        Calendar future = Calendar.getInstance();
        future.setTime(now);
        future.add(Calendar.HOUR, 2);
        return future.getTime();
    }

    private boolean passesDate(String[] str, Date now, Date future) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyHH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(str[0] + str[1]);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date!=null){
            Calendar localCalendar = Calendar.getInstance();
            localCalendar.setTime(date);
            localCalendar.add(Calendar.MILLISECOND, localCalendar.getTimeZone().getRawOffset());
            date = localCalendar.getTime();

            return date.after(now) && date.before(future);
        }else{
            return false;
        }
    }
}
