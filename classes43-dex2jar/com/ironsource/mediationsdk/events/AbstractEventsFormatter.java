// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.events;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONException;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import org.json.JSONArray;
import org.json.JSONObject;

abstract class AbstractEventsFormatter
{
    private final String EVENTS_KEY_DEFAULT;
    private final String EVENTS_KEY_IS;
    private final String EVENTS_KEY_RV;
    private final String KEY_AD_UNIT;
    private final String KEY_EVENT_ID;
    private final String KEY_TIMESTAMP;
    int mAdUnit;
    JSONObject mGeneralProperties;
    private String mServerUrl;
    
    AbstractEventsFormatter() {
        this.KEY_EVENT_ID = "eventId";
        this.KEY_TIMESTAMP = "timestamp";
        this.KEY_AD_UNIT = "adUnit";
        this.EVENTS_KEY_IS = "InterstitialEvents";
        this.EVENTS_KEY_RV = "events";
        this.EVENTS_KEY_DEFAULT = "events";
    }
    
    private String getEventsKey(final int n) {
        switch (n) {
            default: {
                return "events";
            }
            case 2: {
                return "InterstitialEvents";
            }
            case 3: {
                return "events";
            }
        }
    }
    
    String createDataToSend(final JSONArray jsonArray) {
        String string = "";
        try {
            if (this.mGeneralProperties != null) {
                final JSONObject jsonObject = new JSONObject(this.mGeneralProperties.toString());
                jsonObject.put("timestamp", IronSourceUtils.getTimeStamp());
                jsonObject.put("adUnit", this.mAdUnit);
                jsonObject.put(this.getEventsKey(this.mAdUnit), (Object)jsonArray);
                string = jsonObject.toString();
            }
            return string;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    JSONObject createJSONForEvent(final EventData eventData) {
        try {
            final JSONObject jsonObject = new JSONObject(eventData.getAdditionalData());
            jsonObject.put("eventId", eventData.getEventId());
            jsonObject.put("timestamp", eventData.getTimeStamp());
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public abstract String format(final ArrayList<EventData> p0, final JSONObject p1);
    
    protected abstract String getDefaultEventsUrl();
    
    String getEventsServerUrl() {
        if (TextUtils.isEmpty((CharSequence)this.mServerUrl)) {
            return this.getDefaultEventsUrl();
        }
        return this.mServerUrl;
    }
    
    public abstract String getFormatterType();
    
    void setEventsServerUrl(final String mServerUrl) {
        this.mServerUrl = mServerUrl;
    }
}
