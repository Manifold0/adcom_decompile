package com.ironsource.mediationsdk.events;

import android.text.TextUtils;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

abstract class AbstractEventsFormatter {
    private final String EVENTS_KEY_DEFAULT = EventEntry.TABLE_NAME;
    private final String EVENTS_KEY_IS = "InterstitialEvents";
    private final String EVENTS_KEY_RV = EventEntry.TABLE_NAME;
    private final String KEY_AD_UNIT = "adUnit";
    private final String KEY_EVENT_ID = "eventId";
    private final String KEY_TIMESTAMP = "timestamp";
    int mAdUnit;
    JSONObject mGeneralProperties;
    private String mServerUrl;

    public abstract String format(ArrayList<EventData> arrayList, JSONObject jSONObject);

    protected abstract String getDefaultEventsUrl();

    public abstract String getFormatterType();

    AbstractEventsFormatter() {
    }

    private String getEventsKey(int adUnit) {
        switch (adUnit) {
            case 2:
                return "InterstitialEvents";
            case 3:
                return EventEntry.TABLE_NAME;
            default:
                return EventEntry.TABLE_NAME;
        }
    }

    JSONObject createJSONForEvent(EventData event) {
        try {
            JSONObject jsonEvent = new JSONObject(event.getAdditionalData());
            jsonEvent.put("eventId", event.getEventId());
            jsonEvent.put("timestamp", event.getTimeStamp());
            return jsonEvent;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    String createDataToSend(JSONArray eventsArray) {
        String result = "";
        try {
            if (this.mGeneralProperties != null) {
                JSONObject data = new JSONObject(this.mGeneralProperties.toString());
                data.put("timestamp", IronSourceUtils.getTimeStamp());
                data.put("adUnit", this.mAdUnit);
                data.put(getEventsKey(this.mAdUnit), eventsArray);
                result = data.toString();
            }
        } catch (Exception e) {
        }
        return result;
    }

    String getEventsServerUrl() {
        return TextUtils.isEmpty(this.mServerUrl) ? getDefaultEventsUrl() : this.mServerUrl;
    }

    void setEventsServerUrl(String url) {
        this.mServerUrl = url;
    }
}
