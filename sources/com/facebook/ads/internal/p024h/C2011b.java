package com.facebook.ads.internal.p024h;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.h.b */
public class C2011b {
    /* renamed from: a */
    private static final String f4450a = C2011b.class.getSimpleName();
    /* renamed from: b */
    private static final ExecutorService f4451b = Executors.newSingleThreadExecutor();
    /* renamed from: c */
    private static final ExecutorService f4452c = Executors.newFixedThreadPool(5);
    /* renamed from: d */
    private final Handler f4453d = new Handler();
    /* renamed from: e */
    private final C2015d f4454e;
    /* renamed from: f */
    private final C2017e f4455f;
    /* renamed from: g */
    private final C2014c f4456g;
    /* renamed from: h */
    private final List<Callable<Boolean>> f4457h;

    /* renamed from: com.facebook.ads.internal.h.b$a */
    private class C2008a implements Callable<Boolean> {
        /* renamed from: a */
        final /* synthetic */ C2011b f4442a;
        /* renamed from: b */
        private final String f4443b;

        public C2008a(C2011b c2011b, String str) {
            this.f4442a = c2011b;
            this.f4443b = str;
        }

        /* renamed from: a */
        public Boolean m4833a() {
            return Boolean.valueOf(this.f4442a.f4456g.m4854a(this.f4443b));
        }

        public /* synthetic */ Object call() {
            return m4833a();
        }
    }

    /* renamed from: com.facebook.ads.internal.h.b$b */
    private class C2009b implements Callable<Boolean> {
        /* renamed from: a */
        final /* synthetic */ C2011b f4444a;
        /* renamed from: b */
        private final String f4445b;
        /* renamed from: c */
        private final int f4446c;
        /* renamed from: d */
        private final int f4447d;

        public C2009b(C2011b c2011b, String str, int i, int i2) {
            this.f4444a = c2011b;
            this.f4445b = str;
            this.f4446c = i;
            this.f4447d = i2;
        }

        /* renamed from: a */
        public Boolean m4834a() {
            return Boolean.valueOf(this.f4444a.f4454e.m4864a(this.f4445b, this.f4446c, this.f4447d) != null);
        }

        public /* synthetic */ Object call() {
            return m4834a();
        }
    }

    /* renamed from: com.facebook.ads.internal.h.b$c */
    private class C2010c implements Callable<Boolean> {
        /* renamed from: a */
        final /* synthetic */ C2011b f4448a;
        /* renamed from: b */
        private final String f4449b;

        public C2010c(C2011b c2011b, String str) {
            this.f4448a = c2011b;
            this.f4449b = str;
        }

        /* renamed from: a */
        public Boolean m4835a() {
            return Boolean.valueOf(this.f4448a.f4455f.m4868a(this.f4449b));
        }

        public /* synthetic */ Object call() {
            return m4835a();
        }
    }

    public C2011b(Context context) {
        this.f4454e = C2015d.m4857a(context);
        this.f4455f = C2017e.m4866a(context);
        this.f4456g = C2014c.m4851a(context);
        this.f4457h = new ArrayList();
    }

    /* renamed from: a */
    public void m4842a(@Nullable final C1832a c1832a) {
        final ArrayList arrayList = new ArrayList(this.f4457h);
        f4451b.execute(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C2011b f4441c;

            public void run() {
                Throwable e;
                List<Future> arrayList = new ArrayList(arrayList.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(C2011b.f4452c.submit((Callable) it.next()));
                }
                final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                try {
                    for (Future future : arrayList) {
                        atomicBoolean.set(((Boolean) future.get()).booleanValue() & atomicBoolean.get());
                    }
                } catch (InterruptedException e2) {
                    e = e2;
                    Log.e(C2011b.f4450a, "Exception while executing cache downloads.", e);
                    atomicBoolean.set(false);
                    this.f4441c.f4453d.post(new Runnable(this) {
                        /* renamed from: b */
                        final /* synthetic */ C20071 f4438b;

                        public void run() {
                            if (c1832a == null) {
                                return;
                            }
                            if (atomicBoolean.get()) {
                                c1832a.mo5368a();
                            } else {
                                c1832a.mo5369b();
                            }
                        }
                    });
                } catch (ExecutionException e3) {
                    e = e3;
                    Log.e(C2011b.f4450a, "Exception while executing cache downloads.", e);
                    atomicBoolean.set(false);
                    this.f4441c.f4453d.post(/* anonymous class already generated */);
                }
                this.f4441c.f4453d.post(/* anonymous class already generated */);
            }
        });
        this.f4457h.clear();
    }

    /* renamed from: a */
    public void m4843a(String str) {
        this.f4457h.add(new C2010c(this, str));
    }

    /* renamed from: a */
    public void m4844a(String str, int i, int i2) {
        this.f4457h.add(new C2009b(this, str, i, i2));
    }

    /* renamed from: b */
    public void m4845b(String str) {
        this.f4457h.add(new C2008a(this, str));
    }

    /* renamed from: c */
    public String m4846c(String str) {
        return this.f4455f.m4869b(str);
    }

    /* renamed from: d */
    public String m4847d(String str) {
        return this.f4456g.m4855b(str);
    }
}
