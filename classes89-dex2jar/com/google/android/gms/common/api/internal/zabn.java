// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;

final class zabn implements Runnable
{
    private final /* synthetic */ zabm zaja;
    
    zabn(final zabm zaja) {
        this.zaja = zaja;
    }
    
    @Override
    public final void run() {
        this.zaja.zaiy.zaio.disconnect();
    }
}
