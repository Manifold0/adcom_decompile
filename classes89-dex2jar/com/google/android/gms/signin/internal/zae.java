// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.base.zac;
import com.google.android.gms.common.ConnectionResult;
import android.os.Parcel;
import com.google.android.gms.internal.base.zab;

public abstract class zae extends zab implements zad
{
    public zae() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }
    
    @Override
    protected boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 3: {
                this.zaa(com.google.android.gms.internal.base.zac.zaa(parcel, (android.os.Parcelable$Creator<ConnectionResult>)ConnectionResult.CREATOR), com.google.android.gms.internal.base.zac.zaa(parcel, zaa.CREATOR));
                break;
            }
            case 4: {
                this.zag(com.google.android.gms.internal.base.zac.zaa(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 6: {
                this.zah(com.google.android.gms.internal.base.zac.zaa(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 7: {
                this.zaa(com.google.android.gms.internal.base.zac.zaa(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR), com.google.android.gms.internal.base.zac.zaa(parcel, GoogleSignInAccount.CREATOR));
                break;
            }
            case 8: {
                this.zab(com.google.android.gms.internal.base.zac.zaa(parcel, zaj.CREATOR));
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
