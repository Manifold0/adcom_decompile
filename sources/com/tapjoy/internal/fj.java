package com.tapjoy.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.kongregate.p000o.p003a.C0583e;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class fj extends fi {
    /* renamed from: b */
    private final File f7770b;
    /* renamed from: c */
    private final gb f7771c;
    /* renamed from: d */
    private volatile SQLiteDatabase f7772d;
    /* renamed from: e */
    private long f7773e;
    /* renamed from: f */
    private long f7774f;
    /* renamed from: g */
    private long f7775g;

    public fj(File file, gb gbVar) {
        this.f7770b = file;
        this.f7771c = gbVar;
    }

    protected void finalize() {
        if (this.f7772d != null) {
            dc.m7357a(this.f7772d);
            this.f7772d = null;
        }
        super.finalize();
    }

    /* renamed from: a */
    protected void mo6284a(long j) {
        if (this.f7772d == null) {
            this.f7772d = SQLiteDatabase.openOrCreateDatabase(this.f7770b, null);
            int version = this.f7772d.getVersion();
            switch (version) {
                case 0:
                    this.f7772d.beginTransaction();
                    try {
                        this.f7772d.execSQL("CREATE TABLE IF NOT EXISTS UsageStats(name TEXT,dimensions TEXT,count INTEGER,first_time INTEGER,last_time INTEGER,PRIMARY KEY(name, dimensions))");
                        this.f7772d.execSQL("CREATE TABLE IF NOT EXISTS UsageStatValues(stat_id LONG,name TEXT,count INTEGER,avg REAL,max INTEGER,PRIMARY KEY(stat_id, name))");
                        this.f7772d.setVersion(1);
                        this.f7772d.setTransactionSuccessful();
                        break;
                    } finally {
                        this.f7772d.endTransaction();
                    }
                case 1:
                    break;
                default:
                    throw new SQLException("Unknown database version: " + version);
            }
            Cursor rawQuery = this.f7772d.rawQuery("SELECT MIN(first_time), MAX(last_time) FROM UsageStats", null);
            try {
                if (rawQuery.moveToNext()) {
                    this.f7774f = rawQuery.getLong(0);
                    this.f7775g = rawQuery.getLong(1);
                }
                rawQuery.close();
                if (this.f7774f > 0 && this.f7774f + 86400000 <= j) {
                    m7758b();
                }
            } catch (Throwable th) {
                rawQuery.close();
            }
        }
    }

    /* renamed from: a */
    protected void mo6283a() {
        if (this.f7772d != null) {
            dc.m7357a(this.f7772d);
            this.f7772d = null;
        }
        this.f7770b.delete();
        this.f7775g = 0;
        this.f7774f = 0;
    }

    /* renamed from: a */
    protected void mo6285a(long j, String str, String str2, Map map) {
        if (this.f7772d != null) {
            if (this.f7773e == 0) {
                this.f7775g = j;
                this.f7773e = j;
            } else if (j < this.f7773e || j >= this.f7773e + 86400000) {
                if (j >= this.f7773e || this.f7775g - j >= 86400000) {
                    m7758b();
                    this.f7775g = j;
                    this.f7773e = j;
                } else {
                    this.f7773e = j;
                }
            } else if (j > this.f7775g) {
                this.f7775g = j;
            }
            if (str2 == null) {
                str2 = "";
            }
            Cursor rawQuery = this.f7772d.rawQuery("SELECT ROWID,count,first_time,last_time FROM UsageStats WHERE name = ? AND dimensions = ?", new String[]{str, str2});
            Cursor rawQuery2;
            try {
                long j2;
                long j3;
                long j4;
                ContentValues contentValues = new ContentValues();
                if (rawQuery.moveToNext()) {
                    j2 = rawQuery.getLong(0);
                    int i = rawQuery.getInt(1);
                    long j5 = rawQuery.getLong(2);
                    j3 = rawQuery.getLong(3);
                    contentValues.put(NewHtcHomeBadger.COUNT, Integer.valueOf(i + 1));
                    if (j < j5) {
                        contentValues.put("first_time", Long.valueOf(j));
                    }
                    if (j > j3) {
                        contentValues.put("last_time", Long.valueOf(j));
                    }
                    this.f7772d.update("UsageStats", contentValues, "ROWID = " + j2, null);
                    j4 = j2;
                } else {
                    contentValues.put("name", str);
                    contentValues.put(String.USAGE_TRACKER_DIMENSIONS, str2);
                    contentValues.put(NewHtcHomeBadger.COUNT, Integer.valueOf(1));
                    contentValues.put("first_time", Long.valueOf(j));
                    contentValues.put("last_time", Long.valueOf(j));
                    j4 = this.f7772d.insert("UsageStats", null, contentValues);
                }
                if (!(map == null || map.isEmpty())) {
                    for (Entry entry : map.entrySet()) {
                        if (entry.getValue() != null) {
                            String str3 = (String) entry.getKey();
                            j3 = ((Long) entry.getValue()).longValue();
                            rawQuery2 = this.f7772d.rawQuery("SELECT ROWID, * FROM UsageStatValues WHERE stat_id = ? AND name = ?", new String[]{Long.toString(j4), str3});
                            if (rawQuery2.moveToNext()) {
                                j2 = rawQuery2.getLong(0);
                                int i2 = rawQuery2.getInt(3);
                                double d = rawQuery2.getDouble(4);
                                long j6 = rawQuery2.getLong(5);
                                contentValues.clear();
                                contentValues.put(NewHtcHomeBadger.COUNT, Integer.valueOf(i2 + 1));
                                contentValues.put("avg", Double.valueOf(d + ((((double) j3) - d) / ((double) (i2 + 1)))));
                                if (j3 > j6) {
                                    contentValues.put(C0583e.f803b, Long.valueOf(j3));
                                }
                                this.f7772d.update("UsageStatValues", contentValues, "ROWID = " + j2, null);
                            } else {
                                contentValues.clear();
                                contentValues.put("stat_id", Long.valueOf(j4));
                                contentValues.put("name", str3);
                                contentValues.put(NewHtcHomeBadger.COUNT, Integer.valueOf(1));
                                contentValues.put("avg", Long.valueOf(j3));
                                contentValues.put(C0583e.f803b, Long.valueOf(j3));
                                this.f7772d.insert("UsageStatValues", null, contentValues);
                            }
                            rawQuery2.close();
                        }
                    }
                }
                rawQuery.close();
            } catch (Throwable th) {
                rawQuery.close();
                throw th;
            }
        }
    }

    /* renamed from: b */
    private void m7758b() {
        Set set = fi.f7766a;
        Cursor rawQuery = this.f7772d.rawQuery("SELECT ROWID, * FROM UsageStats ORDER BY ROWID ASC", null);
        Cursor rawQuery2;
        try {
            rawQuery2 = this.f7772d.rawQuery("SELECT * FROM UsageStatValues ORDER BY stat_id ASC", null);
            rawQuery2.moveToNext();
            while (rawQuery.moveToNext()) {
                long j = rawQuery.getLong(0);
                String string = rawQuery.getString(1);
                String string2 = rawQuery.getString(2);
                if (string2.isEmpty()) {
                    string2 = null;
                }
                int i = rawQuery.getInt(3);
                long j2 = rawQuery.getLong(4);
                long j3 = rawQuery.getLong(5);
                Map map = null;
                if (!rawQuery2.isAfterLast()) {
                    while (rawQuery2.getLong(0) == j) {
                        if (map == null) {
                            map = new HashMap();
                        }
                        String string3 = rawQuery2.getString(1);
                        long j4 = rawQuery2.getLong(3);
                        long j5 = rawQuery2.getLong(4);
                        map.put(string3, Long.valueOf(j4));
                        map.put(string3 + "_max", Long.valueOf(j5));
                        if (!rawQuery2.moveToNext()) {
                            break;
                        }
                    }
                }
                if (set == null || !set.contains(string)) {
                    this.f7771c.m7826a(string, string2, i, j2, j3, map);
                }
            }
        } catch (Throwable th) {
        } finally {
            
/*
Method generation error in method: com.tapjoy.internal.fj.b():void, dex: classes91.dex
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x00b3: INVOKE  (wrap: android.database.Cursor
  ?: MERGE  (r13_2 android.database.Cursor) = (r13_1 'rawQuery2' android.database.Cursor), (r12_0 'rawQuery' android.database.Cursor)) android.database.Cursor.close():void type: INTERFACE in method: com.tapjoy.internal.fj.b():void, dex: classes91.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:203)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:100)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:50)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:299)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:187)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:320)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:257)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:220)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:75)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:12)
	at jadx.core.ProcessClass.process(ProcessClass.java:40)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1364497552.run(Unknown Source)
Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: MERGE  (r13_2 android.database.Cursor) = (r13_1 'rawQuery2' android.database.Cursor), (r12_0 'rawQuery' android.database.Cursor) in method: com.tapjoy.internal.fj.b():void, dex: classes91.dex
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:101)
	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:84)
	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:632)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:338)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 22 more
Caused by: jadx.core.utils.exceptions.CodegenException: MERGE can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:537)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:509)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:211)
	... 27 more

*/
        }
