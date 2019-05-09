// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONObject;
import com.google.android.gms.ads.internal.gmsg.zzv;

@zzadh
public final class zzfb implements zzfo
{
    private final zzet zzafq;
    private final zzaqw zzafr;
    private final zzv<zzaqw> zzafs;
    private final zzv<zzaqw> zzaft;
    private final zzv<zzaqw> zzafu;
    
    public zzfb(final zzet zzafq, final zzaqw zzafr) {
        this.zzafs = new zzfc(this);
        this.zzaft = new zzfd(this);
        this.zzafu = new zzfe(this);
        this.zzafq = zzafq;
        this.zzafr = zzafr;
        final zzaqw zzafr2 = this.zzafr;
        zzafr2.zza("/updateActiveView", this.zzafs);
        zzafr2.zza("/untrackActiveViewUnit", this.zzaft);
        zzafr2.zza("/visibilityChanged", this.zzafu);
        final String value = String.valueOf(this.zzafq.zzaet.zzfy());
        String concat;
        if (value.length() != 0) {
            concat = "Custom JS tracking ad unit: ".concat(value);
        }
        else {
            concat = new String("Custom JS tracking ad unit: ");
        }
        zzakb.zzck(concat);
    }
    
    @Override
    public final void zzb(final JSONObject jsonObject, final boolean b) {
        if (!b) {
            this.zzafr.zzb("AFMA_updateActiveView", jsonObject);
            return;
        }
        this.zzafq.zzb(this);
    }
    
    @Override
    public final boolean zzgk() {
        return true;
    }
    
    @Override
    public final void zzgl() {
        final zzaqw zzafr = this.zzafr;
        zzafr.zzb("/visibilityChanged", this.zzafu);
        zzafr.zzb("/untrackActiveViewUnit", this.zzaft);
        zzafr.zzb("/updateActiveView", this.zzafs);
    }
}
