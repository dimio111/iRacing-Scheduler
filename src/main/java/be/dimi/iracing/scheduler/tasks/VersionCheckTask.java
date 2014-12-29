package be.dimi.iracing.scheduler.tasks;

import be.dimi.iracing.scheduler.controllers.BottomViewController;
import be.dimi.iracing.scheduler.manager.ControllerManager;
import be.dimi.iracing.scheduler.version.Version;
import be.dimi.iracing.scheduler.version.VersionHandler;

import java.util.Timer;

/**
 * Created by Dimitri on 29/12/2014.
 */
public class VersionCheckTask implements Runnable {
    private Version version;

    public VersionCheckTask(Version version) {
        this.version = version;
    }

    @Override
    public void run() {
        ControllerManager controllerManager = ControllerManager.getControllerManager();

        VersionHandler versionHandler = new VersionHandler();
        Version latestVersion = versionHandler.checkForNewVersion(version);
        BottomViewController bottomViewController = controllerManager.getBottomViewController();

        bottomViewController.setMessage2(latestVersion.getMessage());

        Timer timer = new Timer();
        timer.schedule(new ClearMessage("message2"), 10000);
    }
}
