// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.TimerTask;
import java.util.Timer;

public class ga
{
    private final AppLovinSdkImpl a;
    private Timer b;
    private long c;
    private long d;
    private final Runnable e;
    private long f;
    private final Object g;
    
    private ga(final AppLovinSdkImpl a, final Runnable e) {
        this.g = new Object();
        this.a = a;
        this.e = e;
    }
    
    public static ga a(final long d, final AppLovinSdkImpl appLovinSdkImpl, final Runnable runnable) {
        if (d < 0L) {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Invalid fire time passed in: " + d + ".");
        }
        if (runnable == null) {
            throw new IllegalArgumentException("Cannot create a scheduled timer. Runnable is null.");
        }
        final ga ga = new ga(appLovinSdkImpl, runnable);
        ga.c = System.currentTimeMillis();
        ga.d = d;
        (ga.b = new Timer()).schedule(ga.c(), d);
        return ga;
    }
    
    private TimerTask c() {
        return new gb(this);
    }
    
    public void a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/applovin/impl/sdk/ga.g:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/applovin/impl/sdk/ga.b:Ljava/util/Timer;
        //    11: astore_2       
        //    12: aload_2        
        //    13: ifnull          40
        //    16: aload_0        
        //    17: getfield        com/applovin/impl/sdk/ga.b:Ljava/util/Timer;
        //    20: invokevirtual   java/util/Timer.cancel:()V
        //    23: aload_0        
        //    24: invokestatic    java/lang/System.currentTimeMillis:()J
        //    27: aload_0        
        //    28: getfield        com/applovin/impl/sdk/ga.c:J
        //    31: lsub           
        //    32: putfield        com/applovin/impl/sdk/ga.f:J
        //    35: aload_0        
        //    36: aconst_null    
        //    37: putfield        com/applovin/impl/sdk/ga.b:Ljava/util/Timer;
        //    40: aload_1        
        //    41: monitorexit    
        //    42: return         
        //    43: astore_2       
        //    44: aload_0        
        //    45: getfield        com/applovin/impl/sdk/ga.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    48: ifnull          68
        //    51: aload_0        
        //    52: getfield        com/applovin/impl/sdk/ga.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    55: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //    58: ldc             "Timer"
        //    60: ldc             "Encountered error while pausing timer"
        //    62: aload_2        
        //    63: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    68: aload_0        
        //    69: aconst_null    
        //    70: putfield        com/applovin/impl/sdk/ga.b:Ljava/util/Timer;
        //    73: goto            40
        //    76: astore_2       
        //    77: aload_1        
        //    78: monitorexit    
        //    79: aload_2        
        //    80: athrow         
        //    81: astore_2       
        //    82: aload_0        
        //    83: aconst_null    
        //    84: putfield        com/applovin/impl/sdk/ga.b:Ljava/util/Timer;
        //    87: aload_2        
        //    88: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  7      12     76     81     Any
        //  16     35     43     76     Ljava/lang/Throwable;
        //  16     35     81     89     Any
        //  35     40     76     81     Any
        //  40     42     76     81     Any
        //  44     68     81     89     Any
        //  68     73     76     81     Any
        //  77     79     76     81     Any
        //  82     89     76     81     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    public void b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/applovin/impl/sdk/ga.g:Ljava/lang/Object;
        //     4: astore_3       
        //     5: aload_3        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/applovin/impl/sdk/ga.f:J
        //    11: lstore_1       
        //    12: lload_1        
        //    13: lconst_0       
        //    14: lcmp           
        //    15: ifle            83
        //    18: aload_0        
        //    19: aload_0        
        //    20: getfield        com/applovin/impl/sdk/ga.d:J
        //    23: aload_0        
        //    24: getfield        com/applovin/impl/sdk/ga.f:J
        //    27: lsub           
        //    28: putfield        com/applovin/impl/sdk/ga.d:J
        //    31: aload_0        
        //    32: getfield        com/applovin/impl/sdk/ga.d:J
        //    35: lconst_0       
        //    36: lcmp           
        //    37: ifge            45
        //    40: aload_0        
        //    41: lconst_0       
        //    42: putfield        com/applovin/impl/sdk/ga.d:J
        //    45: aload_0        
        //    46: new             Ljava/util/Timer;
        //    49: dup            
        //    50: invokespecial   java/util/Timer.<init>:()V
        //    53: putfield        com/applovin/impl/sdk/ga.b:Ljava/util/Timer;
        //    56: aload_0        
        //    57: getfield        com/applovin/impl/sdk/ga.b:Ljava/util/Timer;
        //    60: aload_0        
        //    61: invokespecial   com/applovin/impl/sdk/ga.c:()Ljava/util/TimerTask;
        //    64: aload_0        
        //    65: getfield        com/applovin/impl/sdk/ga.d:J
        //    68: invokevirtual   java/util/Timer.schedule:(Ljava/util/TimerTask;J)V
        //    71: aload_0        
        //    72: invokestatic    java/lang/System.currentTimeMillis:()J
        //    75: putfield        com/applovin/impl/sdk/ga.c:J
        //    78: aload_0        
        //    79: lconst_0       
        //    80: putfield        com/applovin/impl/sdk/ga.f:J
        //    83: aload_3        
        //    84: monitorexit    
        //    85: return         
        //    86: astore          4
        //    88: aload_0        
        //    89: getfield        com/applovin/impl/sdk/ga.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    92: ifnull          113
        //    95: aload_0        
        //    96: getfield        com/applovin/impl/sdk/ga.a:Lcom/applovin/impl/sdk/AppLovinSdkImpl;
        //    99: invokevirtual   com/applovin/impl/sdk/AppLovinSdkImpl.getLogger:()Lcom/applovin/sdk/AppLovinLogger;
        //   102: ldc             "Timer"
        //   104: ldc             "Encountered error while resuming timer"
        //   106: aload           4
        //   108: invokeinterface com/applovin/sdk/AppLovinLogger.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   113: aload_0        
        //   114: lconst_0       
        //   115: putfield        com/applovin/impl/sdk/ga.f:J
        //   118: goto            83
        //   121: astore          4
        //   123: aload_3        
        //   124: monitorexit    
        //   125: aload           4
        //   127: athrow         
        //   128: astore          4
        //   130: aload_0        
        //   131: lconst_0       
        //   132: putfield        com/applovin/impl/sdk/ga.f:J
        //   135: aload           4
        //   137: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  7      12     121    128    Any
        //  18     45     86     121    Ljava/lang/Throwable;
        //  18     45     128    138    Any
        //  45     78     86     121    Ljava/lang/Throwable;
        //  45     78     128    138    Any
        //  78     83     121    128    Any
        //  83     85     121    128    Any
        //  88     113    128    138    Any
        //  113    118    121    128    Any
        //  123    125    121    128    Any
        //  130    138    121    128    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
