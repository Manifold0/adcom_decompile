// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public final class zzaio implements zzaiu
{
    private zzaiv zzcnc;
    
    public zzaio(final zzaiv zzcnc) {
        this.zzcnc = zzcnc;
    }
    
    @Override
    public final zzait zza(final Context context, final zzang zzang, final zzaej zzaej) {
        if (zzaej.zzcfk == null) {
            return null;
        }
        return new zzaii(context, zzang, zzaej.zzcfk, zzaej.zzbyq, this.zzcnc);
    }
}
