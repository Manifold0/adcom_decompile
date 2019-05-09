// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbbc
{
    private final int number;
    private final Object object;
    
    zzbbc(final Object object, final int number) {
        this.object = object;
        this.number = number;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof zzbbc) {
            final zzbbc zzbbc = (zzbbc)o;
            if (this.object == zzbbc.object && this.number == zzbbc.number) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public final int hashCode() {
        return System.identityHashCode(this.object) * 65535 + this.number;
    }
}
