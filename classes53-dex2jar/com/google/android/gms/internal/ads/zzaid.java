// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzaid extends zzek implements zzaic
{
    public zzaid() {
        super("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
    }
    
    public static zzaic zzaa(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.reward.mediation.client.IMediationRewardedVideoAdListener");
        if (queryLocalInterface instanceof zzaic) {
            return (zzaic)queryLocalInterface;
        }
        return new zzaie(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zzq(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 2: {
                this.zzc(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            }
            case 3: {
                this.zzr(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 4: {
                this.zzs(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 5: {
                this.zzt(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 6: {
                this.zzu(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 7: {
                this.zza(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), (zzaig)zzel.zza(parcel, (Parcelable$Creator)zzaig.CREATOR));
                break;
            }
            case 8: {
                this.zzv(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 9: {
                this.zzd(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                break;
            }
            case 10: {
                this.zzw(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 11: {
                this.zzx(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                break;
            }
            case 12: {
                this.zzc((Bundle)zzel.zza(parcel, Bundle.CREATOR));
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
