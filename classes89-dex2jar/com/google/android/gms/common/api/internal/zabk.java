// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;

final class zabk implements Runnable
{
    private final /* synthetic */ GoogleApiManager.zaa zaiy;
    
    zabk(final GoogleApiManager.zaa zaiy) {
        this.zaiy = zaiy;
    }
    
    @Override
    public final void run() {
        this.zaiy.zabh();
    }
}
