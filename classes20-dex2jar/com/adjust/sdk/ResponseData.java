// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import org.json.JSONObject;

public class ResponseData
{
    public ActivityKind activityKind;
    public String adid;
    public AdjustAttribution attribution;
    public JSONObject jsonResponse;
    public String message;
    public boolean success;
    public String timestamp;
    public boolean willRetry;
    
    protected ResponseData() {
    }
    
    public static ResponseData buildResponseData(final ActivityPackage activityPackage) {
        final ActivityKind activityKind = activityPackage.getActivityKind();
        ResponseData responseData = null;
        switch (activityKind) {
            default: {
                responseData = new ResponseData();
                break;
            }
            case SESSION: {
                responseData = new SessionResponseData();
                break;
            }
            case CLICK: {
                responseData = new SdkClickResponseData();
                break;
            }
            case ATTRIBUTION: {
                responseData = new AttributionResponseData();
                break;
            }
            case EVENT: {
                responseData = new EventResponseData(activityPackage);
                break;
            }
        }
        responseData.activityKind = activityKind;
        return responseData;
    }
    
    @Override
    public String toString() {
        return Util.formatString("message:%s timestamp:%s json:%s", this.message, this.timestamp, this.jsonResponse);
    }
}
