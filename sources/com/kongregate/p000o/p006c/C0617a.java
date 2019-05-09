package com.kongregate.p000o.p006c;

import android.os.AsyncTask;
import android.os.Looper;
import com.kongregate.android.internal.util.C0542a;
import com.kongregate.android.internal.util.C0562j;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.kongregate.o.c.a */
public final class C0617a extends AsyncTask<Void, Void, Void> {
    /* renamed from: a */
    private static final C0619f f949a = new C0619f(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory());
    /* renamed from: b */
    private final Runnable f950b;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m956a((Void[]) objArr);
    }

    /* renamed from: a */
    public static void m954a(Runnable runnable) {
        C0617a.m955a(runnable, false);
    }

    /* renamed from: a */
    public static void m955a(Runnable runnable, boolean z) {
        C0542a.m604a(f949a);
        try {
            new C0617a(runnable).execute(new Void[0]);
            return;
        } catch (Throwable e) {
            C0562j.m760c("Task execution rejected, running with backup executor", e);
        } catch (Throwable e2) {
            if (e2.getClass().getName().equals("android.view.ViewRoot$CalledFromWrongThreadException")) {
                C0562j.m760c("CalledFromWrongThreadException in BackgroundTask, running with backup executor", e2);
            } else {
                throw e2;
            }
        } catch (Throwable e22) {
            if (z) {
                C0562j.m762d("OOM thrown inside critical background task, rethrowing", e22);
                throw e22;
            } else {
                C0562j.m760c("OOM thrown inside non-critical background task", e22);
                return;
            }
        }
        f949a.execute(runnable);
    }

    private C0617a(Runnable runnable) {
        this.f950b = runnable;
        m953a();
    }

    /* renamed from: a */
    private void m953a() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            try {
                throw new RuntimeException();
            } catch (Throwable e) {
                C0562j.m760c("BackgroundTask.execute called from non-UI thread", e);
            }
        }
    }

    /* renamed from: a */
    protected Void m956a(Void... voidArr) {
        this.f950b.run();
        return null;
    }
}
