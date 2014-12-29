package be.dimi.iracing.scheduler.version;

import au.com.bytecode.opencsv.CSVReader;
import be.dimi.iracing.scheduler.dropbox.Dropbox;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Dimitri on 29/12/2014.
 */
public class VersionHandler {

    public Version checkForNewVersion(Version currentVersion){
        Version onlineVersion= null;
        try (ByteArrayOutputStream file = new ByteArrayOutputStream()) {

            DbxEntry.File downloadedFile = Dropbox.authenticate().getFile("/Version.csv", null, file);

            CSVReader reader = new CSVReader((new InputStreamReader(new ByteArrayInputStream(file.toByteArray()))));
            onlineVersion = getVersion(reader.readAll());

        } catch (IOException | DbxException e) {
            e.printStackTrace();
        }

        if(onlineVersion != null && currentVersion.isOlder(onlineVersion)){
            return onlineVersion;
        }else{
            return currentVersion;
        }
    }

    private Version getVersion(List<String[]> entries) {
       return new Version(entries.get(1)[0], entries.get(1)[1]);
    }
}
