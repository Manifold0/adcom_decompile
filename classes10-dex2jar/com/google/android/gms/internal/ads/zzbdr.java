// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Map;
import java.util.Iterator;

final class zzbdr implements Iterator<Map.Entry<Object, Object>>
{
    private int pos;
    private Iterator<Map.Entry<Object, Object>> zzdyp;
    private final /* synthetic */ zzbdp zzdyq;
    
    private zzbdr(final zzbdp zzdyq) {
        this.zzdyq = zzdyq;
        this.pos = this.zzdyq.zzdyk.size();
    }
    
    private final Iterator<Map.Entry<Object, Object>> zzafx() {
        if (this.zzdyp == null) {
            this.zzdyp = this.zzdyq.zzdyn.entrySet().iterator();
        }
        return this.zzdyp;
    }
    
    @Override
    public final boolean hasNext() {
        return (this.pos > 0 && this.pos <= this.zzdyq.zzdyk.size()) || this.zzafx().hasNext();
    }
    
    @Override
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
