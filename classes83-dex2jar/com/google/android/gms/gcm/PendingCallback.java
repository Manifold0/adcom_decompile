// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import android.os.Parcelable;

public class PendingCallback implements Parcelable, ReflectedParcelable
{
    public static final Parcelable$Creator<PendingCallback> CREATOR;
    final IBinder zzfwl;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    public PendingCallback(final Parcel parcel) {
        this.zzfwl = parcel.readStrongBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStrongBinder(this.zzfwl);
    }
}
