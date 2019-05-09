// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzbej implements Iterator<String>
{
    private final /* synthetic */ zzbeh zzdzc;
    private Iterator<String> zzdzd;
    
    zzbej(final zzbeh zzdzc) {
        this.zzdzc = zzdzc;
        this.zzdzd = this.zzdzc.zzdyz.iterator();
    }
    
    @Override
    public final boolean hasNext() {
        return this.zzdzd.hasNext();
    }
    
    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
