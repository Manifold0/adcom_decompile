// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONObject;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzv;
import android.content.Context;

@zzadh
public final class zzff implements zzfo
{
    private final Context mContext;
    private final zzet zzafq;
    private final zzv<zzwb> zzafs;
    private final zzv<zzwb> zzaft;
    private final zzv<zzwb> zzafu;
    private final zzab zzafw;
    private zzvs zzafx;
    private boolean zzafy;
    private final zzv<zzwb> zzafz;
    
    public zzff(final zzet zzafq, final zzvf zzvf, final Context mContext) {
        this.zzafs = new zzfk(this);
        this.zzaft = new zzfl(this);
        this.zzafu = new zzfm(this);
        this.zzafz = new zzfn(this);
        this.zzafq = zzafq;
        this.mContext = mContext;
        this.zzafw = new zzab(this.mContext);
        (this.zzafx = zzvf.zzb((zzci)null)).zza(new zzfg(this), new zzfh(this));
        final String value = String.valueOf(this.zzafq.zzaet.zzfy());
        String concat;
        if (value.length() != 0) {
            concat = "Core JS tracking ad unit: ".concat(value);
        }
        else {
            concat = new String("Core JS tracking ad unit: ");
        }
        zzakb.zzck(concat);
    }
    
    final void zza(final zzwb zzwb) {
        zzwb.zza("/updateActiveView", this.zzafs);
        zzwb.zza("/untrackActiveViewUnit", this.zzaft);
        zzwb.zza("/visibilityChanged", this.zzafu);
        if (zzbv.zzfh().zzs(this.mContext)) {
            zzwb.zza("/logScionEvent", this.zzafz);
        }
    }
    
    final void zzb(final zzwb zzwb) {
        zzwb.zzb("/visibilityChanged", this.zzafu);
        zzwb.zzb("/untrackActiveViewUnit", this.zzaft);
        zzwb.zzb("/updateActiveView", this.zzafs);
        if (zzbv.zzfh().zzs(this.mContext)) {
            zzwb.zzb("/logScionEvent", this.zzafz);
        }
    }
    
    @Override
    public final void zzb(final JSONObject jsonObject, final boolean b) {
        this.zzafx.zza(new zzfi(this, jsonObject), new zzaon());
    }
    
    @Override
    public final boolean zzgk() {
        return this.zzafy;
    }
    
    @Override
    public final void zzgl() {
        this.zzafx.zza(new zzfj(this), new zzaon());
        this.zzafx.release();
    }
}
