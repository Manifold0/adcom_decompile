// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.b;

import com.kongregate.o.g.b;
import android.content.ContentValues;
import java.util.Iterator;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import java.util.HashMap;
import org.json.JSONObject;
import com.kongregate.android.internal.util.StringUtils;
import java.util.Map;
import android.content.Context;
import com.kongregate.o.e.a;
import java.text.ParseException;
import com.kongregate.android.internal.util.j;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import android.provider.BaseColumns;

public class e implements BaseColumns
{
    public static final String a = "statistic_records";
    public static final String b = "statistic_records_view";
    public static final String c = "statistic_id";
    public static final String d = "statistic_name";
    public static final String e = "user_id";
    public static final String f = "value";
    public static final String g = "server_value";
    public static final String h = "pending_value";
    public static final String i = "max_value";
    public static final String j = "min_value";
    public static final String k = "add_value";
    public static final String l = "replace_value";
    public static final String m = "sent_at";
    public static final String n = "dirty";
    protected static final AtomicReference<Boolean> o;
    protected static final AtomicReference<Boolean> p;
    protected static final AtomicBoolean q;
    protected static final TimeZone r;
    protected static final ThreadLocal<DateFormat> s;
    
    static {
        o = new AtomicReference<Boolean>(null);
        p = new AtomicReference<Boolean>(null);
        q = new AtomicBoolean(false);
        r = TimeZone.getTimeZone("America/Los_Angeles");
        s = new ThreadLocal<DateFormat>() {
            protected SimpleDateFormat a() {
                return new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            }
        };
    }
    
    public static long a() {
        return a(new Date());
    }
    
    public static long a(final SQLiteDatabase p0, final long p1, final long p2, final String p3, final long p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokestatic    com/kongregate/o/b/e.a:(J)Ljava/util/Date;
        //     6: astore          18
        //     8: new             Landroid/content/ContentValues;
        //    11: dup            
        //    12: invokespecial   android/content/ContentValues.<init>:()V
        //    15: astore          17
        //    17: new             Ljava/lang/StringBuilder;
        //    20: dup            
        //    21: invokespecial   java/lang/StringBuilder.<init>:()V
        //    24: ldc             "Statistic submit: "
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: aload           5
        //    31: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    34: ldc             "="
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    39: lload           6
        //    41: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    44: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    47: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //    50: aload_0        
        //    51: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    54: aload_0        
        //    55: ldc             "statistics"
        //    57: aconst_null    
        //    58: new             Ljava/lang/StringBuilder;
        //    61: dup            
        //    62: invokespecial   java/lang/StringBuilder.<init>:()V
        //    65: aload_0        
        //    66: invokestatic    com/kongregate/o/b/e.c:(Landroid/database/sqlite/SQLiteDatabase;)Ljava/lang/String;
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    72: ldc             "=? AND "
        //    74: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    77: ldc             "name"
        //    79: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    82: ldc             "=?"
        //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    87: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    90: iconst_2       
        //    91: anewarray       Ljava/lang/String;
        //    94: dup            
        //    95: iconst_0       
        //    96: lload_3        
        //    97: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   100: aastore        
        //   101: dup            
        //   102: iconst_1       
        //   103: aload           5
        //   105: aastore        
        //   106: aconst_null    
        //   107: aconst_null    
        //   108: aconst_null    
        //   109: aconst_null    
        //   110: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   113: astore          15
        //   115: aload           15
        //   117: ifnonnull       142
        //   120: ldc             "Statistic query failed"
        //   122: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   125: aconst_null    
        //   126: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   129: aload           15
        //   131: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   134: aload_0        
        //   135: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   138: ldc2_w          -1
        //   141: lreturn        
        //   142: aload           15
        //   144: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   149: istore          13
        //   151: aload_0        
        //   152: invokestatic    com/kongregate/o/b/e.d:(Landroid/database/sqlite/SQLiteDatabase;)Z
        //   155: istore          14
        //   157: new             Ljava/lang/StringBuilder;
        //   160: dup            
        //   161: invokespecial   java/lang/StringBuilder.<init>:()V
        //   164: lload_3        
        //   165: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   168: ldc             "-"
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: aload           5
        //   175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   181: astore          19
        //   183: new             Ljava/lang/StringBuilder;
        //   186: dup            
        //   187: invokespecial   java/lang/StringBuilder.<init>:()V
        //   190: ldc             "user_id=? AND "
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: astore          20
        //   197: iload           13
        //   199: ifne            1287
        //   202: iload           14
        //   204: ifne            318
        //   207: goto            1287
        //   210: aload           20
        //   212: aload           16
        //   214: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   217: ldc             ""
        //   219: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   222: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   225: astore          20
        //   227: iload           13
        //   229: ifne            237
        //   232: iload           14
        //   234: ifne            325
        //   237: iconst_2       
        //   238: anewarray       Ljava/lang/String;
        //   241: astore          16
        //   243: aload           16
        //   245: iconst_0       
        //   246: lload_1        
        //   247: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   250: aastore        
        //   251: aload           16
        //   253: iconst_1       
        //   254: aload           15
        //   256: aload           15
        //   258: ldc             "_id"
        //   260: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   265: invokeinterface android/database/Cursor.getLong:(I)J
        //   270: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   273: aastore        
        //   274: aload_0        
        //   275: ldc             "statistic_records"
        //   277: aconst_null    
        //   278: aload           20
        //   280: aload           16
        //   282: aconst_null    
        //   283: aconst_null    
        //   284: aconst_null    
        //   285: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   288: astore          16
        //   290: aload           16
        //   292: ifnonnull       378
        //   295: ldc             "StatisticRecord query failed"
        //   297: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   300: aload           16
        //   302: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   305: aload           15
        //   307: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   310: aload_0        
        //   311: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   314: ldc2_w          -1
        //   317: lreturn        
        //   318: ldc             "statistic_name=?"
        //   320: astore          16
        //   322: goto            210
        //   325: iconst_2       
        //   326: anewarray       Ljava/lang/String;
        //   329: astore          16
        //   331: aload           16
        //   333: iconst_0       
        //   334: lload_1        
        //   335: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   338: aastore        
        //   339: aload           16
        //   341: iconst_1       
        //   342: aload           19
        //   344: aastore        
        //   345: goto            274
        //   348: astore          5
        //   350: aconst_null    
        //   351: astore          17
        //   353: aload           15
        //   355: astore          16
        //   357: aload           17
        //   359: astore          15
        //   361: aload           15
        //   363: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   366: aload           16
        //   368: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   371: aload_0        
        //   372: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   375: aload           5
        //   377: athrow         
        //   378: aload           16
        //   380: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   385: ifne            675
        //   388: iconst_1       
        //   389: istore          8
        //   391: iload           13
        //   393: ifeq            810
        //   396: aload           15
        //   398: aload           15
        //   400: ldc             "stat_type"
        //   402: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   407: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   412: invokestatic    com/kongregate/o/b/d$a.a:(Ljava/lang/String;)Lcom/kongregate/o/b/d$a;
        //   415: astore          19
        //   417: aload           15
        //   419: aload           15
        //   421: ldc             "_id"
        //   423: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   428: invokeinterface android/database/Cursor.getLong:(I)J
        //   433: lstore          11
        //   435: iload           8
        //   437: ifne            459
        //   440: aload           16
        //   442: aload           16
        //   444: ldc             "value"
        //   446: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   451: invokeinterface android/database/Cursor.isNull:(I)Z
        //   456: ifeq            681
        //   459: aload           19
        //   461: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   464: lstore_3       
        //   465: iload           8
        //   467: ifne            489
        //   470: aload           16
        //   472: aload           16
        //   474: ldc             "server_value"
        //   476: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   481: invokeinterface android/database/Cursor.isNull:(I)Z
        //   486: ifeq            701
        //   489: aload           19
        //   491: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   494: lstore          9
        //   496: iload           8
        //   498: ifeq            722
        //   501: new             Ljava/util/Date;
        //   504: dup            
        //   505: lconst_0       
        //   506: invokespecial   java/util/Date.<init>:(J)V
        //   509: astore          5
        //   511: aload           5
        //   513: aload           18
        //   515: invokevirtual   java/util/Date.before:(Ljava/util/Date;)Z
        //   518: istore          13
        //   520: aload           19
        //   522: lload_3        
        //   523: lload           6
        //   525: invokevirtual   com/kongregate/o/b/d$a.a:(JJ)J
        //   528: lstore_3       
        //   529: iload           13
        //   531: ifne            545
        //   534: aload           19
        //   536: lload           9
        //   538: lload_3        
        //   539: invokestatic    com/kongregate/o/b/d.a:(Lcom/kongregate/o/b/d$a;JJ)Z
        //   542: ifeq            792
        //   545: iload           8
        //   547: ifne            569
        //   550: aload           16
        //   552: aload           16
        //   554: ldc             "_id"
        //   556: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   561: invokeinterface android/database/Cursor.isNull:(I)Z
        //   566: ifeq            746
        //   569: aload           17
        //   571: ldc             "statistic_id"
        //   573: lload           11
        //   575: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   578: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   581: aload           17
        //   583: ldc             "user_id"
        //   585: lload_1        
        //   586: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   589: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   592: aload           17
        //   594: ldc             "value"
        //   596: lload_3        
        //   597: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   600: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   603: lload_3        
        //   604: lstore_1       
        //   605: aload_0        
        //   606: ldc             "statistic_records"
        //   608: aload           17
        //   610: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   613: lstore_3       
        //   614: lload_3        
        //   615: ldc2_w          -1
        //   618: lcmp           
        //   619: ifeq            1258
        //   622: aload_0        
        //   623: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   626: new             Ljava/lang/StringBuilder;
        //   629: dup            
        //   630: invokespecial   java/lang/StringBuilder.<init>:()V
        //   633: ldc_w           "StatisticRecord "
        //   636: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   639: lload_3        
        //   640: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   643: ldc_w           ", updated with value: "
        //   646: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   649: lload_1        
        //   650: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   653: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   656: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //   659: aload           16
        //   661: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   664: aload           15
        //   666: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   669: aload_0        
        //   670: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   673: lload_3        
        //   674: lreturn        
        //   675: iconst_0       
        //   676: istore          8
        //   678: goto            391
        //   681: aload           16
        //   683: aload           16
        //   685: ldc             "value"
        //   687: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   692: invokeinterface android/database/Cursor.getLong:(I)J
        //   697: lstore_3       
        //   698: goto            465
        //   701: aload           16
        //   703: aload           16
        //   705: ldc             "server_value"
        //   707: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   712: invokeinterface android/database/Cursor.getLong:(I)J
        //   717: lstore          9
        //   719: goto            496
        //   722: aload           16
        //   724: aload           16
        //   726: ldc             "sent_at"
        //   728: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   733: invokeinterface android/database/Cursor.getLong:(I)J
        //   738: invokestatic    com/kongregate/o/b/e.a:(J)Ljava/util/Date;
        //   741: astore          5
        //   743: goto            511
        //   746: aload           17
        //   748: ldc             "_id"
        //   750: aload           16
        //   752: aload           16
        //   754: ldc             "_id"
        //   756: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   761: invokeinterface android/database/Cursor.getLong:(I)J
        //   766: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   769: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   772: goto            592
        //   775: astore          5
        //   777: aload           15
        //   779: astore          17
        //   781: aload           16
        //   783: astore          15
        //   785: aload           17
        //   787: astore          16
        //   789: goto            361
        //   792: aload           16
        //   794: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   797: aload           15
        //   799: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   802: aload_0        
        //   803: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   806: ldc2_w          -1
        //   809: lreturn        
        //   810: iload           14
        //   812: ifeq            1218
        //   815: new             Ljava/lang/StringBuilder;
        //   818: dup            
        //   819: invokespecial   java/lang/StringBuilder.<init>:()V
        //   822: ldc_w           "Statistic definition doesn't exist for "
        //   825: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   828: aload           5
        //   830: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   833: ldc_w           ", value="
        //   836: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   839: lload           6
        //   841: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   844: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   847: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //   850: aload           17
        //   852: ldc             "value"
        //   854: invokevirtual   android/content/ContentValues.putNull:(Ljava/lang/String;)V
        //   857: aload           17
        //   859: ldc             "statistic_name"
        //   861: aload           19
        //   863: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/String;)V
        //   866: getstatic       com/kongregate/o/b/d$a.a:Lcom/kongregate/o/b/d$a;
        //   869: astore          5
        //   871: iload           8
        //   873: ifne            895
        //   876: aload           16
        //   878: aload           16
        //   880: ldc             "max_value"
        //   882: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   887: invokeinterface android/database/Cursor.isNull:(I)Z
        //   892: ifeq            1138
        //   895: getstatic       com/kongregate/o/b/d$a.a:Lcom/kongregate/o/b/d$a;
        //   898: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   901: lstore_3       
        //   902: aload           17
        //   904: ldc             "max_value"
        //   906: aload           5
        //   908: lload_3        
        //   909: lload           6
        //   911: invokevirtual   com/kongregate/o/b/d$a.a:(JJ)J
        //   914: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   917: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   920: getstatic       com/kongregate/o/b/d$a.b:Lcom/kongregate/o/b/d$a;
        //   923: astore          5
        //   925: iload           8
        //   927: ifne            949
        //   930: aload           16
        //   932: aload           16
        //   934: ldc             "min_value"
        //   936: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   941: invokeinterface android/database/Cursor.isNull:(I)Z
        //   946: ifeq            1158
        //   949: getstatic       com/kongregate/o/b/d$a.b:Lcom/kongregate/o/b/d$a;
        //   952: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   955: lstore_3       
        //   956: aload           17
        //   958: ldc             "min_value"
        //   960: aload           5
        //   962: lload_3        
        //   963: lload           6
        //   965: invokevirtual   com/kongregate/o/b/d$a.a:(JJ)J
        //   968: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   971: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   974: getstatic       com/kongregate/o/b/d$a.d:Lcom/kongregate/o/b/d$a;
        //   977: astore          5
        //   979: iload           8
        //   981: ifne            1003
        //   984: aload           16
        //   986: aload           16
        //   988: ldc             "add_value"
        //   990: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   995: invokeinterface android/database/Cursor.isNull:(I)Z
        //  1000: ifeq            1178
        //  1003: getstatic       com/kongregate/o/b/d$a.d:Lcom/kongregate/o/b/d$a;
        //  1006: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //  1009: lstore_3       
        //  1010: aload           17
        //  1012: ldc             "add_value"
        //  1014: aload           5
        //  1016: lload_3        
        //  1017: lload           6
        //  1019: invokevirtual   com/kongregate/o/b/d$a.a:(JJ)J
        //  1022: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1025: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //  1028: getstatic       com/kongregate/o/b/d$a.c:Lcom/kongregate/o/b/d$a;
        //  1031: astore          5
        //  1033: iload           8
        //  1035: ifne            1057
        //  1038: aload           16
        //  1040: aload           16
        //  1042: ldc             "replace_value"
        //  1044: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //  1049: invokeinterface android/database/Cursor.isNull:(I)Z
        //  1054: ifeq            1198
        //  1057: getstatic       com/kongregate/o/b/d$a.c:Lcom/kongregate/o/b/d$a;
        //  1060: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //  1063: lstore_3       
        //  1064: aload           17
        //  1066: ldc             "replace_value"
        //  1068: aload           5
        //  1070: lload_3        
        //  1071: lload           6
        //  1073: invokevirtual   com/kongregate/o/b/d$a.a:(JJ)J
        //  1076: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1079: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //  1082: aload           17
        //  1084: ldc             "user_id"
        //  1086: lload_1        
        //  1087: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1090: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //  1093: lload           6
        //  1095: lstore_1       
        //  1096: aload           16
        //  1098: invokeinterface android/database/Cursor.isAfterLast:()Z
        //  1103: ifne            605
        //  1106: aload           17
        //  1108: ldc             "_id"
        //  1110: aload           16
        //  1112: aload           16
        //  1114: ldc             "_id"
        //  1116: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //  1121: invokeinterface android/database/Cursor.getLong:(I)J
        //  1126: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  1129: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //  1132: lload           6
        //  1134: lstore_1       
        //  1135: goto            605
        //  1138: aload           16
        //  1140: aload           16
        //  1142: ldc             "max_value"
        //  1144: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //  1149: invokeinterface android/database/Cursor.getLong:(I)J
        //  1154: lstore_3       
        //  1155: goto            902
        //  1158: aload           16
        //  1160: aload           16
        //  1162: ldc             "min_value"
        //  1164: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //  1169: invokeinterface android/database/Cursor.getLong:(I)J
        //  1174: lstore_3       
        //  1175: goto            956
        //  1178: aload           16
        //  1180: aload           16
        //  1182: ldc             "add_value"
        //  1184: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //  1189: invokeinterface android/database/Cursor.getLong:(I)J
        //  1194: lstore_3       
        //  1195: goto            1010
        //  1198: aload           16
        //  1200: aload           16
        //  1202: ldc             "replace_value"
        //  1204: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //  1209: invokeinterface android/database/Cursor.getLong:(I)J
        //  1214: lstore_3       
        //  1215: goto            1064
        //  1218: new             Ljava/lang/StringBuilder;
        //  1221: dup            
        //  1222: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1225: ldc_w           "Ignoring statistic submission for undefined stat: "
        //  1228: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1231: aload           5
        //  1233: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1236: ldc             "="
        //  1238: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1241: lload           6
        //  1243: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1246: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1249: invokestatic    com/kongregate/android/internal/util/j.b:(Ljava/lang/String;)V
        //  1252: lload           6
        //  1254: lstore_1       
        //  1255: goto            605
        //  1258: aload           16
        //  1260: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //  1263: aload           15
        //  1265: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //  1268: aload_0        
        //  1269: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //  1272: ldc2_w          -1
        //  1275: lreturn        
        //  1276: astore          5
        //  1278: aconst_null    
        //  1279: astore          15
        //  1281: aconst_null    
        //  1282: astore          16
        //  1284: goto            361
        //  1287: ldc_w           "statistic_id=?"
        //  1290: astore          16
        //  1292: goto            210
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  54     115    1276   1287   Any
        //  120    125    348    361    Any
        //  142    197    348    361    Any
        //  210    227    348    361    Any
        //  237    274    348    361    Any
        //  274    290    348    361    Any
        //  295    300    775    792    Any
        //  325    339    348    361    Any
        //  378    388    775    792    Any
        //  396    435    775    792    Any
        //  440    459    775    792    Any
        //  459    465    775    792    Any
        //  470    489    775    792    Any
        //  489    496    775    792    Any
        //  501    511    775    792    Any
        //  511    529    775    792    Any
        //  534    545    775    792    Any
        //  550    569    775    792    Any
        //  569    592    775    792    Any
        //  592    603    775    792    Any
        //  605    614    775    792    Any
        //  622    659    775    792    Any
        //  681    698    775    792    Any
        //  701    719    775    792    Any
        //  722    743    775    792    Any
        //  746    772    775    792    Any
        //  815    871    775    792    Any
        //  876    895    775    792    Any
        //  895    902    775    792    Any
        //  902    925    775    792    Any
        //  930    949    775    792    Any
        //  949    956    775    792    Any
        //  956    979    775    792    Any
        //  984    1003   775    792    Any
        //  1003   1010   775    792    Any
        //  1010   1033   775    792    Any
        //  1038   1057   775    792    Any
        //  1057   1064   775    792    Any
        //  1064   1093   775    792    Any
        //  1096   1132   775    792    Any
        //  1138   1155   775    792    Any
        //  1158   1175   775    792    Any
        //  1178   1195   775    792    Any
        //  1198   1215   775    792    Any
        //  1218   1252   775    792    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0318:
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
    
    public static long a(final Date date) {
        return (date.getTime() + com.kongregate.o.b.e.r.getOffset(date.getTime())) / 1000L;
    }
    
    static Cursor a(final SQLiteDatabase sqLiteDatabase) {
        return sqLiteDatabase.query("statistic_records", (String[])null, "statistic_id IS NULL", (String[])null, (String)null, (String)null, (String)null);
    }
    
    public static Cursor a(final SQLiteDatabase sqLiteDatabase, final long n) {
        return sqLiteDatabase.query("statistic_records", (String[])null, "user_id=? AND value IS NOT NULL", new String[] { String.valueOf(n) }, (String)null, (String)null, (String)null);
    }
    
    public static Date a(final long n) {
        try {
            return com.kongregate.o.b.e.s.get().parse(com.kongregate.o.b.e.s.get().format(new Date(1000L * n)));
        }
        catch (ParseException ex) {
            com.kongregate.android.internal.util.j.d("ParseException", ex);
            throw new IllegalStateException("Invalid date", ex);
        }
    }
    
    static void b(final SQLiteDatabase p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: aload_0        
        //     4: invokestatic    com/kongregate/o/b/e.a:(Landroid/database/sqlite/SQLiteDatabase;)Landroid/database/Cursor;
        //     7: astore          7
        //     9: aload           7
        //    11: ifnonnull       21
        //    14: ldc_w           "Couldn't query for unknown statistic records"
        //    17: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //    20: return         
        //    21: aload           7
        //    23: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    28: pop            
        //    29: aconst_null    
        //    30: astore          5
        //    32: aload           7
        //    34: invokeinterface android/database/Cursor.isAfterLast:()Z
        //    39: ifne            630
        //    42: aload           7
        //    44: aload           7
        //    46: ldc             "_id"
        //    48: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //    53: invokeinterface android/database/Cursor.getLong:(I)J
        //    58: lstore_1       
        //    59: aload           7
        //    61: aload           7
        //    63: ldc             "statistic_name"
        //    65: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //    70: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    75: astore          8
        //    77: aload           8
        //    79: ifnull          653
        //    82: aload           8
        //    84: ldc             "-"
        //    86: iconst_2       
        //    87: invokevirtual   java/lang/String.split:(Ljava/lang/String;I)[Ljava/lang/String;
        //    90: astore          6
        //    92: aload           6
        //    94: ifnull          653
        //    97: aload           6
        //    99: arraylength    
        //   100: iconst_2       
        //   101: if_icmpne       653
        //   104: aload           6
        //   106: iconst_0       
        //   107: aaload         
        //   108: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   111: lstore_3       
        //   112: aload           6
        //   114: iconst_1       
        //   115: aaload         
        //   116: astore          6
        //   118: aload           5
        //   120: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   123: aload_0        
        //   124: ldc             "statistics"
        //   126: iconst_2       
        //   127: anewarray       Ljava/lang/String;
        //   130: dup            
        //   131: iconst_0       
        //   132: ldc             "_id"
        //   134: aastore        
        //   135: dup            
        //   136: iconst_1       
        //   137: ldc             "stat_type"
        //   139: aastore        
        //   140: ldc_w           "game_id=? AND name=?"
        //   143: iconst_2       
        //   144: anewarray       Ljava/lang/String;
        //   147: dup            
        //   148: iconst_0       
        //   149: lload_3        
        //   150: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   153: aastore        
        //   154: dup            
        //   155: iconst_1       
        //   156: aload           6
        //   158: aastore        
        //   159: aconst_null    
        //   160: aconst_null    
        //   161: aconst_null    
        //   162: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   165: astore          6
        //   167: aload           6
        //   169: ifnull          561
        //   172: aload           6
        //   174: invokeinterface android/database/Cursor.moveToFirst:()Z
        //   179: ifeq            561
        //   182: aload           6
        //   184: aload           6
        //   186: ldc             "_id"
        //   188: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   193: invokeinterface android/database/Cursor.getLong:(I)J
        //   198: lstore_3       
        //   199: aload           6
        //   201: aload           6
        //   203: ldc             "stat_type"
        //   205: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   210: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   215: invokestatic    com/kongregate/o/b/d$a.a:(Ljava/lang/String;)Lcom/kongregate/o/b/d$a;
        //   218: astore          5
        //   220: new             Landroid/content/ContentValues;
        //   223: dup            
        //   224: invokespecial   android/content/ContentValues.<init>:()V
        //   227: astore          8
        //   229: aload           8
        //   231: ldc             "_id"
        //   233: lload_1        
        //   234: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   237: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   240: aload           8
        //   242: ldc             "statistic_id"
        //   244: lload_3        
        //   245: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   248: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   251: aload           8
        //   253: ldc             "statistic_name"
        //   255: invokevirtual   android/content/ContentValues.putNull:(Ljava/lang/String;)V
        //   258: getstatic       com/kongregate/o/b/e$2.a:[I
        //   261: aload           5
        //   263: invokevirtual   com/kongregate/o/b/d$a.ordinal:()I
        //   266: iaload         
        //   267: tableswitch {
        //                2: 369
        //                3: 417
        //                4: 465
        //                5: 513
        //          default: 660
        //        }
        //   296: aload           5
        //   298: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   301: lstore_1       
        //   302: aload           8
        //   304: ldc             "value"
        //   306: lload_1        
        //   307: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   310: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   313: aload           8
        //   315: ldc             "max_value"
        //   317: invokevirtual   android/content/ContentValues.putNull:(Ljava/lang/String;)V
        //   320: aload           8
        //   322: ldc             "min_value"
        //   324: invokevirtual   android/content/ContentValues.putNull:(Ljava/lang/String;)V
        //   327: aload           8
        //   329: ldc             "add_value"
        //   331: invokevirtual   android/content/ContentValues.putNull:(Ljava/lang/String;)V
        //   334: aload           8
        //   336: ldc             "replace_value"
        //   338: invokevirtual   android/content/ContentValues.putNull:(Ljava/lang/String;)V
        //   341: aload_0        
        //   342: ldc             "statistic_records"
        //   344: aload           8
        //   346: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   349: pop2           
        //   350: aload           6
        //   352: astore          5
        //   354: aload           7
        //   356: invokeinterface android/database/Cursor.moveToNext:()Z
        //   361: pop            
        //   362: aload           6
        //   364: astore          5
        //   366: goto            32
        //   369: aload           7
        //   371: aload           7
        //   373: ldc             "max_value"
        //   375: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   380: invokeinterface android/database/Cursor.isNull:(I)Z
        //   385: ifeq            397
        //   388: aload           5
        //   390: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   393: lstore_1       
        //   394: goto            302
        //   397: aload           7
        //   399: aload           7
        //   401: ldc             "max_value"
        //   403: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   408: invokeinterface android/database/Cursor.getLong:(I)J
        //   413: lstore_1       
        //   414: goto            302
        //   417: aload           7
        //   419: aload           7
        //   421: ldc             "min_value"
        //   423: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   428: invokeinterface android/database/Cursor.isNull:(I)Z
        //   433: ifeq            445
        //   436: aload           5
        //   438: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   441: lstore_1       
        //   442: goto            302
        //   445: aload           7
        //   447: aload           7
        //   449: ldc             "min_value"
        //   451: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   456: invokeinterface android/database/Cursor.getLong:(I)J
        //   461: lstore_1       
        //   462: goto            302
        //   465: aload           7
        //   467: aload           7
        //   469: ldc             "add_value"
        //   471: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   476: invokeinterface android/database/Cursor.isNull:(I)Z
        //   481: ifeq            493
        //   484: aload           5
        //   486: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   489: lstore_1       
        //   490: goto            302
        //   493: aload           7
        //   495: aload           7
        //   497: ldc             "add_value"
        //   499: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   504: invokeinterface android/database/Cursor.getLong:(I)J
        //   509: lstore_1       
        //   510: goto            302
        //   513: aload           7
        //   515: aload           7
        //   517: ldc             "replace_value"
        //   519: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   524: invokeinterface android/database/Cursor.isNull:(I)Z
        //   529: ifeq            541
        //   532: aload           5
        //   534: invokevirtual   com/kongregate/o/b/d$a.a:()J
        //   537: lstore_1       
        //   538: goto            302
        //   541: aload           7
        //   543: aload           7
        //   545: ldc             "replace_value"
        //   547: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   552: invokeinterface android/database/Cursor.getLong:(I)J
        //   557: lstore_1       
        //   558: goto            302
        //   561: aload           6
        //   563: astore          5
        //   565: new             Ljava/lang/StringBuilder;
        //   568: dup            
        //   569: invokespecial   java/lang/StringBuilder.<init>:()V
        //   572: ldc_w           "Deleting unknown statistic record: "
        //   575: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   578: aload           8
        //   580: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   583: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   586: invokestatic    com/kongregate/android/internal/util/j.a:(Ljava/lang/String;)V
        //   589: aload           6
        //   591: astore          5
        //   593: aload_0        
        //   594: ldc             "statistic_records"
        //   596: ldc_w           "_id=?"
        //   599: iconst_1       
        //   600: anewarray       Ljava/lang/String;
        //   603: dup            
        //   604: iconst_0       
        //   605: lload_1        
        //   606: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   609: aastore        
        //   610: invokevirtual   android/database/sqlite/SQLiteDatabase.delete:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
        //   613: pop            
        //   614: goto            350
        //   617: astore_0       
        //   618: aload           7
        //   620: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   623: aload           5
        //   625: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   628: aload_0        
        //   629: athrow         
        //   630: aload           7
        //   632: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   635: aload           5
        //   637: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   640: return         
        //   641: astore_0       
        //   642: aload           6
        //   644: astore          5
        //   646: goto            618
        //   649: astore_0       
        //   650: goto            618
        //   653: aload           5
        //   655: astore          6
        //   657: goto            561
        //   660: goto            296
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  21     29     617    618    Any
        //  32     77     649    653    Any
        //  82     92     649    653    Any
        //  97     112    649    653    Any
        //  118    167    649    653    Any
        //  172    296    641    649    Any
        //  296    302    641    649    Any
        //  302    350    641    649    Any
        //  354    362    617    618    Any
        //  369    394    641    649    Any
        //  397    414    641    649    Any
        //  417    442    641    649    Any
        //  445    462    641    649    Any
        //  465    490    641    649    Any
        //  493    510    641    649    Any
        //  513    538    641    649    Any
        //  541    558    641    649    Any
        //  565    589    617    618    Any
        //  593    614    617    618    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0296:
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
    
    public static void b(final SQLiteDatabase p0, final long p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: lconst_0       
        //     2: lcmp           
        //     3: ifne            7
        //     6: return         
        //     7: aload_0        
        //     8: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
        //    11: aload_0        
        //    12: ldc             "statistic_records"
        //    14: aconst_null    
        //    15: ldc_w           "user_id=?"
        //    18: iconst_1       
        //    19: anewarray       Ljava/lang/String;
        //    22: dup            
        //    23: iconst_0       
        //    24: lconst_0       
        //    25: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //    28: aastore        
        //    29: aconst_null    
        //    30: aconst_null    
        //    31: aconst_null    
        //    32: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    35: astore          9
        //    37: aload           9
        //    39: ifnonnull       62
        //    42: ldc_w           "Couldn't query statistic records"
        //    45: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //    48: aload_0        
        //    49: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //    52: aload           9
        //    54: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //    57: aconst_null    
        //    58: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //    61: return         
        //    62: new             Landroid/content/ContentValues;
        //    65: dup            
        //    66: invokespecial   android/content/ContentValues.<init>:()V
        //    69: astore          12
        //    71: aload           9
        //    73: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    78: pop            
        //    79: aconst_null    
        //    80: astore          11
        //    82: aload           11
        //    84: astore          10
        //    86: aload           9
        //    88: invokeinterface android/database/Cursor.isAfterLast:()Z
        //    93: ifne            399
        //    96: aload           11
        //    98: astore          10
        //   100: aload           12
        //   102: invokevirtual   android/content/ContentValues.clear:()V
        //   105: aload           11
        //   107: astore          10
        //   109: aload           9
        //   111: aload           9
        //   113: ldc             "_id"
        //   115: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   120: invokeinterface android/database/Cursor.getLong:(I)J
        //   125: lstore_3       
        //   126: aload           11
        //   128: astore          10
        //   130: aload           12
        //   132: ldc             "_id"
        //   134: lload_3        
        //   135: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   138: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   141: aload           11
        //   143: astore          10
        //   145: aload           12
        //   147: ldc             "user_id"
        //   149: lload_1        
        //   150: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   153: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Long;)V
        //   156: aload           11
        //   158: astore          10
        //   160: aload           9
        //   162: aload           9
        //   164: ldc             "statistic_id"
        //   166: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   171: invokeinterface android/database/Cursor.isNull:(I)Z
        //   176: ifeq            234
        //   179: aload           11
        //   181: astore          10
        //   183: aload_0        
        //   184: ldc             "statistic_records"
        //   186: aload           12
        //   188: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Landroid/content/ContentValues;)J
        //   191: pop2           
        //   192: aload           11
        //   194: astore          10
        //   196: aload           9
        //   198: invokeinterface android/database/Cursor.moveToNext:()Z
        //   203: pop            
        //   204: goto            82
        //   207: astore          12
        //   209: aload           9
        //   211: astore          11
        //   213: aload           12
        //   215: astore          9
        //   217: aload_0        
        //   218: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   221: aload           11
        //   223: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   226: aload           10
        //   228: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   231: aload           9
        //   233: athrow         
        //   234: aload           11
        //   236: astore          10
        //   238: aload           11
        //   240: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   243: aload           11
        //   245: astore          10
        //   247: aload           9
        //   249: aload           9
        //   251: ldc             "statistic_id"
        //   253: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   258: invokeinterface android/database/Cursor.getLong:(I)J
        //   263: lstore          5
        //   265: aload           11
        //   267: astore          10
        //   269: aload           9
        //   271: aload           9
        //   273: ldc             "value"
        //   275: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   280: invokeinterface android/database/Cursor.getLong:(I)J
        //   285: lstore          7
        //   287: aload           11
        //   289: astore          10
        //   291: aload_0        
        //   292: ldc             "statistics"
        //   294: aconst_null    
        //   295: ldc_w           "_id=?"
        //   298: iconst_1       
        //   299: anewarray       Ljava/lang/String;
        //   302: dup            
        //   303: iconst_0       
        //   304: lload           5
        //   306: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   309: aastore        
        //   310: aconst_null    
        //   311: aconst_null    
        //   312: aconst_null    
        //   313: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   316: astore          11
        //   318: aload           11
        //   320: ifnull          396
        //   323: aload           11
        //   325: invokeinterface android/database/Cursor.moveToNext:()Z
        //   330: ifeq            396
        //   333: aload_0        
        //   334: lload_1        
        //   335: aload           11
        //   337: aload           11
        //   339: aload_0        
        //   340: invokestatic    com/kongregate/o/b/e.c:(Landroid/database/sqlite/SQLiteDatabase;)Ljava/lang/String;
        //   343: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   348: invokeinterface android/database/Cursor.getLong:(I)J
        //   353: aload           11
        //   355: aload           11
        //   357: ldc             "name"
        //   359: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //   364: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //   369: lload           7
        //   371: invokestatic    com/kongregate/o/b/e.a:(Landroid/database/sqlite/SQLiteDatabase;JJLjava/lang/String;J)J
        //   374: pop2           
        //   375: aload_0        
        //   376: ldc             "statistic_records"
        //   378: ldc_w           "_id=?"
        //   381: iconst_1       
        //   382: anewarray       Ljava/lang/String;
        //   385: dup            
        //   386: iconst_0       
        //   387: lload_3        
        //   388: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
        //   391: aastore        
        //   392: invokevirtual   android/database/sqlite/SQLiteDatabase.delete:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
        //   395: pop            
        //   396: goto            192
        //   399: aload           11
        //   401: astore          10
        //   403: aload_0        
        //   404: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
        //   407: aload_0        
        //   408: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
        //   411: aload           9
        //   413: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   416: aload           11
        //   418: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
        //   421: return         
        //   422: astore          9
        //   424: aconst_null    
        //   425: astore          10
        //   427: aconst_null    
        //   428: astore          11
        //   430: goto            217
        //   433: astore          12
        //   435: aconst_null    
        //   436: astore          10
        //   438: aload           9
        //   440: astore          11
        //   442: aload           12
        //   444: astore          9
        //   446: goto            217
        //   449: astore          12
        //   451: aload           11
        //   453: astore          10
        //   455: aload           9
        //   457: astore          11
        //   459: aload           12
        //   461: astore          9
        //   463: goto            217
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  11     37     422    433    Any
        //  42     48     433    449    Any
        //  62     79     433    449    Any
        //  86     96     207    217    Any
        //  100    105    207    217    Any
        //  109    126    207    217    Any
        //  130    141    207    217    Any
        //  145    156    207    217    Any
        //  160    179    207    217    Any
        //  183    192    207    217    Any
        //  196    204    207    217    Any
        //  238    243    207    217    Any
        //  247    265    207    217    Any
        //  269    287    207    217    Any
        //  291    318    207    217    Any
        //  323    396    449    466    Any
        //  403    407    207    217    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 213, Size: 213
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
    
    protected static String c(final SQLiteDatabase sqLiteDatabase) {
        if (com.kongregate.o.b.e.o.get() == null) {
            com.kongregate.o.b.e.o.set(com.kongregate.o.e.a.a(sqLiteDatabase, "statistics", "application_id"));
        }
        if (com.kongregate.o.b.e.o.get()) {
            return "application_id";
        }
        return "game_id";
    }
    
    protected static boolean d(final SQLiteDatabase sqLiteDatabase) {
        if (com.kongregate.o.b.e.p.get() == null) {
            com.kongregate.o.b.e.p.set(com.kongregate.o.e.a.a(sqLiteDatabase, "statistic_records", "statistic_name"));
        }
        return com.kongregate.o.b.e.p.get();
    }
    
    public static class a implements Runnable
    {
        protected final Context a;
        protected final String b;
        protected final long c;
        protected final long d;
        protected final SQLiteDatabase e;
        
        public a(final Context context, final SQLiteDatabase e, final long c, final long d, final String s) {
            this.a = context.getApplicationContext();
            String b = s;
            if (s == null) {
                b = "/statistics/mobile_submit.json";
            }
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
        }
        
        protected Cursor a() {
            return com.kongregate.o.b.e.a(this.e, this.c);
        }
        
        protected Map<String, Object> a(final Cursor p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: invokespecial   org/json/JSONObject.<init>:()V
            //     7: astore          6
            //     9: aload_1        
            //    10: ifnonnull       15
            //    13: aconst_null    
            //    14: areturn        
            //    15: aload_1        
            //    16: invokeinterface android/database/Cursor.moveToFirst:()Z
            //    21: pop            
            //    22: aload_1        
            //    23: invokeinterface android/database/Cursor.isAfterLast:()Z
            //    28: ifne            93
            //    31: aload_1        
            //    32: aload_1        
            //    33: ldc             "statistic_id"
            //    35: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
            //    40: invokeinterface android/database/Cursor.getLong:(I)J
            //    45: lstore_2       
            //    46: aload_1        
            //    47: aload_1        
            //    48: ldc             "value"
            //    50: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
            //    55: invokeinterface android/database/Cursor.getLong:(I)J
            //    60: lstore          4
            //    62: aload           6
            //    64: lload_2        
            //    65: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
            //    68: lload           4
            //    70: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
            //    73: pop            
            //    74: aload_1        
            //    75: invokeinterface android/database/Cursor.moveToNext:()Z
            //    80: pop            
            //    81: goto            22
            //    84: astore          6
            //    86: aload_1        
            //    87: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
            //    90: aload           6
            //    92: athrow         
            //    93: aload_1        
            //    94: invokestatic    com/kongregate/o/e/a.a:(Landroid/database/Cursor;)V
            //    97: aload           6
            //    99: invokevirtual   org/json/JSONObject.length:()I
            //   102: ifle            13
            //   105: new             Ljava/util/HashMap;
            //   108: dup            
            //   109: invokespecial   java/util/HashMap.<init>:()V
            //   112: astore_1       
            //   113: aload_1        
            //   114: ldc             "stats"
            //   116: aload           6
            //   118: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
            //   121: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
            //   126: pop            
            //   127: aload_1        
            //   128: ldc             "user_id"
            //   130: aload_0        
            //   131: getfield        com/kongregate/o/b/e$a.c:J
            //   134: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
            //   137: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
            //   142: pop            
            //   143: aload_0        
            //   144: getfield        com/kongregate/o/b/e$a.d:J
            //   147: ldc2_w          -1
            //   150: lcmp           
            //   151: ifeq            170
            //   154: aload_1        
            //   155: ldc             "game_id"
            //   157: aload_0        
            //   158: getfield        com/kongregate/o/b/e$a.d:J
            //   161: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
            //   164: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
            //   169: pop            
            //   170: aload_0        
            //   171: aload_1        
            //   172: invokevirtual   com/kongregate/o/b/e$a.a:(Ljava/util/Map;)Ljava/util/Map;
            //   175: areturn        
            //   176: astore          7
            //   178: goto            74
            //    Signature:
            //  (Landroid/database/Cursor;)Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                    
            //  -----  -----  -----  -----  ------------------------
            //  15     22     84     93     Any
            //  22     62     84     93     Any
            //  62     74     176    181    Lorg/json/JSONException;
            //  62     74     84     93     Any
            //  74     81     84     93     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        protected Map<String, Object> a(final Map<String, Object> map) {
            map.put("s", StringUtils.d(StringUtils.g(map.get("user_id").toString() + map.get("game_id").toString() + map.get("stats").toString()), "0EADBEEF"));
            map.put("v", "1");
            return map;
        }
        
        protected void a(final JSONObject jsonObject) {
            final JSONObject optJSONObject = jsonObject.optJSONObject("stats");
            if (optJSONObject == null) {
                com.kongregate.android.internal.util.j.c("Statistic response from server with no stats array");
            }
            else {
                final HashMap<Long, Long> hashMap = new HashMap<Long, Long>();
                final Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    final String s = keys.next();
                    hashMap.put(Long.valueOf(s), optJSONObject.optLong(s));
                }
            Label_0240_Outer:
                while (true) {
                    final String string = "user_id=? AND statistic_id IN (" + StringUtils.a(hashMap.keySet(), ",") + ")";
                    final long c = this.c;
                    this.e.beginTransaction();
                    while (true) {
                        while (true) {
                            try {
                                final Cursor query = this.e.query("statistic_records", (String[])null, string, new String[] { String.valueOf(c) }, (String)null, (String)null, (String)null);
                                if (query != null) {
                                    int a = 0;
                                    final JSONObject jsonObject2;
                                    Label_0222: {
                                        try {
                                            query.moveToFirst();
                                            a = 0;
                                            while (!query.isAfterLast()) {
                                                a = (this.a(query, hashMap) ? 1 : 0);
                                                query.moveToNext();
                                            }
                                            break Label_0222;
                                        }
                                        finally {}
                                        com.kongregate.o.e.a.a(query);
                                        this.e.endTransaction();
                                        throw jsonObject2;
                                    }
                                    this.b(jsonObject2);
                                    this.c(jsonObject2);
                                    this.e.setTransactionSuccessful();
                                    com.kongregate.o.e.a.a(query);
                                    this.e.endTransaction();
                                    if (a != 0) {
                                        LocalBroadcastManager.getInstance(this.a).sendBroadcast(new Intent("com.kongregate.android.internal.sdk.KongregateStatsSync"));
                                        return;
                                    }
                                    break;
                                }
                            }
                            finally {
                                final Cursor query = null;
                                continue Label_0240_Outer;
                            }
                            break;
                        }
                        int a = 0;
                        continue;
                    }
                }
            }
        }
        
        protected boolean a(final Cursor cursor, Map<Long, Long> contentValues) {
            final long long1 = cursor.getLong(cursor.getColumnIndex("_id"));
            long n = cursor.getLong(cursor.getColumnIndex("statistic_id"));
        Label_0280:
            while (true) {
                try {
                    final Cursor query = this.e.query("statistics", (String[])null, "_id=?", new String[] { String.valueOf(n) }, (String)null, (String)null, (String)null);
                    Label_0076: {
                        if (query == null) {
                            break Label_0076;
                        }
                        while (true) {
                            Label_0322: {
                                while (true) {
                                    d.a a = null;
                                    long long2 = 0L;
                                    Label_0287: {
                                        try {
                                            if (!query.moveToFirst()) {
                                                com.kongregate.android.internal.util.j.c("Couldn't find statistic ID: " + n);
                                                com.kongregate.o.e.a.a(query);
                                                return false;
                                            }
                                            a = com.kongregate.o.b.d.a.a(query.getString(query.getColumnIndex("stat_type")));
                                            n = ((Map<Long, Long>)contentValues).get(n);
                                            contentValues = new ContentValues();
                                            long2 = cursor.getLong(cursor.getColumnIndex("value"));
                                            if (a != com.kongregate.o.b.d.a.d) {
                                                break Label_0287;
                                            }
                                            long2 -= n;
                                            if (long2 == com.kongregate.o.b.d.a.d.a()) {
                                                contentValues.putNull("value");
                                            }
                                            else {
                                                contentValues.put("value", Long.valueOf(long2));
                                            }
                                            contentValues.put("server_value", Long.valueOf(n));
                                            contentValues.put("sent_at", Long.valueOf(com.kongregate.o.b.e.a()));
                                            if (this.e.update("statistic_records", contentValues, "_id=?", new String[] { String.valueOf(long1) }) > 0) {
                                                com.kongregate.o.e.a.a(query);
                                                return true;
                                            }
                                            break Label_0322;
                                        }
                                        finally {}
                                        break Label_0280;
                                    }
                                    if (com.kongregate.o.b.d.a(a, n, long2)) {
                                        contentValues.put("value", Long.valueOf(long2));
                                        continue;
                                    }
                                    contentValues.putNull("value");
                                    continue;
                                }
                                com.kongregate.o.e.a.a(query);
                                throw;
                            }
                            com.kongregate.o.e.a.a(query);
                            return false;
                        }
                    }
                }
                finally {
                    final Cursor query = null;
                    continue Label_0280;
                }
                break;
            }
        }
        
        protected boolean b(JSONObject query) {
            JSONObject optJSONObject = query.optJSONObject("stats.prgs");
            if (optJSONObject != null) {
                final Iterator keys = optJSONObject.keys();
                boolean b = false;
            Label_0378_Outer:
                while (true) {
                    boolean b2 = b;
                    if (keys.hasNext()) {
                        final String s = keys.next();
                        final long long1 = Long.parseLong(s);
                        final ContentValues contentValues = new ContentValues();
                        contentValues.put("user_id", Long.valueOf(this.c));
                        contentValues.put("value", Long.valueOf(optJSONObject.optLong(s)));
                        while (true) {
                            try {
                                this.e.beginTransaction();
                                query = (JSONObject)this.e.query("accomplishment_tasks", (String[])null, "statistic_id=?", new String[] { String.valueOf(long1) }, (String)null, (String)null, (String)null);
                                if (query == null) {
                                    b2 = false;
                                    com.kongregate.o.e.a.a((Cursor)query);
                                    this.e.endTransaction();
                                    return b2;
                                }
                                Iterator<String> iterator = null;
                                Label_0392: {
                                    try {
                                        ((Cursor)query).moveToFirst();
                                        while (!((Cursor)query).isAfterLast()) {
                                            final long long2 = ((Cursor)query).getLong(((Cursor)query).getColumnIndex("_id"));
                                            final long long3 = ((Cursor)query).getLong(((Cursor)query).getColumnIndex("badge_id"));
                                            contentValues.put("accomplishment_task_id", Long.valueOf(long2));
                                            final boolean a = com.kongregate.o.b.c.a(this.e, this.c, long3);
                                            com.kongregate.android.internal.util.j.b("wasCompleted=" + a);
                                            if (com.kongregate.o.e.a.a(this.e, "accomplishment_task_progress_trackers", contentValues) != -1L) {
                                                com.kongregate.android.internal.util.j.b("Updated accomplishment task progress: " + long2 + "=" + contentValues.getAsLong("value"));
                                                final boolean b3 = b = true;
                                                if (!a) {
                                                    b = b3;
                                                    if (com.kongregate.o.b.c.a(this.e, this.c, long3)) {
                                                        com.kongregate.android.internal.util.j.b("Badge completed: " + long3);
                                                        b = b3;
                                                    }
                                                }
                                            }
                                            ((Cursor)query).moveToNext();
                                        }
                                        break Label_0392;
                                    }
                                    finally {
                                        optJSONObject = query;
                                        iterator = (Iterator<String>)keys;
                                    }
                                    com.kongregate.o.e.a.a((Cursor)optJSONObject);
                                    this.e.endTransaction();
                                    throw iterator;
                                }
                                this.e.setTransactionSuccessful();
                                com.kongregate.o.e.a.a((Cursor)iterator);
                                this.e.endTransaction();
                                continue Label_0378_Outer;
                            }
                            finally {
                                optJSONObject = null;
                                continue;
                            }
                            break;
                        }
                        break;
                    }
                    return b2;
                }
            }
            return false;
        }
        
        protected void c(final JSONObject jsonObject) {
        }
        
        @Override
        public void run() {
            if (this.c != 0L) {
                final Map<String, Object> a = this.a(this.a());
                if (a != null) {
                    if (!com.kongregate.o.b.e.q.compareAndSet(false, true)) {
                        com.kongregate.android.internal.util.j.b("Already syncing stats, will retry later");
                        return;
                    }
                    com.kongregate.android.internal.util.j.b("Syncing statistic data, stats: " + a.get("stats") + ", s: " + a.get("s"));
                    com.kongregate.o.g.b.a().a(this.b, a, (b.a)new b.a() {
                        @Override
                        public void a(final com.kongregate.o.g.c c) {
                            super.a(c);
                            e.q.set(false);
                        }
                        
                        @Override
                        public void a(final com.kongregate.o.g.c c, final JSONObject jsonObject) {
                            com.kongregate.android.internal.util.j.b("Statistic sync successful: " + jsonObject);
                            com.kongregate.o.c.d.a(new Runnable() {
                                @Override
                                public void run() {
                                    com.kongregate.o.b.e.a.this.a(jsonObject);
                                    e.q.set(false);
                                }
                            });
                        }
                        
                        @Override
                        public void b(final com.kongregate.o.g.c c, final JSONObject jsonObject) {
                            super.b(c, jsonObject);
                            e.q.set(false);
                        }
                    });
                }
            }
        }
    }
}
