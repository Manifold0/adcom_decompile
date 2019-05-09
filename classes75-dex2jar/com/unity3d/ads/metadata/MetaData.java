// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads.metadata;

import java.util.Iterator;
import com.unity3d.services.core.device.Storage;
import com.unity3d.services.core.device.StorageEvent;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import org.json.JSONObject;
import com.unity3d.services.core.device.StorageManager;
import android.content.Context;
import com.unity3d.services.core.misc.JsonStorage;

public class MetaData extends JsonStorage
{
    private String _category;
    protected Context _context;
    
    public MetaData(final Context context) {
        this._context = context.getApplicationContext();
    }
    
    private String getActualKey(final String s) {
        String string = s;
        if (this.getCategory() != null) {
            string = this.getCategory() + "." + s;
        }
        return string;
    }
    
    public void commit() {
        if (StorageManager.init(this._context)) {
            final Storage storage = StorageManager.getStorage(StorageManager.StorageType.PUBLIC);
            if (this.getData() != null && storage != null) {
                final Iterator keys = this.getData().keys();
            Label_0122_Outer:
                while (keys.hasNext()) {
                    final String s = keys.next();
                    Object o2;
                    final Object o = o2 = this.get(s);
                    while (true) {
                        if (storage.get(s) == null) {
                            break Label_0122;
                        }
                        o2 = o;
                        if (!(storage.get(s) instanceof JSONObject)) {
                            break Label_0122;
                        }
                        o2 = o;
                        if (!(this.get(s) instanceof JSONObject)) {
                            break Label_0122;
                        }
                        try {
                            o2 = Utilities.mergeJsonObjects((JSONObject)o, (JSONObject)storage.get(s));
                            storage.set(s, o2);
                            continue Label_0122_Outer;
                        }
                        catch (Exception ex) {
                            DeviceLog.exception("Exception merging JSONs", ex);
                            o2 = o;
                            continue;
                        }
                        break;
                    }
                    break;
                }
                storage.writeStorage();
                storage.sendEvent(StorageEvent.SET, this.getData());
            }
            return;
        }
        DeviceLog.error("Unity Ads could not commit metadata due to storage error");
    }
    
    public String getCategory() {
        return this._category;
    }
    
    @Override
    public boolean set(final String s, final Object o) {
        synchronized (this) {
            this.initData();
            boolean b = false;
            if (super.set(this.getActualKey(s) + ".value", o)) {
                final boolean set = super.set(this.getActualKey(s) + ".ts", System.currentTimeMillis());
                b = b;
                if (set) {
                    b = true;
                }
            }
            return b;
        }
    }
    
    public void setCategory(final String category) {
        this._category = category;
    }
    
    protected boolean setRaw(final String s, final Object o) {
        synchronized (this) {
            this.initData();
            boolean b = false;
            if (super.set(this.getActualKey(s), o)) {
                b = true;
            }
            return b;
        }
    }
}
