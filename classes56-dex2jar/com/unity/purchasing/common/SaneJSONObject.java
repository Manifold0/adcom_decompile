// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

import org.json.JSONException;
import org.json.JSONObject;

public class SaneJSONObject extends JSONObject
{
    public JSONObject put(final String s, final double n) {
        try {
            return super.put(s, n);
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public JSONObject put(final String s, final Object o) {
        try {
            return super.put(s, o);
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    public JSONObject put(final String s, final boolean b) {
        try {
            return super.put(s, b);
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
}
