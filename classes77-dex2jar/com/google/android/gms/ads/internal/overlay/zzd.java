// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.dynamic.ObjectWrapper;
import android.content.res.Configuration;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Collections;
import com.google.android.gms.internal.ads.zzaal;
import android.os.Build$VERSION;
import com.google.android.gms.internal.ads.zzakq;
import android.os.Bundle;
import android.content.Intent;
import android.view.ViewParent;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.ads.internal.gmsg.zzb;
import com.google.android.gms.internal.ads.zzasc;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzasi;
import android.view.Window;
import com.google.android.gms.internal.ads.zzakb;
import android.view.ViewGroup;
import com.google.android.gms.internal.ads.zzasd;
import com.google.android.gms.internal.ads.zzait;
import com.google.android.gms.internal.ads.zzaam;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzarc;
import com.google.android.gms.internal.ads.zzhs;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zzakk;
import android.graphics.Color;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import com.google.android.gms.internal.ads.zzaqw;
import android.app.Activity;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaaq;

@zzadh
public class zzd extends zzaaq implements zzw
{
    @VisibleForTesting
    private static final int zzbxm;
    protected final Activity mActivity;
    @VisibleForTesting
    zzaqw zzbnd;
    @VisibleForTesting
    AdOverlayInfoParcel zzbxn;
    @VisibleForTesting
    private zzi zzbxo;
    @VisibleForTesting
    private zzo zzbxp;
    @VisibleForTesting
    private boolean zzbxq;
    @VisibleForTesting
    private FrameLayout zzbxr;
    @VisibleForTesting
    private WebChromeClient$CustomViewCallback zzbxs;
    @VisibleForTesting
    private boolean zzbxt;
    @VisibleForTesting
    private boolean zzbxu;
    @VisibleForTesting
    private zzh zzbxv;
    @VisibleForTesting
    private boolean zzbxw;
    @VisibleForTesting
    int zzbxx;
    private final Object zzbxy;
    private Runnable zzbxz;
    private boolean zzbya;
    private boolean zzbyb;
    private boolean zzbyc;
    private boolean zzbyd;
    private boolean zzbye;
    
    static {
        zzbxm = Color.argb(0, 0, 0, 0);
    }
    
    public zzd(final Activity mActivity) {
        this.zzbxq = false;
        this.zzbxt = false;
        this.zzbxu = false;
        this.zzbxw = false;
        this.zzbxx = 0;
        this.zzbxy = new Object();
        this.zzbyc = false;
        this.zzbyd = false;
        this.zzbye = true;
        this.mActivity = mActivity;
    }
    
    private final void zznl() {
        if (!this.mActivity.isFinishing() || this.zzbyc) {
            return;
        }
        this.zzbyc = true;
        if (this.zzbnd != null) {
            this.zzbnd.zzai(this.zzbxx);
            synchronized (this.zzbxy) {
                if (!this.zzbya && this.zzbnd.zzun()) {
                    this.zzbxz = new zzf(this);
                    zzakk.zzcrm.postDelayed(this.zzbxz, (long)zzkb.zzik().zzd(zznk.zzayq));
                    return;
                }
            }
        }
        // monitorexit(o)
        this.zznm();
    }
    
    private final void zzno() {
        this.zzbnd.zzno();
    }
    
    private final void zzs(final boolean b) {
        final int intValue = (int)zzkb.zzik().zzd(zznk.zzben);
        final zzp zzp = new zzp();
        zzp.size = 50;
        int paddingLeft;
        if (b) {
            paddingLeft = intValue;
        }
        else {
            paddingLeft = 0;
        }
        zzp.paddingLeft = paddingLeft;
        int paddingRight;
        if (b) {
            paddingRight = 0;
        }
        else {
            paddingRight = intValue;
        }
        zzp.paddingRight = paddingRight;
        zzp.paddingTop = 0;
        zzp.paddingBottom = intValue;
        this.zzbxp = new zzo((Context)this.mActivity, zzp, this);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        relativeLayout$LayoutParams.addRule(10);
        int n;
        if (b) {
            n = 11;
        }
        else {
            n = 9;
        }
        relativeLayout$LayoutParams.addRule(n);
        this.zza(b, this.zzbxn.zzbyr);
        this.zzbxv.addView((View)this.zzbxp, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    private final void zzt(final boolean b) throws zzg {
        if (!this.zzbyb) {
            this.mActivity.requestWindowFeature(1);
        }
        final Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zzg("Invalid activity, no window available.");
        }
        boolean zza;
        if (PlatformVersion.isAtLeastN() && (boolean)zzkb.zzik().zzd(zznk.zzbel)) {
            zzbv.zzek();
            zza = zzakk.zza(this.mActivity, this.mActivity.getResources().getConfiguration());
        }
        else {
            zza = true;
        }
        while (true) {
            boolean b2 = false;
            Activity mActivity;
            zzasi zzud;
            String zzue;
            zzang zzacr;
            com.google.android.gms.ads.internal.zzw zzbi;
            boolean zzfz = false;
            zzasc zzuf;
            zzb zzbyx;
            com.google.android.gms.ads.internal.gmsg.zzd zzbyp;
            zzt zzbyt;
            zzx zzut;
            ViewParent parent;
            zzasc zzuf2 = null;
            boolean zzbxw = false;
            Label_0792_Outer:Label_0798_Outer:Label_0860_Outer:
            while (true) {
                Label_0368: {
                    while (true) {
                        Label_0291: {
                            Label_0285: {
                            Label_0809_Outer:
                                while (true) {
                                    while (true) {
                                        Label_0240: {
                                            while (true) {
                                                Label_0227: {
                                                    while (true) {
                                                        Label_0115: {
                                                            if (this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzf) {
                                                                b2 = true;
                                                                break Label_0115;
                                                            }
                                                            Label_0787: {
                                                                break Label_0787;
                                                            Label_0640_Outer:
                                                                while (true) {
                                                                    while (true) {
                                                                    Label_0918:
                                                                        while (true) {
                                                                            Label_0912: {
                                                                                try {
                                                                                    zzbv.zzel();
                                                                                    mActivity = this.mActivity;
                                                                                    if (this.zzbxn.zzbyo != null) {
                                                                                        zzud = this.zzbxn.zzbyo.zzud();
                                                                                    }
                                                                                    else {
                                                                                        zzud = null;
                                                                                    }
                                                                                    if (this.zzbxn.zzbyo != null) {
                                                                                        zzue = this.zzbxn.zzbyo.zzue();
                                                                                    }
                                                                                    else {
                                                                                        zzue = null;
                                                                                    }
                                                                                    zzacr = this.zzbxn.zzacr;
                                                                                    if (this.zzbxn.zzbyo != null) {
                                                                                        zzbi = this.zzbxn.zzbyo.zzbi();
                                                                                    }
                                                                                    else {
                                                                                        zzbi = null;
                                                                                    }
                                                                                    this.zzbnd = zzarc.zza((Context)mActivity, zzud, zzue, true, zzfz, null, zzacr, null, null, zzbi, zzhs.zzhm());
                                                                                    zzuf = this.zzbnd.zzuf();
                                                                                    zzbyx = this.zzbxn.zzbyx;
                                                                                    zzbyp = this.zzbxn.zzbyp;
                                                                                    zzbyt = this.zzbxn.zzbyt;
                                                                                    if (this.zzbxn.zzbyo == null) {
                                                                                        break Label_0912;
                                                                                    }
                                                                                    zzut = this.zzbxn.zzbyo.zzuf().zzut();
                                                                                    zzuf.zza(null, zzbyx, null, zzbyp, zzbyt, true, null, zzut, null, null);
                                                                                    this.zzbnd.zzuf().zza(new zze(this));
                                                                                    if (this.zzbxn.url != null) {
                                                                                        this.zzbnd.loadUrl(this.zzbxn.url);
                                                                                        if (this.zzbxn.zzbyo != null) {
                                                                                            this.zzbxn.zzbyo.zzb(this);
                                                                                        }
                                                                                        this.zzbnd.zza(this);
                                                                                        parent = this.zzbnd.getParent();
                                                                                        if (parent != null && parent instanceof ViewGroup) {
                                                                                            ((ViewGroup)parent).removeView(this.zzbnd.getView());
                                                                                        }
                                                                                        if (this.zzbxu) {
                                                                                            this.zzbnd.zzur();
                                                                                        }
                                                                                        this.zzbxv.addView(this.zzbnd.getView(), -1, -1);
                                                                                        if (!b && !this.zzbxw) {
                                                                                            this.zzno();
                                                                                        }
                                                                                        this.zzs(zzfz);
                                                                                        if (this.zzbnd.zzuh()) {
                                                                                            this.zza(zzfz, true);
                                                                                        }
                                                                                        return;
                                                                                    }
                                                                                    break Label_0918;
                                                                                    while (true) {
                                                                                        this.zzbxw = (this.mActivity.getResources().getConfiguration().orientation == 2);
                                                                                        break Label_0291;
                                                                                        zzuf2 = null;
                                                                                        break Label_0227;
                                                                                        zzfz = false;
                                                                                        break Label_0240;
                                                                                        this.zzbxv.setBackgroundColor(zzd.zzbxm);
                                                                                        break Label_0368;
                                                                                        zzbxw = false;
                                                                                        break Label_0285;
                                                                                        b2 = false;
                                                                                        break Label_0115;
                                                                                        continue Label_0792_Outer;
                                                                                    }
                                                                                }
                                                                                // iftrue(Label_0291:, this.zzbxn.orientation != zzbv.zzem().zzrm())
                                                                                catch (Exception ex) {
                                                                                    zzakb.zzb("Error obtaining webview.", (Throwable)ex);
                                                                                    throw new zzg("Could not obtain webview for the overlay.");
                                                                                }
                                                                            }
                                                                            zzut = null;
                                                                            continue Label_0640_Outer;
                                                                        }
                                                                        if (this.zzbxn.zzbys != null) {
                                                                            this.zzbnd.loadDataWithBaseURL(this.zzbxn.zzbyq, this.zzbxn.zzbys, "text/html", "UTF-8", null);
                                                                            continue Label_0792_Outer;
                                                                        }
                                                                        break;
                                                                    }
                                                                    break;
                                                                }
                                                            }
                                                            throw new zzg("No URL or HTML to display in ad overlay.");
                                                        }
                                                        if ((!this.zzbxu || b2) && zza) {
                                                            window.setFlags(1024, 1024);
                                                            if ((boolean)zzkb.zzik().zzd(zznk.zzayr) && PlatformVersion.isAtLeastKitKat() && this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzk) {
                                                                window.getDecorView().setSystemUiVisibility(4098);
                                                            }
                                                        }
                                                        if (this.zzbxn.zzbyo == null) {
                                                            continue Label_0798_Outer;
                                                        }
                                                        break;
                                                    }
                                                    zzuf2 = this.zzbxn.zzbyo.zzuf();
                                                }
                                                if (zzuf2 == null) {
                                                    continue Label_0860_Outer;
                                                }
                                                break;
                                            }
                                            zzfz = zzuf2.zzfz();
                                        }
                                        this.zzbxw = false;
                                        if (!zzfz) {
                                            break Label_0291;
                                        }
                                        if (this.zzbxn.orientation != zzbv.zzem().zzrl()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (this.mActivity.getResources().getConfiguration().orientation != 1) {
                                        continue Label_0809_Outer;
                                    }
                                    break;
                                }
                                zzbxw = true;
                            }
                            this.zzbxw = zzbxw;
                        }
                        zzakb.zzck(new StringBuilder(46).append("Delay onShow to next orientation change: ").append(this.zzbxw).toString());
                        this.setRequestedOrientation(this.zzbxn.orientation);
                        if (zzbv.zzem().zza(window)) {
                            zzakb.zzck("Hardware acceleration on the AdActivity window enabled.");
                        }
                        if (this.zzbxu) {
                            continue;
                        }
                        break;
                    }
                    this.zzbxv.setBackgroundColor(-16777216);
                }
                this.mActivity.setContentView((View)this.zzbxv);
                this.zzbyb = true;
                if (b) {
                    continue Label_0860_Outer;
                }
                break;
            }
            (this.zzbnd = this.zzbxn.zzbyo).zzbm((Context)this.mActivity);
            continue;
        }
    }
    
    public final void close() {
        this.zzbxx = 2;
        this.mActivity.finish();
    }
    
    public final void onActivityResult(final int n, final int n2, final Intent intent) {
    }
    
    public final void onBackPressed() {
        this.zzbxx = 0;
    }
    
    public void onCreate(final Bundle bundle) {
        boolean boolean1 = false;
        this.mActivity.requestWindowFeature(1);
        if (bundle != null) {
            boolean1 = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzbxt = boolean1;
        try {
            this.zzbxn = AdOverlayInfoParcel.zzc(this.mActivity.getIntent());
            if (this.zzbxn == null) {
                throw new zzg("Could not get info for ad overlay.");
            }
        }
        catch (zzg zzg) {
            zzakb.zzdk(zzg.getMessage());
            this.zzbxx = 3;
            this.mActivity.finish();
            return;
        }
        if (this.zzbxn.zzacr.zzcvf > 7500000) {
            this.zzbxx = 3;
        }
        if (this.mActivity.getIntent() != null) {
            this.zzbye = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
        }
        if (this.zzbxn.zzbyw != null) {
            this.zzbxu = this.zzbxn.zzbyw.zzze;
        }
        else {
            this.zzbxu = false;
        }
        if ((boolean)zzkb.zzik().zzd(zznk.zzbbg) && this.zzbxu && this.zzbxn.zzbyw.zzzj != -1) {
            new zzj(this, null).zzqo();
        }
        if (bundle == null) {
            if (this.zzbxn.zzbyn != null && this.zzbye) {
                this.zzbxn.zzbyn.zzcc();
            }
            if (this.zzbxn.zzbyu != 1 && this.zzbxn.zzbym != null) {
                this.zzbxn.zzbym.onAdClicked();
            }
        }
        (this.zzbxv = new zzh((Context)this.mActivity, this.zzbxn.zzbyv, this.zzbxn.zzacr.zzcw)).setId(1000);
        switch (this.zzbxn.zzbyu) {
            case 1: {
                this.zzt(false);
            }
            case 2: {
                this.zzbxo = new zzi(this.zzbxn.zzbyo);
                this.zzt(false);
            }
            case 3: {
                this.zzt(true);
            }
            default: {
                throw new zzg("Could not determine ad overlay type.");
            }
        }
    }
    
    public final void onDestroy() {
        if (this.zzbnd != null) {
            this.zzbxv.removeView(this.zzbnd.getView());
        }
        this.zznl();
    }
    
    public final void onPause() {
        this.zznh();
        if (this.zzbxn.zzbyn != null) {
            this.zzbxn.zzbyn.onPause();
        }
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbem) && this.zzbnd != null && (!this.mActivity.isFinishing() || this.zzbxo == null)) {
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
        }
        this.zznl();
    }
    
    public final void onRestart() {
    }
    
    public final void onResume() {
        if (this.zzbxn.zzbyn != null) {
            this.zzbxn.zzbyn.onResume();
        }
        if (!(boolean)zzkb.zzik().zzd(zznk.zzbem)) {
            if (this.zzbnd == null || this.zzbnd.isDestroyed()) {
                zzakb.zzdk("The webview does not exist. Ignoring action.");
                return;
            }
            zzbv.zzem();
            zzakq.zzj(this.zzbnd);
        }
    }
    
    public final void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbxt);
    }
    
    public final void onStart() {
        if (zzkb.zzik().zzd(zznk.zzbem)) {
            if (this.zzbnd == null || this.zzbnd.isDestroyed()) {
                zzakb.zzdk("The webview does not exist. Ignoring action.");
                return;
            }
            zzbv.zzem();
            zzakq.zzj(this.zzbnd);
        }
    }
    
    public final void onStop() {
        if ((boolean)zzkb.zzik().zzd(zznk.zzbem) && this.zzbnd != null && (!this.mActivity.isFinishing() || this.zzbxo == null)) {
            zzbv.zzem();
            zzakq.zzi(this.zzbnd);
        }
        this.zznl();
    }
    
    public final void setRequestedOrientation(final int requestedOrientation) {
        if (this.mActivity.getApplicationInfo().targetSdkVersion >= (int)zzkb.zzik().zzd(zznk.zzbfs) && this.mActivity.getApplicationInfo().targetSdkVersion <= (int)zzkb.zzik().zzd(zznk.zzbft) && Build$VERSION.SDK_INT >= (int)zzkb.zzik().zzd(zznk.zzbfu) && Build$VERSION.SDK_INT <= (int)zzkb.zzik().zzd(zznk.zzbfv)) {
            return;
        }
        this.mActivity.setRequestedOrientation(requestedOrientation);
    }
    
    public final void zza(final View view, final WebChromeClient$CustomViewCallback zzbxs) {
        (this.zzbxr = new FrameLayout((Context)this.mActivity)).setBackgroundColor(-16777216);
        this.zzbxr.addView(view, -1, -1);
        this.mActivity.setContentView((View)this.zzbxr);
        this.zzbyb = true;
        this.zzbxs = zzbxs;
        this.zzbxq = true;
    }
    
    public final void zza(final boolean b, final boolean b2) {
        final boolean b3 = false;
        boolean b4;
        if ((boolean)zzkb.zzik().zzd(zznk.zzays) && this.zzbxn != null && this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzl) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        boolean b5;
        if ((boolean)zzkb.zzik().zzd(zznk.zzayt) && this.zzbxn != null && this.zzbxn.zzbyw != null && this.zzbxn.zzbyw.zzzm) {
            b5 = true;
        }
        else {
            b5 = false;
        }
        if (b && b2 && b4 && !b5) {
            new zzaal(this.zzbnd, "useCustomClose").zzbw("Custom close has been disabled for interstitial ads in this ad slot.");
        }
        if (this.zzbxp != null) {
            final zzo zzbxp = this.zzbxp;
            boolean b6 = false;
            Label_0183: {
                if (!b5) {
                    b6 = b3;
                    if (!b2) {
                        break Label_0183;
                    }
                    b6 = b3;
                    if (b4) {
                        break Label_0183;
                    }
                }
                b6 = true;
            }
            zzbxp.zzu(b6);
        }
    }
    
    public final void zzax() {
        this.zzbyb = true;
    }
    
    public final void zznh() {
        if (this.zzbxn != null && this.zzbxq) {
            this.setRequestedOrientation(this.zzbxn.orientation);
        }
        if (this.zzbxr != null) {
            this.mActivity.setContentView((View)this.zzbxv);
            this.zzbyb = true;
            this.zzbxr.removeAllViews();
            this.zzbxr = null;
        }
        if (this.zzbxs != null) {
            this.zzbxs.onCustomViewHidden();
            this.zzbxs = null;
        }
        this.zzbxq = false;
    }
    
    public final void zzni() {
        this.zzbxx = 1;
        this.mActivity.finish();
    }
    
    public final boolean zznj() {
        this.zzbxx = 0;
        boolean zzul;
        if (this.zzbnd == null) {
            zzul = true;
        }
        else {
            final boolean b = zzul = this.zzbnd.zzul();
            if (!b) {
                this.zzbnd.zza("onbackblocked", Collections.emptyMap());
                return b;
            }
        }
        return zzul;
    }
    
    public final void zznk() {
        this.zzbxv.removeView((View)this.zzbxp);
        this.zzs(true);
    }
    
    @VisibleForTesting
    final void zznm() {
        if (!this.zzbyd) {
            this.zzbyd = true;
            if (this.zzbnd != null) {
                this.zzbxv.removeView(this.zzbnd.getView());
                if (this.zzbxo != null) {
                    this.zzbnd.zzbm(this.zzbxo.zzrt);
                    this.zzbnd.zzai(false);
                    this.zzbxo.parent.addView(this.zzbnd.getView(), this.zzbxo.index, this.zzbxo.zzbyi);
                    this.zzbxo = null;
                }
                else if (this.mActivity.getApplicationContext() != null) {
                    this.zzbnd.zzbm(this.mActivity.getApplicationContext());
                }
                this.zzbnd = null;
            }
            if (this.zzbxn != null && this.zzbxn.zzbyn != null) {
                this.zzbxn.zzbyn.zzcb();
            }
        }
    }
    
    public final void zznn() {
        if (this.zzbxw) {
            this.zzbxw = false;
            this.zzno();
        }
    }
    
    public final void zznp() {
        this.zzbxv.zzbyh = true;
    }
    
    public final void zznq() {
        synchronized (this.zzbxy) {
            this.zzbya = true;
            if (this.zzbxz != null) {
                zzakk.zzcrm.removeCallbacks(this.zzbxz);
                zzakk.zzcrm.post(this.zzbxz);
            }
        }
    }
    
    public final void zzo(final IObjectWrapper objectWrapper) {
        if ((boolean)zzkb.zzik().zzd(zznk.zzbel) && PlatformVersion.isAtLeastN()) {
            final Configuration configuration = (Configuration)ObjectWrapper.unwrap(objectWrapper);
            zzbv.zzek();
            if (!zzakk.zza(this.mActivity, configuration)) {
                this.mActivity.getWindow().addFlags(2048);
                this.mActivity.getWindow().clearFlags(1024);
                return;
            }
            this.mActivity.getWindow().addFlags(1024);
            this.mActivity.getWindow().clearFlags(2048);
        }
    }
}
