// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzbdw implements Comparable<zzbdw>, Entry<Object, Object>
{
    private value;
    private final /* synthetic */ zzbdp zzdyq;
    private final zzdyt;
    
    zzbdw(final zzbdp zzdyq, final Comparable zzdyt, final Object value) {
        this.zzdyq = zzdyq;
        this.zzdyt = zzdyt;
        this.value = value;
    }
    
    zzbdw(final zzbdp zzbdp, final Entry<Object, Object> entry) {
        this(zzbdp, entry.getKey(), entry.getValue());
    }
    
    private static boolean equals(final Object o, final Object o2) {
        if (o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof Entry)) {
                return false;
            }
            final Entry entry = (Entry)o;
            if (!equals(this.zzdyt, entry.getKey()) || !equals(this.value, entry.getValue())) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final Object getValue() {
        return this.value;
    }
    
    @Override
    public final int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.zzdyt == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzdyt.hashCode();
        }
        if (this.value != null) {
            hashCode = this.value.hashCode();
        }
        return hashCode2 ^ hashCode;
    }
    
    @Override
    public final Object setValue(final Object value) {
        this.zzdyq.zzafv();
        final Object value2 = this.value;
        this.value = value;
        return value2;
    }
    
    @Override
    public final String toString() {
        final String value = String.valueOf(this.zzdyt);
        final String value2 = String.valueOf(this.value);
        return new StringBuilder(String.valueOf(value).length() + 1 + String.valueOf(value2).length()).append(value).append("=").append(value2).toString();
    }
}
