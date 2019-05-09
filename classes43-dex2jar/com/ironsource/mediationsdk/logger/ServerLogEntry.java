// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

import org.json.JSONException;
import org.json.JSONObject;

class ServerLogEntry
{
    private int mLogLevel;
    private String mMessage;
    private IronSourceLogger.IronSourceTag mTag;
    private String mTimetamp;
    
    public ServerLogEntry(final IronSourceLogger.IronSourceTag mTag, final String mTimetamp, final String mMessage, final int mLogLevel) {
        this.mTag = mTag;
        this.mTimetamp = mTimetamp;
        this.mMessage = mMessage;
        this.mLogLevel = mLogLevel;
    }
    
    public int getLogLevel() {
        return this.mLogLevel;
    }
    
    public JSONObject toJSON() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("timestamp", (Object)this.mTimetamp);
            jsonObject.put("tag", (Object)this.mTag);
            jsonObject.put("level", this.mLogLevel);
            jsonObject.put("message", (Object)this.mMessage);
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
}
