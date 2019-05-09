// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.Parcelable;

@KeepForSdk
@KeepName
public final class BinderWrapper implements Parcelable
{
    public static final Parcelable$Creator<BinderWrapper> CREATOR;
    private IBinder zzcz;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public BinderWrapper() {
        this.zzcz = null;
    }
    
    @KeepForSdk
    public BinderWrapper(final IBinder zzcz) {
        this.zzcz = null;
        this.zzcz = zzcz;
    }
    
    private BinderWrapper(final Parcel parcel) {
        this.zzcz = null;
        this.zzcz = parcel.readStrongBinder();
    }
    
    public final int describeContents() {
        return 0;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStrongBinder(this.zzcz);
    }
}
