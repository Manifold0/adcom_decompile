// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.ref.Reference;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.view.MotionEvent;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.annotation.TargetApi;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Collections;
import java.util.HashMap;
import android.graphics.Point;
import java.lang.ref.WeakReference;
import java.util.Map;
import android.view.View;
import android.widget.FrameLayout;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;

@zzadh
@ParametersAreNonnullByDefault
public final class zzpn extends zzqb implements View$OnClickListener, View$OnTouchListener, ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    @VisibleForTesting
    private static final String[] zzbjs;
    private final Object mLock;
    @Nullable
    @VisibleForTesting
    private zzoz zzbij;
    private final FrameLayout zzbjt;
    private View zzbju;
    private final boolean zzbjv;
    @VisibleForTesting
    private Map<String, WeakReference<View>> zzbjw;
    @Nullable
    @VisibleForTesting
    private View zzbjx;
    @VisibleForTesting
    private boolean zzbjy;
    @VisibleForTesting
    private Point zzbjz;
    @VisibleForTesting
    private Point zzbka;
    @VisibleForTesting
    private WeakReference<zzfp> zzbkb;
    @Nullable
    @VisibleForTesting
    private FrameLayout zzvh;
    
    static {
        zzbjs = new String[] { "2011", "1009", "3010" };
    }
    
    @TargetApi(21)
    public zzpn(final FrameLayout zzbjt, final FrameLayout zzvh) {
        this.mLock = new Object();
        this.zzbjw = Collections.synchronizedMap(new HashMap<String, WeakReference<View>>());
        this.zzbjy = false;
        this.zzbjz = new Point();
        this.zzbka = new Point();
        this.zzbkb = new WeakReference<zzfp>(null);
        this.zzbjt = zzbjt;
        this.zzvh = zzvh;
        zzbv.zzfg();
        zzaor.zza((View)this.zzbjt, (ViewTreeObserver$OnGlobalLayoutListener)this);
        zzbv.zzfg();
        zzaor.zza((View)this.zzbjt, (ViewTreeObserver$OnScrollChangedListener)this);
        this.zzbjt.setOnTouchListener((View$OnTouchListener)this);
        this.zzbjt.setOnClickListener((View$OnClickListener)this);
        if (zzvh != null && PlatformVersion.isAtLeastLollipop()) {
            zzvh.setElevation(Float.MAX_VALUE);
        }
        zznk.initialize(this.zzbjt.getContext());
        this.zzbjv = (boolean)zzkb.zzik().zzd(zznk.zzbcd);
    }
    
    private final void zzkt() {
        synchronized (this.mLock) {
            if (!this.zzbjv && this.zzbjy) {
                final int measuredWidth = this.zzbjt.getMeasuredWidth();
                final int measuredHeight = this.zzbjt.getMeasuredHeight();
                if (measuredWidth != 0 && measuredHeight != 0 && this.zzvh != null) {
                    this.zzvh.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(measuredWidth, measuredHeight));
                    this.zzbjy = false;
                }
            }
        }
    }
    
    private final void zzl(@Nullable final View view) {
        if (this.zzbij != null) {
            zzoz zzoz;
            if (this.zzbij instanceof zzoy) {
                zzoz = ((zzoy)this.zzbij).zzkn();
            }
            else {
                zzoz = this.zzbij;
            }
            if (zzoz != null) {
                zzoz.zzl(view);
            }
        }
    }
    
    @VisibleForTesting
    private final int zzv(final int n) {
        zzkb.zzif();
        return zzamu.zzb(this.zzbij.getContext(), n);
    }
    
    public final void destroy() {
        synchronized (this.mLock) {
            if (this.zzvh != null) {
                this.zzvh.removeAllViews();
            }
            this.zzvh = null;
            this.zzbjw = null;
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
            this.zzbkb = null;
            this.zzbju = null;
        }
    }
    
    public final void onClick(final View view) {
        while (true) {
            final Bundle bundle;
            final View view2;
            Label_0206: {
                synchronized (this.mLock) {
                    if (this.zzbij == null) {
                        return;
                    }
                    this.zzbij.cancelUnconfirmedClick();
                    bundle = new Bundle();
                    bundle.putFloat("x", (float)this.zzv(this.zzbjz.x));
                    bundle.putFloat("y", (float)this.zzv(this.zzbjz.y));
                    bundle.putFloat("start_x", (float)this.zzv(this.zzbka.x));
                    bundle.putFloat("start_y", (float)this.zzv(this.zzbka.y));
                    if (this.zzbjx == null || !this.zzbjx.equals(view)) {
                        break Label_0206;
                    }
                    if (this.zzbij instanceof zzoy) {
                        if (((zzoy)this.zzbij).zzkn() != null) {
                            ((zzoy)this.zzbij).zzkn().zza(view, "1007", bundle, this.zzbjw, (View)this.zzbjt);
                        }
                        return;
                    }
                }
                this.zzbij.zza(view2, "1007", bundle, this.zzbjw, (View)this.zzbjt);
                return;
            }
            this.zzbij.zza(view2, this.zzbjw, bundle, (View)this.zzbjt);
        }
    }
    
    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            this.zzkt();
            if (this.zzbij != null) {
                this.zzbij.zzc((View)this.zzbjt, this.zzbjw);
            }
        }
    }
    
    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                this.zzbij.zzc((View)this.zzbjt, this.zzbjw);
            }
            this.zzkt();
        }
    }
    
    public final boolean onTouch(final View view, MotionEvent obtain) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                return false;
            }
            final int[] array = new int[2];
            this.zzbjt.getLocationOnScreen(array);
            final Point point = new Point((int)(obtain.getRawX() - array[0]), (int)(obtain.getRawY() - array[1]));
            this.zzbjz = point;
            if (obtain.getAction() == 0) {
                this.zzbka = point;
            }
            obtain = MotionEvent.obtain(obtain);
            obtain.setLocation((float)point.x, (float)point.y);
            this.zzbij.zzd(obtain);
            obtain.recycle();
            return false;
        }
    }
    
    public final void zza(final IObjectWrapper p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzpn.mLock:Ljava/lang/Object;
        //     4: astore          6
        //     6: aload           6
        //     8: monitorenter   
        //     9: aload_0        
        //    10: aconst_null    
        //    11: invokespecial   com/google/android/gms/internal/ads/zzpn.zzl:(Landroid/view/View;)V
        //    14: aload_1        
        //    15: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.unwrap:(Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
        //    18: astore_1       
        //    19: aload_1        
        //    20: instanceof      Lcom/google/android/gms/internal/ads/zzpd;
        //    23: ifne            36
        //    26: ldc_w           "Not an instance of native engine. This is most likely a transient error"
        //    29: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //    32: aload           6
        //    34: monitorexit    
        //    35: return         
        //    36: aload_0        
        //    37: getfield        com/google/android/gms/internal/ads/zzpn.zzbjv:Z
        //    40: ifne            73
        //    43: aload_0        
        //    44: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //    47: ifnull          73
        //    50: aload_0        
        //    51: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //    54: new             Landroid/widget/FrameLayout$LayoutParams;
        //    57: dup            
        //    58: iconst_0       
        //    59: iconst_0       
        //    60: invokespecial   android/widget/FrameLayout$LayoutParams.<init>:(II)V
        //    63: invokevirtual   android/widget/FrameLayout.setLayoutParams:(Landroid/view/ViewGroup$LayoutParams;)V
        //    66: aload_0        
        //    67: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //    70: invokevirtual   android/widget/FrameLayout.requestLayout:()V
        //    73: aload_0        
        //    74: iconst_1       
        //    75: putfield        com/google/android/gms/internal/ads/zzpn.zzbjy:Z
        //    78: aload_1        
        //    79: checkcast       Lcom/google/android/gms/internal/ads/zzpd;
        //    82: astore          5
        //    84: aload_0        
        //    85: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //    88: ifnull          128
        //    91: getstatic       com/google/android/gms/internal/ads/zznk.zzbbu:Lcom/google/android/gms/internal/ads/zzna;
        //    94: astore_1       
        //    95: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //    98: aload_1        
        //    99: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   102: checkcast       Ljava/lang/Boolean;
        //   105: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   108: ifeq            128
        //   111: aload_0        
        //   112: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   115: aload_0        
        //   116: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   119: aload_0        
        //   120: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   123: invokeinterface com/google/android/gms/internal/ads/zzoz.zzb:(Landroid/view/View;Ljava/util/Map;)V
        //   128: aload_0        
        //   129: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   132: instanceof      Lcom/google/android/gms/internal/ads/zzpd;
        //   135: ifeq            214
        //   138: aload_0        
        //   139: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   142: checkcast       Lcom/google/android/gms/internal/ads/zzpd;
        //   145: astore_1       
        //   146: aload_1        
        //   147: ifnull          214
        //   150: aload_1        
        //   151: invokevirtual   com/google/android/gms/internal/ads/zzpd.getContext:()Landroid/content/Context;
        //   154: ifnull          214
        //   157: invokestatic    com/google/android/gms/ads/internal/zzbv.zzfh:()Lcom/google/android/gms/internal/ads/zzaiy;
        //   160: aload_0        
        //   161: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   164: invokevirtual   android/widget/FrameLayout.getContext:()Landroid/content/Context;
        //   167: invokevirtual   com/google/android/gms/internal/ads/zzaiy.zzu:(Landroid/content/Context;)Z
        //   170: ifeq            214
        //   173: aload_1        
        //   174: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzks:()Lcom/google/android/gms/internal/ads/zzaix;
        //   177: astore_1       
        //   178: aload_1        
        //   179: ifnull          187
        //   182: aload_1        
        //   183: iconst_0       
        //   184: invokevirtual   com/google/android/gms/internal/ads/zzaix.zzx:(Z)V
        //   187: aload_0        
        //   188: getfield        com/google/android/gms/internal/ads/zzpn.zzbkb:Ljava/lang/ref/WeakReference;
        //   191: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   194: checkcast       Lcom/google/android/gms/internal/ads/zzfp;
        //   197: astore          7
        //   199: aload           7
        //   201: ifnull          214
        //   204: aload_1        
        //   205: ifnull          214
        //   208: aload           7
        //   210: aload_1        
        //   211: invokevirtual   com/google/android/gms/internal/ads/zzfp.zzb:(Lcom/google/android/gms/internal/ads/zzft;)V
        //   214: aload_0        
        //   215: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   218: instanceof      Lcom/google/android/gms/internal/ads/zzoy;
        //   221: ifeq            266
        //   224: aload_0        
        //   225: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   228: checkcast       Lcom/google/android/gms/internal/ads/zzoy;
        //   231: invokevirtual   com/google/android/gms/internal/ads/zzoy.zzkm:()Z
        //   234: ifeq            266
        //   237: aload_0        
        //   238: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   241: checkcast       Lcom/google/android/gms/internal/ads/zzoy;
        //   244: aload           5
        //   246: invokevirtual   com/google/android/gms/internal/ads/zzoy.zzc:(Lcom/google/android/gms/internal/ads/zzoz;)V
        //   249: aload_0        
        //   250: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //   253: ifnonnull       292
        //   256: aload           6
        //   258: monitorexit    
        //   259: return         
        //   260: astore_1       
        //   261: aload           6
        //   263: monitorexit    
        //   264: aload_1        
        //   265: athrow         
        //   266: aload_0        
        //   267: aload           5
        //   269: putfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   272: aload           5
        //   274: instanceof      Lcom/google/android/gms/internal/ads/zzoy;
        //   277: ifeq            249
        //   280: aload           5
        //   282: checkcast       Lcom/google/android/gms/internal/ads/zzoy;
        //   285: aconst_null    
        //   286: invokevirtual   com/google/android/gms/internal/ads/zzoy.zzc:(Lcom/google/android/gms/internal/ads/zzoz;)V
        //   289: goto            249
        //   292: getstatic       com/google/android/gms/internal/ads/zznk.zzbbu:Lcom/google/android/gms/internal/ads/zzna;
        //   295: astore_1       
        //   296: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //   299: aload_1        
        //   300: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   303: checkcast       Ljava/lang/Boolean;
        //   306: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   309: ifeq            320
        //   312: aload_0        
        //   313: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //   316: iconst_0       
        //   317: invokevirtual   android/widget/FrameLayout.setClickable:(Z)V
        //   320: aload_0        
        //   321: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //   324: invokevirtual   android/widget/FrameLayout.removeAllViews:()V
        //   327: aload           5
        //   329: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzkj:()Z
        //   332: istore          4
        //   334: iload           4
        //   336: ifeq            978
        //   339: aload_0        
        //   340: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   343: ifnull          1005
        //   346: iconst_0       
        //   347: istore_2       
        //   348: iload_2        
        //   349: iconst_2       
        //   350: if_icmpge       1005
        //   353: iconst_2       
        //   354: anewarray       Ljava/lang/String;
        //   357: dup            
        //   358: iconst_0       
        //   359: ldc_w           "1098"
        //   362: aastore        
        //   363: dup            
        //   364: iconst_1       
        //   365: ldc_w           "3011"
        //   368: aastore        
        //   369: iload_2        
        //   370: aaload         
        //   371: astore_1       
        //   372: aload_0        
        //   373: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   376: aload_1        
        //   377: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   382: checkcast       Ljava/lang/ref/WeakReference;
        //   385: astore_1       
        //   386: aload_1        
        //   387: ifnull          998
        //   390: aload_1        
        //   391: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   394: checkcast       Landroid/view/View;
        //   397: astore_1       
        //   398: aload_1        
        //   399: instanceof      Landroid/view/ViewGroup;
        //   402: ifeq            978
        //   405: aload_1        
        //   406: checkcast       Landroid/view/ViewGroup;
        //   409: astore_1       
        //   410: goto            983
        //   413: aload_0        
        //   414: aload           5
        //   416: aload_0        
        //   417: iload           4
        //   419: invokevirtual   com/google/android/gms/internal/ads/zzpd.zza:(Landroid/view/View$OnClickListener;Z)Landroid/view/View;
        //   422: putfield        com/google/android/gms/internal/ads/zzpn.zzbjx:Landroid/view/View;
        //   425: aload_0        
        //   426: getfield        com/google/android/gms/internal/ads/zzpn.zzbjx:Landroid/view/View;
        //   429: ifnull          479
        //   432: aload_0        
        //   433: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   436: ifnull          462
        //   439: aload_0        
        //   440: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   443: ldc             "1007"
        //   445: new             Ljava/lang/ref/WeakReference;
        //   448: dup            
        //   449: aload_0        
        //   450: getfield        com/google/android/gms/internal/ads/zzpn.zzbjx:Landroid/view/View;
        //   453: invokespecial   java/lang/ref/WeakReference.<init>:(Ljava/lang/Object;)V
        //   456: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   461: pop            
        //   462: iload           4
        //   464: ifeq            829
        //   467: aload_1        
        //   468: invokevirtual   android/view/ViewGroup.removeAllViews:()V
        //   471: aload_1        
        //   472: aload_0        
        //   473: getfield        com/google/android/gms/internal/ads/zzpn.zzbjx:Landroid/view/View;
        //   476: invokevirtual   android/view/ViewGroup.addView:(Landroid/view/View;)V
        //   479: aload           5
        //   481: aload_0        
        //   482: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   485: aload_0        
        //   486: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   489: aconst_null    
        //   490: aload_0        
        //   491: aload_0        
        //   492: invokevirtual   com/google/android/gms/internal/ads/zzpd.zza:(Landroid/view/View;Ljava/util/Map;Ljava/util/Map;Landroid/view/View$OnTouchListener;Landroid/view/View$OnClickListener;)V
        //   495: aload_0        
        //   496: getfield        com/google/android/gms/internal/ads/zzpn.zzbjv:Z
        //   499: ifeq            568
        //   502: aload_0        
        //   503: getfield        com/google/android/gms/internal/ads/zzpn.zzbju:Landroid/view/View;
        //   506: ifnonnull       543
        //   509: aload_0        
        //   510: new             Landroid/view/View;
        //   513: dup            
        //   514: aload_0        
        //   515: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   518: invokevirtual   android/widget/FrameLayout.getContext:()Landroid/content/Context;
        //   521: invokespecial   android/view/View.<init>:(Landroid/content/Context;)V
        //   524: putfield        com/google/android/gms/internal/ads/zzpn.zzbju:Landroid/view/View;
        //   527: aload_0        
        //   528: getfield        com/google/android/gms/internal/ads/zzpn.zzbju:Landroid/view/View;
        //   531: new             Landroid/widget/FrameLayout$LayoutParams;
        //   534: dup            
        //   535: iconst_m1      
        //   536: iconst_0       
        //   537: invokespecial   android/widget/FrameLayout$LayoutParams.<init>:(II)V
        //   540: invokevirtual   android/view/View.setLayoutParams:(Landroid/view/ViewGroup$LayoutParams;)V
        //   543: aload_0        
        //   544: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   547: aload_0        
        //   548: getfield        com/google/android/gms/internal/ads/zzpn.zzbju:Landroid/view/View;
        //   551: invokevirtual   android/view/View.getParent:()Landroid/view/ViewParent;
        //   554: if_acmpeq       568
        //   557: aload_0        
        //   558: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   561: aload_0        
        //   562: getfield        com/google/android/gms/internal/ads/zzpn.zzbju:Landroid/view/View;
        //   565: invokevirtual   android/widget/FrameLayout.addView:(Landroid/view/View;)V
        //   568: aload           5
        //   570: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzko:()Lcom/google/android/gms/internal/ads/zzaqw;
        //   573: astore_1       
        //   574: aload_1        
        //   575: ifnull          598
        //   578: aload_0        
        //   579: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //   582: ifnull          598
        //   585: aload_0        
        //   586: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //   589: aload_1        
        //   590: invokeinterface com/google/android/gms/internal/ads/zzaqw.getView:()Landroid/view/View;
        //   595: invokevirtual   android/widget/FrameLayout.addView:(Landroid/view/View;)V
        //   598: aload_0        
        //   599: getfield        com/google/android/gms/internal/ads/zzpn.mLock:Ljava/lang/Object;
        //   602: astore          7
        //   604: aload           7
        //   606: monitorenter   
        //   607: aload           5
        //   609: aload_0        
        //   610: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   613: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzf:(Ljava/util/Map;)V
        //   616: aload_0        
        //   617: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   620: ifnull          922
        //   623: getstatic       com/google/android/gms/internal/ads/zzpn.zzbjs:[Ljava/lang/String;
        //   626: astore_1       
        //   627: aload_1        
        //   628: arraylength    
        //   629: istore_3       
        //   630: iconst_0       
        //   631: istore_2       
        //   632: iload_2        
        //   633: iload_3        
        //   634: if_icmpge       922
        //   637: aload_1        
        //   638: iload_2        
        //   639: aaload         
        //   640: astore          8
        //   642: aload_0        
        //   643: getfield        com/google/android/gms/internal/ads/zzpn.zzbjw:Ljava/util/Map;
        //   646: aload           8
        //   648: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   653: checkcast       Ljava/lang/ref/WeakReference;
        //   656: astore          8
        //   658: aload           8
        //   660: ifnull          915
        //   663: aload           8
        //   665: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   668: checkcast       Landroid/view/View;
        //   671: astore_1       
        //   672: aload_1        
        //   673: instanceof      Landroid/widget/FrameLayout;
        //   676: ifne            927
        //   679: aload           5
        //   681: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzkq:()V
        //   684: aload           7
        //   686: monitorexit    
        //   687: aload           5
        //   689: aload_0        
        //   690: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   693: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzi:(Landroid/view/View;)V
        //   696: aload_0        
        //   697: aload_0        
        //   698: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   701: invokespecial   com/google/android/gms/internal/ads/zzpn.zzl:(Landroid/view/View;)V
        //   704: aload_0        
        //   705: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   708: aload_0        
        //   709: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   712: invokeinterface com/google/android/gms/internal/ads/zzoz.zzj:(Landroid/view/View;)V
        //   717: aload_0        
        //   718: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   721: instanceof      Lcom/google/android/gms/internal/ads/zzpd;
        //   724: ifeq            825
        //   727: aload_0        
        //   728: getfield        com/google/android/gms/internal/ads/zzpn.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   731: checkcast       Lcom/google/android/gms/internal/ads/zzpd;
        //   734: astore          7
        //   736: aload           7
        //   738: ifnull          825
        //   741: aload           7
        //   743: invokevirtual   com/google/android/gms/internal/ads/zzpd.getContext:()Landroid/content/Context;
        //   746: ifnull          825
        //   749: invokestatic    com/google/android/gms/ads/internal/zzbv.zzfh:()Lcom/google/android/gms/internal/ads/zzaiy;
        //   752: aload_0        
        //   753: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   756: invokevirtual   android/widget/FrameLayout.getContext:()Landroid/content/Context;
        //   759: invokevirtual   com/google/android/gms/internal/ads/zzaiy.zzu:(Landroid/content/Context;)Z
        //   762: ifeq            825
        //   765: aload_0        
        //   766: getfield        com/google/android/gms/internal/ads/zzpn.zzbkb:Ljava/lang/ref/WeakReference;
        //   769: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   772: checkcast       Lcom/google/android/gms/internal/ads/zzfp;
        //   775: astore          5
        //   777: aload           5
        //   779: astore_1       
        //   780: aload           5
        //   782: ifnonnull       816
        //   785: new             Lcom/google/android/gms/internal/ads/zzfp;
        //   788: dup            
        //   789: aload_0        
        //   790: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   793: invokevirtual   android/widget/FrameLayout.getContext:()Landroid/content/Context;
        //   796: aload_0        
        //   797: getfield        com/google/android/gms/internal/ads/zzpn.zzbjt:Landroid/widget/FrameLayout;
        //   800: invokespecial   com/google/android/gms/internal/ads/zzfp.<init>:(Landroid/content/Context;Landroid/view/View;)V
        //   803: astore_1       
        //   804: aload_0        
        //   805: new             Ljava/lang/ref/WeakReference;
        //   808: dup            
        //   809: aload_1        
        //   810: invokespecial   java/lang/ref/WeakReference.<init>:(Ljava/lang/Object;)V
        //   813: putfield        com/google/android/gms/internal/ads/zzpn.zzbkb:Ljava/lang/ref/WeakReference;
        //   816: aload_1        
        //   817: aload           7
        //   819: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzks:()Lcom/google/android/gms/internal/ads/zzaix;
        //   822: invokevirtual   com/google/android/gms/internal/ads/zzfp.zza:(Lcom/google/android/gms/internal/ads/zzft;)V
        //   825: aload           6
        //   827: monitorexit    
        //   828: return         
        //   829: new             Lcom/google/android/gms/ads/formats/AdChoicesView;
        //   832: dup            
        //   833: aload           5
        //   835: invokevirtual   com/google/android/gms/internal/ads/zzpd.getContext:()Landroid/content/Context;
        //   838: invokespecial   com/google/android/gms/ads/formats/AdChoicesView.<init>:(Landroid/content/Context;)V
        //   841: astore_1       
        //   842: aload_1        
        //   843: new             Landroid/widget/FrameLayout$LayoutParams;
        //   846: dup            
        //   847: iconst_m1      
        //   848: iconst_m1      
        //   849: invokespecial   android/widget/FrameLayout$LayoutParams.<init>:(II)V
        //   852: invokevirtual   android/view/ViewGroup.setLayoutParams:(Landroid/view/ViewGroup$LayoutParams;)V
        //   855: aload_1        
        //   856: aload_0        
        //   857: getfield        com/google/android/gms/internal/ads/zzpn.zzbjx:Landroid/view/View;
        //   860: invokevirtual   android/view/ViewGroup.addView:(Landroid/view/View;)V
        //   863: aload_0        
        //   864: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //   867: ifnull          479
        //   870: aload_0        
        //   871: getfield        com/google/android/gms/internal/ads/zzpn.zzvh:Landroid/widget/FrameLayout;
        //   874: aload_1        
        //   875: invokevirtual   android/widget/FrameLayout.addView:(Landroid/view/View;)V
        //   878: goto            479
        //   881: astore_1       
        //   882: invokestatic    com/google/android/gms/ads/internal/zzbv.zzem:()Lcom/google/android/gms/internal/ads/zzakq;
        //   885: pop            
        //   886: invokestatic    com/google/android/gms/internal/ads/zzakq.zzrp:()Z
        //   889: ifeq            903
        //   892: ldc_w           "Privileged processes cannot create HTML overlays."
        //   895: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   898: aconst_null    
        //   899: astore_1       
        //   900: goto            574
        //   903: ldc_w           "Error obtaining overlay."
        //   906: aload_1        
        //   907: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   910: aconst_null    
        //   911: astore_1       
        //   912: goto            574
        //   915: iload_2        
        //   916: iconst_1       
        //   917: iadd           
        //   918: istore_2       
        //   919: goto            632
        //   922: aconst_null    
        //   923: astore_1       
        //   924: goto            672
        //   927: new             Lcom/google/android/gms/internal/ads/zzpo;
        //   930: dup            
        //   931: aload_0        
        //   932: aload_1        
        //   933: invokespecial   com/google/android/gms/internal/ads/zzpo.<init>:(Lcom/google/android/gms/internal/ads/zzpn;Landroid/view/View;)V
        //   936: astore          8
        //   938: aload           5
        //   940: instanceof      Lcom/google/android/gms/internal/ads/zzoy;
        //   943: ifeq            967
        //   946: aload           5
        //   948: aload_1        
        //   949: aload           8
        //   951: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzb:(Landroid/view/View;Lcom/google/android/gms/internal/ads/zzox;)Z
        //   954: pop            
        //   955: aload           7
        //   957: monitorexit    
        //   958: goto            687
        //   961: astore_1       
        //   962: aload           7
        //   964: monitorexit    
        //   965: aload_1        
        //   966: athrow         
        //   967: aload           5
        //   969: aload_1        
        //   970: aload           8
        //   972: invokevirtual   com/google/android/gms/internal/ads/zzpd.zza:(Landroid/view/View;Lcom/google/android/gms/internal/ads/zzox;)V
        //   975: goto            955
        //   978: aconst_null    
        //   979: astore_1       
        //   980: goto            983
        //   983: iload           4
        //   985: ifeq            1010
        //   988: aload_1        
        //   989: ifnull          1010
        //   992: iconst_1       
        //   993: istore          4
        //   995: goto            413
        //   998: iload_2        
        //   999: iconst_1       
        //  1000: iadd           
        //  1001: istore_2       
        //  1002: goto            348
        //  1005: aconst_null    
        //  1006: astore_1       
        //  1007: goto            398
        //  1010: iconst_0       
        //  1011: istore          4
        //  1013: goto            413
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      35     260    266    Any
        //  36     73     260    266    Any
        //  73     128    260    266    Any
        //  128    146    260    266    Any
        //  150    178    260    266    Any
        //  182    187    260    266    Any
        //  187    199    260    266    Any
        //  208    214    260    266    Any
        //  214    249    260    266    Any
        //  249    259    260    266    Any
        //  261    264    260    266    Any
        //  266    289    260    266    Any
        //  292    320    260    266    Any
        //  320    334    260    266    Any
        //  339    346    260    266    Any
        //  353    386    260    266    Any
        //  390    398    260    266    Any
        //  398    410    260    266    Any
        //  413    462    260    266    Any
        //  467    479    260    266    Any
        //  479    543    260    266    Any
        //  543    568    260    266    Any
        //  568    574    881    915    Ljava/lang/Exception;
        //  568    574    260    266    Any
        //  578    598    260    266    Any
        //  598    607    260    266    Any
        //  607    630    961    967    Any
        //  642    658    961    967    Any
        //  663    672    961    967    Any
        //  672    687    961    967    Any
        //  687    736    260    266    Any
        //  741    777    260    266    Any
        //  785    816    260    266    Any
        //  816    825    260    266    Any
        //  825    828    260    266    Any
        //  829    878    260    266    Any
        //  882    898    260    266    Any
        //  903    910    260    266    Any
        //  927    955    961    967    Any
        //  955    958    961    967    Any
        //  962    965    961    967    Any
        //  965    967    260    266    Any
        //  967    975    961    967    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0632:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public final IObjectWrapper zzak(final String s) {
        while (true) {
            final Object o = null;
            while (true) {
                synchronized (this.mLock) {
                    if (this.zzbjw == null) {
                        return null;
                    }
                    if (this.zzbjw.get(s) == null) {
                        final Object o2 = o;
                        return ObjectWrapper.wrap(o2);
                    }
                }
                final Reference<View> reference;
                final Object o2 = reference.get();
                continue;
            }
        }
    }
    
    public final void zzb(final IObjectWrapper objectWrapper, final int n) {
        if (zzbv.zzfh().zzu(this.zzbjt.getContext()) && this.zzbkb != null) {
            final zzfp zzfp = this.zzbkb.get();
            if (zzfp != null) {
                zzfp.zzgm();
            }
        }
        this.zzkt();
    }
    
    public final void zzb(final String s, final IObjectWrapper objectWrapper) {
        while (true) {
            final View view = (View)ObjectWrapper.unwrap(objectWrapper);
            synchronized (this.mLock) {
                if (this.zzbjw == null) {
                    return;
                }
                if (view == null) {
                    this.zzbjw.remove(s);
                    return;
                }
            }
            final String s2;
            this.zzbjw.put(s2, new WeakReference<View>(view));
            if ("1098".equals(s2) || "3011".equals(s2)) {
                break;
            }
            view.setOnTouchListener((View$OnTouchListener)this);
            view.setClickable(true);
            view.setOnClickListener((View$OnClickListener)this);
            return;
        }
    }
    // monitorexit(objectWrapper)
    
    public final void zzc(final IObjectWrapper objectWrapper) {
        this.zzbij.setClickConfirmingView((View)ObjectWrapper.unwrap(objectWrapper));
    }
}
