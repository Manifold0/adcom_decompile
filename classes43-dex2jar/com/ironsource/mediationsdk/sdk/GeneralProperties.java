// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public class GeneralProperties
{
    public static final String ALLOW_LOCATION_SHARED_PREFS_KEY = "GeneralProperties.ALLOW_LOCATION_SHARED_PREFS_KEY";
    public static final String USER_ID_TYPE = "userIdType";
    private static GeneralProperties mInstance;
    private JSONObject mProperties;
    
    private GeneralProperties() {
        this.mProperties = new JSONObject();
    }
    
    public static GeneralProperties getProperties() {
        synchronized (GeneralProperties.class) {
            if (GeneralProperties.mInstance == null) {
                GeneralProperties.mInstance = new GeneralProperties();
            }
            return GeneralProperties.mInstance;
        }
    }
    
    public String get(String optString) {
        synchronized (this) {
            optString = this.mProperties.optString(optString);
            return optString;
        }
    }
    
    public void putKey(final String s, final Object o) {
        synchronized (this) {
            try {
                this.mProperties.put(s, o);
            }
            catch (Exception ex) {}
        }
    }
    
    public void putKeys(final Map<String, Object> map) {
        // monitorenter(this)
        if (map != null) {
            try {
                for (final String s : map.keySet()) {
                    this.putKey(s, map.get(s));
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    // monitorexit(this)
    
    public JSONObject toJSON() {
        synchronized (this) {
            return this.mProperties;
        }
    }
}
