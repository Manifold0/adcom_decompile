// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import java.util.Set;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests;

final class zzch implements UpdateRequestsResult
{
    private final /* synthetic */ Status zzbc;
    
    zzch(final zzcg zzcg, final Status zzbc) {
        this.zzbc = zzbc;
    }
    
    @Override
    public final Set<String> getRequestIds() {
        return null;
    }
    
    @Override
    public final int getRequestOutcome(String s) {
        s = String.valueOf(s);
        if (s.length() != 0) {
            s = "Unknown request ID ".concat(s);
        }
        else {
            s = new String("Unknown request ID ");
        }
        throw new IllegalArgumentException(s);
    }
    
    public final Status getStatus() {
        return this.zzbc;
    }
    
    public final void release() {
    }
}
