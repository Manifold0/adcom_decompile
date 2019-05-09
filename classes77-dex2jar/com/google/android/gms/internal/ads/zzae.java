// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public class zzae extends Exception
{
    private long zzad;
    private final zzp zzbj;
    
    public zzae() {
        this.zzbj = null;
    }
    
    public zzae(final zzp zzbj) {
        this.zzbj = zzbj;
    }
    
    public zzae(final String s) {
        super(s);
        this.zzbj = null;
    }
    
    public zzae(final Throwable t) {
        super(t);
        this.zzbj = null;
    }
    
    final void zza(final long zzad) {
        this.zzad = zzad;
    }
}
