// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzqs;

final class zzv implements Runnable
{
    private final /* synthetic */ zzq zzwt;
    private final /* synthetic */ zzqs zzwx;
    
    zzv(final zzq zzwt, final zzqs zzwx) {
        this.zzwt = zzwt;
        this.zzwx = zzwx;
    }
    
    @Override
    public final void run() {
        try {
            ((zzrf)this.zzwt.zzvw.zzadi.get((Object)this.zzwx.getCustomTemplateId())).zzb(this.zzwx);
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
