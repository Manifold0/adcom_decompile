// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.content.Intent;
import java.util.UUID;

public class AppCall
{
    private static AppCall currentPendingCall;
    private UUID callId;
    private int requestCode;
    private Intent requestIntent;
    
    public AppCall(final int n) {
        this(n, UUID.randomUUID());
    }
    
    public AppCall(final int requestCode, final UUID callId) {
        this.callId = callId;
        this.requestCode = requestCode;
    }
    
    public static AppCall finishPendingCall(final UUID uuid, final int n) {
        synchronized (AppCall.class) {
            final AppCall currentPendingCall = getCurrentPendingCall();
            AppCall appCall;
            if (currentPendingCall == null || !currentPendingCall.getCallId().equals(uuid) || currentPendingCall.getRequestCode() != n) {
                appCall = null;
            }
            else {
                setCurrentPendingCall(null);
                appCall = currentPendingCall;
            }
            return appCall;
        }
    }
    
    public static AppCall getCurrentPendingCall() {
        return AppCall.currentPendingCall;
    }
    
    private static boolean setCurrentPendingCall(final AppCall currentPendingCall) {
        synchronized (AppCall.class) {
            final AppCall currentPendingCall2 = getCurrentPendingCall();
            AppCall.currentPendingCall = currentPendingCall;
            return currentPendingCall2 != null;
        }
    }
    
    public UUID getCallId() {
        return this.callId;
    }
    
    public int getRequestCode() {
        return this.requestCode;
    }
    
    public Intent getRequestIntent() {
        return this.requestIntent;
    }
    
    public boolean setPending() {
        return setCurrentPendingCall(this);
    }
    
    public void setRequestCode(final int requestCode) {
        this.requestCode = requestCode;
    }
    
    public void setRequestIntent(final Intent requestIntent) {
        this.requestIntent = requestIntent;
    }
}
