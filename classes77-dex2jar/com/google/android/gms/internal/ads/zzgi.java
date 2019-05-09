// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzgi implements Runnable
{
    private final /* synthetic */ zzgh zzahx;
    
    zzgi(final zzgh zzahx) {
        this.zzahx = zzahx;
    }
    
    @Override
    public final void run() {
        Label_0106: {
            synchronized (this.zzahx.mLock) {
                if (this.zzahx.zzahr && this.zzahx.zzahs) {
                    zzgh.zza(this.zzahx, false);
                    zzakb.zzck("App went background");
                    for (final zzgj zzgj : this.zzahx.zzaht) {
                        try {
                            zzgj.zzh(false);
                        }
                        catch (Exception ex) {
                            zzane.zzb("", (Throwable)ex);
                        }
                    }
                    break Label_0106;
                }
            }
            zzakb.zzck("App is still foreground");
        }
    }
    // monitorexit(o)
}
