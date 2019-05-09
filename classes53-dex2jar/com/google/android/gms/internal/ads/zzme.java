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
public final class zzme extends RemoteCreator<zzlm>
{
    public zzme() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }
    
    public final zzlj zzg(final Context context) {
        try {
            final IBinder zza = ((zzlm)this.getRemoteCreatorInstance(context)).zza(ObjectWrapper.wrap((Object)context), 12451000);
            if (zza == null) {
                return null;
            }
            final IInterface queryLocalInterface = zza.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (queryLocalInterface instanceof zzlj) {
                return (zzlj)queryLocalInterface;
            }
            return new zzll(zza);
        }
        catch (RemoteException ex) {
            zzane.zzc("Could not get remote MobileAdsSettingManager.", (Throwable)ex);
            return null;
        }
        catch (RemoteCreator$RemoteCreatorException ex2) {
            zzane.zzc("Could not get remote MobileAdsSettingManager.", (Throwable)ex2);
            return null;
        }
    }
}
