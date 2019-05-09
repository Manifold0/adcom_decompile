// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.ads.internal.zzw;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.internal.zzd;

@zzadh
public final class zzagr extends zzd implements zzahu
{
    private static zzagr zzcle;
    private boolean zzclf;
    private final zzago zzclg;
    private boolean zzyu;
    @VisibleForTesting
    private final zzaix zzyv;
    
    public zzagr(final Context context, final zzw zzw, final zzjn zzjn, final zzxn zzxn, final zzang zzang) {
        super(context, zzjn, null, zzxn, zzang, zzw);
        zzagr.zzcle = this;
        this.zzyv = new zzaix(context, null);
        this.zzclg = new zzago(this.zzvw, this.zzwh, this, this, this);
    }
    
    private static zzaji zzc(final zzaji zzaji) {
        zzakb.v("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            final JSONObject zzb = zzafs.zzb(zzaji.zzcos);
            zzb.remove("impression_urls");
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("pubid", (Object)zzaji.zzcgs.zzacp);
            return new zzaji(zzaji.zzcgs, zzaji.zzcos, new zzwy(Arrays.asList(new zzwx(zzb.toString(), null, Arrays.asList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jsonObject.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1L)), (long)zzkb.zzik().zzd(zznk.zzbao), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1L, 0, 1, null, 0, -1, -1L, false), zzaji.zzacv, zzaji.errorCode, zzaji.zzcoh, zzaji.zzcoi, zzaji.zzcob, zzaji.zzcoq, null);
        }
        catch (JSONException ex) {
            zzakb.zzb("Unable to generate ad state for non-mediated rewarded video.", (Throwable)ex);
            return new zzaji(zzaji.zzcgs, zzaji.zzcos, null, zzaji.zzacv, 0, zzaji.zzcoh, zzaji.zzcoi, zzaji.zzcob, zzaji.zzcoq, null);
        }
    }
    
    public static zzagr zzox() {
        return zzagr.zzcle;
    }
    
    @Override
    public final void destroy() {
        this.zzclg.destroy();
        super.destroy();
    }
    
    public final boolean isLoaded() {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return this.zzvw.zzact == null && this.zzvw.zzacu == null && this.zzvw.zzacw != null;
    }
    
    public final void onContextChanged(final Context context) {
        this.zzclg.onContextChanged(context);
    }
    
    @Override
    public final void onRewardedVideoAdClosed() {
        if (zzbv.zzfh().zzw(this.zzvw.zzrt)) {
            this.zzyv.zzx(false);
        }
        this.zzbn();
    }
    
    @Override
    public final void onRewardedVideoAdLeftApplication() {
        this.zzbo();
    }
    
    @Override
    public final void onRewardedVideoAdOpened() {
        if (zzbv.zzfh().zzw(this.zzvw.zzrt)) {
            this.zzyv.zzx(true);
        }
        this.zza(this.zzvw.zzacw, false);
        this.zzbp();
    }
    
    @Override
    public final void onRewardedVideoCompleted() {
        this.zzclg.zzow();
        this.zzbu();
    }
    
    @Override
    public final void onRewardedVideoStarted() {
        this.zzclg.zzov();
        this.zzbt();
    }
    
    @Override
    public final void pause() {
        this.zzclg.pause();
    }
    
    @Override
    public final void resume() {
        this.zzclg.resume();
    }
    
    @Override
    public final void setImmersiveMode(final boolean zzyu) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = zzyu;
    }
    
    public final void zza(final zzahk zzahk) {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty((CharSequence)zzahk.zzacp)) {
            zzakb.zzdk("Invalid ad unit id. Aborting.");
            zzakk.zzcrm.post((Runnable)new zzags(this));
            return;
        }
        this.zzclf = false;
        this.zzvw.zzacp = zzahk.zzacp;
        this.zzyv.setAdUnitId(zzahk.zzacp);
        super.zzb(zzahk.zzccv);
    }
    
    public final void zza(final zzaji zzacx, final zznx zznx) {
        if (zzacx.errorCode != -2) {
            zzakk.zzcrm.post((Runnable)new zzagt(this, zzacx));
            return;
        }
        this.zzvw.zzacx = zzacx;
        if (zzacx.zzcod == null) {
            this.zzvw.zzacx = zzc(zzacx);
        }
        this.zzclg.zzou();
    }
    
    public final boolean zza(final zzajh zzajh, final zzajh zzajh2) {
        this.zzb(zzajh2, false);
        return zzago.zza(zzajh, zzajh2);
    }
    
    @Override
    protected final boolean zza(final zzjj zzjj, final zzajh zzajh, final boolean b) {
        return false;
    }
    
    @Override
    protected final void zzbn() {
        this.zzvw.zzacw = null;
        super.zzbn();
    }
    
    @Override
    public final void zzc(@Nullable zzaig zzd) {
        zzd = this.zzclg.zzd(zzd);
        if (zzbv.zzfh().zzw(this.zzvw.zzrt) && zzd != null) {
            zzbv.zzfh().zza(this.zzvw.zzrt, zzbv.zzfh().zzab(this.zzvw.zzrt), this.zzvw.zzacp, zzd.type, zzd.zzcmk);
        }
        this.zza(zzd);
    }
    
    @Nullable
    public final zzaib zzca(final String s) {
        return this.zzclg.zzca(s);
    }
    
    @Override
    public final void zzdm() {
        this.onAdClicked();
    }
    
    public final void zzoy() {
        Preconditions.checkMainThread("showAd must be called on the main UI thread.");
        if (!this.isLoaded()) {
            zzakb.zzdk("The reward video has not loaded.");
            return;
        }
        this.zzclg.zzw(this.zzyu);
    }
}
