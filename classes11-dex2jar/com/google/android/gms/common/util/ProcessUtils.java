// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.io.IOException;
import android.os.StrictMode$ThreadPolicy;
import java.io.Reader;
import java.io.FileReader;
import android.os.StrictMode;
import java.io.BufferedReader;
import javax.annotation.Nullable;
import android.os.Process;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ProcessUtils
{
    private static String zzhf;
    private static int zzhg;
    
    static {
        ProcessUtils.zzhf = null;
        ProcessUtils.zzhg = 0;
    }
    
    private ProcessUtils() {
    }
    
    @Nullable
    @KeepForSdk
    public static String getMyProcessName() {
        if (ProcessUtils.zzhf == null) {
            if (ProcessUtils.zzhg == 0) {
                ProcessUtils.zzhg = Process.myPid();
            }
            ProcessUtils.zzhf = zzd(ProcessUtils.zzhg);
        }
        return ProcessUtils.zzhf;
    }
    
    @Nullable
    private static String zzd(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifgt            6
        //     4: aconst_null    
        //     5: areturn        
        //     6: new             Ljava/lang/StringBuilder;
        //     9: dup            
        //    10: bipush          25
        //    12: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //    15: ldc             "/proc/"
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: iload_0        
        //    21: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    24: ldc             "/cmdline"
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    32: invokestatic    com/google/android/gms/common/util/ProcessUtils.zzk:(Ljava/lang/String;)Ljava/io/BufferedReader;
        //    35: astore_1       
        //    36: aload_1        
        //    37: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    40: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    43: astore_2       
        //    44: aload_1        
        //    45: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    48: aload_2        
        //    49: areturn        
        //    50: astore_1       
        //    51: aconst_null    
        //    52: astore_1       
        //    53: aload_1        
        //    54: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    57: aconst_null    
        //    58: areturn        
        //    59: astore_1       
        //    60: aconst_null    
        //    61: astore_3       
        //    62: aload_1        
        //    63: astore_2       
        //    64: aload_3        
        //    65: invokestatic    com/google/android/gms/common/util/IOUtils.closeQuietly:(Ljava/io/Closeable;)V
        //    68: aload_2        
        //    69: athrow         
        //    70: astore_2       
        //    71: aload_1        
        //    72: astore_3       
        //    73: goto            64
        //    76: astore_2       
        //    77: goto            53
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      36     50     53     Ljava/io/IOException;
        //  6      36     59     64     Any
        //  36     44     76     80     Ljava/io/IOException;
        //  36     44     70     76     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0053:
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
    
    private static BufferedReader zzk(final String s) throws IOException {
        final StrictMode$ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(s));
        }
        finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
