// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.content.Context;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.dynamic.RemoteCreator;

@zzadh
public final class zzahi extends RemoteCreator<zzahc>
{
    public zzahi() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }
    
    public final zzagz zza(Context zza, final zzxn zzxn) {
        try {
            zza = (RemoteCreator$RemoteCreatorException)((zzahc)this.getRemoteCreatorInstance((Context)zza)).zza(ObjectWrapper.wrap((Object)zza), zzxn, 12451000);
            if (zza == null) {
                return null;
            }
            final IInterface queryLocalInterface = ((IBinder)zza).queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
            if (queryLocalInterface instanceof zzagz) {
                return (zzagz)queryLocalInterface;
            }
            zza = (RemoteCreator$RemoteCreatorException)new zzahb((IBinder)zza);
            return (zzagz)zza;
        }
        catch (RemoteException ex) {}
        catch (RemoteCreator$RemoteCreatorException zza) {
            goto Label_0062;
        }
    }
}
