// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzos;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzajh;

final class zzbj implements Runnable
{
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ String zzaal;
    private final /* synthetic */ zzajh zzaam;
    
    zzbj(final zzbc zzaaf, final String zzaal, final zzajh zzaam) {
        this.zzaaf = zzaaf;
        this.zzaal = zzaal;
        this.zzaam = zzaam;
    }
    
    @Override
    public final void run() {
        try {
            ((zzrf)this.zzaaf.zzvw.zzadi.get((Object)this.zzaal)).zzb((zzqs)this.zzaam.zzcoj);
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
}
