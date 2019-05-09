// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.content.Context;
import android.app.Activity;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.dynamic.RemoteCreator;

@zzadh
public final class zzaao extends RemoteCreator<zzaas>
{
    public zzaao() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }
    
    public final zzaap zze(final Activity activity) {
        try {
            final IBinder zzp = ((zzaas)this.getRemoteCreatorInstance((Context)activity)).zzp(ObjectWrapper.wrap((Object)activity));
            if (zzp == null) {
                return null;
            }
            final IInterface queryLocalInterface = zzp.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            if (queryLocalInterface instanceof zzaap) {
                return (zzaap)queryLocalInterface;
            }
            return new zzaar(zzp);
        }
        catch (RemoteException ex) {
            zzane.zzc("Could not create remote AdOverlay.", (Throwable)ex);
            return null;
        }
        catch (RemoteCreator$RemoteCreatorException ex2) {
            zzane.zzc("Could not create remote AdOverlay.", (Throwable)ex2);
            return null;
        }
    }
}
