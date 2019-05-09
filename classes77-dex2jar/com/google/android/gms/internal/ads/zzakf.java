// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.os.Bundle;

final class zzakf extends zzakg
{
    private final /* synthetic */ zzakd zzcrh;
    private final /* synthetic */ Bundle zzcri;
    
    zzakf(final zzakd zzcrh, final Bundle zzcri) {
        this.zzcrh = zzcrh;
        this.zzcri = zzcri;
        super(null);
    }
    
    @Override
    public final void zzdn() {
        final Iterator<zzakh> iterator = this.zzcrh.zzcqv.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzd(this.zzcri);
        }
    }
}
