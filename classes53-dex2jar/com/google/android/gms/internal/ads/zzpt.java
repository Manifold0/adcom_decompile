// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.List;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzpt extends zzek implements zzps
{
    public zzpt() {
        super("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }
    
    public static zzps zzg(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
        if (queryLocalInterface instanceof zzps) {
            return (zzps)queryLocalInterface;
        }
        return new zzpu(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 2: {
                final String text = this.getText();
                parcel2.writeNoException();
                parcel2.writeString(text);
                break;
            }
            case 3: {
                final List<zzpw> zzjr = this.zzjr();
                parcel2.writeNoException();
                parcel2.writeList((List)zzjr);
                break;
            }
        }
        return true;
    }
}
