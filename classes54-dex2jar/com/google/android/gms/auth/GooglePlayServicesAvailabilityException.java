// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import android.content.Intent;

public class GooglePlayServicesAvailabilityException extends UserRecoverableAuthException
{
    private final int zzu;
    
    GooglePlayServicesAvailabilityException(final int zzu, final String s, final Intent intent) {
        super(s, intent);
        this.zzu = zzu;
    }
    
    public int getConnectionStatusCode() {
        return this.zzu;
    }
}
