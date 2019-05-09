// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import android.view.Window;
import android.graphics.Point;
import android.graphics.Rect;
import android.app.Activity;
import com.google.android.gms.internal.ads.zzasc;
import java.util.Map;
import java.util.HashMap;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzakq;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.gmsg.zzah;
import com.google.android.gms.internal.ads.zzaam;
import com.google.android.gms.ads.internal.gmsg.zzd;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzait;
import android.support.annotation.Nullable;
import android.graphics.Bitmap;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.ads.zzasf;
import com.google.android.gms.internal.ads.zzft;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.common.util.PlatformVersion;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.common.internal.Preconditions;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;
import org.json.JSONException;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzwy;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzaiq;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzaej;
import java.util.List;
import com.google.android.gms.internal.ads.zzwx;
import java.util.Collections;
import org.json.JSONObject;
import com.google.android.gms.internal.ads.zzafs;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzabm;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.internal.ads.zzahu;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzjn;
import android.content.Context;
import com.google.android.gms.internal.ads.zzago;
import com.google.android.gms.internal.ads.zzaix;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.gmsg.zzai;

@zzadh
@ParametersAreNonnullByDefault
public final class zzal extends zzi implements zzai, zzz
{
    private transient boolean zzyq;
    private int zzyr;
    private boolean zzys;
    private float zzyt;
    private boolean zzyu;
    private zzaix zzyv;
    private String zzyw;
    private final String zzyx;
    private final zzago zzyy;
    
    public zzal(final Context context, final zzjn zzjn, final String s, final zzxn zzxn, final zzang zzang, final zzw zzw) {
        super(context, zzjn, s, zzxn, zzang, zzw);
        this.zzyr = -1;
        this.zzyq = false;
        boolean b;
        if (zzjn != null && "reward_mb".equals(zzjn.zzarb)) {
            b = true;
        }
        else {
            b = false;
        }
        String zzyx;
        if (b) {
            zzyx = "/Rewarded";
        }
        else {
            zzyx = "/Interstitial";
        }
        this.zzyx = zzyx;
        zzago zzyy;
        if (b) {
            zzyy = new zzago(this.zzvw, this.zzwh, new zzan(this), this, this);
        }
        else {
            zzyy = null;
        }
        this.zzyy = zzyy;
    }
    
    @VisibleForTesting
    private static zzaji zzb(final zzaji zzaji) {
        try {
            final String string = zzafs.zzb(zzaji.zzcos).toString();
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("pubid", (Object)zzaji.zzcgs.zzacp);
            final zzwx zzwx = new zzwx(string, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jsonObject.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1L);
            final zzaej zzcos = zzaji.zzcos;
            return new zzaji(zzaji.zzcgs, new zzaej(zzaji.zzcgs, zzcos.zzbyq, zzcos.zzceo, Collections.emptyList(), Collections.emptyList(), zzcos.zzcep, true, zzcos.zzcer, Collections.emptyList(), zzcos.zzbsu, zzcos.orientation, zzcos.zzcet, zzcos.zzceu, zzcos.zzcev, zzcos.zzcew, zzcos.zzcex, null, zzcos.zzcez, zzcos.zzare, zzcos.zzcdd, zzcos.zzcfa, zzcos.zzcfb, zzcos.zzamj, zzcos.zzarf, zzcos.zzarg, null, Collections.emptyList(), Collections.emptyList(), zzcos.zzcfh, zzcos.zzcfi, zzcos.zzcdr, zzcos.zzcds, zzcos.zzbsr, zzcos.zzbss, zzcos.zzcfj, null, zzcos.zzcfl, zzcos.zzcfm, zzcos.zzced, zzcos.zzzl, 0, zzcos.zzcfp, Collections.emptyList(), zzcos.zzzm, zzcos.zzcfq), new zzwy(Collections.singletonList(zzwx), (long)zzkb.zzik().zzd(zznk.zzbao), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzcos.zzbsr, zzcos.zzbss, "", -1L, 0, 1, null, 0, -1, -1L, false), zzaji.zzacv, zzaji.errorCode, zzaji.zzcoh, zzaji.zzcoi, null, zzaji.zzcoq, null);
        }
        catch (JSONException ex) {
            zzakb.zzb("Unable to generate ad state for an interstitial ad with pooling.", (Throwable)ex);
            return zzaji;
        }
    }
    
    private final void zzb(final Bundle bundle) {
        zzbv.zzek().zzb(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, "gmob-apps", bundle, false);
    }
    
    private final boolean zzc(final boolean b) {
        return this.zzyy != null && b;
    }
    
    @Override
    public final void setImmersiveMode(final boolean zzyu) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = zzyu;
    }
    
    @Override
    public final void showInterstitial() {
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        if (this.zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq)) {
            this.zzyy.zzw(this.zzyu);
        }
        else {
            if (zzbv.zzfh().zzv(this.zzvw.zzrt)) {
                this.zzyw = zzbv.zzfh().zzy(this.zzvw.zzrt);
                final String value = String.valueOf(this.zzyw);
                final String value2 = String.valueOf(this.zzyx);
                String concat;
                if (value2.length() != 0) {
                    concat = value.concat(value2);
                }
                else {
                    concat = new String(value);
                }
                this.zzyw = concat;
            }
            if (this.zzvw.zzacw == null) {
                zzakb.zzdk("The interstitial has not loaded.");
                return;
            }
            while (true) {
                Label_0427: {
                    Label_0319: {
                        if (!(boolean)zzkb.zzik().zzd(zznk.zzazx)) {
                            break Label_0319;
                        }
                        if (this.zzvw.zzrt.getApplicationContext() == null) {
                            break Label_0427;
                        }
                        final String s = this.zzvw.zzrt.getApplicationContext().getPackageName();
                        if (!this.zzyq) {
                            zzakb.zzdk("It is not recommended to show an interstitial before onAdLoaded completes.");
                            final Bundle bundle = new Bundle();
                            bundle.putString("appid", s);
                            bundle.putString("action", "show_interstitial_before_load_finish");
                            this.zzb(bundle);
                        }
                        zzbv.zzek();
                        if (!zzakk.zzaq(this.zzvw.zzrt)) {
                            zzakb.zzdk("It is not recommended to show an interstitial when app is not in foreground.");
                            final Bundle bundle2 = new Bundle();
                            bundle2.putString("appid", s);
                            bundle2.putString("action", "show_interstitial_app_not_in_foreground");
                            this.zzb(bundle2);
                        }
                    }
                    if (this.zzvw.zzfp()) {
                        return;
                    }
                    if (this.zzvw.zzacw.zzceq && this.zzvw.zzacw.zzbtx != null) {
                        try {
                            if (zzkb.zzik().zzd(zznk.zzayr)) {
                                this.zzvw.zzacw.zzbtx.setImmersiveMode(this.zzyu);
                            }
                            this.zzvw.zzacw.zzbtx.showInterstitial();
                            return;
                        }
                        catch (RemoteException ex) {
                            zzakb.zzc("Could not show interstitial.", (Throwable)ex);
                            this.zzdj();
                            return;
                        }
                    }
                    else {
                        if (this.zzvw.zzacw.zzbyo == null) {
                            zzakb.zzdk("The interstitial failed to load.");
                            return;
                        }
                        if (this.zzvw.zzacw.zzbyo.zzuj()) {
                            zzakb.zzdk("The interstitial is already showing.");
                            return;
                        }
                        this.zzvw.zzacw.zzbyo.zzai(true);
                        this.zzvw.zzj(this.zzvw.zzacw.zzbyo.getView());
                        if (this.zzvw.zzacw.zzcob != null) {
                            this.zzvy.zza(this.zzvw.zzacv, this.zzvw.zzacw);
                        }
                        if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                            final zzajh zzacw = this.zzvw.zzacw;
                            if (zzacw.zzfz()) {
                                new zzfp(this.zzvw.zzrt, zzacw.zzbyo.getView()).zza(zzacw.zzbyo);
                            }
                            else {
                                zzacw.zzbyo.zzuf().zza(new zzam(this, zzacw));
                            }
                        }
                        Bitmap zzar;
                        if (this.zzvw.zzze) {
                            zzbv.zzek();
                            zzar = zzakk.zzar(this.zzvw.zzrt);
                        }
                        else {
                            zzar = null;
                        }
                        this.zzyr = zzbv.zzfe().zzb(zzar);
                        if ((boolean)zzkb.zzik().zzd(zznk.zzbbg) && zzar != null) {
                            new zzao(this, this.zzyr).zzqo();
                            return;
                        }
                        final zzaq zzaq = new zzaq(this.zzvw.zzze, this.zzdi(), false, 0.0f, -1, this.zzyu, this.zzvw.zzacw.zzzl, this.zzvw.zzacw.zzzm);
                        int n;
                        if ((n = this.zzvw.zzacw.zzbyo.getRequestedOrientation()) == -1) {
                            n = this.zzvw.zzacw.orientation;
                        }
                        final AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel((zzjd)this, this, this, this.zzvw.zzacw.zzbyo, n, this.zzvw.zzacr, this.zzvw.zzacw.zzcev, zzaq);
                        zzbv.zzei();
                        zzl.zza(this.zzvw.zzrt, adOverlayInfoParcel, true);
                        return;
                    }
                }
                final String s = this.zzvw.zzrt.getPackageName();
                continue;
            }
        }
    }
    
    @Override
    protected final zzaqw zza(final zzaji zzaji, @Nullable final zzx zzx, @Nullable final zzait zzait) throws zzarg {
        zzbv.zzel();
        final zzaqw zza = zzarc.zza(this.zzvw.zzrt, zzasi.zzb(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, zzaji.zzcoq);
        zza.zzuf().zza((zzjd)this, this, null, this, this, (boolean)zzkb.zzik().zzd(zznk.zzaxe), this, zzx, this, zzait);
        this.zza(zza);
        zza.zzdr(zzaji.zzcgs.zzcdi);
        zza.zza("/reward", new zzah(this));
        return zza;
    }
    
    public final void zza(final zzaji zzaji, final zznx zznx) {
        boolean b = true;
        if (zzaji.errorCode != -2) {
            super.zza(zzaji, zznx);
            return;
        }
        if (this.zzc(zzaji.zzcod != null)) {
            this.zzyy.zzou();
            return;
        }
        if (!(boolean)zzkb.zzik().zzd(zznk.zzayy)) {
            super.zza(zzaji, zznx);
            return;
        }
        if (zzaji.zzcos.zzceq) {
            b = false;
        }
        if (zza.zza(zzaji.zzcgs.zzccv) && b) {
            this.zzvw.zzacx = zzb(zzaji);
        }
        super.zza(this.zzvw.zzacx, zznx);
    }
    
    @Override
    public final void zza(final boolean zzys, final float zzyt) {
        this.zzys = zzys;
        this.zzyt = zzyt;
    }
    
    public final boolean zza(@Nullable final zzajh zzajh, final zzajh zzajh2) {
        boolean zza = false;
        if (this.zzc(zzajh2.zzceq)) {
            zza = zzago.zza(zzajh, zzajh2);
        }
        else if (super.zza(zzajh, zzajh2)) {
            if (!this.zzvw.zzfo() && this.zzvw.zzadu != null && zzajh2.zzcob != null) {
                this.zzvy.zza(this.zzvw.zzacv, zzajh2, this.zzvw.zzadu);
            }
            this.zzb(zzajh2, false);
            return true;
        }
        return zza;
    }
    
    @Override
    protected final boolean zza(final zzjj zzjj, final zzajh zzajh, final boolean b) {
        if (this.zzvw.zzfo() && zzajh.zzbyo != null) {
            zzbv.zzem();
            zzakq.zzi(zzajh.zzbyo);
        }
        return this.zzvv.zzdz();
    }
    
    @Override
    public final boolean zza(final zzjj zzjj, final zznx zznx) {
        if (this.zzvw.zzacw != null) {
            zzakb.zzdk("An interstitial is already loading. Aborting.");
            return false;
        }
        if (this.zzyv == null && zza.zza(zzjj) && zzbv.zzfh().zzv(this.zzvw.zzrt) && !TextUtils.isEmpty((CharSequence)this.zzvw.zzacp)) {
            this.zzyv = new zzaix(this.zzvw.zzrt, this.zzvw.zzacp);
        }
        return super.zza(zzjj, zznx);
    }
    
    @Override
    public final void zzb(final zzaig zzaig) {
        if (this.zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq)) {
            this.zza(this.zzyy.zzd(zzaig));
            return;
        }
        zzaig zzcfe = zzaig;
        if (this.zzvw.zzacw != null) {
            if (this.zzvw.zzacw.zzcfg != null) {
                zzbv.zzek();
                zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.zzcfg);
            }
            zzcfe = zzaig;
            if (this.zzvw.zzacw.zzcfe != null) {
                zzcfe = this.zzvw.zzacw.zzcfe;
            }
        }
        this.zza(zzcfe);
    }
    
    @Override
    protected final void zzbn() {
        this.zzdj();
        super.zzbn();
    }
    
    @Override
    protected final void zzbq() {
        zzaqw zzbyo;
        if (this.zzvw.zzacw != null) {
            zzbyo = this.zzvw.zzacw.zzbyo;
        }
        else {
            zzbyo = null;
        }
        final zzaji zzacx = this.zzvw.zzacx;
        if (zzacx != null && zzacx.zzcos != null && zzacx.zzcos.zzcfp && zzbyo != null && zzbv.zzfa().zzi(this.zzvw.zzrt)) {
            this.zzwb = zzbv.zzfa().zza(new StringBuilder(23).append(this.zzvw.zzacr.zzcve).append(".").append(this.zzvw.zzacr.zzcvf).toString(), zzbyo.getWebView(), "", "javascript", this.zzbz());
            if (this.zzwb != null && zzbyo.getView() != null) {
                zzbv.zzfa().zza(this.zzwb, zzbyo.getView());
                zzbv.zzfa().zzm(this.zzwb);
            }
        }
        super.zzbq();
        this.zzyq = true;
    }
    
    @Override
    public final void zzcb() {
        super.zzcb();
        this.zzvy.zzh(this.zzvw.zzacw);
        if (this.zzyv != null) {
            this.zzyv.zzx(false);
        }
        this.zzby();
    }
    
    @Override
    public final void zzcc() {
        this.recordImpression();
        super.zzcc();
        if (this.zzvw.zzacw != null && this.zzvw.zzacw.zzbyo != null) {
            final zzasc zzuf = this.zzvw.zzacw.zzbyo.zzuf();
            if (zzuf != null) {
                zzuf.zzuz();
            }
        }
        if (zzbv.zzfh().zzv(this.zzvw.zzrt) && this.zzvw.zzacw != null && this.zzvw.zzacw.zzbyo != null) {
            zzbv.zzfh().zzd(this.zzvw.zzacw.zzbyo.getContext(), this.zzyw);
        }
        if (this.zzyv != null) {
            this.zzyv.zzx(true);
        }
        if (this.zzwb != null && this.zzvw.zzacw != null && this.zzvw.zzacw.zzbyo != null) {
            this.zzvw.zzacw.zzbyo.zza("onSdkImpression", new HashMap<String, Object>());
        }
    }
    
    @Override
    public final void zzcz() {
        final com.google.android.gms.ads.internal.overlay.zzd zzub = this.zzvw.zzacw.zzbyo.zzub();
        if (zzub != null) {
            zzub.close();
        }
    }
    
    @Override
    public final void zzd(final boolean zzze) {
        this.zzvw.zzze = zzze;
    }
    
    protected final boolean zzdi() {
        if (!(this.zzvw.zzrt instanceof Activity)) {
            return false;
        }
        final Window window = ((Activity)this.zzvw.zzrt).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        final Rect rect = new Rect();
        final Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, (Point)null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        return rect.bottom != 0 && rect2.bottom != 0 && rect.top == rect2.top;
    }
    
    public final void zzdj() {
        zzbv.zzfe().zzb(this.zzyr);
        if (this.zzvw.zzfo()) {
            this.zzvw.zzfm();
            this.zzvw.zzacw = null;
            this.zzvw.zzze = false;
            this.zzyq = false;
        }
    }
    
    @Override
    public final void zzdk() {
        if (this.zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq)) {
            this.zzyy.zzov();
            this.zzbt();
            return;
        }
        if (this.zzvw.zzacw != null && this.zzvw.zzacw.zzcog != null) {
            zzbv.zzek();
            zzakk.zza(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.zzcog);
        }
        this.zzbt();
    }
    
    @Override
    public final void zzdl() {
        if (this.zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.zzceq)) {
            this.zzyy.zzow();
        }
        this.zzbu();
    }
}
