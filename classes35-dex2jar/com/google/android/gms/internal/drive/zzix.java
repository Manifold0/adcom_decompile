// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import java.io.IOException;

public abstract class zzix
{
    protected volatile int zznf;
    
    public zzix() {
        this.zznf = -1;
    }
    
    public static final <T extends zzix> T zza(final T t, final byte[] array, final int n, final int n2) throws zziw {
        try {
            final zzio zza = zzio.zza(array, 0, n2);
            t.zza(zza);
            zza.zzj(0);
            return t;
        }
        catch (zziw zziw) {
            throw zziw;
        }
        catch (IOException ex) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    public static final byte[] zza(final zzix zzix) {
        final int zzaq = zzix.zzaq();
        zzix.zznf = zzaq;
        final byte[] array = new byte[zzaq];
        final int length = array.length;
        try {
            final zzip zzb = zzip.zzb(array, 0, length);
            zzix.zza(zzb);
            zzb.zzbh();
            return array;
        }
        catch (IOException ex) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    @Override
    public String toString() {
        return zziy.zzb(this);
    }
    
    public abstract zzix zza(final zzio p0) throws IOException;
    
    public void zza(final zzip zzip) throws IOException {
    }
    
    protected int zzaq() {
        return 0;
    }
    
    public zzix zzbi() throws CloneNotSupportedException {
        return (zzix)super.clone();
    }
}
