// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.IBinder$DeathRecipient;
import com.google.android.gms.common.api.zac;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import java.util.Collections;
import java.util.WeakHashMap;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Set;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.common.api.Status;

public final class zacp
{
    public static final Status zakx;
    private static final BasePendingResult<?>[] zaky;
    private final Map<Api.AnyClientKey<?>, Api.Client> zagz;
    @VisibleForTesting
    final Set<BasePendingResult<?>> zakz;
    private final zacs zala;
    
    static {
        zakx = new Status(8, "The connection to Google Play services was lost");
        zaky = new BasePendingResult[0];
    }
    
    public zacp(final Map<Api.AnyClientKey<?>, Api.Client> zagz) {
        this.zakz = Collections.synchronizedSet((Set<BasePendingResult<?>>)Collections.newSetFromMap((Map<T, Boolean>)new WeakHashMap<Object, Boolean>()));
        this.zala = new zacq(this);
        this.zagz = zagz;
    }
    
    public final void release() {
        final BasePendingResult<?>[] array = this.zakz.toArray(zacp.zaky);
        for (int length = array.length, i = 0; i < length; ++i) {
            final BasePendingResult<?> basePendingResult = array[i];
            basePendingResult.zaa((zacs)null);
            if (basePendingResult.zam() == null) {
                if (basePendingResult.zat()) {
                    this.zakz.remove(basePendingResult);
                }
            }
            else {
                basePendingResult.setResultCallback(null);
                final IBinder serviceBrokerBinder = this.zagz.get(((BaseImplementation.ApiMethodImpl<?, ?>)basePendingResult).getClientKey()).getServiceBrokerBinder();
                Label_0124: {
                    if (!basePendingResult.isReady()) {
                        if (serviceBrokerBinder != null && serviceBrokerBinder.isBinderAlive()) {
                            final zacr zacr = new zacr(basePendingResult, null, serviceBrokerBinder, null);
                            basePendingResult.zaa(zacr);
                            try {
                                serviceBrokerBinder.linkToDeath((IBinder$DeathRecipient)zacr, 0);
                                break Label_0124;
                            }
                            catch (RemoteException ex) {
                                basePendingResult.cancel();
                                basePendingResult.zam();
                                throw new NullPointerException();
                            }
                        }
                        basePendingResult.zaa((zacs)null);
                        basePendingResult.cancel();
                        basePendingResult.zam();
                        throw new NullPointerException();
                    }
                    basePendingResult.zaa(new zacr(basePendingResult, null, serviceBrokerBinder, null));
                }
                this.zakz.remove(basePendingResult);
            }
        }
    }
    
    final void zab(final BasePendingResult<? extends Result> basePendingResult) {
        this.zakz.add(basePendingResult);
        basePendingResult.zaa(this.zala);
    }
    
    public final void zabx() {
        final BasePendingResult<?>[] array = this.zakz.toArray(zacp.zaky);
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].zab(zacp.zakx);
        }
    }
}
