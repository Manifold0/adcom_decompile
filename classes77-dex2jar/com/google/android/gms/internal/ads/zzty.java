// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.LinkedList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzty
{
    private final LinkedList<zztz> zzbon;
    private zzjj zzboo;
    private final int zzbop;
    private boolean zzboq;
    private final String zzye;
    
    zzty(final zzjj zzboo, final String zzye, final int zzbop) {
        Preconditions.checkNotNull((Object)zzboo);
        Preconditions.checkNotNull((Object)zzye);
        this.zzbon = new LinkedList<zztz>();
        this.zzboo = zzboo;
        this.zzye = zzye;
        this.zzbop = zzbop;
    }
    
    final String getAdUnitId() {
        return this.zzye;
    }
    
    final int getNetworkType() {
        return this.zzbop;
    }
    
    final int size() {
        return this.zzbon.size();
    }
    
    final void zza(final zzss zzss, final zzjj zzjj) {
        this.zzbon.add(new zztz(this, zzss, zzjj));
    }
    
    final boolean zzb(final zzss zzss) {
        final zztz zztz = new zztz(this, zzss);
        this.zzbon.add(zztz);
        return zztz.load();
    }
    
    final zztz zzl(@Nullable final zzjj zzboo) {
        if (zzboo != null) {
            this.zzboo = zzboo;
        }
        return this.zzbon.remove();
    }
    
    final zzjj zzlf() {
        return this.zzboo;
    }
    
    final int zzlg() {
        final Iterator<zztz> iterator = this.zzbon.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (iterator.next().zzwa) {
                ++n;
            }
        }
        return n;
    }
    
    final int zzlh() {
        final Iterator<zztz> iterator = this.zzbon.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (iterator.next().load()) {
                ++n;
            }
        }
        return n;
    }
    
    final void zzli() {
        this.zzboq = true;
    }
    
    final boolean zzlj() {
        return this.zzboq;
    }
}
