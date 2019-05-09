package com.google.android.gms.gcm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class PendingCallback implements Parcelable, ReflectedParcelable {
    public static final Creator<PendingCallback> CREATOR = new zzg();
    final IBinder zzfwl;

    public PendingCallback(Parcel parcel) {
        this.zzfwl = parcel.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.zzfwl);
    }
}
