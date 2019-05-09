// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.os.Bundle;
import android.content.IntentFilter;
import android.view.Window;
import android.app.Activity;
import com.google.android.gms.ads.internal.zzbv;
import android.util.DisplayMetrics;
import java.util.HashSet;
import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import android.app.Application;
import android.graphics.Rect;
import com.google.android.gms.common.util.VisibleForTesting;
import android.support.annotation.Nullable;
import android.content.BroadcastReceiver;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.view.WindowManager;
import android.content.Context;
import android.annotation.TargetApi;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnAttachStateChangeListener;
import android.app.Application$ActivityLifecycleCallbacks;

@zzadh
@TargetApi(14)
public final class zzfp implements Application$ActivityLifecycleCallbacks, View$OnAttachStateChangeListener, ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    private static final long zzagc;
    private zzamj zzadz;
    private final Context zzaeo;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private boolean zzafd;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzafe;
    private final Rect zzafh;
    private Application zzagd;
    private WeakReference<ViewTreeObserver> zzage;
    private WeakReference<View> zzagf;
    private zzfu zzagg;
    private int zzagh;
    private final HashSet<zzft> zzagi;
    private final DisplayMetrics zzagj;
    
    static {
        zzagc = (long)zzkb.zzik().zzd(zznk.zzazt);
    }
    
    public zzfp(final Context context, final View view) {
        this.zzadz = new zzamj(zzfp.zzagc);
        this.zzafd = false;
        this.zzagh = -1;
        this.zzagi = new HashSet<zzft>();
        this.zzaeo = context.getApplicationContext();
        this.zzaeu = (WindowManager)context.getSystemService("window");
        this.zzaev = (PowerManager)this.zzaeo.getSystemService("power");
        this.zzaew = (KeyguardManager)context.getSystemService("keyguard");
        if (this.zzaeo instanceof Application) {
            this.zzagd = (Application)this.zzaeo;
            this.zzagg = new zzfu((Application)this.zzaeo, (Application$ActivityLifecycleCallbacks)this);
        }
        this.zzagj = context.getResources().getDisplayMetrics();
        this.zzafh = new Rect();
        this.zzafh.right = this.zzaeu.getDefaultDisplay().getWidth();
        this.zzafh.bottom = this.zzaeu.getDefaultDisplay().getHeight();
        View view2;
        if (this.zzagf != null) {
            view2 = this.zzagf.get();
        }
        else {
            view2 = null;
        }
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
            this.zzf(view2);
        }
        this.zzagf = new WeakReference<View>(view);
        if (view != null) {
            if (zzbv.zzem().isAttachedToWindow(view)) {
                this.zze(view);
            }
            view.addOnAttachStateChangeListener((View$OnAttachStateChangeListener)this);
        }
    }
    
    private final Rect zza(final Rect rect) {
        return new Rect(this.zzn(rect.left), this.zzn(rect.top), this.zzn(rect.right), this.zzn(rect.bottom));
    }
    
    private final void zza(final Activity activity, final int zzagh) {
        if (this.zzagf != null) {
            final Window window = activity.getWindow();
            if (window != null) {
                final View peekDecorView = window.peekDecorView();
                final View view = this.zzagf.get();
                if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                    this.zzagh = zzagh;
                }
            }
        }
    }
    
    private final void zzao() {
        zzbv.zzek();
        zzakk.zzcrm.post((Runnable)new zzfq(this));
    }
    
    private final void zze(final View view) {
        final ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzage = new WeakReference<ViewTreeObserver>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
            viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
        }
        if (this.zzafe == null) {
            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzafe = new zzfr(this);
            zzbv.zzfk().zza(this.zzaeo, this.zzafe, intentFilter);
        }
        if (this.zzagd == null) {
            return;
        }
        try {
            this.zzagd.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.zzagg);
        }
        catch (Exception ex) {
            zzakb.zzb("Error registering activity lifecycle callbacks.", (Throwable)ex);
        }
    }
    
    private final void zzf(final View p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzfp.zzage:Ljava/lang/ref/WeakReference;
        //     4: ifnull          44
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzfp.zzage:Ljava/lang/ref/WeakReference;
        //    11: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //    14: checkcast       Landroid/view/ViewTreeObserver;
        //    17: astore_2       
        //    18: aload_2        
        //    19: ifnull          39
        //    22: aload_2        
        //    23: invokevirtual   android/view/ViewTreeObserver.isAlive:()Z
        //    26: ifeq            39
        //    29: aload_2        
        //    30: aload_0        
        //    31: invokevirtual   android/view/ViewTreeObserver.removeOnScrollChangedListener:(Landroid/view/ViewTreeObserver$OnScrollChangedListener;)V
        //    34: aload_2        
        //    35: aload_0        
        //    36: invokevirtual   android/view/ViewTreeObserver.removeGlobalOnLayoutListener:(Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;)V
        //    39: aload_0        
        //    40: aconst_null    
        //    41: putfield        com/google/android/gms/internal/ads/zzfp.zzage:Ljava/lang/ref/WeakReference;
        //    44: aload_1        
        //    45: invokevirtual   android/view/View.getViewTreeObserver:()Landroid/view/ViewTreeObserver;
        //    48: astore_1       
        //    49: aload_1        
        //    50: invokevirtual   android/view/ViewTreeObserver.isAlive:()Z
        //    53: ifeq            66
        //    56: aload_1        
        //    57: aload_0        
        //    58: invokevirtual   android/view/ViewTreeObserver.removeOnScrollChangedListener:(Landroid/view/ViewTreeObserver$OnScrollChangedListener;)V
        //    61: aload_1        
        //    62: aload_0        
        //    63: invokevirtual   android/view/ViewTreeObserver.removeGlobalOnLayoutListener:(Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;)V
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/internal/ads/zzfp.zzafe:Landroid/content/BroadcastReceiver;
        //    70: ifnull          92
        //    73: invokestatic    com/google/android/gms/ads/internal/zzbv.zzfk:()Lcom/google/android/gms/internal/ads/zzamq;
        //    76: aload_0        
        //    77: getfield        com/google/android/gms/internal/ads/zzfp.zzaeo:Landroid/content/Context;
        //    80: aload_0        
        //    81: getfield        com/google/android/gms/internal/ads/zzfp.zzafe:Landroid/content/BroadcastReceiver;
        //    84: invokevirtual   com/google/android/gms/internal/ads/zzamq.zza:(Landroid/content/Context;Landroid/content/BroadcastReceiver;)V
        //    87: aload_0        
        //    88: aconst_null    
        //    89: putfield        com/google/android/gms/internal/ads/zzfp.zzafe:Landroid/content/BroadcastReceiver;
        //    92: aload_0        
        //    93: getfield        com/google/android/gms/internal/ads/zzfp.zzagd:Landroid/app/Application;
        //    96: ifnull          110
        //    99: aload_0        
        //   100: getfield        com/google/android/gms/internal/ads/zzfp.zzagd:Landroid/app/Application;
        //   103: aload_0        
        //   104: getfield        com/google/android/gms/internal/ads/zzfp.zzagg:Lcom/google/android/gms/internal/ads/zzfu;
        //   107: invokevirtual   android/app/Application.unregisterActivityLifecycleCallbacks:(Landroid/app/Application$ActivityLifecycleCallbacks;)V
        //   110: return         
        //   111: astore_2       
        //   112: ldc_w           "Error while unregistering listeners from the last ViewTreeObserver."
        //   115: aload_2        
        //   116: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   119: goto            44
        //   122: astore_1       
        //   123: ldc_w           "Error while unregistering listeners from the ViewTreeObserver."
        //   126: aload_1        
        //   127: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   130: goto            66
        //   133: astore_1       
        //   134: ldc_w           "Failed trying to unregister the receiver"
        //   137: aload_1        
        //   138: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   141: goto            87
        //   144: astore_1       
        //   145: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   148: aload_1        
        //   149: ldc_w           "ActiveViewUnit.stopScreenStatusMonitoring"
        //   152: invokevirtual   com/google/android/gms/internal/ads/zzajm.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //   155: goto            87
        //   158: astore_1       
        //   159: ldc_w           "Error registering activity lifecycle callbacks."
        //   162: aload_1        
        //   163: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   166: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      18     111    122    Ljava/lang/Exception;
        //  22     39     111    122    Ljava/lang/Exception;
        //  39     44     111    122    Ljava/lang/Exception;
        //  44     66     122    133    Ljava/lang/Exception;
        //  73     87     133    144    Ljava/lang/IllegalStateException;
        //  73     87     144    158    Ljava/lang/Exception;
        //  99     110    158    167    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 81, Size: 81
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private final void zzm(int windowVisibility) {
        if (this.zzagi.size() != 0 && this.zzagf != null) {
            Object o = this.zzagf.get();
            while (true) {
            Label_0446_Outer:
                while (true) {
                    Label_0045: {
                        while (true) {
                            Label_0037: {
                                if (windowVisibility == 1) {
                                    final int n = 1;
                                    break Label_0037;
                                }
                                boolean zzafd = false;
                                Label_0441: {
                                    break Label_0441;
                                    int n = 0;
                                    final Rect rect;
                                    boolean globalVisibleRect;
                                    final Rect rect2;
                                    boolean localVisibleRect;
                                    final Rect rect3;
                                    final int[] array;
                                    final int[] array2;
                                    Object iterator = null;
                                    int n2;
                                    int n3 = 0;
                                    long elapsedRealtime;
                                    boolean screenOn;
                                    boolean attachedToWindow;
                                    Label_0202_Outer:Label_0256_Outer:Label_0330_Outer:Label_0341_Outer:
                                    while (true) {
                                        globalVisibleRect = ((View)o).getGlobalVisibleRect(rect);
                                        localVisibleRect = ((View)o).getLocalVisibleRect(rect2);
                                        ((View)o).getHitRect(rect3);
                                        while (true) {
                                        Label_0483:
                                            while (true) {
                                            Label_0477:
                                                while (true) {
                                                Label_0471:
                                                    while (true) {
                                                    Label_0465:
                                                        while (true) {
                                                            try {
                                                                ((View)o).getLocationOnScreen(array);
                                                                ((View)o).getLocationInWindow(array2);
                                                                ((Rect)iterator).left = array[0];
                                                                ((Rect)iterator).top = array[1];
                                                                ((Rect)iterator).right = ((Rect)iterator).left + ((View)o).getWidth();
                                                                ((Rect)iterator).bottom = ((Rect)iterator).top + ((View)o).getHeight();
                                                                if (o == null) {
                                                                    break Label_0465;
                                                                }
                                                                n2 = ((View)o).getWindowVisibility();
                                                                if (this.zzagh != -1) {
                                                                    n2 = this.zzagh;
                                                                }
                                                                if (n3 != 0 || !zzbv.zzek().zza((View)o, this.zzaev, this.zzaew) || !globalVisibleRect || !localVisibleRect || n2 != 0) {
                                                                    break Label_0471;
                                                                }
                                                                zzafd = true;
                                                                if ((n != 0 && !this.zzadz.tryAcquire() && zzafd == this.zzafd) || (!zzafd && !this.zzafd && windowVisibility == 1)) {
                                                                    return;
                                                                }
                                                                elapsedRealtime = zzbv.zzer().elapsedRealtime();
                                                                screenOn = this.zzaev.isScreenOn();
                                                                if (o == null) {
                                                                    break Label_0477;
                                                                }
                                                                attachedToWindow = zzbv.zzem().isAttachedToWindow((View)o);
                                                                if (o != null) {
                                                                    windowVisibility = ((View)o).getWindowVisibility();
                                                                    o = new zzfs(elapsedRealtime, screenOn, attachedToWindow, windowVisibility, this.zza(this.zzafh), this.zza((Rect)iterator), this.zza(rect), globalVisibleRect, this.zza(rect2), localVisibleRect, this.zza(rect3), this.zzagj.density, zzafd);
                                                                    iterator = this.zzagi.iterator();
                                                                    while (((Iterator)iterator).hasNext()) {
                                                                        ((Iterator<zzft>)iterator).next().zza((zzfs)o);
                                                                    }
                                                                    break;
                                                                }
                                                                break Label_0483;
                                                                n3 = 0;
                                                                break Label_0045;
                                                                n = 0;
                                                                break Label_0037;
                                                            }
                                                            catch (Exception ex) {
                                                                zzakb.zzb("Failure getting view location.", (Throwable)ex);
                                                                continue Label_0202_Outer;
                                                            }
                                                            break;
                                                        }
                                                        n2 = 8;
                                                        continue Label_0256_Outer;
                                                    }
                                                    zzafd = false;
                                                    continue Label_0330_Outer;
                                                }
                                                attachedToWindow = false;
                                                continue Label_0341_Outer;
                                            }
                                            windowVisibility = 8;
                                            continue Label_0446_Outer;
                                        }
                                    }
                                }
                                this.zzafd = zzafd;
                                return;
                            }
                            if (o != null) {
                                continue;
                            }
                            break;
                        }
                        int n3 = 1;
                    }
                    Object iterator = new Rect();
                    final Rect rect = new Rect();
                    boolean globalVisibleRect = false;
                    final Rect rect2 = new Rect();
                    boolean localVisibleRect = false;
                    final Rect rect3 = new Rect();
                    final int[] array = new int[2];
                    final int[] array2 = new int[2];
                    if (o != null) {
                        continue Label_0446_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    private final int zzn(final int n) {
        return (int)(n / this.zzagj.density);
    }
    
    public final void onActivityCreated(final Activity activity, final Bundle bundle) {
        this.zza(activity, 0);
        this.zzm(3);
        this.zzao();
    }
    
    public final void onActivityDestroyed(final Activity activity) {
        this.zzm(3);
        this.zzao();
    }
    
    public final void onActivityPaused(final Activity activity) {
        this.zza(activity, 4);
        this.zzm(3);
        this.zzao();
    }
    
    public final void onActivityResumed(final Activity activity) {
        this.zza(activity, 0);
        this.zzm(3);
        this.zzao();
    }
    
    public final void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        this.zzm(3);
        this.zzao();
    }
    
    public final void onActivityStarted(final Activity activity) {
        this.zza(activity, 0);
        this.zzm(3);
        this.zzao();
    }
    
    public final void onActivityStopped(final Activity activity) {
        this.zzm(3);
        this.zzao();
    }
    
    public final void onGlobalLayout() {
        this.zzm(2);
        this.zzao();
    }
    
    public final void onScrollChanged() {
        this.zzm(1);
    }
    
    public final void onViewAttachedToWindow(final View view) {
        this.zzagh = -1;
        this.zze(view);
        this.zzm(3);
    }
    
    public final void onViewDetachedFromWindow(final View view) {
        this.zzagh = -1;
        this.zzm(3);
        this.zzao();
        this.zzf(view);
    }
    
    public final void zza(final zzft zzft) {
        this.zzagi.add(zzft);
        this.zzm(3);
    }
    
    public final void zzb(final zzft zzft) {
        this.zzagi.remove(zzft);
    }
    
    public final void zzgm() {
        this.zzm(4);
    }
}
