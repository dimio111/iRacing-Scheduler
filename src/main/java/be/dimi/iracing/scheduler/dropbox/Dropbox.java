package be.dimi.iracing.scheduler.dropbox;

import com.dropbox.core.*;

import java.util.Locale;

/**
 * Created by Dimitri on 26/12/2014.
 */
public class Dropbox {

    public static DbxClient authenticate() {

        DbxRequestConfig config = new DbxRequestConfig(
                "iRacing Scheduler", Locale.getDefault().toString());

        String accessToken = "OkCM_6XruG8AAAAAAAAABxUbiTedExunrrZ9m1Re2sQuZO90dOhanVs6pny7eqvx";

        return new DbxClient(config, accessToken);
    }
}
