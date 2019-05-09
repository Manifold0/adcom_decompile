// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zacf implements Runnable
{
    private final /* synthetic */ zace zakk;
    
    zacf(final zace zakk) {
        this.zakk = zakk;
    }
    
    @Override
    public final void run() {
        this.zakk.zakj.zag(new ConnectionResult(4));
    }
}
