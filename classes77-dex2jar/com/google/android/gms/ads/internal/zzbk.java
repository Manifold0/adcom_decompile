// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzqs;

final class zzbk implements Runnable
{
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzqs zzwx;
    
    zzbk(final zzbc zzaaf, final zzqs zzwx) {
        this.zzaaf = zzaaf;
        this.zzwx = zzwx;
    }
    
    @Override
    public final void run() {
        try {
            ((zzrf)this.zzaaf.zzvw.zzadi.get((Object)this.zzwx.getCustomTemplateId())).zzb(this.zzwx);
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
