// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import java.util.Set;
import com.google.android.gms.common.api.AvailabilityException;
import android.support.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import java.util.Iterator;
import com.google.android.gms.common.api.GoogleApi;
import java.util.Map;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.common.ConnectionResult;
import android.support.v4.util.ArrayMap;

public final class zak
{
    private final ArrayMap<zai<?>, ConnectionResult> zaay;
    private final ArrayMap<zai<?>, String> zadb;
    private final TaskCompletionSource<Map<zai<?>, String>> zadc;
    private int zadd;
    private boolean zade;
    
    public zak(final Iterable<? extends GoogleApi<?>> iterable) {
        this.zadb = (ArrayMap<zai<?>, String>)new ArrayMap();
        this.zadc = (TaskCompletionSource<Map<zai<?>, String>>)new TaskCompletionSource();
        this.zade = false;
        this.zaay = (ArrayMap<zai<?>, ConnectionResult>)new ArrayMap();
        final Iterator<? extends GoogleApi<?>> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.zaay.put((Object)((GoogleApi)iterator.next()).zak(), (Object)null);
        }
        this.zadd = this.zaay.keySet().size();
    }
    
    public final Task<Map<zai<?>, String>> getTask() {
        return (Task<Map<zai<?>, String>>)this.zadc.getTask();
    }
    
    public final void zaa(final zai<?> zai, final ConnectionResult connectionResult, @Nullable final String s) {
        this.zaay.put((Object)zai, (Object)connectionResult);
        this.zadb.put((Object)zai, (Object)s);
        --this.zadd;
        if (!connectionResult.isSuccess()) {
            this.zade = true;
        }
        if (this.zadd == 0) {
            if (!this.zade) {
                this.zadc.setResult((Object)this.zadb);
                return;
            }
            this.zadc.setException((Exception)new AvailabilityException(this.zaay));
        }
    }
    
    public final Set<zai<?>> zap() {
        return (Set<zai<?>>)this.zaay.keySet();
    }
}
