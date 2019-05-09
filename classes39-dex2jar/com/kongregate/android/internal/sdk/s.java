// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import android.database.sqlite.SQLiteDatabase;
import java.util.Iterator;
import android.util.Log;
import java.util.List;
import android.os.Bundle;
import java.util.ArrayList;
import android.content.Context;
import java.util.HashMap;

class s
{
    private HashMap<String, j> a;
    private g b;
    
    s(final Context context) {
        this.a = new HashMap<String, j>();
        this.b = new g(context);
    }
    
    ArrayList<Bundle> a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: new             Ljava/util/ArrayList;
        //     5: dup            
        //     6: invokespecial   java/util/ArrayList.<init>:()V
        //     9: astore          4
        //    11: aload_0        
        //    12: getfield        com/kongregate/android/internal/sdk/s.b:Lcom/kongregate/android/internal/sdk/g;
        //    15: invokevirtual   com/kongregate/android/internal/sdk/g.getReadableDatabase:()Landroid/database/sqlite/SQLiteDatabase;
        //    18: astore_1       
        //    19: aload_1        
        //    20: ldc             "statistic_cache"
        //    22: aconst_null    
        //    23: aconst_null    
        //    24: aconst_null    
        //    25: aconst_null    
        //    26: aconst_null    
        //    27: aconst_null    
        //    28: aconst_null    
        //    29: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    32: astore_2       
        //    33: aload_2        
        //    34: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    39: pop            
        //    40: aload_2        
        //    41: invokeinterface android/database/Cursor.isAfterLast:()Z
        //    46: ifne            119
        //    49: aload           4
        //    51: new             Lcom/kongregate/android/internal/sdk/j;
        //    54: dup            
        //    55: aload_2        
        //    56: invokespecial   com/kongregate/android/internal/sdk/j.<init>:(Landroid/database/Cursor;)V
        //    59: invokevirtual   com/kongregate/android/internal/sdk/j.a:()Landroid/os/Bundle;
        //    62: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    65: pop            
        //    66: aload_2        
        //    67: invokeinterface android/database/Cursor.moveToNext:()Z
        //    72: pop            
        //    73: goto            40
        //    76: astore          4
        //    78: aload_1        
        //    79: astore_3       
        //    80: aload           4
        //    82: astore_1       
        //    83: aload_2        
        //    84: ifnull          102
        //    87: aload_2        
        //    88: invokeinterface android/database/Cursor.isClosed:()Z
        //    93: ifne            102
        //    96: aload_2        
        //    97: invokeinterface android/database/Cursor.close:()V
        //   102: aload_3        
        //   103: ifnull          117
        //   106: aload_3        
        //   107: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //   110: ifeq            117
        //   113: aload_3        
        //   114: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   117: aload_1        
        //   118: athrow         
        //   119: aload_2        
        //   120: ifnull          138
        //   123: aload_2        
        //   124: invokeinterface android/database/Cursor.isClosed:()Z
        //   129: ifne            138
        //   132: aload_2        
        //   133: invokeinterface android/database/Cursor.close:()V
        //   138: aload_1        
        //   139: ifnull          153
        //   142: aload_1        
        //   143: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //   146: ifeq            153
        //   149: aload_1        
        //   150: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   153: aload           4
        //   155: areturn        
        //   156: astore_1       
        //   157: aconst_null    
        //   158: astore_2       
        //   159: goto            83
        //   162: astore          4
        //   164: aconst_null    
        //   165: astore_2       
        //   166: aload_1        
        //   167: astore_3       
        //   168: aload           4
        //   170: astore_1       
        //   171: goto            83
        //    Signature:
        //  ()Ljava/util/ArrayList<Landroid/os/Bundle;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  11     19     156    162    Any
        //  19     33     162    174    Any
        //  33     40     76     83     Any
        //  40     73     76     83     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    void a(final List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        final StringBuilder sb = new StringBuilder("(");
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append("'").append(iterator.next()).append("',");
        }
        final String string = sb.substring(0, sb.lastIndexOf(",")) + ")";
        final SQLiteDatabase writableDatabase = this.b.getWritableDatabase();
        Log.v("KongSDK", "cleared " + writableDatabase.delete("statistic_cache", "NAME IN " + string, (String[])null) + " rows from stats cache");
        writableDatabase.close();
    }
}
