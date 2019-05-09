// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import android.os.Parcel;

public abstract class zzam extends zzb implements zzal
{
    public zzam() {
        super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zza(com.google.android.gms.internal.auth.zzc.zza(parcel, ProxyResponse.CREATOR));
                break;
            }
            case 2: {
                this.zzb(parcel.readString());
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
