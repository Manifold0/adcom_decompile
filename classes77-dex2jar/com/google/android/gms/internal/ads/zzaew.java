// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.OutputStream;

final class zzaew implements Runnable
{
    private final /* synthetic */ OutputStream zzcfw;
    private final /* synthetic */ byte[] zzcfx;
    
    zzaew(final zzaev zzaev, final OutputStream zzcfw, final byte[] zzcfx) {
        this.zzcfw = zzcfw;
        this.zzcfx = zzcfx;
    }
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/google/android/gms/internal/ads/zzaew.zzcfw:Ljava/io/OutputStream;
        //     8: invokespecial   java/io/DataOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    11: astore_2       
        //    12: aload_2        
        //    13: astore_1       
        //    14: aload_2        
        //    15: aload_0        
        //    16: getfield        com/google/android/gms/internal/ads/zzaew.zzcfx:[B
        //    19: arraylength    
        //    20: invokevirtual   java/io/DataOutputStream.writeInt:(I)V
        //    23: aload_2        
        //    24: astore_1       
        //    25: aload_2        
        //    26: aload_0        
        //    27: getfield        com/google/android/gms/internal/ads/zzaew.zzcfx:[B
        //    30: invokevirtual   java/io/DataOutputStream.write:([B)V
        //    33: aload_2        
        //    34: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    37: return         
        //    38: astore_3       
        //    39: aconst_null    
        //    40: astore_2       
        //    41: aload_2        
        //    42: astore_1       
        //    43: ldc             "Error transporting the ad response"
        //    45: aload_3        
        //    46: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    49: aload_2        
        //    50: astore_1       
        //    51: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //    54: aload_3        
        //    55: ldc             "LargeParcelTeleporter.pipeData.1"
        //    57: invokevirtual   com/google/android/gms/internal/ads/zzajm.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //    60: aload_2        
        //    61: ifnonnull       72
        //    64: aload_0        
        //    65: getfield        com/google/android/gms/internal/ads/zzaew.zzcfw:Ljava/io/OutputStream;
        //    68: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    71: return         
        //    72: aload_2        
        //    73: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    76: return         
        //    77: astore_2       
        //    78: aconst_null    
        //    79: astore_1       
        //    80: aload_1        
        //    81: ifnonnull       93
        //    84: aload_0        
        //    85: getfield        com/google/android/gms/internal/ads/zzaew.zzcfw:Ljava/io/OutputStream;
        //    88: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    91: aload_2        
        //    92: athrow         
        //    93: aload_1        
        //    94: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    97: goto            91
        //   100: astore_2       
        //   101: goto            80
        //   104: astore_3       
        //   105: goto            41
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      12     38     41     Ljava/io/IOException;
        //  0      12     77     80     Any
        //  14     23     104    108    Ljava/io/IOException;
        //  14     23     100    104    Any
        //  25     33     104    108    Ljava/io/IOException;
        //  25     33     100    104    Any
        //  43     49     100    104    Any
        //  51     60     100    104    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
