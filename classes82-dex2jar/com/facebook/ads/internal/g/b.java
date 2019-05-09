// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.g;

import android.content.Context;
import android.support.annotation.WorkerThread;

@WorkerThread
public class b
{
    public static String a;
    public static String b;
    public static boolean c;
    public static String d;
    
    static {
        com.facebook.ads.internal.g.b.a = "";
        com.facebook.ads.internal.g.b.b = "";
        com.facebook.ads.internal.g.b.c = false;
        com.facebook.ads.internal.g.b.d = "";
    }
    
    public static void a(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "SDKIDFA"
        //     3: aload_0        
        //     4: invokestatic    com/facebook/ads/internal/w/f/a.a:(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
        //     7: iconst_0       
        //     8: invokevirtual   android/content/Context.getSharedPreferences:(Ljava/lang/String;I)Landroid/content/SharedPreferences;
        //    11: astore_3       
        //    12: aload_3        
        //    13: ldc             "attributionId"
        //    15: invokeinterface android/content/SharedPreferences.contains:(Ljava/lang/String;)Z
        //    20: ifeq            36
        //    23: aload_3        
        //    24: ldc             "attributionId"
        //    26: ldc             ""
        //    28: invokeinterface android/content/SharedPreferences.getString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    33: putstatic       com/facebook/ads/internal/g/b.a:Ljava/lang/String;
        //    36: aload_3        
        //    37: ldc             "advertisingId"
        //    39: invokeinterface android/content/SharedPreferences.contains:(Ljava/lang/String;)Z
        //    44: ifeq            83
        //    47: aload_3        
        //    48: ldc             "advertisingId"
        //    50: ldc             ""
        //    52: invokeinterface android/content/SharedPreferences.getString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    57: putstatic       com/facebook/ads/internal/g/b.b:Ljava/lang/String;
        //    60: aload_3        
        //    61: ldc             "limitAdTracking"
        //    63: getstatic       com/facebook/ads/internal/g/b.c:Z
        //    66: invokeinterface android/content/SharedPreferences.getBoolean:(Ljava/lang/String;Z)Z
        //    71: putstatic       com/facebook/ads/internal/g/b.c:Z
        //    74: getstatic       com/facebook/ads/internal/g/a$c.a:Lcom/facebook/ads/internal/g/a$c;
        //    77: invokevirtual   com/facebook/ads/internal/g/a$c.name:()Ljava/lang/String;
        //    80: putstatic       com/facebook/ads/internal/g/b.d:Ljava/lang/String;
        //    83: aload_0        
        //    84: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //    87: invokestatic    com/facebook/ads/internal/g/c.a:(Landroid/content/ContentResolver;)Lcom/facebook/ads/internal/g/c$a;
        //    90: astore_2       
        //    91: aload_2        
        //    92: ifnull          109
        //    95: aload_2        
        //    96: getfield        com/facebook/ads/internal/g/c$a.a:Ljava/lang/String;
        //    99: ifnull          109
        //   102: aload_2        
        //   103: getfield        com/facebook/ads/internal/g/c$a.a:Ljava/lang/String;
        //   106: putstatic       com/facebook/ads/internal/g/b.a:Ljava/lang/String;
        //   109: invokestatic    com/facebook/ads/internal/w/b/a.a:()Z
        //   112: ifeq            131
        //   115: ldc             "aid_override"
        //   117: invokestatic    com/facebook/ads/internal/w/b/a.b:(Ljava/lang/String;)Z
        //   120: ifeq            131
        //   123: ldc             "aid_override"
        //   125: invokestatic    com/facebook/ads/internal/w/b/a.a:(Ljava/lang/String;)Ljava/lang/String;
        //   128: putstatic       com/facebook/ads/internal/g/b.a:Ljava/lang/String;
        //   131: aload_0        
        //   132: aload_2        
        //   133: invokestatic    com/facebook/ads/internal/g/a.a:(Landroid/content/Context;Lcom/facebook/ads/internal/g/c$a;)Lcom/facebook/ads/internal/g/a;
        //   136: astore_0       
        //   137: aload_0        
        //   138: ifnull          179
        //   141: aload_0        
        //   142: invokevirtual   com/facebook/ads/internal/g/a.a:()Ljava/lang/String;
        //   145: astore_2       
        //   146: aload_0        
        //   147: invokevirtual   com/facebook/ads/internal/g/a.b:()Z
        //   150: istore_1       
        //   151: aload_2        
        //   152: ifnull          179
        //   155: aload_2        
        //   156: putstatic       com/facebook/ads/internal/g/b.b:Ljava/lang/String;
        //   159: iload_1        
        //   160: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   163: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   166: putstatic       com/facebook/ads/internal/g/b.c:Z
        //   169: aload_0        
        //   170: invokevirtual   com/facebook/ads/internal/g/a.c:()Lcom/facebook/ads/internal/g/a$c;
        //   173: invokevirtual   com/facebook/ads/internal/g/a$c.name:()Ljava/lang/String;
        //   176: putstatic       com/facebook/ads/internal/g/b.d:Ljava/lang/String;
        //   179: aload_3        
        //   180: invokeinterface android/content/SharedPreferences.edit:()Landroid/content/SharedPreferences$Editor;
        //   185: astore_0       
        //   186: aload_0        
        //   187: ldc             "attributionId"
        //   189: getstatic       com/facebook/ads/internal/g/b.a:Ljava/lang/String;
        //   192: invokeinterface android/content/SharedPreferences$Editor.putString:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
        //   197: pop            
        //   198: aload_0        
        //   199: ldc             "advertisingId"
        //   201: getstatic       com/facebook/ads/internal/g/b.b:Ljava/lang/String;
        //   204: invokeinterface android/content/SharedPreferences$Editor.putString:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
        //   209: pop            
        //   210: aload_0        
        //   211: ldc             "limitAdTracking"
        //   213: getstatic       com/facebook/ads/internal/g/b.c:Z
        //   216: invokeinterface android/content/SharedPreferences$Editor.putBoolean:(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;
        //   221: pop            
        //   222: aload_0        
        //   223: invokeinterface android/content/SharedPreferences$Editor.apply:()V
        //   228: return         
        //   229: astore_2       
        //   230: aload_2        
        //   231: ldc             "Error retrieving attribution id from fb4a"
        //   233: invokestatic    com/facebook/ads/internal/o/a.a:(Ljava/lang/Throwable;Ljava/lang/String;)Lcom/facebook/ads/internal/o/a;
        //   236: invokestatic    com/facebook/ads/internal/o/b.a:(Lcom/facebook/ads/internal/o/a;)V
        //   239: aconst_null    
        //   240: astore_2       
        //   241: goto            91
        //   244: astore_0       
        //   245: aload_0        
        //   246: ldc             "Error retrieving advertising id from Google Play Services"
        //   248: invokestatic    com/facebook/ads/internal/o/a.a:(Ljava/lang/Throwable;Ljava/lang/String;)Lcom/facebook/ads/internal/o/a;
        //   251: invokestatic    com/facebook/ads/internal/o/b.a:(Lcom/facebook/ads/internal/o/a;)V
        //   254: aconst_null    
        //   255: astore_0       
        //   256: goto            137
        //   259: astore_0       
        //   260: aload_0        
        //   261: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   264: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      36     259    265    Ljava/lang/Exception;
        //  36     83     259    265    Ljava/lang/Exception;
        //  83     91     229    244    Ljava/lang/Exception;
        //  95     109    259    265    Ljava/lang/Exception;
        //  109    131    259    265    Ljava/lang/Exception;
        //  131    137    244    259    Ljava/lang/Exception;
        //  141    151    259    265    Ljava/lang/Exception;
        //  155    179    259    265    Ljava/lang/Exception;
        //  179    228    259    265    Ljava/lang/Exception;
        //  230    239    259    265    Ljava/lang/Exception;
        //  245    254    259    265    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
