// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcel;

public abstract class zzagv extends zzek implements zzagu
{
    public zzagv() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardItem");
    }
    
    protected final boolean dispatchTransaction(int amount, final Parcel parcel, final Parcel parcel2, final int n) throws RemoteException {
        switch (amount) {
            default: {
                return false;
            }
            case 1: {
                final String type = this.getType();
                parcel2.writeNoException();
                parcel2.writeString(type);
                break;
            }
            case 2: {
                amount = this.getAmount();
                parcel2.writeNoException();
                parcel2.writeInt(amount);
                break;
            }
        }
        return true;
    }
}
