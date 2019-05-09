// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api-phone;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.os.Parcel;

public class zzc
{
    private static final ClassLoader zzd;
    
    static {
        zzd = zzc.class.getClassLoader();
    }
    
    private zzc() {
    }
    
    public static <T extends Parcelable> T zza(final Parcel parcel, final Parcelable$Creator<T> parcelable$Creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return (T)parcelable$Creator.createFromParcel(parcel);
    }
    
    public static void zza(final Parcel parcel, final IInterface interface1) {
        if (interface1 == null) {
            parcel.writeStrongBinder((IBinder)null);
            return;
        }
        parcel.writeStrongBinder(interface1.asBinder());
    }
}
