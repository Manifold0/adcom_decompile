package com.unity3d.ads.metadata;

import android.content.Context;
import com.unity3d.services.core.device.Storage;
import com.unity3d.services.core.device.StorageEvent;
import com.unity3d.services.core.device.StorageManager;
import com.unity3d.services.core.device.StorageManager.StorageType;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.JsonStorage;
import com.unity3d.services.core.misc.Utilities;
import java.util.Iterator;
import org.json.JSONObject;

public class MetaData extends JsonStorage {
    private String _category;
    protected Context _context;

    public MetaData(Context context) {
        this._context = context.getApplicationContext();
    }

    public void setCategory(String category) {
        this._category = category;
    }

    public String getCategory() {
        return this._category;
    }

    public synchronized boolean set(String key, Object value) {
        boolean success;
        initData();
        success = false;
        if (super.set(getActualKey(key) + ".value", value) && super.set(getActualKey(key) + ".ts", Long.valueOf(System.currentTimeMillis()))) {
            success = true;
        }
        return success;
    }

    protected synchronized boolean setRaw(String key, Object value) {
        boolean success;
        initData();
        success = false;
        if (super.set(getActualKey(key), value)) {
            success = true;
        }
        return success;
    }

    public void commit() {
        if (StorageManager.init(this._context)) {
            Storage storage = StorageManager.getStorage(StorageType.PUBLIC);
            if (getData() != null && storage != null) {
                Iterator<String> keys = getData().keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    Object value = get(key);
                    if (storage.get(key) != null && (storage.get(key) instanceof JSONObject) && (get(key) instanceof JSONObject)) {
                        try {
                            value = Utilities.mergeJsonObjects((JSONObject) value, (JSONObject) storage.get(key));
                        } catch (Exception e) {
                            DeviceLog.exception("Exception merging JSONs", e);
                        }
                    }
                    storage.set(key, value);
                }
                storage.writeStorage();
                storage.sendEvent(StorageEvent.SET, getData());
                return;
            }
            return;
        }
        DeviceLog.error("Unity Ads could not commit metadata due to storage error");
    }

    private String getActualKey(String key) {
        String finalKey = key;
        if (getCategory() != null) {
            return getCategory() + "." + key;
        }
        return finalKey;
    }
}
