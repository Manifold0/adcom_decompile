// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.File;

public final class ga implements Runnable
{
    final go a;
    ci b;
    private final Object c;
    private final Thread d;
    private boolean e;
    
    public ga(final File file) {
        this.a = new go(file);
        this.c = this.a;
        this.a.b();
        (this.d = new Thread(this, "5Rocks")).start();
    }
    
    public final void a() {
        if (this.b != null && !this.a.c()) {
            this.a(true);
        }
    }
    
    final void a(final boolean e) {
        synchronized (this.c) {
            this.e = e;
            this.c.notify();
        }
    }
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1       
        //     2: lconst_0       
        //     3: lstore_3       
        //     4: aload_0        
        //     5: getfield        com/tapjoy/internal/ga.b:Lcom/tapjoy/internal/ci;
        //     8: ifnull          345
        //    11: aload_0        
        //    12: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //    15: invokevirtual   com/tapjoy/internal/go.b:()I
        //    18: ifle            345
        //    21: lload_3        
        //    22: lconst_0       
        //    23: lcmp           
        //    24: ifgt            345
        //    27: aload_0        
        //    28: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //    31: invokevirtual   com/tapjoy/internal/go.b:()I
        //    34: sipush          10000
        //    37: if_icmple       58
        //    40: aload_0        
        //    41: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //    44: aload_0        
        //    45: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //    48: invokevirtual   com/tapjoy/internal/go.b:()I
        //    51: sipush          10000
        //    54: isub           
        //    55: invokevirtual   com/tapjoy/internal/go.a:(I)V
        //    58: aload_0        
        //    59: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //    62: iconst_0       
        //    63: invokevirtual   com/tapjoy/internal/go.b:(I)Lcom/tapjoy/internal/dy;
        //    66: astore          7
        //    68: aload           7
        //    70: ifnull          345
        //    73: aload           7
        //    75: getfield        com/tapjoy/internal/dy.w:Lcom/tapjoy/internal/ek;
        //    78: astore          8
        //    80: aload           8
        //    82: ifnull          106
        //    85: aload           8
        //    87: getfield        com/tapjoy/internal/ek.G:Ljava/lang/String;
        //    90: ifnonnull       106
        //    93: getstatic       com/tapjoy/internal/gq.c:Ljava/util/concurrent/CountDownLatch;
        //    96: ldc2_w          3
        //    99: getstatic       java/util/concurrent/TimeUnit.SECONDS:Ljava/util/concurrent/TimeUnit;
        //   102: invokevirtual   java/util/concurrent/CountDownLatch.await:(JLjava/util/concurrent/TimeUnit;)Z
        //   105: pop            
        //   106: invokestatic    com/tapjoy/internal/y.c:()Z
        //   109: ifne            125
        //   112: getstatic       com/tapjoy/internal/gq.b:Ljava/util/concurrent/CountDownLatch;
        //   115: ldc2_w          3
        //   118: getstatic       java/util/concurrent/TimeUnit.SECONDS:Ljava/util/concurrent/TimeUnit;
        //   121: invokevirtual   java/util/concurrent/CountDownLatch.await:(JLjava/util/concurrent/TimeUnit;)Z
        //   124: pop            
        //   125: aload_0        
        //   126: getfield        com/tapjoy/internal/ga.e:Z
        //   129: ifne            456
        //   132: aload           7
        //   134: getfield        com/tapjoy/internal/dy.n:Lcom/tapjoy/internal/eb;
        //   137: getstatic       com/tapjoy/internal/eb.APP:Lcom/tapjoy/internal/eb;
        //   140: if_acmpeq       456
        //   143: aload_0        
        //   144: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //   147: invokevirtual   com/tapjoy/internal/go.b:()I
        //   150: bipush          100
        //   152: if_icmpge       456
        //   155: aload           7
        //   157: getfield        com/tapjoy/internal/dy.p:Ljava/lang/Long;
        //   160: invokevirtual   java/lang/Long.longValue:()J
        //   163: invokestatic    java/lang/System.currentTimeMillis:()J
        //   166: lcmp           
        //   167: ifle            251
        //   170: goto            456
        //   173: lload           5
        //   175: lstore_3       
        //   176: lload           5
        //   178: lconst_0       
        //   179: lcmp           
        //   180: ifgt            4
        //   183: new             Lcom/tapjoy/internal/hn;
        //   186: dup            
        //   187: invokespecial   com/tapjoy/internal/hn.<init>:()V
        //   190: astore          8
        //   192: aload           8
        //   194: aload           7
        //   196: invokevirtual   com/tapjoy/internal/hn.a:(Lcom/tapjoy/internal/dy;)Z
        //   199: pop            
        //   200: iconst_1       
        //   201: istore_2       
        //   202: iload_2        
        //   203: bipush          100
        //   205: if_icmpge       282
        //   208: iload_2        
        //   209: aload_0        
        //   210: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //   213: invokevirtual   com/tapjoy/internal/go.b:()I
        //   216: if_icmpge       282
        //   219: aload_0        
        //   220: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //   223: iload_2        
        //   224: invokevirtual   com/tapjoy/internal/go.b:(I)Lcom/tapjoy/internal/dy;
        //   227: astore          7
        //   229: aload           7
        //   231: ifnull          282
        //   234: aload           8
        //   236: aload           7
        //   238: invokevirtual   com/tapjoy/internal/hn.a:(Lcom/tapjoy/internal/dy;)Z
        //   241: ifeq            282
        //   244: iload_2        
        //   245: iconst_1       
        //   246: iadd           
        //   247: istore_2       
        //   248: goto            202
        //   251: aload           7
        //   253: getfield        com/tapjoy/internal/dy.p:Ljava/lang/Long;
        //   256: invokevirtual   java/lang/Long.longValue:()J
        //   259: ldc2_w          60000
        //   262: ladd           
        //   263: invokestatic    java/lang/System.currentTimeMillis:()J
        //   266: lsub           
        //   267: lconst_0       
        //   268: invokestatic    java/lang/Math.max:(JJ)J
        //   271: ldc2_w          60000
        //   274: invokestatic    java/lang/Math.min:(JJ)J
        //   277: lstore          5
        //   279: goto            173
        //   282: iload_1        
        //   283: iconst_1       
        //   284: iadd           
        //   285: istore_1       
        //   286: aload           8
        //   288: invokevirtual   com/tapjoy/internal/hn.g:()I
        //   291: pop            
        //   292: aload_0        
        //   293: getfield        com/tapjoy/internal/ga.b:Lcom/tapjoy/internal/ci;
        //   296: aload           8
        //   298: invokeinterface com/tapjoy/internal/ci.a:(Lcom/tapjoy/internal/cf;)Ljava/lang/Object;
        //   303: pop            
        //   304: aload_0        
        //   305: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //   308: aload           8
        //   310: invokevirtual   com/tapjoy/internal/hn.g:()I
        //   313: invokevirtual   com/tapjoy/internal/go.a:(I)V
        //   316: aload           8
        //   318: invokevirtual   com/tapjoy/internal/hn.g:()I
        //   321: pop            
        //   322: iconst_0       
        //   323: istore_1       
        //   324: lload           5
        //   326: lstore_3       
        //   327: goto            4
        //   330: astore          7
        //   332: aload           8
        //   334: invokevirtual   com/tapjoy/internal/hn.g:()I
        //   337: pop            
        //   338: ldc2_w          300000
        //   341: lstore_3       
        //   342: goto            4
        //   345: aload_0        
        //   346: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //   349: invokevirtual   com/tapjoy/internal/go.flush:()V
        //   352: lload_3        
        //   353: lconst_0       
        //   354: lcmp           
        //   355: ifle            394
        //   358: aload_0        
        //   359: getfield        com/tapjoy/internal/ga.c:Ljava/lang/Object;
        //   362: astore          7
        //   364: aload           7
        //   366: monitorenter   
        //   367: aload_0        
        //   368: iconst_0       
        //   369: putfield        com/tapjoy/internal/ga.e:Z
        //   372: aload_0        
        //   373: getfield        com/tapjoy/internal/ga.c:Ljava/lang/Object;
        //   376: lload_3        
        //   377: invokevirtual   java/lang/Object.wait:(J)V
        //   380: aload           7
        //   382: monitorexit    
        //   383: goto            2
        //   386: astore          8
        //   388: aload           7
        //   390: monitorexit    
        //   391: aload           8
        //   393: athrow         
        //   394: aload_0        
        //   395: getfield        com/tapjoy/internal/ga.c:Ljava/lang/Object;
        //   398: astore          7
        //   400: aload           7
        //   402: monitorenter   
        //   403: aload_0        
        //   404: iconst_0       
        //   405: putfield        com/tapjoy/internal/ga.e:Z
        //   408: aload_0        
        //   409: getfield        com/tapjoy/internal/ga.b:Lcom/tapjoy/internal/ci;
        //   412: ifnull          425
        //   415: aload_0        
        //   416: getfield        com/tapjoy/internal/ga.a:Lcom/tapjoy/internal/go;
        //   419: invokevirtual   com/tapjoy/internal/go.c:()Z
        //   422: ifeq            432
        //   425: aload_0        
        //   426: getfield        com/tapjoy/internal/ga.c:Ljava/lang/Object;
        //   429: invokevirtual   java/lang/Object.wait:()V
        //   432: aload           7
        //   434: monitorexit    
        //   435: goto            2
        //   438: astore          8
        //   440: aload           7
        //   442: monitorexit    
        //   443: aload           8
        //   445: athrow         
        //   446: astore          7
        //   448: return         
        //   449: astore          7
        //   451: iconst_0       
        //   452: istore_1       
        //   453: goto            332
        //   456: lconst_0       
        //   457: lstore          5
        //   459: goto            173
        //   462: astore          7
        //   464: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  4      21     462    465    Ljava/lang/InterruptedException;
        //  4      21     446    449    Ljava/lang/Exception;
        //  27     58     462    465    Ljava/lang/InterruptedException;
        //  27     58     446    449    Ljava/lang/Exception;
        //  58     68     462    465    Ljava/lang/InterruptedException;
        //  58     68     446    449    Ljava/lang/Exception;
        //  73     80     462    465    Ljava/lang/InterruptedException;
        //  73     80     446    449    Ljava/lang/Exception;
        //  85     106    462    465    Ljava/lang/InterruptedException;
        //  85     106    446    449    Ljava/lang/Exception;
        //  106    125    462    465    Ljava/lang/InterruptedException;
        //  106    125    446    449    Ljava/lang/Exception;
        //  125    170    462    465    Ljava/lang/InterruptedException;
        //  125    170    446    449    Ljava/lang/Exception;
        //  183    200    462    465    Ljava/lang/InterruptedException;
        //  183    200    446    449    Ljava/lang/Exception;
        //  208    229    462    465    Ljava/lang/InterruptedException;
        //  208    229    446    449    Ljava/lang/Exception;
        //  234    244    462    465    Ljava/lang/InterruptedException;
        //  234    244    446    449    Ljava/lang/Exception;
        //  251    279    462    465    Ljava/lang/InterruptedException;
        //  251    279    446    449    Ljava/lang/Exception;
        //  286    316    330    332    Ljava/lang/Exception;
        //  286    316    462    465    Ljava/lang/InterruptedException;
        //  316    322    449    456    Ljava/lang/Exception;
        //  316    322    462    465    Ljava/lang/InterruptedException;
        //  332    338    462    465    Ljava/lang/InterruptedException;
        //  332    338    446    449    Ljava/lang/Exception;
        //  345    352    462    465    Ljava/lang/InterruptedException;
        //  345    352    446    449    Ljava/lang/Exception;
        //  358    367    462    465    Ljava/lang/InterruptedException;
        //  358    367    446    449    Ljava/lang/Exception;
        //  367    383    386    394    Any
        //  388    391    386    394    Any
        //  391    394    462    465    Ljava/lang/InterruptedException;
        //  391    394    446    449    Ljava/lang/Exception;
        //  394    403    462    465    Ljava/lang/InterruptedException;
        //  394    403    446    449    Ljava/lang/Exception;
        //  403    425    438    446    Any
        //  425    432    438    446    Any
        //  432    435    438    446    Any
        //  440    443    438    446    Any
        //  443    446    462    465    Ljava/lang/InterruptedException;
        //  443    446    446    449    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0345:
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
