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
public final class zzjg extends RemoteCreator<zzkq>
{
    public zzjg() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }
    
    public final zzkn zza(Context zza, final String s, final zzxn zzxn) {
        try {
            zza = (RemoteCreator$RemoteCreatorException)((zzkq)this.getRemoteCreatorInstance((Context)zza)).zza(ObjectWrapper.wrap((Object)zza), s, zzxn, 12451000);
            if (zza == null) {
                return null;
            }
            final IInterface queryLocalInterface = ((IBinder)zza).queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            if (queryLocalInterface instanceof zzkn) {
                return (zzkn)queryLocalInterface;
            }
            zza = (RemoteCreator$RemoteCreatorException)new zzkp((IBinder)zza);
            return (zzkn)zza;
        }
        catch (RemoteException ex) {}
        catch (RemoteCreator$RemoteCreatorException zza) {
            goto Label_0065;
        }
    }
}
