// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

import org.json.JSONException;
import org.json.JSONObject;
import com.ironsource.environment.ConnectivityService;
import com.ironsource.sdk.utils.SDKUtils;
import android.content.Context;

public class SSASession
{
    public final String CONNECTIVITY;
    public final String SESSION_END_TIME;
    public final String SESSION_START_TIME;
    public final String SESSION_TYPE;
    private String connectivity;
    private long sessionEndTime;
    private long sessionStartTime;
    private SessionType sessionType;
    
    public SSASession(final Context context, final SessionType sessionType) {
        this.SESSION_START_TIME = "sessionStartTime";
        this.SESSION_END_TIME = "sessionEndTime";
        this.SESSION_TYPE = "sessionType";
        this.CONNECTIVITY = "connectivity";
        this.setSessionStartTime(SDKUtils.getCurrentTimeMillis());
        this.setSessionType(sessionType);
        this.setConnectivity(ConnectivityService.getConnectionType(context));
    }
    
    public SSASession(final JSONObject jsonObject) {
        this.SESSION_START_TIME = "sessionStartTime";
        this.SESSION_END_TIME = "sessionEndTime";
        this.SESSION_TYPE = "sessionType";
        this.CONNECTIVITY = "connectivity";
        try {
            jsonObject.get("sessionStartTime");
            jsonObject.get("sessionEndTime");
            jsonObject.get("sessionType");
            jsonObject.get("connectivity");
        }
        catch (JSONException ex) {}
    }
    
    public void endSession() {
        this.setSessionEndTime(SDKUtils.getCurrentTimeMillis());
    }
    
    public String getConnectivity() {
        return this.connectivity;
    }
    
    public long getSessionEndTime() {
        return this.sessionEndTime;
    }
    
    public long getSessionStartTime() {
        return this.sessionStartTime;
    }
    
    public SessionType getSessionType() {
        return this.sessionType;
    }
    
    public void setConnectivity(final String connectivity) {
        this.connectivity = connectivity;
    }
    
    public void setSessionEndTime(final long sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }
    
    public void setSessionStartTime(final long sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }
    
    public void setSessionType(final SessionType sessionType) {
        this.sessionType = sessionType;
    }
    
    public enum SessionType
    {
        backFromBG, 
        launched;
    }
}
