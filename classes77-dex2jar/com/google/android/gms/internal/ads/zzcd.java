// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.ConditionVariable;

final class zzcd implements Runnable
{
    private final /* synthetic */ zzcc zzpx;
    
    zzcd(final zzcc zzpx) {
        this.zzpx = zzpx;
    }
    
    @Override
    public final void run() {
        if (this.zzpx.zzpv != null) {
            return;
        }
        synchronized (zzcc.zzpt) {
            if (this.zzpx.zzpv != null) {
                return;
            }
        }
        boolean booleanValue;
        final boolean b = booleanValue = (boolean)zzkb.zzik().zzd(zznk.zzbap);
        while (true) {
            if (!b) {
                break Label_0088;
            }
            try {
                zzcc.zzpu = new zzhx(this.zzpx.zzps.zzrt, "ADSHIELD", (String)null);
                booleanValue = b;
                this.zzpx.zzpv = booleanValue;
                zzcc.zzpt.open();
            }
            // monitorexit(conditionVariable)
            catch (Throwable t) {
                booleanValue = false;
                continue;
            }
            break;
        }
    }
}
