// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.events;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import com.ironsource.eventsmodule.EventData;
import java.util.ArrayList;

class OutcomeEventsFormatter extends AbstractEventsFormatter
{
    private final String DEFAULT_OC_EVENTS_URL;
    
    OutcomeEventsFormatter(final int mAdUnit) {
        this.DEFAULT_OC_EVENTS_URL = "https://outcome.supersonicads.com/mediation/";
        this.mAdUnit = mAdUnit;
    }
    
    @Override
    public String format(final ArrayList<EventData> list, final JSONObject mGeneralProperties) {
        if (mGeneralProperties == null) {
            this.mGeneralProperties = new JSONObject();
        }
        else {
            this.mGeneralProperties = mGeneralProperties;
        }
        final JSONArray jsonArray = new JSONArray();
        if (list != null && !list.isEmpty()) {
            final Iterator<EventData> iterator = list.iterator();
            while (iterator.hasNext()) {
                final JSONObject jsonForEvent = this.createJSONForEvent(iterator.next());
                if (jsonForEvent != null) {
                    jsonArray.put((Object)jsonForEvent);
                }
            }
        }
        return this.createDataToSend(jsonArray);
    }
    
    public String getDefaultEventsUrl() {
        return "https://outcome.supersonicads.com/mediation/";
    }
    
    @Override
    public String getFormatterType() {
        return "outcome";
    }
}
