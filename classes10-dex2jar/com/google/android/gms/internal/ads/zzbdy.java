// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;
import java.util.AbstractSet;

class zzbdy extends AbstractSet<Map.Entry<Object, Object>>
{
    private final /* synthetic */ zzbdp zzdyq;
    
    private zzbdy(final zzbdp zzdyq) {
        this.zzdyq = zzdyq;
    }
    
    @Override
    public void clear() {
        this.zzdyq.clear();
    }
    
    @Override
    public boolean contains(Object value) {
        final Map.Entry entry = (Map.Entry)value;
        value = this.zzdyq.get(entry.getKey());
        final Object value2 = entry.getValue();
        return value == value2 || (value != null && value.equals(value2));
    }
    
    @Override
    public Iterator<Map.Entry<Object, Object>> iterator() {
        return new zzbdx(this.zzdyq, null);
    }
    
    @Override
    public boolean remove(final Object o) {
        final Map.Entry entry = (Map.Entry)o;
        if (this.contains(entry)) {
            this.zzdyq.remove(entry.getKey());
            return true;
        }
        return false;
    }
    
    @Override
    public int size() {
        return this.zzdyq.size();
    }
}
