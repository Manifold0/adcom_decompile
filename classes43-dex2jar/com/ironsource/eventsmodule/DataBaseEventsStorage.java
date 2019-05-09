// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.eventsmodule;

import android.provider.BaseColumns;
import java.util.List;
import android.database.Cursor;
import android.util.Log;
import org.json.JSONObject;
import java.util.ArrayList;
import android.os.SystemClock;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseEventsStorage extends SQLiteOpenHelper implements IEventsStorageHelper
{
    private static final String COMMA_SEP = ",";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String TYPE_TEXT = " TEXT";
    private static DataBaseEventsStorage mInstance;
    private final int DB_OPEN_BACKOFF_TIME;
    private final int DB_RETRY_NUM;
    private final String SQL_CREATE_ENTRIES;
    private final String SQL_DELETE_TABLE;
    
    public DataBaseEventsStorage(final Context context, final String s, final int n) {
        super(context, s, (SQLiteDatabase$CursorFactory)null, n);
        this.DB_RETRY_NUM = 4;
        this.DB_OPEN_BACKOFF_TIME = 400;
        this.SQL_DELETE_TABLE = "DROP TABLE IF EXISTS events";
        this.SQL_CREATE_ENTRIES = "CREATE TABLE events (_id INTEGER PRIMARY KEY,eventid INTEGER,timestamp INTEGER,type TEXT,data TEXT )";
    }
    
    private ContentValues getContentValuesForEvent(final EventData eventData, final String s) {
        ContentValues contentValues = null;
        if (eventData != null) {
            contentValues = new ContentValues(4);
            contentValues.put("eventid", Integer.valueOf(eventData.getEventId()));
            contentValues.put("timestamp", Long.valueOf(eventData.getTimeStamp()));
            contentValues.put("type", s);
            contentValues.put("data", eventData.getAdditionalData());
        }
        return contentValues;
    }
    
    private SQLiteDatabase getDataBaseWithRetries(final boolean b) throws Throwable {
        // monitorenter(this)
        int n = 0;
        while (true) {
            Label_0019: {
                if (!b) {
                    break Label_0019;
                }
                try {
                    return this.getWritableDatabase();
                    sqLiteDatabase = this.getReadableDatabase();
                    return sqLiteDatabase;
                }
                catch (Throwable t) {
                    ++n;
                    if (n >= 4) {
                        throw t;
                    }
                }
                finally {
                }
                // monitorexit(this)
            }
            SystemClock.sleep((long)(n * 400));
        }
    }
    
    public static DataBaseEventsStorage getInstance(final Context context, final String s, final int n) {
        synchronized (DataBaseEventsStorage.class) {
            if (DataBaseEventsStorage.mInstance == null) {
                DataBaseEventsStorage.mInstance = new DataBaseEventsStorage(context, s, n);
            }
            return DataBaseEventsStorage.mInstance;
        }
    }
    
    public void clearEvents(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aconst_null    
        //     3: astore_3       
        //     4: aconst_null    
        //     5: astore_2       
        //     6: aload_0        
        //     7: iconst_1       
        //     8: invokespecial   com/ironsource/eventsmodule/DataBaseEventsStorage.getDataBaseWithRetries:(Z)Landroid/database/sqlite/SQLiteDatabase;
        //    11: astore          4
        //    13: aload           4
        //    15: astore_2       
        //    16: aload           4
        //    18: astore_3       
        //    19: aload           4
        //    21: ldc             "events"
        //    23: ldc             "type = ?"
        //    25: iconst_1       
        //    26: anewarray       Ljava/lang/String;
        //    29: dup            
        //    30: iconst_0       
        //    31: aload_1        
        //    32: aastore        
        //    33: invokevirtual   android/database/sqlite/SQLiteDatabase.delete:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
        //    36: pop            
        //    37: aload           4
        //    39: ifnull          55
        //    42: aload           4
        //    44: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //    47: ifeq            55
        //    50: aload           4
        //    52: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //    55: aload_0        
        //    56: monitorexit    
        //    57: return         
        //    58: astore_1       
        //    59: aload_2        
        //    60: astore_3       
        //    61: ldc             "IronSource"
        //    63: ldc             "Exception while clearing events: "
        //    65: aload_1        
        //    66: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    69: pop            
        //    70: aload_2        
        //    71: ifnull          55
        //    74: aload_2        
        //    75: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //    78: ifeq            55
        //    81: aload_2        
        //    82: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //    85: goto            55
        //    88: astore_1       
        //    89: aload_0        
        //    90: monitorexit    
        //    91: aload_1        
        //    92: athrow         
        //    93: astore_1       
        //    94: aload_3        
        //    95: ifnull          109
        //    98: aload_3        
        //    99: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //   102: ifeq            109
        //   105: aload_3        
        //   106: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   109: aload_1        
        //   110: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      13     58     88     Ljava/lang/Throwable;
        //  6      13     93     111    Any
        //  19     37     58     88     Ljava/lang/Throwable;
        //  19     37     93     111    Any
        //  42     55     88     93     Any
        //  61     70     93     111    Any
        //  74     85     88     93     Any
        //  98     109    88     93     Any
        //  109    111    88     93     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
    
    public ArrayList<EventData> loadEvents(final String s) {
        // monitorenter(this)
        SQLiteDatabase sqLiteDatabase = null;
        SQLiteDatabase dataBaseWithRetries = null;
        final Cursor cursor = null;
        final Cursor cursor2 = null;
        try {
            final ArrayList<EventData> list = new ArrayList<EventData>();
            Cursor cursor3 = cursor2;
            Cursor cursor4 = cursor;
            Label_0285: {
                try {
                    final SQLiteDatabase sqLiteDatabase2 = dataBaseWithRetries = this.getDataBaseWithRetries((boolean)(0 != 0));
                    cursor3 = cursor2;
                    sqLiteDatabase = sqLiteDatabase2;
                    cursor4 = cursor;
                    final Cursor query = sqLiteDatabase2.query("events", (String[])null, "type = ?", new String[] { s }, (String)null, (String)null, "timestamp ASC");
                    dataBaseWithRetries = sqLiteDatabase2;
                    cursor3 = query;
                    sqLiteDatabase = sqLiteDatabase2;
                    cursor4 = query;
                    if (query.getCount() <= 0) {
                        break Label_0285;
                    }
                    dataBaseWithRetries = sqLiteDatabase2;
                    cursor3 = query;
                    sqLiteDatabase = sqLiteDatabase2;
                    cursor4 = query;
                    query.moveToFirst();
                    while (true) {
                        dataBaseWithRetries = sqLiteDatabase2;
                        cursor3 = query;
                        sqLiteDatabase = sqLiteDatabase2;
                        cursor4 = query;
                        if (query.isAfterLast()) {
                            break Label_0285;
                        }
                        dataBaseWithRetries = sqLiteDatabase2;
                        cursor3 = query;
                        sqLiteDatabase = sqLiteDatabase2;
                        cursor4 = query;
                        list.add(new EventData(query.getInt(query.getColumnIndex("eventid")), query.getLong(query.getColumnIndex("timestamp")), new JSONObject(query.getString(query.getColumnIndex("data")))));
                        dataBaseWithRetries = sqLiteDatabase2;
                        cursor3 = query;
                        sqLiteDatabase = sqLiteDatabase2;
                        cursor4 = query;
                        query.moveToNext();
                    }
                }
                catch (Throwable t) {
                    sqLiteDatabase = dataBaseWithRetries;
                    cursor4 = cursor3;
                    Log.e("IronSource", "Exception while loading events: ", t);
                    if (cursor3 != null && !cursor3.isClosed()) {
                        cursor3.close();
                    }
                    if (dataBaseWithRetries != null && dataBaseWithRetries.isOpen()) {
                        dataBaseWithRetries.close();
                    }
                    Label_0280: {
                        return list;
                    }
                    // iftrue(Label_0280:, sqLiteDatabase2 == null || !sqLiteDatabase2.isOpen())
                    while (true) {
                        final SQLiteDatabase sqLiteDatabase2;
                        sqLiteDatabase2.close();
                        return list;
                        final Cursor cursor5;
                        cursor5.close();
                        Label_0322: {
                            break Label_0322;
                            sqLiteDatabase = sqLiteDatabase2;
                            cursor4 = cursor5;
                            cursor5.close();
                            break Label_0285;
                        }
                        continue;
                    }
                }
                // iftrue(Label_0322:, cursor5 == null || cursor5.isClosed())
                finally {
                    if (cursor4 != null && !cursor4.isClosed()) {
                        cursor4.close();
                    }
                    if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                        sqLiteDatabase.close();
                    }
                }
            }
        }
        finally {}
    }
    
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY,eventid INTEGER,timestamp INTEGER,type TEXT,data TEXT )");
    }
    
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        this.onCreate(sqLiteDatabase);
    }
    
    public void saveEvents(final List<EventData> p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_1        
        //     3: ifnull          17
        //     6: aload_1        
        //     7: invokeinterface java/util/List.isEmpty:()Z
        //    12: istore_3       
        //    13: iload_3        
        //    14: ifeq            20
        //    17: aload_0        
        //    18: monitorexit    
        //    19: return         
        //    20: aconst_null    
        //    21: astore          5
        //    23: aconst_null    
        //    24: astore          4
        //    26: aload_0        
        //    27: iconst_1       
        //    28: invokespecial   com/ironsource/eventsmodule/DataBaseEventsStorage.getDataBaseWithRetries:(Z)Landroid/database/sqlite/SQLiteDatabase;
        //    31: astore          6
        //    33: aload           6
        //    35: astore          4
        //    37: aload           6
        //    39: astore          5
        //    41: aload_1        
        //    42: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    47: astore_1       
        //    48: aload           6
        //    50: astore          4
        //    52: aload           6
        //    54: astore          5
        //    56: aload_1        
        //    57: invokeinterface java/util/Iterator.hasNext:()Z
        //    62: ifeq            161
        //    65: aload           6
        //    67: astore          4
        //    69: aload           6
        //    71: astore          5
        //    73: aload_0        
        //    74: aload_1        
        //    75: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    80: checkcast       Lcom/ironsource/eventsmodule/EventData;
        //    83: aload_2        
        //    84: invokespecial   com/ironsource/eventsmodule/DataBaseEventsStorage.getContentValuesForEvent:(Lcom/ironsource/eventsmodule/EventData;Ljava/lang/String;)Landroid/content/ContentValues;
        //    87: astore          7
        //    89: aload           6
        //    91: ifnull          48
        //    94: aload           7
        //    96: ifnull          48
        //    99: aload           6
        //   101: astore          4
        //   103: aload           6
        //   105: astore          5
        //   107: aload           6
        //   109: ldc             "events"
        //   111: aconst_null    
        //   112: aload           7
        //   114: invokevirtual   android/database/sqlite/SQLiteDatabase.insert:(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   117: pop2           
        //   118: goto            48
        //   121: astore_1       
        //   122: aload           4
        //   124: astore          5
        //   126: ldc             "IronSource"
        //   128: ldc             "Exception while saving events: "
        //   130: aload_1        
        //   131: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   134: pop            
        //   135: aload           4
        //   137: ifnull          17
        //   140: aload           4
        //   142: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //   145: ifeq            17
        //   148: aload           4
        //   150: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   153: goto            17
        //   156: astore_1       
        //   157: aload_0        
        //   158: monitorexit    
        //   159: aload_1        
        //   160: athrow         
        //   161: aload           6
        //   163: ifnull          17
        //   166: aload           6
        //   168: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //   171: ifeq            17
        //   174: aload           6
        //   176: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   179: goto            17
        //   182: astore_1       
        //   183: aload           5
        //   185: ifnull          201
        //   188: aload           5
        //   190: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
        //   193: ifeq            201
        //   196: aload           5
        //   198: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   201: aload_1        
        //   202: athrow         
        //    Signature:
        //  (Ljava/util/List<Lcom/ironsource/eventsmodule/EventData;>;Ljava/lang/String;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      13     156    161    Any
        //  26     33     121    156    Ljava/lang/Throwable;
        //  26     33     182    203    Any
        //  41     48     121    156    Ljava/lang/Throwable;
        //  41     48     182    203    Any
        //  56     65     121    156    Ljava/lang/Throwable;
        //  56     65     182    203    Any
        //  73     89     121    156    Ljava/lang/Throwable;
        //  73     89     182    203    Any
        //  107    118    121    156    Ljava/lang/Throwable;
        //  107    118    182    203    Any
        //  126    135    182    203    Any
        //  140    153    156    161    Any
        //  166    179    156    161    Any
        //  188    201    156    161    Any
        //  201    203    156    161    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    abstract static class EventEntry implements BaseColumns
    {
        public static final String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_EVENT_ID = "eventid";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final int NUMBER_OF_COLUMNS = 4;
        public static final String TABLE_NAME = "events";
    }
}
