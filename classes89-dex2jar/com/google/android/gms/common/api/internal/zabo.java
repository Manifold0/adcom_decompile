// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Collections;
import com.google.android.gms.common.ConnectionResult;

final class zabo implements Runnable
{
    private final /* synthetic */ ConnectionResult zaiz;
    private final /* synthetic */ GoogleApiManager.zac zajg;
    
    zabo(final GoogleApiManager.zac zajg, final ConnectionResult zaiz) {
        this.zajg = zajg;
        this.zaiz = zaiz;
    }
    
    @Override
    public final void run() {
        if (this.zaiz.isSuccess()) {
            GoogleApiManager.zac.zaa(this.zajg, true);
            if (this.zajg.zaio.requiresSignIn()) {
                this.zajg.zabr();
                return;
            }
            try {
                this.zajg.zaio.getRemoteService(null, Collections.emptySet());
                return;
            }
            catch (SecurityException ex) {
                Log.e("GoogleApiManager", "Failed to get service from broker. ", (Throwable)ex);
                this.zajg.zaim.zaii.get(this.zajg.zafq).onConnectionFailed(new ConnectionResult(10));
                return;
            }
        }
        this.zajg.zaim.zaii.get(this.zajg.zafq).onConnectionFailed(this.zaiz);
    }
}
