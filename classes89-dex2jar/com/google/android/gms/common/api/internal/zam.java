// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.ConnectionResult;

final class zam
{
    private final int zadh;
    private final ConnectionResult zadi;
    
    zam(final ConnectionResult zadi, final int zadh) {
        Preconditions.checkNotNull((Object)zadi);
        this.zadi = zadi;
        this.zadh = zadh;
    }
    
    final ConnectionResult getConnectionResult() {
        return this.zadi;
    }
    
    final int zar() {
        return this.zadh;
    }
}
