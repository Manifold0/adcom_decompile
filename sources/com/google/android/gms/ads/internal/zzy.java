package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaix;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzasi;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzy extends zzi implements OnGlobalLayoutListener, OnScrollChangedListener {
    private boolean zzvm;
    private boolean zzxf;
    private WeakReference<Object> zzxg = new WeakReference(null);

    public zzy(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, zzw zzw) {
        super(context, zzjn, str, zzxn, zzang, zzw);
    }

    private final void zzc(zzaqw zzaqw) {
        if (zzcp()) {
            WebView webView = zzaqw.getWebView();
            if (webView != null) {
                View view = zzaqw.getView();
                if (view != null && zzbv.zzfa().zzi(this.zzvw.zzrt)) {
                    int i = this.zzvw.zzacr.zzcve;
                    this.zzwb = zzbv.zzfa().zza(i + "." + this.zzvw.zzacr.zzcvf, webView, "", "javascript", zzbz());
                    if (this.zzwb != null) {
                        zzbv.zzfa().zza(this.zzwb, view);
                        zzbv.zzfa().zzm(this.zzwb);
                        this.zzxf = true;
                    }
                }
            }
        }
    }

    private final boolean zzd(@Nullable zzajh zzajh, zzajh zzajh2) {
        if (zzajh2.zzceq) {
            View zze = zzas.zze(zzajh2);
            if (zze == null) {
                zzakb.zzdk("Could not get mediation view");
                return false;
            }
            View nextView = this.zzvw.zzacs.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzaqw) {
                    ((zzaqw) nextView).destroy();
                }
                this.zzvw.zzacs.removeView(nextView);
            }
            if (!zzas.zzf(zzajh2)) {
                try {
                    if (zzbv.zzfh().zzt(this.zzvw.zzrt)) {
                        new zzfp(this.zzvw.zzrt, zze).zza(new zzaix(this.zzvw.zzrt, this.zzvw.zzacp));
                    }
                    if (zzajh2.zzcof != null) {
                        this.zzvw.zzacs.setMinimumWidth(zzajh2.zzcof.widthPixels);
                        this.zzvw.zzacs.setMinimumHeight(zzajh2.zzcof.heightPixels);
                    }
                    zzg(zze);
                } catch (Throwable e) {
                    zzbv.zzeo().zza(e, "BannerAdManager.swapViews");
                    zzakb.zzc("Could not add mediation view to view hierarchy.", e);
                    return false;
                }
            }
        } else if (!(zzajh2.zzcof == null || zzajh2.zzbyo == null)) {
            zzajh2.zzbyo.zza(zzasi.zzb(zzajh2.zzcof));
            this.zzvw.zzacs.removeAllViews();
            this.zzvw.zzacs.setMinimumWidth(zzajh2.zzcof.widthPixels);
            this.zzvw.zzacs.setMinimumHeight(zzajh2.zzcof.heightPixels);
            zzg(zzajh2.zzbyo.getView());
        }
        if (this.zzvw.zzacs.getChildCount() > 1) {
            this.zzvw.zzacs.showNext();
        }
        if (zzajh != null) {
            View nextView2 = this.zzvw.zzacs.getNextView();
            if (nextView2 instanceof zzaqw) {
                ((zzaqw) nextView2).destroy();
            } else if (nextView2 != null) {
                this.zzvw.zzacs.removeView(nextView2);
            }
            this.zzvw.zzfn();
        }
        this.zzvw.zzacs.setVisibility(0);
        return true;
    }

    @Nullable
    public final zzlo getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        return (this.zzvw.zzacw == null || this.zzvw.zzacw.zzbyo == null) ? null : this.zzvw.zzacw.zzbyo.zztm();
    }

    public final void onGlobalLayout() {
        zzd(this.zzvw.zzacw);
    }

    public final void onScrollChanged() {
        zzd(this.zzvw.zzacw);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    protected final zzaqw zza(zzaji zzaji, @Nullable zzx zzx, @Nullable zzait zzait) throws zzarg {
        if (this.zzvw.zzacv.zzard == null && this.zzvw.zzacv.zzarf) {
            zzjn zzjn;
            zzbw zzbw = this.zzvw;
            if (zzaji.zzcos.zzarf) {
                zzjn = this.zzvw.zzacv;
            } else {
                AdSize adSize;
                String str = zzaji.zzcos.zzcet;
                if (str != null) {
                    String[] split = str.split("[xX]");
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    adSize = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } else {
                    adSize = this.zzvw.zzacv.zzhy();
                }
                zzjn = new zzjn(this.zzvw.zzrt, adSize);
            }
            zzbw.zzacv = zzjn;
        }
        return super.zza(zzaji, zzx, zzait);
    }

    protected final void zza(@Nullable zzajh zzajh, boolean z) {
        zzyc zzyc = null;
        if (zzcp()) {
            zzaqw zzaqw = zzajh != null ? zzajh.zzbyo : null;
            if (zzaqw != null) {
                if (!this.zzxf) {
                    zzc(zzaqw);
                }
                if (this.zzwb != null) {
                    zzaqw.zza("onSdkImpression", new ArrayMap());
                }
            }
        }
        super.zza(zzajh, z);
        if (zzas.zzf(zzajh)) {
            zzac zzac = new zzac(this);
            if (zzajh != null && zzas.zzf(zzajh)) {
                zzaqw zzaqw2 = zzajh.zzbyo;
                Object view = zzaqw2 != null ? zzaqw2.getView() : null;
                if (view == null) {
                    zzakb.zzdk("AdWebView is null");
                    return;
                }
                try {
                    List list = zzajh.zzbtw != null ? zzajh.zzbtw.zzbsi : null;
                    if (list == null || list.isEmpty()) {
                        zzakb.zzdk("No template ids present in mediation response");
                        return;
                    }
                    zzxz zzmo = zzajh.zzbtx != null ? zzajh.zzbtx.zzmo() : null;
                    if (zzajh.zzbtx != null) {
                        zzyc = zzajh.zzbtx.zzmp();
                    }
                    if (list.contains("2") && zzmo != null) {
                        zzmo.zzk(ObjectWrapper.wrap(view));
                        if (!zzmo.getOverrideImpressionRecording()) {
                            zzmo.recordImpression();
                        }
                        zzaqw2.zza("/nativeExpressViewClicked", zzas.zza(zzmo, null, zzac));
                    } else if (!list.contains("1") || zzyc == null) {
                        zzakb.zzdk("No matching template id and mapper");
                    } else {
                        zzyc.zzk(ObjectWrapper.wrap(view));
                        if (!zzyc.getOverrideImpressionRecording()) {
                            zzyc.recordImpression();
                        }
                        zzaqw2.zza("/nativeExpressViewClicked", zzas.zza(null, zzyc, zzac));
                    }
                } catch (Throwable e) {
                    zzakb.zzc("Error occurred while recording impression and registering for clicks", e);
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(@android.support.annotation.Nullable com.google.android.gms.internal.ads.zzajh r6, com.google.android.gms.internal.ads.zzajh r7) {
        /*
        r5 = this;
        r1 = 0;
        r2 = 0;
        r0 = super.zza(r6, r7);
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        r0 = r2;
    L_0x0009:
        return r0;
    L_0x000a:
        r0 = r5.zzvw;
        r0 = r0.zzfo();
        if (r0 == 0) goto L_0x0028;
    L_0x0012:
        r0 = r5.zzd(r6, r7);
        if (r0 != 0) goto L_0x0028;
    L_0x0018:
        r0 = r7.zzcoq;
        if (r0 == 0) goto L_0x0023;
    L_0x001c:
        r0 = r7.zzcoq;
        r1 = com.google.android.gms.internal.ads.zzhu.zza.zzb.AD_FAILED_TO_LOAD;
        r0.zza(r1);
    L_0x0023:
        r5.zzi(r2);
        r0 = r2;
        goto L_0x0009;
    L_0x0028:
        r5.zzb(r7, r2);
        r0 = r7.zzcfh;
        if (r0 == 0) goto L_0x00ff;
    L_0x002f:
        r5.zzd(r7);
        com.google.android.gms.ads.internal.zzbv.zzfg();
        r0 = r5.zzvw;
        r0 = r0.zzacs;
        com.google.android.gms.internal.ads.zzaor.zza(r0, r5);
        com.google.android.gms.ads.internal.zzbv.zzfg();
        r0 = r5.zzvw;
        r0 = r0.zzacs;
        com.google.android.gms.internal.ads.zzaor.zza(r0, r5);
        r0 = r7.zzcoc;
        if (r0 != 0) goto L_0x0063;
    L_0x004a:
        r2 = new com.google.android.gms.ads.internal.zzab;
        r2.<init>(r5);
        r0 = r7.zzbyo;
        if (r0 == 0) goto L_0x00fc;
    L_0x0053:
        r0 = r7.zzbyo;
        r0 = r0.zzuf();
    L_0x0059:
        if (r0 == 0) goto L_0x0063;
    L_0x005b:
        r3 = new com.google.android.gms.ads.internal.zzz;
        r3.<init>(r7, r2);
        r0.zza(r3);
    L_0x0063:
        r0 = r7.zzbyo;
        if (r0 == 0) goto L_0x0087;
    L_0x0067:
        r0 = r7.zzbyo;
        r0 = r0.zztm();
        r2 = r7.zzbyo;
        r2 = r2.zzuf();
        if (r2 == 0) goto L_0x0078;
    L_0x0075:
        r2.zzuz();
    L_0x0078:
        r2 = r5.zzvw;
        r2 = r2.zzadk;
        if (r2 == 0) goto L_0x0087;
    L_0x007e:
        if (r0 == 0) goto L_0x0087;
    L_0x0080:
        r2 = r5.zzvw;
        r2 = r2.zzadk;
        r0.zzb(r2);
    L_0x0087:
        r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich();
        if (r0 == 0) goto L_0x00f9;
    L_0x008d:
        r0 = r5.zzvw;
        r0 = r0.zzfo();
        if (r0 == 0) goto L_0x012d;
    L_0x0095:
        r0 = r7.zzbyo;
        if (r0 == 0) goto L_0x0149;
    L_0x0099:
        r0 = r7.zzcob;
        if (r0 == 0) goto L_0x00a6;
    L_0x009d:
        r0 = r5.zzvy;
        r1 = r5.zzvw;
        r1 = r1.zzacv;
        r0.zza(r1, r7);
    L_0x00a6:
        r0 = r7.zzbyo;
        r0 = r0.getView();
        r1 = new com.google.android.gms.internal.ads.zzfp;
        r2 = r5.zzvw;
        r2 = r2.zzrt;
        r1.<init>(r2, r0);
        r2 = com.google.android.gms.ads.internal.zzbv.zzfh();
        r3 = r5.zzvw;
        r3 = r3.zzrt;
        r2 = r2.zzt(r3);
        if (r2 == 0) goto L_0x00e5;
    L_0x00c3:
        r2 = r7.zzccv;
        r2 = com.google.android.gms.ads.internal.zza.zza(r2);
        if (r2 == 0) goto L_0x00e5;
    L_0x00cb:
        r2 = r5.zzvw;
        r2 = r2.zzacp;
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x00e5;
    L_0x00d5:
        r2 = new com.google.android.gms.internal.ads.zzaix;
        r3 = r5.zzvw;
        r3 = r3.zzrt;
        r4 = r5.zzvw;
        r4 = r4.zzacp;
        r2.<init>(r3, r4);
        r1.zza(r2);
    L_0x00e5:
        r2 = r7.zzfz();
        if (r2 == 0) goto L_0x011e;
    L_0x00eb:
        r2 = r7.zzbyo;
        r1.zza(r2);
    L_0x00f0:
        r1 = r7.zzceq;
        if (r1 != 0) goto L_0x00f9;
    L_0x00f4:
        r1 = r5.zzvw;
        r1.zzj(r0);
    L_0x00f9:
        r0 = 1;
        goto L_0x0009;
    L_0x00fc:
        r0 = r1;
        goto L_0x0059;
    L_0x00ff:
        r0 = r5.zzvw;
        r0 = r0.zzfp();
        if (r0 == 0) goto L_0x0119;
    L_0x0107:
        r0 = com.google.android.gms.internal.ads.zznk.zzbbo;
        r3 = com.google.android.gms.internal.ads.zzkb.zzik();
        r0 = r3.zzd(r0);
        r0 = (java.lang.Boolean) r0;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x0063;
    L_0x0119:
        r5.zza(r7, r2);
        goto L_0x0063;
    L_0x011e:
        r2 = r7.zzbyo;
        r2 = r2.zzuf();
        r3 = new com.google.android.gms.ads.internal.zzaa;
        r3.<init>(r1, r7);
        r2.zza(r3);
        goto L_0x00f0;
    L_0x012d:
        r0 = r5.zzvw;
        r0 = r0.zzadu;
        if (r0 == 0) goto L_0x0149;
    L_0x0133:
        r0 = r7.zzcob;
        if (r0 == 0) goto L_0x0149;
    L_0x0137:
        r0 = r5.zzvy;
        r1 = r5.zzvw;
        r1 = r1.zzacv;
        r2 = r5.zzvw;
        r2 = r2.zzadu;
        r0.zza(r1, r7, r2);
        r0 = r5.zzvw;
        r0 = r0.zzadu;
        goto L_0x00f0;
    L_0x0149:
        r0 = r1;
        goto L_0x00f0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzy.zza(com.google.android.gms.internal.ads.zzajh, com.google.android.gms.internal.ads.zzajh):boolean");
    }

    public final boolean zzb(zzjj zzjj) {
        if (zzjj.zzaqb != this.zzvm) {
            int i = zzjj.versionCode;
            long j = zzjj.zzapw;
            Bundle bundle = zzjj.extras;
            int i2 = zzjj.zzapx;
            List list = zzjj.zzapy;
            boolean z = zzjj.zzapz;
            int i3 = zzjj.zzaqa;
            boolean z2 = zzjj.zzaqb || this.zzvm;
            zzjj = new zzjj(i, j, bundle, i2, list, z, i3, z2, zzjj.zzaqc, zzjj.zzaqd, zzjj.zzaqe, zzjj.zzaqf, zzjj.zzaqg, zzjj.zzaqh, zzjj.zzaqi, zzjj.zzaqj, zzjj.zzaqk, zzjj.zzaql);
        }
        return super.zzb(zzjj);
    }

    protected final void zzbq() {
        zzaqw zzaqw = this.zzvw.zzacw != null ? this.zzvw.zzacw.zzbyo : null;
        if (!(this.zzxf || zzaqw == null)) {
            zzc(zzaqw);
        }
        super.zzbq();
    }

    protected final boolean zzca() {
        boolean z = true;
        zzbv.zzek();
        if (!zzakk.zzl(this.zzvw.zzrt, "android.permission.INTERNET")) {
            zzkb.zzif().zza(this.zzvw.zzacs, this.zzvw.zzacv, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        zzbv.zzek();
        if (!zzakk.zzaj(this.zzvw.zzrt)) {
            zzkb.zzif().zza(this.zzvw.zzacs, this.zzvw.zzacv, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!(z || this.zzvw.zzacs == null)) {
            this.zzvw.zzacs.setVisibility(0);
        }
        return z;
    }

    public final void zzcz() {
        this.zzvv.zzdy();
    }

    @VisibleForTesting
    final void zzd(@Nullable zzajh zzajh) {
        if (zzajh != null && !zzajh.zzcoc && this.zzvw.zzacs != null && zzbv.zzek().zza(this.zzvw.zzacs, this.zzvw.zzrt) && this.zzvw.zzacs.getGlobalVisibleRect(new Rect(), null)) {
            if (!(zzajh == null || zzajh.zzbyo == null || zzajh.zzbyo.zzuf() == null)) {
                zzajh.zzbyo.zzuf().zza(null);
            }
            zza(zzajh, false);
            zzajh.zzcoc = true;
        }
    }
}
