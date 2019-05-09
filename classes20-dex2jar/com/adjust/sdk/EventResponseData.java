// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

public class EventResponseData extends ResponseData
{
    public String eventToken;
    
    public EventResponseData(final ActivityPackage activityPackage) {
        this.eventToken = activityPackage.getParameters().get("event_token");
    }
    
    public AdjustEventFailure getFailureResponseData() {
        if (this.success) {
            return null;
        }
        final AdjustEventFailure adjustEventFailure = new AdjustEventFailure();
        adjustEventFailure.message = this.message;
        adjustEventFailure.timestamp = this.timestamp;
        adjustEventFailure.adid = this.adid;
        adjustEventFailure.willRetry = this.willRetry;
        adjustEventFailure.jsonResponse = this.jsonResponse;
        adjustEventFailure.eventToken = this.eventToken;
        return adjustEventFailure;
    }
    
    public AdjustEventSuccess getSuccessResponseData() {
        if (!this.success) {
            return null;
        }
        final AdjustEventSuccess adjustEventSuccess = new AdjustEventSuccess();
        adjustEventSuccess.message = this.message;
        adjustEventSuccess.timestamp = this.timestamp;
        adjustEventSuccess.adid = this.adid;
        adjustEventSuccess.jsonResponse = this.jsonResponse;
        adjustEventSuccess.eventToken = this.eventToken;
        return adjustEventSuccess;
    }
}
