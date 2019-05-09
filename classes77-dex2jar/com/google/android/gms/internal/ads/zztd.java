// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class zztd implements zzts
{
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzbny;
    
    zztd(final zztc zztc, final String val$name, final String zzbny) {
        this.val$name = val$name;
        this.zzbny = zzbny;
    }
    
    @Override
    public final void zzb(final zztt zztt) throws RemoteException {
        if (zztt.zzboe != null) {
            zztt.zzboe.onAppEvent(this.val$name, this.zzbny);
        }
    }
}
