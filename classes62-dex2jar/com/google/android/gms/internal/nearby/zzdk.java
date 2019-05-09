// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzdk extends zzb implements zzdj
{
    public zzdk() {
        super("com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 2: {
                this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzeh.CREATOR));
                break;
            }
            case 3: {
                this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzen.CREATOR));
                break;
            }
            case 4: {
                this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzep.CREATOR));
                break;
            }
            case 5: {
                this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzef.CREATOR));
                break;
            }
        }
        return true;
    }
}
