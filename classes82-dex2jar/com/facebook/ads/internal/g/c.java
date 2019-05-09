// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.g;

import android.content.ContentResolver;

public class c
{
    public static a a(final ContentResolver p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "content://com.facebook.katana.provider.AttributionIdProvider"
        //     3: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //     6: iconst_3       
        //     7: anewarray       Ljava/lang/String;
        //    10: dup            
        //    11: iconst_0       
        //    12: ldc             "aid"
        //    14: aastore        
        //    15: dup            
        //    16: iconst_1       
        //    17: ldc             "androidid"
        //    19: aastore        
        //    20: dup            
        //    21: iconst_2       
        //    22: ldc             "limit_tracking"
        //    24: aastore        
        //    25: aconst_null    
        //    26: aconst_null    
        //    27: aconst_null    
        //    28: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    31: astore_1       
        //    32: aload_1        
        //    33: ifnull          47
        //    36: aload_1        
        //    37: astore_0       
        //    38: aload_1        
        //    39: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    44: ifne            76
        //    47: aload_1        
        //    48: astore_0       
        //    49: new             Lcom/facebook/ads/internal/g/c$a;
        //    52: dup            
        //    53: aconst_null    
        //    54: aconst_null    
        //    55: iconst_0       
        //    56: invokespecial   com/facebook/ads/internal/g/c$a.<init>:(Ljava/lang/String;Ljava/lang/String;Z)V
        //    59: astore_2       
        //    60: aload_2        
        //    61: astore_0       
        //    62: aload_1        
        //    63: ifnull          74
        //    66: aload_1        
        //    67: invokeinterface android/database/Cursor.close:()V
        //    72: aload_2        
        //    73: astore_0       
        //    74: aload_0        
        //    75: areturn        
        //    76: aload_1        
        //    77: astore_0       
        //    78: new             Lcom/facebook/ads/internal/g/c$a;
        //    81: dup            
        //    82: aload_1        
        //    83: aload_1        
        //    84: ldc             "aid"
        //    86: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //    91: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    96: aload_1        
        //    97: aload_1        
        //    98: ldc             "androidid"
        //   100: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   105: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   110: aload_1        
        //   111: aload_1        
        //   112: ldc             "limit_tracking"
        //   114: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   119: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   124: invokestatic    java/lang/Boolean.valueOf:(Ljava/lang/String;)Ljava/lang/Boolean;
        //   127: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   130: invokespecial   com/facebook/ads/internal/g/c$a.<init>:(Ljava/lang/String;Ljava/lang/String;Z)V
        //   133: astore_2       
        //   134: aload_2        
        //   135: astore_0       
        //   136: aload_1        
        //   137: ifnull          74
        //   140: aload_1        
        //   141: invokeinterface android/database/Cursor.close:()V
        //   146: aload_2        
        //   147: areturn        
        //   148: astore_0       
        //   149: aconst_null    
        //   150: astore_1       
        //   151: aload_1        
        //   152: astore_0       
        //   153: new             Lcom/facebook/ads/internal/g/c$a;
        //   156: dup            
        //   157: aconst_null    
        //   158: aconst_null    
        //   159: iconst_0       
        //   160: invokespecial   com/facebook/ads/internal/g/c$a.<init>:(Ljava/lang/String;Ljava/lang/String;Z)V
        //   163: astore_2       
        //   164: aload_2        
        //   165: astore_0       
        //   166: aload_1        
        //   167: ifnull          74
        //   170: aload_1        
        //   171: invokeinterface android/database/Cursor.close:()V
        //   176: aload_2        
        //   177: areturn        
        //   178: astore_1       
        //   179: aconst_null    
        //   180: astore_0       
        //   181: aload_0        
        //   182: ifnull          191
        //   185: aload_0        
        //   186: invokeinterface android/database/Cursor.close:()V
        //   191: aload_1        
        //   192: athrow         
        //   193: astore_1       
        //   194: goto            181
        //   197: astore_0       
        //   198: goto            151
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      32     148    151    Ljava/lang/Exception;
        //  0      32     178    181    Any
        //  38     47     197    201    Ljava/lang/Exception;
        //  38     47     193    197    Any
        //  49     60     197    201    Ljava/lang/Exception;
        //  49     60     193    197    Any
        //  78     134    197    201    Ljava/lang/Exception;
        //  78     134    193    197    Any
        //  153    164    193    197    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0047:
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
    
    public static class a
    {
        public String a;
        public String b;
        public boolean c;
        
        public a(final String a, final String b, final boolean c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
