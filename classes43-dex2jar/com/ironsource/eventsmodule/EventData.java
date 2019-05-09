// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.eventsmodule;

import org.json.JSONException;
import org.json.JSONObject;

public class EventData
{
    private JSONObject mAdditionalData;
    private int mEventId;
    private long mTimeStamp;
    
    public EventData(final int mEventId, final long mTimeStamp, final JSONObject mAdditionalData) {
        this.mEventId = -1;
        this.mTimeStamp = -1L;
        this.mEventId = mEventId;
        this.mTimeStamp = mTimeStamp;
        if (mAdditionalData == null) {
            this.mAdditionalData = new JSONObject();
            return;
        }
        this.mAdditionalData = mAdditionalData;
    }
    
    public EventData(final int mEventId, final JSONObject mAdditionalData) {
        this.mEventId = -1;
        this.mTimeStamp = -1L;
        this.mEventId = mEventId;
        this.mTimeStamp = System.currentTimeMillis();
        if (mAdditionalData == null) {
            this.mAdditionalData = new JSONObject();
            return;
        }
        this.mAdditionalData = mAdditionalData;
    }
    
    public void addToAdditionalData(final String s, final Object o) {
        try {
            this.mAdditionalData.put(s, o);
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public String getAdditionalData() {
        return this.mAdditionalData.toString();
    }
    
    public JSONObject getAdditionalDataJSON() {
        return this.mAdditionalData;
    }
    
    public int getEventId() {
        return this.mEventId;
    }
    
    public long getTimeStamp() {
        return this.mTimeStamp;
    }
}
