// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.text.TextUtils;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import android.graphics.Point;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;
import android.view.View;
import android.os.Build$VERSION;
import android.view.Display;
import android.database.ContentObserver;
import android.provider.Settings$System;
import android.os.Handler;
import java.util.UUID;
import android.graphics.Rect;
import java.util.HashSet;
import com.google.android.gms.common.util.VisibleForTesting;
import android.content.BroadcastReceiver;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.app.KeyguardManager;
import android.os.PowerManager;
import android.view.WindowManager;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

@zzadh
@ParametersAreNonnullByDefault
public final class zzet implements ViewTreeObserver$OnGlobalLayoutListener, ViewTreeObserver$OnScrollChangedListener
{
    private final Object mLock;
    private boolean zzaaq;
    private zzamj zzadz;
    private final Context zzaeo;
    private final WeakReference<zzajh> zzaeq;
    private WeakReference<ViewTreeObserver> zzaer;
    private final zzgd zzaes;
    protected final zzer zzaet;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private final DisplayMetrics zzaex;
    @Nullable
    private zzfa zzaey;
    private boolean zzaez;
    private boolean zzafa;
    private boolean zzafb;
    private boolean zzafc;
    private boolean zzafd;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzafe;
    private final HashSet<zzeq> zzaff;
    private final HashSet<zzfo> zzafg;
    private final Rect zzafh;
    private final zzew zzafi;
    private float zzafj;
    
    public zzet(final Context zzaeo, final zzjn zzjn, final zzajh zzajh, final zzang zzang, final zzgd zzaes) {
        this.mLock = new Object();
        this.zzaaq = false;
        this.zzafa = false;
        this.zzaff = new HashSet<zzeq>();
        this.zzafg = new HashSet<zzfo>();
        this.zzafh = new Rect();
        this.zzaeq = new WeakReference<zzajh>(zzajh);
        this.zzaes = zzaes;
        this.zzaer = new WeakReference<ViewTreeObserver>(null);
        this.zzafb = true;
        this.zzafd = false;
        this.zzadz = new zzamj(200L);
        this.zzaet = new zzer(UUID.randomUUID().toString(), zzang, zzjn.zzarb, zzajh.zzcob, zzajh.zzfz(), zzjn.zzare);
        this.zzaeu = (WindowManager)zzaeo.getSystemService("window");
        this.zzaev = (PowerManager)zzaeo.getApplicationContext().getSystemService("power");
        this.zzaew = (KeyguardManager)zzaeo.getSystemService("keyguard");
        this.zzaeo = zzaeo;
        this.zzafi = new zzew(this, new Handler());
        this.zzaeo.getContentResolver().registerContentObserver(Settings$System.CONTENT_URI, true, (ContentObserver)this.zzafi);
        this.zzaex = zzaeo.getResources().getDisplayMetrics();
        final Display defaultDisplay = this.zzaeu.getDefaultDisplay();
        this.zzafh.right = defaultDisplay.getWidth();
        this.zzafh.bottom = defaultDisplay.getHeight();
        this.zzgb();
    }
    
    @VisibleForTesting
    private final boolean isScreenOn() {
        if (Build$VERSION.SDK_INT >= 20) {
            return this.zzaev.isInteractive();
        }
        return this.zzaev.isScreenOn();
    }
    
    private static int zza(final int n, final DisplayMetrics displayMetrics) {
        return (int)(n / displayMetrics.density);
    }
    
    private final JSONObject zza(@Nullable final View view, @Nullable final Boolean b) throws JSONException {
        if (view == null) {
            return this.zzgg().put("isAttachedToWindow", false).put("isScreenOn", this.isScreenOn()).put("isVisible", false);
        }
        final boolean attachedToWindow = zzbv.zzem().isAttachedToWindow(view);
        Object zzgg = new int[2];
        final int[] array = new int[2];
        while (true) {
            try {
                view.getLocationOnScreen((int[])zzgg);
                view.getLocationInWindow(array);
                final Rect rect = new Rect();
                rect.left = zzgg[0];
                rect.top = zzgg[1];
                rect.right = rect.left + view.getWidth();
                rect.bottom = rect.top + view.getHeight();
                final Rect rect2 = new Rect();
                final boolean globalVisibleRect = view.getGlobalVisibleRect(rect2, (Point)null);
                final Rect rect3 = new Rect();
                final boolean localVisibleRect = view.getLocalVisibleRect(rect3);
                final Rect rect4 = new Rect();
                view.getHitRect(rect4);
                zzgg = this.zzgg();
                ((JSONObject)zzgg).put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", attachedToWindow).put("viewBox", (Object)new JSONObject().put("top", zza(this.zzafh.top, this.zzaex)).put("bottom", zza(this.zzafh.bottom, this.zzaex)).put("left", zza(this.zzafh.left, this.zzaex)).put("right", zza(this.zzafh.right, this.zzaex))).put("adBox", (Object)new JSONObject().put("top", zza(rect.top, this.zzaex)).put("bottom", zza(rect.bottom, this.zzaex)).put("left", zza(rect.left, this.zzaex)).put("right", zza(rect.right, this.zzaex))).put("globalVisibleBox", (Object)new JSONObject().put("top", zza(rect2.top, this.zzaex)).put("bottom", zza(rect2.bottom, this.zzaex)).put("left", zza(rect2.left, this.zzaex)).put("right", zza(rect2.right, this.zzaex))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", (Object)new JSONObject().put("top", zza(rect3.top, this.zzaex)).put("bottom", zza(rect3.bottom, this.zzaex)).put("left", zza(rect3.left, this.zzaex)).put("right", zza(rect3.right, this.zzaex))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", (Object)new JSONObject().put("top", zza(rect4.top, this.zzaex)).put("bottom", zza(rect4.bottom, this.zzaex)).put("left", zza(rect4.left, this.zzaex)).put("right", zza(rect4.right, this.zzaex))).put("screenDensity", (double)this.zzaex.density);
                Boolean value = b;
                if (b == null) {
                    value = zzbv.zzek().zza(view, this.zzaev, this.zzaew);
                }
                ((JSONObject)zzgg).put("isVisible", (boolean)value);
                return (JSONObject)zzgg;
            }
            catch (Exception ex) {
                zzakb.zzb("Failure getting view location.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    private static JSONObject zza(final JSONObject jsonObject) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final JSONObject jsonObject2 = new JSONObject();
        jsonArray.put((Object)jsonObject);
        jsonObject2.put("units", (Object)jsonArray);
        return jsonObject2;
    }
    
    private final void zza(JSONObject zza, final boolean b) {
        try {
            zza = zza(zza);
            final ArrayList<Object> list = new ArrayList<Object>(this.zzafg);
            final int size = list.size();
            int i = 0;
            while (i < size) {
                final zzfo value = list.get(i);
                ++i;
                value.zzb(zza, b);
            }
        }
        catch (Throwable t) {
            zzakb.zzb("Skipping active view message.", t);
        }
    }
    
    private final void zzgd() {
        if (this.zzaey != null) {
            this.zzaey.zza(this);
        }
    }
    
    private final void zzgf() {
        final ViewTreeObserver viewTreeObserver = this.zzaer.get();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return;
        }
        viewTreeObserver.removeOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
        viewTreeObserver.removeGlobalOnLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
    }
    
    private final JSONObject zzgg() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("afmaVersion", (Object)this.zzaet.zzfw()).put("activeViewJSON", (Object)this.zzaet.zzfx()).put("timestamp", zzbv.zzer().elapsedRealtime()).put("adFormat", (Object)this.zzaet.zzfv()).put("hashCode", (Object)this.zzaet.zzfy()).put("isMraid", this.zzaet.zzfz()).put("isStopped", this.zzafa).put("isPaused", this.zzaaq).put("isNative", this.zzaet.zzga()).put("isScreenOn", this.isScreenOn()).put("appMuted", zzbv.zzfj().zzdp()).put("appVolume", (double)zzbv.zzfj().zzdo()).put("deviceVolume", (double)this.zzafj);
        return jsonObject;
    }
    
    public final void onGlobalLayout() {
        this.zzl(2);
    }
    
    public final void onScrollChanged() {
        this.zzl(1);
    }
    
    public final void pause() {
        synchronized (this.mLock) {
            this.zzaaq = true;
            this.zzl(3);
        }
    }
    
    public final void resume() {
        synchronized (this.mLock) {
            this.zzaaq = false;
            this.zzl(3);
        }
    }
    
    public final void stop() {
        synchronized (this.mLock) {
            this.zzafa = true;
            this.zzl(3);
        }
    }
    
    public final void zza(final zzfa zzaey) {
        synchronized (this.mLock) {
            this.zzaey = zzaey;
        }
    }
    
    public final void zza(final zzfo p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzet.zzafg:Ljava/util/HashSet;
        //     4: invokevirtual   java/util/HashSet.isEmpty:()Z
        //     7: ifeq            31
        //    10: aload_0        
        //    11: getfield        com/google/android/gms/internal/ads/zzet.mLock:Ljava/lang/Object;
        //    14: astore_2       
        //    15: aload_2        
        //    16: monitorenter   
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/internal/ads/zzet.zzafe:Landroid/content/BroadcastReceiver;
        //    21: ifnull          65
        //    24: aload_2        
        //    25: monitorexit    
        //    26: aload_0        
        //    27: iconst_3       
        //    28: invokevirtual   com/google/android/gms/internal/ads/zzet.zzl:(I)V
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/internal/ads/zzet.zzafg:Ljava/util/HashSet;
        //    35: aload_1        
        //    36: invokevirtual   java/util/HashSet.add:(Ljava/lang/Object;)Z
        //    39: pop            
        //    40: aload_1        
        //    41: aload_0        
        //    42: aload_0        
        //    43: getfield        com/google/android/gms/internal/ads/zzet.zzaes:Lcom/google/android/gms/internal/ads/zzgd;
        //    46: invokeinterface com/google/android/gms/internal/ads/zzgd.zzgh:()Landroid/view/View;
        //    51: aconst_null    
        //    52: invokespecial   com/google/android/gms/internal/ads/zzet.zza:(Landroid/view/View;Ljava/lang/Boolean;)Lorg/json/JSONObject;
        //    55: invokestatic    com/google/android/gms/internal/ads/zzet.zza:(Lorg/json/JSONObject;)Lorg/json/JSONObject;
        //    58: iconst_0       
        //    59: invokeinterface com/google/android/gms/internal/ads/zzfo.zzb:(Lorg/json/JSONObject;Z)V
        //    64: return         
        //    65: new             Landroid/content/IntentFilter;
        //    68: dup            
        //    69: invokespecial   android/content/IntentFilter.<init>:()V
        //    72: astore_3       
        //    73: aload_3        
        //    74: ldc_w           "android.intent.action.SCREEN_ON"
        //    77: invokevirtual   android/content/IntentFilter.addAction:(Ljava/lang/String;)V
        //    80: aload_3        
        //    81: ldc_w           "android.intent.action.SCREEN_OFF"
        //    84: invokevirtual   android/content/IntentFilter.addAction:(Ljava/lang/String;)V
        //    87: aload_0        
        //    88: new             Lcom/google/android/gms/internal/ads/zzeu;
        //    91: dup            
        //    92: aload_0        
        //    93: invokespecial   com/google/android/gms/internal/ads/zzeu.<init>:(Lcom/google/android/gms/internal/ads/zzet;)V
        //    96: putfield        com/google/android/gms/internal/ads/zzet.zzafe:Landroid/content/BroadcastReceiver;
        //    99: invokestatic    com/google/android/gms/ads/internal/zzbv.zzfk:()Lcom/google/android/gms/internal/ads/zzamq;
        //   102: aload_0        
        //   103: getfield        com/google/android/gms/internal/ads/zzet.zzaeo:Landroid/content/Context;
        //   106: aload_0        
        //   107: getfield        com/google/android/gms/internal/ads/zzet.zzafe:Landroid/content/BroadcastReceiver;
        //   110: aload_3        
        //   111: invokevirtual   com/google/android/gms/internal/ads/zzamq.zza:(Landroid/content/Context;Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)V
        //   114: aload_2        
        //   115: monitorexit    
        //   116: goto            26
        //   119: astore_1       
        //   120: aload_2        
        //   121: monitorexit    
        //   122: aload_1        
        //   123: athrow         
        //   124: astore_1       
        //   125: ldc_w           "Skipping measurement update for new client."
        //   128: aload_1        
        //   129: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   132: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  17     26     119    124    Any
        //  40     64     124    133    Lorg/json/JSONException;
        //  65     116    119    124    Any
        //  120    122    119    124    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    final void zza(final zzfo zzfo, final Map<String, String> map) {
        final String value = String.valueOf(this.zzaet.zzfy());
        String concat;
        if (value.length() != 0) {
            concat = "Received request to untrack: ".concat(value);
        }
        else {
            concat = new String("Received request to untrack: ");
        }
        zzakb.zzck(concat);
        this.zzb(zzfo);
    }
    
    public final void zzb(final zzfo p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzet.zzafg:Ljava/util/HashSet;
        //     4: aload_1        
        //     5: invokevirtual   java/util/HashSet.remove:(Ljava/lang/Object;)Z
        //     8: pop            
        //     9: aload_1        
        //    10: invokeinterface com/google/android/gms/internal/ads/zzfo.zzgl:()V
        //    15: aload_0        
        //    16: getfield        com/google/android/gms/internal/ads/zzet.zzafg:Ljava/util/HashSet;
        //    19: invokevirtual   java/util/HashSet.isEmpty:()Z
        //    22: ifeq            200
        //    25: aload_0        
        //    26: getfield        com/google/android/gms/internal/ads/zzet.mLock:Ljava/lang/Object;
        //    29: astore_1       
        //    30: aload_1        
        //    31: monitorenter   
        //    32: aload_0        
        //    33: invokespecial   com/google/android/gms/internal/ads/zzet.zzgf:()V
        //    36: aload_0        
        //    37: getfield        com/google/android/gms/internal/ads/zzet.mLock:Ljava/lang/Object;
        //    40: astore          4
        //    42: aload           4
        //    44: monitorenter   
        //    45: aload_0        
        //    46: getfield        com/google/android/gms/internal/ads/zzet.zzafe:Landroid/content/BroadcastReceiver;
        //    49: astore          5
        //    51: aload           5
        //    53: ifnull          75
        //    56: invokestatic    com/google/android/gms/ads/internal/zzbv.zzfk:()Lcom/google/android/gms/internal/ads/zzamq;
        //    59: aload_0        
        //    60: getfield        com/google/android/gms/internal/ads/zzet.zzaeo:Landroid/content/Context;
        //    63: aload_0        
        //    64: getfield        com/google/android/gms/internal/ads/zzet.zzafe:Landroid/content/BroadcastReceiver;
        //    67: invokevirtual   com/google/android/gms/internal/ads/zzamq.zza:(Landroid/content/Context;Landroid/content/BroadcastReceiver;)V
        //    70: aload_0        
        //    71: aconst_null    
        //    72: putfield        com/google/android/gms/internal/ads/zzet.zzafe:Landroid/content/BroadcastReceiver;
        //    75: aload           4
        //    77: monitorexit    
        //    78: aload_0        
        //    79: getfield        com/google/android/gms/internal/ads/zzet.zzaeo:Landroid/content/Context;
        //    82: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    85: aload_0        
        //    86: getfield        com/google/android/gms/internal/ads/zzet.zzafi:Lcom/google/android/gms/internal/ads/zzew;
        //    89: invokevirtual   android/content/ContentResolver.unregisterContentObserver:(Landroid/database/ContentObserver;)V
        //    92: aload_0        
        //    93: iconst_0       
        //    94: putfield        com/google/android/gms/internal/ads/zzet.zzafb:Z
        //    97: aload_0        
        //    98: invokespecial   com/google/android/gms/internal/ads/zzet.zzgd:()V
        //   101: new             Ljava/util/ArrayList;
        //   104: dup            
        //   105: aload_0        
        //   106: getfield        com/google/android/gms/internal/ads/zzet.zzafg:Ljava/util/HashSet;
        //   109: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //   112: checkcast       Ljava/util/ArrayList;
        //   115: astore          4
        //   117: aload           4
        //   119: invokevirtual   java/util/ArrayList.size:()I
        //   122: istore_3       
        //   123: iconst_0       
        //   124: istore_2       
        //   125: iload_2        
        //   126: iload_3        
        //   127: if_icmpge       198
        //   130: aload           4
        //   132: iload_2        
        //   133: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   136: astore          5
        //   138: iload_2        
        //   139: iconst_1       
        //   140: iadd           
        //   141: istore_2       
        //   142: aload_0        
        //   143: aload           5
        //   145: checkcast       Lcom/google/android/gms/internal/ads/zzfo;
        //   148: invokevirtual   com/google/android/gms/internal/ads/zzet.zzb:(Lcom/google/android/gms/internal/ads/zzfo;)V
        //   151: goto            125
        //   154: astore          4
        //   156: aload_1        
        //   157: monitorexit    
        //   158: aload           4
        //   160: athrow         
        //   161: astore          5
        //   163: ldc_w           "Failed trying to unregister the receiver"
        //   166: aload           5
        //   168: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   171: goto            70
        //   174: astore          5
        //   176: aload           4
        //   178: monitorexit    
        //   179: aload           5
        //   181: athrow         
        //   182: astore          5
        //   184: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   187: aload           5
        //   189: ldc_w           "ActiveViewUnit.stopScreenStatusMonitoring"
        //   192: invokevirtual   com/google/android/gms/internal/ads/zzajm.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //   195: goto            70
        //   198: aload_1        
        //   199: monitorexit    
        //   200: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  32     45     154    161    Any
        //  45     51     174    182    Any
        //  56     70     161    174    Ljava/lang/IllegalStateException;
        //  56     70     182    198    Ljava/lang/Exception;
        //  56     70     174    182    Any
        //  70     75     174    182    Any
        //  75     78     174    182    Any
        //  78     123    154    161    Any
        //  130    138    154    161    Any
        //  142    151    154    161    Any
        //  156    158    154    161    Any
        //  163    171    174    182    Any
        //  176    179    174    182    Any
        //  179    182    154    161    Any
        //  184    195    174    182    Any
        //  198    200    154    161    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    final boolean zzc(@Nullable final Map<String, String> map) {
        if (map == null) {
            return false;
        }
        final String s = map.get("hashCode");
        return !TextUtils.isEmpty((CharSequence)s) && s.equals(this.zzaet.zzfy());
    }
    
    final void zzd(final Map<String, String> map) {
        this.zzl(3);
    }
    
    final void zze(final Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            final boolean b = "1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
            final Iterator<zzeq> iterator = this.zzaff.iterator();
            while (iterator.hasNext()) {
                iterator.next().zza(this, b);
            }
        }
    }
    
    public final void zzgb() {
        this.zzafj = zzalb.zzay(this.zzaeo);
    }
    
    public final void zzgc() {
        while (true) {
            // monitorenter(this.mLock)
            while (true) {
                try {
                    if (!this.zzafb) {
                        return;
                    }
                    this.zzafc = true;
                    try {
                        final JSONObject zzgg = this.zzgg();
                        zzgg.put("doneReasonCode", (Object)"u");
                        this.zza(zzgg, true);
                        final String value = String.valueOf(this.zzaet.zzfy());
                        if (value.length() != 0) {
                            final String concat = "Untracking ad unit: ".concat(value);
                            zzakb.zzck(concat);
                            return;
                        }
                    }
                    catch (JSONException ex) {
                        zzakb.zzb("JSON failure while processing active view data.", (Throwable)ex);
                    }
                    catch (RuntimeException ex2) {
                        zzakb.zzb("Failure while processing active view data.", (Throwable)ex2);
                    }
                }
                finally {}
                final String concat = new String("Untracking ad unit: ");
                continue;
            }
        }
    }
    
    public final boolean zzge() {
        synchronized (this.mLock) {
            return this.zzafb;
        }
    }
    
    protected final void zzl(final int n) {
        View zzgh = null;
        boolean b = false;
        boolean zzafd = false;
    Label_0128_Outer:
        while (true) {
            while (true) {
            Label_0161:
                while (true) {
                    synchronized (this.mLock) {
                        final Iterator<zzfo> iterator = this.zzafg.iterator();
                        Block_11: {
                            while (iterator.hasNext()) {
                                if (iterator.next().zzgk()) {
                                    break Block_11;
                                }
                            }
                            goto Label_0362;
                        }
                        if (!true || !this.zzafb) {
                            return;
                        }
                        zzgh = this.zzaes.zzgh();
                        if (zzgh != null && zzbv.zzek().zza(zzgh, this.zzaev, this.zzaew)) {
                            b = true;
                            if (zzgh == null || !b || !zzgh.getGlobalVisibleRect(new Rect(), (Point)null)) {
                                break Label_0161;
                            }
                            zzafd = true;
                            if (this.zzaes.zzgi()) {
                                this.zzgc();
                                return;
                            }
                            break;
                        }
                    }
                    b = false;
                    continue Label_0128_Outer;
                }
                zzafd = false;
                continue;
            }
        }
        if (n == 1 && !this.zzadz.tryAcquire() && zzafd == this.zzafd) {
            // monitorexit(o)
            return;
        }
        if (!zzafd && !this.zzafd && n == 1) {
            // monitorexit(o)
            return;
        }
        while (true) {
            try {
                this.zza(this.zza(zzgh, b), false);
                this.zzafd = zzafd;
                while (true) {
                    final View zzgh2 = this.zzaes.zzgj().zzgh();
                    if (zzgh2 != null) {
                        final ViewTreeObserver viewTreeObserver = this.zzaer.get();
                        final ViewTreeObserver viewTreeObserver2 = zzgh2.getViewTreeObserver();
                        if (viewTreeObserver2 != viewTreeObserver) {
                            this.zzgf();
                            if (!this.zzaez || (viewTreeObserver != null && viewTreeObserver.isAlive())) {
                                this.zzaez = true;
                                viewTreeObserver2.addOnScrollChangedListener((ViewTreeObserver$OnScrollChangedListener)this);
                                viewTreeObserver2.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
                            }
                            this.zzaer = new WeakReference<ViewTreeObserver>(viewTreeObserver2);
                        }
                    }
                    this.zzgd();
                    return;
                    final JSONException ex;
                    zzakb.zza("Active view update failed.", (Throwable)ex);
                    continue;
                }
            }
            // monitorexit(o)
            catch (JSONException ex) {
                continue;
            }
            catch (RuntimeException ex) {
                continue;
            }
            break;
        }
    }
}
