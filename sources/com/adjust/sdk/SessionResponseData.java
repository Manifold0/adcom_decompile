package com.adjust.sdk;

public class SessionResponseData extends ResponseData {
    public AdjustSessionSuccess getSuccessResponseData() {
        if (!this.success) {
            return null;
        }
        AdjustSessionSuccess successResponseData = new AdjustSessionSuccess();
        successResponseData.message = this.message;
        successResponseData.timestamp = this.timestamp;
        successResponseData.adid = this.adid;
        successResponseData.jsonResponse = this.jsonResponse;
        return successResponseData;
    }

    public AdjustSessionFailure getFailureResponseData() {
        if (this.success) {
            return null;
        }
        AdjustSessionFailure failureResponseData = new AdjustSessionFailure();
        failureResponseData.message = this.message;
        failureResponseData.timestamp = this.timestamp;
        failureResponseData.adid = this.adid;
        failureResponseData.willRetry = this.willRetry;
        failureResponseData.jsonResponse = this.jsonResponse;
        return failureResponseData;
    }
}
