// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.nearby.zzc;
import com.google.android.gms.common.api.Status;
import android.os.IBinder;
import com.google.android.gms.internal.nearby.zza;

public final class zzr extends zza implements zzp
{
    zzr(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.messages.internal.INearbyMessagesCallback");
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)status);
        this.transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
