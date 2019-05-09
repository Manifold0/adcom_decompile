// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.ViewParent;
import android.view.Window;
import android.widget.RelativeLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.View$OnClickListener;
import android.view.View;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CollectionUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.app.Activity;
import java.util.Set;

@zzadh
public final class zzaab extends zzaal
{
    private static final Set<String> zzbvy;
    private final Object mLock;
    private zzaam zzbmy;
    private final zzaqw zzbnd;
    private final Activity zzbvp;
    private String zzbvz;
    private boolean zzbwa;
    private int zzbwb;
    private int zzbwc;
    private int zzbwd;
    private int zzbwe;
    private zzasi zzbwf;
    private ImageView zzbwg;
    private LinearLayout zzbwh;
    private PopupWindow zzbwi;
    private RelativeLayout zzbwj;
    private ViewGroup zzbwk;
    private int zzuq;
    private int zzur;
    
    static {
        zzbvy = CollectionUtils.setOf((Object[])new String[] { "top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center" });
    }
    
    public zzaab(final zzaqw zzbnd, final zzaam zzbmy) {
        super(zzbnd, "resize");
        this.zzbvz = "top-right";
        this.zzbwa = true;
        this.zzbwb = 0;
        this.zzbwc = 0;
        this.zzur = -1;
        this.zzbwd = 0;
        this.zzbwe = 0;
        this.zzuq = -1;
        this.mLock = new Object();
        this.zzbnd = zzbnd;
        this.zzbvp = zzbnd.zzto();
        this.zzbmy = zzbmy;
    }
    
    private final void zza(final int n, final int n2) {
        this.zzb(n, n2 - zzbv.zzek().zzh(this.zzbvp)[0], this.zzuq, this.zzur);
    }
    
    private final int[] zzne() {
        final int[] zzg = zzbv.zzek().zzg(this.zzbvp);
        final int[] zzh = zzbv.zzek().zzh(this.zzbvp);
        final int n = zzg[0];
        final int n2 = zzg[1];
        int n3 = 0;
        Label_0058: {
            if (this.zzuq < 50 || this.zzuq > n) {
                zzakb.zzdk("Width is too small or too large.");
                n3 = 0;
            }
            else if (this.zzur < 50 || this.zzur > n2) {
                zzakb.zzdk("Height is too small or too large.");
                n3 = 0;
            }
            else if (this.zzur == n2 && this.zzuq == n) {
                zzakb.zzdk("Cannot resize to a full-screen ad.");
                n3 = 0;
            }
            else {
                if (this.zzbwa) {
                    final String zzbvz = this.zzbvz;
                    int n4 = -1;
                    switch (zzbvz.hashCode()) {
                        case -1012429441: {
                            if (zzbvz.equals("top-left")) {
                                n4 = 0;
                                break;
                            }
                            break;
                        }
                        case 1755462605: {
                            if (zzbvz.equals("top-center")) {
                                n4 = 1;
                                break;
                            }
                            break;
                        }
                        case -1364013995: {
                            if (zzbvz.equals("center")) {
                                n4 = 2;
                                break;
                            }
                            break;
                        }
                        case -655373719: {
                            if (zzbvz.equals("bottom-left")) {
                                n4 = 3;
                                break;
                            }
                            break;
                        }
                        case 1288627767: {
                            if (zzbvz.equals("bottom-center")) {
                                n4 = 4;
                                break;
                            }
                            break;
                        }
                        case 1163912186: {
                            if (zzbvz.equals("bottom-right")) {
                                n4 = 5;
                                break;
                            }
                            break;
                        }
                    }
                    int n5 = 0;
                    int n6 = 0;
                    switch (n4) {
                        default: {
                            n5 = this.zzbwb + this.zzbwd + this.zzuq - 50;
                            n6 = this.zzbwc + this.zzbwe;
                            break;
                        }
                        case 0: {
                            n5 = this.zzbwd + this.zzbwb;
                            n6 = this.zzbwc + this.zzbwe;
                            break;
                        }
                        case 1: {
                            n5 = this.zzbwb + this.zzbwd + this.zzuq / 2 - 25;
                            n6 = this.zzbwc + this.zzbwe;
                            break;
                        }
                        case 2: {
                            n5 = this.zzbwb + this.zzbwd + this.zzuq / 2 - 25;
                            n6 = this.zzbwc + this.zzbwe + this.zzur / 2 - 25;
                            break;
                        }
                        case 3: {
                            n5 = this.zzbwd + this.zzbwb;
                            n6 = this.zzbwc + this.zzbwe + this.zzur - 50;
                            break;
                        }
                        case 4: {
                            n5 = this.zzbwb + this.zzbwd + this.zzuq / 2 - 25;
                            n6 = this.zzbwc + this.zzbwe + this.zzur - 50;
                            break;
                        }
                        case 5: {
                            n5 = this.zzbwb + this.zzbwd + this.zzuq - 50;
                            n6 = this.zzbwc + this.zzbwe + this.zzur - 50;
                            break;
                        }
                    }
                    if (n5 < 0 || n5 + 50 > n || n6 < zzh[0] || n6 + 50 > zzh[1]) {
                        n3 = 0;
                        break Label_0058;
                    }
                }
                n3 = 1;
            }
        }
        if (n3 == 0) {
            return null;
        }
        if (this.zzbwa) {
            return new int[] { this.zzbwb + this.zzbwd, this.zzbwc + this.zzbwe };
        }
        final int[] zzg2 = zzbv.zzek().zzg(this.zzbvp);
        final int[] zzh2 = zzbv.zzek().zzh(this.zzbvp);
        final int n7 = zzg2[0];
        final int n8 = this.zzbwb + this.zzbwd;
        final int n9 = this.zzbwc + this.zzbwe;
        int n10;
        if (n8 < 0) {
            n10 = 0;
        }
        else {
            n10 = n8;
            if (this.zzuq + n8 > n7) {
                n10 = n7 - this.zzuq;
            }
        }
        int n11;
        if (n9 < zzh2[0]) {
            n11 = zzh2[0];
        }
        else {
            n11 = n9;
            if (this.zzur + n9 > zzh2[1]) {
                n11 = zzh2[1] - this.zzur;
            }
        }
        return new int[] { n10, n11 };
    }
    
    public final void zza(int zza, final int zzbwc, final boolean b) {
        synchronized (this.mLock) {
            this.zzbwb = zza;
            this.zzbwc = zzbwc;
            if (this.zzbwi != null && b) {
                final int[] zzne = this.zzne();
                if (zzne != null) {
                    final PopupWindow zzbwi = this.zzbwi;
                    zzkb.zzif();
                    zza = zzamu.zza((Context)this.zzbvp, zzne[0]);
                    zzkb.zzif();
                    zzbwi.update(zza, zzamu.zza((Context)this.zzbvp, zzne[1]), this.zzbwi.getWidth(), this.zzbwi.getHeight());
                    this.zza(zzne[0], zzne[1]);
                }
                else {
                    this.zzm(true);
                }
            }
        }
    }
    
    public final void zzb(final int zzbwb, final int zzbwc) {
        this.zzbwb = zzbwb;
        this.zzbwc = zzbwc;
    }
    
    public final void zzk(final Map<String, String> map) {
        synchronized (this.mLock) {
            if (this.zzbvp == null) {
                this.zzbw("Not an activity context. Cannot resize.");
                return;
            }
            if (this.zzbnd.zzud() == null) {
                this.zzbw("Webview is not yet available, size is not set.");
                return;
            }
        }
        if (this.zzbnd.zzud().zzvs()) {
            this.zzbw("Is interstitial. Cannot resize an interstitial.");
            // monitorexit(o)
            return;
        }
        if (this.zzbnd.zzuj()) {
            this.zzbw("Cannot resize an expanded banner.");
            // monitorexit(o)
            return;
        }
        final Map<K, String> map2;
        if (!TextUtils.isEmpty((CharSequence)map2.get("width"))) {
            zzbv.zzek();
            this.zzuq = zzakk.zzcv(map2.get("width"));
        }
        if (!TextUtils.isEmpty((CharSequence)map2.get("height"))) {
            zzbv.zzek();
            this.zzur = zzakk.zzcv(map2.get("height"));
        }
        if (!TextUtils.isEmpty((CharSequence)map2.get("offsetX"))) {
            zzbv.zzek();
            this.zzbwd = zzakk.zzcv(map2.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence)map2.get("offsetY"))) {
            zzbv.zzek();
            this.zzbwe = zzakk.zzcv(map2.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence)map2.get("allowOffscreen"))) {
            this.zzbwa = Boolean.parseBoolean(map2.get("allowOffscreen"));
        }
        final String zzbvz = map2.get("customClosePosition");
        if (!TextUtils.isEmpty((CharSequence)zzbvz)) {
            this.zzbvz = zzbvz;
        }
    Label_0810_Outer:
        while (true) {
            Label_1287: {
                if (this.zzuq < 0 || this.zzur < 0) {
                    break Label_1287;
                }
                final int n = 1;
                if (n == 0) {
                    this.zzbw("Invalid width and height options. Cannot resize.");
                    // monitorexit(o)
                    return;
                }
                final Window window = this.zzbvp.getWindow();
                if (window == null || window.getDecorView() == null) {
                    this.zzbw("Activity context is not ready, cannot get window or decor view.");
                    // monitorexit(o)
                    return;
                }
                final int[] zzne = this.zzne();
                if (zzne == null) {
                    this.zzbw("Resize location out of screen or close button is not visible.");
                    // monitorexit(o)
                    return;
                }
                zzkb.zzif();
                final int zza = zzamu.zza((Context)this.zzbvp, this.zzuq);
                zzkb.zzif();
                final int zza2 = zzamu.zza((Context)this.zzbvp, this.zzur);
                final ViewParent parent = this.zzbnd.getView().getParent();
                RelativeLayout$LayoutParams relativeLayout$LayoutParams = null;
                PopupWindow zzbwi;
                View decorView;
                int zza3;
                int n2;
                int n3;
                String zzbvz2;
                int n4 = 0;
                String value;
                String concat;
                int zza4;
                Label_0992_Outer:Label_1143_Outer:
                while (true) {
                Label_1082_Outer:
                    while (true) {
                    Label_1160_Outer:
                        while (true) {
                        Label_1126_Outer:
                            while (true) {
                            Label_1099_Outer:
                                while (true) {
                                Label_1116_Outer:
                                    while (true) {
                                        while (true) {
                                            Label_1294: {
                                                while (true) {
                                                    while (true) {
                                                    Label_1067_Outer:
                                                        while (true) {
                                                        Label_1052_Outer:
                                                            while (true) {
                                                            Label_1037_Outer:
                                                                while (true) {
                                                                    while (true) {
                                                                        Label_0981: {
                                                                            if (parent == null || !(parent instanceof ViewGroup)) {
                                                                                break Label_0981;
                                                                            }
                                                                            ((ViewGroup)parent).removeView(this.zzbnd.getView());
                                                                            if (this.zzbwi == null) {
                                                                                this.zzbwk = (ViewGroup)parent;
                                                                                zzbv.zzek();
                                                                                (this.zzbwg = new ImageView((Context)this.zzbvp)).setImageBitmap(zzakk.zzs(this.zzbnd.getView()));
                                                                                this.zzbwf = this.zzbnd.zzud();
                                                                                this.zzbwk.addView((View)this.zzbwg);
                                                                                break Label_0981;
                                                                            }
                                                                            Label_0971: {
                                                                                break Label_0971;
                                                                            Block_30_Outer:
                                                                                while (true) {
                                                                                    this.zzbwh.setOnClickListener((View$OnClickListener)new zzaac(this));
                                                                                    this.zzbwh.setContentDescription((CharSequence)"Close button");
                                                                                    this.zzbwj.addView((View)this.zzbwh, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
                                                                                    try {
                                                                                        zzbwi = this.zzbwi;
                                                                                        decorView = window.getDecorView();
                                                                                        zzkb.zzif();
                                                                                        zza3 = zzamu.zza((Context)this.zzbvp, zzne[0]);
                                                                                        zzkb.zzif();
                                                                                        zzbwi.showAtLocation(decorView, 0, zza3, zzamu.zza((Context)this.zzbvp, zzne[1]));
                                                                                        n2 = zzne[0];
                                                                                        n3 = zzne[1];
                                                                                        if (this.zzbmy != null) {
                                                                                            this.zzbmy.zza(n2, n3, this.zzuq, this.zzur);
                                                                                        }
                                                                                        this.zzbnd.zza(zzasi.zzi(zza, zza2));
                                                                                        this.zza(zzne[0], zzne[1]);
                                                                                        this.zzby("resized");
                                                                                        // monitorexit(o)
                                                                                        return;
                                                                                        // iftrue(Label_1292:, !zzbvz2.equals((Object)"top-left"))
                                                                                        // monitorexit(o)
                                                                                        // iftrue(Label_1292:, !zzbvz2.equals((Object)"top-center"))
                                                                                        // iftrue(Label_1292:, !zzbvz2.equals((Object)"center"))
                                                                                        // iftrue(Label_1292:, !zzbvz2.equals((Object)"bottom-right"))
                                                                                        // iftrue(Label_1292:, !zzbvz2.equals((Object)"bottom-center"))
                                                                                        // iftrue(Label_1292:, !zzbvz2.equals((Object)"bottom-left"))
                                                                                    Block_31:
                                                                                        while (true) {
                                                                                            while (true) {
                                                                                                Block_26: {
                                                                                                    break Block_26;
                                                                                                    while (true) {
                                                                                                        n4 = 2;
                                                                                                        break Label_1294;
                                                                                                        n4 = 4;
                                                                                                        break Label_1294;
                                                                                                        while (true) {
                                                                                                            n4 = 1;
                                                                                                            break Label_1294;
                                                                                                            this.zzbw("Webview is detached, probably in the middle of a resize or expand.");
                                                                                                            return;
                                                                                                            relativeLayout$LayoutParams.addRule(12);
                                                                                                            relativeLayout$LayoutParams.addRule(14);
                                                                                                            continue Label_0810_Outer;
                                                                                                            continue Label_1143_Outer;
                                                                                                        }
                                                                                                        n4 = 3;
                                                                                                        break Label_1294;
                                                                                                        relativeLayout$LayoutParams.addRule(10);
                                                                                                        relativeLayout$LayoutParams.addRule(9);
                                                                                                        continue Label_0810_Outer;
                                                                                                        relativeLayout$LayoutParams.addRule(12);
                                                                                                        relativeLayout$LayoutParams.addRule(11);
                                                                                                        continue Label_0810_Outer;
                                                                                                        relativeLayout$LayoutParams.addRule(12);
                                                                                                        relativeLayout$LayoutParams.addRule(9);
                                                                                                        continue Label_0810_Outer;
                                                                                                        relativeLayout$LayoutParams.addRule(10);
                                                                                                        relativeLayout$LayoutParams.addRule(14);
                                                                                                        continue Label_0810_Outer;
                                                                                                        continue Block_30_Outer;
                                                                                                    }
                                                                                                }
                                                                                                n4 = 0;
                                                                                                break Label_1294;
                                                                                                break Block_31;
                                                                                                continue Label_1143_Outer;
                                                                                            }
                                                                                            continue Label_1082_Outer;
                                                                                        }
                                                                                        n4 = 5;
                                                                                        break Label_1294;
                                                                                        this.zzbwi.dismiss();
                                                                                        break;
                                                                                        relativeLayout$LayoutParams.addRule(13);
                                                                                        continue Label_0810_Outer;
                                                                                    }
                                                                                    catch (RuntimeException ex) {
                                                                                        value = String.valueOf(ex.getMessage());
                                                                                        if (value.length() != 0) {
                                                                                            concat = "Cannot show popup window: ".concat(value);
                                                                                        }
                                                                                        else {
                                                                                            concat = new String("Cannot show popup window: ");
                                                                                        }
                                                                                        this.zzbw(concat);
                                                                                        this.zzbwj.removeView(this.zzbnd.getView());
                                                                                        if (this.zzbwk != null) {
                                                                                            this.zzbwk.removeView((View)this.zzbwg);
                                                                                            this.zzbwk.addView(this.zzbnd.getView());
                                                                                            this.zzbnd.zza(this.zzbwf);
                                                                                        }
                                                                                        // monitorexit(o)
                                                                                        return;
                                                                                    }
                                                                                    break Label_1287;
                                                                                }
                                                                            }
                                                                        }
                                                                        (this.zzbwj = new RelativeLayout((Context)this.zzbvp)).setBackgroundColor(0);
                                                                        this.zzbwj.setLayoutParams(new ViewGroup$LayoutParams(zza, zza2));
                                                                        zzbv.zzek();
                                                                        (this.zzbwi = zzakk.zza((View)this.zzbwj, zza, zza2, false)).setOutsideTouchable(true);
                                                                        this.zzbwi.setTouchable(true);
                                                                        this.zzbwi.setClippingEnabled(!this.zzbwa);
                                                                        this.zzbwj.addView(this.zzbnd.getView(), -1, -1);
                                                                        this.zzbwh = new LinearLayout((Context)this.zzbvp);
                                                                        zzkb.zzif();
                                                                        zza4 = zzamu.zza((Context)this.zzbvp, 50);
                                                                        zzkb.zzif();
                                                                        relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(zza4, zzamu.zza((Context)this.zzbvp, 50));
                                                                        zzbvz2 = this.zzbvz;
                                                                        switch (zzbvz2.hashCode()) {
                                                                            case -1012429441: {
                                                                                continue Label_1143_Outer;
                                                                            }
                                                                            case 1755462605: {
                                                                                continue Label_1082_Outer;
                                                                            }
                                                                            case -1364013995: {
                                                                                continue Label_1067_Outer;
                                                                            }
                                                                            case -655373719: {
                                                                                continue Label_1116_Outer;
                                                                            }
                                                                            case 1288627767: {
                                                                                continue Label_1037_Outer;
                                                                            }
                                                                            case 1163912186: {
                                                                                continue Label_1052_Outer;
                                                                            }
                                                                        }
                                                                        break;
                                                                    }
                                                                    break;
                                                                }
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                Label_1292: {
                                                    n4 = -1;
                                                }
                                            }
                                            switch (n4) {
                                                case 0: {
                                                    continue Label_1160_Outer;
                                                }
                                                case 1: {
                                                    continue Label_1116_Outer;
                                                }
                                                case 2: {
                                                    continue;
                                                }
                                                case 3: {
                                                    continue Label_1099_Outer;
                                                }
                                                case 4: {
                                                    continue Label_1082_Outer;
                                                }
                                                case 5: {
                                                    continue Label_1126_Outer;
                                                }
                                                default: {
                                                    relativeLayout$LayoutParams.addRule(10);
                                                    relativeLayout$LayoutParams.addRule(11);
                                                    continue Label_0992_Outer;
                                                }
                                            }
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    break;
                }
            }
            final int n = 0;
            continue;
        }
    }
    
    public final void zzm(final boolean b) {
        synchronized (this.mLock) {
            if (this.zzbwi != null) {
                this.zzbwi.dismiss();
                this.zzbwj.removeView(this.zzbnd.getView());
                if (this.zzbwk != null) {
                    this.zzbwk.removeView((View)this.zzbwg);
                    this.zzbwk.addView(this.zzbnd.getView());
                    this.zzbnd.zza(this.zzbwf);
                }
                if (b) {
                    this.zzby("default");
                    if (this.zzbmy != null) {
                        this.zzbmy.zzcq();
                    }
                }
                this.zzbwi = null;
                this.zzbwj = null;
                this.zzbwk = null;
                this.zzbwh = null;
            }
        }
    }
    
    public final boolean zznf() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzbwi != null) {
                    return true;
                }
            }
            return false;
        }
    }
}
