// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import android.support.annotation.Nullable;
import android.support.annotation.NonNull;

public class ApiException extends Exception
{
    protected final Status mStatus;
    
    public ApiException(@NonNull final Status mStatus) {
        final int statusCode = mStatus.getStatusCode();
        String statusMessage;
        if (mStatus.getStatusMessage() != null) {
            statusMessage = mStatus.getStatusMessage();
        }
        else {
            statusMessage = "";
        }
        super(new StringBuilder(String.valueOf(statusMessage).length() + 13).append(statusCode).append(": ").append(statusMessage).toString());
        this.mStatus = mStatus;
    }
    
    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }
    
    @Deprecated
    @Nullable
    public String getStatusMessage() {
        return this.mStatus.getStatusMessage();
    }
}
