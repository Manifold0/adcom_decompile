// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzafp implements Runnable
{
    private final /* synthetic */ zzaef zzchk;
    private final /* synthetic */ zzaeq zzchl;
    private final /* synthetic */ zzafn zzchm;
    
    zzafp(final zzafn zzchm, final zzaef zzchk, final zzaeq zzchl) {
        this.zzchm = zzchm;
        this.zzchk = zzchk;
        this.zzchl = zzchl;
    }
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzafp.zzchm:Lcom/google/android/gms/internal/ads/zzafn;
        //     4: aload_0        
        //     5: getfield        com/google/android/gms/internal/ads/zzafp.zzchk:Lcom/google/android/gms/internal/ads/zzaef;
        //     8: invokevirtual   com/google/android/gms/internal/ads/zzafn.zzb:(Lcom/google/android/gms/internal/ads/zzaef;)Lcom/google/android/gms/internal/ads/zzaej;
        //    11: astore_1       
        //    12: aload_1        
        //    13: astore_2       
        //    14: aload_1        
        //    15: ifnonnull       27
        //    18: new             Lcom/google/android/gms/internal/ads/zzaej;
        //    21: dup            
        //    22: iconst_0       
        //    23: invokespecial   com/google/android/gms/internal/ads/zzaej.<init>:(I)V
        //    26: astore_2       
        //    27: aload_0        
        //    28: getfield        com/google/android/gms/internal/ads/zzafp.zzchl:Lcom/google/android/gms/internal/ads/zzaeq;
        //    31: aload_2        
        //    32: invokeinterface com/google/android/gms/internal/ads/zzaeq.zza:(Lcom/google/android/gms/internal/ads/zzaej;)V
        //    37: return         
        //    38: astore_1       
        //    39: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //    42: aload_1        
        //    43: ldc             "AdRequestServiceImpl.loadAdAsync"
        //    45: invokevirtual   com/google/android/gms/internal/ads/zzajm.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //    48: ldc             "Could not fetch ad response due to an Exception."
        //    50: aload_1        
        //    51: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    54: aconst_null    
        //    55: astore_1       
        //    56: goto            12
        //    59: astore_1       
        //    60: ldc             "Fail to forward ad response."
        //    62: aload_1        
        //    63: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    66: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      12     38     59     Ljava/lang/Exception;
        //  27     37     59     67     Landroid/os/RemoteException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
