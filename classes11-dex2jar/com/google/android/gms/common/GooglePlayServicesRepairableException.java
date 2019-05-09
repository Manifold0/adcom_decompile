// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.content.Intent;

public class GooglePlayServicesRepairableException extends UserRecoverableException
{
    private final int zzag;
    
    public GooglePlayServicesRepairableException(final int zzag, final String s, final Intent intent) {
        super(s, intent);
        this.zzag = zzag;
    }
    
    public int getConnectionStatusCode() {
        return this.zzag;
    }
}
