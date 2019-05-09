// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.File;

public final class aa
{
    public static String a(final File p0, final File p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_0        
        //     4: invokevirtual   java/io/File.exists:()Z
        //     7: ifeq            91
        //    10: aload_0        
        //    11: invokestatic    com/tapjoy/internal/bl.a:(Ljava/io/File;)Ljava/lang/String;
        //    14: invokestatic    com/tapjoy/internal/ct.b:(Ljava/lang/String;)Ljava/lang/String;
        //    17: astore_3       
        //    18: aload           4
        //    20: astore_2       
        //    21: aload_1        
        //    22: ifnull          43
        //    25: aload           4
        //    27: astore_2       
        //    28: aload_1        
        //    29: invokevirtual   java/io/File.exists:()Z
        //    32: ifeq            43
        //    35: aload_1        
        //    36: invokestatic    com/tapjoy/internal/bl.a:(Ljava/io/File;)Ljava/lang/String;
        //    39: invokestatic    com/tapjoy/internal/ct.b:(Ljava/lang/String;)Ljava/lang/String;
        //    42: astore_2       
        //    43: aload_3        
        //    44: ifnonnull       96
        //    47: aload_2        
        //    48: astore_3       
        //    49: aload_2        
        //    50: ifnonnull       73
        //    53: invokestatic    java/util/UUID.randomUUID:()Ljava/util/UUID;
        //    56: invokevirtual   java/util/UUID.toString:()Ljava/lang/String;
        //    59: astore_2       
        //    60: aload_2        
        //    61: astore_3       
        //    62: aload_1        
        //    63: ifnull          73
        //    66: aload_1        
        //    67: aload_2        
        //    68: invokestatic    com/tapjoy/internal/bl.a:(Ljava/io/File;Ljava/lang/String;)V
        //    71: aload_2        
        //    72: astore_3       
        //    73: aload_0        
        //    74: aload_3        
        //    75: invokestatic    com/tapjoy/internal/bl.a:(Ljava/io/File;Ljava/lang/String;)V
        //    78: aload_3        
        //    79: aload_0        
        //    80: invokestatic    com/tapjoy/internal/bl.a:(Ljava/io/File;)Ljava/lang/String;
        //    83: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    86: ifne            89
        //    89: aload_3        
        //    90: areturn        
        //    91: aconst_null    
        //    92: astore_3       
        //    93: goto            18
        //    96: aload_2        
        //    97: ifnonnull       112
        //   100: aload_1        
        //   101: ifnull          112
        //   104: aload_1        
        //   105: aload_3        
        //   106: invokestatic    com/tapjoy/internal/bl.a:(Ljava/io/File;Ljava/lang/String;)V
        //   109: aload_3        
        //   110: areturn        
        //   111: astore_0       
        //   112: aload_3        
        //   113: areturn        
        //   114: astore_1       
        //   115: aload_2        
        //   116: astore_3       
        //   117: goto            73
        //   120: astore_1       
        //   121: goto            78
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  66     71     114    120    Ljava/io/IOException;
        //  73     78     120    124    Ljava/io/IOException;
        //  104    109    111    112    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
