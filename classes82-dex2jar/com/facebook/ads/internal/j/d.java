// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.j;

import com.facebook.ads.internal.w.h.b;
import android.database.sqlite.SQLiteException;
import android.support.annotation.Nullable;
import java.util.Map;
import java.util.concurrent.Executor;
import android.os.Build$VERSION;
import com.facebook.ads.internal.w.b.p;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class d
{
    private static final String a;
    private static final ReentrantReadWriteLock b;
    private static final Lock c;
    private static final Lock d;
    private final Context e;
    private final h f;
    private final c g;
    private SQLiteOpenHelper h;
    
    static {
        a = "SELECT tokens." + h.a.b + ", " + "tokens" + "." + h.b.b + ", " + "events" + "." + com.facebook.ads.internal.j.c.a.b + ", " + "events" + "." + com.facebook.ads.internal.j.c.c.b + ", " + "events" + "." + com.facebook.ads.internal.j.c.d.b + ", " + "events" + "." + com.facebook.ads.internal.j.c.e.b + ", " + "events" + "." + com.facebook.ads.internal.j.c.f.b + ", " + "events" + "." + com.facebook.ads.internal.j.c.g.b + ", " + "events" + "." + com.facebook.ads.internal.j.c.h.b + ", " + "events" + "." + com.facebook.ads.internal.j.c.i.b + " FROM " + "events" + " JOIN " + "tokens" + " ON " + "events" + "." + com.facebook.ads.internal.j.c.b.b + " = " + "tokens" + "." + h.a.b + " ORDER BY " + "events" + "." + com.facebook.ads.internal.j.c.e.b + " ASC";
        b = new ReentrantReadWriteLock();
        c = com.facebook.ads.internal.j.d.b.readLock();
        d = com.facebook.ads.internal.j.d.b.writeLock();
    }
    
    public d(final Context e) {
        this.e = e;
        this.f = new h(this);
        this.g = new c(this);
    }
    
    private SQLiteDatabase j() {
        synchronized (this) {
            if (this.h == null) {
                this.h = new e(this.e, this);
            }
            return this.h.getWritableDatabase();
        }
    }
    
    @WorkerThread
    public Cursor a(final int n) {
        com.facebook.ads.internal.j.d.c.lock();
        try {
            return this.a().rawQuery(com.facebook.ads.internal.j.d.a + " LIMIT " + String.valueOf(n), (String[])null);
        }
        finally {
            com.facebook.ads.internal.j.d.c.unlock();
        }
    }
    
    public SQLiteDatabase a() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot call getDatabase from the UI thread!");
        }
        return this.j();
    }
    
    public <T> AsyncTask a(final f<T> f, final com.facebook.ads.internal.j.a<T> a) {
        final Executor b = p.b;
        final a a2 = new a(this.e.getApplicationContext(), (f<T>)f, (com.facebook.ads.internal.j.a<T>)a);
        final Void[] array = new Void[0];
        if (Build$VERSION.SDK_INT >= 11) {
            a2.executeOnExecutor(b, (Object[])array);
            return a2;
        }
        a2.execute((Object[])array);
        return a2;
    }
    
    public AsyncTask a(final String s, final int n, final String s2, final double n2, final double n3, final String s3, final Map<String, String> map, final com.facebook.ads.internal.j.a<String> a) {
        return this.a((f<Object>)new i<String>() {
            @Nullable
            public String a() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        com/facebook/ads/internal/j/d$1.a:Ljava/lang/String;
                //     4: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
                //     7: ifeq            12
                //    10: aconst_null    
                //    11: areturn        
                //    12: invokestatic    com/facebook/ads/internal/j/d.i:()Ljava/util/concurrent/locks/Lock;
                //    15: invokeinterface java/util/concurrent/locks/Lock.lock:()V
                //    20: aload_0        
                //    21: getfield        com/facebook/ads/internal/j/d$1.h:Lcom/facebook/ads/internal/j/d;
                //    24: invokevirtual   com/facebook/ads/internal/j/d.a:()Landroid/database/sqlite/SQLiteDatabase;
                //    27: astore_1       
                //    28: aload_1        
                //    29: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
                //    32: aload_0        
                //    33: getfield        com/facebook/ads/internal/j/d$1.h:Lcom/facebook/ads/internal/j/d;
                //    36: invokestatic    com/facebook/ads/internal/j/d.b:(Lcom/facebook/ads/internal/j/d;)Lcom/facebook/ads/internal/j/c;
                //    39: aload_0        
                //    40: getfield        com/facebook/ads/internal/j/d$1.h:Lcom/facebook/ads/internal/j/d;
                //    43: invokestatic    com/facebook/ads/internal/j/d.a:(Lcom/facebook/ads/internal/j/d;)Lcom/facebook/ads/internal/j/h;
                //    46: aload_0        
                //    47: getfield        com/facebook/ads/internal/j/d$1.a:Ljava/lang/String;
                //    50: invokevirtual   com/facebook/ads/internal/j/h.a:(Ljava/lang/String;)Ljava/lang/String;
                //    53: aload_0        
                //    54: getfield        com/facebook/ads/internal/j/d$1.b:I
                //    57: aload_0        
                //    58: getfield        com/facebook/ads/internal/j/d$1.c:Ljava/lang/String;
                //    61: aload_0        
                //    62: getfield        com/facebook/ads/internal/j/d$1.d:D
                //    65: aload_0        
                //    66: getfield        com/facebook/ads/internal/j/d$1.e:D
                //    69: aload_0        
                //    70: getfield        com/facebook/ads/internal/j/d$1.f:Ljava/lang/String;
                //    73: aload_0        
                //    74: getfield        com/facebook/ads/internal/j/d$1.g:Ljava/util/Map;
                //    77: invokevirtual   com/facebook/ads/internal/j/c.a:(Ljava/lang/String;ILjava/lang/String;DDLjava/lang/String;Ljava/util/Map;)Ljava/lang/String;
                //    80: astore_2       
                //    81: aload_1        
                //    82: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
                //    85: aload_1        
                //    86: ifnull          107
                //    89: aload_1        
                //    90: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
                //    93: ifeq            107
                //    96: aload_1        
                //    97: invokevirtual   android/database/sqlite/SQLiteDatabase.inTransaction:()Z
                //   100: ifeq            107
                //   103: aload_1        
                //   104: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   107: invokestatic    com/facebook/ads/internal/j/d.i:()Ljava/util/concurrent/locks/Lock;
                //   110: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
                //   115: aload_2        
                //   116: areturn        
                //   117: astore_1       
                //   118: aload_0        
                //   119: getfield        com/facebook/ads/internal/j/d$1.h:Lcom/facebook/ads/internal/j/d;
                //   122: invokestatic    com/facebook/ads/internal/j/d.c:(Lcom/facebook/ads/internal/j/d;)Landroid/content/Context;
                //   125: ldc             "database"
                //   127: getstatic       com/facebook/ads/internal/w/h/b.w:I
                //   130: aload_1        
                //   131: invokestatic    com/facebook/ads/internal/w/h/a.b:(Landroid/content/Context;Ljava/lang/String;ILjava/lang/Exception;)V
                //   134: goto            107
                //   137: astore_2       
                //   138: aconst_null    
                //   139: astore_1       
                //   140: aload_0        
                //   141: getstatic       com/facebook/ads/internal/j/f$a.c:Lcom/facebook/ads/internal/j/f$a;
                //   144: invokevirtual   com/facebook/ads/internal/j/d$1.a:(Lcom/facebook/ads/internal/j/f$a;)V
                //   147: aload_0        
                //   148: getfield        com/facebook/ads/internal/j/d$1.h:Lcom/facebook/ads/internal/j/d;
                //   151: invokestatic    com/facebook/ads/internal/j/d.c:(Lcom/facebook/ads/internal/j/d;)Landroid/content/Context;
                //   154: ldc             "database"
                //   156: getstatic       com/facebook/ads/internal/w/h/b.u:I
                //   159: aload_2        
                //   160: invokestatic    com/facebook/ads/internal/w/h/a.b:(Landroid/content/Context;Ljava/lang/String;ILjava/lang/Exception;)V
                //   163: aload_1        
                //   164: ifnull          185
                //   167: aload_1        
                //   168: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
                //   171: ifeq            185
                //   174: aload_1        
                //   175: invokevirtual   android/database/sqlite/SQLiteDatabase.inTransaction:()Z
                //   178: ifeq            185
                //   181: aload_1        
                //   182: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   185: invokestatic    com/facebook/ads/internal/j/d.i:()Ljava/util/concurrent/locks/Lock;
                //   188: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
                //   193: aconst_null    
                //   194: areturn        
                //   195: astore_1       
                //   196: aload_0        
                //   197: getfield        com/facebook/ads/internal/j/d$1.h:Lcom/facebook/ads/internal/j/d;
                //   200: invokestatic    com/facebook/ads/internal/j/d.c:(Lcom/facebook/ads/internal/j/d;)Landroid/content/Context;
                //   203: ldc             "database"
                //   205: getstatic       com/facebook/ads/internal/w/h/b.w:I
                //   208: aload_1        
                //   209: invokestatic    com/facebook/ads/internal/w/h/a.b:(Landroid/content/Context;Ljava/lang/String;ILjava/lang/Exception;)V
                //   212: goto            185
                //   215: astore_2       
                //   216: aconst_null    
                //   217: astore_1       
                //   218: aload_1        
                //   219: ifnull          240
                //   222: aload_1        
                //   223: invokevirtual   android/database/sqlite/SQLiteDatabase.isOpen:()Z
                //   226: ifeq            240
                //   229: aload_1        
                //   230: invokevirtual   android/database/sqlite/SQLiteDatabase.inTransaction:()Z
                //   233: ifeq            240
                //   236: aload_1        
                //   237: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   240: invokestatic    com/facebook/ads/internal/j/d.i:()Ljava/util/concurrent/locks/Lock;
                //   243: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
                //   248: aload_2        
                //   249: athrow         
                //   250: astore_1       
                //   251: aload_0        
                //   252: getfield        com/facebook/ads/internal/j/d$1.h:Lcom/facebook/ads/internal/j/d;
                //   255: invokestatic    com/facebook/ads/internal/j/d.c:(Lcom/facebook/ads/internal/j/d;)Landroid/content/Context;
                //   258: ldc             "database"
                //   260: getstatic       com/facebook/ads/internal/w/h/b.w:I
                //   263: aload_1        
                //   264: invokestatic    com/facebook/ads/internal/w/h/a.b:(Landroid/content/Context;Ljava/lang/String;ILjava/lang/Exception;)V
                //   267: goto            240
                //   270: astore_2       
                //   271: goto            218
                //   274: astore_2       
                //   275: goto            218
                //   278: astore_2       
                //   279: goto            140
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  20     28     137    140    Ljava/lang/Exception;
                //  20     28     215    218    Any
                //  28     85     278    282    Ljava/lang/Exception;
                //  28     85     270    274    Any
                //  96     107    117    137    Ljava/lang/Exception;
                //  140    163    274    278    Any
                //  174    185    195    215    Ljava/lang/Exception;
                //  229    240    250    270    Ljava/lang/Exception;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 130, Size: 130
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        }, (com.facebook.ads.internal.j.a<Object>)a);
    }
    
    @WorkerThread
    public boolean a(final String s) {
        boolean b = true;
        com.facebook.ads.internal.j.d.d.lock();
        while (true) {
            try {
                final StringBuilder sb = new StringBuilder();
                sb.append("UPDATE ").append("events").append(" SET ").append(com.facebook.ads.internal.j.c.i.b).append("=").append(com.facebook.ads.internal.j.c.i.b).append("+1").append(" WHERE ").append(com.facebook.ads.internal.j.c.a.b).append("=?");
                this.a().execSQL(sb.toString(), (Object[])new String[] { s });
                com.facebook.ads.internal.j.d.d.unlock();
                return b;
            }
            catch (SQLiteException ex) {
                b = false;
                continue;
            }
            break;
        }
    }
    
    public void b() {
        synchronized (this) {
            final g[] c = this.c();
            for (int length = c.length, i = 0; i < length; ++i) {
                c[i].e();
            }
            if (this.h != null) {
                this.h.close();
                this.h = null;
            }
        }
    }
    
    @WorkerThread
    public boolean b(final String s) {
        com.facebook.ads.internal.j.d.d.lock();
        try {
            return this.g.a(s);
        }
        finally {
            com.facebook.ads.internal.j.d.d.unlock();
        }
    }
    
    public g[] c() {
        return new g[] { this.f, this.g };
    }
    
    public Cursor d() {
        com.facebook.ads.internal.j.d.c.lock();
        try {
            return this.g.c();
        }
        finally {
            com.facebook.ads.internal.j.d.c.unlock();
        }
    }
    
    @WorkerThread
    public Cursor e() {
        com.facebook.ads.internal.j.d.c.lock();
        try {
            return this.g.d();
        }
        finally {
            com.facebook.ads.internal.j.d.c.unlock();
        }
    }
    
    @WorkerThread
    public Cursor f() {
        com.facebook.ads.internal.j.d.c.lock();
        try {
            return this.f.c();
        }
        finally {
            com.facebook.ads.internal.j.d.c.unlock();
        }
    }
    
    @WorkerThread
    public void g() {
        com.facebook.ads.internal.j.d.d.lock();
        try {
            this.f.d();
        }
        finally {
            com.facebook.ads.internal.j.d.d.unlock();
        }
    }
    
    @WorkerThread
    public void h() {
        com.facebook.ads.internal.j.d.d.lock();
        try {
            this.g.g();
            this.f.g();
        }
        finally {
            com.facebook.ads.internal.j.d.d.unlock();
        }
    }
    
    private static class a<T> extends AsyncTask<Void, Void, T>
    {
        private final f<T> a;
        private final com.facebook.ads.internal.j.a<T> b;
        private final Context c;
        private f.a d;
        
        a(final Context c, final f<T> a, final com.facebook.ads.internal.j.a<T> b) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        protected T a(Void... b) {
            try {
                b = (Void[])(Object)this.a.b();
                final a a = this;
                final a a2 = this;
                final f<T> f = a2.a;
                final f.a a3 = f.c();
                a.d = a3;
                final Void[] array = b;
                return (T)(Object)array;
            }
            catch (Exception ex) {
                b = null;
            }
            while (true) {
                try {
                    final a a = this;
                    final a a2 = this;
                    final f<T> f = a2.a;
                    final f.a a3 = f.c();
                    a.d = a3;
                    final Void[] array = b;
                    return (T)(Object)array;
                    final Exception ex;
                    com.facebook.ads.internal.w.h.a.b(this.c, "database", b.x, ex);
                    this.d = com.facebook.ads.internal.j.f.a.a;
                    return (T)(Object)b;
                }
                catch (Exception ex) {
                    continue;
                }
                break;
            }
        }
        
        protected void onPostExecute(final T t) {
            if (this.d == null) {
                this.b.a(t);
            }
            else {
                this.b.a(this.d.a(), this.d.b());
            }
            this.b.a();
        }
    }
}
