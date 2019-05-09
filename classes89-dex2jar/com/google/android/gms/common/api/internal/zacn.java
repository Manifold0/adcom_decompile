// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

final class zacn implements Runnable
{
    private final /* synthetic */ Result zakv;
    private final /* synthetic */ zacm zakw;
    
    zacn(final zacm zakw, final Result zakv) {
        this.zakw = zakw;
        this.zakv = zakv;
    }
    
    @WorkerThread
    @Override
    public final void run() {
        try {
            BasePendingResult.zadn.set(true);
            this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(0, (Object)this.zakw.zako.onSuccess(this.zakv)));
        }
        catch (RuntimeException ex) {
            this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(1, (Object)ex));
        }
        finally {
            BasePendingResult.zadn.set(false);
            zacm.zaa(this.zakw, this.zakv);
            final GoogleApiClient googleApiClient = (GoogleApiClient)this.zakw.zadq.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zakw);
            }
        }
    }
}
