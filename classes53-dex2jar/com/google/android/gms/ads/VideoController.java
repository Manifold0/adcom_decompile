// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class VideoController
{
    @KeepForSdk
    public static final int PLAYBACK_STATE_ENDED = 3;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PAUSED = 2;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PLAYING = 1;
    @KeepForSdk
    public static final int PLAYBACK_STATE_READY = 5;
    @KeepForSdk
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object mLock;
    @Nullable
    @GuardedBy("mLock")
    private zzlo zzux;
    @Nullable
    @GuardedBy("mLock")
    private VideoLifecycleCallbacks zzuy;
    
    public VideoController() {
        this.mLock = new Object();
    }
    
    public final float getAspectRatio() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    11: ifnonnull       18
        //    14: aload_2        
        //    15: monitorexit    
        //    16: fconst_0       
        //    17: freturn        
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    22: invokeinterface com/google/android/gms/internal/ads/zzlo.getAspectRatio:()F
        //    27: fstore_1       
        //    28: aload_2        
        //    29: monitorexit    
        //    30: fload_1        
        //    31: freturn        
        //    32: astore_3       
        //    33: aload_2        
        //    34: monitorexit    
        //    35: aload_3        
        //    36: athrow         
        //    37: astore_3       
        //    38: ldc             "Unable to call getAspectRatio on video controller."
        //    40: aload_3        
        //    41: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    44: aload_2        
        //    45: monitorexit    
        //    46: fconst_0       
        //    47: freturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     32     37     Any
        //  18     28     37     48     Landroid/os/RemoteException;
        //  18     28     32     37     Any
        //  28     30     32     37     Any
        //  33     35     32     37     Any
        //  38     46     32     37     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    @KeepForSdk
    public final int getPlaybackState() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    11: ifnonnull       18
        //    14: aload_2        
        //    15: monitorexit    
        //    16: iconst_0       
        //    17: ireturn        
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    22: invokeinterface com/google/android/gms/internal/ads/zzlo.getPlaybackState:()I
        //    27: istore_1       
        //    28: aload_2        
        //    29: monitorexit    
        //    30: iload_1        
        //    31: ireturn        
        //    32: astore_3       
        //    33: aload_2        
        //    34: monitorexit    
        //    35: aload_3        
        //    36: athrow         
        //    37: astore_3       
        //    38: ldc             "Unable to call getPlaybackState on video controller."
        //    40: aload_3        
        //    41: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    44: aload_2        
        //    45: monitorexit    
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     32     37     Any
        //  18     28     37     48     Landroid/os/RemoteException;
        //  18     28     32     37     Any
        //  28     30     32     37     Any
        //  33     35     32     37     Any
        //  38     46     32     37     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    @Nullable
    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        synchronized (this.mLock) {
            return this.zzuy;
        }
    }
    
    public final boolean hasVideoContent() {
        while (true) {
            synchronized (this.mLock) {
                if (this.zzux != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public final boolean isClickToExpandEnabled() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    11: ifnonnull       18
        //    14: aload_2        
        //    15: monitorexit    
        //    16: iconst_0       
        //    17: ireturn        
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    22: invokeinterface com/google/android/gms/internal/ads/zzlo.isClickToExpandEnabled:()Z
        //    27: istore_1       
        //    28: aload_2        
        //    29: monitorexit    
        //    30: iload_1        
        //    31: ireturn        
        //    32: astore_3       
        //    33: aload_2        
        //    34: monitorexit    
        //    35: aload_3        
        //    36: athrow         
        //    37: astore_3       
        //    38: ldc             "Unable to call isClickToExpandEnabled."
        //    40: aload_3        
        //    41: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    44: aload_2        
        //    45: monitorexit    
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     32     37     Any
        //  18     28     37     48     Landroid/os/RemoteException;
        //  18     28     32     37     Any
        //  28     30     32     37     Any
        //  33     35     32     37     Any
        //  38     46     32     37     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    public final boolean isCustomControlsEnabled() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    11: ifnonnull       18
        //    14: aload_2        
        //    15: monitorexit    
        //    16: iconst_0       
        //    17: ireturn        
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    22: invokeinterface com/google/android/gms/internal/ads/zzlo.isCustomControlsEnabled:()Z
        //    27: istore_1       
        //    28: aload_2        
        //    29: monitorexit    
        //    30: iload_1        
        //    31: ireturn        
        //    32: astore_3       
        //    33: aload_2        
        //    34: monitorexit    
        //    35: aload_3        
        //    36: athrow         
        //    37: astore_3       
        //    38: ldc             "Unable to call isUsingCustomPlayerControls."
        //    40: aload_3        
        //    41: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    44: aload_2        
        //    45: monitorexit    
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     32     37     Any
        //  18     28     37     48     Landroid/os/RemoteException;
        //  18     28     32     37     Any
        //  28     30     32     37     Any
        //  33     35     32     37     Any
        //  38     46     32     37     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    public final boolean isMuted() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    11: ifnonnull       18
        //    14: aload_2        
        //    15: monitorexit    
        //    16: iconst_1       
        //    17: ireturn        
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    22: invokeinterface com/google/android/gms/internal/ads/zzlo.isMuted:()Z
        //    27: istore_1       
        //    28: aload_2        
        //    29: monitorexit    
        //    30: iload_1        
        //    31: ireturn        
        //    32: astore_3       
        //    33: aload_2        
        //    34: monitorexit    
        //    35: aload_3        
        //    36: athrow         
        //    37: astore_3       
        //    38: ldc             "Unable to call isMuted on video controller."
        //    40: aload_3        
        //    41: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    44: aload_2        
        //    45: monitorexit    
        //    46: iconst_1       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     32     37     Any
        //  18     28     37     48     Landroid/os/RemoteException;
        //  18     28     32     37     Any
        //  28     30     32     37     Any
        //  33     35     32     37     Any
        //  38     46     32     37     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    public final void mute(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    11: ifnonnull       17
        //    14: aload_2        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    21: iload_1        
        //    22: invokeinterface com/google/android/gms/internal/ads/zzlo.mute:(Z)V
        //    27: aload_2        
        //    28: monitorexit    
        //    29: return         
        //    30: astore_3       
        //    31: aload_2        
        //    32: monitorexit    
        //    33: aload_3        
        //    34: athrow         
        //    35: astore_3       
        //    36: ldc             "Unable to call mute on video controller."
        //    38: aload_3        
        //    39: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    42: goto            27
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     30     35     Any
        //  17     27     35     45     Landroid/os/RemoteException;
        //  17     27     30     35     Any
        //  27     29     30     35     Any
        //  31     33     30     35     Any
        //  36     42     30     35     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    
    public final void pause() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    11: ifnonnull       17
        //    14: aload_1        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    21: invokeinterface com/google/android/gms/internal/ads/zzlo.pause:()V
        //    26: aload_1        
        //    27: monitorexit    
        //    28: return         
        //    29: astore_2       
        //    30: aload_1        
        //    31: monitorexit    
        //    32: aload_2        
        //    33: athrow         
        //    34: astore_2       
        //    35: ldc             "Unable to call pause on video controller."
        //    37: aload_2        
        //    38: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    41: goto            26
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     29     34     Any
        //  17     26     34     44     Landroid/os/RemoteException;
        //  17     26     29     34     Any
        //  26     28     29     34     Any
        //  30     32     29     34     Any
        //  35     41     29     34     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    
    public final void play() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    11: ifnonnull       17
        //    14: aload_1        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    21: invokeinterface com/google/android/gms/internal/ads/zzlo.play:()V
        //    26: aload_1        
        //    27: monitorexit    
        //    28: return         
        //    29: astore_2       
        //    30: aload_1        
        //    31: monitorexit    
        //    32: aload_2        
        //    33: athrow         
        //    34: astore_2       
        //    35: ldc             "Unable to call play on video controller."
        //    37: aload_2        
        //    38: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    41: goto            26
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     29     34     Any
        //  17     26     34     44     Landroid/os/RemoteException;
        //  17     26     29     34     Any
        //  26     28     29     34     Any
        //  30     32     29     34     Any
        //  35     41     29     34     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    
    public final void setVideoLifecycleCallbacks(final VideoLifecycleCallbacks p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "VideoLifecycleCallbacks may not be null."
        //     3: invokestatic    com/google/android/gms/common/internal/Preconditions.checkNotNull:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //     6: pop            
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/ads/VideoController.mLock:Ljava/lang/Object;
        //    11: astore_2       
        //    12: aload_2        
        //    13: monitorenter   
        //    14: aload_0        
        //    15: aload_1        
        //    16: putfield        com/google/android/gms/ads/VideoController.zzuy:Lcom/google/android/gms/ads/VideoController$VideoLifecycleCallbacks;
        //    19: aload_0        
        //    20: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    23: ifnonnull       29
        //    26: aload_2        
        //    27: monitorexit    
        //    28: return         
        //    29: aload_0        
        //    30: getfield        com/google/android/gms/ads/VideoController.zzux:Lcom/google/android/gms/internal/ads/zzlo;
        //    33: new             Lcom/google/android/gms/internal/ads/zzmt;
        //    36: dup            
        //    37: aload_1        
        //    38: invokespecial   com/google/android/gms/internal/ads/zzmt.<init>:(Lcom/google/android/gms/ads/VideoController$VideoLifecycleCallbacks;)V
        //    41: invokeinterface com/google/android/gms/internal/ads/zzlo.zza:(Lcom/google/android/gms/internal/ads/zzlr;)V
        //    46: aload_2        
        //    47: monitorexit    
        //    48: return         
        //    49: astore_1       
        //    50: aload_2        
        //    51: monitorexit    
        //    52: aload_1        
        //    53: athrow         
        //    54: astore_1       
        //    55: ldc             "Unable to call setVideoLifecycleCallbacks on video controller."
        //    57: aload_1        
        //    58: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    61: goto            46
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  14     28     49     54     Any
        //  29     46     54     64     Landroid/os/RemoteException;
        //  29     46     49     54     Any
        //  46     48     49     54     Any
        //  50     52     49     54     Any
        //  55     61     49     54     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
    
    public final void zza(final zzlo zzux) {
        synchronized (this.mLock) {
            this.zzux = zzux;
            if (this.zzuy != null) {
                this.setVideoLifecycleCallbacks(this.zzuy);
            }
        }
    }
    
    public final zzlo zzbc() {
        synchronized (this.mLock) {
            return this.zzux;
        }
    }
    
    public static class VideoLifecycleCallbacks
    {
        public void onVideoEnd() {
        }
        
        public void onVideoMute(final boolean b) {
        }
        
        public void onVideoPause() {
        }
        
        public void onVideoPlay() {
        }
        
        public void onVideoStart() {
        }
    }
}
