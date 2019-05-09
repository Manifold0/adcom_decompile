// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.b;

import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.android.internal.util.i;
import android.content.ContentValues;
import com.kongregate.o.c.d;
import android.database.Cursor;
import com.kongregate.o.e.a;
import com.kongregate.o.e.b;
import com.kongregate.android.internal.util.j;
import org.json.JSONArray;
import org.json.JSONObject;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class c implements BaseColumns
{
    public static final String a = "badges";
    public static final String b = "user_badges_view";
    public static final String c = "level";
    public static final String d = "name";
    public static final String e = "description";
    public static final String f = "users_count";
    public static final String g = "url";
    public static final String h = "application_id";
    public static final String i = "game_id";
    public static final String j = "download_state";
    public static final String k = "updated_at";
    public static final String l = "_data";
    public static final String m = "completed";
    public static final String n = "CASE level WHEN 'easy' THEN 1 WHEN 'medium' THEN 2 WHEN 'hard' THEN 3 WHEN 'impossible' THEN 4 ELSE 5 END, name";
    
    public static void a(final long n, final JSONArray jsonArray) {
        if (jsonArray == null) {
            com.kongregate.android.internal.util.j.c("null JSON passed into updateBadges");
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
    
    static boolean a(final SQLiteDatabase p0, final long p1, final long p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc             "user_badges"
        //     3: iconst_1       
        //     4: anewarray       Ljava/lang/String;
        //     7: dup            
        //     8: iconst_0       
        //     9: ldc             "_id"
        //    11: aastore        
        //    12: ldc             "user_id=? AND badge_id=?"
        //    14: iconst_2       
        //    15: anewarray       Ljava/lang/String;
        //    18: dup            
        //    19: iconst_0       
        //    20: lload_1        
        //    21: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    24: aastore        
        //    25: dup            
        //    26: iconst_1       
        //    27: lload_3        
        //    28: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    31: aastore        
        //    32: aconst_null    
        //    33: aconst_null    
        //    34: aconst_null    
        //    35: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    38: astore          6
        //    40: aload           6
        //    42: ifnull          66
        //    45: aload           6
        //    47: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    52: istore          5
        //    54: iload           5
        //    56: ifeq            66
        //    59: aload           6
        //    61: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //    64: iconst_1       
        //    65: ireturn        
        //    66: aload           6
        //    68: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //    71: aload_0        
        //    72: ldc             "user_accomplishment_tasks_view"
        //    74: iconst_1       
        //    75: anewarray       Ljava/lang/String;
        //    78: dup            
        //    79: iconst_0       
        //    80: ldc             "completed"
        //    82: aastore        
        //    83: ldc             "badge_id=? AND completed=0"
        //    85: iconst_1       
        //    86: anewarray       Ljava/lang/String;
        //    89: dup            
        //    90: iconst_0       
        //    91: lload_3        
        //    92: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    95: aastore        
        //    96: aconst_null    
        //    97: aconst_null    
        //    98: aconst_null    
        //    99: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   102: astore          7
        //   104: aload           7
        //   106: astore          6
        //   108: aload           6
        //   110: ifnull          173
        //   113: aload           6
        //   115: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   120: ifne            173
        //   123: new             Ljava/lang/StringBuilder;
        //   126: dup            
        //   127: invokespecial   java/lang/StringBuilder.<init>:()V
        //   130: ldc             "Found "
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: aload           6
        //   137: invokeinterface android/database/Cursor.getCount:()I
        //   142: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   145: ldc             " incomplete tasks for badge "
        //   147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: lload_3        
        //   151: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //   160: aload_0        
        //   161: lload_1        
        //   162: lload_3        
        //   163: invokestatic    com/kongregate/o/b/f.a:(Landroid/database/sqlite/SQLiteDatabase;JJ)V
        //   166: aload           6
        //   168: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   171: iconst_1       
        //   172: ireturn        
        //   173: aload           6
        //   175: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   178: iconst_0       
        //   179: ireturn        
        //   180: astore_0       
        //   181: aconst_null    
        //   182: astore          6
        //   184: aload           6
        //   186: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   189: aload_0        
        //   190: athrow         
        //   191: astore_0       
        //   192: goto            184
        //   195: astore_0       
        //   196: goto            184
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  0      40     180    184    Any
        //  45     54     191    195    Any
        //  66     104    191    195    Any
        //  113    166    195    199    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 104, Size: 104
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
        final long optLong = jsonObject.optLong("id", 0L);
        contentValues.put("_id", Long.valueOf(optLong));
        contentValues.put("game_id", Long.valueOf(a));
        contentValues.put("description", jsonObject.optString("description", ""));
        contentValues.put("url", jsonObject.optString("image_url", ""));
        contentValues.put("name", jsonObject.optString("name", ""));
        contentValues.put("users_count", Long.valueOf(jsonObject.optLong("users_count", 0L)));
        contentValues.put("updated_at", Long.valueOf(com.kongregate.android.internal.util.i.b(jsonObject, "created_at")));
        contentValues.put("level", jsonObject.optString("level", "easy"));
        a = a.a(sqLiteDatabase, "badges", contentValues);
        if (a == -1L) {
            com.kongregate.android.internal.util.j.c("Failed to add badge, id: " + contentValues.get("_id"));
            return a;
        }
        com.kongregate.android.internal.util.j.a("Badge: " + contentValues.get("name"));
        a.a(optLong, jsonObject.optJSONArray("accomplishment_tasks"));
        return a;
    }
    
    public enum a
    {
        a, 
        b, 
        c, 
        d;
        
        public static a a(final String s) {
            if (StringUtils.c((CharSequence)s)) {
                return a.a;
            }
            return valueOf(StringUtils.g(s));
        }
        
        @Override
        public String toString() {
            return StringUtils.f(this.name());
        }
    }
}
