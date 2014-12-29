package be.dimi.iracing.scheduler.dropbox;

import com.dropbox.core.*;

import java.util.Locale;

/**
 * Created by Dimitri on 26/12/2014.
 */
public class Dropbox {

    public static DbxClient authenticate() {

        DbxRequestConfig config = new DbxRequestConfig(
                "iRacing Schedular", Locale.getDefault().toString());

        String accessToken = "OkCM_6XruG8AAAAAAAAABphu3zV-Y86KtSUnJkQy4kUyi-AJcZxmpQd5ECcqDYkF";

        return new DbxClient(config, accessToken);
    }
}
