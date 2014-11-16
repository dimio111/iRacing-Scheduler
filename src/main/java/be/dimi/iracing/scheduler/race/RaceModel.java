package be.dimi.iracing.scheduler.race;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dimitri on 15/11/2014.
 */
public class RaceModel implements Comparable<RaceModel>{

    private Date date;
    private String series;
    private String track;
    private int laps;

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYY");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

        return  dateFormat.format(date) + "\t" + hourFormat.format(date)
                + "\t" +  series  + "\t" +
                track  + "\t" +
                laps
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceModel raceModel = (RaceModel) o;
        if (!date.equals(raceModel.date)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result;
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
            return raceModel;
        }
        public Builder date(final String str){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyHH:mm:ss");
            try {
                raceModel.date = formatter.parse(str);
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
    }
}
