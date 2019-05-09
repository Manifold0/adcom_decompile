package com.facebook.ads.internal.p046v.p047a;

import android.os.AsyncTask;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.facebook.ads.internal.v.a.h */
public class C2144h extends AsyncTask<C2145l, Void, C2150n> implements C2139c {
    /* renamed from: a */
    private static Executor f4963a = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    /* renamed from: b */
    private C2138a f4964b;
    /* renamed from: c */
    private C2124b f4965c;
    /* renamed from: d */
    private Exception f4966d;

    public C2144h(C2138a c2138a, C2124b c2124b) {
        this.f4964b = c2138a;
        this.f4965c = c2124b;
    }

    /* renamed from: a */
    protected C2150n m5490a(C2145l... c2145lArr) {
        if (c2145lArr != null) {
            try {
                if (c2145lArr.length > 0) {
                    return this.f4964b.m5457a(c2145lArr[0]);
                }
            } catch (Exception e) {
                this.f4966d = e;
                cancel(true);
                return null;
            }
        }
        throw new IllegalArgumentException("DoHttpRequestTask takes exactly one argument of type HttpRequest");
    }

    /* renamed from: a */
    public void mo5519a(C2145l c2145l) {
        super.executeOnExecutor(f4963a, new C2145l[]{c2145l});
    }

    /* renamed from: a */
    protected void m5492a(C2150n c2150n) {
        this.f4965c.mo5504a(c2150n);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m5490a((C2145l[]) objArr);
    }

    protected void onCancelled() {
        this.f4965c.mo5505a(this.f4966d);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m5492a((C2150n) obj);
    }
}
