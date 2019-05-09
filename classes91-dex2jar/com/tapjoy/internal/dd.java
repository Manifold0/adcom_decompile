// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.concurrent.Executor;
import java.util.logging.Logger;

public abstract class dd implements di
{
    private static final Logger a;
    private final di b;
    
    static {
        a = Logger.getLogger(dd.class.getName());
    }
    
    public dd() {
        this.b = new df() {
            @Override
            protected final void a() {
                new Executor() {
                    @Override
                    public final void execute(final Runnable runnable) {
                        new Thread(runnable, dd.this.getClass().getSimpleName()).start();
                    }
                }.execute(new Runnable() {
                    @Override
                    public final void run() {
                        // 
                        // This method could not be decompiled.
                        // 
                        // Original Bytecode:
                        // 
                        //     1: getfield        com/tapjoy/internal/dd$1$1.a:Lcom/tapjoy/internal/dd$1;
                        //     4: getfield        com/tapjoy/internal/dd$1.a:Lcom/tapjoy/internal/dd;
                        //     7: invokevirtual   com/tapjoy/internal/dd.a:()V
                        //    10: aload_0        
                        //    11: getfield        com/tapjoy/internal/dd$1$1.a:Lcom/tapjoy/internal/dd$1;
                        //    14: invokevirtual   com/tapjoy/internal/dd$1.c:()V
                        //    17: aload_0        
                        //    18: getfield        com/tapjoy/internal/dd$1$1.a:Lcom/tapjoy/internal/dd$1;
                        //    21: invokevirtual   com/tapjoy/internal/df.f:()Lcom/tapjoy/internal/di$a;
                        //    24: astore_2       
                        //    25: getstatic       com/tapjoy/internal/di$a.c:Lcom/tapjoy/internal/di$a;
                        //    28: astore_3       
                        //    29: aload_2        
                        //    30: aload_3        
                        //    31: if_acmpne       68
                        //    34: iconst_1       
                        //    35: istore_1       
                        //    36: iload_1        
                        //    37: ifeq            50
                        //    40: aload_0        
                        //    41: getfield        com/tapjoy/internal/dd$1$1.a:Lcom/tapjoy/internal/dd$1;
                        //    44: getfield        com/tapjoy/internal/dd$1.a:Lcom/tapjoy/internal/dd;
                        //    47: invokevirtual   com/tapjoy/internal/dd.b:()V
                        //    50: aload_0        
                        //    51: getfield        com/tapjoy/internal/dd$1$1.a:Lcom/tapjoy/internal/dd$1;
                        //    54: getfield        com/tapjoy/internal/dd$1.a:Lcom/tapjoy/internal/dd;
                        //    57: invokevirtual   com/tapjoy/internal/dd.c:()V
                        //    60: aload_0        
                        //    61: getfield        com/tapjoy/internal/dd$1$1.a:Lcom/tapjoy/internal/dd$1;
                        //    64: invokevirtual   com/tapjoy/internal/dd$1.d:()V
                        //    67: return         
                        //    68: iconst_0       
                        //    69: istore_1       
                        //    70: goto            36
                        //    73: astore_2       
                        //    74: aload_0        
                        //    75: getfield        com/tapjoy/internal/dd$1$1.a:Lcom/tapjoy/internal/dd$1;
                        //    78: getfield        com/tapjoy/internal/dd$1.a:Lcom/tapjoy/internal/dd;
                        //    81: invokevirtual   com/tapjoy/internal/dd.c:()V
                        //    84: aload_2        
                        //    85: athrow         
                        //    86: astore_2       
                        //    87: aload_0        
                        //    88: getfield        com/tapjoy/internal/dd$1$1.a:Lcom/tapjoy/internal/dd$1;
                        //    91: aload_2        
                        //    92: invokevirtual   com/tapjoy/internal/dd$1.a:(Ljava/lang/Throwable;)V
                        //    95: aload_2        
                        //    96: invokestatic    com/tapjoy/internal/cu.a:(Ljava/lang/Throwable;)Ljava/lang/RuntimeException;
                        //    99: athrow         
                        //   100: astore_3       
                        //   101: invokestatic    com/tapjoy/internal/dd.g:()Ljava/util/logging/Logger;
                        //   104: getstatic       java/util/logging/Level.WARNING:Ljava/util/logging/Level;
                        //   107: ldc             "Error while attempting to shut down the service after failure."
                        //   109: aload_3        
                        //   110: invokevirtual   java/util/logging/Logger.log:(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
                        //   113: goto            84
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type                 
                        //  -----  -----  -----  -----  ---------------------
                        //  0      29     86     100    Ljava/lang/Throwable;
                        //  40     50     73     116    Ljava/lang/Throwable;
                        //  50     67     86     100    Ljava/lang/Throwable;
                        //  74     84     100    116    Ljava/lang/Exception;
                        //  74     84     86     100    Ljava/lang/Throwable;
                        //  84     86     86     100    Ljava/lang/Throwable;
                        //  101    113    86     100    Ljava/lang/Throwable;
                        // 
                        // The error that occurred was:
                        // 
                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0084:
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
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
                });
            }
            
            @Override
            protected final void b() {
                dd.this.d();
            }
        };
    }
    
    public void a() {
    }
    
    public abstract void b();
    
    public void c() {
    }
    
    public void d() {
    }
    
    @Override
    public final dh e() {
        return this.b.e();
    }
    
    @Override
    public final a f() {
        return this.b.f();
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + this.f() + "]";
    }
}
