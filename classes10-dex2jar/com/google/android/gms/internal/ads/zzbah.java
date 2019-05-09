// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.io.Serializable;

public abstract class zzbah implements Serializable, Iterable<Byte>
{
    public static final zzbah zzdpq;
    private static final zzbal zzdpr;
    private int zzdpa;
    
    static {
        zzdpq = new zzbao(zzbbq.zzduq);
        zzbal zzdpr2;
        if (zzbac.zzabb()) {
            zzdpr2 = new zzbap(null);
        }
        else {
            zzdpr2 = new zzbaj(null);
        }
        zzdpr = zzdpr2;
    }
    
    zzbah() {
        this.zzdpa = 0;
    }
    
    static zzbam zzbo(final int n) {
        return new zzbam(n, null);
    }
    
    public static zzbah zzc(final byte[] array, final int n, final int n2) {
        return new zzbao(zzbah.zzdpr.zzd(array, n, n2));
    }
    
    static int zzd(final int n, final int n2, final int n3) {
        final int n4 = n2 - n;
        if ((n | n2 | n4 | n3 - n2) >= 0) {
            return n4;
        }
        if (n < 0) {
            throw new IndexOutOfBoundsException(new StringBuilder(32).append("Beginning index: ").append(n).append(" < 0").toString());
        }
        if (n2 < n) {
            throw new IndexOutOfBoundsException(new StringBuilder(66).append("Beginning index larger than ending index: ").append(n).append(", ").append(n2).toString());
        }
        throw new IndexOutOfBoundsException(new StringBuilder(37).append("End index: ").append(n2).append(" >= ").append(n3).toString());
    }
    
    public static zzbah zzem(final String s) {
        return new zzbao(s.getBytes(zzbbq.UTF_8));
    }
    
    public static zzbah zzo(final byte[] array) {
        return zzc(array, 0, array.length);
    }
    
    static zzbah zzp(final byte[] array) {
        return new zzbao(array);
    }
    
    @Override
    public abstract boolean equals(final Object p0);
    
    @Override
    public final int hashCode() {
        int zzdpa;
        if ((zzdpa = this.zzdpa) == 0) {
            final int size = this.size();
            if ((zzdpa = this.zzc(size, 0, size)) == 0) {
                zzdpa = 1;
            }
            this.zzdpa = zzdpa;
        }
        return zzdpa;
    }
    
    public abstract int size();
    
    public final byte[] toByteArray() {
        final int size = this.size();
        if (size == 0) {
            return zzbbq.zzduq;
        }
        final byte[] array = new byte[size];
        this.zza(array, 0, 0, size);
        return array;
    }
    
    @Override
    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), this.size());
    }
    
    protected abstract String zza(final Charset p0);
    
    abstract void zza(final zzbag p0) throws IOException;
    
    protected abstract void zza(final byte[] p0, final int p1, final int p2, final int p3);
    
    public final String zzabd() {
        final Charset utf_8 = zzbbq.UTF_8;
        if (this.size() == 0) {
            return "";
        }
        return this.zza(utf_8);
    }
    
    public abstract boolean zzabe();
    
    public abstract zzbaq zzabf();
    
    protected final int zzabg() {
        return this.zzdpa;
    }
    
    public abstract byte zzbn(final int p0);
    
    protected abstract int zzc(final int p0, final int p1, final int p2);
    
    public abstract zzbah zzk(final int p0, final int p1);
}
