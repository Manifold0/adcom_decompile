// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.view.Window;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.app.Activity;
import android.view.View;

@zzadh
public final class zzamt
{
    private final View mView;
    private Activity zzcuj;
    private boolean zzcuk;
    private boolean zzcul;
    private boolean zzcum;
    private ViewTreeObserver$OnGlobalLayoutListener zzcun;
    private ViewTreeObserver$OnScrollChangedListener zzcuo;
    
    public zzamt(final Activity zzcuj, final View mView, final ViewTreeObserver$OnGlobalLayoutListener zzcun, final ViewTreeObserver$OnScrollChangedListener zzcuo) {
        this.zzcuj = zzcuj;
        this.mView = mView;
        this.zzcun = zzcun;
        this.zzcuo = zzcuo;
    }
    
    private static ViewTreeObserver zzj(final Activity activity) {
        if (activity != null) {
            final Window window = activity.getWindow();
            if (window != null) {
                final View decorView = window.getDecorView();
                if (decorView != null) {
                    return decorView.getViewTreeObserver();
                }
            }
        }
        return null;
    }
    
    private final void zzse() {
        if (!this.zzcuk) {
            if (this.zzcun != null) {
                if (this.zzcuj != null) {
                    final Activity zzcuj = this.zzcuj;
                    final ViewTreeObserver$OnGlobalLayoutListener zzcun = this.zzcun;
                    final ViewTreeObserver zzj = zzj(zzcuj);
                    if (zzj != null) {
                        zzj.addOnGlobalLayoutListener(zzcun);
                    }
                }
                zzbv.zzfg();
                zzaor.zza(this.mView, this.zzcun);
            }
            if (this.zzcuo != null) {
                if (this.zzcuj != null) {
                    final Activity zzcuj2 = this.zzcuj;
                    final ViewTreeObserver$OnScrollChangedListener zzcuo = this.zzcuo;
                    final ViewTreeObserver zzj2 = zzj(zzcuj2);
                    if (zzj2 != null) {
                        zzj2.addOnScrollChangedListener(zzcuo);
                    }
                }
                zzbv.zzfg();
                zzaor.zza(this.mView, this.zzcuo);
            }
            this.zzcuk = true;
        }
    }
    
    private final void zzsf() {
        if (this.zzcuj != null && this.zzcuk) {
            if (this.zzcun != null) {
                final Activity zzcuj = this.zzcuj;
                final ViewTreeObserver$OnGlobalLayoutListener zzcun = this.zzcun;
                final ViewTreeObserver zzj = zzj(zzcuj);
                if (zzj != null) {
                    zzbv.zzem().zza(zzj, zzcun);
                }
            }
            if (this.zzcuo != null) {
                final Activity zzcuj2 = this.zzcuj;
                final ViewTreeObserver$OnScrollChangedListener zzcuo = this.zzcuo;
                final ViewTreeObserver zzj2 = zzj(zzcuj2);
                if (zzj2 != null) {
                    zzj2.removeOnScrollChangedListener(zzcuo);
                }
            }
            this.zzcuk = false;
        }
    }
    
    public final void onAttachedToWindow() {
        this.zzcul = true;
        if (this.zzcum) {
            this.zzse();
        }
    }
    
    public final void onDetachedFromWindow() {
        this.zzcul = false;
        this.zzsf();
    }
    
    public final void zzi(final Activity zzcuj) {
        this.zzcuj = zzcuj;
    }
    
    public final void zzsc() {
        this.zzcum = true;
        if (this.zzcul) {
            this.zzse();
        }
    }
    
    public final void zzsd() {
        this.zzcum = false;
        this.zzsf();
    }
}
