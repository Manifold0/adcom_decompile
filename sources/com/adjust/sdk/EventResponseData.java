package com.adjust.sdk;

public class EventResponseData extends ResponseData {
    public String eventToken;

    public EventResponseData(ActivityPackage activityPackage) {
        this.eventToken = (String) activityPackage.getParameters().get("event_token");
    }

    public AdjustEventSuccess getSuccessResponseData() {
        if (!this.success) {
            return null;
        }
        AdjustEventSuccess successResponseData = new AdjustEventSuccess();
        successResponseData.message = this.message;
        successResponseData.timestamp = this.timestamp;
        successResponseData.adid = this.adid;
        successResponseData.jsonResponse = this.jsonResponse;
        successResponseData.eventToken = this.eventToken;
        return successResponseData;
    }

    public AdjustEventFailure getFailureResponseData() {
        if (this.success) {
            return null;
        }
        AdjustEventFailure failureResponseData = new AdjustEventFailure();
        failureResponseData.message = this.message;
        failureResponseData.timestamp = this.timestamp;
        failureResponseData.adid = this.adid;
        failureResponseData.willRetry = this.willRetry;
        failureResponseData.jsonResponse = this.jsonResponse;
        failureResponseData.eventToken = this.eventToken;
        return failureResponseData;
    }
}
