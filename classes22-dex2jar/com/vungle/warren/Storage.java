// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import com.vungle.warren.model.Cookie;
import com.vungle.warren.model.Report;
import java.io.IOException;
import java.util.Collection;
import java.io.File;
import java.util.Iterator;
import android.support.annotation.Nullable;
import android.util.Log;
import com.vungle.warren.persistence.Memorable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import android.support.annotation.NonNull;
import java.util.List;
import com.vungle.warren.model.Placement;
import com.vungle.warren.persistence.Persistor;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.model.Advertisement;
import java.util.Map;

public class Storage
{
    public static final int DB_VERSION = 1;
    private static final String TAG;
    private final Map<String, Advertisement> advMap;
    private final Designer designer;
    private final Persistor persistor;
    private final Map<String, Placement> placementsMap;
    private List<String> validPlacements;
    
    static {
        TAG = Storage.class.getSimpleName();
    }
    
    private Storage(@NonNull final Persistor persistor, @NonNull final Designer designer) {
        this.placementsMap = new ConcurrentHashMap<String, Placement>();
        this.advMap = new ConcurrentHashMap<String, Advertisement>();
        this.validPlacements = new ArrayList<String>();
        this.persistor = persistor;
        this.designer = designer;
    }
    
    static Storage makeInstance(@NonNull final Persistor persistor, @NonNull final Designer designer) {
        return new Storage(persistor, designer);
    }
    
    public void addAdvertisementToPlacement(final String s, final String s2) {
        synchronized (this) {
            final Placement placement = this.load(s, Placement.class);
            if (placement != null && !TextUtils.isEmpty((CharSequence)s2)) {
                placement.addAdvertisementID(s2);
                this.save(placement);
            }
        }
    }
    
    public void clearAllData() {
        this.designer.clearCache();
        this.persistor.clearCache();
        this.advMap.clear();
        this.placementsMap.clear();
        Log.d(Storage.TAG, "Cache cleared.");
    }
    
    public void delete(@NonNull final Memorable memorable) {
        if (memorable instanceof Placement) {
            this.placementsMap.remove(memorable.getId());
        }
        else if (memorable instanceof Advertisement) {
            this.advMap.remove(memorable.getId());
        }
        this.persistor.delete(memorable);
    }
    
    @Nullable
    public Advertisement findValidAdvertisementForPlacement(@Nullable final String s) {
        final Placement placement = this.load(s, Placement.class);
        Log.i(Storage.TAG, " Searching for valid adv for pl " + s);
        if (placement == null || placement.getAdvertisementIDs() == null || placement.getAdvertisementIDs().isEmpty()) {
            return null;
        }
        Log.i(Storage.TAG, " Searching for valid adv for pl " + s + " all ids " + placement.getAdvertisementIDs());
        final Advertisement advertisement = null;
        final Iterator<String> iterator = placement.getAdvertisementIDs().iterator();
        Advertisement advertisement2;
        while (true) {
            advertisement2 = advertisement;
            if (!iterator.hasNext()) {
                break;
            }
            advertisement2 = this.load(iterator.next(), Advertisement.class);
            if (advertisement2 == null) {
                continue;
            }
            boolean b;
            if (advertisement2.getState() == 1 || advertisement2.getState() == 0) {
                b = true;
            }
            else {
                b = false;
            }
            boolean b2;
            if (advertisement2.getExpireTime() > System.currentTimeMillis()) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            if (b2 && b) {
                break;
            }
        }
        final String tag = Storage.TAG;
        String string;
        if (advertisement2 == null) {
            string = "Didn't find valid adv";
        }
        else {
            string = "Found valid adv " + advertisement2.getId();
        }
        Log.i(tag, string);
        return advertisement2;
    }
    
    public File getAdvertisementAssetDirectory(final String s) {
        return this.designer.getAssetDirectory(s);
    }
    
    public Collection<String> getValidPlacements() {
        synchronized (this) {
            return new ArrayList<String>(this.validPlacements);
        }
    }
    
    public boolean hasValidAssets(final Advertisement advertisement) {
        return this.designer.hasAssetsFor(advertisement.getId(), advertisement.getDownloadableUrls().size());
    }
    
    public void init(final int n) {
        this.placementsMap.clear();
        this.advMap.clear();
        this.persistor.init(n, (Persistor.MigrationCallback)new Migrator());
        final List<Advertisement> all = this.persistor.extractAll(Advertisement.class);
        if (all != null && all.size() > 0) {
            for (final Advertisement advertisement : all) {
                if (advertisement != null) {
                    if (advertisement.getState() == 2) {
                        advertisement.setState(3);
                        this.save(advertisement);
                        Log.i(Storage.TAG, "Advertisement " + advertisement.getId() + " state marked as DONE, it stuck in VIEWING state");
                    }
                    else {
                        if (advertisement.getState() != 1 || this.hasValidAssets(advertisement)) {
                            continue;
                        }
                        this.delete(advertisement);
                        try {
                            this.designer.deleteAssets(advertisement.getId());
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    @Nullable
    public <T extends Memorable> T load(@NonNull final String s, @NonNull final Class<T> clazz) {
        if (Placement.class.isAssignableFrom(clazz)) {
            final Placement placement = this.placementsMap.get(s);
            Memorable copy;
            if (placement != null) {
                copy = placement.copy();
            }
            else {
                final Placement placement2 = (Placement)this.persistor.find(s, clazz);
                if ((copy = placement2) != null) {
                    this.placementsMap.put(s, placement2);
                    return (T)placement2;
                }
            }
            return (T)copy;
        }
        if (Advertisement.class.isAssignableFrom(clazz)) {
            final Advertisement advertisement = this.advMap.get(s);
            Memorable copy2;
            if (advertisement != null) {
                copy2 = advertisement.copy();
            }
            else {
                final Advertisement advertisement2 = (Advertisement)this.persistor.find(s, clazz);
                if ((copy2 = advertisement2) != null) {
                    this.advMap.put(s, advertisement2);
                    copy2 = advertisement2;
                }
            }
            return (T)copy2;
        }
        return this.persistor.find(s, clazz);
    }
    
    @NonNull
    public <T extends Memorable> List<T> loadAll(@NonNull final Class<T> clazz) {
        return this.persistor.extractAll(clazz);
    }
    
    public Collection<Placement> loadValidPlacements() {
        synchronized (this) {
            final ArrayList<Placement> list = new ArrayList<Placement>();
            final Iterator<String> iterator = this.validPlacements.iterator();
            while (iterator.hasNext()) {
                final Placement placement = this.load(iterator.next(), Placement.class);
                if (placement != null) {
                    list.add(placement.copy());
                }
            }
        }
        // monitorexit(this)
        return;
    }
    
    public void removeAdvertisementFromPlacement(final String s, final String s2) {
        synchronized (this) {
            final Placement placement = this.load(s, Placement.class);
            if (placement != null && !TextUtils.isEmpty((CharSequence)s2)) {
                placement.removeAdvertisementID(s2);
                this.save(placement);
            }
        }
    }
    
    public void save(@NonNull final Memorable memorable) {
        if (memorable instanceof Placement) {
            this.placementsMap.put(memorable.getId(), (Placement)memorable);
        }
        else if (memorable instanceof Advertisement) {
            this.advMap.put(memorable.getId(), (Advertisement)memorable);
        }
        this.persistor.save(memorable);
    }
    
    public void saveAndApplyState(@NonNull final Advertisement advertisement, @NonNull final String s, @Advertisement.State final int state) {
        Log.i(Storage.TAG, "Setting " + state + " for adv " + advertisement.getId() + " and pl " + s);
        advertisement.setState(state);
        this.save(advertisement);
        final String id = advertisement.getId();
        switch (state) {
            default: {}
            case 0:
            case 1: {
                this.addAdvertisementToPlacement(s, id);
            }
            case 2: {
                this.removeAdvertisementFromPlacement(s, id);
            }
            case 3:
            case 4: {
                this.removeAdvertisementFromPlacement(s, id);
                this.delete(advertisement);
                try {
                    this.designer.deleteAssets(id);
                    return;
                }
                catch (IOException ex) {
                    Log.e(Storage.TAG, "error on deleting assets for " + advertisement.getId(), (Throwable)ex);
                    return;
                }
                break;
            }
        }
    }
    
    public void setValidPlacements(@NonNull List<Placement> placement) {
        while (true) {
            while (true) {
                Placement placement2 = null;
                Label_0227: {
                    Label_0219: {
                        synchronized (this) {
                            this.validPlacements.clear();
                            final Iterator<Placement> iterator = ((List<Placement>)placement).iterator();
                            while (iterator.hasNext()) {
                                placement = iterator.next();
                                placement2 = this.load(placement.getId(), Placement.class);
                                if (placement2 == null || placement2.equalsIgnoreAdvertisements(placement)) {
                                    break Label_0227;
                                }
                                Log.w(Storage.TAG, "Placements data for " + placement.getId() + " is different from disc, deleting old");
                                try {
                                    for (final String s : placement.getAdvertisementIDs()) {
                                        final Advertisement advertisement = this.load(s, Advertisement.class);
                                        if (advertisement != null) {
                                            this.delete(advertisement);
                                        }
                                        this.designer.deleteAssets(s);
                                    }
                                    break Label_0219;
                                }
                                catch (IOException placement2) {
                                    Log.e("Vungle", "Failed to delete old assets, this could lead to disk space errors");
                                    Log.e("Vungle", ((Throwable)placement2).getMessage());
                                }
                                this.save(placement);
                                this.validPlacements.add(placement.getId());
                            }
                            break;
                        }
                    }
                    this.delete(placement2);
                    continue;
                }
                if (placement2 != null) {
                    placement = placement2;
                    continue;
                }
                continue;
            }
        }
    }
    // monitorexit(this)
    
    private class Migrator implements MigrationCallback
    {
        @Override
        public void onDowngrade(final int n, final int n2) {
            Storage.this.clearAllData();
        }
        
        @Override
        public void onUpgrade(final int n, final int n2) {
            if (n < 1) {
                Storage.this.persistor.migrateData(n, n2, Report.class, (Persistor.Transformation<Report>)new Transformation<Report>() {
                    public Report transform(final int n, final int n2, final byte[] array) {
                        return Report.restore(n, n2, array);
                    }
                });
                Storage.this.persistor.migrateData(n, n2, Cookie.class, (Persistor.Transformation<Cookie>)new Transformation<Cookie>() {
                    public Cookie transform(final int n, final int n2, final byte[] array) {
                        return Cookie.restore(n, n2, array);
                    }
                });
                Storage.this.persistor.migrateData(n, n2, Placement.class, (Persistor.Transformation<Placement>)new Transformation<Placement>() {
                    public Placement transform(final int n, final int n2, final byte[] array) {
                        final Placement restore = Placement.restore(n, n2, array);
                        if (restore != null) {
                            final Iterator<String> iterator = restore.getAdvertisementIDs().iterator();
                            while (iterator.hasNext()) {
                                restore.removeAdvertisementID(iterator.next());
                            }
                        }
                        return restore;
                    }
                });
                Storage.this.persistor.migrateData(n, n2, Advertisement.class, (Persistor.Transformation<Advertisement>)new Transformation<Advertisement>() {
                    public Advertisement transform(final int n, final int n2, final byte[] array) {
                        return null;
                    }
                });
            }
        }
    }
}
