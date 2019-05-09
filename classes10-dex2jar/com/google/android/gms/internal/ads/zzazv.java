// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.io.PrintWriter;

final class zzazv extends zzazs
{
    private final zzazt zzdpb;
    
    zzazv() {
        this.zzdpb = new zzazt();
    }
    
    @Override
    public final void zza(Throwable zza, final PrintWriter printWriter) {
        zza.printStackTrace(printWriter);
        zza = (Throwable)this.zzdpb.zza(zza, false);
        if (zza == null) {
            return;
        }
        synchronized (zza) {
            for (final Throwable t : zza) {
                printWriter.print("Suppressed: ");
                t.printStackTrace(printWriter);
            }
        }
    }
    // monitorexit(zza)
}
