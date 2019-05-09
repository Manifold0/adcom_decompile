// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class TapjoyCachedAssetData implements Serializable
{
    private long a;
    private long b;
    private String c;
    private String d;
    private String e;
    private long f;
    private String g;
    private String h;
    
    public TapjoyCachedAssetData(final String s, final String s2, final long n) {
        this(s, s2, n, System.currentTimeMillis() / 1000L);
    }
    
    public TapjoyCachedAssetData(final String assetURL, final String localFilePath, final long b, final long a) {
        this.setAssetURL(assetURL);
        this.setLocalFilePath(localFilePath);
        this.b = b;
        this.a = a;
        this.f = a + b;
    }
    
    public static TapjoyCachedAssetData fromJSONObject(final JSONObject jsonObject) {
        Serializable string = null;
        try {
            string = jsonObject.getString("assetURL");
            final Serializable s;
            string = (s = new TapjoyCachedAssetData((String)string, jsonObject.getString("localFilePath"), jsonObject.getLong("timeToLive"), jsonObject.getLong("timestamp")));
            final JSONObject jsonObject2 = jsonObject;
            final String s2 = "offerID";
            final String s3 = jsonObject2.optString(s2);
            ((TapjoyCachedAssetData)s).setOfferID(s3);
            final String s4 = (String)string;
            return (TapjoyCachedAssetData)s4;
        }
        catch (JSONException ex) {
            final Serializable s5 = null;
        }
        while (true) {
            try {
                final Serializable s = string;
                final JSONObject jsonObject2 = jsonObject;
                final String s2 = "offerID";
                final String s3 = jsonObject2.optString(s2);
                ((TapjoyCachedAssetData)s).setOfferID(s3);
                final String s4 = (String)string;
                return (TapjoyCachedAssetData)s4;
                TapjoyLog.i("TapjoyCachedAssetData", "Can not build TapjoyVideoObject -- not enough data.");
                final Serializable s5;
                return (TapjoyCachedAssetData)s5;
            }
            catch (JSONException ex2) {
                final Serializable s5 = string;
                continue;
            }
            break;
        }
    }
    
    public static TapjoyCachedAssetData fromRawJSONString(final String s) {
        try {
            return fromJSONObject(new JSONObject(s));
        }
        catch (JSONException ex) {
            TapjoyLog.i("TapjoyCachedAssetData", "Can not build TapjoyVideoObject -- error reading json string");
            return null;
        }
    }
    
    public String getAssetURL() {
        return this.c;
    }
    
    public String getLocalFilePath() {
        return this.d;
    }
    
    public String getLocalURL() {
        return this.e;
    }
    
    public String getMimeType() {
        return this.g;
    }
    
    public String getOfferId() {
        return this.h;
    }
    
    public long getTimeOfDeathInSeconds() {
        return this.f;
    }
    
    public long getTimeToLiveInSeconds() {
        return this.b;
    }
    
    public long getTimestampInSeconds() {
        return this.a;
    }
    
    public void resetTimeToLive(final long b) {
        this.b = b;
        this.f = System.currentTimeMillis() / 1000L + b;
    }
    
    public void setAssetURL(final String c) {
        this.c = c;
        this.g = TapjoyUtil.determineMimeType(c);
    }
    
    public void setLocalFilePath(final String d) {
        this.d = d;
        this.e = "file://" + d;
    }
    
    public void setOfferID(final String h) {
        this.h = h;
    }
    
    public JSONObject toJSON() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("timestamp", this.getTimestampInSeconds());
            jsonObject.put("timeToLive", this.getTimeToLiveInSeconds());
            jsonObject.put("assetURL", (Object)this.getAssetURL());
            jsonObject.put("localFilePath", (Object)this.getLocalFilePath());
            jsonObject.put("offerID", (Object)this.getOfferId());
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    public String toRawJSONString() {
        return this.toJSON().toString();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nURL=").append(this.e).append("\n");
        sb.append("AssetURL=").append(this.c).append("\n");
        sb.append("MimeType=").append(this.g).append("\n");
        sb.append("Timestamp=").append(this.getTimestampInSeconds()).append("\n");
        sb.append("TimeOfDeath=").append(this.f).append("\n");
        sb.append("TimeToLive=").append(this.b).append("\n");
        return sb.toString();
    }
}
