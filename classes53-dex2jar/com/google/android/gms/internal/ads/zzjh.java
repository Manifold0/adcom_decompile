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
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.RemoteCreator;

@zzadh
public final class zzjh extends RemoteCreator<zzkv>
{
    @VisibleForTesting
    public zzjh() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }
    
    public final zzks zza(Context zza, final zzjn zzjn, final String s, final zzxn zzxn, final int n) {
        try {
            zza = (RemoteCreator$RemoteCreatorException)((zzkv)this.getRemoteCreatorInstance((Context)zza)).zza(ObjectWrapper.wrap((Object)zza), zzjn, s, zzxn, 12451000, n);
            if (zza == null) {
                return null;
            }
            final IInterface queryLocalInterface = ((IBinder)zza).queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzks) {
                return (zzks)queryLocalInterface;
            }
            zza = (RemoteCreator$RemoteCreatorException)new zzku((IBinder)zza);
            return (zzks)zza;
        }
        catch (RemoteException ex) {}
        catch (RemoteCreator$RemoteCreatorException zza) {
            goto Label_0069;
        }
    }
}
