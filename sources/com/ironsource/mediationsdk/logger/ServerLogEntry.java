package com.ironsource.mediationsdk.logger;

import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import org.json.JSONException;
import org.json.JSONObject;

class ServerLogEntry {
    private int mLogLevel;
    private String mMessage;
    private IronSourceTag mTag;
    private String mTimetamp;

    public ServerLogEntry(IronSourceTag tag, String timestamp, String message, int level) {
        this.mTag = tag;
        this.mTimetamp = timestamp;
        this.mMessage = message;
        this.mLogLevel = level;
    }

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        try {
            result.put("timestamp", this.mTimetamp);
            result.put("tag", this.mTag);
            result.put("level", this.mLogLevel);
            result.put("message", this.mMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getLogLevel() {
        return this.mLogLevel;
    }
}
