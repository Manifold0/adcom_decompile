// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.account;

import android.os.RemoteException;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.auth.zzc;
import android.accounts.Account;
import android.os.Parcel;

public abstract class zzb extends com.google.android.gms.internal.auth.zzb implements zza
{
    public zzb() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zzc(com.google.android.gms.internal.auth.zzc.zza(parcel, (android.os.Parcelable$Creator<Account>)Account.CREATOR));
                break;
            }
            case 2: {
                this.zza(com.google.android.gms.internal.auth.zzc.zza(parcel));
                break;
            }
        }
        return true;
    }
}
