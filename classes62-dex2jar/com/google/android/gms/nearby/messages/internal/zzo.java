// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import java.util.List;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.nearby.zzc;
import android.os.IBinder;
import com.google.android.gms.internal.nearby.zza;

public final class zzo extends zza implements zzm
{
    zzo(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.messages.internal.IMessageListener");
    }
    
    @Override
    public final void zza(final zzaf zzaf) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzaf);
        this.transactOneway(1, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final List<Update> list) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeTypedList((List)list);
        this.transactOneway(4, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zzb(final zzaf zzaf) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzaf);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
}
