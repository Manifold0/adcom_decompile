// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import java.util.Iterator;
import java.util.Collections;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.ConnectionResult;
import android.support.v4.util.ArrayMap;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import com.google.android.gms.tasks.OnCompleteListener;

final class zaz implements OnCompleteListener<Map<zai<?>, String>>
{
    private final /* synthetic */ zax zafi;
    
    private zaz(final zax zafi) {
        this.zafi = zafi;
    }
    
    public final void onComplete(@NonNull final Task<Map<zai<?>, String>> task) {
        this.zafi.zaeo.lock();
        Label_0342: {
            try {
                if (!this.zafi.zafd) {
                    return;
                }
                if (task.isSuccessful()) {
                    this.zafi.zafe = (Map<zai<?>, ConnectionResult>)new ArrayMap(this.zafi.zaeu.size());
                    final Iterator<zaw> iterator = this.zafi.zaeu.values().iterator();
                    while (iterator.hasNext()) {
                        this.zafi.zafe.put(iterator.next().zak(), ConnectionResult.RESULT_SUCCESS);
                    }
                    break Label_0342;
                }
            }
            finally {
                this.zafi.zaeo.unlock();
            }
            final Task task2;
            if (task2.getException() instanceof AvailabilityException) {
                final AvailabilityException ex = (AvailabilityException)task2.getException();
                if (this.zafi.zafb) {
                    this.zafi.zafe = (Map<zai<?>, ConnectionResult>)new ArrayMap(this.zafi.zaeu.size());
                    for (final zaw<? extends Api.ApiOptions> zaw : this.zafi.zaeu.values()) {
                        final zai<Api.ApiOptions> zak = zaw.zak();
                        final ConnectionResult connectionResult = ex.getConnectionResult(zaw);
                        if (this.zafi.zaa(zaw, connectionResult)) {
                            this.zafi.zafe.put(zak, new ConnectionResult(16));
                        }
                        else {
                            this.zafi.zafe.put(zak, connectionResult);
                        }
                    }
                }
                else {
                    this.zafi.zafe = (Map<zai<?>, ConnectionResult>)(Map)ex.zaj();
                }
                this.zafi.zafh = this.zafi.zaaf();
            }
            else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", (Throwable)task2.getException());
                this.zafi.zafe = (Map<zai<?>, ConnectionResult>)Collections.emptyMap();
                this.zafi.zafh = new ConnectionResult(8);
            }
        }
        if (this.zafi.zaff != null) {
            this.zafi.zafe.putAll(this.zafi.zaff);
            this.zafi.zafh = this.zafi.zaaf();
        }
        if (this.zafi.zafh == null) {
            this.zafi.zaad();
            this.zafi.zaae();
        }
        else {
            zax.zaa(this.zafi, false);
            this.zafi.zaex.zac(this.zafi.zafh);
        }
        this.zafi.zaez.signalAll();
        this.zafi.zaeo.unlock();
    }
}
