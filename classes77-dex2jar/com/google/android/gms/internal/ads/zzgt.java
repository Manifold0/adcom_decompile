// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.OutputStream;
import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzgt
{
    @VisibleForTesting
    private ByteArrayOutputStream zzajc;
    @VisibleForTesting
    private Base64OutputStream zzajd;
    
    public zzgt() {
        this.zzajc = new ByteArrayOutputStream(4096);
        this.zzajd = new Base64OutputStream((OutputStream)this.zzajc, 10);
    }
    
    @Override
    public final String toString() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzgt.zzajd:Landroid/util/Base64OutputStream;
        //     4: invokevirtual   android/util/Base64OutputStream.close:()V
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzgt.zzajc:Ljava/io/ByteArrayOutputStream;
        //    11: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    14: aload_0        
        //    15: getfield        com/google/android/gms/internal/ads/zzgt.zzajc:Ljava/io/ByteArrayOutputStream;
        //    18: invokevirtual   java/io/ByteArrayOutputStream.toString:()Ljava/lang/String;
        //    21: astore_1       
        //    22: aload_0        
        //    23: aconst_null    
        //    24: putfield        com/google/android/gms/internal/ads/zzgt.zzajc:Ljava/io/ByteArrayOutputStream;
        //    27: aload_0        
        //    28: aconst_null    
        //    29: putfield        com/google/android/gms/internal/ads/zzgt.zzajd:Landroid/util/Base64OutputStream;
        //    32: aload_1        
        //    33: areturn        
        //    34: astore_1       
        //    35: ldc             "HashManager: Unable to convert to Base64."
        //    37: aload_1        
        //    38: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    41: goto            7
        //    44: astore_1       
        //    45: ldc             "HashManager: Unable to convert to Base64."
        //    47: aload_1        
        //    48: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    51: aload_0        
        //    52: aconst_null    
        //    53: putfield        com/google/android/gms/internal/ads/zzgt.zzajc:Ljava/io/ByteArrayOutputStream;
        //    56: aload_0        
        //    57: aconst_null    
        //    58: putfield        com/google/android/gms/internal/ads/zzgt.zzajd:Landroid/util/Base64OutputStream;
        //    61: ldc             ""
        //    63: areturn        
        //    64: astore_1       
        //    65: aload_0        
        //    66: aconst_null    
        //    67: putfield        com/google/android/gms/internal/ads/zzgt.zzajc:Ljava/io/ByteArrayOutputStream;
        //    70: aload_0        
        //    71: aconst_null    
        //    72: putfield        com/google/android/gms/internal/ads/zzgt.zzajd:Landroid/util/Base64OutputStream;
        //    75: aload_1        
        //    76: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      7      34     44     Ljava/io/IOException;
        //  7      22     44     64     Ljava/io/IOException;
        //  7      22     64     77     Any
        //  45     51     64     77     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0007:
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
    
    public final void write(final byte[] array) throws IOException {
        this.zzajd.write(array);
    }
}
