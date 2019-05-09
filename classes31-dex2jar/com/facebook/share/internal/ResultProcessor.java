// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.AppCall;
import com.facebook.FacebookCallback;

public abstract class ResultProcessor
{
    private FacebookCallback appCallback;
    
    public ResultProcessor(final FacebookCallback appCallback) {
        this.appCallback = appCallback;
    }
    
    public void onCancel(final AppCall appCall) {
        if (this.appCallback != null) {
            this.appCallback.onCancel();
        }
    }
    
    public void onError(final AppCall appCall, final FacebookException ex) {
        if (this.appCallback != null) {
            this.appCallback.onError(ex);
        }
    }
    
    public abstract void onSuccess(final AppCall p0, final Bundle p1);
}
