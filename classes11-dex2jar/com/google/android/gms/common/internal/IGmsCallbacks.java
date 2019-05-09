// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable$Creator;
import com.google.android.gms.internal.common.zzc;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;

public interface IGmsCallbacks extends IInterface
{
    void onPostInitComplete(final int p0, final IBinder p1, final Bundle p2) throws RemoteException;
    
    void zza(final int p0, final Bundle p1) throws RemoteException;
    
    void zza(final int p0, final IBinder p1, final zzb p2) throws RemoteException;
    
    public abstract static class zza extends zzb implements IGmsCallbacks
    {
        public zza() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }
        
        @Override
        protected final boolean zza(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
            switch (n) {
                default: {
                    return false;
                }
                case 1: {
                    this.onPostInitComplete(parcel.readInt(), parcel.readStrongBinder(), com.google.android.gms.internal.common.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                    break;
                }
                case 2: {
                    this.zza(parcel.readInt(), com.google.android.gms.internal.common.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                    break;
                }
                case 3: {
                    this.zza(parcel.readInt(), parcel.readStrongBinder(), com.google.android.gms.internal.common.zzc.zza(parcel, com.google.android.gms.common.internal.zzb.CREATOR));
                    break;
                }
            }
            parcel2.writeNoException();
            return true;
        }
    }
}
