package be.dimi.iracing.scheduler.race;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class RaceModel implements Comparable<RaceModel>, Serializable{

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYY");
    private static final DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

    private Date date;
    private String raceDay;
    private String raceHour;
    private String series;
    private String track;
    private TrackType trackType;
    private int laps;
    private boolean alarmSet;

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public static DateFormat getHourFormat() {
        return hourFormat;
    }

    public String getRaceDay() {
        return raceDay;
    }

    public String getRaceHour() {
        return raceHour;
    }

    public String getSeries() {
        return series;
    }

    public String getTrack() {
        return track;
    }

    public TrackType getTrackType() {
        return trackType;
    }

    public int getLaps() {
        return laps;
    }

    public boolean isAlarmSet() {
        return alarmSet;
    }

    public void setAlarmSet(boolean alarmSet) {
        this.alarmSet = alarmSet;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return  dateFormat.format(date) + "\t" + hourFormat.format(date)
                + "\t" +  series  + "\t" +
                track  + "\t" +
                laps
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RaceModel)) return false;

        RaceModel raceModel = (RaceModel) o;

        if (laps != raceModel.laps) return false;
        if (date != null ? !date.equals(raceModel.date) : raceModel.date != null) return false;
        if (raceDay != null ? !raceDay.equals(raceModel.raceDay) : raceModel.raceDay != null) return false;
        if (raceHour != null ? !raceHour.equals(raceModel.raceHour) : raceModel.raceHour != null) return false;
        if (series != null ? !series.equals(raceModel.series) : raceModel.series != null) return false;
        if (track != null ? !track.equals(raceModel.track) : raceModel.track != null) return false;
        if (trackType != raceModel.trackType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (raceDay != null ? raceDay.hashCode() : 0);
        result = 31 * result + (raceHour != null ? raceHour.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (track != null ? track.hashCode() : 0);
        result = 31 * result + (trackType != null ? trackType.hashCode() : 0);
        result = 31 * result + laps;
        return result;
    }

    @Override
    public int compareTo(RaceModel o) {
        return this.date.compareTo(o.date);
    }

    public static class Builder{
        private RaceModel raceModel;

        public Builder(){
            this.raceModel = new RaceModel();
        }

        public RaceModel build(){
            raceModel.raceDay = dateFormat.format(raceModel.date);
            raceModel.raceHour = hourFormat.format(raceModel.date);
            return raceModel;
        }
        public Builder date(final String str){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyHH:mm:ss");

            try {
                Date date = formatter.parse(str);
                Calendar localCalendar = Calendar.getInstance();
                localCalendar.setTime(date);
                localCalendar.add(Calendar.MILLISECOND, localCalendar.getTimeZone().getRawOffset());

                raceModel.date = localCalendar.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return this;
        }
        public Builder series(final String str){
            raceModel.series = str;
            return this;
        }
        public Builder track(final String str){
            raceModel.track = str;
            return this;
        }
        public Builder laps(final String str){
            raceModel.laps = Integer.valueOf(str);
            return this;
        }

        public Builder trackType(final TrackType trackType){
            raceModel.trackType = trackType;
            return this;
        }
    }
}
