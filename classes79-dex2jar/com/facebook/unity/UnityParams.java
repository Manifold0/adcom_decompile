// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import java.util.Iterator;
import android.os.Bundle;
import android.util.Log;
import java.io.Serializable;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class UnityParams
{
    JSONObject json;
    
    public UnityParams(final String s) throws JSONException {
        this.json = new JSONObject(s);
    }
    
    public UnityParams(final Map<String, Serializable> map) {
        this.json = new JSONObject((Map)map);
    }
    
    public UnityParams(final JSONObject json) {
        this.json = json;
    }
    
    public static UnityParams parse(final String s) {
        return parse(s, "couldn't parse params: " + s);
    }
    
    public static UnityParams parse(final String s, final String s2) {
        try {
            return new UnityParams(s);
        }
        catch (JSONException ex) {
            Log.e(FB.TAG, s2);
            return null;
        }
    }
    
    public double getDouble(final String s) {
        try {
            return this.json.getDouble(s);
        }
        catch (JSONException ex) {
            Log.e(FB.TAG, "cannot get double " + s + " from " + this.toString());
            return 0.0;
        }
    }
    
    public UnityParams getParamsObject(final String s) {
        try {
            return new UnityParams(this.json.getJSONObject(s));
        }
        catch (JSONException ex) {
            Log.e(FB.TAG, "cannot get object " + s + " from " + this.toString());
            return null;
        }
    }
    
    public String getString(final String s) {
        try {
            return this.json.getString(s);
        }
        catch (JSONException ex) {
            Log.e(FB.TAG, "cannot get string " + s + " from " + this.toString());
            return "";
        }
    }
    
    public Bundle getStringParams() {
        final Bundle bundle = new Bundle();
        final Iterator keys = this.json.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            try {
                final String string = this.json.getString(s);
                if (string == null) {
                    continue;
                }
                bundle.putString(s, string);
            }
            catch (JSONException ex) {}
        }
        return bundle;
    }
    
    public boolean has(final String s) {
        return this.json.has(s) && !this.json.isNull(s);
    }
    
    public Boolean hasString(final String s) {
        return this.has(s) && this.getString(s) != "";
    }
    
    public void put(final String s, final Object o) {
        try {
            this.json.put(s, o);
        }
        catch (JSONException ex) {
            Log.e(FB.TAG, "couldn't add key " + s + " to " + this.toString());
        }
    }
    
    @Override
    public String toString() {
        return this.json.toString();
    }
}
