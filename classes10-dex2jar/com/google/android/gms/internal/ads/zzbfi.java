// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbfi
{
    protected volatile int zzebt;
    
    public zzbfi() {
        this.zzebt = -1;
    }
    
    public static final <T extends zzbfi> T zza(final T t, final byte[] array) throws zzbfh {
        return zza(t, array, 0, array.length);
    }
    
    private static final <T extends zzbfi> T zza(final T t, final byte[] array, final int n, final int n2) throws zzbfh {
        try {
            final zzbez zzi = zzbez.zzi(array, 0, n2);
            t.zza(zzi);
            zzi.zzbp(0);
            return t;
        }
        catch (zzbfh zzbfh) {
            throw zzbfh;
        }
        catch (IOException ex) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    public static final byte[] zzb(final zzbfi zzbfi) {
        final byte[] array = new byte[zzbfi.zzacw()];
        final int length = array.length;
        try {
            final zzbfa zzj = zzbfa.zzj(array, 0, length);
            zzbfi.zza(zzj);
            zzj.zzacl();
            return array;
        }
        catch (IOException ex) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    @Override
    public String toString() {
        return zzbfj.zzc(this);
    }
    
    public abstract zzbfi zza(final zzbez p0) throws IOException;
    
    public void zza(final zzbfa zzbfa) throws IOException {
    }
    
    public final int zzacw() {
        return this.zzebt = this.zzr();
    }
    
    public zzbfi zzago() throws CloneNotSupportedException {
        return (zzbfi)super.clone();
    }
    
    protected int zzr() {
        return 0;
    }
}
