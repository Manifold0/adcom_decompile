package com.ironsource.mediationsdk.events;

import com.ironsource.eventsmodule.EventData;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

class OutcomeEventsFormatter extends AbstractEventsFormatter {
    private final String DEFAULT_OC_EVENTS_URL = "https://outcome.supersonicads.com/mediation/";

    OutcomeEventsFormatter(int adUnit) {
        this.mAdUnit = adUnit;
    }

    public String getDefaultEventsUrl() {
        return "https://outcome.supersonicads.com/mediation/";
    }

    public String getFormatterType() {
        return "outcome";
    }

    public String format(ArrayList<EventData> toSend, JSONObject generalProperties) {
        if (generalProperties == null) {
            this.mGeneralProperties = new JSONObject();
        } else {
            this.mGeneralProperties = generalProperties;
        }
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
        return createDataToSend(eventsArray);
    }
}
