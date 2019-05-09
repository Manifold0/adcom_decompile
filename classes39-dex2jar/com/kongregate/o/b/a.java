// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.b;

import android.content.ContentValues;
import com.kongregate.o.c.d;
import android.database.Cursor;
import com.kongregate.o.e.b;
import com.kongregate.android.internal.util.j;
import org.json.JSONArray;
import org.json.JSONObject;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class a implements BaseColumns
{
    public static final String a = "accomplishment_tasks";
    public static final String b = "user_accomplishment_tasks_view";
    public static final String c = "badge_id";
    public static final String d = "description";
    public static final String e = "name";
    public static final String f = "quota";
    public static final String g = "statistic_id";
    public static final String h = "value";
    public static final String i = "completed";
    
    static void a(final long n, final JSONArray jsonArray) {
        if (jsonArray == null) {
            j.c("null JSON passed into updateTasks");
            return;
        }
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                final SQLiteDatabase a = com.kongregate.o.e.b.a();
                a.beginTransaction();
                int i = 0;
                try {
                    while (i < jsonArray.length()) {
                        b(a, n, jsonArray.optJSONObject(i));
                        ++i;
                    }
                    a.setTransactionSuccessful();
                }
                finally {
                    com.kongregate.o.e.a.a(null);
                    a.endTransaction();
                }
            }
        });
    }
    
    public static boolean a(final SQLiteDatabase p0, final long p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "user_accomplishment_tasks_view"
        //     3: iconst_1       
        //     4: anewarray       Ljava/lang/String;
        //     7: dup            
        //     8: iconst_0       
        //     9: ldc             "completed"
        //    11: aastore        
        //    12: ldc             "_id=?"
        //    14: iconst_1       
        //    15: anewarray       Ljava/lang/String;
        //    18: dup            
        //    19: iconst_0       
        //    20: lload_1        
        //    21: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    24: aastore        
        //    25: aconst_null    
        //    26: aconst_null    
        //    27: aconst_null    
        //    28: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    31: astore_0       
        //    32: aload_0        
        //    33: ifnull          59
        //    36: aload_0        
        //    37: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    42: ifeq            59
        //    45: aload_0        
        //    46: iconst_0       
        //    47: invokeinterface android/database/Cursor.isNull:(I)Z
        //    52: istore          4
        //    54: iload           4
        //    56: ifeq            65
        //    59: aload_0        
        //    60: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //    63: iconst_0       
        //    64: ireturn        
        //    65: aload_0        
        //    66: iconst_0       
        //    67: invokeinterface android/database/Cursor.getInt:(I)I
        //    72: istore_3       
        //    73: iload_3        
        //    74: iconst_1       
        //    75: if_icmpne       88
        //    78: iconst_1       
        //    79: istore          4
        //    81: aload_0        
        //    82: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //    85: iload           4
        //    87: ireturn        
        //    88: iconst_0       
        //    89: istore          4
        //    91: goto            81
        //    94: astore          5
        //    96: aconst_null    
        //    97: astore_0       
        //    98: aload_0        
        //    99: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   102: aload           5
        //   104: athrow         
        //   105: astore          5
        //   107: goto            98
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      32     94     98     Any
        //  36     54     105    110    Any
        //  65     73     105    110    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    private static long b(final SQLiteDatabase sqLiteDatabase, long a, final JSONObject jsonObject) {
        if (jsonObject == null || !jsonObject.has("id")) {
            return -1L;
        }
        final ContentValues contentValues = new ContentValues();
        contentValues.put("_id", Long.valueOf(jsonObject.optLong("id", 0L)));
        contentValues.put("badge_id", Long.valueOf(a));
        contentValues.put("description", jsonObject.optString("description", ""));
        contentValues.put("name", jsonObject.optString("name", ""));
        contentValues.put("quota", Long.valueOf(jsonObject.optLong("quota", 0L)));
        contentValues.put("statistic_id", Long.valueOf(jsonObject.optLong("statistic_id", 0L)));
        a = a.a(sqLiteDatabase, "accomplishment_tasks", contentValues);
        if (a == -1L) {
            j.c("Failed to add accomplishment task, id: " + contentValues.get("_id"));
            return a;
        }
        j.a("Task: " + contentValues.get("name"));
        return a;
    }
}
