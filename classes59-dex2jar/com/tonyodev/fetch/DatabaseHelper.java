// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch;

import android.database.DatabaseUtils;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

final class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String COLUMN_DOWNLOADED_BYTES = "_written_bytes";
    private static final String COLUMN_ERROR = "_error";
    private static final String COLUMN_FILEPATH = "_file_path";
    private static final String COLUMN_FILE_SIZE = "_file_size";
    private static final String COLUMN_HEADERS = "_headers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PRIORITY = "_priority";
    private static final String COLUMN_STATUS = "_status";
    private static final String COLUMN_URL = "_url";
    private static final String DB_NAME = "com_tonyodev_fetch.db";
    static final int EMPTY_COLUMN_VALUE = -1;
    static final int INDEX_COLUMN_DOWNLOADED_BYTES = 5;
    static final int INDEX_COLUMN_ERROR = 7;
    static final int INDEX_COLUMN_FILEPATH = 2;
    static final int INDEX_COLUMN_FILE_SIZE = 6;
    static final int INDEX_COLUMN_HEADERS = 4;
    static final int INDEX_COLUMN_ID = 0;
    static final int INDEX_COLUMN_PRIORITY = 8;
    static final int INDEX_COLUMN_STATUS = 3;
    static final int INDEX_COLUMN_URL = 1;
    private static final String TABLE_NAME = "requests";
    private static final int VERSION = 2;
    private static DatabaseHelper databaseHelper;
    private final SQLiteDatabase db;
    private boolean loggingEnabled;
    
    private DatabaseHelper(final Context context) {
        super(context, "com_tonyodev_fetch.db", (SQLiteDatabase$CursorFactory)null, 2);
        this.loggingEnabled = true;
        this.db = this.getWritableDatabase();
    }
    
    static DatabaseHelper getInstance(final Context context) {
        while (true) {
            synchronized (DatabaseHelper.class) {
                if (DatabaseHelper.databaseHelper != null) {
                    return DatabaseHelper.databaseHelper;
                }
                if (context == null) {
                    throw new NullPointerException("Context cannot be null");
                }
            }
            final Context context2;
            DatabaseHelper.databaseHelper = new DatabaseHelper(context2.getApplicationContext());
            return DatabaseHelper.databaseHelper;
        }
    }
    
    void clean() {
        while (true) {
            Label_0177: {
                final Cursor cursor;
                Label_0044: {
                    synchronized (this) {
                        final Cursor rawQuery = this.db.rawQuery("SELECT _id, _file_path FROM requests WHERE _status = 903", (String[])null);
                        if (rawQuery != null) {
                            if (rawQuery.getCount() >= 1) {
                                break Label_0044;
                            }
                            rawQuery.close();
                        }
                        return;
                    }
                    try {
                        this.db.beginTransaction();
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            final String string = cursor.getString(cursor.getColumnIndex("_file_path"));
                            if (string != null && !Utils.fileExist(string)) {
                                this.db.execSQL("UPDATE requests SET _status = 904, _error = -111 WHERE _id = " + cursor.getLong(cursor.getColumnIndex("_id")));
                            }
                            cursor.moveToNext();
                        }
                        break Label_0177;
                    }
                    catch (SQLiteException ex) {
                        if (this.loggingEnabled) {
                            ex.printStackTrace();
                        }
                    }
                }
                try {
                    while (true) {
                        this.db.endTransaction();
                        return;
                        this.db.setTransactionSuccessful();
                        continue;
                    }
                }
                catch (SQLiteException ex2) {
                    if (this.loggingEnabled) {
                        ex2.printStackTrace();
                    }
                }
                finally {
                    cursor.close();
                }
            }
        }
    }
    
    boolean delete(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore          4
        //     5: aload_0        
        //     6: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //     9: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    12: aload_0        
        //    13: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    16: new             Ljava/lang/StringBuilder;
        //    19: dup            
        //    20: invokespecial   java/lang/StringBuilder.<init>:()V
        //    23: ldc             "DELETE FROM requests WHERE _id = "
        //    25: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    28: lload_1        
        //    29: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    32: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    35: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    38: aload_0        
        //    39: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    42: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    45: aload_0        
        //    46: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    49: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    52: iconst_1       
        //    53: istore_3       
        //    54: aload_0        
        //    55: monitorexit    
        //    56: iload_3        
        //    57: ireturn        
        //    58: astore          5
        //    60: aload_0        
        //    61: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    64: ifeq            45
        //    67: aload           5
        //    69: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //    72: goto            45
        //    75: astore          5
        //    77: aload_0        
        //    78: monitorexit    
        //    79: aload           5
        //    81: athrow         
        //    82: astore          5
        //    84: iload           4
        //    86: istore_3       
        //    87: aload_0        
        //    88: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    91: ifeq            54
        //    94: aload           5
        //    96: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //    99: iload           4
        //   101: istore_3       
        //   102: goto            54
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  5      45     58     75     Landroid/database/sqlite/SQLiteException;
        //  5      45     75     82     Any
        //  45     52     82     105    Landroid/database/sqlite/SQLiteException;
        //  45     52     75     82     Any
        //  60     72     75     82     Any
        //  87     99     75     82     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
    
    boolean deleteAll() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore_2       
        //     4: aload_0        
        //     5: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //     8: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    11: aload_0        
        //    12: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    15: ldc             "DELETE FROM requests"
        //    17: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    20: aload_0        
        //    21: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    24: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    27: aload_0        
        //    28: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    31: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    34: iconst_1       
        //    35: istore_1       
        //    36: aload_0        
        //    37: monitorexit    
        //    38: iload_1        
        //    39: ireturn        
        //    40: astore_3       
        //    41: aload_0        
        //    42: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    45: ifeq            27
        //    48: aload_3        
        //    49: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //    52: goto            27
        //    55: astore_3       
        //    56: aload_0        
        //    57: monitorexit    
        //    58: aload_3        
        //    59: athrow         
        //    60: astore_3       
        //    61: iload_2        
        //    62: istore_1       
        //    63: aload_0        
        //    64: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    67: ifeq            36
        //    70: aload_3        
        //    71: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //    74: iload_2        
        //    75: istore_1       
        //    76: goto            36
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  4      27     40     55     Landroid/database/sqlite/SQLiteException;
        //  4      27     55     60     Any
        //  27     34     60     79     Landroid/database/sqlite/SQLiteException;
        //  27     34     55     60     Any
        //  41     52     55     60     Any
        //  63     74     55     60     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    Cursor get() {
        final Cursor cursor = null;
        synchronized (this) {
            try {
                return this.db.rawQuery("SELECT * FROM requests", (String[])null);
            }
            catch (SQLiteException ex) {
                Cursor rawQuery = cursor;
                if (this.loggingEnabled) {
                    ex.printStackTrace();
                    rawQuery = cursor;
                    return rawQuery;
                }
                return rawQuery;
            }
        }
    }
    
    Cursor get(final long n) {
        final Cursor cursor = null;
        synchronized (this) {
            try {
                return this.db.rawQuery("SELECT * FROM requests WHERE _id = " + n, (String[])null);
            }
            catch (SQLiteException ex) {
                Cursor rawQuery = cursor;
                if (this.loggingEnabled) {
                    ex.printStackTrace();
                    rawQuery = cursor;
                    return rawQuery;
                }
                return rawQuery;
            }
        }
    }
    
    Cursor get(final long[] array) {
        final Cursor cursor = null;
        synchronized (this) {
            try {
                final StringBuilder sb = new StringBuilder();
                sb.append('(');
                if (array.length > 0) {
                    for (int length = array.length, i = 0; i < length; ++i) {
                        sb.append(array[i]).append(',');
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append(')');
                return this.db.rawQuery("SELECT * FROM requests WHERE _id IN " + sb.toString(), (String[])null);
            }
            catch (SQLiteException ex) {
                Cursor rawQuery = cursor;
                if (this.loggingEnabled) {
                    ex.printStackTrace();
                    rawQuery = cursor;
                    return rawQuery;
                }
                return rawQuery;
            }
        }
    }
    
    Cursor getByStatus(final int n) {
        final Cursor cursor = null;
        synchronized (this) {
            try {
                return this.db.rawQuery("SELECT * FROM requests WHERE _status = " + n, (String[])null);
            }
            catch (SQLiteException ex) {
                Cursor rawQuery = cursor;
                if (this.loggingEnabled) {
                    ex.printStackTrace();
                    rawQuery = cursor;
                    return rawQuery;
                }
                return rawQuery;
            }
        }
    }
    
    Cursor getByUrlAndFilePath(final String s, final String s2) {
        final Cursor cursor = null;
        synchronized (this) {
            try {
                return this.db.rawQuery("SELECT * FROM requests WHERE _url = " + DatabaseUtils.sqlEscapeString(s) + " AND " + "_file_path" + " = " + DatabaseUtils.sqlEscapeString(s2) + " LIMIT 1", (String[])null);
            }
            catch (SQLiteException ex) {
                Cursor rawQuery = cursor;
                if (this.loggingEnabled) {
                    ex.printStackTrace();
                    rawQuery = cursor;
                    return rawQuery;
                }
                return rawQuery;
            }
        }
    }
    
    String getInsertStatementClose() {
        return ";";
    }
    
    String getInsertStatementOpen() {
        return "INSERT INTO requests ( _id, _url, _file_path, _status, _headers, _written_bytes, _file_size, _error, _priority ) VALUES ";
    }
    
    Cursor getNextPendingRequest() {
        synchronized (this) {
            Cursor cursor = this.db.rawQuery("SELECT * FROM requests WHERE _status = 900 AND _priority = 601 LIMIT 1", (String[])null);
            if (cursor == null || cursor.getCount() <= 0) {
                if (cursor != null) {
                    cursor.close();
                }
                cursor = this.db.rawQuery("SELECT * FROM requests WHERE _status = 900 LIMIT 1", (String[])null);
            }
            return cursor;
        }
    }
    
    String getRowInsertStatement(final long n, final String s, final String s2, final int n2, final String s3, final long n3, final long n4, final int n5, final int n6) {
        return "( " + n + ", " + DatabaseUtils.sqlEscapeString(s) + ", " + DatabaseUtils.sqlEscapeString(s2) + ", " + n2 + ", " + DatabaseUtils.sqlEscapeString(s3) + ", " + n3 + ", " + n4 + ", " + n6 + ", " + n5 + " )";
    }
    
    boolean hasPendingRequests() {
        synchronized (this) {
            final Cursor rawQuery = this.db.rawQuery("SELECT _id FROM requests WHERE _status = 900 LIMIT 1", (String[])null);
            boolean b = false;
            if (rawQuery != null) {
                b = b;
                if (rawQuery.getCount() > 0) {
                    b = true;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return b;
        }
    }
    
    boolean insert(final long n, final String s, final String s2, final int n2, final String s3, final long n3, final long n4, final int n5, final int n6) {
        synchronized (this) {
            return this.insert(this.getInsertStatementOpen() + this.getRowInsertStatement(n, s, s2, n2, s3, n3, n4, n5, n6) + this.getInsertStatementClose());
        }
    }
    
    boolean insert(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: aload_1        
        //     3: ifnonnull       12
        //     6: iconst_0       
        //     7: istore_2       
        //     8: aload_0        
        //     9: monitorexit    
        //    10: iload_2        
        //    11: ireturn        
        //    12: aload_0        
        //    13: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    16: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    19: aload_0        
        //    20: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    23: aload_1        
        //    24: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    27: aload_0        
        //    28: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    31: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    34: aload_0        
        //    35: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    38: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    41: iconst_1       
        //    42: istore_2       
        //    43: goto            8
        //    46: astore_1       
        //    47: aload_0        
        //    48: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    51: ifeq            58
        //    54: aload_1        
        //    55: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //    58: new             Lcom/tonyodev/fetch/exception/EnqueueException;
        //    61: dup            
        //    62: aload_1        
        //    63: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //    66: aload_1        
        //    67: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //    70: invokestatic    com/tonyodev/fetch/ErrorUtils.getCode:(Ljava/lang/String;)I
        //    73: invokespecial   com/tonyodev/fetch/exception/EnqueueException.<init>:(Ljava/lang/String;I)V
        //    76: athrow         
        //    77: astore_1       
        //    78: aload_0        
        //    79: monitorexit    
        //    80: aload_1        
        //    81: athrow         
        //    82: astore_1       
        //    83: aload_0        
        //    84: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    87: ifeq            94
        //    90: aload_1        
        //    91: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    94: new             Lcom/tonyodev/fetch/exception/EnqueueException;
        //    97: dup            
        //    98: aload_1        
        //    99: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   102: aload_1        
        //   103: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   106: invokestatic    com/tonyodev/fetch/ErrorUtils.getCode:(Ljava/lang/String;)I
        //   109: invokespecial   com/tonyodev/fetch/exception/EnqueueException.<init>:(Ljava/lang/String;I)V
        //   112: athrow         
        //   113: astore_1       
        //   114: aload_0        
        //   115: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //   118: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   121: aload_1        
        //   122: athrow         
        //   123: astore_1       
        //   124: aload_0        
        //   125: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   128: ifeq            135
        //   131: aload_1        
        //   132: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   135: new             Lcom/tonyodev/fetch/exception/EnqueueException;
        //   138: dup            
        //   139: aload_1        
        //   140: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   143: aload_1        
        //   144: invokevirtual   android/database/sqlite/SQLiteException.getMessage:()Ljava/lang/String;
        //   147: invokestatic    com/tonyodev/fetch/ErrorUtils.getCode:(Ljava/lang/String;)I
        //   150: invokespecial   com/tonyodev/fetch/exception/EnqueueException.<init>:(Ljava/lang/String;I)V
        //   153: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  12     34     82     113    Ljava/lang/Exception;
        //  12     34     113    154    Any
        //  34     41     46     77     Landroid/database/sqlite/SQLiteException;
        //  34     41     77     82     Any
        //  47     58     77     82     Any
        //  58     77     77     82     Any
        //  83     94     113    154    Any
        //  94     113    113    154    Any
        //  114    121    123    154    Landroid/database/sqlite/SQLiteException;
        //  114    121    77     82     Any
        //  121    123    77     82     Any
        //  124    135    77     82     Any
        //  135    154    77     82     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 82, Size: 82
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
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
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE requests ( _id INTEGER PRIMARY KEY NOT NULL, _url TEXT NOT NULL, _file_path TEXT NOT NULL, _status INTEGER NOT NULL, _headers TEXT NOT NULL, _written_bytes INTEGER NOT NULL, _file_size INTEGER NOT NULL, _error INTEGER NOT NULL, _priority INTEGER NOT NULL, unique( _file_path ) )");
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        switch (n) {
            default: {}
            case 1: {
                sqLiteDatabase.execSQL("CREATE UNIQUE INDEX table_unique ON requests ( _file_path)");
            }
        }
    }
    
    boolean pause(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore          6
        //     5: iconst_0       
        //     6: istore          5
        //     8: aload_0        
        //     9: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    12: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    15: aload_0        
        //    16: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    19: new             Ljava/lang/StringBuilder;
        //    22: dup            
        //    23: invokespecial   java/lang/StringBuilder.<init>:()V
        //    26: ldc_w           "UPDATE requests SET _status = 902 WHERE _id = "
        //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    32: lload_1        
        //    33: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    36: ldc             " AND "
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: ldc             "_status"
        //    43: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    46: ldc_w           " != "
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: sipush          903
        //    55: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    58: ldc             " AND "
        //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    63: ldc             "_status"
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: ldc_w           " != "
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: sipush          904
        //    77: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    80: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    83: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    86: aload_0        
        //    87: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    90: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    93: aconst_null    
        //    94: astore          10
        //    96: aconst_null    
        //    97: astore          9
        //    99: aload           9
        //   101: astore          8
        //   103: aload           10
        //   105: astore          7
        //   107: aload_0        
        //   108: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //   111: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   114: aload           9
        //   116: astore          8
        //   118: aload           10
        //   120: astore          7
        //   122: aload_0        
        //   123: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //   126: new             Ljava/lang/StringBuilder;
        //   129: dup            
        //   130: invokespecial   java/lang/StringBuilder.<init>:()V
        //   133: ldc_w           "SELECT _id FROM requests WHERE _id = "
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: lload_1        
        //   140: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   143: ldc             " AND "
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: ldc             "_status"
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: ldc             " = "
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: sipush          902
        //   161: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   164: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   167: aconst_null    
        //   168: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   171: astore          9
        //   173: iload           5
        //   175: istore          4
        //   177: aload           9
        //   179: ifnull          209
        //   182: aload           9
        //   184: astore          8
        //   186: aload           9
        //   188: astore          7
        //   190: aload           9
        //   192: invokeinterface android/database/Cursor.getCount:()I
        //   197: istore_3       
        //   198: iload           5
        //   200: istore          4
        //   202: iload_3        
        //   203: ifle            209
        //   206: iconst_1       
        //   207: istore          4
        //   209: iload           4
        //   211: istore          5
        //   213: aload           9
        //   215: ifnull          229
        //   218: aload           9
        //   220: invokeinterface android/database/Cursor.close:()V
        //   225: iload           4
        //   227: istore          5
        //   229: aload_0        
        //   230: monitorexit    
        //   231: iload           5
        //   233: ireturn        
        //   234: astore          7
        //   236: aload_0        
        //   237: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   240: ifeq            93
        //   243: aload           7
        //   245: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   248: goto            93
        //   251: astore          7
        //   253: aload_0        
        //   254: monitorexit    
        //   255: aload           7
        //   257: athrow         
        //   258: astore          9
        //   260: aload           8
        //   262: astore          7
        //   264: aload_0        
        //   265: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   268: ifeq            280
        //   271: aload           8
        //   273: astore          7
        //   275: aload           9
        //   277: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   280: iload           6
        //   282: istore          5
        //   284: aload           8
        //   286: ifnull          229
        //   289: aload           8
        //   291: invokeinterface android/database/Cursor.close:()V
        //   296: iload           6
        //   298: istore          5
        //   300: goto            229
        //   303: astore          8
        //   305: aload           7
        //   307: ifnull          317
        //   310: aload           7
        //   312: invokeinterface android/database/Cursor.close:()V
        //   317: aload           8
        //   319: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  8      93     234    251    Landroid/database/sqlite/SQLiteException;
        //  8      93     251    258    Any
        //  107    114    258    303    Landroid/database/sqlite/SQLiteException;
        //  107    114    303    320    Any
        //  122    173    258    303    Landroid/database/sqlite/SQLiteException;
        //  122    173    303    320    Any
        //  190    198    258    303    Landroid/database/sqlite/SQLiteException;
        //  190    198    303    320    Any
        //  218    225    251    258    Any
        //  236    248    251    258    Any
        //  264    271    303    320    Any
        //  275    280    303    320    Any
        //  289    296    251    258    Any
        //  310    317    251    258    Any
        //  317    320    251    258    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0209:
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
    
    boolean resume(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore          6
        //     5: iconst_0       
        //     6: istore          5
        //     8: aload_0        
        //     9: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    12: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    15: aload_0        
        //    16: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    19: new             Ljava/lang/StringBuilder;
        //    22: dup            
        //    23: invokespecial   java/lang/StringBuilder.<init>:()V
        //    26: ldc_w           "UPDATE requests SET _status = 900 WHERE _id = "
        //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    32: lload_1        
        //    33: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    36: ldc             " AND "
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: ldc             "_status"
        //    43: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    46: ldc_w           " != "
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: sipush          903
        //    55: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    58: ldc             " AND "
        //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    63: ldc             "_status"
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: ldc_w           " != "
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: sipush          904
        //    77: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    80: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    83: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    86: aload_0        
        //    87: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    90: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    93: aconst_null    
        //    94: astore          10
        //    96: aconst_null    
        //    97: astore          9
        //    99: aload           9
        //   101: astore          8
        //   103: aload           10
        //   105: astore          7
        //   107: aload_0        
        //   108: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //   111: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   114: aload           9
        //   116: astore          8
        //   118: aload           10
        //   120: astore          7
        //   122: aload_0        
        //   123: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //   126: new             Ljava/lang/StringBuilder;
        //   129: dup            
        //   130: invokespecial   java/lang/StringBuilder.<init>:()V
        //   133: ldc_w           "SELECT _id FROM requests WHERE _id = "
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: lload_1        
        //   140: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   143: ldc             " AND "
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: ldc             "_status"
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: ldc             " = "
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: sipush          900
        //   161: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   164: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   167: aconst_null    
        //   168: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   171: astore          9
        //   173: iload           5
        //   175: istore          4
        //   177: aload           9
        //   179: ifnull          209
        //   182: aload           9
        //   184: astore          8
        //   186: aload           9
        //   188: astore          7
        //   190: aload           9
        //   192: invokeinterface android/database/Cursor.getCount:()I
        //   197: istore_3       
        //   198: iload           5
        //   200: istore          4
        //   202: iload_3        
        //   203: ifle            209
        //   206: iconst_1       
        //   207: istore          4
        //   209: iload           4
        //   211: istore          5
        //   213: aload           9
        //   215: ifnull          229
        //   218: aload           9
        //   220: invokeinterface android/database/Cursor.close:()V
        //   225: iload           4
        //   227: istore          5
        //   229: aload_0        
        //   230: monitorexit    
        //   231: iload           5
        //   233: ireturn        
        //   234: astore          7
        //   236: aload_0        
        //   237: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   240: ifeq            93
        //   243: aload           7
        //   245: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   248: goto            93
        //   251: astore          7
        //   253: aload_0        
        //   254: monitorexit    
        //   255: aload           7
        //   257: athrow         
        //   258: astore          9
        //   260: aload           8
        //   262: astore          7
        //   264: aload_0        
        //   265: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   268: ifeq            280
        //   271: aload           8
        //   273: astore          7
        //   275: aload           9
        //   277: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   280: iload           6
        //   282: istore          5
        //   284: aload           8
        //   286: ifnull          229
        //   289: aload           8
        //   291: invokeinterface android/database/Cursor.close:()V
        //   296: iload           6
        //   298: istore          5
        //   300: goto            229
        //   303: astore          8
        //   305: aload           7
        //   307: ifnull          317
        //   310: aload           7
        //   312: invokeinterface android/database/Cursor.close:()V
        //   317: aload           8
        //   319: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  8      93     234    251    Landroid/database/sqlite/SQLiteException;
        //  8      93     251    258    Any
        //  107    114    258    303    Landroid/database/sqlite/SQLiteException;
        //  107    114    303    320    Any
        //  122    173    258    303    Landroid/database/sqlite/SQLiteException;
        //  122    173    303    320    Any
        //  190    198    258    303    Landroid/database/sqlite/SQLiteException;
        //  190    198    303    320    Any
        //  218    225    251    258    Any
        //  236    248    251    258    Any
        //  264    271    303    320    Any
        //  275    280    303    320    Any
        //  289    296    251    258    Any
        //  310    317    251    258    Any
        //  317    320    251    258    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0209:
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
    
    boolean retry(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore          6
        //     5: iconst_0       
        //     6: istore          5
        //     8: aload_0        
        //     9: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    12: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    15: aload_0        
        //    16: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    19: new             Ljava/lang/StringBuilder;
        //    22: dup            
        //    23: invokespecial   java/lang/StringBuilder.<init>:()V
        //    26: ldc_w           "UPDATE requests SET _status = 900, _error = -1 WHERE _id = "
        //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    32: lload_1        
        //    33: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    36: ldc             " AND "
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: ldc             "_status"
        //    43: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    46: ldc             " = "
        //    48: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    51: sipush          904
        //    54: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    57: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    60: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    63: aload_0        
        //    64: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    67: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    70: aconst_null    
        //    71: astore          10
        //    73: aconst_null    
        //    74: astore          9
        //    76: aload           9
        //    78: astore          8
        //    80: aload           10
        //    82: astore          7
        //    84: aload_0        
        //    85: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    88: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    91: aload           9
        //    93: astore          8
        //    95: aload           10
        //    97: astore          7
        //    99: aload_0        
        //   100: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //   103: new             Ljava/lang/StringBuilder;
        //   106: dup            
        //   107: invokespecial   java/lang/StringBuilder.<init>:()V
        //   110: ldc_w           "SELECT _id FROM requests WHERE _id = "
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: lload_1        
        //   117: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   120: ldc             " AND "
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: ldc             "_status"
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: ldc             " = "
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: sipush          900
        //   138: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   141: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   144: aconst_null    
        //   145: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   148: astore          9
        //   150: iload           5
        //   152: istore          4
        //   154: aload           9
        //   156: ifnull          186
        //   159: aload           9
        //   161: astore          8
        //   163: aload           9
        //   165: astore          7
        //   167: aload           9
        //   169: invokeinterface android/database/Cursor.getCount:()I
        //   174: istore_3       
        //   175: iload           5
        //   177: istore          4
        //   179: iload_3        
        //   180: ifle            186
        //   183: iconst_1       
        //   184: istore          4
        //   186: iload           4
        //   188: istore          5
        //   190: aload           9
        //   192: ifnull          206
        //   195: aload           9
        //   197: invokeinterface android/database/Cursor.close:()V
        //   202: iload           4
        //   204: istore          5
        //   206: aload_0        
        //   207: monitorexit    
        //   208: iload           5
        //   210: ireturn        
        //   211: astore          7
        //   213: aload_0        
        //   214: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   217: ifeq            70
        //   220: aload           7
        //   222: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   225: goto            70
        //   228: astore          7
        //   230: aload_0        
        //   231: monitorexit    
        //   232: aload           7
        //   234: athrow         
        //   235: astore          9
        //   237: aload           8
        //   239: astore          7
        //   241: aload_0        
        //   242: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   245: ifeq            257
        //   248: aload           8
        //   250: astore          7
        //   252: aload           9
        //   254: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   257: iload           6
        //   259: istore          5
        //   261: aload           8
        //   263: ifnull          206
        //   266: aload           8
        //   268: invokeinterface android/database/Cursor.close:()V
        //   273: iload           6
        //   275: istore          5
        //   277: goto            206
        //   280: astore          8
        //   282: aload           7
        //   284: ifnull          294
        //   287: aload           7
        //   289: invokeinterface android/database/Cursor.close:()V
        //   294: aload           8
        //   296: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  8      70     211    228    Landroid/database/sqlite/SQLiteException;
        //  8      70     228    235    Any
        //  84     91     235    280    Landroid/database/sqlite/SQLiteException;
        //  84     91     280    297    Any
        //  99     150    235    280    Landroid/database/sqlite/SQLiteException;
        //  99     150    280    297    Any
        //  167    175    235    280    Landroid/database/sqlite/SQLiteException;
        //  167    175    280    297    Any
        //  195    202    228    235    Any
        //  213    225    228    235    Any
        //  241    248    280    297    Any
        //  252    257    280    297    Any
        //  266    273    228    235    Any
        //  287    294    228    235    Any
        //  294    297    228    235    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0186:
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
    
    void setLoggingEnabled(final boolean loggingEnabled) {
        synchronized (this) {
            this.loggingEnabled = loggingEnabled;
        }
    }
    
    boolean setPriority(final long p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore          5
        //     5: aload_0        
        //     6: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //     9: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    12: aload_0        
        //    13: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    16: new             Ljava/lang/StringBuilder;
        //    19: dup            
        //    20: invokespecial   java/lang/StringBuilder.<init>:()V
        //    23: ldc_w           "UPDATE requests SET _priority = "
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: iload_3        
        //    30: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    33: ldc_w           " WHERE "
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    39: ldc             "_id"
        //    41: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    44: ldc             " = "
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    49: lload_1        
        //    50: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    53: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    56: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    59: aload_0        
        //    60: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    63: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    66: aload_0        
        //    67: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    70: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    73: iconst_1       
        //    74: istore          4
        //    76: aload_0        
        //    77: monitorexit    
        //    78: iload           4
        //    80: ireturn        
        //    81: astore          6
        //    83: aload_0        
        //    84: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    87: ifeq            66
        //    90: aload           6
        //    92: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //    95: goto            66
        //    98: astore          6
        //   100: aload_0        
        //   101: monitorexit    
        //   102: aload           6
        //   104: athrow         
        //   105: astore          6
        //   107: iload           5
        //   109: istore          4
        //   111: aload_0        
        //   112: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   115: ifeq            76
        //   118: aload           6
        //   120: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   123: iload           5
        //   125: istore          4
        //   127: goto            76
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  5      66     81     98     Landroid/database/sqlite/SQLiteException;
        //  5      66     98     105    Any
        //  66     73     105    130    Landroid/database/sqlite/SQLiteException;
        //  66     73     98     105    Any
        //  83     95     98     105    Any
        //  111    123    98     105    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    
    boolean updateFileBytes(final long p0, final long p1, final long p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore          8
        //     5: aload_0        
        //     6: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //     9: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    12: aload_0        
        //    13: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    16: new             Ljava/lang/StringBuilder;
        //    19: dup            
        //    20: invokespecial   java/lang/StringBuilder.<init>:()V
        //    23: ldc_w           "UPDATE requests SET _file_size = "
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: lload           5
        //    31: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    34: ldc             ", "
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    39: ldc             "_written_bytes"
        //    41: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    44: ldc             " = "
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    49: lload_3        
        //    50: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    53: ldc_w           " WHERE "
        //    56: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    59: ldc             "_id"
        //    61: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    64: ldc             " = "
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: lload_1        
        //    70: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    73: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    76: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    79: aload_0        
        //    80: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    83: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    86: aload_0        
        //    87: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    90: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    93: iconst_1       
        //    94: istore          7
        //    96: aload_0        
        //    97: monitorexit    
        //    98: iload           7
        //   100: ireturn        
        //   101: astore          9
        //   103: aload_0        
        //   104: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   107: ifeq            86
        //   110: aload           9
        //   112: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   115: goto            86
        //   118: astore          9
        //   120: aload_0        
        //   121: monitorexit    
        //   122: aload           9
        //   124: athrow         
        //   125: astore          9
        //   127: iload           8
        //   129: istore          7
        //   131: aload_0        
        //   132: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   135: ifeq            96
        //   138: aload           9
        //   140: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   143: iload           8
        //   145: istore          7
        //   147: goto            96
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  5      86     101    118    Landroid/database/sqlite/SQLiteException;
        //  5      86     118    125    Any
        //  86     93     125    150    Landroid/database/sqlite/SQLiteException;
        //  86     93     118    125    Any
        //  103    115    118    125    Any
        //  131    143    118    125    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0086:
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
    
    boolean updateStatus(final long p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore          6
        //     5: aload_0        
        //     6: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //     9: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    12: aload_0        
        //    13: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    16: new             Ljava/lang/StringBuilder;
        //    19: dup            
        //    20: invokespecial   java/lang/StringBuilder.<init>:()V
        //    23: ldc_w           "UPDATE requests SET _status = "
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: iload_3        
        //    30: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    33: ldc             ", "
        //    35: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    38: ldc             "_error"
        //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    43: ldc             " = "
        //    45: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    48: iload           4
        //    50: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    53: ldc_w           " WHERE "
        //    56: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    59: ldc             "_id"
        //    61: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    64: ldc             " = "
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: lload_1        
        //    70: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    73: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    76: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    79: aload_0        
        //    80: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    83: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    86: aload_0        
        //    87: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    90: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    93: iconst_1       
        //    94: istore          5
        //    96: aload_0        
        //    97: monitorexit    
        //    98: iload           5
        //   100: ireturn        
        //   101: astore          7
        //   103: aload_0        
        //   104: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   107: ifeq            86
        //   110: aload           7
        //   112: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   115: goto            86
        //   118: astore          7
        //   120: aload_0        
        //   121: monitorexit    
        //   122: aload           7
        //   124: athrow         
        //   125: astore          7
        //   127: iload           6
        //   129: istore          5
        //   131: aload_0        
        //   132: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   135: ifeq            96
        //   138: aload           7
        //   140: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   143: iload           6
        //   145: istore          5
        //   147: goto            96
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  5      86     101    118    Landroid/database/sqlite/SQLiteException;
        //  5      86     118    125    Any
        //  86     93     125    150    Landroid/database/sqlite/SQLiteException;
        //  86     93     118    125    Any
        //  103    115    118    125    Any
        //  131    143    118    125    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0086:
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
    
    boolean updateUrl(final long p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: iconst_0       
        //     3: istore          7
        //     5: iconst_0       
        //     6: istore          6
        //     8: aload_0        
        //     9: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    12: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    15: aload_0        
        //    16: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    19: new             Ljava/lang/StringBuilder;
        //    22: dup            
        //    23: invokespecial   java/lang/StringBuilder.<init>:()V
        //    26: ldc_w           "UPDATE requests SET _url = "
        //    29: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    32: aload_3        
        //    33: invokestatic    android/database/DatabaseUtils.sqlEscapeString:(Ljava/lang/String;)Ljava/lang/String;
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    39: ldc_w           " WHERE "
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    45: ldc             "_id"
        //    47: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    50: ldc             " = "
        //    52: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    55: lload_1        
        //    56: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    59: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    62: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    65: aload_0        
        //    66: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    69: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    72: aconst_null    
        //    73: astore          11
        //    75: aconst_null    
        //    76: astore          10
        //    78: aload           10
        //    80: astore          9
        //    82: aload           11
        //    84: astore          8
        //    86: aload_0        
        //    87: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    90: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    93: aload           10
        //    95: astore          9
        //    97: aload           11
        //    99: astore          8
        //   101: aload_0        
        //   102: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //   105: new             Ljava/lang/StringBuilder;
        //   108: dup            
        //   109: invokespecial   java/lang/StringBuilder.<init>:()V
        //   112: ldc_w           "SELECT _id FROM requests WHERE _id = "
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: lload_1        
        //   119: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   122: ldc             " AND "
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: ldc             "_url"
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: ldc             " = "
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: aload_3        
        //   138: invokestatic    android/database/DatabaseUtils.sqlEscapeString:(Ljava/lang/String;)Ljava/lang/String;
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   147: aconst_null    
        //   148: invokevirtual   android/database/sqlite/SQLiteDatabase.rawQuery:(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
        //   151: astore_3       
        //   152: iload           6
        //   154: istore          5
        //   156: aload_3        
        //   157: ifnull          186
        //   160: aload_3        
        //   161: astore          9
        //   163: aload_3        
        //   164: astore          8
        //   166: aload_3        
        //   167: invokeinterface android/database/Cursor.getCount:()I
        //   172: istore          4
        //   174: iload           6
        //   176: istore          5
        //   178: iload           4
        //   180: ifle            186
        //   183: iconst_1       
        //   184: istore          5
        //   186: iload           5
        //   188: istore          6
        //   190: aload_3        
        //   191: ifnull          204
        //   194: aload_3        
        //   195: invokeinterface android/database/Cursor.close:()V
        //   200: iload           5
        //   202: istore          6
        //   204: aload_0        
        //   205: monitorexit    
        //   206: iload           6
        //   208: ireturn        
        //   209: astore          8
        //   211: aload_0        
        //   212: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   215: ifeq            72
        //   218: aload           8
        //   220: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   223: goto            72
        //   226: astore_3       
        //   227: aload_0        
        //   228: monitorexit    
        //   229: aload_3        
        //   230: athrow         
        //   231: astore_3       
        //   232: aload           9
        //   234: astore          8
        //   236: aload_0        
        //   237: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //   240: ifeq            251
        //   243: aload           9
        //   245: astore          8
        //   247: aload_3        
        //   248: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //   251: iload           7
        //   253: istore          6
        //   255: aload           9
        //   257: ifnull          204
        //   260: aload           9
        //   262: invokeinterface android/database/Cursor.close:()V
        //   267: iload           7
        //   269: istore          6
        //   271: goto            204
        //   274: astore_3       
        //   275: aload           8
        //   277: ifnull          287
        //   280: aload           8
        //   282: invokeinterface android/database/Cursor.close:()V
        //   287: aload_3        
        //   288: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  8      72     209    226    Landroid/database/sqlite/SQLiteException;
        //  8      72     226    231    Any
        //  86     93     231    274    Landroid/database/sqlite/SQLiteException;
        //  86     93     274    289    Any
        //  101    152    231    274    Landroid/database/sqlite/SQLiteException;
        //  101    152    274    289    Any
        //  166    174    231    274    Landroid/database/sqlite/SQLiteException;
        //  166    174    274    289    Any
        //  194    200    226    231    Any
        //  211    223    226    231    Any
        //  236    243    274    289    Any
        //  247    251    274    289    Any
        //  260    267    226    231    Any
        //  280    287    226    231    Any
        //  287    289    226    231    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0186:
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
    
    void verifyOK() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: aload_0        
        //     3: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //     6: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //     9: aload_0        
        //    10: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    13: ldc_w           "UPDATE requests SET _status = 900 WHERE _status = 901"
        //    16: invokevirtual   android/database/sqlite/SQLiteDatabase.execSQL:(Ljava/lang/String;)V
        //    19: aload_0        
        //    20: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    23: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //    26: aload_0        
        //    27: getfield        com/tonyodev/fetch/DatabaseHelper.db:Landroid/database/sqlite/SQLiteDatabase;
        //    30: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    33: aload_0        
        //    34: monitorexit    
        //    35: return         
        //    36: astore_1       
        //    37: aload_0        
        //    38: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    41: ifeq            26
        //    44: aload_1        
        //    45: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //    48: goto            26
        //    51: astore_1       
        //    52: aload_0        
        //    53: monitorexit    
        //    54: aload_1        
        //    55: athrow         
        //    56: astore_1       
        //    57: aload_0        
        //    58: getfield        com/tonyodev/fetch/DatabaseHelper.loggingEnabled:Z
        //    61: ifeq            33
        //    64: aload_1        
        //    65: invokevirtual   android/database/sqlite/SQLiteException.printStackTrace:()V
        //    68: goto            33
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  2      26     36     51     Landroid/database/sqlite/SQLiteException;
        //  2      26     51     56     Any
        //  26     33     56     71     Landroid/database/sqlite/SQLiteException;
        //  26     33     51     56     Any
        //  37     48     51     56     Any
        //  57     68     51     56     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
