package com.facebook.ads.internal.p040j;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p026b.C2587p;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p040j.C2026f.C2032a;
import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.facebook.ads.internal.j.d */
public class C2030d {
    /* renamed from: a */
    private static final String f4511a = ("SELECT tokens." + C2033h.f4529a.f4484b + ", " + "tokens" + "." + C2033h.f4530b.f4484b + ", " + EventEntry.TABLE_NAME + "." + C2025c.f4487a.f4484b + ", " + EventEntry.TABLE_NAME + "." + C2025c.f4489c.f4484b + ", " + EventEntry.TABLE_NAME + "." + C2025c.f4490d.f4484b + ", " + EventEntry.TABLE_NAME + "." + C2025c.f4491e.f4484b + ", " + EventEntry.TABLE_NAME + "." + C2025c.f4492f.f4484b + ", " + EventEntry.TABLE_NAME + "." + C2025c.f4493g.f4484b + ", " + EventEntry.TABLE_NAME + "." + C2025c.f4494h.f4484b + ", " + EventEntry.TABLE_NAME + "." + C2025c.f4495i.f4484b + " FROM " + EventEntry.TABLE_NAME + " JOIN " + "tokens" + " ON " + EventEntry.TABLE_NAME + "." + C2025c.f4488b.f4484b + " = " + "tokens" + "." + C2033h.f4529a.f4484b + " ORDER BY " + EventEntry.TABLE_NAME + "." + C2025c.f4491e.f4484b + " ASC");
    /* renamed from: b */
    private static final ReentrantReadWriteLock f4512b = new ReentrantReadWriteLock();
    /* renamed from: c */
    private static final Lock f4513c = f4512b.readLock();
    /* renamed from: d */
    private static final Lock f4514d = f4512b.writeLock();
    /* renamed from: e */
    private final Context f4515e;
    /* renamed from: f */
    private final C2033h f4516f = new C2033h(this);
    /* renamed from: g */
    private final C2025c f4517g = new C2025c(this);
    /* renamed from: h */
    private SQLiteOpenHelper f4518h;

    /* renamed from: com.facebook.ads.internal.j.d$a */
    private static class C2029a<T> extends AsyncTask<Void, Void, T> {
        /* renamed from: a */
        private final C2026f<T> f4507a;
        /* renamed from: b */
        private final C2022a<T> f4508b;
        /* renamed from: c */
        private final Context f4509c;
        /* renamed from: d */
        private C2032a f4510d;

        C2029a(Context context, C2026f<T> c2026f, C2022a<T> c2022a) {
            this.f4507a = c2026f;
            this.f4508b = c2022a;
            this.f4509c = context;
        }

        /* renamed from: a */
        protected T m4899a(Void... voidArr) {
            T b;
            Exception e;
            try {
                b = this.f4507a.mo5462b();
                try {
                    this.f4510d = this.f4507a.m4896c();
                } catch (Exception e2) {
                    e = e2;
                    C2625a.m6741b(this.f4509c, "database", C2626b.f6559x, e);
                    this.f4510d = C2032a.UNKNOWN;
                    return b;
                }
            } catch (Exception e3) {
                Exception exception = e3;
                b = null;
                e = exception;
                C2625a.m6741b(this.f4509c, "database", C2626b.f6559x, e);
                this.f4510d = C2032a.UNKNOWN;
                return b;
            }
            return b;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m4899a((Void[]) objArr);
        }

        protected void onPostExecute(T t) {
            if (this.f4510d == null) {
                this.f4508b.mo5468a(t);
            } else {
                this.f4508b.mo5467a(this.f4510d.m4918a(), this.f4510d.m4919b());
            }
            this.f4508b.m4875a();
        }
    }

    public C2030d(Context context) {
        this.f4515e = context;
    }

    /* renamed from: j */
    private synchronized SQLiteDatabase m4904j() {
        if (this.f4518h == null) {
            this.f4518h = new C2031e(this.f4515e, this);
        }
        return this.f4518h.getWritableDatabase();
    }

    @WorkerThread
    /* renamed from: a */
    public Cursor m4905a(int i) {
        f4513c.lock();
        try {
            Cursor rawQuery = m4906a().rawQuery(f4511a + " LIMIT " + String.valueOf(i), null);
            return rawQuery;
        } finally {
            f4513c.unlock();
        }
    }

    /* renamed from: a */
    public SQLiteDatabase m4906a() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return m4904j();
        }
        throw new IllegalStateException("Cannot call getDatabase from the UI thread!");
    }

    /* renamed from: a */
    public <T> AsyncTask m4907a(C2026f<T> c2026f, C2022a<T> c2022a) {
        Executor executor = C2587p.f6373b;
        AsyncTask c2029a = new C2029a(this.f4515e.getApplicationContext(), c2026f, c2022a);
        Void[] voidArr = new Void[0];
        if (VERSION.SDK_INT >= 11) {
            c2029a.executeOnExecutor(executor, voidArr);
        } else {
            c2029a.execute(voidArr);
        }
        return c2029a;
    }

    /* renamed from: a */
    public AsyncTask m4908a(String str, int i, String str2, double d, double d2, String str3, Map<String, String> map, C2022a<String> c2022a) {
        final String str4 = str;
        final int i2 = i;
        final String str5 = str2;
        final double d3 = d;
        final double d4 = d2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        return m4907a(new C2027i<String>(this) {
            /* renamed from: h */
            final /* synthetic */ C2030d f4506h;

            @Nullable
            /* renamed from: a */
            public String m4897a() {
                Exception e;
                SQLiteDatabase sQLiteDatabase;
                Throwable th;
                if (TextUtils.isEmpty(str4)) {
                    return null;
                }
                C2030d.f4514d.lock();
                SQLiteDatabase a;
                try {
                    a = this.f4506h.m4906a();
                    try {
                        a.beginTransaction();
                        String a2 = this.f4506h.f4517g.m4889a(this.f4506h.f4516f.m4921a(str4), i2, str5, d3, d4, str6, map2);
                        a.setTransactionSuccessful();
                        if (a != null && a.isOpen()) {
                            try {
                                if (a.inTransaction()) {
                                    a.endTransaction();
                                }
                            } catch (Exception e2) {
                                C2625a.m6741b(this.f4506h.f4515e, "database", C2626b.f6558w, e2);
                            }
                        }
                        C2030d.f4514d.unlock();
                        return a2;
                    } catch (Exception e3) {
                        e = e3;
                        sQLiteDatabase = a;
                        try {
                            m4894a(C2032a.DATABASE_INSERT);
                            C2625a.m6741b(this.f4506h.f4515e, "database", C2626b.f6556u, e);
                            if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                                try {
                                    if (sQLiteDatabase.inTransaction()) {
                                        sQLiteDatabase.endTransaction();
                                    }
                                } catch (Exception e4) {
                                    C2625a.m6741b(this.f4506h.f4515e, "database", C2626b.f6558w, e4);
                                }
                            }
                            C2030d.f4514d.unlock();
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            a = sQLiteDatabase;
                            if (a != null && a.isOpen()) {
                                try {
                                    if (a.inTransaction()) {
                                        a.endTransaction();
                                    }
                                } catch (Exception e22) {
                                    C2625a.m6741b(this.f4506h.f4515e, "database", C2626b.f6558w, e22);
                                }
                            }
                            C2030d.f4514d.unlock();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (a.inTransaction()) {
                            a.endTransaction();
                        }
                        C2030d.f4514d.unlock();
                        throw th;
                    }
                } catch (Exception e5) {
                    e4 = e5;
                    sQLiteDatabase = null;
                    m4894a(C2032a.DATABASE_INSERT);
                    C2625a.m6741b(this.f4506h.f4515e, "database", C2626b.f6556u, e4);
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                    C2030d.f4514d.unlock();
                    return null;
                } catch (Throwable th4) {
                    th = th4;
                    a = null;
                    if (a.inTransaction()) {
                        a.endTransaction();
                    }
                    C2030d.f4514d.unlock();
                    throw th;
                }
            }

            @Nullable
            /* renamed from: b */
            public /* synthetic */ Object mo5462b() {
                return m4897a();
            }
        }, c2022a);
    }

    @WorkerThread
    /* renamed from: a */
    public boolean m4909a(String str) {
        boolean z = true;
        f4514d.lock();
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("UPDATE ").append(EventEntry.TABLE_NAME).append(" SET ").append(C2025c.f4495i.f4484b).append(RequestParameters.EQUAL).append(C2025c.f4495i.f4484b).append("+1").append(" WHERE ").append(C2025c.f4487a.f4484b).append("=?");
            m4906a().execSQL(stringBuilder.toString(), new String[]{str});
        } catch (SQLiteException e) {
            z = false;
        }
        f4514d.unlock();
        return z;
    }

    /* renamed from: b */
    public synchronized void m4910b() {
        for (C2024g e : m4912c()) {
            e.m4885e();
        }
        if (this.f4518h != null) {
            this.f4518h.close();
            this.f4518h = null;
        }
    }

    @WorkerThread
    /* renamed from: b */
    public boolean m4911b(String str) {
        f4514d.lock();
        try {
            boolean a = this.f4517g.m4890a(str);
            return a;
        } finally {
            f4514d.unlock();
        }
    }

    /* renamed from: c */
    public C2024g[] m4912c() {
        return new C2024g[]{this.f4516f, this.f4517g};
    }

    /* renamed from: d */
    public Cursor m4913d() {
        f4513c.lock();
        try {
            Cursor c = this.f4517g.mo5461c();
            return c;
        } finally {
            f4513c.unlock();
        }
    }

    @WorkerThread
    /* renamed from: e */
    public Cursor m4914e() {
        f4513c.lock();
        try {
            Cursor d = this.f4517g.m4893d();
            return d;
        } finally {
            f4513c.unlock();
        }
    }

    @WorkerThread
    /* renamed from: f */
    public Cursor m4915f() {
        f4513c.lock();
        try {
            Cursor c = this.f4516f.mo5461c();
            return c;
        } finally {
            f4513c.unlock();
        }
    }

    @WorkerThread
    /* renamed from: g */
    public void m4916g() {
        f4514d.lock();
        try {
            this.f4516f.m4924d();
        } finally {
            f4514d.unlock();
        }
    }

    @WorkerThread
    /* renamed from: h */
    public void m4917h() {
        f4514d.lock();
        try {
            this.f4517g.m4887g();
            this.f4516f.m4887g();
        } finally {
            f4514d.unlock();
        }
    }
}
