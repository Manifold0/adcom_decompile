// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import android.os.Parcelable$Creator;
import android.app.PendingIntent;
import android.os.Parcel;

public abstract class zzan extends zzb implements zzam
{
    public zzan() {
        super("com.google.android.gms.location.internal.IGeofencerCallbacks");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zza(parcel.readInt(), parcel.createStringArray());
                break;
            }
            case 2: {
                this.zzb(parcel.readInt(), parcel.createStringArray());
                break;
            }
            case 3: {
                this.zza(parcel.readInt(), com.google.android.gms.internal.location.zzc.zza(parcel, (android.os.Parcelable$Creator<PendingIntent>)PendingIntent.CREATOR));
                break;
            }
        }
        return true;
    }
}
