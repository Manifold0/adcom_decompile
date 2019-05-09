// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.VideoController;
import android.os.IBinder;
import java.util.WeakHashMap;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzadh
public final class zzqv implements NativeCustomTemplateAd
{
    private static WeakHashMap<IBinder, zzqv> zzbkt;
    private final VideoController zzasv;
    private final zzqs zzbku;
    private final MediaView zzbkv;
    
    static {
        zzqv.zzbkt = new WeakHashMap<IBinder, zzqv>();
    }
    
    @VisibleForTesting
    private zzqv(final zzqs p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_0        
        //     4: invokespecial   java/lang/Object.<init>:()V
        //     7: aload_0        
        //     8: new             Lcom/google/android/gms/ads/VideoController;
        //    11: dup            
        //    12: invokespecial   com/google/android/gms/ads/VideoController.<init>:()V
        //    15: putfield        com/google/android/gms/internal/ads/zzqv.zzasv:Lcom/google/android/gms/ads/VideoController;
        //    18: aload_0        
        //    19: aload_1        
        //    20: putfield        com/google/android/gms/internal/ads/zzqv.zzbku:Lcom/google/android/gms/internal/ads/zzqs;
        //    23: aload_1        
        //    24: invokeinterface com/google/android/gms/internal/ads/zzqs.zzkh:()Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    29: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.unwrap:(Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
        //    32: checkcast       Landroid/content/Context;
        //    35: astore_1       
        //    36: aload           4
        //    38: astore_3       
        //    39: aload_1        
        //    40: ifnull          74
        //    43: new             Lcom/google/android/gms/ads/formats/MediaView;
        //    46: dup            
        //    47: aload_1        
        //    48: invokespecial   com/google/android/gms/ads/formats/MediaView.<init>:(Landroid/content/Context;)V
        //    51: astore_1       
        //    52: aload_0        
        //    53: getfield        com/google/android/gms/internal/ads/zzqv.zzbku:Lcom/google/android/gms/internal/ads/zzqs;
        //    56: aload_1        
        //    57: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.wrap:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    60: invokeinterface com/google/android/gms/internal/ads/zzqs.zzh:(Lcom/google/android/gms/dynamic/IObjectWrapper;)Z
        //    65: istore_2       
        //    66: iload_2        
        //    67: ifne            72
        //    70: aconst_null    
        //    71: astore_1       
        //    72: aload_1        
        //    73: astore_3       
        //    74: aload_0        
        //    75: aload_3        
        //    76: putfield        com/google/android/gms/internal/ads/zzqv.zzbkv:Lcom/google/android/gms/ads/formats/MediaView;
        //    79: return         
        //    80: astore_1       
        //    81: ldc             ""
        //    83: aload_1        
        //    84: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    87: aconst_null    
        //    88: astore_1       
        //    89: goto            36
        //    92: astore_1       
        //    93: ldc             ""
        //    95: aload_1        
        //    96: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    99: aload           4
        //   101: astore_3       
        //   102: goto            74
        //   105: astore_1       
        //   106: goto            81
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  23     36     105    109    Ljava/lang/NullPointerException;
        //  23     36     80     81     Landroid/os/RemoteException;
        //  52     66     92     105    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
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
    
    public static zzqv zza(final zzqs zzqs) {
        synchronized (zzqv.zzbkt) {
            final zzqv zzqv = com.google.android.gms.internal.ads.zzqv.zzbkt.get(zzqs.asBinder());
            if (zzqv != null) {
                return zzqv;
            }
            final zzqv zzqv2 = new zzqv(zzqs);
            com.google.android.gms.internal.ads.zzqv.zzbkt.put(zzqs.asBinder(), zzqv2);
            return zzqv2;
        }
    }
    
    @Override
    public final void destroy() {
        try {
            this.zzbku.destroy();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
    
    @Override
    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzbku.getAvailableAssetNames();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final String getCustomTemplateId() {
        try {
            return this.zzbku.getCustomTemplateId();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final NativeAd.Image getImage(final String s) {
        try {
            final zzpw zzap = this.zzbku.zzap(s);
            if (zzap != null) {
                return new zzpz(zzap);
            }
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
        return null;
    }
    
    @Override
    public final CharSequence getText(String zzao) {
        try {
            zzao = this.zzbku.zzao(zzao);
            return zzao;
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public final VideoController getVideoController() {
        try {
            final zzlo videoController = this.zzbku.getVideoController();
            if (videoController != null) {
                this.zzasv.zza(videoController);
            }
            return this.zzasv;
        }
        catch (RemoteException ex) {
            zzane.zzb("Exception occurred while getting video controller", (Throwable)ex);
            return this.zzasv;
        }
    }
    
    @Override
    public final MediaView getVideoMediaView() {
        return this.zzbkv;
    }
    
    @Override
    public final void performClick(final String s) {
        try {
            this.zzbku.performClick(s);
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
    
    @Override
    public final void recordImpression() {
        try {
            this.zzbku.recordImpression();
        }
        catch (RemoteException ex) {
            zzane.zzb("", (Throwable)ex);
        }
    }
    
    public final zzqs zzku() {
        return this.zzbku;
    }
}
