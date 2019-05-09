// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.ListIterator;

final class zzbei implements ListIterator<String>
{
    private ListIterator<String> zzdza;
    private final /* synthetic */ int zzdzb;
    private final /* synthetic */ zzbeh zzdzc;
    
    zzbei(final zzbeh zzdzc, final int zzdzb) {
        this.zzdzc = zzdzc;
        this.zzdzb = zzdzb;
        this.zzdza = this.zzdzc.zzdyz.listIterator(this.zzdzb);
    }
    
    @Override
    public final boolean hasNext() {
        return this.zzdza.hasNext();
    }
    
    @Override
    public final boolean hasPrevious() {
        return this.zzdza.hasPrevious();
    }
    
    @Override
    public final int nextIndex() {
        return this.zzdza.nextIndex();
    }
    
    @Override
    public final int previousIndex() {
        return this.zzdza.previousIndex();
    }
    
    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
