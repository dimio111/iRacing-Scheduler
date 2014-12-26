package be.dimi.iracing.scheduler.dropbox;

import com.dropbox.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by Dimitri on 26/12/2014.
 */
public class Dropbox {

    public static DbxClient authenticate() {

        final String APP_KEY = "38c8aijekg7z8xx";
        final String APP_SECRET = "x3tivdz9jpljybh";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
                "iRacing-Schedular", Locale.getDefault().toString());
//        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
//
//        String authorizeUrl = webAuth.start();
//        String code = "WXa7TEF-PpMAAAAAAAAActBPuakSVeqgqxYD64PWymE";
//
//        DbxAuthFinish authFinish = null;
//        try {
//            authFinish = webAuth.finish(code);
//        } catch (DbxException e) {
//            e.printStackTrace();
//        }
        //String accessToken = authFinish.accessToken;
        String accessToken = "WXa7TEF-PpMAAAAAAAAAc9bnCkbTUQ9HKXSh_ZUNLoIjtDKuZ5wafQHb44MLNLv4";

        return new DbxClient(config, accessToken);
    }
}
