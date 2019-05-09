// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import android.view.View;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.RemoteCreator;

@zzadh
public final class zzrw extends RemoteCreator<zzqi>
{
    @VisibleForTesting
    public zzrw() {
        super("com.google.android.gms.ads.NativeAdViewHolderDelegateCreatorImpl");
    }
    
    public final zzqf zzb(View zza, final HashMap<String, View> hashMap, final HashMap<String, View> hashMap2) {
        try {
            zza = (RemoteCreator$RemoteCreatorException)((zzqi)this.getRemoteCreatorInstance(((View)zza).getContext())).zza(ObjectWrapper.wrap((Object)zza), ObjectWrapper.wrap((Object)hashMap), ObjectWrapper.wrap((Object)hashMap2));
            if (zza == null) {
                return null;
            }
            final IInterface queryLocalInterface = ((IBinder)zza).queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
            if (queryLocalInterface instanceof zzqf) {
                return (zzqf)queryLocalInterface;
            }
            zza = (RemoteCreator$RemoteCreatorException)new zzqh((IBinder)zza);
            return (zzqf)zza;
        }
        catch (RemoteException ex) {}
        catch (RemoteCreator$RemoteCreatorException zza) {
            goto Label_0076;
        }
    }
}
