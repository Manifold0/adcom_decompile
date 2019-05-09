// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;

public final class zzem
{
    private static final String[] zzmt;
    private final int statusCode;
    private final HashMap<String, Integer> zzmu;
    
    static {
        zzmt = new String[] { "requestId", "outcome" };
    }
    
    private zzem(final int statusCode, final HashMap<String, Integer> zzmu) {
        this.statusCode = statusCode;
        this.zzmu = zzmu;
    }
    
    @VisibleForTesting
    public static zzem zzbd(final DataHolder dataHolder) {
        final zzeo zzeo = new zzeo();
        zzeo.zzo(dataHolder.getStatusCode());
        for (int count = dataHolder.getCount(), i = 0; i < count; ++i) {
            final int windowIndex = dataHolder.getWindowIndex(i);
            zzeo.zzh(dataHolder.getString("requestId", i, windowIndex), dataHolder.getInteger("outcome", i, windowIndex));
        }
        return zzeo.zzca();
    }
    
    @VisibleForTesting
    public final Set<String> getRequestIds() {
        return this.zzmu.keySet();
    }
    
    @VisibleForTesting
    public final int getRequestOutcome(final String s) {
        Preconditions.checkArgument(this.zzmu.containsKey(s), (Object)new StringBuilder(String.valueOf(s).length() + 46).append("Request ").append(s).append(" was not part of the update operation!").toString());
        return this.zzmu.get(s);
    }
}
