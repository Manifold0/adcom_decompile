// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.Closeable;
import android.database.Cursor;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

class fj extends fi
{
    private final File b;
    private final gb c;
    private volatile SQLiteDatabase d;
    private long e;
    private long f;
    private long g;
    
    public fj(final File b, final gb c) {
        this.b = b;
        this.c = c;
    }
    
    private void b() {
        final Set a = fi.a;
        final Cursor rawQuery = this.d.rawQuery("SELECT ROWID, * FROM UsageStats ORDER BY ROWID ASC", (String[])null);
        Cursor rawQuery2;
        try {
            rawQuery2 = this.d.rawQuery("SELECT * FROM UsageStatValues ORDER BY stat_id ASC", (String[])null);
            try {
                rawQuery2.moveToNext();
                while (rawQuery.moveToNext()) {
                    final long long1 = rawQuery.getLong(0);
                    final String string = rawQuery.getString(1);
                    String string2;
                    if ((string2 = rawQuery.getString(2)).isEmpty()) {
                        string2 = null;
                    }
                    final int int1 = rawQuery.getInt(3);
                    final long long2 = rawQuery.getLong(4);
                    final long long3 = rawQuery.getLong(5);
                    Map<String, Long> map = null;
                    Map<String, Long> map2 = null;
                    if (!rawQuery2.isAfterLast()) {
                        do {
                            map = map2;
                            if (rawQuery2.getLong(0) != long1) {
                                break;
                            }
                            if ((map = map2) == null) {
                                map = new HashMap<String, Long>();
                            }
                            final String string3 = rawQuery2.getString(1);
                            final long long4 = rawQuery2.getLong(3);
                            final long long5 = rawQuery2.getLong(4);
                            map.put(string3, long4);
                            map.put(string3 + "_max", long5);
                            map2 = map;
                        } while (rawQuery2.moveToNext());
                    }
                    if (a == null || !a.contains(string)) {
                        this.c.a(string, string2, int1, long2, long3, map);
                    }
                }
            }
            finally {
                rawQuery2.close();
            }
        }
        finally {
            rawQuery.close();
        }
        rawQuery2.close();
        rawQuery.close();
        this.d.execSQL("DELETE FROM UsageStats");
        this.d.execSQL("DELETE FROM UsageStatValues");
        this.g = 0L;
        this.f = 0L;
    }
    
    @Override
    protected void a() {
        if (this.d != null) {
            dc.a((Closeable)this.d);
            this.d = null;
        }
        this.b.delete();
        this.g = 0L;
        this.f = 0L;
    }
    
    @Override
    protected void a(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //     4: ifnull          8
        //     7: return         
        //     8: aload_0        
        //     9: aload_0        
        //    10: getfield        com/tapjoy/internal/fj.b:Ljava/io/File;
        //    13: aconst_null    
        //    14: invokestatic    android/database/sqlite/SQLiteDatabase.openOrCreateDatabase:(Ljava/io/File;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
        //    17: putfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //    20: aload_0        
        //    21: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //    24: invokevirtual   android/database/sqlite/SQLiteDatabase.getVersion:()I
        //    27: istore_3       
        //    28: iload_3        
        //    29: tableswitch {
        //                0: 76
        //                1: 123
        //          default: 52
        //        }
        //    52: new             Landroid/database/SQLException;
        //    55: dup            
        //    56: new             Ljava/lang/StringBuilder;
        //    59: dup            
        //    60: ldc             "Unknown database version: "
        //    62: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    65: iload_3        
        //    66: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    69: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    72: invokespecial   android/database/SQLException.<init>:(Ljava/lang/String;)V
        //    75: athrow         
        //    76: aload_0        
        //    77: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //    80: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    83: aload_0        
        //    84: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //    87: ldc             "CREATE TABLE IF NOT EXISTS UsageStats(name TEXT,dimensions TEXT,count INTEGER,first_time INTEGER,last_time INTEGER,PRIMARY KEY(name, dimensions))"
        //    89: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    92: aload_0        
        //    93: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //    96: ldc             "CREATE TABLE IF NOT EXISTS UsageStatValues(stat_id LONG,name TEXT,count INTEGER,avg REAL,max INTEGER,PRIMARY KEY(stat_id, name))"
        //    98: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //   101: aload_0        
        //   102: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   105: iconst_1       
        //   106: invokevirtual   android/database/sqlite/SQLiteDatabase.setVersion:(I)V
        //   109: aload_0        
        //   110: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   113: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   116: aload_0        
        //   117: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   120: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   123: aload_0        
        //   124: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   127: ldc             "SELECT MIN(first_time), MAX(last_time) FROM UsageStats"
        //   129: aconst_null    
        //   130: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   133: astore          4
        //   135: aload           4
        //   137: invokeinterface android/database/Cursor.moveToNext:()Z
        //   142: ifeq            169
        //   145: aload_0        
        //   146: aload           4
        //   148: iconst_0       
        //   149: invokeinterface android/database/Cursor.getLong:(I)J
        //   154: putfield        com/tapjoy/internal/fj.f:J
        //   157: aload_0        
        //   158: aload           4
        //   160: iconst_1       
        //   161: invokeinterface android/database/Cursor.getLong:(I)J
        //   166: putfield        com/tapjoy/internal/fj.g:J
        //   169: aload           4
        //   171: invokeinterface android/database/Cursor.close:()V
        //   176: aload_0        
        //   177: getfield        com/tapjoy/internal/fj.f:J
        //   180: lconst_0       
        //   181: lcmp           
        //   182: ifle            7
        //   185: aload_0        
        //   186: getfield        com/tapjoy/internal/fj.f:J
        //   189: ldc2_w          86400000
        //   192: ladd           
        //   193: lload_1        
        //   194: lcmp           
        //   195: ifgt            7
        //   198: aload_0        
        //   199: invokespecial   com/tapjoy/internal/fj.b:()V
        //   202: return         
        //   203: astore          4
        //   205: aload_0        
        //   206: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   209: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   212: aload           4
        //   214: athrow         
        //   215: astore          5
        //   217: aload           4
        //   219: invokeinterface android/database/Cursor.close:()V
        //   224: aload           5
        //   226: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  83     116    203    215    Any
        //  135    169    215    227    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0169:
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
    protected void a(final long p0, final String p1, final String p2, final Map p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //     4: ifnonnull       8
        //     7: return         
        //     8: aload_0        
        //     9: getfield        com/tapjoy/internal/fj.e:J
        //    12: lconst_0       
        //    13: lcmp           
        //    14: ifne            488
        //    17: aload_0        
        //    18: lload_1        
        //    19: putfield        com/tapjoy/internal/fj.g:J
        //    22: aload_0        
        //    23: lload_1        
        //    24: putfield        com/tapjoy/internal/fj.e:J
        //    27: aload           4
        //    29: astore          15
        //    31: aload           4
        //    33: ifnonnull       40
        //    36: ldc             ""
        //    38: astore          15
        //    40: aload_0        
        //    41: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //    44: ldc             "SELECT ROWID,count,first_time,last_time FROM UsageStats WHERE name = ? AND dimensions = ?"
        //    46: iconst_2       
        //    47: anewarray       Ljava/lang/String;
        //    50: dup            
        //    51: iconst_0       
        //    52: aload_3        
        //    53: aastore        
        //    54: dup            
        //    55: iconst_1       
        //    56: aload           15
        //    58: aastore        
        //    59: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //    62: astore          4
        //    64: new             Landroid/content/ContentValues;
        //    67: dup            
        //    68: invokespecial   android/content/ContentValues.<init>:()V
        //    71: astore          16
        //    73: aload           4
        //    75: invokeinterface android/database/Cursor.moveToNext:()Z
        //    80: ifeq            574
        //    83: aload           4
        //    85: iconst_0       
        //    86: invokeinterface android/database/Cursor.getLong:(I)J
        //    91: lstore          9
        //    93: aload           4
        //    95: iconst_1       
        //    96: invokeinterface android/database/Cursor.getInt:(I)I
        //   101: istore          8
        //   103: aload           4
        //   105: iconst_2       
        //   106: invokeinterface android/database/Cursor.getLong:(I)J
        //   111: lstore          11
        //   113: aload           4
        //   115: iconst_3       
        //   116: invokeinterface android/database/Cursor.getLong:(I)J
        //   121: lstore          13
        //   123: aload           16
        //   125: ldc             "count"
        //   127: iload           8
        //   129: iconst_1       
        //   130: iadd           
        //   131: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   134: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   137: lload_1        
        //   138: lload           11
        //   140: lcmp           
        //   141: ifge            155
        //   144: aload           16
        //   146: ldc             "first_time"
        //   148: lload_1        
        //   149: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   152: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   155: lload_1        
        //   156: lload           13
        //   158: lcmp           
        //   159: ifle            173
        //   162: aload           16
        //   164: ldc             "last_time"
        //   166: lload_1        
        //   167: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   170: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   173: aload_0        
        //   174: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   177: ldc             "UsageStats"
        //   179: aload           16
        //   181: new             Ljava/lang/StringBuilder;
        //   184: dup            
        //   185: ldc             "ROWID = "
        //   187: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   190: lload           9
        //   192: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   195: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   198: aconst_null    
        //   199: invokevirtual   android/database/sqlite/SQLiteDatabase.update:(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
        //   202: pop            
        //   203: lload           9
        //   205: lstore_1       
        //   206: aload           5
        //   208: ifnull          732
        //   211: aload           5
        //   213: invokeinterface java/util/Map.isEmpty:()Z
        //   218: ifne            732
        //   221: aload           5
        //   223: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   228: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   233: astore_3       
        //   234: aload_3        
        //   235: invokeinterface java/util/Iterator.hasNext:()Z
        //   240: ifeq            732
        //   243: aload_3        
        //   244: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   249: checkcast       Ljava/util/Map$Entry;
        //   252: astore          15
        //   254: aload           15
        //   256: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   261: ifnull          234
        //   264: aload           15
        //   266: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   271: checkcast       Ljava/lang/String;
        //   274: astore          5
        //   276: aload           15
        //   278: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   283: checkcast       Ljava/lang/Long;
        //   286: invokevirtual   java/lang/Long.longValue:()J
        //   289: lstore          9
        //   291: lload_1        
        //   292: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //   295: astore          15
        //   297: aload_0        
        //   298: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   301: ldc             "SELECT ROWID, * FROM UsageStatValues WHERE stat_id = ? AND name = ?"
        //   303: iconst_2       
        //   304: anewarray       Ljava/lang/String;
        //   307: dup            
        //   308: iconst_0       
        //   309: aload           15
        //   311: aastore        
        //   312: dup            
        //   313: iconst_1       
        //   314: aload           5
        //   316: aastore        
        //   317: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   320: astore          15
        //   322: aload           15
        //   324: invokeinterface android/database/Cursor.moveToNext:()Z
        //   329: ifeq            642
        //   332: aload           15
        //   334: iconst_0       
        //   335: invokeinterface android/database/Cursor.getLong:(I)J
        //   340: lstore          11
        //   342: aload           15
        //   344: iconst_3       
        //   345: invokeinterface android/database/Cursor.getInt:(I)I
        //   350: istore          8
        //   352: aload           15
        //   354: iconst_4       
        //   355: invokeinterface android/database/Cursor.getDouble:(I)D
        //   360: dstore          6
        //   362: aload           15
        //   364: iconst_5       
        //   365: invokeinterface android/database/Cursor.getLong:(I)J
        //   370: lstore          13
        //   372: aload           16
        //   374: invokevirtual   android/content/ContentValues.clear:()V
        //   377: aload           16
        //   379: ldc             "count"
        //   381: iload           8
        //   383: iconst_1       
        //   384: iadd           
        //   385: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   388: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   391: aload           16
        //   393: ldc             "avg"
        //   395: dload           6
        //   397: lload           9
        //   399: l2d            
        //   400: dload           6
        //   402: dsub           
        //   403: iload           8
        //   405: iconst_1       
        //   406: iadd           
        //   407: i2d            
        //   408: ddiv           
        //   409: dadd           
        //   410: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   413: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Double;)V
        //   416: lload           9
        //   418: lload           13
        //   420: lcmp           
        //   421: ifle            437
        //   424: aload           16
        //   426: ldc_w           "max"
        //   429: lload           9
        //   431: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   434: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   437: aload_0        
        //   438: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   441: ldc_w           "UsageStatValues"
        //   444: aload           16
        //   446: new             Ljava/lang/StringBuilder;
        //   449: dup            
        //   450: ldc             "ROWID = "
        //   452: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   455: lload           11
        //   457: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   460: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   463: aconst_null    
        //   464: invokevirtual   android/database/sqlite/SQLiteDatabase.update:(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
        //   467: pop            
        //   468: aload           15
        //   470: invokeinterface android/database/Cursor.close:()V
        //   475: goto            234
        //   478: astore_3       
        //   479: aload           4
        //   481: invokeinterface android/database/Cursor.close:()V
        //   486: aload_3        
        //   487: athrow         
        //   488: lload_1        
        //   489: aload_0        
        //   490: getfield        com/tapjoy/internal/fj.e:J
        //   493: lcmp           
        //   494: iflt            527
        //   497: lload_1        
        //   498: aload_0        
        //   499: getfield        com/tapjoy/internal/fj.e:J
        //   502: ldc2_w          86400000
        //   505: ladd           
        //   506: lcmp           
        //   507: ifge            527
        //   510: lload_1        
        //   511: aload_0        
        //   512: getfield        com/tapjoy/internal/fj.g:J
        //   515: lcmp           
        //   516: ifle            27
        //   519: aload_0        
        //   520: lload_1        
        //   521: putfield        com/tapjoy/internal/fj.g:J
        //   524: goto            27
        //   527: lload_1        
        //   528: aload_0        
        //   529: getfield        com/tapjoy/internal/fj.e:J
        //   532: lcmp           
        //   533: ifge            557
        //   536: aload_0        
        //   537: getfield        com/tapjoy/internal/fj.g:J
        //   540: lload_1        
        //   541: lsub           
        //   542: ldc2_w          86400000
        //   545: lcmp           
        //   546: ifge            557
        //   549: aload_0        
        //   550: lload_1        
        //   551: putfield        com/tapjoy/internal/fj.e:J
        //   554: goto            27
        //   557: aload_0        
        //   558: invokespecial   com/tapjoy/internal/fj.b:()V
        //   561: aload_0        
        //   562: lload_1        
        //   563: putfield        com/tapjoy/internal/fj.g:J
        //   566: aload_0        
        //   567: lload_1        
        //   568: putfield        com/tapjoy/internal/fj.e:J
        //   571: goto            27
        //   574: aload           16
        //   576: ldc_w           "name"
        //   579: aload_3        
        //   580: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   583: aload           16
        //   585: ldc_w           "dimensions"
        //   588: aload           15
        //   590: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   593: aload           16
        //   595: ldc             "count"
        //   597: iconst_1       
        //   598: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   601: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   604: aload           16
        //   606: ldc             "first_time"
        //   608: lload_1        
        //   609: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   612: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   615: aload           16
        //   617: ldc             "last_time"
        //   619: lload_1        
        //   620: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   623: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   626: aload_0        
        //   627: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   630: ldc             "UsageStats"
        //   632: aconst_null    
        //   633: aload           16
        //   635: invokevirtual   android/database/sqlite/SQLiteDatabase.insert:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   638: lstore_1       
        //   639: goto            206
        //   642: aload           16
        //   644: invokevirtual   android/content/ContentValues.clear:()V
        //   647: aload           16
        //   649: ldc_w           "stat_id"
        //   652: lload_1        
        //   653: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   656: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   659: aload           16
        //   661: ldc_w           "name"
        //   664: aload           5
        //   666: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   669: aload           16
        //   671: ldc             "count"
        //   673: iconst_1       
        //   674: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   677: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
        //   680: aload           16
        //   682: ldc             "avg"
        //   684: lload           9
        //   686: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   689: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   692: aload           16
        //   694: ldc_w           "max"
        //   697: lload           9
        //   699: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   702: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   705: aload_0        
        //   706: getfield        com/tapjoy/internal/fj.d:Landroid/database/sqlite/SQLiteDatabase;
        //   709: ldc_w           "UsageStatValues"
        //   712: aconst_null    
        //   713: aload           16
        //   715: invokevirtual   android/database/sqlite/SQLiteDatabase.insert:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   718: pop2           
        //   719: goto            468
        //   722: astore_3       
        //   723: aload           15
        //   725: invokeinterface android/database/Cursor.close:()V
        //   730: aload_3        
        //   731: athrow         
        //   732: aload           4
        //   734: invokeinterface android/database/Cursor.close:()V
        //   739: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  64     137    478    488    Any
        //  144    155    478    488    Any
        //  162    173    478    488    Any
        //  173    203    478    488    Any
        //  211    234    478    488    Any
        //  234    322    478    488    Any
        //  322    416    722    732    Any
        //  424    437    722    732    Any
        //  437    468    722    732    Any
        //  468    475    478    488    Any
        //  574    639    478    488    Any
        //  642    719    722    732    Any
        //  723    732    478    488    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0437:
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
    protected void finalize() {
        if (this.d != null) {
            dc.a((Closeable)this.d);
            this.d = null;
        }
        super.finalize();
    }
}
