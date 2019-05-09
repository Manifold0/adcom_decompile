// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import android.app.PendingIntent;
import android.support.annotation.NonNull;

public class ResolvableApiException extends ApiException
{
    public ResolvableApiException(@NonNull final Status status) {
        super(status);
    }
    
    public PendingIntent getResolution() {
        return this.mStatus.getResolution();
    }
    
    public void startResolutionForResult(final Activity activity, final int n) throws IntentSender$SendIntentException {
        this.mStatus.startResolutionForResult(activity, n);
    }
}
