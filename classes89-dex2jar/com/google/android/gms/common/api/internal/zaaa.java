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

final class zaaa implements OnCompleteListener<Map<zai<?>, String>>
{
    private final /* synthetic */ zax zafi;
    private SignInConnectionListener zafj;
    
    zaaa(final zax zafi, final SignInConnectionListener zafj) {
        this.zafi = zafi;
        this.zafj = zafj;
    }
    
    final void cancel() {
        this.zafj.onComplete();
    }
    
    public final void onComplete(@NonNull final Task<Map<zai<?>, String>> task) {
        this.zafi.zaeo.lock();
        Label_0330: {
            try {
                if (!this.zafi.zafd) {
                    this.zafj.onComplete();
                    return;
                }
                if (task.isSuccessful()) {
                    this.zafi.zaff = (Map<zai<?>, ConnectionResult>)new ArrayMap(this.zafi.zaev.size());
                    final Iterator<zaw> iterator = this.zafi.zaev.values().iterator();
                    while (iterator.hasNext()) {
                        this.zafi.zaff.put(iterator.next().zak(), ConnectionResult.RESULT_SUCCESS);
                    }
                    break Label_0330;
                }
            }
            finally {
                this.zafi.zaeo.unlock();
            }
            final Task task2;
            if (task2.getException() instanceof AvailabilityException) {
                final AvailabilityException ex = (AvailabilityException)task2.getException();
                if (this.zafi.zafb) {
                    this.zafi.zaff = (Map<zai<?>, ConnectionResult>)new ArrayMap(this.zafi.zaev.size());
                    for (final zaw<? extends Api.ApiOptions> zaw : this.zafi.zaev.values()) {
                        final zai<Api.ApiOptions> zak = zaw.zak();
                        final ConnectionResult connectionResult = ex.getConnectionResult(zaw);
                        if (this.zafi.zaa(zaw, connectionResult)) {
                            this.zafi.zaff.put(zak, new ConnectionResult(16));
                        }
                        else {
                            this.zafi.zaff.put(zak, connectionResult);
                        }
                    }
                }
                else {
                    this.zafi.zaff = (Map<zai<?>, ConnectionResult>)(Map)ex.zaj();
                }
            }
            else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", (Throwable)task2.getException());
                this.zafi.zaff = (Map<zai<?>, ConnectionResult>)Collections.emptyMap();
            }
        }
        if (this.zafi.isConnected()) {
            this.zafi.zafe.putAll(this.zafi.zaff);
            if (this.zafi.zaaf() == null) {
                this.zafi.zaad();
                this.zafi.zaae();
                this.zafi.zaez.signalAll();
            }
        }
        this.zafj.onComplete();
        this.zafi.zaeo.unlock();
    }
}
