// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

import org.json.JSONObject;

public class ServerSegmetData
{
    private JSONObject mCustomSegments;
    private String mSegmentId;
    private String mSegmentName;
    
    public ServerSegmetData(final String mSegmentName, final String mSegmentId, final JSONObject mCustomSegments) {
        this.mSegmentName = mSegmentName;
        this.mSegmentId = mSegmentId;
        this.mCustomSegments = mCustomSegments;
    }
    
    public JSONObject getCustomSegments() {
        return this.mCustomSegments;
    }
    
    public String getSegmentId() {
        return this.mSegmentId;
    }
    
    public String getSegmentName() {
        return this.mSegmentName;
    }
}
