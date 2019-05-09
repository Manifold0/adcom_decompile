package com.facebook.ads.internal.p051s;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p057e.C2612d;
import com.facebook.ads.internal.p046v.p047a.C2138a;
import com.facebook.ads.internal.p046v.p047a.C2150n;
import com.facebook.ads.internal.p046v.p047a.C2152p;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import com.tonyodev.fetch.FetchConst;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.s.b */
public class C2084b {
    /* renamed from: a */
    private static final String f4715a = C2084b.class.getSimpleName();
    /* renamed from: b */
    private final C2083a f4716b;
    /* renamed from: c */
    private final ThreadPoolExecutor f4717c;
    /* renamed from: d */
    private final ConnectivityManager f4718d;
    /* renamed from: e */
    private final C2138a f4719e;
    /* renamed from: f */
    private final Handler f4720f;
    /* renamed from: g */
    private final long f4721g;
    /* renamed from: h */
    private final long f4722h;
    /* renamed from: i */
    private final Context f4723i;
    /* renamed from: j */
    private final Runnable f4724j = new C20811(this);
    /* renamed from: k */
    private final Runnable f4725k = new C20822(this);
    /* renamed from: l */
    private volatile boolean f4726l;
    /* renamed from: m */
    private int f4727m;
    /* renamed from: n */
    private long f4728n;

    /* renamed from: com.facebook.ads.internal.s.b$1 */
    class C20811 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2084b f4713a;

        C20811(C2084b c2084b) {
            this.f4713a = c2084b;
        }

        public void run() {
            C2084b.m5147a(this.f4713a);
            if (this.f4713a.f4728n > 0) {
                try {
                    Thread.sleep(this.f4713a.f4728n);
                } catch (InterruptedException e) {
                }
            }
            this.f4713a.m5157c();
        }
    }

    /* renamed from: com.facebook.ads.internal.s.b$2 */
    class C20822 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2084b f4714a;

        C20822(C2084b c2084b) {
            this.f4714a = c2084b;
        }

        public void run() {
            this.f4714a.f4726l = false;
            if (this.f4714a.f4717c.getQueue().isEmpty()) {
                this.f4714a.f4717c.execute(this.f4714a.f4724j);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.s.b$a */
    public interface C2083a {
        /* renamed from: a */
        JSONObject mo5490a();

        /* renamed from: a */
        boolean mo5491a(JSONArray jSONArray);

        /* renamed from: b */
        void mo5492b();

        /* renamed from: b */
        void mo5493b(JSONArray jSONArray);

        /* renamed from: c */
        void mo5494c();

        /* renamed from: d */
        boolean mo5495d();
    }

    @UiThread
    C2084b(Context context, C2083a c2083a) {
        this.f4716b = c2083a;
        this.f4723i = context;
        this.f4717c = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        this.f4718d = (ConnectivityManager) context.getSystemService("connectivity");
        this.f4719e = C2612d.m6713b(context);
        this.f4720f = new Handler(Looper.getMainLooper());
        this.f4721g = C2078a.m5112v(context);
        this.f4722h = C2078a.m5113w(context);
    }

    /* renamed from: a */
    static /* synthetic */ int m5147a(C2084b c2084b) {
        int i = c2084b.f4727m + 1;
        c2084b.f4727m = i;
        return i;
    }

    /* renamed from: a */
    private void m5148a(long j) {
        this.f4720f.postDelayed(this.f4725k, j);
    }

    /* renamed from: d */
    private void m5153d() {
        if (this.f4727m >= 5) {
            m5154e();
            m5156b();
            return;
        }
        if (this.f4727m == 1) {
            this.f4728n = FetchConst.DEFAULT_ON_UPDATE_INTERVAL;
        } else {
            this.f4728n *= 2;
        }
        m5155a();
    }

    /* renamed from: e */
    private void m5154e() {
        this.f4727m = 0;
        this.f4728n = 0;
        if (this.f4717c.getQueue().size() == 0) {
            this.f4716b.mo5492b();
        }
    }

    /* renamed from: a */
    void m5155a() {
        this.f4726l = true;
        this.f4720f.removeCallbacks(this.f4725k);
        m5148a(this.f4721g);
    }

    /* renamed from: b */
    void m5156b() {
        if (!this.f4726l) {
            this.f4726l = true;
            this.f4720f.removeCallbacks(this.f4725k);
            m5148a(this.f4722h);
        }
    }

    @WorkerThread
    /* renamed from: c */
    void m5157c() {
        try {
            NetworkInfo activeNetworkInfo = this.f4718d.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                m5148a(this.f4722h);
                return;
            }
            JSONObject a = this.f4716b.mo5490a();
            if (a == null) {
                m5154e();
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("attempt", String.valueOf(this.f4727m));
            a.put("data", jSONObject);
            C2152p c2152p = new C2152p();
            c2152p.m5511a(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, a.toString());
            C2138a c2138a = this.f4719e;
            Context context = this.f4723i;
            String format = TextUtils.isEmpty(AdInternalSettings.getUrlPrefix()) ? "https://www.facebook.com/adnw_logging/" : String.format(Locale.US, "https://www.%s.facebook.com/adnw_logging/", new Object[]{AdInternalSettings.getUrlPrefix()});
            CharSequence M = C2078a.m5074M(context);
            if (!TextUtils.isEmpty(M)) {
                format = format.replace("www", M);
            }
            C2150n b = c2138a.m5470b(format, c2152p);
            Object e = b != null ? b.m5505e() : null;
            if (TextUtils.isEmpty(e)) {
                if (a.has(EventEntry.TABLE_NAME)) {
                    this.f4716b.mo5493b(a.getJSONArray(EventEntry.TABLE_NAME));
                }
                m5153d();
            } else if (b.m5501a() != 200) {
                if (b.m5501a() == 413 && C2078a.m5071J(this.f4723i)) {
                    this.f4716b.mo5494c();
                    m5154e();
                    return;
                }
                if (a.has(EventEntry.TABLE_NAME)) {
                    this.f4716b.mo5493b(a.getJSONArray(EventEntry.TABLE_NAME));
                }
                m5153d();
            } else if (!this.f4716b.mo5491a(new JSONArray(e))) {
                m5153d();
            } else if (this.f4716b.mo5495d()) {
                m5153d();
            } else {
                m5154e();
            }
        } catch (Exception e2) {
            m5153d();
        }
    }
}
