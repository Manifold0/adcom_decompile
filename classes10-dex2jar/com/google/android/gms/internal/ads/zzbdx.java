// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.Iterator;

final class zzbdx implements Iterator<Map.Entry<Object, Object>>
{
    private int pos;
    private Iterator<Map.Entry<Object, Object>> zzdyp;
    private final /* synthetic */ zzbdp zzdyq;
    private boolean zzdyu;
    
    private zzbdx(final zzbdp zzdyq) {
        this.zzdyq = zzdyq;
        this.pos = -1;
    }
    
    private final Iterator<Map.Entry<Object, Object>> zzafx() {
        if (this.zzdyp == null) {
            this.zzdyp = this.zzdyq.zzdyl.entrySet().iterator();
        }
        return this.zzdyp;
    }
    
    @Override
    public final boolean hasNext() {
        return this.pos + 1 < this.zzdyq.zzdyk.size() || (!this.zzdyq.zzdyl.isEmpty() && this.zzafx().hasNext());
    }
    
    @Override
    public final void remove() {
        if (!this.zzdyu) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzdyu = false;
        this.zzdyq.zzafv();
        if (this.pos < this.zzdyq.zzdyk.size()) {
            this.zzdyq.zzcz(this.pos--);
            return;
        }
        this.zzafx().remove();
    }
}
