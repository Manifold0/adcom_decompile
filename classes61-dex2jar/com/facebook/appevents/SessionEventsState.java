// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import java.util.Iterator;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import com.facebook.appevents.internal.AppEventsLoggerUtility;
import org.json.JSONArray;
import android.content.Context;
import com.facebook.GraphRequest;
import java.io.UnsupportedEncodingException;
import com.facebook.internal.Utility;
import java.util.ArrayList;
import com.facebook.internal.AttributionIdentifiers;
import java.util.List;

class SessionEventsState
{
    private final int MAX_ACCUMULATED_LOG_EVENTS;
    private List<AppEvent> accumulatedEvents;
    private String anonymousAppDeviceGUID;
    private AttributionIdentifiers attributionIdentifiers;
    private List<AppEvent> inFlightEvents;
    private int numSkippedEventsDueToFullBuffer;
    
    public SessionEventsState(final AttributionIdentifiers attributionIdentifiers, final String anonymousAppDeviceGUID) {
        this.accumulatedEvents = new ArrayList<AppEvent>();
        this.inFlightEvents = new ArrayList<AppEvent>();
        this.MAX_ACCUMULATED_LOG_EVENTS = 1000;
        this.attributionIdentifiers = attributionIdentifiers;
        this.anonymousAppDeviceGUID = anonymousAppDeviceGUID;
    }
    
    private byte[] getStringAsByteArray(final String s) {
        try {
            return s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            Utility.logd("Encoding exception: ", ex);
            return null;
        }
    }
    
    private void populateRequest(final GraphRequest graphRequest, final Context context, final int n, JSONArray string, final boolean b) {
        while (true) {
            try {
                JSONObject jsonObjectForGraphAPICall;
                final JSONObject jsonObject = jsonObjectForGraphAPICall = AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.CUSTOM_APP_EVENTS, this.attributionIdentifiers, this.anonymousAppDeviceGUID, b, context);
                if (this.numSkippedEventsDueToFullBuffer > 0) {
                    jsonObject.put("num_skipped_events", n);
                    jsonObjectForGraphAPICall = jsonObject;
                }
                graphRequest.setGraphObject(jsonObjectForGraphAPICall);
                Bundle parameters;
                if ((parameters = graphRequest.getParameters()) == null) {
                    parameters = new Bundle();
                }
                string = (JSONArray)string.toString();
                if (string != null) {
                    parameters.putByteArray("custom_events_file", this.getStringAsByteArray((String)string));
                    graphRequest.setTag(string);
                }
                graphRequest.setParameters(parameters);
            }
            catch (JSONException ex) {
                final JSONObject jsonObjectForGraphAPICall = new JSONObject();
                continue;
            }
            break;
        }
    }
    
    public void accumulatePersistedEvents(final List<AppEvent> list) {
        synchronized (this) {
            this.accumulatedEvents.addAll(list);
        }
    }
    
    public void addEvent(final AppEvent appEvent) {
        synchronized (this) {
            if (this.accumulatedEvents.size() + this.inFlightEvents.size() >= 1000) {
                ++this.numSkippedEventsDueToFullBuffer;
            }
            else {
                this.accumulatedEvents.add(appEvent);
            }
        }
    }
    
    public void clearInFlightAndStats(final boolean b) {
        // monitorenter(this)
        Label_0020: {
            if (!b) {
                break Label_0020;
            }
            try {
                this.accumulatedEvents.addAll(this.inFlightEvents);
                this.inFlightEvents.clear();
                this.numSkippedEventsDueToFullBuffer = 0;
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public int getAccumulatedEventCount() {
        synchronized (this) {
            return this.accumulatedEvents.size();
        }
    }
    
    public List<AppEvent> getEventsToPersist() {
        synchronized (this) {
            final List<AppEvent> accumulatedEvents = this.accumulatedEvents;
            this.accumulatedEvents = new ArrayList<AppEvent>();
            return accumulatedEvents;
        }
    }
    
    public int populateRequest(final GraphRequest graphRequest, final Context context, final boolean b, final boolean b2) {
        int numSkippedEventsDueToFullBuffer = 0;
        JSONArray jsonArray = null;
    Label_0051_Outer:
        while (true) {
            while (true) {
                AppEvent appEvent = null;
                Label_0112: {
                    synchronized (this) {
                        numSkippedEventsDueToFullBuffer = this.numSkippedEventsDueToFullBuffer;
                        this.inFlightEvents.addAll(this.accumulatedEvents);
                        this.accumulatedEvents.clear();
                        jsonArray = new JSONArray();
                        final Iterator<AppEvent> iterator = this.inFlightEvents.iterator();
                        while (iterator.hasNext()) {
                            appEvent = iterator.next();
                            if (!appEvent.isChecksumValid()) {
                                break Label_0112;
                            }
                            if (!b && appEvent.getIsImplicit()) {
                                continue Label_0051_Outer;
                            }
                            jsonArray.put((Object)appEvent.getJSONObject());
                        }
                        break;
                    }
                }
                Utility.logd("Event with invalid checksum: %s", appEvent.toString());
                continue;
            }
        }
        if (jsonArray.length() == 0) {
            // monitorexit(this)
            return 0;
        }
        // monitorexit(this)
        final GraphRequest graphRequest2;
        this.populateRequest(graphRequest2, context, numSkippedEventsDueToFullBuffer, jsonArray, b2);
        return jsonArray.length();
    }
}
