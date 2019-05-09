// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;

class ai implements bc
{
    private static final a a;
    private static final a b;
    private static final a c;
    private static final a d;
    private static final a e;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1       
        //     4: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:()Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //     7: astore          4
        //     9: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:()Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    12: astore          5
        //    14: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:()Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    17: astore          6
        //    19: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:()Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    22: astore          7
        //    24: aload           5
        //    26: astore_0       
        //    27: aload           4
        //    29: astore_2       
        //    30: aload_1        
        //    31: astore_3       
        //    32: ldc             Lcom/moat/analytics/mobile/tjy/NativeVideoTracker;.class
        //    34: ldc             "setDebug"
        //    36: iconst_1       
        //    37: anewarray       Ljava/lang/Class;
        //    40: dup            
        //    41: iconst_0       
        //    42: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    45: aastore        
        //    46: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    49: astore          12
        //    51: aload           5
        //    53: astore_0       
        //    54: aload           4
        //    56: astore_2       
        //    57: aload_1        
        //    58: astore_3       
        //    59: ldc             Lcom/moat/analytics/mobile/tjy/NativeVideoTracker;.class
        //    61: ldc             "trackVideoAd"
        //    63: iconst_3       
        //    64: anewarray       Ljava/lang/Class;
        //    67: dup            
        //    68: iconst_0       
        //    69: ldc             Ljava/util/Map;.class
        //    71: aastore        
        //    72: dup            
        //    73: iconst_1       
        //    74: ldc             Landroid/media/MediaPlayer;.class
        //    76: aastore        
        //    77: dup            
        //    78: iconst_2       
        //    79: ldc             Landroid/view/View;.class
        //    81: aastore        
        //    82: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    85: astore          11
        //    87: aload           5
        //    89: astore_0       
        //    90: aload           4
        //    92: astore_2       
        //    93: aload_1        
        //    94: astore_3       
        //    95: ldc             Lcom/moat/analytics/mobile/tjy/NativeVideoTracker;.class
        //    97: ldc             "changeTargetView"
        //    99: iconst_1       
        //   100: anewarray       Ljava/lang/Class;
        //   103: dup            
        //   104: iconst_0       
        //   105: ldc             Landroid/view/View;.class
        //   107: aastore        
        //   108: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   111: astore          10
        //   113: aload           5
        //   115: astore_0       
        //   116: aload           4
        //   118: astore_2       
        //   119: aload_1        
        //   120: astore_3       
        //   121: ldc             Lcom/moat/analytics/mobile/tjy/NativeVideoTracker;.class
        //   123: ldc             "dispatchEvent"
        //   125: iconst_1       
        //   126: anewarray       Ljava/lang/Class;
        //   129: dup            
        //   130: iconst_0       
        //   131: ldc             Ljava/util/Map;.class
        //   133: aastore        
        //   134: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   137: astore          8
        //   139: aload           5
        //   141: astore_0       
        //   142: aload           4
        //   144: astore_2       
        //   145: aload_1        
        //   146: astore_3       
        //   147: ldc             Lcom/moat/analytics/mobile/tjy/NativeVideoTracker;.class
        //   149: ldc             "dispatchEvent"
        //   151: iconst_1       
        //   152: anewarray       Ljava/lang/Class;
        //   155: dup            
        //   156: iconst_0       
        //   157: ldc             Ljava/util/Map;.class
        //   159: aastore        
        //   160: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   163: astore          9
        //   165: aload           5
        //   167: astore_0       
        //   168: aload           4
        //   170: astore_2       
        //   171: aload_1        
        //   172: astore_3       
        //   173: aload           12
        //   175: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   178: astore_1       
        //   179: aload           5
        //   181: astore_0       
        //   182: aload           4
        //   184: astore_2       
        //   185: aload_1        
        //   186: astore_3       
        //   187: aload           11
        //   189: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   192: astore          4
        //   194: aload           5
        //   196: astore_0       
        //   197: aload           4
        //   199: astore_2       
        //   200: aload_1        
        //   201: astore_3       
        //   202: aload           10
        //   204: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   207: astore          5
        //   209: aload           5
        //   211: astore_0       
        //   212: aload           4
        //   214: astore_2       
        //   215: aload_1        
        //   216: astore_3       
        //   217: aload           8
        //   219: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   222: astore          8
        //   224: aload           8
        //   226: astore_0       
        //   227: aload           9
        //   229: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   232: astore_3       
        //   233: aload           4
        //   235: astore_2       
        //   236: aload_3        
        //   237: astore          4
        //   239: aload_0        
        //   240: astore_3       
        //   241: aload_1        
        //   242: putstatic       com/moat/analytics/mobile/tjy/ai.a:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   245: aload_2        
        //   246: putstatic       com/moat/analytics/mobile/tjy/ai.b:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   249: aload           5
        //   251: putstatic       com/moat/analytics/mobile/tjy/ai.c:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   254: aload_3        
        //   255: putstatic       com/moat/analytics/mobile/tjy/ai.d:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   258: aload           4
        //   260: putstatic       com/moat/analytics/mobile/tjy/ai.e:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   263: return         
        //   264: astore          5
        //   266: aload           7
        //   268: astore          4
        //   270: aload_3        
        //   271: astore_1       
        //   272: aload           4
        //   274: astore_3       
        //   275: aload           5
        //   277: invokestatic    com/moat/analytics/mobile/tjy/base/exception/a.a:(Ljava/lang/Exception;)V
        //   280: aload           6
        //   282: astore          4
        //   284: aload_0        
        //   285: astore          5
        //   287: goto            241
        //   290: astore          7
        //   292: aload_0        
        //   293: astore_3       
        //   294: aload           5
        //   296: astore_0       
        //   297: aload           4
        //   299: astore_2       
        //   300: aload           7
        //   302: astore          5
        //   304: goto            275
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  32     51     264    275    Ljava/lang/NoSuchMethodException;
        //  59     87     264    275    Ljava/lang/NoSuchMethodException;
        //  95     113    264    275    Ljava/lang/NoSuchMethodException;
        //  121    139    264    275    Ljava/lang/NoSuchMethodException;
        //  147    165    264    275    Ljava/lang/NoSuchMethodException;
        //  173    179    264    275    Ljava/lang/NoSuchMethodException;
        //  187    194    264    275    Ljava/lang/NoSuchMethodException;
        //  202    209    264    275    Ljava/lang/NoSuchMethodException;
        //  217    224    264    275    Ljava/lang/NoSuchMethodException;
        //  227    233    290    307    Ljava/lang/NoSuchMethodException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0241:
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
    
    @Override
    public Class a() {
        return NativeVideoTracker.class;
    }
}
