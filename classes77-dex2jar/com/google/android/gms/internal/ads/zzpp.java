// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.view.MotionEvent;
import android.os.Bundle;
import java.util.Iterator;
import com.google.android.gms.ads.internal.zzbv;
import java.util.HashMap;
import java.util.Map;
import java.lang.ref.WeakReference;
import android.graphics.Point;
import android.view.View;
import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.View$OnTouchListener;
import android.view.View$OnClickListener;

@zzadh
@ParametersAreNonnullByDefault
public final class zzpp extends zzqg implements View$OnClickListener, View$OnTouchListener, ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    @VisibleForTesting
    static final String[] zzbjs;
    private final Object mLock;
    @Nullable
    @VisibleForTesting
    @GuardedBy("mLock")
    private zzoz zzbij;
    @Nullable
    @VisibleForTesting
    private View zzbjx;
    @VisibleForTesting
    private Point zzbjz;
    @VisibleForTesting
    private Point zzbka;
    @Nullable
    @VisibleForTesting
    private WeakReference<zzfp> zzbkb;
    private final WeakReference<View> zzbke;
    private final Map<String, WeakReference<View>> zzbkf;
    private final Map<String, WeakReference<View>> zzbkg;
    private final Map<String, WeakReference<View>> zzbkh;
    
    static {
        zzbjs = new String[] { "2011", "1009", "3010" };
    }
    
    public zzpp(final View view, final HashMap<String, View> hashMap, final HashMap<String, View> hashMap2) {
        this.mLock = new Object();
        this.zzbkf = new HashMap<String, WeakReference<View>>();
        this.zzbkg = new HashMap<String, WeakReference<View>>();
        this.zzbkh = new HashMap<String, WeakReference<View>>();
        this.zzbjz = new Point();
        this.zzbka = new Point();
        this.zzbkb = new WeakReference<zzfp>(null);
        zzbv.zzfg();
        zzaor.zza(view, (ViewTreeObserver$OnGlobalLayoutListener)this);
        zzbv.zzfg();
        zzaor.zza(view, (ViewTreeObserver$OnScrollChangedListener)this);
        view.setOnTouchListener((View$OnTouchListener)this);
        view.setOnClickListener((View$OnClickListener)this);
        this.zzbke = new WeakReference<View>(view);
        for (final Map.Entry<Object, Object> entry : hashMap.entrySet()) {
            final String s = entry.getKey();
            final View view2 = entry.getValue();
            if (view2 != null) {
                this.zzbkf.put(s, new WeakReference<View>(view2));
                if ("1098".equals(s) || "3011".equals(s)) {
                    continue;
                }
                view2.setOnTouchListener((View$OnTouchListener)this);
                view2.setClickable(true);
                view2.setOnClickListener((View$OnClickListener)this);
            }
        }
        this.zzbkh.putAll(this.zzbkf);
        for (final Map.Entry<Object, Object> entry2 : hashMap2.entrySet()) {
            final View view3 = entry2.getValue();
            if (view3 != null) {
                this.zzbkg.put(entry2.getKey(), new WeakReference<View>(view3));
                view3.setOnTouchListener((View$OnTouchListener)this);
            }
        }
        this.zzbkh.putAll(this.zzbkg);
        zznk.initialize(view.getContext());
    }
    
    private final void zza(final zzpd p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzpp.mLock:Ljava/lang/Object;
        //     4: astore          5
        //     6: aload           5
        //     8: monitorenter   
        //     9: getstatic       com/google/android/gms/internal/ads/zzpp.zzbjs:[Ljava/lang/String;
        //    12: astore          4
        //    14: aload           4
        //    16: arraylength    
        //    17: istore_3       
        //    18: iconst_0       
        //    19: istore_2       
        //    20: iload_2        
        //    21: iload_3        
        //    22: if_icmpge       134
        //    25: aload           4
        //    27: iload_2        
        //    28: aaload         
        //    29: astore          6
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/internal/ads/zzpp.zzbkh:Ljava/util/Map;
        //    35: aload           6
        //    37: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    42: checkcast       Ljava/lang/ref/WeakReference;
        //    45: astore          6
        //    47: aload           6
        //    49: ifnull          127
        //    52: aload           6
        //    54: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //    57: checkcast       Landroid/view/View;
        //    60: astore          4
        //    62: aload           4
        //    64: instanceof      Landroid/widget/FrameLayout;
        //    67: ifne            78
        //    70: aload_1        
        //    71: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzkq:()V
        //    74: aload           5
        //    76: monitorexit    
        //    77: return         
        //    78: new             Lcom/google/android/gms/internal/ads/zzpr;
        //    81: dup            
        //    82: aload_0        
        //    83: aload           4
        //    85: invokespecial   com/google/android/gms/internal/ads/zzpr.<init>:(Lcom/google/android/gms/internal/ads/zzpp;Landroid/view/View;)V
        //    88: astore          6
        //    90: aload_1        
        //    91: instanceof      Lcom/google/android/gms/internal/ads/zzoy;
        //    94: ifeq            116
        //    97: aload_1        
        //    98: aload           4
        //   100: aload           6
        //   102: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzb:(Landroid/view/View;Lcom/google/android/gms/internal/ads/zzox;)Z
        //   105: pop            
        //   106: aload           5
        //   108: monitorexit    
        //   109: return         
        //   110: astore_1       
        //   111: aload           5
        //   113: monitorexit    
        //   114: aload_1        
        //   115: athrow         
        //   116: aload_1        
        //   117: aload           4
        //   119: aload           6
        //   121: invokevirtual   com/google/android/gms/internal/ads/zzpd.zza:(Landroid/view/View;Lcom/google/android/gms/internal/ads/zzox;)V
        //   124: goto            106
        //   127: iload_2        
        //   128: iconst_1       
        //   129: iadd           
        //   130: istore_2       
        //   131: goto            20
        //   134: aconst_null    
        //   135: astore          4
        //   137: goto            62
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      18     110    116    Any
        //  31     47     110    116    Any
        //  52     62     110    116    Any
        //  62     77     110    116    Any
        //  78     106    110    116    Any
        //  106    109    110    116    Any
        //  111    114    110    116    Any
        //  116    124    110    116    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    private final boolean zza(final String[] array) {
        final boolean b = false;
        for (int length = array.length, i = 0; i < length; ++i) {
            if (this.zzbkf.get(array[i]) != null) {
                return true;
            }
        }
        final int length2 = array.length;
        int n = 0;
        while (true) {
            boolean b2 = b;
            if (n >= length2) {
                return b2;
            }
            final String s = array[n];
            b2 = b;
            if (this.zzbkg.get(s) != null) {
                return b2;
            }
            ++n;
        }
    }
    
    private final void zzl(@Nullable final View view) {
        synchronized (this.mLock) {
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
    }
    
    @VisibleForTesting
    private final int zzv(int zzb) {
        synchronized (this.mLock) {
            zzkb.zzif();
            zzb = zzamu.zzb(this.zzbij.getContext(), zzb);
            return zzb;
        }
    }
    
    public final void onClick(final View view) {
        final View view2;
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                return;
            }
            view2 = this.zzbke.get();
            if (view2 == null) {
                return;
            }
        }
        final Bundle bundle = new Bundle();
        bundle.putFloat("x", (float)this.zzv(this.zzbjz.x));
        bundle.putFloat("y", (float)this.zzv(this.zzbjz.y));
        bundle.putFloat("start_x", (float)this.zzv(this.zzbka.x));
        bundle.putFloat("start_y", (float)this.zzv(this.zzbka.y));
        final View view3;
        if (this.zzbjx != null && this.zzbjx.equals(view3)) {
            if (this.zzbij instanceof zzoy) {
                if (((zzoy)this.zzbij).zzkn() != null) {
                    ((zzoy)this.zzbij).zzkn().zza(view3, "1007", bundle, this.zzbkh, view2);
                }
            }
            else {
                this.zzbij.zza(view3, "1007", bundle, this.zzbkh, view2);
            }
        }
        else {
            this.zzbij.zza(view3, this.zzbkh, bundle, view2);
        }
    }
    // monitorexit(o)
    
    public final void onGlobalLayout() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                final View view = this.zzbke.get();
                if (view != null) {
                    this.zzbij.zzc(view, this.zzbkh);
                }
            }
        }
    }
    
    public final void onScrollChanged() {
        synchronized (this.mLock) {
            if (this.zzbij != null) {
                final View view = this.zzbke.get();
                if (view != null) {
                    this.zzbij.zzc(view, this.zzbkh);
                }
            }
        }
    }
    
    public final boolean onTouch(final View view, MotionEvent obtain) {
        final View view2;
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                return false;
            }
            view2 = this.zzbke.get();
            if (view2 == null) {
                return false;
            }
        }
        final int[] array = new int[2];
        view2.getLocationOnScreen(array);
        final MotionEvent motionEvent;
        final Point point = new Point((int)(motionEvent.getRawX() - array[0]), (int)(motionEvent.getRawY() - array[1]));
        this.zzbjz = point;
        if (motionEvent.getAction() == 0) {
            this.zzbka = point;
        }
        obtain = MotionEvent.obtain(motionEvent);
        obtain.setLocation((float)point.x, (float)point.y);
        this.zzbij.zzd(obtain);
        obtain.recycle();
        // monitorexit(view)
        return false;
    }
    
    public final void unregisterNativeAd() {
        synchronized (this.mLock) {
            this.zzbjx = null;
            this.zzbij = null;
            this.zzbjz = null;
            this.zzbka = null;
        }
    }
    
    public final void zza(final IObjectWrapper p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzpp.mLock:Ljava/lang/Object;
        //     4: astore          4
        //     6: aload           4
        //     8: monitorenter   
        //     9: aload_0        
        //    10: aconst_null    
        //    11: invokespecial   com/google/android/gms/internal/ads/zzpp.zzl:(Landroid/view/View;)V
        //    14: aload_1        
        //    15: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.unwrap:(Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
        //    18: astore_1       
        //    19: aload_1        
        //    20: instanceof      Lcom/google/android/gms/internal/ads/zzpd;
        //    23: ifne            36
        //    26: ldc_w           "Not an instance of native engine. This is most likely a transient error"
        //    29: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //    32: aload           4
        //    34: monitorexit    
        //    35: return         
        //    36: aload_1        
        //    37: checkcast       Lcom/google/android/gms/internal/ads/zzpd;
        //    40: astore_3       
        //    41: aload_3        
        //    42: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzkk:()Z
        //    45: ifne            64
        //    48: ldc_w           "Your account must be enabled to use this feature. Talk to your account manager to request this feature for your account."
        //    51: invokestatic    com/google/android/gms/internal/ads/zzakb.e:(Ljava/lang/String;)V
        //    54: aload           4
        //    56: monitorexit    
        //    57: return         
        //    58: astore_1       
        //    59: aload           4
        //    61: monitorexit    
        //    62: aload_1        
        //    63: athrow         
        //    64: aload_0        
        //    65: getfield        com/google/android/gms/internal/ads/zzpp.zzbke:Ljava/lang/ref/WeakReference;
        //    68: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //    71: checkcast       Landroid/view/View;
        //    74: astore          5
        //    76: aload_0        
        //    77: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //    80: ifnull          123
        //    83: aload           5
        //    85: ifnull          123
        //    88: getstatic       com/google/android/gms/internal/ads/zznk.zzbbu:Lcom/google/android/gms/internal/ads/zzna;
        //    91: astore_1       
        //    92: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //    95: aload_1        
        //    96: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //    99: checkcast       Ljava/lang/Boolean;
        //   102: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   105: ifeq            123
        //   108: aload_0        
        //   109: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   112: aload           5
        //   114: aload_0        
        //   115: getfield        com/google/android/gms/internal/ads/zzpp.zzbkh:Ljava/util/Map;
        //   118: invokeinterface com/google/android/gms/internal/ads/zzoz.zzb:(Landroid/view/View;Ljava/util/Map;)V
        //   123: aload_0        
        //   124: getfield        com/google/android/gms/internal/ads/zzpp.mLock:Ljava/lang/Object;
        //   127: astore_1       
        //   128: aload_1        
        //   129: monitorenter   
        //   130: aload_0        
        //   131: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   134: instanceof      Lcom/google/android/gms/internal/ads/zzpd;
        //   137: ifne            311
        //   140: aload_1        
        //   141: monitorexit    
        //   142: aload_0        
        //   143: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   146: instanceof      Lcom/google/android/gms/internal/ads/zzoy;
        //   149: ifeq            421
        //   152: aload_0        
        //   153: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   156: checkcast       Lcom/google/android/gms/internal/ads/zzoy;
        //   159: invokevirtual   com/google/android/gms/internal/ads/zzoy.zzkm:()Z
        //   162: ifeq            421
        //   165: aload_0        
        //   166: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   169: checkcast       Lcom/google/android/gms/internal/ads/zzoy;
        //   172: aload_3        
        //   173: invokevirtual   com/google/android/gms/internal/ads/zzoy.zzc:(Lcom/google/android/gms/internal/ads/zzoz;)V
        //   176: iconst_0       
        //   177: istore_2       
        //   178: iload_2        
        //   179: iconst_2       
        //   180: if_icmpge       670
        //   183: iconst_2       
        //   184: anewarray       Ljava/lang/String;
        //   187: dup            
        //   188: iconst_0       
        //   189: ldc             "1098"
        //   191: aastore        
        //   192: dup            
        //   193: iconst_1       
        //   194: ldc             "3011"
        //   196: aastore        
        //   197: iload_2        
        //   198: aaload         
        //   199: astore_1       
        //   200: aload_0        
        //   201: getfield        com/google/android/gms/internal/ads/zzpp.zzbkh:Ljava/util/Map;
        //   204: aload_1        
        //   205: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   210: checkcast       Ljava/lang/ref/WeakReference;
        //   213: astore_1       
        //   214: aload_1        
        //   215: ifnull          663
        //   218: aload_1        
        //   219: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   222: checkcast       Landroid/view/View;
        //   225: astore_1       
        //   226: aload_1        
        //   227: ifnonnull       444
        //   230: ldc_w           "Ad choices asset view is not provided."
        //   233: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   236: aload_3        
        //   237: aload           5
        //   239: aload_0        
        //   240: getfield        com/google/android/gms/internal/ads/zzpp.zzbkf:Ljava/util/Map;
        //   243: aload_0        
        //   244: getfield        com/google/android/gms/internal/ads/zzpp.zzbkg:Ljava/util/Map;
        //   247: aload_0        
        //   248: aload_0        
        //   249: invokevirtual   com/google/android/gms/internal/ads/zzpd.zza:(Landroid/view/View;Ljava/util/Map;Ljava/util/Map;Landroid/view/View$OnTouchListener;Landroid/view/View$OnClickListener;)V
        //   252: getstatic       com/google/android/gms/internal/ads/zzakk.zzcrm:Landroid/os/Handler;
        //   255: new             Lcom/google/android/gms/internal/ads/zzpq;
        //   258: dup            
        //   259: aload_0        
        //   260: aload_3        
        //   261: invokespecial   com/google/android/gms/internal/ads/zzpq.<init>:(Lcom/google/android/gms/internal/ads/zzpp;Lcom/google/android/gms/internal/ads/zzpd;)V
        //   264: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   267: pop            
        //   268: aload_0        
        //   269: aload           5
        //   271: invokespecial   com/google/android/gms/internal/ads/zzpp.zzl:(Landroid/view/View;)V
        //   274: aload_0        
        //   275: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   278: aload           5
        //   280: invokeinterface com/google/android/gms/internal/ads/zzoz.zzj:(Landroid/view/View;)V
        //   285: aload_0        
        //   286: getfield        com/google/android/gms/internal/ads/zzpp.mLock:Ljava/lang/Object;
        //   289: astore          5
        //   291: aload           5
        //   293: monitorenter   
        //   294: aload_0        
        //   295: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   298: instanceof      Lcom/google/android/gms/internal/ads/zzpd;
        //   301: ifne            540
        //   304: aload           5
        //   306: monitorexit    
        //   307: aload           4
        //   309: monitorexit    
        //   310: return         
        //   311: aload_0        
        //   312: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   315: checkcast       Lcom/google/android/gms/internal/ads/zzpd;
        //   318: astore          6
        //   320: aload_0        
        //   321: getfield        com/google/android/gms/internal/ads/zzpp.zzbke:Ljava/lang/ref/WeakReference;
        //   324: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   327: checkcast       Landroid/view/View;
        //   330: astore          7
        //   332: aload           6
        //   334: ifnull          411
        //   337: aload           6
        //   339: invokevirtual   com/google/android/gms/internal/ads/zzpd.getContext:()Landroid/content/Context;
        //   342: ifnull          411
        //   345: aload           7
        //   347: ifnull          411
        //   350: invokestatic    com/google/android/gms/ads/internal/zzbv.zzfh:()Lcom/google/android/gms/internal/ads/zzaiy;
        //   353: aload           7
        //   355: invokevirtual   android/view/View.getContext:()Landroid/content/Context;
        //   358: invokevirtual   com/google/android/gms/internal/ads/zzaiy.zzu:(Landroid/content/Context;)Z
        //   361: ifeq            411
        //   364: aload           6
        //   366: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzks:()Lcom/google/android/gms/internal/ads/zzaix;
        //   369: astore          6
        //   371: aload           6
        //   373: ifnull          382
        //   376: aload           6
        //   378: iconst_0       
        //   379: invokevirtual   com/google/android/gms/internal/ads/zzaix.zzx:(Z)V
        //   382: aload_0        
        //   383: getfield        com/google/android/gms/internal/ads/zzpp.zzbkb:Ljava/lang/ref/WeakReference;
        //   386: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   389: checkcast       Lcom/google/android/gms/internal/ads/zzfp;
        //   392: astore          7
        //   394: aload           7
        //   396: ifnull          411
        //   399: aload           6
        //   401: ifnull          411
        //   404: aload           7
        //   406: aload           6
        //   408: invokevirtual   com/google/android/gms/internal/ads/zzfp.zzb:(Lcom/google/android/gms/internal/ads/zzft;)V
        //   411: aload_1        
        //   412: monitorexit    
        //   413: goto            142
        //   416: astore_3       
        //   417: aload_1        
        //   418: monitorexit    
        //   419: aload_3        
        //   420: athrow         
        //   421: aload_0        
        //   422: aload_3        
        //   423: putfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   426: aload_3        
        //   427: instanceof      Lcom/google/android/gms/internal/ads/zzoy;
        //   430: ifeq            176
        //   433: aload_3        
        //   434: checkcast       Lcom/google/android/gms/internal/ads/zzoy;
        //   437: aconst_null    
        //   438: invokevirtual   com/google/android/gms/internal/ads/zzoy.zzc:(Lcom/google/android/gms/internal/ads/zzoz;)V
        //   441: goto            176
        //   444: aload_1        
        //   445: instanceof      Landroid/view/ViewGroup;
        //   448: ifeq            658
        //   451: aload_1        
        //   452: checkcast       Landroid/view/ViewGroup;
        //   455: astore_1       
        //   456: aload_1        
        //   457: ifnull          236
        //   460: aload_0        
        //   461: aload_3        
        //   462: aload_0        
        //   463: iconst_1       
        //   464: invokevirtual   com/google/android/gms/internal/ads/zzpd.zza:(Landroid/view/View$OnClickListener;Z)Landroid/view/View;
        //   467: putfield        com/google/android/gms/internal/ads/zzpp.zzbjx:Landroid/view/View;
        //   470: aload_0        
        //   471: getfield        com/google/android/gms/internal/ads/zzpp.zzbjx:Landroid/view/View;
        //   474: ifnull          236
        //   477: aload_0        
        //   478: getfield        com/google/android/gms/internal/ads/zzpp.zzbkh:Ljava/util/Map;
        //   481: ldc_w           "1007"
        //   484: new             Ljava/lang/ref/WeakReference;
        //   487: dup            
        //   488: aload_0        
        //   489: getfield        com/google/android/gms/internal/ads/zzpp.zzbjx:Landroid/view/View;
        //   492: invokespecial   java/lang/ref/WeakReference.<init>:(Ljava/lang/Object;)V
        //   495: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   500: pop            
        //   501: aload_0        
        //   502: getfield        com/google/android/gms/internal/ads/zzpp.zzbkf:Ljava/util/Map;
        //   505: ldc_w           "1007"
        //   508: new             Ljava/lang/ref/WeakReference;
        //   511: dup            
        //   512: aload_0        
        //   513: getfield        com/google/android/gms/internal/ads/zzpp.zzbjx:Landroid/view/View;
        //   516: invokespecial   java/lang/ref/WeakReference.<init>:(Ljava/lang/Object;)V
        //   519: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   524: pop            
        //   525: aload_1        
        //   526: invokevirtual   android/view/ViewGroup.removeAllViews:()V
        //   529: aload_1        
        //   530: aload_0        
        //   531: getfield        com/google/android/gms/internal/ads/zzpp.zzbjx:Landroid/view/View;
        //   534: invokevirtual   android/view/ViewGroup.addView:(Landroid/view/View;)V
        //   537: goto            236
        //   540: aload_0        
        //   541: getfield        com/google/android/gms/internal/ads/zzpp.zzbij:Lcom/google/android/gms/internal/ads/zzoz;
        //   544: checkcast       Lcom/google/android/gms/internal/ads/zzpd;
        //   547: astore          6
        //   549: aload_0        
        //   550: getfield        com/google/android/gms/internal/ads/zzpp.zzbke:Ljava/lang/ref/WeakReference;
        //   553: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   556: checkcast       Landroid/view/View;
        //   559: astore          7
        //   561: aload           6
        //   563: ifnull          646
        //   566: aload           6
        //   568: invokevirtual   com/google/android/gms/internal/ads/zzpd.getContext:()Landroid/content/Context;
        //   571: ifnull          646
        //   574: aload           7
        //   576: ifnull          646
        //   579: invokestatic    com/google/android/gms/ads/internal/zzbv.zzfh:()Lcom/google/android/gms/internal/ads/zzaiy;
        //   582: aload           7
        //   584: invokevirtual   android/view/View.getContext:()Landroid/content/Context;
        //   587: invokevirtual   com/google/android/gms/internal/ads/zzaiy.zzu:(Landroid/content/Context;)Z
        //   590: ifeq            646
        //   593: aload_0        
        //   594: getfield        com/google/android/gms/internal/ads/zzpp.zzbkb:Ljava/lang/ref/WeakReference;
        //   597: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //   600: checkcast       Lcom/google/android/gms/internal/ads/zzfp;
        //   603: astore_3       
        //   604: aload_3        
        //   605: astore_1       
        //   606: aload_3        
        //   607: ifnonnull       637
        //   610: new             Lcom/google/android/gms/internal/ads/zzfp;
        //   613: dup            
        //   614: aload           7
        //   616: invokevirtual   android/view/View.getContext:()Landroid/content/Context;
        //   619: aload           7
        //   621: invokespecial   com/google/android/gms/internal/ads/zzfp.<init>:(Landroid/content/Context;Landroid/view/View;)V
        //   624: astore_1       
        //   625: aload_0        
        //   626: new             Ljava/lang/ref/WeakReference;
        //   629: dup            
        //   630: aload_1        
        //   631: invokespecial   java/lang/ref/WeakReference.<init>:(Ljava/lang/Object;)V
        //   634: putfield        com/google/android/gms/internal/ads/zzpp.zzbkb:Ljava/lang/ref/WeakReference;
        //   637: aload_1        
        //   638: aload           6
        //   640: invokevirtual   com/google/android/gms/internal/ads/zzpd.zzks:()Lcom/google/android/gms/internal/ads/zzaix;
        //   643: invokevirtual   com/google/android/gms/internal/ads/zzfp.zza:(Lcom/google/android/gms/internal/ads/zzft;)V
        //   646: aload           5
        //   648: monitorexit    
        //   649: goto            307
        //   652: astore_1       
        //   653: aload           5
        //   655: monitorexit    
        //   656: aload_1        
        //   657: athrow         
        //   658: aconst_null    
        //   659: astore_1       
        //   660: goto            456
        //   663: iload_2        
        //   664: iconst_1       
        //   665: iadd           
        //   666: istore_2       
        //   667: goto            178
        //   670: aconst_null    
        //   671: astore_1       
        //   672: goto            226
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      35     58     64     Any
        //  36     57     58     64     Any
        //  59     62     58     64     Any
        //  64     83     58     64     Any
        //  88     123    58     64     Any
        //  123    130    58     64     Any
        //  130    142    416    421    Any
        //  142    176    58     64     Any
        //  183    214    58     64     Any
        //  218    226    58     64     Any
        //  230    236    58     64     Any
        //  236    294    58     64     Any
        //  294    307    652    658    Any
        //  307    310    58     64     Any
        //  311    332    416    421    Any
        //  337    345    416    421    Any
        //  350    371    416    421    Any
        //  376    382    416    421    Any
        //  382    394    416    421    Any
        //  404    411    416    421    Any
        //  411    413    416    421    Any
        //  417    419    416    421    Any
        //  419    421    58     64     Any
        //  421    441    58     64     Any
        //  444    456    58     64     Any
        //  460    537    58     64     Any
        //  540    561    652    658    Any
        //  566    574    652    658    Any
        //  579    604    652    658    Any
        //  610    637    652    658    Any
        //  637    646    652    658    Any
        //  646    649    652    658    Any
        //  653    656    652    658    Any
        //  656    658    58     64     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0307:
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
    
    public final void zzc(final IObjectWrapper objectWrapper) {
        synchronized (this.mLock) {
            this.zzbij.setClickConfirmingView((View)ObjectWrapper.unwrap(objectWrapper));
        }
    }
}
