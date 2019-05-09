// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;

class be implements bc
{
    private static final a a;
    private static final a b;
    private static final a c;
    private static final a d;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1       
        //     4: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:()Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //     7: astore_3       
        //     8: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:()Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    11: astore          4
        //    13: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:()Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    16: astore          5
        //    18: aload_3        
        //    19: astore_0       
        //    20: aload_1        
        //    21: astore_2       
        //    22: ldc             Lcom/moat/analytics/mobile/tjy/ReactiveVideoTracker;.class
        //    24: ldc             "setDebug"
        //    26: iconst_1       
        //    27: anewarray       Ljava/lang/Class;
        //    30: dup            
        //    31: iconst_0       
        //    32: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    35: aastore        
        //    36: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    39: astore          9
        //    41: aload_3        
        //    42: astore_0       
        //    43: aload_1        
        //    44: astore_2       
        //    45: ldc             Lcom/moat/analytics/mobile/tjy/ReactiveVideoTracker;.class
        //    47: ldc             "trackVideoAd"
        //    49: iconst_3       
        //    50: anewarray       Ljava/lang/Class;
        //    53: dup            
        //    54: iconst_0       
        //    55: ldc             Ljava/util/Map;.class
        //    57: aastore        
        //    58: dup            
        //    59: iconst_1       
        //    60: ldc             Landroid/view/View;.class
        //    62: aastore        
        //    63: dup            
        //    64: iconst_2       
        //    65: ldc             Landroid/view/View;.class
        //    67: aastore        
        //    68: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    71: astore          8
        //    73: aload_3        
        //    74: astore_0       
        //    75: aload_1        
        //    76: astore_2       
        //    77: ldc             Lcom/moat/analytics/mobile/tjy/ReactiveVideoTracker;.class
        //    79: ldc             "changeTargetView"
        //    81: iconst_1       
        //    82: anewarray       Ljava/lang/Class;
        //    85: dup            
        //    86: iconst_0       
        //    87: ldc             Landroid/view/View;.class
        //    89: aastore        
        //    90: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    93: astore          6
        //    95: aload_3        
        //    96: astore_0       
        //    97: aload_1        
        //    98: astore_2       
        //    99: ldc             Lcom/moat/analytics/mobile/tjy/ReactiveVideoTracker;.class
        //   101: ldc             "dispatchEvent"
        //   103: iconst_1       
        //   104: anewarray       Ljava/lang/Class;
        //   107: dup            
        //   108: iconst_0       
        //   109: ldc             Lcom/moat/analytics/mobile/tjy/MoatAdEvent;.class
        //   111: aastore        
        //   112: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //   115: astore          7
        //   117: aload_3        
        //   118: astore_0       
        //   119: aload_1        
        //   120: astore_2       
        //   121: aload           9
        //   123: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   126: astore_1       
        //   127: aload_3        
        //   128: astore_0       
        //   129: aload_1        
        //   130: astore_2       
        //   131: aload           8
        //   133: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   136: astore_3       
        //   137: aload_3        
        //   138: astore_0       
        //   139: aload_1        
        //   140: astore_2       
        //   141: aload           6
        //   143: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   146: astore          6
        //   148: aload           6
        //   150: astore_0       
        //   151: aload           7
        //   153: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   156: astore_2       
        //   157: aload_3        
        //   158: astore          5
        //   160: aload_0        
        //   161: astore          4
        //   163: aload_2        
        //   164: astore_3       
        //   165: aload_1        
        //   166: putstatic       com/moat/analytics/mobile/tjy/be.a:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   169: aload           5
        //   171: putstatic       com/moat/analytics/mobile/tjy/be.b:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   174: aload           4
        //   176: putstatic       com/moat/analytics/mobile/tjy/be.c:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   179: aload_3        
        //   180: putstatic       com/moat/analytics/mobile/tjy/be.d:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //   183: return         
        //   184: astore_1       
        //   185: aload           4
        //   187: astore_3       
        //   188: aload_1        
        //   189: astore          4
        //   191: aload_2        
        //   192: astore_1       
        //   193: aload_3        
        //   194: astore_2       
        //   195: aload           4
        //   197: invokestatic    com/moat/analytics/mobile/tjy/base/exception/a.a:(Ljava/lang/Exception;)V
        //   200: aload           5
        //   202: astore_3       
        //   203: aload_2        
        //   204: astore          4
        //   206: aload_0        
        //   207: astore          5
        //   209: goto            165
        //   212: astore          4
        //   214: aload_0        
        //   215: astore_2       
        //   216: aload_3        
        //   217: astore_0       
        //   218: goto            195
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  22     41     184    195    Ljava/lang/NoSuchMethodException;
        //  45     73     184    195    Ljava/lang/NoSuchMethodException;
        //  77     95     184    195    Ljava/lang/NoSuchMethodException;
        //  99     117    184    195    Ljava/lang/NoSuchMethodException;
        //  121    127    184    195    Ljava/lang/NoSuchMethodException;
        //  131    137    184    195    Ljava/lang/NoSuchMethodException;
        //  141    148    184    195    Ljava/lang/NoSuchMethodException;
        //  151    157    212    221    Ljava/lang/NoSuchMethodException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0165:
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
        return ReactiveVideoTracker.class;
    }
}
