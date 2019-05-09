// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;

public final class zzhz
{
    private final byte[] zzalp;
    private int zzalq;
    private int zzalr;
    private final /* synthetic */ zzhx zzals;
    
    private zzhz(final zzhx zzals, final byte[] zzalp) {
        this.zzals = zzals;
        this.zzalp = zzalp;
    }
    
    public final void zzbd() {
        synchronized (this) {
            try {
                if (this.zzals.zzalo) {
                    this.zzals.zzaln.zzc(this.zzalp);
                    this.zzals.zzaln.zzg(this.zzalq);
                    this.zzals.zzaln.zzh(this.zzalr);
                    this.zzals.zzaln.zza(null);
                    this.zzals.zzaln.zzbd();
                }
            }
            catch (RemoteException ex) {
                zzane.zza("Clearcut log failed", (Throwable)ex);
            }
        }
    }
    
    public final zzhz zzr(final int zzalq) {
        this.zzalq = zzalq;
        return this;
    }
    
    public final zzhz zzs(final int zzalr) {
        this.zzalr = zzalr;
        return this;
    }
}
