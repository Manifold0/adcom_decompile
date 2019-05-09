package com.ironsource.mediationsdk.model;

import org.json.JSONObject;

public class ServerSegmetData {
    private JSONObject mCustomSegments;
    private String mSegmentId;
    private String mSegmentName;

    public ServerSegmetData(String name, String id, JSONObject customs) {
        this.mSegmentName = name;
        this.mSegmentId = id;
        this.mCustomSegments = customs;
    }

    public String getSegmentName() {
        return this.mSegmentName;
    }

    public String getSegmentId() {
        return this.mSegmentId;
    }

    public JSONObject getCustomSegments() {
        return this.mCustomSegments;
    }
}
