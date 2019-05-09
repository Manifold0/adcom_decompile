package com.unity3d.services.core.device;

import com.unity3d.services.core.device.StorageManager.StorageType;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.JsonStorage;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.io.File;
import org.json.JSONObject;

public class Storage extends JsonStorage {
    private String _targetFileName;
    private StorageType _type;

    public Storage(String targetFileName, StorageType type) {
        this._targetFileName = targetFileName;
        this._type = type;
    }

    public StorageType getType() {
        return this._type;
    }

    public synchronized boolean readStorage() {
        boolean z = false;
        synchronized (this) {
            File f = new File(this._targetFileName);
            if (Utilities.readFile(f) != null) {
                try {
                    setData(new JSONObject(Utilities.readFile(f)));
                    z = true;
                } catch (Exception e) {
                    DeviceLog.exception("Error creating storage JSON", e);
                }
            }
        }
        return z;
    }

    public synchronized boolean initStorage() {
        readStorage();
        super.initData();
        return true;
    }

    public synchronized boolean writeStorage() {
        boolean writeFile;
        File f = new File(this._targetFileName);
        if (getData() != null) {
            writeFile = Utilities.writeFile(f, getData().toString());
        } else {
            writeFile = false;
        }
        return writeFile;
    }

    public synchronized boolean clearStorage() {
        clearData();
        return new File(this._targetFileName).delete();
    }

    public synchronized boolean storageFileExists() {
        return new File(this._targetFileName).exists();
    }

    public synchronized void sendEvent(StorageEvent eventType, Object value) {
        boolean success = false;
        if (WebViewApp.getCurrentApp() != null) {
            success = WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORAGE, eventType, this._type.name(), value);
        }
        if (!success) {
            DeviceLog.debug("Couldn't send storage event to WebApp");
        }
    }
}
