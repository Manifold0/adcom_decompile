// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.Iterator;

final class zzbca<K> implements Iterator<Map.Entry<K, Object>>
{
    private Iterator<Map.Entry<K, Object>> zzdvj;
    
    public zzbca(final Iterator<Map.Entry<K, Object>> zzdvj) {
        this.zzdvj = zzdvj;
    }
    
    @Override
    public final boolean hasNext() {
        return this.zzdvj.hasNext();
    }
    
    @Override
    public final void remove() {
        this.zzdvj.remove();
    }
}
