// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zabl implements Runnable
{
    private final /* synthetic */ GoogleApiManager.zaa zaiy;
    private final /* synthetic */ ConnectionResult zaiz;
    
    zabl(final GoogleApiManager.zaa zaiy, final ConnectionResult zaiz) {
        this.zaiy = zaiy;
        this.zaiz = zaiz;
    }
    
    @Override
    public final void run() {
        this.zaiy.onConnectionFailed(this.zaiz);
    }
}
