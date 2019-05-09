// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class fn
{
    public long a;
    public long b;
    public long c;
    
    private static long a(final byte[] array, int n) {
        final int n2 = array[n];
        final byte b = array[n + 1];
        final byte b2 = array[n + 2];
        final byte b3 = array[n + 3];
        n = n2;
        if ((n2 & 0x80) == 0x80) {
            n = (n2 & 0x7F) + 128;
        }
        int n3 = b;
        if ((b & 0x80) == 0x80) {
            n3 = (b & 0x7F) + 128;
        }
        int n4 = b2;
        if ((b2 & 0x80) == 0x80) {
            n4 = (b2 & 0x7F) + 128;
        }
        int n5 = b3;
        if ((b3 & 0x80) == 0x80) {
            n5 = (b3 & 0x7F) + 128;
        }
        return n5 + (((long)n3 << 16) + ((long)n << 24) + ((long)n4 << 8));
    }
    
    private static long b(final byte[] array, final int n) {
        return (a(array, n) - 2208988800L) * 1000L + a(array, n + 4) * 1000L / 4294967296L;
    }
    
    public final boolean a(final String p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          16
        //     3: new             Ljava/net/DatagramSocket;
        //     6: dup            
        //     7: invokespecial   java/net/DatagramSocket.<init>:()V
        //    10: astore          15
        //    12: aload           15
        //    14: iload_2        
        //    15: invokevirtual   java/net/DatagramSocket.setSoTimeout:(I)V
        //    18: aload_1        
        //    19: invokestatic    java/net/InetAddress.getByName:(Ljava/lang/String;)Ljava/net/InetAddress;
        //    22: astore          16
        //    24: bipush          48
        //    26: newarray        B
        //    28: astore_1       
        //    29: new             Ljava/net/DatagramPacket;
        //    32: dup            
        //    33: aload_1        
        //    34: bipush          48
        //    36: aload           16
        //    38: bipush          123
        //    40: invokespecial   java/net/DatagramPacket.<init>:([BILjava/net/InetAddress;I)V
        //    43: astore          16
        //    45: aload_1        
        //    46: iconst_0       
        //    47: bipush          27
        //    49: bastore        
        //    50: invokestatic    java/lang/System.currentTimeMillis:()J
        //    53: lstore          7
        //    55: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    58: lstore_3       
        //    59: lload           7
        //    61: ldc2_w          1000
        //    64: ldiv           
        //    65: lstore          5
        //    67: lload           5
        //    69: ldc2_w          2208988800
        //    72: ladd           
        //    73: lstore          9
        //    75: aload_1        
        //    76: bipush          40
        //    78: lload           9
        //    80: bipush          24
        //    82: lshr           
        //    83: l2i            
        //    84: i2b            
        //    85: bastore        
        //    86: aload_1        
        //    87: bipush          41
        //    89: lload           9
        //    91: bipush          16
        //    93: lshr           
        //    94: l2i            
        //    95: i2b            
        //    96: bastore        
        //    97: aload_1        
        //    98: bipush          42
        //   100: lload           9
        //   102: bipush          8
        //   104: lshr           
        //   105: l2i            
        //   106: i2b            
        //   107: bastore        
        //   108: aload_1        
        //   109: bipush          43
        //   111: lload           9
        //   113: iconst_0       
        //   114: lshr           
        //   115: l2i            
        //   116: i2b            
        //   117: bastore        
        //   118: ldc2_w          4294967296
        //   121: lload           7
        //   123: ldc2_w          1000
        //   126: lload           5
        //   128: lmul           
        //   129: lsub           
        //   130: lmul           
        //   131: ldc2_w          1000
        //   134: ldiv           
        //   135: lstore          5
        //   137: aload_1        
        //   138: bipush          44
        //   140: lload           5
        //   142: bipush          24
        //   144: lshr           
        //   145: l2i            
        //   146: i2b            
        //   147: bastore        
        //   148: aload_1        
        //   149: bipush          45
        //   151: lload           5
        //   153: bipush          16
        //   155: lshr           
        //   156: l2i            
        //   157: i2b            
        //   158: bastore        
        //   159: aload_1        
        //   160: bipush          46
        //   162: lload           5
        //   164: bipush          8
        //   166: lshr           
        //   167: l2i            
        //   168: i2b            
        //   169: bastore        
        //   170: aload_1        
        //   171: bipush          47
        //   173: invokestatic    java/lang/Math.random:()D
        //   176: ldc2_w          255.0
        //   179: dmul           
        //   180: d2i            
        //   181: i2b            
        //   182: bastore        
        //   183: aload           15
        //   185: aload           16
        //   187: invokevirtual   java/net/DatagramSocket.send:(Ljava/net/DatagramPacket;)V
        //   190: aload           15
        //   192: new             Ljava/net/DatagramPacket;
        //   195: dup            
        //   196: aload_1        
        //   197: bipush          48
        //   199: invokespecial   java/net/DatagramPacket.<init>:([BI)V
        //   202: invokevirtual   java/net/DatagramSocket.receive:(Ljava/net/DatagramPacket;)V
        //   205: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   208: lstore          5
        //   210: lload           7
        //   212: lload           5
        //   214: lload_3        
        //   215: lsub           
        //   216: ladd           
        //   217: lstore          7
        //   219: aload_1        
        //   220: bipush          24
        //   222: invokestatic    com/tapjoy/internal/fn.b:([BI)J
        //   225: lstore          9
        //   227: aload_1        
        //   228: bipush          32
        //   230: invokestatic    com/tapjoy/internal/fn.b:([BI)J
        //   233: lstore          11
        //   235: aload_1        
        //   236: bipush          40
        //   238: invokestatic    com/tapjoy/internal/fn.b:([BI)J
        //   241: lstore          13
        //   243: aload_0        
        //   244: lload           13
        //   246: lload           7
        //   248: lsub           
        //   249: lload           11
        //   251: lload           9
        //   253: lsub           
        //   254: ladd           
        //   255: ldc2_w          2
        //   258: ldiv           
        //   259: lload           7
        //   261: ladd           
        //   262: putfield        com/tapjoy/internal/fn.a:J
        //   265: aload_0        
        //   266: lload           5
        //   268: putfield        com/tapjoy/internal/fn.b:J
        //   271: aload_0        
        //   272: lload           5
        //   274: lload_3        
        //   275: lsub           
        //   276: lload           13
        //   278: lload           11
        //   280: lsub           
        //   281: lsub           
        //   282: putfield        com/tapjoy/internal/fn.c:J
        //   285: aload           15
        //   287: invokevirtual   java/net/DatagramSocket.close:()V
        //   290: iconst_1       
        //   291: ireturn        
        //   292: astore_1       
        //   293: aconst_null    
        //   294: astore          15
        //   296: aload           15
        //   298: ifnull          306
        //   301: aload           15
        //   303: invokevirtual   java/net/DatagramSocket.close:()V
        //   306: iconst_0       
        //   307: ireturn        
        //   308: astore_1       
        //   309: aload           16
        //   311: astore          15
        //   313: aload           15
        //   315: ifnull          323
        //   318: aload           15
        //   320: invokevirtual   java/net/DatagramSocket.close:()V
        //   323: aload_1        
        //   324: athrow         
        //   325: astore_1       
        //   326: goto            313
        //   329: astore_1       
        //   330: goto            296
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      12     292    296    Ljava/lang/Exception;
        //  3      12     308    313    Any
        //  12     45     329    333    Ljava/lang/Exception;
        //  12     45     325    329    Any
        //  50     67     329    333    Ljava/lang/Exception;
        //  50     67     325    329    Any
        //  118    137    329    333    Ljava/lang/Exception;
        //  118    137    325    329    Any
        //  170    210    329    333    Ljava/lang/Exception;
        //  170    210    325    329    Any
        //  219    285    329    333    Ljava/lang/Exception;
        //  219    285    325    329    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0296:
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
