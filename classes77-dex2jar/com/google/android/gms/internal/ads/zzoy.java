// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View$OnTouchListener;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import android.view.View$OnClickListener;
import java.util.Iterator;
import java.util.HashMap;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;
import android.content.Context;
import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzoy extends zzpd
{
    private Object mLock;
    @Nullable
    private zzxz zzbit;
    @Nullable
    private zzyc zzbiu;
    @Nullable
    private zzyf zzbiv;
    private final zzpa zzbiw;
    @Nullable
    private zzoz zzbix;
    private boolean zzbiy;
    
    private zzoy(final Context context, final zzpa zzbiw, final zzci zzci, final zzpb zzpb) {
        super(context, zzbiw, null, zzci, null, zzpb, null, null);
        this.zzbiy = false;
        this.mLock = new Object();
        this.zzbiw = zzbiw;
    }
    
    public zzoy(final Context context, final zzpa zzpa, final zzci zzci, final zzxz zzbit, final zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbit = zzbit;
    }
    
    public zzoy(final Context context, final zzpa zzpa, final zzci zzci, final zzyc zzbiu, final zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbiu = zzbiu;
    }
    
    public zzoy(final Context context, final zzpa zzpa, final zzci zzci, final zzyf zzbiv, final zzpb zzpb) {
        this(context, zzpa, zzci, zzpb);
        this.zzbiv = zzbiv;
    }
    
    private static HashMap<String, View> zzb(final Map<String, WeakReference<View>> map) {
        final HashMap<String, View> hashMap = new HashMap<String, View>();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (final Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                final View view = entry.getValue().get();
                if (view != null) {
                    hashMap.put(entry.getKey(), view);
                }
            }
        }
        // monitorexit(map)
        return;
    }
    
    @Override
    public final void cancelUnconfirmedClick() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.cancelUnconfirmedClick();
            }
        }
    }
    
    @Override
    public final void setClickConfirmingView(final View clickConfirmingView) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.setClickConfirmingView(clickConfirmingView);
            }
        }
    }
    
    @Nullable
    @Override
    public final View zza(final View$OnClickListener p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzoy.mLock:Ljava/lang/Object;
        //     4: astore_3       
        //     5: aload_3        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzoy.zzbix:Lcom/google/android/gms/internal/ads/zzoz;
        //    11: ifnull          30
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/internal/ads/zzoy.zzbix:Lcom/google/android/gms/internal/ads/zzoz;
        //    18: aload_1        
        //    19: iload_2        
        //    20: invokeinterface com/google/android/gms/internal/ads/zzoz.zza:(Landroid/view/View$OnClickListener;Z)Landroid/view/View;
        //    25: astore_1       
        //    26: aload_3        
        //    27: monitorexit    
        //    28: aload_1        
        //    29: areturn        
        //    30: aload_0        
        //    31: getfield        com/google/android/gms/internal/ads/zzoy.zzbiv:Lcom/google/android/gms/internal/ads/zzyf;
        //    34: ifnull          68
        //    37: aload_0        
        //    38: getfield        com/google/android/gms/internal/ads/zzoy.zzbiv:Lcom/google/android/gms/internal/ads/zzyf;
        //    41: invokeinterface com/google/android/gms/internal/ads/zzyf.zzmv:()Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    46: astore_1       
        //    47: aload_1        
        //    48: ifnull          118
        //    51: aload_1        
        //    52: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.unwrap:(Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
        //    55: checkcast       Landroid/view/View;
        //    58: astore_1       
        //    59: aload_3        
        //    60: monitorexit    
        //    61: aload_1        
        //    62: areturn        
        //    63: astore_1       
        //    64: aload_3        
        //    65: monitorexit    
        //    66: aload_1        
        //    67: athrow         
        //    68: aload_0        
        //    69: getfield        com/google/android/gms/internal/ads/zzoy.zzbit:Lcom/google/android/gms/internal/ads/zzxz;
        //    72: ifnull          88
        //    75: aload_0        
        //    76: getfield        com/google/android/gms/internal/ads/zzoy.zzbit:Lcom/google/android/gms/internal/ads/zzxz;
        //    79: invokeinterface com/google/android/gms/internal/ads/zzxz.zzmv:()Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    84: astore_1       
        //    85: goto            47
        //    88: aload_0        
        //    89: getfield        com/google/android/gms/internal/ads/zzoy.zzbiu:Lcom/google/android/gms/internal/ads/zzyc;
        //    92: ifnull          122
        //    95: aload_0        
        //    96: getfield        com/google/android/gms/internal/ads/zzoy.zzbiu:Lcom/google/android/gms/internal/ads/zzyc;
        //    99: invokeinterface com/google/android/gms/internal/ads/zzyc.zzmv:()Lcom/google/android/gms/dynamic/IObjectWrapper;
        //   104: astore_1       
        //   105: goto            47
        //   108: astore_1       
        //   109: ldc             "Failed to call getAdChoicesContent"
        //   111: aload_1        
        //   112: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   115: goto            122
        //   118: aload_3        
        //   119: monitorexit    
        //   120: aconst_null    
        //   121: areturn        
        //   122: aconst_null    
        //   123: astore_1       
        //   124: goto            47
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      28     63     68     Any
        //  30     47     108    118    Landroid/os/RemoteException;
        //  30     47     63     68     Any
        //  51     61     63     68     Any
        //  64     66     63     68     Any
        //  68     85     108    118    Landroid/os/RemoteException;
        //  68     85     63     68     Any
        //  88     105    108    118    Landroid/os/RemoteException;
        //  88     105    63     68     Any
        //  109    115    63     68     Any
        //  118    120    63     68     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    
    @Override
    public final void zza(final View view, final Map<String, WeakReference<View>> map) {
        while (true) {
            Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
            Label_0102: {
                synchronized (this.mLock) {
                    super.zzbjd = true;
                    if (this.zzbix != null) {
                        this.zzbix.zza(view, map);
                        this.zzbiw.recordImpression();
                    }
                    else {
                        try {
                            if (this.zzbiv == null || this.zzbiv.getOverrideImpressionRecording()) {
                                break Label_0102;
                            }
                            this.zzbiv.recordImpression();
                            this.zzbiw.recordImpression();
                        }
                        catch (RemoteException ex) {
                            zzakb.zzc("Failed to call recordImpression", (Throwable)ex);
                        }
                    }
                    return;
                }
            }
            if (this.zzbit != null && !this.zzbit.getOverrideImpressionRecording()) {
                this.zzbit.recordImpression();
                this.zzbiw.recordImpression();
                return;
            }
            if (this.zzbiu != null && !this.zzbiu.getOverrideImpressionRecording()) {
                this.zzbiu.recordImpression();
                this.zzbiw.recordImpression();
            }
        }
    }
    
    @Override
    public final void zza(final View view, final Map<String, WeakReference<View>> map, final Bundle bundle, final View view2) {
        while (true) {
            Preconditions.checkMainThread("performClick must be called on the main UI thread.");
            Label_0108: {
                synchronized (this.mLock) {
                    if (this.zzbix != null) {
                        this.zzbix.zza(view, map, bundle, view2);
                        this.zzbiw.onAdClicked();
                    }
                    else {
                        try {
                            if (this.zzbiv == null || this.zzbiv.getOverrideClickHandling()) {
                                break Label_0108;
                            }
                            this.zzbiv.zzj(ObjectWrapper.wrap((Object)view));
                            this.zzbiw.onAdClicked();
                        }
                        catch (RemoteException ex) {
                            zzakb.zzc("Failed to call performClick", (Throwable)ex);
                        }
                    }
                    return;
                }
            }
            final Throwable t;
            if (this.zzbit != null && !this.zzbit.getOverrideClickHandling()) {
                this.zzbit.zzj(ObjectWrapper.wrap((Object)t));
                this.zzbiw.onAdClicked();
                return;
            }
            if (this.zzbiu != null && !this.zzbiu.getOverrideClickHandling()) {
                this.zzbiu.zzj(ObjectWrapper.wrap((Object)t));
                this.zzbiw.onAdClicked();
            }
        }
    }
    
    @Override
    public final void zza(final View view, @Nullable Map<String, WeakReference<View>> zzb, @Nullable Map<String, WeakReference<View>> zzb2, final View$OnTouchListener view$OnTouchListener, final View$OnClickListener view$OnClickListener) {
        while (true) {
            while (true) {
                Label_0121: {
                    synchronized (this.mLock) {
                        this.zzbiy = true;
                        zzb = zzb((Map<String, WeakReference<View>>)zzb);
                        zzb2 = zzb((Map<String, WeakReference<View>>)zzb2);
                        try {
                            if (this.zzbiv != null) {
                                this.zzbiv.zzb(ObjectWrapper.wrap((Object)view), ObjectWrapper.wrap((Object)zzb), ObjectWrapper.wrap((Object)zzb2));
                            }
                            else {
                                if (this.zzbit == null) {
                                    break Label_0121;
                                }
                                this.zzbit.zzb(ObjectWrapper.wrap((Object)view), ObjectWrapper.wrap((Object)zzb), ObjectWrapper.wrap((Object)zzb2));
                                this.zzbit.zzk(ObjectWrapper.wrap((Object)view));
                            }
                            this.zzbiy = false;
                            return;
                        }
                        catch (RemoteException ex) {
                            zzakb.zzc("Failed to call prepareAd", (Throwable)ex);
                            continue;
                        }
                        continue;
                    }
                }
                if (this.zzbiu != null) {
                    final Throwable t;
                    this.zzbiu.zzb(ObjectWrapper.wrap((Object)t), ObjectWrapper.wrap((Object)zzb), ObjectWrapper.wrap((Object)zzb2));
                    this.zzbiu.zzk(ObjectWrapper.wrap((Object)t));
                    continue;
                }
                continue;
            }
        }
    }
    
    @Override
    public final void zza(final zzro zzro) {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                this.zzbix.zza(zzro);
            }
        }
    }
    
    @Override
    public final void zzb(final View view, final Map<String, WeakReference<View>> map) {
        while (true) {
            Label_0068: {
                synchronized (this.mLock) {
                    try {
                        if (this.zzbiv != null) {
                            this.zzbiv.zzl(ObjectWrapper.wrap((Object)view));
                        }
                        else {
                            if (this.zzbit == null) {
                                break Label_0068;
                            }
                            this.zzbit.zzl(ObjectWrapper.wrap((Object)view));
                        }
                        return;
                    }
                    catch (RemoteException ex) {
                        zzakb.zzc("Failed to call untrackView", (Throwable)ex);
                        return;
                    }
                }
            }
            if (this.zzbiu != null) {
                final Throwable t;
                this.zzbiu.zzl(ObjectWrapper.wrap((Object)t));
            }
        }
    }
    
    public final void zzc(@Nullable final zzoz zzbix) {
        synchronized (this.mLock) {
            this.zzbix = zzbix;
        }
    }
    
    @Override
    public final void zzcr() {
        if (this.zzbix != null) {
            this.zzbix.zzcr();
        }
    }
    
    @Override
    public final void zzcs() {
        if (this.zzbix != null) {
            this.zzbix.zzcs();
        }
    }
    
    @Override
    public final boolean zzkj() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                return this.zzbix.zzkj();
            }
            return this.zzbiw.zzcu();
        }
    }
    
    @Override
    public final boolean zzkk() {
        synchronized (this.mLock) {
            if (this.zzbix != null) {
                return this.zzbix.zzkk();
            }
            return this.zzbiw.zzcv();
        }
    }
    
    @Override
    public final void zzkl() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on main UI thread.");
        synchronized (this.mLock) {
            this.zzbje = true;
            if (this.zzbix != null) {
                this.zzbix.zzkl();
            }
        }
    }
    
    public final boolean zzkm() {
        synchronized (this.mLock) {
            return this.zzbiy;
        }
    }
    
    public final zzoz zzkn() {
        synchronized (this.mLock) {
            return this.zzbix;
        }
    }
    
    @Nullable
    @Override
    public final zzaqw zzko() {
        return null;
    }
    
    @Override
    public final void zzkp() {
    }
    
    @Override
    public final void zzkq() {
    }
}
