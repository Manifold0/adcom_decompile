// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;

@zzadh
public final class zzafm
{
    public final boolean zzcgv;
    public final zzafy zzcgw;
    public final zzhn zzcgx;
    public final zzajc zzcgy;
    public final zzmz zzcgz;
    public final zzagh zzcha;
    public final zzwu zzchb;
    public final zzagi zzchc;
    public final zzagj zzchd;
    public final zzaav zzche;
    public final zzajg zzchf;
    public final zzafr zzchg;
    
    private zzafm(final zzafy zzafy, final zzhn zzcgx, final zzajc zzcgy, final zzmz zzcgz, final zzagh zzcha, final zzwu zzchb, final zzagi zzchc, final zzagj zzchd, final zzaav zzche, final zzajg zzchf, final boolean b, final zzafr zzchg) {
        this.zzcgw = null;
        this.zzcgx = zzcgx;
        this.zzcgy = zzcgy;
        this.zzcgz = zzcgz;
        this.zzcha = zzcha;
        this.zzchb = zzchb;
        this.zzchc = zzchc;
        this.zzchd = zzchd;
        this.zzche = zzche;
        this.zzchf = zzchf;
        this.zzcgv = true;
        this.zzchg = zzchg;
    }
    
    public static zzafm zzm(final Context context) {
        zzbv.zzfi().initialize(context);
        final zzagn zzagn = new zzagn(context);
        return new zzafm(null, new zzhq(), new zzajd(), new zzmy(), new zzagf(context, zzagn.zzog()), new zzwv(), new zzagl(), new zzagm(), new zzaau(), new zzaje(), true, zzagn);
    }
}
