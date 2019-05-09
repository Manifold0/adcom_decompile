// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;

public abstract class zzlk extends zzek implements zzlj
{
    public zzlk() {
        super("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                this.zza();
                parcel2.writeNoException();
                break;
            }
            case 2: {
                this.setAppVolume(parcel.readFloat());
                parcel2.writeNoException();
                break;
            }
            case 3: {
                this.zzt(parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 4: {
                this.setAppMuted(zzel.zza(parcel));
                parcel2.writeNoException();
                break;
            }
            case 5: {
                this.zzb(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                break;
            }
            case 6: {
                this.zza(parcel.readString(), IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 7: {
                final float zzdo = this.zzdo();
                parcel2.writeNoException();
                parcel2.writeFloat(zzdo);
                break;
            }
            case 8: {
                final boolean zzdp = this.zzdp();
                parcel2.writeNoException();
                zzel.zza(parcel2, zzdp);
                break;
            }
        }
        return true;
    }
}
