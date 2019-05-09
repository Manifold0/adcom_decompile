// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import android.os.Parcel;
import com.google.android.gms.internal.auth-api.zzd;

public abstract class zzr extends zzd implements zzq
{
    public zzr() {
        super("com.google.android.gms.auth.api.signin.internal.IRevocationService");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zzj();
                break;
            }
            case 2: {
                this.zzk();
                break;
            }
        }
        return true;
    }
}
