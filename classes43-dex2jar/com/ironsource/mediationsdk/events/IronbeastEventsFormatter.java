// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.events;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.ironsource.eventsmodule.EventData;
import java.util.ArrayList;

class IronbeastEventsFormatter extends AbstractEventsFormatter
{
    private final String DEFAULT_IB_EVENTS_URL;
    private final String IB_KEY_DATA;
    private final String IB_KEY_TABLE;
    private final String IB_TABLE_NAME;
    
    IronbeastEventsFormatter(final int mAdUnit) {
        this.DEFAULT_IB_EVENTS_URL = "https://track.atom-data.io";
        this.IB_TABLE_NAME = "super.dwh.mediation_events";
        this.IB_KEY_TABLE = "table";
        this.IB_KEY_DATA = "data";
        this.mAdUnit = mAdUnit;
    }
    
    @Override
    public String format(final ArrayList<EventData> list, final JSONObject mGeneralProperties) {
        final JSONObject jsonObject = new JSONObject();
        if (mGeneralProperties == null) {
            this.mGeneralProperties = new JSONObject();
        }
        else {
            this.mGeneralProperties = mGeneralProperties;
        }
        JSONArray jsonArray = null;
        Label_0104: {
            try {
                jsonArray = new JSONArray();
                if (list != null && !list.isEmpty()) {
                    final Iterator<EventData> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        final JSONObject jsonForEvent = this.createJSONForEvent(iterator.next());
                        if (jsonForEvent != null) {
                            jsonArray.put((Object)jsonForEvent);
                        }
                    }
                }
                break Label_0104;
            }
            catch (JSONException ex) {
                ex.printStackTrace();
            }
            return jsonObject.toString();
        }
        jsonObject.put("table", (Object)"super.dwh.mediation_events");
        jsonObject.put("data", (Object)this.createDataToSend(jsonArray));
        return jsonObject.toString();
    }
    
    public String getDefaultEventsUrl() {
        return "https://track.atom-data.io";
    }
    
    @Override
    public String getFormatterType() {
        return "ironbeast";
    }
}
