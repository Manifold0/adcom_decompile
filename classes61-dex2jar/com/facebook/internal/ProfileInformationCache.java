// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import org.json.JSONObject;
import java.util.concurrent.ConcurrentHashMap;

class ProfileInformationCache
{
    private static final ConcurrentHashMap<String, JSONObject> infoCache;
    
    static {
        infoCache = new ConcurrentHashMap<String, JSONObject>();
    }
    
    public static JSONObject getProfileInformation(final String s) {
        return ProfileInformationCache.infoCache.get(s);
    }
    
    public static void putProfileInformation(final String s, final JSONObject jsonObject) {
        ProfileInformationCache.infoCache.put(s, jsonObject);
    }
}
