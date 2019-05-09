package com.unity3d.services.core.device;

import android.content.Context;
import com.unity3d.services.core.properties.SdkProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageManager {
    protected static final Map<StorageType, String> _storageFileMap = new HashMap();
    protected static final List<Storage> _storages = new ArrayList();

    public enum StorageType {
        PRIVATE,
        PUBLIC
    }

    public static boolean init(Context context) {
        if (context == null) {
            return false;
        }
        File cacheDir = context.getFilesDir();
        if (cacheDir == null) {
            return false;
        }
        addStorageLocation(StorageType.PUBLIC, cacheDir + "/" + SdkProperties.getLocalStorageFilePrefix() + "public-data.json");
        if (!setupStorage(StorageType.PUBLIC)) {
            return false;
        }
        addStorageLocation(StorageType.PRIVATE, cacheDir + "/" + SdkProperties.getLocalStorageFilePrefix() + "private-data.json");
        if (setupStorage(StorageType.PRIVATE)) {
            return true;
        }
        return false;
    }

    public static void initStorage(StorageType type) {
        Storage s;
        if (hasStorage(type)) {
            s = getStorage(type);
            if (s != null) {
                s.initStorage();
            }
        } else if (_storageFileMap.containsKey(type)) {
            s = new Storage((String) _storageFileMap.get(type), type);
            s.initStorage();
            _storages.add(s);
        }
    }

    private static boolean setupStorage(StorageType type) {
        if (!hasStorage(type)) {
            initStorage(type);
            Storage s = getStorage(type);
            if (!(s == null || s.storageFileExists())) {
                s.writeStorage();
            }
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    public static Storage getStorage(StorageType type) {
        if (_storages != null) {
            for (Storage s : _storages) {
                if (s.getType().equals(type)) {
                    return s;
                }
            }
        }
        return null;
    }

    public static boolean hasStorage(StorageType type) {
        if (_storages != null) {
            for (Storage s : _storages) {
                if (s.getType().equals(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static synchronized void addStorageLocation(StorageType type, String filename) {
        synchronized (StorageManager.class) {
            if (!_storageFileMap.containsKey(type)) {
                _storageFileMap.put(type, filename);
            }
        }
    }

    public static synchronized void removeStorage(StorageType type) {
        synchronized (StorageManager.class) {
            if (getStorage(type) != null) {
                _storages.remove(getStorage(type));
            }
            if (_storageFileMap != null) {
                _storageFileMap.remove(type);
            }
        }
    }
}
