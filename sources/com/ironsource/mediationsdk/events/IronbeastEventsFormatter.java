package com.ironsource.mediationsdk.events;

import com.ironsource.eventsmodule.EventData;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class IronbeastEventsFormatter extends AbstractEventsFormatter {
    private final String DEFAULT_IB_EVENTS_URL = "https://track.atom-data.io";
    private final String IB_KEY_DATA = "data";
    private final String IB_KEY_TABLE = "table";
    private final String IB_TABLE_NAME = "super.dwh.mediation_events";

    IronbeastEventsFormatter(int adUnit) {
        this.mAdUnit = adUnit;
    }

    public String getDefaultEventsUrl() {
        return "https://track.atom-data.io";
    }

    public String getFormatterType() {
        return "ironbeast";
    }

    public String format(ArrayList<EventData> toSend, JSONObject generalProperties) {
        JSONObject jsonBody = new JSONObject();
        if (generalProperties == null) {
            this.mGeneralProperties = new JSONObject();
        } else {
            this.mGeneralProperties = generalProperties;
        }
        try {
            JSONArray eventsArray = new JSONArray();
            if (!(toSend == null || toSend.isEmpty())) {
                Iterator it = toSend.iterator();
                while (it.hasNext()) {
                    JSONObject jsonEvent = createJSONForEvent((EventData) it.next());
                    if (jsonEvent != null) {
                        eventsArray.put(jsonEvent);
                    }
                }
            }
            jsonBody.put("table", "super.dwh.mediation_events");
            jsonBody.put("data", createDataToSend(eventsArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonBody.toString();
    }
}
