// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.common.util.VisibleForTesting;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzarl;
import com.google.android.gms.internal.ads.zzasc;
import com.google.android.gms.internal.ads.zzasf;
import android.text.TextUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzasg;
import com.google.android.gms.internal.ads.zzaor;
import com.google.android.gms.internal.ads.zzhu;
import java.util.List;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.os.RemoteException;
import java.util.Map;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzft;
import com.google.android.gms.internal.ads.zzaix;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzakb;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzajh;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzjn;
import android.content.Context;
import java.lang.ref.WeakReference;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zzadh
@ParametersAreNonnullByDefault
public final class zzy extends zzi implements ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    private boolean zzvm;
    private boolean zzxf;
    private WeakReference<Object> zzxg;
    
    public zzy(final Context context, final zzjn zzjn, final String s, final zzxn zzxn, final zzang zzang, final zzw zzw) {
        super(context, zzjn, s, zzxn, zzang, zzw);
        this.zzxg = new WeakReference<Object>(null);
    }
    
    private final void zzc(final zzaqw zzaqw) {
        if (this.zzcp()) {
            final WebView webView = zzaqw.getWebView();
            if (webView != null) {
                final View view = zzaqw.getView();
                if (view != null && zzbv.zzfa().zzi(this.zzvw.zzrt)) {
                    this.zzwb = zzbv.zzfa().zza(new StringBuilder(23).append(this.zzvw.zzacr.zzcve).append(".").append(this.zzvw.zzacr.zzcvf).toString(), webView, "", "javascript", this.zzbz());
                    if (this.zzwb != null) {
                        zzbv.zzfa().zza(this.zzwb, view);
                        zzbv.zzfa().zzm(this.zzwb);
                        this.zzxf = true;
                    }
                }
            }
        }
    }
    
    private final boolean zzd(@Nullable final zzajh zzajh, final zzajh zzajh2) {
        while (true) {
            Label_0271: {
                if (!zzajh2.zzceq) {
                    break Label_0271;
                }
                final View zze = zzas.zze(zzajh2);
                if (zze == null) {
                    zzakb.zzdk("Could not get mediation view");
                    return false;
                }
                final View nextView = this.zzvw.zzacs.getNextView();
                if (nextView != null) {
                    if (nextView instanceof zzaqw) {
                        ((zzaqw)nextView).destroy();
                    }
                    this.zzvw.zzacs.removeView(nextView);
                }
                if (zzas.zzf(zzajh2)) {
                    break Label_0178;
                }
                while (true) {
                    while (true) {
                        View nextView2 = null;
                        Label_0361: {
                            try {
                                if (zzbv.zzfh().zzt(this.zzvw.zzrt)) {
                                    new zzfp(this.zzvw.zzrt, zze).zza(new zzaix(this.zzvw.zzrt, this.zzvw.zzacp));
                                }
                                if (zzajh2.zzcof != null) {
                                    this.zzvw.zzacs.setMinimumWidth(zzajh2.zzcof.widthPixels);
                                    this.zzvw.zzacs.setMinimumHeight(zzajh2.zzcof.heightPixels);
                                }
                                this.zzg(zze);
                                if (this.zzvw.zzacs.getChildCount() > 1) {
                                    this.zzvw.zzacs.showNext();
                                }
                                if (zzajh != null) {
                                    nextView2 = this.zzvw.zzacs.getNextView();
                                    if (!(nextView2 instanceof zzaqw)) {
                                        break Label_0361;
                                    }
                                    ((zzaqw)nextView2).destroy();
                                    this.zzvw.zzfn();
                                }
                                this.zzvw.zzacs.setVisibility(0);
                                return true;
                            }
                            catch (Exception ex) {
                                zzbv.zzeo().zza(ex, "BannerAdManager.swapViews");
                                zzakb.zzc("Could not add mediation view to view hierarchy.", (Throwable)ex);
                                return false;
                            }
                            break;
                        }
                        if (nextView2 != null) {
                            this.zzvw.zzacs.removeView(nextView2);
                            continue;
                        }
                        continue;
                    }
                }
            }
            if (zzajh2.zzcof != null && zzajh2.zzbyo != null) {
                zzajh2.zzbyo.zza(zzasi.zzb(zzajh2.zzcof));
                this.zzvw.zzacs.removeAllViews();
                this.zzvw.zzacs.setMinimumWidth(zzajh2.zzcof.widthPixels);
                this.zzvw.zzacs.setMinimumHeight(zzajh2.zzcof.heightPixels);
                this.zzg(zzajh2.zzbyo.getView());
                continue;
            }
            continue;
        }
    }
    
    @Nullable
    public final zzlo getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        if (this.zzvw.zzacw != null && this.zzvw.zzacw.zzbyo != null) {
            return (zzlo)this.zzvw.zzacw.zzbyo.zztm();
        }
        return null;
    }
    
    public final void onGlobalLayout() {
        this.zzd(this.zzvw.zzacw);
    }
    
    public final void onScrollChanged() {
        this.zzd(this.zzvw.zzacw);
    }
    
    public final void setManualImpressionsEnabled(final boolean zzvm) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = zzvm;
    }
    
    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }
    
    @Override
    protected final zzaqw zza(final zzaji zzaji, @Nullable final zzx zzx, @Nullable final zzait zzait) throws zzarg {
        if (this.zzvw.zzacv.zzard == null && this.zzvw.zzacv.zzarf) {
            final zzbw zzvw = this.zzvw;
            zzjn zzacv;
            if (zzaji.zzcos.zzarf) {
                zzacv = this.zzvw.zzacv;
            }
            else {
                final String zzcet = zzaji.zzcos.zzcet;
                AdSize zzhy;
                if (zzcet != null) {
                    final String[] split = zzcet.split("[xX]");
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    zzhy = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                }
                else {
                    zzhy = this.zzvw.zzacv.zzhy();
                }
                zzacv = new zzjn(this.zzvw.zzrt, zzhy);
            }
            zzvw.zzacv = zzacv;
        }
        return super.zza(zzaji, zzx, zzait);
    }
    
    protected final void zza(@Nullable final zzajh zzajh, final boolean b) {
        zzyc zzmp = null;
        if (this.zzcp()) {
            zzaqw zzbyo;
            if (zzajh != null) {
                zzbyo = zzajh.zzbyo;
            }
            else {
                zzbyo = null;
            }
            if (zzbyo != null) {
                if (!this.zzxf) {
                    this.zzc(zzbyo);
                }
                if (this.zzwb != null) {
                    zzbyo.zza("onSdkImpression", (Map<String, ?>)new ArrayMap());
                }
            }
        }
        super.zza(zzajh, b);
        if (zzas.zzf(zzajh)) {
            final zzac zzac = new zzac(this);
            if (zzajh != null && zzas.zzf(zzajh)) {
                final zzaqw zzbyo2 = zzajh.zzbyo;
                View view;
                if (zzbyo2 != null) {
                    view = zzbyo2.getView();
                }
                else {
                    view = null;
                }
                if (view == null) {
                    zzakb.zzdk("AdWebView is null");
                }
                else {
                    List<String> zzbsi;
                    while (true) {
                        while (true) {
                            try {
                                if (zzajh.zzbtw != null) {
                                    zzbsi = zzajh.zzbtw.zzbsi;
                                    if (zzbsi == null || zzbsi.isEmpty()) {
                                        zzakb.zzdk("No template ids present in mediation response");
                                        return;
                                    }
                                    break;
                                }
                            }
                            catch (RemoteException ex) {
                                zzakb.zzc("Error occurred while recording impression and registering for clicks", (Throwable)ex);
                                return;
                            }
                            zzbsi = null;
                            continue;
                        }
                    }
                    zzxz zzmo;
                    if (zzajh.zzbtx != null) {
                        zzmo = zzajh.zzbtx.zzmo();
                    }
                    else {
                        zzmo = null;
                    }
                    if (zzajh.zzbtx != null) {
                        zzmp = zzajh.zzbtx.zzmp();
                    }
                    if (zzbsi.contains("2") && zzmo != null) {
                        zzmo.zzk(ObjectWrapper.wrap((Object)view));
                        if (!zzmo.getOverrideImpressionRecording()) {
                            zzmo.recordImpression();
                        }
                        zzbyo2.zza("/nativeExpressViewClicked", zzas.zza(zzmo, null, zzac));
                        return;
                    }
                    if (zzbsi.contains("1") && zzmp != null) {
                        zzmp.zzk(ObjectWrapper.wrap((Object)view));
                        if (!zzmp.getOverrideImpressionRecording()) {
                            zzmp.recordImpression();
                        }
                        zzbyo2.zza("/nativeExpressViewClicked", zzas.zza(null, zzmp, zzac));
                        return;
                    }
                    zzakb.zzdk("No matching template id and mapper");
                }
            }
        }
    }
    
    public final boolean zza(@Nullable final zzajh zzajh, final zzajh zzajh2) {
        if (!super.zza(zzajh, zzajh2)) {
            return false;
        }
        if (this.zzvw.zzfo() && !this.zzd(zzajh, zzajh2)) {
            if (zzajh2.zzcoq != null) {
                zzajh2.zzcoq.zza(zzhu.zza.zzb.zzakl);
            }
            this.zzi(0);
            return false;
        }
        this.zzb(zzajh2, false);
        if (zzajh2.zzcfh) {
            this.zzd(zzajh2);
            zzbv.zzfg();
            zzaor.zza((View)this.zzvw.zzacs, (ViewTreeObserver$OnGlobalLayoutListener)this);
            zzbv.zzfg();
            zzaor.zza((View)this.zzvw.zzacs, (ViewTreeObserver$OnScrollChangedListener)this);
            if (!zzajh2.zzcoc) {
                final zzab zzab = new zzab(this);
                zzasc zzuf;
                if (zzajh2.zzbyo != null) {
                    zzuf = zzajh2.zzbyo.zzuf();
                }
                else {
                    zzuf = null;
                }
                if (zzuf != null) {
                    zzuf.zza(new zzz(zzajh2, zzab));
                }
            }
        }
        else if (!this.zzvw.zzfp() || (boolean)zzkb.zzik().zzd(zznk.zzbbo)) {
            this.zza(zzajh2, false);
        }
        if (zzajh2.zzbyo != null) {
            final zzarl zztm = zzajh2.zzbyo.zztm();
            final zzasc zzuf2 = zzajh2.zzbyo.zzuf();
            if (zzuf2 != null) {
                zzuf2.zzuz();
            }
            if (this.zzvw.zzadk != null && zztm != null) {
                zztm.zzb(this.zzvw.zzadk);
            }
        }
        if (PlatformVersion.isAtLeastIceCreamSandwich()) {
            while (true) {
                Label_0503: {
                    View view;
                    if (this.zzvw.zzfo()) {
                        if (zzajh2.zzbyo == null) {
                            break Label_0503;
                        }
                        if (zzajh2.zzcob != null) {
                            this.zzvy.zza(this.zzvw.zzacv, zzajh2);
                        }
                        view = zzajh2.zzbyo.getView();
                        final zzfp zzfp = new zzfp(this.zzvw.zzrt, view);
                        if (zzbv.zzfh().zzt(this.zzvw.zzrt) && zza.zza(zzajh2.zzccv) && !TextUtils.isEmpty((CharSequence)this.zzvw.zzacp)) {
                            zzfp.zza(new zzaix(this.zzvw.zzrt, this.zzvw.zzacp));
                        }
                        if (zzajh2.zzfz()) {
                            zzfp.zza(zzajh2.zzbyo);
                        }
                        else {
                            zzajh2.zzbyo.zzuf().zza(new zzaa(zzfp, zzajh2));
                        }
                    }
                    else {
                        if (this.zzvw.zzadu == null || zzajh2.zzcob == null) {
                            break Label_0503;
                        }
                        this.zzvy.zza(this.zzvw.zzacv, zzajh2, this.zzvw.zzadu);
                        view = this.zzvw.zzadu;
                    }
                    if (!zzajh2.zzceq) {
                        this.zzvw.zzj(view);
                        return true;
                    }
                    return true;
                }
                View view = null;
                continue;
            }
        }
        return true;
    }
    
    public final boolean zzb(zzjj zzjj) {
        if (zzjj.zzaqb != this.zzvm) {
            zzjj = new zzjj(zzjj.versionCode, zzjj.zzapw, zzjj.extras, zzjj.zzapx, zzjj.zzapy, zzjj.zzapz, zzjj.zzaqa, zzjj.zzaqb || this.zzvm, zzjj.zzaqc, zzjj.zzaqd, zzjj.zzaqe, zzjj.zzaqf, zzjj.zzaqg, zzjj.zzaqh, zzjj.zzaqi, zzjj.zzaqj, zzjj.zzaqk, zzjj.zzaql);
        }
        return super.zzb(zzjj);
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
        if (!this.zzxf && zzbyo != null) {
            this.zzc(zzbyo);
        }
        super.zzbq();
    }
    
    protected final boolean zzca() {
        boolean b = true;
        zzbv.zzek();
        if (!zzakk.zzl(this.zzvw.zzrt, "android.permission.INTERNET")) {
            zzkb.zzif().zza((ViewGroup)this.zzvw.zzacs, this.zzvw.zzacv, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            b = false;
        }
        zzbv.zzek();
        if (!zzakk.zzaj(this.zzvw.zzrt)) {
            zzkb.zzif().zza((ViewGroup)this.zzvw.zzacs, this.zzvw.zzacv, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            b = false;
        }
        if (!b && this.zzvw.zzacs != null) {
            this.zzvw.zzacs.setVisibility(0);
        }
        return b;
    }
    
    public final void zzcz() {
        this.zzvv.zzdy();
    }
    
    @VisibleForTesting
    final void zzd(@Nullable final zzajh zzajh) {
        if (zzajh != null && !zzajh.zzcoc && this.zzvw.zzacs != null && zzbv.zzek().zza((View)this.zzvw.zzacs, this.zzvw.zzrt) && this.zzvw.zzacs.getGlobalVisibleRect(new Rect(), (Point)null)) {
            if (zzajh != null && zzajh.zzbyo != null && zzajh.zzbyo.zzuf() != null) {
                zzajh.zzbyo.zzuf().zza((zzasg)null);
            }
            this.zza(zzajh, false);
            zzajh.zzcoc = true;
        }
    }
}
