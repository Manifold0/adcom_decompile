// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.j;

import android.database.SQLException;
import android.database.Cursor;
import android.support.annotation.WorkerThread;

public class h extends g
{
    public static final b a;
    public static final b b;
    public static final b[] c;
    private static final String d;
    private static final String e;
    private static final String f;
    private static final String g;
    
    static {
        a = new b(0, "token_id", "TEXT PRIMARY KEY");
        b = new b(1, "token", "TEXT");
        c = new b[] { h.a, h.b };
        d = h.class.getSimpleName();
        e = com.facebook.ads.internal.j.g.a("tokens", h.c);
        final b[] c2 = h.c;
        final b b2 = h.b;
        final StringBuilder sb = new StringBuilder(com.facebook.ads.internal.j.g.a("tokens", c2));
        sb.append(" WHERE ");
        sb.append(b2.b);
        sb.append(" = ?");
        f = sb.toString();
        g = "DELETE FROM tokens WHERE NOT EXISTS (SELECT 1 FROM events WHERE tokens." + h.a.b + " = " + "events" + "." + com.facebook.ads.internal.j.c.b.b + ")";
    }
    
    public h(final d d) {
        super(d);
    }
    
    @Override
    public String a() {
        return "tokens";
    }
    
    @WorkerThread
    String a(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: ifeq            17
        //     7: new             Ljava/lang/IllegalArgumentException;
        //    10: dup            
        //    11: ldc             "Invalid token."
        //    13: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    16: athrow         
        //    17: aload_0        
        //    18: invokevirtual   com/facebook/ads/internal/j/h.f:()Landroid/database/sqlite/SQLiteDatabase;
        //    21: getstatic       com/facebook/ads/internal/j/h.f:Ljava/lang/String;
        //    24: iconst_1       
        //    25: anewarray       Ljava/lang/String;
        //    28: dup            
        //    29: iconst_0       
        //    30: aload_1        
        //    31: aastore        
        //    32: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //    35: astore          4
        //    37: aload           4
        //    39: invokeinterface android/database/Cursor.moveToNext:()Z
        //    44: ifeq            88
        //    47: aload           4
        //    49: getstatic       com/facebook/ads/internal/j/h.a:Lcom/facebook/ads/internal/j/b;
        //    52: getfield        com/facebook/ads/internal/j/b.a:I
        //    55: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    60: astore_3       
        //    61: aload_3        
        //    62: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    65: istore_2       
        //    66: iload_2        
        //    67: ifne            93
        //    70: aload_3        
        //    71: astore_1       
        //    72: aload           4
        //    74: ifnull          86
        //    77: aload           4
        //    79: invokeinterface android/database/Cursor.close:()V
        //    84: aload_3        
        //    85: astore_1       
        //    86: aload_1        
        //    87: areturn        
        //    88: aconst_null    
        //    89: astore_3       
        //    90: goto            61
        //    93: invokestatic    java/util/UUID.randomUUID:()Ljava/util/UUID;
        //    96: invokevirtual   java/util/UUID.toString:()Ljava/lang/String;
        //    99: astore_3       
        //   100: new             Landroid/content/ContentValues;
        //   103: dup            
        //   104: iconst_2       
        //   105: invokespecial   android/content/ContentValues.<init>:(I)V
        //   108: astore          5
        //   110: aload           5
        //   112: getstatic       com/facebook/ads/internal/j/h.a:Lcom/facebook/ads/internal/j/b;
        //   115: getfield        com/facebook/ads/internal/j/b.b:Ljava/lang/String;
        //   118: aload_3        
        //   119: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   122: aload           5
        //   124: getstatic       com/facebook/ads/internal/j/h.b:Lcom/facebook/ads/internal/j/b;
        //   127: getfield        com/facebook/ads/internal/j/b.b:Ljava/lang/String;
        //   130: aload_1        
        //   131: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   134: aload_0        
        //   135: invokevirtual   com/facebook/ads/internal/j/h.f:()Landroid/database/sqlite/SQLiteDatabase;
        //   138: ldc             "tokens"
        //   140: aconst_null    
        //   141: aload           5
        //   143: invokevirtual   android/database/sqlite/SQLiteDatabase.insertOrThrow:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   146: pop2           
        //   147: aload_3        
        //   148: astore_1       
        //   149: aload           4
        //   151: ifnull          86
        //   154: aload           4
        //   156: invokeinterface android/database/Cursor.close:()V
        //   161: aload_3        
        //   162: areturn        
        //   163: astore_1       
        //   164: aconst_null    
        //   165: astore          4
        //   167: aload           4
        //   169: ifnull          179
        //   172: aload           4
        //   174: invokeinterface android/database/Cursor.close:()V
        //   179: aload_1        
        //   180: athrow         
        //   181: astore_1       
        //   182: goto            167
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  17     37     163    167    Any
        //  37     61     181    185    Any
        //  61     66     181    185    Any
        //  93     147    181    185    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0061:
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
    public b[] b() {
        return h.c;
    }
    
    @WorkerThread
    Cursor c() {
        return this.f().rawQuery(h.e, (String[])null);
    }
    
    @WorkerThread
    public void d() {
        try {
            this.f().execSQL(h.g);
        }
        catch (SQLException ex) {}
    }
}
