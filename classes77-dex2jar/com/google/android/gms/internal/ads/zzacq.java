// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.ads.internal.gmsg.zzv;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.zzbc;
import android.content.Context;

@zzadh
public final class zzacq implements zzacm<zzaqw>
{
    private final Context mContext;
    private String zzaae;
    private final zzci zzbjc;
    private final zzbc zzcbc;
    private zzanz<zzaqw> zzcbm;
    private final zzab zzcbn;
    private final zzpe zzcbo;
    private final zzang zzzw;
    
    public zzacq(Context mContext, final zzbc zzcbc, final String zzaae, final zzci zzbjc, final zzang zzzw) {
        zzakb.zzdj("Webview loading for native ads.");
        this.mContext = mContext;
        this.zzcbc = zzcbc;
        this.zzbjc = zzbjc;
        this.zzzw = zzzw;
        this.zzaae = zzaae;
        zzbv.zzel();
        mContext = this.mContext;
        final zzanz<zzaqw> zza = zzarc.zza(mContext, this.zzzw, (String)zzkb.zzik().zzd(zznk.zzbbp), this.zzbjc, this.zzcbc.zzbi());
        this.zzcbn = new zzab(this.mContext);
        this.zzcbo = new zzpe(zzcbc, zzaae);
        zzanm.zza(this.zzcbm = (zzanz<zzaqw>)zzano.zza((zzanz<Object>)zza, (zzanj<? super Object, ?>)new zzacr(this), zzaoe.zzcvz), "WebViewNativeAdsUtil.constructor");
    }
    
    @Override
    public final void zza(final String s, final zzv<? super zzaqw> zzv) {
        zzano.zza(this.zzcbm, new zzacx(this, s, zzv), zzaoe.zzcvy);
    }
    
    @Override
    public final void zza(final String s, final JSONObject jsonObject) {
        zzano.zza(this.zzcbm, new zzacz(this, s, jsonObject), zzaoe.zzcvy);
    }
    
    @Override
    public final void zzb(final String s, final zzv<? super zzaqw> zzv) {
        zzano.zza(this.zzcbm, new zzacy(this, s, zzv), zzaoe.zzcvy);
    }
    
    @Override
    public final zzanz<JSONObject> zzh(final JSONObject jsonObject) {
        return zzano.zza(this.zzcbm, (zzanj<? super zzaqw, ? extends JSONObject>)new zzacs(this, jsonObject), zzaoe.zzcvy);
    }
    
    @Override
    public final zzanz<JSONObject> zzi(final JSONObject jsonObject) {
        return zzano.zza(this.zzcbm, (zzanj<? super zzaqw, ? extends JSONObject>)new zzact(this, jsonObject), zzaoe.zzcvy);
    }
    
    @Override
    public final zzanz<JSONObject> zzj(final JSONObject jsonObject) {
        return zzano.zza(this.zzcbm, (zzanj<? super zzaqw, ? extends JSONObject>)new zzacu(this, jsonObject), zzaoe.zzcvy);
    }
    
    @Override
    public final zzanz<JSONObject> zzk(final JSONObject jsonObject) {
        return zzano.zza(this.zzcbm, (zzanj<? super zzaqw, ? extends JSONObject>)new zzacv(this, jsonObject), zzaoe.zzcvy);
    }
    
    @Override
    public final void zzmc() {
        zzano.zza(this.zzcbm, new zzada(this), zzaoe.zzcvy);
    }
}
