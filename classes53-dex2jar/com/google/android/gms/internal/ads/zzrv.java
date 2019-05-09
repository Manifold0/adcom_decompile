// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.widget.FrameLayout;
import android.content.Context;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.RemoteCreator;

@zzadh
public final class zzrv extends RemoteCreator<zzqd>
{
    @VisibleForTesting
    public zzrv() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }
    
    public final zzqa zzb(Context zza, final FrameLayout frameLayout, final FrameLayout frameLayout2) {
        try {
            zza = (RemoteCreator$RemoteCreatorException)((zzqd)this.getRemoteCreatorInstance((Context)zza)).zza(ObjectWrapper.wrap((Object)zza), ObjectWrapper.wrap((Object)frameLayout), ObjectWrapper.wrap((Object)frameLayout2), 12451000);
            if (zza == null) {
                return null;
            }
            final IInterface queryLocalInterface = ((IBinder)zza).queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegate");
            if (queryLocalInterface instanceof zzqa) {
                return (zzqa)queryLocalInterface;
            }
            zza = (RemoteCreator$RemoteCreatorException)new zzqc((IBinder)zza);
            return (zzqa)zza;
        }
        catch (RemoteException ex) {}
        catch (RemoteCreator$RemoteCreatorException zza) {
            goto Label_0075;
        }
    }
}
