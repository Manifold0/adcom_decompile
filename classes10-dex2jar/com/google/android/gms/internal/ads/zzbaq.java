// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbaq
{
    private static volatile boolean zzdqc;
    int zzdpx;
    int zzdpy;
    private int zzdpz;
    zzbat zzdqa;
    private boolean zzdqb;
    
    static {
        zzbaq.zzdqc = false;
        zzbaq.zzdqc = true;
    }
    
    private zzbaq() {
        this.zzdpy = 100;
        this.zzdpz = Integer.MAX_VALUE;
        this.zzdqb = false;
    }
    
    static zzbaq zza(final byte[] array, final int n, final int n2, final boolean b) {
        final zzbas zzbas = new zzbas(array, n, n2, b, null);
        try {
            zzbas.zzbr(n2);
            return zzbas;
        }
        catch (zzbbu zzbbu) {
            throw new IllegalArgumentException(zzbbu);
        }
    }
    
    public static int zzbu(final int n) {
        return n >>> 1 ^ -(n & 0x1);
    }
    
    public static long zzl(final long n) {
        return n >>> 1 ^ -(0x1L & n);
    }
    
    public abstract double readDouble() throws IOException;
    
    public abstract float readFloat() throws IOException;
    
    public abstract String readString() throws IOException;
    
    public abstract int zzabk() throws IOException;
    
    public abstract long zzabl() throws IOException;
    
    public abstract long zzabm() throws IOException;
    
    public abstract int zzabn() throws IOException;
    
    public abstract long zzabo() throws IOException;
    
    public abstract int zzabp() throws IOException;
    
    public abstract boolean zzabq() throws IOException;
    
    public abstract String zzabr() throws IOException;
    
    public abstract zzbah zzabs() throws IOException;
    
    public abstract int zzabt() throws IOException;
    
    public abstract int zzabu() throws IOException;
    
    public abstract int zzabv() throws IOException;
    
    public abstract long zzabw() throws IOException;
    
    public abstract int zzabx() throws IOException;
    
    public abstract long zzaby() throws IOException;
    
    abstract long zzabz() throws IOException;
    
    public abstract boolean zzaca() throws IOException;
    
    public abstract int zzacb();
    
    public abstract void zzbp(final int p0) throws zzbbu;
    
    public abstract boolean zzbq(final int p0) throws IOException;
    
    public abstract int zzbr(final int p0) throws zzbbu;
    
    public abstract void zzbs(final int p0);
    
    public abstract void zzbt(final int p0) throws IOException;
}
