// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;

public abstract class zzob extends zzek implements zzoa
{
    public zzob() {
        super("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final String zzjn = this.zzjn();
                parcel2.writeNoException();
                parcel2.writeString(zzjn);
                break;
            }
            case 2: {
                final String content = this.getContent();
                parcel2.writeNoException();
                parcel2.writeString(content);
                break;
            }
            case 3: {
                this.zzg(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                break;
            }
            case 4: {
                this.recordClick();
                parcel2.writeNoException();
                break;
            }
            case 5: {
                this.recordImpression();
                parcel2.writeNoException();
                break;
            }
        }
        return true;
    }
}
