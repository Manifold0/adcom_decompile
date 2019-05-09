// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.device;

import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.log.DeviceLog;
import org.json.JSONObject;
import com.unity3d.services.core.misc.Utilities;
import java.io.File;
import com.unity3d.services.core.misc.JsonStorage;

public class Storage extends JsonStorage
{
    private String _targetFileName;
    private StorageManager.StorageType _type;
    
    public Storage(final String targetFileName, final StorageManager.StorageType type) {
        this._targetFileName = targetFileName;
        this._type = type;
    }
    
    public boolean clearStorage() {
        synchronized (this) {
            this.clearData();
            return new File(this._targetFileName).delete();
        }
    }
    
    public StorageManager.StorageType getType() {
        return this._type;
    }
    
    public boolean initStorage() {
        synchronized (this) {
            this.readStorage();
            super.initData();
            return true;
        }
    }
    
    public boolean readStorage() {
        final boolean b = false;
        synchronized (this) {
            final File file = new File(this._targetFileName);
            final String file2 = Utilities.readFile(file);
            boolean b2 = b;
            if (file2 == null) {
                return b2;
            }
            try {
                this.setData(new JSONObject(Utilities.readFile(file)));
                b2 = true;
                return b2;
            }
            catch (Exception ex) {
                DeviceLog.exception("Error creating storage JSON", ex);
                b2 = b;
            }
        }
    }
    
    public void sendEvent(final StorageEvent storageEvent, final Object o) {
        // monitorenter(this)
        boolean sendEvent = false;
        try {
            if (WebViewApp.getCurrentApp() != null) {
                sendEvent = WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.STORAGE, storageEvent, this._type.name(), o);
            }
            if (!sendEvent) {
                DeviceLog.debug("Couldn't send storage event to WebApp");
            }
        }
        finally {
        }
        // monitorexit(this)
    }
    
    public boolean storageFileExists() {
        synchronized (this) {
            return new File(this._targetFileName).exists();
        }
    }
    
    public boolean writeStorage() {
        synchronized (this) {
            final File file = new File(this._targetFileName);
            return this.getData() != null && Utilities.writeFile(file, this.getData().toString());
        }
    }
}
