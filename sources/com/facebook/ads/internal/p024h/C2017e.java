package com.facebook.ads.internal.p024h;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.p046v.p053b.C2175f;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: com.facebook.ads.internal.h.e */
public class C2017e {
    /* renamed from: a */
    private static final String f4470a = C2017e.class.getSimpleName();
    /* renamed from: b */
    private static C2017e f4471b;
    /* renamed from: c */
    private final Future<C2175f> f4472c;

    private C2017e(final Context context) {
        this.f4472c = Executors.newSingleThreadExecutor().submit(new Callable<C2175f>(this) {
            /* renamed from: b */
            final /* synthetic */ C2017e f4469b;

            /* renamed from: a */
            public C2175f m4865a() {
                return new C2175f(context);
            }

            public /* synthetic */ Object call() {
                return m4865a();
            }
        });
    }

    /* renamed from: a */
    public static C2017e m4866a(Context context) {
        if (f4471b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (C2017e.class) {
                if (f4471b == null) {
                    f4471b = new C2017e(applicationContext);
                }
            }
        }
        return f4471b;
    }

    @Nullable
    /* renamed from: a */
    private C2175f m4867a() {
        Throwable e;
        try {
            return (C2175f) this.f4472c.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        } catch (TimeoutException e4) {
            e = e4;
        }
        Log.e(f4470a, "Timed out waiting for cache server.", e);
        return null;
    }

    /* renamed from: a */
    public boolean m4868a(String str) {
        C2175f a = m4867a();
        return a != null && a.m5572a(str);
    }

    @Nullable
    /* renamed from: b */
    public String m4869b(String str) {
        C2175f a = m4867a();
        return a == null ? null : a.m5573b(str);
    }
}
