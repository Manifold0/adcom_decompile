package com.vungle.warren;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Advertisement.State;
import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.Placement;
import com.vungle.warren.model.Report;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.Persistor;
import com.vungle.warren.persistence.Persistor.MigrationCallback;
import com.vungle.warren.persistence.Persistor.Transformation;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    public static final int DB_VERSION = 1;
    private static final String TAG = Storage.class.getSimpleName();
    private final Map<String, Advertisement> advMap = new ConcurrentHashMap();
    private final Designer designer;
    private final Persistor persistor;
    private final Map<String, Placement> placementsMap = new ConcurrentHashMap();
    private List<String> validPlacements = new ArrayList();

    private class Migrator implements MigrationCallback {

        /* renamed from: com.vungle.warren.Storage$Migrator$1 */
        class C01191 implements Transformation<Report> {
            C01191() {
            }

            public Report transform(int oldVersion, int newVersion, byte[] data) {
                return Report.restore(oldVersion, newVersion, data);
            }
        }

        /* renamed from: com.vungle.warren.Storage$Migrator$2 */
        class C01202 implements Transformation<Cookie> {
            C01202() {
            }

            public Cookie transform(int oldVersion, int newVersion, byte[] data) {
                return Cookie.restore(oldVersion, newVersion, data);
            }
        }

        /* renamed from: com.vungle.warren.Storage$Migrator$3 */
        class C01213 implements Transformation<Placement> {
            C01213() {
            }

            public Placement transform(int oldVersion, int newVersion, byte[] data) {
                Placement placement = Placement.restore(oldVersion, newVersion, data);
                if (placement != null) {
                    for (String advId : placement.getAdvertisementIDs()) {
                        placement.removeAdvertisementID(advId);
                    }
                }
                return placement;
            }
        }

        /* renamed from: com.vungle.warren.Storage$Migrator$4 */
        class C01224 implements Transformation<Advertisement> {
            C01224() {
            }

            public Advertisement transform(int oldVersion, int newVersion, byte[] data) {
                return null;
            }
        }

        private Migrator() {
        }

        public void onUpgrade(int oldVersion, int newVersion) {
            if (oldVersion < 1) {
                Storage.this.persistor.migrateData(oldVersion, newVersion, Report.class, new C01191());
                Storage.this.persistor.migrateData(oldVersion, newVersion, Cookie.class, new C01202());
                Storage.this.persistor.migrateData(oldVersion, newVersion, Placement.class, new C01213());
                Storage.this.persistor.migrateData(oldVersion, newVersion, Advertisement.class, new C01224());
            }
        }

        public void onDowngrade(int oldVersion, int newVersion) {
            Storage.this.clearAllData();
        }
    }

    private Storage(@NonNull Persistor persistor, @NonNull Designer designer) {
        this.persistor = persistor;
        this.designer = designer;
    }

    static Storage makeInstance(@NonNull Persistor persistor, @NonNull Designer designer) {
        return new Storage(persistor, designer);
    }

    public void init(int version) {
        this.placementsMap.clear();
        this.advMap.clear();
        this.persistor.init(version, new Migrator());
        List<Advertisement> advs = this.persistor.extractAll(Advertisement.class);
        if (advs != null && advs.size() > 0) {
            for (Advertisement adv : advs) {
                if (adv != null) {
                    if (adv.getState() == 2) {
                        adv.setState(3);
                        save(adv);
                        Log.i(TAG, "Advertisement " + adv.getId() + " state marked as DONE, it stuck in VIEWING state");
                    } else if (adv.getState() == 1 && !hasValidAssets(adv)) {
                        delete(adv);
                        try {
                            this.designer.deleteAssets(adv.getId());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void save(@NonNull Memorable memorable) {
        if (memorable instanceof Placement) {
            this.placementsMap.put(memorable.getId(), (Placement) memorable);
        } else if (memorable instanceof Advertisement) {
            this.advMap.put(memorable.getId(), (Advertisement) memorable);
        }
        this.persistor.save(memorable);
    }

    @Nullable
    public <T extends Memorable> T load(@NonNull String id, @NonNull Class<T> clazz) {
        if (Placement.class.isAssignableFrom(clazz)) {
            Placement placement = (Placement) this.placementsMap.get(id);
            if (placement != null) {
                return placement.copy();
            }
            placement = (Placement) this.persistor.find(id, clazz);
            if (placement == null) {
                return placement;
            }
            this.placementsMap.put(id, placement);
            return placement;
        } else if (!Advertisement.class.isAssignableFrom(clazz)) {
            return this.persistor.find(id, clazz);
        } else {
            Advertisement advertisement = (Advertisement) this.advMap.get(id);
            if (advertisement != null) {
                advertisement = advertisement.copy();
            } else {
                advertisement = (Advertisement) this.persistor.find(id, clazz);
                if (advertisement != null) {
                    this.advMap.put(id, advertisement);
                }
            }
            return advertisement;
        }
    }

    @NonNull
    public <T extends Memorable> List<T> loadAll(@NonNull Class<T> clazz) {
        return this.persistor.extractAll(clazz);
    }

    public void delete(@NonNull Memorable memorable) {
        if (memorable instanceof Placement) {
            this.placementsMap.remove(memorable.getId());
        } else if (memorable instanceof Advertisement) {
            this.advMap.remove(memorable.getId());
        }
        this.persistor.delete(memorable);
    }

    @Nullable
    public Advertisement findValidAdvertisementForPlacement(@Nullable String placementId) {
        Placement placement = (Placement) load(placementId, Placement.class);
        Log.i(TAG, " Searching for valid adv for pl " + placementId);
        if (placement == null || placement.getAdvertisementIDs() == null || placement.getAdvertisementIDs().isEmpty()) {
            return null;
        }
        Log.i(TAG, " Searching for valid adv for pl " + placementId + " all ids " + placement.getAdvertisementIDs());
        Advertisement result = null;
        for (String id : placement.getAdvertisementIDs()) {
            Advertisement advertisement = (Advertisement) load(id, Advertisement.class);
            if (advertisement != null) {
                boolean isNotExpired;
                boolean stateMatches;
                if (advertisement.getState() == 1 || advertisement.getState() == 0) {
                    stateMatches = true;
                } else {
                    stateMatches = false;
                }
                if (advertisement.getExpireTime() > System.currentTimeMillis()) {
                    isNotExpired = true;
                } else {
                    isNotExpired = false;
                }
                if (isNotExpired && stateMatches) {
                    result = advertisement;
                    break;
                }
            }
        }
        Log.i(TAG, result == null ? "Didn't find valid adv" : "Found valid adv " + result.getId());
        return result;
    }

    public boolean hasValidAssets(Advertisement advertisement) {
        return this.designer.hasAssetsFor(advertisement.getId(), advertisement.getDownloadableUrls().size());
    }

    public synchronized void setValidPlacements(@NonNull List<Placement> placements) {
        this.validPlacements.clear();
        for (Placement placement : placements) {
            Placement placement2;
            Placement disk = (Placement) load(placement2.getId(), Placement.class);
            if (disk != null && !disk.equalsIgnoreAdvertisements(placement2)) {
                Log.w(TAG, "Placements data for " + placement2.getId() + " is different from disc, deleting old");
                try {
                    for (String id : placement2.getAdvertisementIDs()) {
                        Advertisement advertisement = (Advertisement) load(id, Advertisement.class);
                        if (advertisement != null) {
                            delete(advertisement);
                        }
                        this.designer.deleteAssets(id);
                    }
                    delete(disk);
                } catch (IOException e) {
                    Log.e("Vungle", "Failed to delete old assets, this could lead to disk space errors");
                    Log.e("Vungle", e.getMessage());
                }
            } else if (disk != null) {
                placement2 = disk;
            }
            save(placement2);
            this.validPlacements.add(placement2.getId());
        }
    }

    public void saveAndApplyState(@NonNull Advertisement advertisement, @NonNull String placementId, @State int state) {
        Log.i(TAG, "Setting " + state + " for adv " + advertisement.getId() + " and pl " + placementId);
        advertisement.setState(state);
        save(advertisement);
        String advertisementId = advertisement.getId();
        switch (state) {
            case 0:
            case 1:
                addAdvertisementToPlacement(placementId, advertisementId);
                return;
            case 2:
                removeAdvertisementFromPlacement(placementId, advertisementId);
                return;
            case 3:
            case 4:
                removeAdvertisementFromPlacement(placementId, advertisementId);
                delete(advertisement);
                try {
                    this.designer.deleteAssets(advertisementId);
                    return;
                } catch (IOException ex) {
                    Log.e(TAG, "error on deleting assets for " + advertisement.getId(), ex);
                    return;
                }
            default:
                return;
        }
    }

    public void clearAllData() {
        this.designer.clearCache();
        this.persistor.clearCache();
        this.advMap.clear();
        this.placementsMap.clear();
        Log.d(TAG, "Cache cleared.");
    }

    public synchronized Collection<Placement> loadValidPlacements() {
        List<Placement> placements;
        placements = new ArrayList();
        for (String id : this.validPlacements) {
            Placement placement = (Placement) load(id, Placement.class);
            if (placement != null) {
                placements.add(placement.copy());
            }
        }
        return placements;
    }

    public synchronized Collection<String> getValidPlacements() {
        return new ArrayList(this.validPlacements);
    }

    public File getAdvertisementAssetDirectory(String id) {
        return this.designer.getAssetDirectory(id);
    }

    public synchronized void removeAdvertisementFromPlacement(String plId, String advId) {
        Placement placement = (Placement) load(plId, Placement.class);
        if (!(placement == null || TextUtils.isEmpty(advId))) {
            placement.removeAdvertisementID(advId);
            save(placement);
        }
    }

    public synchronized void addAdvertisementToPlacement(String plId, String advId) {
        Placement placement = (Placement) load(plId, Placement.class);
        if (!(placement == null || TextUtils.isEmpty(advId))) {
            placement.addAdvertisementID(advId);
            save(placement);
        }
    }
}
