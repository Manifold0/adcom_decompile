// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;
import com.google.android.gms.internal.zzed;

public final class zze extends zzed implements zzd
{
    zze(final IBinder binder) {
        super(binder, "com.google.android.gms.gcm.INetworkTaskCallback");
    }
    
    public final void zzdr(final int n) throws RemoteException {
        final Parcel zzaz = this.zzaz();
        zzaz.writeInt(n);
        this.zzb(2, zzaz);
    }
}
