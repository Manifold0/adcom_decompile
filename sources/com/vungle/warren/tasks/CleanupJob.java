package com.vungle.warren.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.Storage;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CleanupJob implements Job {
    static final String TAG = CleanupJob.class.getCanonicalName();
    private Designer designer;
    private Storage storage;

    CleanupJob(@NonNull Designer designer, @NonNull Storage storage) {
        this.designer = designer;
        this.storage = storage;
    }

    public int onRunJob(Bundle bundle, JobRunner jobRunner) {
        if (this.designer == null || this.storage == null) {
            return 1;
        }
        Log.d(TAG, "CleanupJob: Current directory snapshot");
        FileUtility.printDirectoryTree(this.designer.getCacheDirectory());
        File[] assets = this.designer.getCacheDirectory().listFiles();
        List<Advertisement> cachedAdvs = this.storage.loadAll(Advertisement.class);
        List<Placement> cachedPlacements = this.storage.loadAll(Placement.class);
        if (cachedPlacements.size() == 0) {
            return 0;
        }
        Collection<Placement> validPlacements = this.storage.loadValidPlacements();
        Set<String> validIds = new HashSet();
        for (Placement p : cachedPlacements) {
            if (validPlacements.isEmpty() || validPlacements.contains(p)) {
                for (String advertisementId : p.getAdvertisementIDs()) {
                    Advertisement advertisement = (Advertisement) this.storage.load(advertisementId, Advertisement.class);
                    if (advertisement == null) {
                        Log.w(TAG, "removing adv " + advertisementId + " from placement " + p.getId());
                        this.storage.removeAdvertisementFromPlacement(p.getId(), advertisementId);
                    } else if (advertisement.getExpireTime() > System.currentTimeMillis() || advertisement.getState() == 2) {
                        validIds.add(advertisement.getId());
                        Log.w(TAG, "setting valid adv " + advertisementId + " for placement " + p.getId());
                    } else {
                        this.storage.removeAdvertisementFromPlacement(p.getId(), advertisementId);
                        try {
                            this.designer.deleteAssets(advertisement.getId());
                        } catch (IOException e) {
                            Log.e(TAG, Log.getStackTraceString(e));
                        }
                        this.storage.delete(advertisement);
                        if (p.isAutoCached()) {
                            jobRunner.execute(DownloadJob.makeJobInfo(p.getId(), true).setDelay(1000));
                        }
                    }
                }
            } else {
                Log.d(TAG, String.format(Locale.ENGLISH, "Placement %s is no longer valid, deleting it and its advertisement", new Object[]{p.getId()}));
                this.storage.delete(p);
            }
        }
        for (Advertisement ad : cachedAdvs) {
            if (ad.getState() == 2) {
                validIds.add(ad.getId());
                Log.d(TAG, "found adv in viewing state " + ad.getId());
            } else if (!validIds.contains(ad.getId())) {
                Log.e(TAG, "delete ad " + ad.getId());
                this.storage.delete(ad);
            }
        }
        for (File f : assets) {
            if (!validIds.contains(f.getName())) {
                Log.v(TAG, String.format(Locale.ENGLISH, "Deleting assets under directory %s", new Object[]{f.getName()}));
                try {
                    FileUtility.delete(f);
                } catch (Throwable t) {
                    Log.e(TAG, "Failed to delete asset directory!", t);
                }
            }
        }
        return 0;
    }

    public static JobInfo makeJobInfo() {
        return new JobInfo(TAG).setPriority(0).setUpdateCurrent(true);
    }
}
