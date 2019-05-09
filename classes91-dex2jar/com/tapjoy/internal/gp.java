// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public final class gp
{
    final gb a;
    final AtomicBoolean b;
    ScheduledFuture c;
    private final Runnable d;
    private final Runnable e;
    
    gp(final gb a) {
        this.b = new AtomicBoolean();
        this.d = new Runnable() {
            @Override
            public final void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        com/tapjoy/internal/gp$1.a:Lcom/tapjoy/internal/gp;
                //     4: getfield        com/tapjoy/internal/gp.b:Ljava/util/concurrent/atomic/AtomicBoolean;
                //     7: iconst_1       
                //     8: iconst_0       
                //     9: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.compareAndSet:(ZZ)Z
                //    12: ifeq            264
                //    15: ldc             "The session ended"
                //    17: invokestatic    com/tapjoy/internal/fz.a:(Ljava/lang/String;)V
                //    20: aload_0        
                //    21: getfield        com/tapjoy/internal/gp$1.a:Lcom/tapjoy/internal/gp;
                //    24: getfield        com/tapjoy/internal/gp.a:Lcom/tapjoy/internal/gb;
                //    27: astore          5
                //    29: invokestatic    android/os/SystemClock.elapsedRealtime:()J
                //    32: aload           5
                //    34: getfield        com/tapjoy/internal/gb.c:J
                //    37: lsub           
                //    38: lstore_1       
                //    39: aload           5
                //    41: getfield        com/tapjoy/internal/gb.a:Lcom/tapjoy/internal/gf;
                //    44: astore          6
                //    46: aload           6
                //    48: monitorenter   
                //    49: aload           6
                //    51: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
                //    54: getfield        com/tapjoy/internal/gm.i:Lcom/tapjoy/internal/n;
                //    57: invokevirtual   com/tapjoy/internal/n.a:()J
                //    60: lload_1        
                //    61: ladd           
                //    62: lstore_3       
                //    63: aload           6
                //    65: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
                //    68: getfield        com/tapjoy/internal/gm.i:Lcom/tapjoy/internal/n;
                //    71: lload_3        
                //    72: invokevirtual   com/tapjoy/internal/n.a:(J)V
                //    75: aload           6
                //    77: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
                //    80: lload_3        
                //    81: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
                //    84: putfield        com/tapjoy/internal/ek$a.i:Ljava/lang/Long;
                //    87: aload           6
                //    89: monitorexit    
                //    90: aload           5
                //    92: getstatic       com/tapjoy/internal/eb.APP:Lcom/tapjoy/internal/eb;
                //    95: ldc             "session"
                //    97: invokevirtual   com/tapjoy/internal/gb.a:(Lcom/tapjoy/internal/eb;Ljava/lang/String;)Lcom/tapjoy/internal/dy$a;
                //   100: astore          7
                //   102: aload           7
                //   104: lload_1        
                //   105: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
                //   108: putfield        com/tapjoy/internal/dy$a.i:Ljava/lang/Long;
                //   111: aload           5
                //   113: aload           7
                //   115: invokevirtual   com/tapjoy/internal/gb.a:(Lcom/tapjoy/internal/dy$a;)V
                //   118: aload           5
                //   120: lconst_0       
                //   121: putfield        com/tapjoy/internal/gb.c:J
                //   124: aload           5
                //   126: getfield        com/tapjoy/internal/gb.a:Lcom/tapjoy/internal/gf;
                //   129: astore          6
                //   131: aload           7
                //   133: getfield        com/tapjoy/internal/dy$a.e:Ljava/lang/Long;
                //   136: invokevirtual   java/lang/Long.longValue:()J
                //   139: lstore_3       
                //   140: aload           6
                //   142: monitorenter   
                //   143: aload           6
                //   145: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
                //   148: invokevirtual   com/tapjoy/internal/gm.a:()Landroid/content/SharedPreferences$Editor;
                //   151: astore          7
                //   153: aload           6
                //   155: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
                //   158: getfield        com/tapjoy/internal/gm.j:Lcom/tapjoy/internal/n;
                //   161: aload           7
                //   163: lload_3        
                //   164: invokevirtual   com/tapjoy/internal/n.a:(Landroid/content/SharedPreferences$Editor;J)Landroid/content/SharedPreferences$Editor;
                //   167: pop            
                //   168: aload           6
                //   170: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
                //   173: getfield        com/tapjoy/internal/gm.k:Lcom/tapjoy/internal/n;
                //   176: aload           7
                //   178: lload_1        
                //   179: invokevirtual   com/tapjoy/internal/n.a:(Landroid/content/SharedPreferences$Editor;J)Landroid/content/SharedPreferences$Editor;
                //   182: pop            
                //   183: aload           7
                //   185: invokeinterface android/content/SharedPreferences$Editor.commit:()Z
                //   190: pop            
                //   191: aload           6
                //   193: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
                //   196: lload_3        
                //   197: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
                //   200: putfield        com/tapjoy/internal/ek$a.j:Ljava/lang/Long;
                //   203: aload           6
                //   205: getfield        com/tapjoy/internal/gf.b:Lcom/tapjoy/internal/ek$a;
                //   208: lload_1        
                //   209: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
                //   212: putfield        com/tapjoy/internal/ek$a.k:Ljava/lang/Long;
                //   215: aload           6
                //   217: monitorexit    
                //   218: aload           5
                //   220: getfield        com/tapjoy/internal/gb.b:Lcom/tapjoy/internal/ga;
                //   223: astore          5
                //   225: aload           5
                //   227: getfield        com/tapjoy/internal/ga.b:Lcom/tapjoy/internal/ci;
                //   230: ifnull          250
                //   233: aload           5
                //   235: invokevirtual   com/tapjoy/internal/ga.a:()V
                //   238: new             Lcom/tapjoy/internal/ga$1;
                //   241: dup            
                //   242: aload           5
                //   244: invokespecial   com/tapjoy/internal/ga$1.<init>:(Lcom/tapjoy/internal/ga;)V
                //   247: invokevirtual   com/tapjoy/internal/ga$1.run:()V
                //   250: aload           5
                //   252: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
                //   255: invokevirtual   com/tapjoy/internal/go.flush:()V
                //   258: getstatic       com/tapjoy/internal/ev.d:Lcom/tapjoy/internal/ev$a;
                //   261: invokevirtual   com/tapjoy/internal/ev$a.notifyObservers:()V
                //   264: return         
                //   265: astore          5
                //   267: aload           6
                //   269: monitorexit    
                //   270: aload           5
                //   272: athrow         
                //   273: astore          5
                //   275: aload           6
                //   277: monitorexit    
                //   278: aload           5
                //   280: athrow         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type
                //  -----  -----  -----  -----  ----
                //  49     90     265    273    Any
                //  143    218    273    281    Any
                //  267    270    265    273    Any
                //  275    278    273    281    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0250:
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
        };
        this.e = new Runnable() {
            @Override
            public final void run() {
            }
        };
        this.a = a;
    }
    
    public final void a() {
        if (this.b.get()) {
            if (!Boolean.FALSE) {
                this.d.run();
                return;
            }
            if (this.c == null || this.c.cancel(false)) {
                this.c = gq.a.schedule(this.d, 3000L, TimeUnit.MILLISECONDS);
            }
        }
    }
}
