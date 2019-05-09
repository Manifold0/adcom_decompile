package com.tapjoy;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class TapjoyCachedAssetData implements Serializable {
    /* renamed from: a */
    private long f7021a;
    /* renamed from: b */
    private long f7022b;
    /* renamed from: c */
    private String f7023c;
    /* renamed from: d */
    private String f7024d;
    /* renamed from: e */
    private String f7025e;
    /* renamed from: f */
    private long f7026f;
    /* renamed from: g */
    private String f7027g;
    /* renamed from: h */
    private String f7028h;

    public TapjoyCachedAssetData(String assetURL, String localFilePath, long timeToLiveInSeconds) {
        this(assetURL, localFilePath, timeToLiveInSeconds, System.currentTimeMillis() / 1000);
    }

    public TapjoyCachedAssetData(String assetURL, String localFilePath, long timeToLiveInSeconds, long timestampInSeconds) {
        setAssetURL(assetURL);
        setLocalFilePath(localFilePath);
        this.f7022b = timeToLiveInSeconds;
        this.f7021a = timestampInSeconds;
        this.f7026f = timestampInSeconds + timeToLiveInSeconds;
    }

    public void setAssetURL(String assetURL) {
        this.f7023c = assetURL;
        this.f7027g = TapjoyUtil.determineMimeType(assetURL);
    }

    public void setLocalFilePath(String localFilePath) {
        this.f7024d = localFilePath;
        this.f7025e = "file://" + localFilePath;
    }

    public void resetTimeToLive(long timeToLiveInSeconds) {
        this.f7022b = timeToLiveInSeconds;
        this.f7026f = (System.currentTimeMillis() / 1000) + timeToLiveInSeconds;
    }

    public void setOfferID(String offerID) {
        this.f7028h = offerID;
    }

    public long getTimestampInSeconds() {
        return this.f7021a;
    }

    public long getTimeToLiveInSeconds() {
        return this.f7022b;
    }

    public long getTimeOfDeathInSeconds() {
        return this.f7026f;
    }

    public String getAssetURL() {
        return this.f7023c;
    }

    public String getLocalFilePath() {
        return this.f7024d;
    }

    public String getLocalURL() {
        return this.f7025e;
    }

    public String getMimeType() {
        return this.f7027g;
    }

    public String getOfferId() {
        return this.f7028h;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", getTimestampInSeconds());
            jSONObject.put(TapjoyConstants.TJC_TIME_TO_LIVE, getTimeToLiveInSeconds());
            jSONObject.put("assetURL", getAssetURL());
            jSONObject.put("localFilePath", getLocalFilePath());
            jSONObject.put("offerID", getOfferId());
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public String toRawJSONString() {
        return toJSON().toString();
    }

    public static TapjoyCachedAssetData fromRawJSONString(String jsonRep) {
        try {
            return fromJSONObject(new JSONObject(jsonRep));
        } catch (JSONException e) {
            TapjoyLog.m7129i("TapjoyCachedAssetData", "Can not build TapjoyVideoObject -- error reading json string");
            return null;
        }
    }

    public static TapjoyCachedAssetData fromJSONObject(JSONObject data) {
        TapjoyCachedAssetData tapjoyCachedAssetData;
        try {
            tapjoyCachedAssetData = new TapjoyCachedAssetData(data.getString("assetURL"), data.getString("localFilePath"), data.getLong(TapjoyConstants.TJC_TIME_TO_LIVE), data.getLong("timestamp"));
            try {
                tapjoyCachedAssetData.setOfferID(data.optString("offerID"));
            } catch (JSONException e) {
                TapjoyLog.m7129i("TapjoyCachedAssetData", "Can not build TapjoyVideoObject -- not enough data.");
                return tapjoyCachedAssetData;
            }
        } catch (JSONException e2) {
            tapjoyCachedAssetData = null;
            TapjoyLog.m7129i("TapjoyCachedAssetData", "Can not build TapjoyVideoObject -- not enough data.");
            return tapjoyCachedAssetData;
        }
        return tapjoyCachedAssetData;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nURL=").append(this.f7025e).append("\n");
        stringBuilder.append("AssetURL=").append(this.f7023c).append("\n");
        stringBuilder.append("MimeType=").append(this.f7027g).append("\n");
        stringBuilder.append("Timestamp=").append(getTimestampInSeconds()).append("\n");
        stringBuilder.append("TimeOfDeath=").append(this.f7026f).append("\n");
        stringBuilder.append("TimeToLive=").append(this.f7022b).append("\n");
        return stringBuilder.toString();
    }
}
