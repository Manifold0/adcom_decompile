// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

public class IabResult
{
    String mMessage;
    int mResponse;
    
    public IabResult(final int mResponse, final String s) {
        this.mResponse = mResponse;
        if (s == null || s.trim().length() == 0) {
            this.mMessage = IabHelper.getResponseDesc(mResponse);
            return;
        }
        this.mMessage = s + " (response: " + IabHelper.getResponseDesc(mResponse) + ")";
    }
    
    public String getMessage() {
        return this.mMessage;
    }
    
    public int getResponse() {
        return this.mResponse;
    }
    
    public boolean isFailure() {
        return !this.isSuccess();
    }
    
    public boolean isSuccess() {
        return this.mResponse == 0;
    }
    
    @Override
    public String toString() {
        return "{ IabResult: message = " + this.getMessage() + ", response = " + this.getResponse() + "}";
    }
}
