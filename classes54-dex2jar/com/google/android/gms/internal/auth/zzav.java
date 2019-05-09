// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;

final class zzav extends zzaj
{
    private final /* synthetic */ zzau zzcg;
    
    zzav(final zzau zzcg) {
        this.zzcg = zzcg;
    }
    
    @Override
    public final void zzb(final String s) {
        if (s != null) {
            this.zzcg.setResult((Result)new zzax(s));
            return;
        }
        this.zzcg.setResult((Result)zzaq.zzc(new Status(3006)));
    }
}
