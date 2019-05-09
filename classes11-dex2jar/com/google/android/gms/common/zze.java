// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.common.internal.zzi;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzj;

abstract class zze extends zzj
{
    private int zzt;
    
    protected zze(final byte[] array) {
        Preconditions.checkArgument(array.length == 25);
        this.zzt = Arrays.hashCode(array);
    }
    
    protected static byte[] zza(final String s) {
        try {
            return s.getBytes("ISO-8859-1");
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof zzi)) {
            return false;
        }
        try {
            final zzi zzi = (zzi)o;
            if (zzi.zzc() != this.hashCode()) {
                return false;
            }
            final IObjectWrapper zzb = zzi.zzb();
            return zzb != null && Arrays.equals(this.getBytes(), (byte[])ObjectWrapper.unwrap(zzb));
        }
        catch (RemoteException ex) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", (Throwable)ex);
            return false;
        }
    }
    
    abstract byte[] getBytes();
    
    public int hashCode() {
        return this.zzt;
    }
    
    @Override
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this.getBytes());
    }
    
    @Override
    public final int zzc() {
        return this.hashCode();
    }
}
