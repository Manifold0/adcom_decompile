package com.kongregate.p000o.p003a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import com.kongregate.android.api.AnalyticsServices;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.internal.sdk.C0487b;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.p000o.p002g.C0640a;
import com.kongregate.p000o.p002g.C0645b;
import com.kongregate.p000o.p002g.C0645b.C0468a;
import com.kongregate.p000o.p002g.C0646c;
import com.kongregate.p000o.p007d.C0635b;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.a.i */
public class C0593i {
    /* renamed from: a */
    private Context f842a;
    /* renamed from: b */
    private String f843b;
    /* renamed from: c */
    private String f844c;
    /* renamed from: d */
    private String f845d;
    /* renamed from: e */
    private AtomicBoolean f846e;
    /* renamed from: f */
    private SharedPreferences f847f;
    /* renamed from: g */
    private List<Map<String, Object>> f848g;
    /* renamed from: h */
    private final Object f849h = new Object();
    /* renamed from: i */
    private Boolean f850i = Boolean.valueOf(false);
    /* renamed from: j */
    private final Handler f851j = new Handler(Looper.getMainLooper());
    /* renamed from: k */
    private final Runnable f852k = new C05901(this);

    /* renamed from: com.kongregate.o.a.i$1 */
    class C05901 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0593i f838a;

        C05901(C0593i c0593i) {
            this.f838a = c0593i;
        }

        public void run() {
            this.f838a.m898f();
            this.f838a.f850i = Boolean.valueOf(false);
            this.f838a.m897e();
        }
    }

    /* renamed from: com.kongregate.o.a.i$2 */
    class C05912 extends TypeToken<List<Map<String, Object>>> {
        /* renamed from: a */
        final /* synthetic */ C0593i f839a;

        C05912(C0593i c0593i) {
            this.f839a = c0593i;
        }
    }

    public C0593i(Context context) {
        this.f842a = context.getApplicationContext();
    }

    /* renamed from: a */
    public boolean m902a(Map<String, Object> map) {
        this.f843b = C0487b.m421a((Map) map, KongregateAPI.KONGREGATE_OPTION_KONG_ANALYTICS_ID);
        this.f844c = C0487b.m421a((Map) map, KongregateAPI.KONGREGATE_OPTION_KONG_ANALYTICS_KEY);
        if (TextUtils.isEmpty(this.f843b) || TextUtils.isEmpty(this.f844c)) {
            C0562j.m753a("Kong Analytics Service - Analytics ID or API Key missing, not initialized");
            return false;
        }
        if (!this.f843b.startsWith("/")) {
            this.f843b = "/" + this.f843b;
        }
        try {
            this.f845d = URI.create("https://" + C0487b.m422a((Map) map, KongregateAPI.KONGREGATE_OPTION_ANALYTICS_DOMAIN, C0635b.f970a) + this.f843b).toString();
            this.f848g = new ArrayList();
            Context context = this.f842a;
            this.f847f = this.f842a.getSharedPreferences("kongregate_analytics_wrapper", 0);
            this.f846e = new AtomicBoolean(false);
            m896d();
            m897e();
            C0562j.m753a("Kong Analytics Service Initialized");
            return true;
        } catch (IllegalArgumentException e) {
            C0562j.m753a("Invalid Kong Analytics URL");
            return false;
        }
    }

    /* renamed from: a */
    public void m899a() {
        m898f();
        this.f851j.removeCallbacks(this.f852k);
        this.f850i = Boolean.valueOf(false);
    }

    /* renamed from: b */
    public void m903b() {
        m897e();
    }

    /* renamed from: b */
    private boolean m893b(String str) {
        return (TextUtils.isEmpty(str) || str.startsWith("swrve.") || str.startsWith(AnalyticsServices.DELTA_EVENT_PREFIX)) ? false : true;
    }

    /* renamed from: a */
    public void m900a(String str) {
        m901a(str, new HashMap());
    }

    /* renamed from: a */
    public void m901a(String str, Map<String, Object> map) {
        if (m893b(str)) {
            m892b(str, map);
        }
    }

    /* renamed from: b */
    private void m892b(String str, Map<String, Object> map) {
        C0561i.m742a((Map) map);
        Map hashMap = new HashMap();
        hashMap.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, map);
        hashMap.put("event_type", str);
        synchronized (this.f849h) {
            if (this.f848g == null) {
                C0562j.m753a("KongAnalyticsWrapper - Pending events not initialized");
                return;
            }
            this.f848g.add(hashMap);
            if (this.f848g.size() > 100) {
                C0562j.m753a("Saved events overflow, discarding oldest event");
                this.f848g.remove(0);
            }
            m895c();
            m898f();
        }
    }

    /* renamed from: a */
    private void m890a(List<Map<String, Object>> list) {
        synchronized (this.f849h) {
            this.f848g.removeAll(list);
            m895c();
        }
    }

    /* renamed from: c */
    private void m895c() {
        synchronized (this.f849h) {
            this.f847f.edit().putString("pending_events", new Gson().toJson(this.f848g)).commit();
        }
    }

    /* renamed from: d */
    private void m896d() {
        String string = this.f847f.getString("pending_events", "[]");
        ArrayList arrayList = new ArrayList();
        try {
            List list = (List) new Gson().fromJson(string, new C05912(this).getType());
        } catch (JsonSyntaxException e) {
            C0562j.m759c("Failed to parse saved event json, discarding corrupt event data");
            Object obj = arrayList;
        }
        synchronized (this.f849h) {
            this.f848g = list;
        }
    }

    /* renamed from: e */
    private void m897e() {
        if (!this.f850i.booleanValue()) {
            this.f850i = Boolean.valueOf(true);
            this.f851j.postDelayed(this.f852k, 30000);
        }
    }

    /* renamed from: f */
    private void m898f() {
        if (this.f846e.compareAndSet(false, true)) {
            final List arrayList;
            Map hashMap = new HashMap();
            hashMap.put("X-Api-Key", this.f844c);
            synchronized (this.f849h) {
                arrayList = new ArrayList(this.f848g);
            }
            if (arrayList == null || arrayList.size() == 0) {
                this.f846e.set(false);
                return;
            }
            Map hashMap2 = new HashMap();
            hashMap2.put(EventEntry.TABLE_NAME, arrayList);
            try {
                C0645b.m1085a().m1092a(this.f845d, hashMap2, C0640a.f1003a, hashMap, new C0468a(this) {
                    /* renamed from: b */
                    final /* synthetic */ C0593i f841b;

                    /* renamed from: a */
                    public void mo1133a(C0646c c0646c, JSONObject jSONObject) {
                        C0562j.m753a("Event submitted complete: " + c0646c.m1098e());
                        if (c0646c.m787f()) {
                            this.f841b.m890a(arrayList);
                        }
                        this.f841b.f846e.set(false);
                    }

                    /* renamed from: a */
                    public void mo1185a(C0646c c0646c) {
                        super.mo1185a(c0646c);
                        m883b(c0646c);
                    }

                    /* renamed from: b */
                    public void mo1186b(C0646c c0646c, JSONObject jSONObject) {
                        super.mo1186b(c0646c, jSONObject);
                        m883b(c0646c);
                    }

                    /* renamed from: b */
                    private void m883b(C0646c c0646c) {
                        long e = (long) c0646c.m1098e();
                        C0562j.m753a("Event submit failed: " + e);
                        if (e == 0 || e >= 500) {
                            C0562j.m753a("Non-fatal error, will retry");
                        } else {
                            C0562j.m753a("Rejected payload, discarding sent events");
                            this.f841b.m890a(arrayList);
                        }
                        this.f841b.f846e.set(false);
                    }
                });
            } catch (IllegalStateException e) {
                C0562j.m753a("HttpQueueManager not initialized");
                this.f846e.set(false);
            }
        }
    }
}
