// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzds extends zzb implements zzdr
{
    public zzds() {
        super("com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 2: {
                this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzer.CREATOR));
                break;
            }
            case 3: {
                this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzet.CREATOR));
                break;
            }
            case 4: {
                this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzfd.CREATOR));
                break;
            }
        }
        return true;
    }
}
