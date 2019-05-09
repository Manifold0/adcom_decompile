package com.adjust.sdk;

import org.json.JSONObject;

public class AdjustSessionSuccess {
    public String adid;
    public JSONObject jsonResponse;
    public String message;
    public String timestamp;

    public String toString() {
        return Util.formatString("Session Success msg:%s time:%s adid:%s json:%s", this.message, this.timestamp, this.adid, this.jsonResponse);
    }
}
