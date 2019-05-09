// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;

public final class zzsb extends zzrj
{
    private final OnPublisherAdViewLoadedListener zzblf;
    
    public zzsb(final OnPublisherAdViewLoadedListener zzblf) {
        this.zzblf = zzblf;
    }
    
    public final void zza(final zzks p0, final IObjectWrapper p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          8
        //     4: aload_2        
        //     5: ifnonnull       9
        //     8: return         
        //     9: new             Lcom/google/android/gms/ads/doubleclick/PublisherAdView;
        //    12: dup            
        //    13: aload_2        
        //    14: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.unwrap:(Lcom/google/android/gms/dynamic/IObjectWrapper;)Ljava/lang/Object;
        //    17: checkcast       Landroid/content/Context;
        //    20: invokespecial   com/google/android/gms/ads/doubleclick/PublisherAdView.<init>:(Landroid/content/Context;)V
        //    23: astore_3       
        //    24: aload_1        
        //    25: invokeinterface com/google/android/gms/internal/ads/zzks.zzbx:()Lcom/google/android/gms/internal/ads/zzkh;
        //    30: instanceof      Lcom/google/android/gms/internal/ads/zzjf;
        //    33: ifeq            60
        //    36: aload_1        
        //    37: invokeinterface com/google/android/gms/internal/ads/zzks.zzbx:()Lcom/google/android/gms/internal/ads/zzkh;
        //    42: checkcast       Lcom/google/android/gms/internal/ads/zzjf;
        //    45: astore_2       
        //    46: aload_2        
        //    47: ifnull          114
        //    50: aload_2        
        //    51: invokevirtual   com/google/android/gms/internal/ads/zzjf.getAdListener:()Lcom/google/android/gms/ads/AdListener;
        //    54: astore_2       
        //    55: aload_3        
        //    56: aload_2        
        //    57: invokevirtual   com/google/android/gms/ads/doubleclick/PublisherAdView.setAdListener:(Lcom/google/android/gms/ads/AdListener;)V
        //    60: aload_1        
        //    61: invokeinterface com/google/android/gms/internal/ads/zzks.zzbw:()Lcom/google/android/gms/internal/ads/zzla;
        //    66: instanceof      Lcom/google/android/gms/internal/ads/zzjp;
        //    69: ifeq            96
        //    72: aload_1        
        //    73: invokeinterface com/google/android/gms/internal/ads/zzks.zzbw:()Lcom/google/android/gms/internal/ads/zzla;
        //    78: checkcast       Lcom/google/android/gms/internal/ads/zzjp;
        //    81: astore_2       
        //    82: aload_2        
        //    83: ifnull          129
        //    86: aload_2        
        //    87: invokevirtual   com/google/android/gms/internal/ads/zzjp.getAppEventListener:()Lcom/google/android/gms/ads/doubleclick/AppEventListener;
        //    90: astore_2       
        //    91: aload_3        
        //    92: aload_2        
        //    93: invokevirtual   com/google/android/gms/ads/doubleclick/PublisherAdView.setAppEventListener:(Lcom/google/android/gms/ads/doubleclick/AppEventListener;)V
        //    96: getstatic       com/google/android/gms/internal/ads/zzamu.zzsy:Landroid/os/Handler;
        //    99: new             Lcom/google/android/gms/internal/ads/zzsc;
        //   102: dup            
        //   103: aload_0        
        //   104: aload_3        
        //   105: aload_1        
        //   106: invokespecial   com/google/android/gms/internal/ads/zzsc.<init>:(Lcom/google/android/gms/internal/ads/zzsb;Lcom/google/android/gms/ads/doubleclick/PublisherAdView;Lcom/google/android/gms/internal/ads/zzks;)V
        //   109: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //   112: pop            
        //   113: return         
        //   114: aconst_null    
        //   115: astore_2       
        //   116: goto            55
        //   119: astore_2       
        //   120: ldc             ""
        //   122: aload_2        
        //   123: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   126: goto            60
        //   129: aconst_null    
        //   130: astore_2       
        //   131: goto            91
        //   134: astore_2       
        //   135: ldc             ""
        //   137: aload_2        
        //   138: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   141: goto            96
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  24     46     119    129    Landroid/os/RemoteException;
        //  50     55     119    129    Landroid/os/RemoteException;
        //  55     60     119    129    Landroid/os/RemoteException;
        //  60     82     134    144    Landroid/os/RemoteException;
        //  86     91     134    144    Landroid/os/RemoteException;
        //  91     96     134    144    Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0060:
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
}
