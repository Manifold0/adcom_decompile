// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.content.IntentFilter;
import android.view.WindowManager$LayoutParams;
import android.os.SystemClock;
import android.graphics.Rect;
import android.view.Window;
import android.app.Activity;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import android.content.BroadcastReceiver;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.content.Context;
import android.app.Application;
import android.os.Handler;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnAttachStateChangeListener;
import android.app.Application$ActivityLifecycleCallbacks;

public final class zzdi implements Application$ActivityLifecycleCallbacks, View$OnAttachStateChangeListener, ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    private static final Handler zzsy;
    private final zzcz zzps;
    private Application zzrk;
    private final Context zzsz;
    private final PowerManager zzta;
    private final KeyguardManager zztb;
    private BroadcastReceiver zztc;
    private WeakReference<ViewTreeObserver> zztd;
    private WeakReference<View> zzte;
    private zzcn zztf;
    private boolean zztg;
    private int zzth;
    private long zzti;
    
    static {
        zzsy = new Handler(Looper.getMainLooper());
    }
    
    public zzdi(final zzcz zzps, final View view) {
        this.zztg = false;
        this.zzth = -1;
        this.zzti = -3L;
        this.zzps = zzps;
        this.zzsz = zzps.zzrt;
        this.zzta = (PowerManager)this.zzsz.getSystemService("power");
        this.zztb = (KeyguardManager)this.zzsz.getSystemService("keyguard");
        if (this.zzsz instanceof Application) {
            this.zzrk = (Application)this.zzsz;
            this.zztf = new zzcn((Application)this.zzsz, (Application$ActivityLifecycleCallbacks)this);
        }
        this.zzd(view);
    }
    
    private final void zza(final Activity activity, final int zzth) {
        if (this.zzte != null) {
            final Window window = activity.getWindow();
            if (window != null) {
                final View peekDecorView = window.peekDecorView();
                final View view = this.zzte.get();
                if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                    this.zzth = zzth;
                }
            }
        }
    }
    
    private final void zzao() {
        zzdi.zzsy.post((Runnable)new zzdj(this));
    }
    
    private final void zzaq() {
        boolean zztg = true;
        if (this.zzte != null) {
            final View view = this.zzte.get();
            if (view == null) {
                this.zzti = -3L;
                this.zztg = false;
                return;
            }
            final boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
            final boolean localVisibleRect = view.getLocalVisibleRect(new Rect());
            boolean b = false;
            Label_0139: {
                Label_0137: {
                    if (!this.zzps.zzai()) {
                        Label_0246: {
                            if (this.zztb.inKeyguardRestrictedInputMode()) {
                                final Activity zzc = zzdg.zzc(view);
                                while (true) {
                                    Label_0241: {
                                        if (zzc == null) {
                                            break Label_0241;
                                        }
                                        final Window window = zzc.getWindow();
                                        WindowManager$LayoutParams attributes;
                                        if (window == null) {
                                            attributes = null;
                                        }
                                        else {
                                            attributes = window.getAttributes();
                                        }
                                        if (attributes == null || (attributes.flags & 0x80000) == 0x0) {
                                            break Label_0241;
                                        }
                                        final int n = 1;
                                        if (n != 0) {
                                            break Label_0137;
                                        }
                                        break Label_0246;
                                    }
                                    final int n = 0;
                                    continue;
                                }
                            }
                        }
                        b = false;
                        break Label_0139;
                    }
                }
                b = true;
            }
            int n2 = view.getWindowVisibility();
            if (this.zzth != -1) {
                n2 = this.zzth;
            }
            if (view.getVisibility() != 0 || !view.isShown() || !this.zzta.isScreenOn() || !b || !localVisibleRect || !globalVisibleRect || n2 != 0) {
                zztg = false;
            }
            if (this.zztg != zztg) {
                long elapsedRealtime;
                if (zztg) {
                    elapsedRealtime = SystemClock.elapsedRealtime();
                }
                else {
                    elapsedRealtime = -2L;
                }
                this.zzti = elapsedRealtime;
                this.zztg = zztg;
            }
        }
    }
    
    private final void zze(final View view) {
        final ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zztd = new WeakReference<ViewTreeObserver>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
            viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
        }
        if (this.zztc == null) {
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zztc = new zzdk(this);
            this.zzsz.registerReceiver(this.zztc, intentFilter);
        }
        if (this.zzrk == null) {
            return;
        }
        try {
            this.zzrk.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.zztf);
        }
        catch (Exception ex) {}
    }
    
    private final void zzf(final View ex) {
        while (true) {
            try {
                if (this.zztd != null) {
                    final ViewTreeObserver viewTreeObserver = this.zztd.get();
                    if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
                        viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                    this.zztd = null;
                }
                try {
                    final ViewTreeObserver viewTreeObserver2 = ((View)ex).getViewTreeObserver();
                    if (viewTreeObserver2.isAlive()) {
                        viewTreeObserver2.removeOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
                        viewTreeObserver2.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                    }
                    Label_0089: {
                        if (this.zztc == null) {
                            break Label_0089;
                        }
                        try {
                            this.zzsz.unregisterReceiver(this.zztc);
                            this.zztc = null;
                            if (this.zzrk == null) {
                                return;
                            }
                            try {
                                this.zzrk.unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.zztf);
                            }
                            catch (Exception ex2) {}
                        }
                        catch (Exception ex3) {}
                    }
                }
                catch (Exception ex) {}
            }
            catch (Exception ex4) {
                continue;
            }
            break;
        }
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
        this.zza(activity, 0);
        this.zzaq();
    }
    
    public final void onActivityDestroyed(final Activity activity) {
        this.zzaq();
    }
    
    public final void onActivityPaused(final Activity activity) {
        this.zza(activity, 4);
        this.zzaq();
    }
    
    public final void onActivityResumed(final Activity activity) {
        this.zza(activity, 0);
        this.zzaq();
        this.zzao();
    }
    
    public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        this.zzaq();
    }
    
    public final void onActivityStarted(final Activity activity) {
        this.zza(activity, 0);
        this.zzaq();
    }
    
    public final void onActivityStopped(final Activity activity) {
        this.zzaq();
    }
    
    public final void onGlobalLayout() {
        this.zzaq();
    }
    
    public final void onScrollChanged() {
        this.zzaq();
    }
    
    public final void onViewAttachedToWindow(final View view) {
        this.zzth = -1;
        this.zze(view);
        this.zzaq();
    }
    
    public final void onViewDetachedFromWindow(final View view) {
        this.zzth = -1;
        this.zzaq();
        this.zzao();
        this.zzf(view);
    }
    
    public final long zzap() {
        if (this.zzti == -2L && this.zzte.get() == null) {
            this.zzti = -3L;
        }
        return this.zzti;
    }
    
    final void zzd(final View view) {
        View view2;
        if (this.zzte != null) {
            view2 = this.zzte.get();
        }
        else {
            view2 = null;
        }
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
            this.zzf(view2);
        }
        this.zzte = new WeakReference<View>(view);
        if (view != null) {
            int n;
            if (view.getWindowToken() != null || view.getWindowVisibility() != 8) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                this.zze(view);
            }
            view.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
            this.zzti = -2L;
            return;
        }
        this.zzti = -3L;
    }
}
