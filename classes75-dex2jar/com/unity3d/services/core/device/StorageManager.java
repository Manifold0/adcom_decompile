// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.device;

import java.io.File;
import com.unity3d.services.core.properties.SdkProperties;
import android.content.Context;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageManager
{
    protected static final Map<StorageType, String> _storageFileMap;
    protected static final List<Storage> _storages;
    
    static {
        _storageFileMap = new HashMap<StorageType, String>();
        _storages = new ArrayList<Storage>();
    }
    
    public static void addStorageLocation(final StorageType storageType, final String s) {
        synchronized (StorageManager.class) {
            if (!StorageManager._storageFileMap.containsKey(storageType)) {
                StorageManager._storageFileMap.put(storageType, s);
            }
        }
    }
    
    public static Storage getStorage(final StorageType storageType) {
        if (StorageManager._storages != null) {
            for (final Storage storage : StorageManager._storages) {
                if (storage.getType().equals(storageType)) {
                    return storage;
                }
            }
        }
        return null;
    }
    
    public static boolean hasStorage(final StorageType storageType) {
        if (StorageManager._storages != null) {
            final Iterator<Storage> iterator = StorageManager._storages.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getType().equals(storageType)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean init(final Context context) {
        if (context != null) {
            final File filesDir = context.getFilesDir();
            if (filesDir != null) {
                addStorageLocation(StorageType.PUBLIC, filesDir + "/" + SdkProperties.getLocalStorageFilePrefix() + "public-data.json");
                if (setupStorage(StorageType.PUBLIC)) {
                    addStorageLocation(StorageType.PRIVATE, filesDir + "/" + SdkProperties.getLocalStorageFilePrefix() + "private-data.json");
                    if (setupStorage(StorageType.PRIVATE)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static void initStorage(final StorageType storageType) {
        if (hasStorage(storageType)) {
            final Storage storage = getStorage(storageType);
            if (storage != null) {
                storage.initStorage();
            }
        }
        else if (StorageManager._storageFileMap.containsKey(storageType)) {
            final Storage storage2 = new Storage(StorageManager._storageFileMap.get(storageType), storageType);
            storage2.initStorage();
            StorageManager._storages.add(storage2);
        }
    }
    
    public static void removeStorage(final StorageType storageType) {
        synchronized (StorageManager.class) {
            if (getStorage(storageType) != null) {
                StorageManager._storages.remove(getStorage(storageType));
            }
            if (StorageManager._storageFileMap != null) {
                StorageManager._storageFileMap.remove(storageType);
            }
        }
    }
    
    private static boolean setupStorage(final StorageType storageType) {
        if (!hasStorage(storageType)) {
            initStorage(storageType);
            final Storage storage = getStorage(storageType);
            if (storage != null && !storage.storageFileExists()) {
                storage.writeStorage();
            }
            if (storage == null) {
                return false;
            }
        }
        return true;
    }
    
    public enum StorageType
    {
        PRIVATE, 
        PUBLIC;
    }
}
