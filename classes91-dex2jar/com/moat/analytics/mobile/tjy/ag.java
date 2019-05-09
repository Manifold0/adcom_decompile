// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.functional.a;

class ag implements bc
{
    private static final a a;
    private static final a b;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_0       
        //     4: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:()Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //     7: astore_2       
        //     8: ldc             Lcom/moat/analytics/mobile/tjy/NativeDisplayTracker;.class
        //    10: ldc             "track"
        //    12: iconst_1       
        //    13: anewarray       Ljava/lang/Class;
        //    16: dup            
        //    17: iconst_0       
        //    18: ldc             Ljava/util/Map;.class
        //    20: aastore        
        //    21: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    24: astore_1       
        //    25: ldc             Lcom/moat/analytics/mobile/tjy/NativeDisplayTracker;.class
        //    27: ldc             "stopTracking"
        //    29: iconst_0       
        //    30: anewarray       Ljava/lang/Class;
        //    33: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    36: astore_3       
        //    37: aload_1        
        //    38: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    41: astore_1       
        //    42: aload_1        
        //    43: astore_0       
        //    44: aload_3        
        //    45: invokestatic    com/moat/analytics/mobile/tjy/base/functional/a.a:(Ljava/lang/Object;)Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    48: astore_1       
        //    49: aload_0        
        //    50: putstatic       com/moat/analytics/mobile/tjy/ag.a:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    53: aload_1        
        //    54: putstatic       com/moat/analytics/mobile/tjy/ag.b:Lcom/moat/analytics/mobile/tjy/base/functional/a;
        //    57: return         
        //    58: astore_1       
        //    59: aload_1        
        //    60: invokestatic    com/moat/analytics/mobile/tjy/base/exception/a.a:(Ljava/lang/Exception;)V
        //    63: aload_2        
        //    64: astore_1       
        //    65: goto            49
        //    68: astore_1       
        //    69: goto            59
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  8      42     58     59     Ljava/lang/NoSuchMethodException;
        //  44     49     68     72     Ljava/lang/NoSuchMethodException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0049:
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
        return NativeDisplayTracker.class;
    }
}
