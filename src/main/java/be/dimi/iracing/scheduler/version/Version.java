package be.dimi.iracing.scheduler.version;

/**
 * Created by Dimitri on 29/12/2014.
 */
public class Version {

    private String version;
    private String message;

    public Version(String version, String message) {
        this.version = version;
        this.message = message;
    }

    public String getVersion() {
        return version;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOlder(Version onlineVersion) {
        String thisVersionString = this.version.replace(".", "");
        String onlineVersionString = onlineVersion.getVersion().replace(",", "");

        Integer thisVersionInteger = Integer.valueOf(thisVersionString);
        Integer onlineVersionInteger = Integer.valueOf(onlineVersionString);

        return thisVersionInteger < onlineVersionInteger;
    }
}
