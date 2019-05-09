// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzkk;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzrc;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzxn;
import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzko;

@zzadh
public final class zzak extends zzko
{
    private final Context mContext;
    private final zzw zzwc;
    private final zzxn zzwh;
    private zzkh zzxs;
    private zzjn zzxx;
    private PublisherAdViewOptions zzxy;
    private zzpl zzyb;
    private zzlg zzyd;
    private final String zzye;
    private final zzang zzyf;
    private zzqw zzyk;
    private zzrl zzyl;
    private zzqz zzym;
    private SimpleArrayMap<String, zzrc> zzyn;
    private SimpleArrayMap<String, zzrf> zzyo;
    private zzri zzyp;
    
    public zzak(final Context mContext, final String zzye, final zzxn zzwh, final zzang zzyf, final zzw zzwc) {
        this.mContext = mContext;
        this.zzye = zzye;
        this.zzwh = zzwh;
        this.zzyf = zzyf;
        this.zzyo = (SimpleArrayMap<String, zzrf>)new SimpleArrayMap();
        this.zzyn = (SimpleArrayMap<String, zzrc>)new SimpleArrayMap();
        this.zzwc = zzwc;
    }
    
    public final void zza(final PublisherAdViewOptions zzxy) {
        this.zzxy = zzxy;
    }
    
    public final void zza(final zzpl zzyb) {
        this.zzyb = zzyb;
    }
    
    public final void zza(final zzqw zzyk) {
        this.zzyk = zzyk;
    }
    
    public final void zza(final zzqz zzym) {
        this.zzym = zzym;
    }
    
    public final void zza(final zzri zzyp, final zzjn zzxx) {
        this.zzyp = zzyp;
        this.zzxx = zzxx;
    }
    
    public final void zza(final zzrl zzyl) {
        this.zzyl = zzyl;
    }
    
    public final void zza(final String s, final zzrf zzrf, final zzrc zzrc) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzyo.put((Object)s, (Object)zzrf);
        this.zzyn.put((Object)s, (Object)zzrc);
    }
    
    public final void zzb(final zzkh zzxs) {
        this.zzxs = zzxs;
    }
    
    public final void zzb(final zzlg zzyd) {
        this.zzyd = zzyd;
    }
    
    public final zzkk zzdh() {
        return (zzkk)new zzah(this.mContext, this.zzye, this.zzwh, this.zzyf, this.zzxs, this.zzyk, this.zzyl, this.zzym, this.zzyo, this.zzyn, this.zzyb, this.zzyd, this.zzwc, this.zzyp, this.zzxx, this.zzxy);
    }
}
