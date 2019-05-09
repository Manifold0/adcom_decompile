// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.nearby.zzc;
import android.os.Parcel;
import com.google.android.gms.internal.nearby.zzb;

public abstract class zzn extends zzb implements zzm
{
    public zzn() {
        super("com.google.android.gms.nearby.messages.internal.IMessageListener");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zza(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzaf.CREATOR));
                break;
            }
            case 2: {
                this.zzb(com.google.android.gms.internal.nearby.zzc.zza(parcel, zzaf.CREATOR));
                break;
            }
            case 4: {
                this.zza(parcel.createTypedArrayList((Parcelable$Creator)Update.CREATOR));
                break;
            }
        }
        return true;
    }
}
