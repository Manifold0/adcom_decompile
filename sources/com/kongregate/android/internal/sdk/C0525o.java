package com.kongregate.android.internal.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.Constants;
import com.adjust.sdk.OnAttributionChangedListener;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.nearby.messages.Strategy;
import com.google.gson.Gson;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.AnalyticsServices;
import com.kongregate.android.api.AnalyticsServices.AutoEventListener;
import com.kongregate.android.api.AnalyticsServices.Events;
import com.kongregate.android.api.AnalyticsServices.Fields;
import com.kongregate.android.api.AnalyticsServices.IabResult;
import com.kongregate.android.api.CommonPropertiesEvaluator;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.api.receivers.InstallReceiver;
import com.kongregate.android.internal.sdk.o.AnonymousClass13;
import com.kongregate.android.internal.sdk.o.AnonymousClass14;
import com.kongregate.android.internal.util.C0545c;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0563k;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p001j.C0666a;
import com.kongregate.p000o.p002g.C0640a;
import com.kongregate.p000o.p003a.C0524b;
import com.kongregate.p000o.p003a.C0578a;
import com.kongregate.p000o.p003a.C0580c;
import com.kongregate.p000o.p003a.C0582d;
import com.kongregate.p000o.p003a.C0582d.C0532b;
import com.kongregate.p000o.p003a.C0582d.C0534c;
import com.kongregate.p000o.p003a.C0583e;
import com.kongregate.p000o.p003a.C0593i;
import com.kongregate.p000o.p003a.C0597k;
import com.kongregate.p000o.p003a.C0597k.C0596a;
import com.kongregate.p000o.p006c.C0626d;
import com.kongregate.p000o.p010i.C0657a;
import com.tapjoy.TapjoyConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(9)
/* renamed from: com.kongregate.android.internal.sdk.o */
public class C0525o implements AnalyticsServices, C0524b {
    /* renamed from: a */
    static final Fields[] f615a = new Fields[]{Fields.EMAIL, Fields.FB_USER_ID, Fields.FB_USERNAME, Fields.FB_EMAIL, Fields.KONG_USERNAME, Fields.KONG_USER_ID, Fields.KONG_JOIN_DATE, Fields.PUR_TIER, Fields.PUR_LINK_DATE, Fields.SITE_VISITOR_ID, Fields.TWITTER_ID, Fields.USD_SPENT_ON_KREDS};
    /* renamed from: b */
    public static final String f616b = "utm_source";
    /* renamed from: c */
    public static final String f617c = "utm_medium";
    /* renamed from: d */
    public static final String f618d = "utm_term";
    /* renamed from: e */
    public static final String f619e = "utm_content";
    /* renamed from: f */
    public static final String f620f = "utm_campaign";
    /* renamed from: g */
    public static final String f621g = "usd_cost";
    /* renamed from: h */
    public static final String f622h = "product_id";
    /* renamed from: i */
    public static final String f623i = "local_currency_type";
    /* renamed from: j */
    public static final String f624j = "local_currency_cost";
    /* renamed from: k */
    public static final String f625k = "receipt_id";
    /* renamed from: l */
    public static final String f626l = "receipt_data";
    /* renamed from: m */
    public static final String f627m = "receipt_signature";
    /* renamed from: n */
    public static final String f628n = "iap_id";
    /* renamed from: o */
    public static final String f629o = "fail_reason";
    /* renamed from: p */
    public static final String f630p = "success_reason";
    /* renamed from: q */
    public static final String f631q = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    /* renamed from: r */
    public static final ThreadLocal<DateFormat> f632r = new C05151();
    /* renamed from: s */
    private static final Fields[] f633s = new Fields[]{Fields.SESSION_ID, Fields.KONG_USER_ID, Fields.KONG_USERNAME, Fields.DATA_CONNECTION_TYPE, Fields.EVENT_TIME, Fields.EVENT_TIME_OFFSET, Fields.IP_ADDRESS, Fields.TIME_SKEW, Fields.FIRST_SERVER_VERSION, Fields.FILTER_TYPE, Fields.DEVICE_EVENT_ID, Fields.NUM_SESSIONS, Fields.EXTERNAL_IP_ADDRESS, Fields.KONG_PLUS, Fields.PUR_TIER, Fields.USD_SPENT_ON_KREDS, Fields.DAYS_RETAINED, Fields.NUM_PURCHASES, Fields.SITE_VISITOR_ID, Fields.PUSH_NOTIFICATIONS, Fields.PRIVACY_POLICY_ACCEPTED_AT, Fields.PRIVACY_POLICY_VERSION};
    /* renamed from: t */
    private static final Fields[] f634t = new Fields[]{Fields.TOTAL_SPENT_IN_USD};
    /* renamed from: u */
    private static final Fields[] f635u = new Fields[]{Fields.FIRST_SERVER_VERSION};
    /* renamed from: v */
    private static final Map<String, Object> f636v = Collections.unmodifiableMap(new HashMap(0));
    /* renamed from: w */
    private static final Map<String, String> f637w = new HashMap();
    /* renamed from: x */
    private static final Pattern f638x = Pattern.compile("^[a-zA-Z0-9_]+$");
    /* renamed from: y */
    private static final List<String> f639y = Arrays.asList(new String[]{"session_starts", "session_ends", "installs", "invalid_states", "iap_attempts", "iap_fails", "iap_transactions"});
    /* renamed from: A */
    private volatile CommonPropertiesEvaluator f640A = null;
    /* renamed from: B */
    private volatile AutoEventListener f641B = null;
    /* renamed from: C */
    private volatile ScheduledFuture f642C = null;
    /* renamed from: D */
    private final Object f643D = new Object();
    /* renamed from: E */
    private long f644E = 0;
    /* renamed from: F */
    private long f645F = 0;
    /* renamed from: G */
    private String f646G = KongregateAPI.ENABLE_ALL;
    /* renamed from: H */
    private volatile Collection<String> f647H = new ArrayList();
    /* renamed from: I */
    private C0578a f648I;
    /* renamed from: J */
    private final C0582d f649J;
    /* renamed from: K */
    private final C0593i f650K;
    /* renamed from: L */
    private C0597k f651L;
    /* renamed from: M */
    private boolean f652M;
    /* renamed from: N */
    private boolean f653N;
    /* renamed from: O */
    private boolean f654O;
    /* renamed from: P */
    private boolean f655P;
    /* renamed from: Q */
    private volatile Map<String, Object> f656Q;
    /* renamed from: R */
    private C0583e f657R = new C0583e("panel_closes");
    /* renamed from: S */
    private volatile Map<String, Object> f658S = null;
    /* renamed from: T */
    private volatile boolean f659T = false;
    /* renamed from: U */
    private volatile boolean f660U = false;
    /* renamed from: V */
    private volatile Map<String, Object> f661V = f636v;
    /* renamed from: W */
    private volatile AtomicBoolean f662W = new AtomicBoolean(false);
    /* renamed from: X */
    private volatile boolean f663X;
    /* renamed from: Y */
    private final AtomicBoolean f664Y = new AtomicBoolean(false);
    /* renamed from: Z */
    private boolean f665Z = false;
    /* renamed from: z */
    private Context f666z;

    /* renamed from: com.kongregate.android.internal.sdk.o$1 */
    static class C05151 extends ThreadLocal<DateFormat> {
        C05151() {
        }

        protected /* synthetic */ Object initialValue() {
            return m467a();
        }

        /* renamed from: a */
        protected DateFormat m467a() {
            DateFormat simpleDateFormat = new SimpleDateFormat(C0525o.f631q, Locale.US);
            simpleDateFormat.setCalendar(GregorianCalendar.getInstance());
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.o$6 */
    class C05206 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0525o f611a;

        C05206(C0525o c0525o) {
            this.f611a = c0525o;
        }

        public void run() {
            if (this.f611a.f665Z) {
                this.f611a.m510v();
                synchronized (this.f611a.f643D) {
                    this.f611a.f642C.cancel(false);
                }
                this.f611a.f650K.m899a();
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.o$7 */
    class C05217 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0525o f612a;

        C05217(C0525o c0525o) {
            this.f612a = c0525o;
        }

        public void run() {
            if (this.f612a.f665Z) {
                this.f612a.m509u();
                synchronized (this.f612a.f643D) {
                    if (this.f612a.f642C.isCancelled()) {
                        this.f612a.m514z();
                    }
                }
                this.f612a.f650K.m903b();
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.o$8 */
    class C05228 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0525o f613a;

        C05228(C0525o c0525o) {
            this.f613a = c0525o;
        }

        public void run() {
            this.f613a.m531d().m834c();
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.o$9 */
    class C05239 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0525o f614a;

        C05239(C0525o c0525o) {
            this.f614a = c0525o;
        }

        public void run() {
            this.f614a.m540m();
        }
    }

    static {
        f637w.put("installs", C0578a.f790c);
        f637w.put("session_starts", C0578a.f789b);
        f637w.put("iap_transactions", C0578a.f788a);
    }

    public C0525o(Context context) {
        this.f666z = context.getApplicationContext();
        this.f648I = new C0578a(this.f666z);
        this.f649J = new C0582d(this.f666z);
        this.f650K = new C0593i(this.f666z);
        this.f651L = new C0597k(this.f666z);
    }

    /* renamed from: a */
    public void m519a(Activity activity, Map<String, Object> map) {
        boolean z = true;
        this.f656Q = map;
        this.f663X = Boolean.TRUE.equals(this.f656Q.get(KongregateAPI.KONGREGATE_OPTION_DEFER_ANALYTICS));
        this.f652M = C0487b.m425a(this.f656Q, "_delta_enabled_internal", true);
        this.f653N = C0487b.m425a(this.f656Q, "_kong_analytics_enabled_internal", true);
        this.f654O = C0487b.m425a(this.f656Q, "_adjust_enabled_internal", true);
        this.f646G = C0487b.m422a(this.f656Q, KongregateAPI.KONGREGATE_OPTION_ANALYTICS_MODE, KongregateAPI.ENABLE_ALL);
        if (m511w()) {
            C0562j.m756b("Automatic analytics disabled");
        } else if (m512x()) {
            C0562j.m756b("Game has a server. Will only track subset of automatic analytics");
        } else {
            C0562j.m756b("Automatic analytics enabled");
            this.f646G = KongregateAPI.ENABLE_ALL;
        }
        this.f647H = Arrays.asList(C0487b.m422a(this.f656Q, KongregateAPI.KONGREGATE_OPTION_AUTO_ANALYTICS_FILTER, "").split("\\s*,\\s*"));
        C0562j.m756b("filtering props: " + StringUtils.m568a(this.f647H.iterator(), ","));
        SharedPreferences sharedPreferences = this.f666z.getSharedPreferences("kongregate_analytics", 0);
        if (sharedPreferences.getString("first_play", null) != null) {
            z = false;
        }
        this.f655P = z;
        if (this.f655P && !m511w()) {
            Editor edit = sharedPreferences.edit();
            edit.putString("first_play", ((DateFormat) f632r.get()).format(new Date()));
            edit.putLong("first_play_time_offset", (long) TimeZone.getDefault().getOffset(System.currentTimeMillis()));
            edit.putString("first_version", C0563k.m769b(this.f666z));
            if (!m512x()) {
                edit.putString("player_id", m507s());
            }
            edit.putString("first_sdk_version", KongregateAPI.KONGREGATE_API_VERSION);
            edit.apply();
        }
        if (!sharedPreferences.contains("first_sdk_version")) {
            C0562j.m753a("Upgrade from pre 1.1.0.0");
            sharedPreferences.edit().putString("first_sdk_version", AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN).putLong("first_play_time_offset", (long) TimeZone.getDefault().getOffset(System.currentTimeMillis())).apply();
        }
        m470B();
        C0562j.m756b("AnalyticsServices initialized, deferred: " + this.f663X);
        if (!this.f663X) {
            start(activity);
        }
    }

    /* renamed from: s */
    private String m507s() {
        CharSequence a = C0487b.m422a(this.f656Q, KongregateAPI.KONGREGATE_OPTION_CUSTOM_PLAYER_ID, null);
        if (!StringUtils.m584b(a)) {
            return UUID.randomUUID().toString();
        }
        C0562j.m759c("Using custom player ID: " + a);
        return a;
    }

    public void start(final Activity activity) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0525o f575b;

            public void run() {
                this.f575b.m491c(activity);
            }
        });
    }

    /* renamed from: c */
    private void m491c(Activity activity) {
        if (this.f664Y.getAndSet(true)) {
            C0562j.m759c("Already starting AnalyticsServices, ignoring duplicate call");
            return;
        }
        C0562j.m756b("Starting Analytics subsystem");
        if (this.f652M) {
            C0562j.m756b("Initializing Delta");
            m520a(activity, this.f656Q, getAutoStringProperty(Fields.PLAYER_ID.fieldName()));
        } else {
            C0562j.m753a("Delta disabled via remote configuration, not initializing");
        }
        if (this.f653N) {
            m525a(this.f656Q);
        } else {
            C0562j.m753a("Kong Analytics Service disabled via remote configuration, not initializing");
        }
        if (this.f654O) {
            this.f648I.m831a(this.f656Q, new OnAttributionChangedListener(this) {
                /* renamed from: a */
                final /* synthetic */ C0525o f576a;

                {
                    this.f576a = r1;
                }

                public void onAttributionChanged(AdjustAttribution adjustAttribution) {
                    String str;
                    if (adjustAttribution != null) {
                        str = adjustAttribution.network + "." + adjustAttribution.campaign;
                    } else {
                        str = "null.null";
                    }
                    C0562j.m753a("adjust attribution changed: " + str);
                }
            });
        } else {
            C0562j.m753a("Adjust disabled via remote configuration, not initializing");
        }
        C0562j.m753a("Analytics services initialized");
        this.f665Z = true;
        if (this.f655P && m513y()) {
            Map referrerFields = InstallReceiver.getReferrerFields(this.f666z);
            referrerFields.put("stub_field", Integer.valueOf(0));
            m523a("installs", referrerFields);
        }
        if (C0545c.m627g()) {
            m488b("jailbroken", "jailbroken");
        }
        if (((NativeAPI) APIBootstrap.getInstance()).m367I()) {
            addFilterType("not_kong_signed");
        }
        m531d().m832b();
        m509u();
        m514z();
        m470B();
    }

    /* renamed from: a */
    void m520a(Activity activity, Map<String, Object> map, String str) {
        m527b().m849a(activity, activity.getApplication(), map, str);
    }

    /* renamed from: a */
    void m525a(Map<String, Object> map) {
        m530c().m902a((Map) map);
    }

    /* renamed from: a */
    void m523a(String str, Map<String, Object> map) {
        if (this.f647H.contains(str)) {
            C0562j.m756b("suppressing filtered auto event: " + str);
            return;
        }
        m508t();
        m483a(str, (Map) map, true);
    }

    /* renamed from: t */
    private void m508t() {
        if (C0626d.m967a()) {
            this.f661V = m469A();
        } else {
            C0626d.m962a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ C0525o f580a;

                {
                    this.f580a = r1;
                }

                public void run() {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    final Map hashMap = new HashMap();
                    C0626d.m968b(new Runnable(this) {
                        /* renamed from: c */
                        final /* synthetic */ AnonymousClass13 f579c;

                        public void run() {
                            hashMap.putAll(this.f579c.f580a.m469A());
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        if (countDownLatch.await(5000, TimeUnit.MILLISECONDS)) {
                            this.f580a.f661V = hashMap;
                            return;
                        }
                    } catch (InterruptedException e) {
                    }
                    C0562j.m759c("Failed to retrieve common properties, using stale ones");
                    if (this.f580a.f661V != C0525o.f636v) {
                        this.f580a.f662W.set(true);
                    }
                }
            });
        }
    }

    public void addEvent(String str, Map<String, Object> map) {
        if (m511w() || this.f647H.contains(str) || !f639y.contains(str)) {
            Map hashMap;
            m508t();
            if (map == null) {
                hashMap = new HashMap(0);
            }
            m483a(str, hashMap, false);
            return;
        }
        C0562j.m759c("ANALYTICS WARNING: Ignoring addEvent for " + str + " since this is a kong-automatic event");
    }

    /* renamed from: a */
    public Map<String, Object> m516a() {
        m508t();
        Map<String, Object> hashMap = new HashMap();
        hashMap.putAll(this.f661V);
        hashMap.putAll(m541n());
        return hashMap;
    }

    /* renamed from: a */
    private void m483a(final String str, final Map<String, Object> map, final boolean z) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ C0525o f587d;

            public void run() {
                if (this.f587d.f665Z) {
                    C0562j.m753a("adding event to collection: " + str);
                    if (str.startsWith("swrve.") || str.startsWith(AnalyticsServices.DELTA_EVENT_PREFIX)) {
                        this.f587d.m527b().m854a(str, map);
                        return;
                    }
                    final Map hashMap = new HashMap();
                    hashMap.putAll(this.f587d.f661V);
                    if (this.f587d.f662W.getAndSet(false)) {
                        hashMap.put("kong_warning", "stale_common_props");
                    }
                    hashMap.putAll(map);
                    hashMap.putAll(this.f587d.m541n());
                    for (Fields fields : C0525o.f635u) {
                        if (hashMap.get(fields.fieldName()) == null) {
                            String str = (String) hashMap.get(fields.fieldName().replaceFirst("first_", ""));
                            if (str != null) {
                                this.f587d.m472D().edit().putString(fields.fieldName(), str).apply();
                                hashMap.put(fields.fieldName(), str);
                            }
                        }
                    }
                    if (!Events.PLAYER_INFO.equals(Events.fromEventName(str)) || this.f587d.m492c(hashMap)) {
                        if (C0525o.f637w.containsKey(str)) {
                            this.f587d.m531d().m830a((String) C0525o.f637w.get(str), hashMap);
                        }
                        if (str.startsWith("adjust.")) {
                            C0562j.m753a("adding adjust only event: " + str);
                            this.f587d.m531d().m833b(str.substring("adjust.".length()), hashMap);
                            return;
                        }
                        this.f587d.m527b().m854a(str, hashMap);
                        this.f587d.m530c().m901a(str, hashMap);
                        this.f587d.f659T = true;
                        C0562j.m753a("added event to collection: " + str);
                        final AutoEventListener f = this.f587d.f641B;
                        if (z && f != null) {
                            C0562j.m753a("echo event to auto event listener: " + str);
                            C0626d.m968b(new Runnable(this) {
                                /* renamed from: c */
                                final /* synthetic */ AnonymousClass14 f583c;

                                public void run() {
                                    f.onAutoEvent(str, hashMap);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    C0562j.m753a("player_info fields did not change. not firing event");
                    return;
                }
                C0562j.m759c("addEvent called before analytics services were ready. Ignoring event: " + str);
            }
        });
    }

    /* renamed from: b */
    C0582d m527b() {
        return this.f649J;
    }

    /* renamed from: c */
    C0593i m530c() {
        return this.f650K;
    }

    /* renamed from: d */
    C0578a m531d() {
        return this.f648I;
    }

    public void addEvent(String str, String str2, String str3) {
        C0562j.m753a("adding event from json: " + str);
        setCommonProperties(str3);
        addEvent(str, m478a(str2));
    }

    public void setCommonProperties(String str) {
        C0562j.m753a("updating common props from json: " + str);
        setCommonProperties(m478a(str));
    }

    public synchronized void setCommonProperties(Map<String, Object> map) {
        if (map != null) {
            if (map != f636v) {
                Object readObject;
                try {
                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    new ObjectOutputStream(byteArrayOutputStream).writeObject(map);
                    readObject = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
                } catch (Throwable e) {
                    C0562j.m759c("IOException cloning common properties");
                    C0626d.m966a(e);
                    readObject = null;
                } catch (Throwable e2) {
                    C0562j.m759c("IOException cloning common properties");
                    C0626d.m966a(e2);
                    readObject = null;
                }
                if (readObject instanceof Map) {
                    this.f661V = (Map) readObject;
                } else {
                    this.f661V = f636v;
                }
                setCommonPropertiesEvaluator(null);
            }
        }
        this.f661V = f636v;
        setCommonPropertiesEvaluator(null);
    }

    public void gameUserUpdate(String str) {
        C0562j.m753a("game user update from json: " + str);
        JSONObject a = C0561i.m740a(str, new JSONObject());
        Map hashMap = new HashMap();
        Iterator keys = a.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            hashMap.put(str2, a.optString(str2));
        }
        gameUserUpdate(hashMap);
    }

    public void gameUserUpdate(final Map<String, String> map) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0525o f589b;

            public void run() {
                this.f589b.m527b().m855a(map);
            }
        });
    }

    public void setSwrveCustomButtonListener(final C0532b c0532b) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0525o f591b;

            public void run() {
                this.f591b.m527b().m850a(c0532b);
            }
        });
    }

    public void setDeltaCustomButtonListener(final C0532b c0532b) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0525o f593b;

            public void run() {
                this.f593b.m527b().m850a(c0532b);
            }
        });
    }

    public void setDeltaCustomParamListener(final C0534c c0534c) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0525o f595b;

            public void run() {
                this.f595b.m527b().m851a(c0534c);
            }
        });
    }

    public String getAutoPropertiesJSON() {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(m541n());
        } catch (Throwable e) {
            C0562j.m760c("Exception building json string: ", e);
            jSONObject = null;
        }
        if (jSONObject != null) {
            return jSONObject.toString();
        }
        return null;
    }

    public String getInstallReferrer() {
        return InstallReceiver.getInstallReferrer(this.f666z);
    }

    public String getAutoStringProperty(String str) {
        Object a = mo1188a(Fields.fromFieldName(str));
        if (a instanceof String) {
            return (String) a;
        }
        C0562j.m759c("Common props is not a string: " + str + " : " + a);
        return null;
    }

    public long getAutoLongProperty(String str) {
        Object a = mo1188a(Fields.fromFieldName(str));
        if (a instanceof Long) {
            return ((Long) a).longValue();
        }
        C0562j.m759c("Common props is not a long: " + str + " : " + a);
        return 0;
    }

    public int getAutoIntProperty(String str) {
        Object a = mo1188a(Fields.fromFieldName(str));
        if (a instanceof Integer) {
            return ((Integer) a).intValue();
        }
        C0562j.m759c("Common props is not an int: " + str + " : " + a);
        return 0;
    }

    public double getAutoDoubleProperty(String str) {
        Object a = mo1188a(Fields.fromFieldName(str));
        if (a instanceof Double) {
            return ((Double) a).doubleValue();
        }
        C0562j.m759c("Common props is not a double: " + str + " : " + a);
        return 0.0d;
    }

    public void trackPurchase(String str) {
        trackPurchase(str, new HashMap(0));
    }

    public void trackPurchase(String str, Map<String, Object> map) {
        m482a(str, null, map, null, null);
    }

    /* renamed from: a */
    private void m482a(String str, Map<String, Object> map, Map<String, Object> map2, String str2, String str3) {
        final String str4 = str;
        final Map<String, Object> map3 = map;
        final Map<String, Object> map4 = map2;
        final String str5 = str2;
        final String str6 = str3;
        C0626d.m962a(new Runnable(this) {
            /* renamed from: f */
            final /* synthetic */ C0525o f601f;

            public void run() {
                if (!this.f601f.f665Z) {
                    C0562j.m759c("Analytics not initialized. Not tracking sale: " + str4);
                }
                SharedPreferences e = this.f601f.m472D();
                Editor edit = e.edit();
                if (this.f601f.m513y()) {
                    edit.putLong(Fields.TOTAL_SPENT_IN_USD.fieldName(), Double.doubleToLongBits(C0580c.m842b(str4) + ((Double) this.f601f.mo1188a(Fields.TOTAL_SPENT_IN_USD)).doubleValue()));
                }
                edit.putInt(Fields.NUM_PURCHASES.fieldName(), e.getInt(Fields.NUM_PURCHASES.fieldName(), 0) + 1);
                edit.apply();
                if (map3 != null || (map4 != null && map4.size() > 0)) {
                    C0562j.m756b("iapTransaction: " + str4);
                    if (map3 != null) {
                        map3.put("receipt_data", str5);
                        map3.put(C0525o.f627m, str6);
                    }
                    this.f601f.m522a("iap_transactions", str4, map3, map4, str5, str6);
                }
            }
        });
    }

    public void trackPurchase(String str, String str2) {
        trackPurchase(str, m478a(str2));
    }

    public void startPurchase(String str, String str2) {
        startPurchase(str, m478a(str2));
    }

    public void startPurchase(final String str, final Map<String, Object> map) {
        C0562j.m756b("IAP FLOW STEP: startPurchase() - " + str);
        SharedPreferences D = m472D();
        if (D.contains(f628n)) {
            C0562j.m759c("startPurchase: invoked before active transaction finished. iap_ids may notmatch up");
        }
        String uuid = UUID.randomUUID().toString();
        D.edit().putString(f628n, uuid).apply();
        final HashMap hashMap = new HashMap();
        hashMap.put(f628n, uuid);
        C0626d.m962a(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ C0525o f605d;

            public void run() {
                this.f605d.m522a("iap_attempts", str, hashMap, map, null, null);
            }
        });
    }

    public void finishPurchase(String str, String str2, String str3) {
        finishPurchase(str, str2, str3, null);
    }

    public void finishPurchase(String str, String str2, String str3, String str4) {
        finishPurchase(IabResult.valueOf(str), str2, m478a(str3), str4);
    }

    public void finishPurchase(IabResult iabResult, String str, Map<String, Object> map) {
        finishPurchase(iabResult, str, (Map) map, null);
    }

    public void finishPurchase(IabResult iabResult, String str, Map<String, Object> map, String str2) {
        JSONObject a;
        String a2;
        Map hashMap = new HashMap();
        C0562j.m759c("IAP FLOW STEP: finishPurchase(): " + iabResult);
        if (IabResult.SUCCESS.equals(iabResult) || IabResult.RECEIPT_FAIL.equals(iabResult)) {
            a = C0561i.m740a(str, new JSONObject());
            String a3 = C0561i.m735a(a, "orderId", "unable to parse orderId from responseInfo in finishPurchase()");
            hashMap.put(f625k, a3);
            a2 = ((C0512n) APIBootstrap.getInstance().mtx()).m464a(a3);
        } else {
            hashMap.put(f625k, null);
            a2 = null;
            a = null;
        }
        SharedPreferences D = m472D();
        hashMap.put(f628n, D.getString(f628n, null));
        D.edit().remove(f628n).apply();
        if (IabResult.SUCCESS.equals(iabResult)) {
            String a4 = C0561i.m736a(a, "productId", "", "unable to parse productId from responseInfo in finishPurchase()");
            if (!TextUtils.isEmpty(a2)) {
                hashMap.put(f630p, a2);
            }
            m482a(a4, hashMap, map, str, str2);
        } else if (IabResult.RECEIPT_FAIL.equals(iabResult)) {
            m524a(a2, hashMap, (Map) map);
        } else if (IabResult.FAIL.equals(iabResult)) {
            m524a(str, hashMap, (Map) map);
        } else {
            C0562j.m759c("invalid result code passed to finishPurchase: " + iabResult);
        }
    }

    public String getResourceAsString(String str, String str2, String str3) {
        if (m527b().m856b(str) == null) {
            C0562j.m753a("Delta resource not found: " + str);
        }
        return str3;
    }

    public int getResourceAsInt(String str, String str2, int i) {
        if (m527b().m856b(str) == null) {
            C0562j.m753a("Delta resource not found: " + str);
        }
        return i;
    }

    public Map<String, Object> getResources() {
        return m527b().m847a();
    }

    public Set<String> getResouceNames() {
        Map resources = getResources();
        if (resources == null) {
            return null;
        }
        return resources.keySet();
    }

    public String getResourceNamesAsJson() {
        Collection resouceNames = getResouceNames();
        return (resouceNames != null ? new JSONArray(resouceNames) : new JSONArray()).toString();
    }

    public float getResourceAsFloat(String str, String str2, float f) {
        if (m527b().m856b(str) == null) {
            C0562j.m753a("Delta resource not found: " + str);
        }
        return f;
    }

    public boolean getResourceAsBool(String str, String str2, boolean z) {
        if (m527b().m856b(str) == null) {
            C0562j.m753a("Delta resource not found: " + str);
        }
        return z;
    }

    public void finishPromoAward(String str) {
        m527b().m859c(str);
    }

    /* renamed from: a */
    void m524a(String str, Map<String, Object> map, Map<String, Object> map2) {
        C0562j.m756b("iapFail: " + str);
        Map hashMap = new HashMap(map);
        hashMap.put(f629o, str);
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        m523a("iap_fails", hashMap);
        C0562j.m759c("IAP FLOW STEP: completed: iap_fails : " + str);
    }

    /* renamed from: a */
    void m522a(String str, String str2, Map<String, Object> map, Map<String, Object> map2, String str3, String str4) {
        C0626d.m970c();
        if (m511w() || !this.f665Z) {
            C0562j.m753a("Auto analytics disabled or not initalized. Not sending event: " + str);
            return;
        }
        Map hashMap = new HashMap();
        double b = C0580c.m842b(str2);
        hashMap.put(f621g, Double.valueOf(b));
        C0596a a = this.f651L.m912a(str2);
        hashMap.put(f624j, a != null ? Double.valueOf(a.m911b()) : null);
        hashMap.put(f623i, a != null ? a.m910a() : null);
        hashMap.put(f622h, str2);
        if (map != null) {
            hashMap.putAll(map);
        }
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        m523a(str, hashMap);
        if ("iap_transactions".equals(str)) {
            m527b().m853a(str2, b, hashMap, str3, str4);
            C0562j.m759c("IAP FLOW STEP: completed: " + str);
        }
    }

    public String getAutoUTCProperty(String str) {
        Object a = mo1188a(Fields.fromFieldName(str));
        try {
            if ((a instanceof String) && ((DateFormat) f632r.get()).parse((String) a) != null) {
                return (String) a;
            }
        } catch (ParseException e) {
            C0562j.m759c("exception parsing common prop: " + a);
        }
        C0562j.m759c("Common prop is not a string: " + str + " : " + a);
        return null;
    }

    public boolean getAutoBoolProperty(String str) {
        Object a = mo1188a(Fields.fromFieldName(str));
        if (a instanceof Boolean) {
            return ((Boolean) a).booleanValue();
        }
        C0562j.m759c("Common props is not a bool: " + str + " : " + a);
        return false;
    }

    /* renamed from: a */
    private Map<String, Object> m478a(String str) {
        C0562j.m753a("parsing JSON to analytics map: " + str);
        Map hashMap = new HashMap();
        Object c = C0561i.m749c(str);
        if (c != null) {
            return (Map) m474a(c);
        }
        C0562j.m759c("event is not a valid json object: " + str);
        return hashMap;
    }

    /* renamed from: a */
    private Object m474a(Object obj) {
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(m474a(jSONArray.opt(i)));
            }
            return arrayList;
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            Map hashMap = new HashMap();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, m474a(jSONObject.opt(str)));
            }
            return hashMap;
        } else if (obj == JSONObject.NULL) {
            return null;
        } else {
            return obj;
        }
    }

    @Deprecated
    public void setFilterType(String str) {
        addFilterType(str);
    }

    public void addFilterType(final String str) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0525o f607b;

            public void run() {
                SharedPreferences sharedPreferences = APIBootstrap.getInstance().getApplicationContext().getSharedPreferences("kongregate_analytics", 0);
                String string = sharedPreferences.getString(Fields.FILTER_TYPE.fieldName(), "");
                if (C0525o.f638x.matcher(str).matches()) {
                    C0562j.m756b("adding filter type: " + str);
                    Set treeSet = new TreeSet(Arrays.asList(TextUtils.split(string, ",")));
                    treeSet.add(str);
                    sharedPreferences.edit().putString(Fields.FILTER_TYPE.fieldName(), TextUtils.join(",", treeSet.toArray())).apply();
                    return;
                }
                C0562j.m759c("filter type must be alpha_numeric. not adding: " + str);
            }
        });
    }

    /* renamed from: a */
    public void m526a(JSONObject jSONObject) {
        if (jSONObject == null) {
            C0562j.m759c("updatePanelClosesEvent - empty payload");
            return;
        }
        String optString = jSONObject.optString("event", null);
        Object opt = jSONObject.opt("value");
        String optString2 = jSONObject.optString("type", null);
        if (optString == null || optString2 == null) {
            C0562j.m759c("updatePanelClosesEvent - invalid field name (" + optString + ")  or operator (" + optString2 + ")");
        } else if (C0583e.f805d.equals(optString2)) {
            this.f657R.m862a(optString, opt);
        } else if (opt instanceof Number) {
            this.f657R.m861a(optString, (Number) opt, optString2);
        } else {
            C0562j.m759c("updatePanelClosesEvent: invalid operator (" + optString2 + ") or value (" + opt + ")");
        }
    }

    /* renamed from: e */
    public void m532e() {
        this.f657R.m864b("panel_opened_time_local", ((DateFormat) f632r.get()).format(new Date()));
    }

    /* renamed from: f */
    public void m533f() {
        Map a = this.f657R.m860a();
        if (!a.isEmpty()) {
            m523a(this.f657R.m863b(), a);
        }
    }

    /* renamed from: a */
    public void m521a(final String str, final String str2) {
        C0626d.m962a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C0525o f610c;

            public void run() {
                SharedPreferences sharedPreferences = APIBootstrap.getInstance().getApplicationContext().getSharedPreferences("kongregate_analytics", 0);
                Date a = C0561i.m737a(str);
                if (StringUtils.m587c(str2)) {
                    C0562j.m759c("external IP not available");
                    sharedPreferences.edit().remove(Fields.EXTERNAL_IP_ADDRESS.fieldName()).apply();
                } else {
                    sharedPreferences.edit().putString(Fields.EXTERNAL_IP_ADDRESS.fieldName(), str2).apply();
                }
                if (a == null) {
                    C0562j.m759c("problem parsing server time: " + str);
                    sharedPreferences.edit().remove(Fields.TIME_SKEW.fieldName()).apply();
                    return;
                }
                int time = ((int) (new Date().getTime() - a.getTime())) / 1000;
                sharedPreferences.edit().putLong(Fields.TIME_SKEW.fieldName(), (long) time).apply();
                if (Math.abs(time) > Strategy.TTL_SECONDS_DEFAULT) {
                    this.f610c.m488b("bad_timestamp", Math.abs(time) > 3900 ? Fields.TIME_SKEW.fieldName() : null);
                }
            }
        });
    }

    public synchronized void setCommonPropertiesEvaluator(CommonPropertiesEvaluator commonPropertiesEvaluator) {
        this.f640A = commonPropertiesEvaluator;
    }

    /* renamed from: g */
    public synchronized CommonPropertiesEvaluator m534g() {
        return this.f640A;
    }

    public synchronized void setAutoEventListener(AutoEventListener autoEventListener) {
        this.f641B = autoEventListener;
    }

    /* renamed from: h */
    void m535h() {
        m531d().m829a();
    }

    /* renamed from: a */
    void m517a(Activity activity) {
        m531d().m832b();
    }

    /* renamed from: i */
    void m536i() {
        C0626d.m962a(new C05206(this));
    }

    /* renamed from: j */
    void m537j() {
        C0626d.m962a(new C05217(this));
    }

    /* renamed from: k */
    void m538k() {
    }

    /* renamed from: a */
    void m518a(Activity activity, Bundle bundle, Uri uri) {
        m527b().m848a(activity);
    }

    /* renamed from: l */
    void m539l() {
        if (this.f665Z) {
            C0626d.m962a(new C05228(this));
        }
    }

    /* renamed from: b */
    void m528b(Activity activity) {
        m527b().m857b(activity);
    }

    /* renamed from: b */
    private void m488b(String str, String str2) {
        if (!m511w() && this.f665Z) {
            Map hashMap = new HashMap();
            if (str2 != null) {
                addFilterType(str2);
            }
            hashMap.put("reason", str);
            m523a("invalid_states", hashMap);
        }
    }

    /* renamed from: u */
    private void m509u() {
        C0626d.m970c();
        if (!m511w() && this.f665Z && this.f644E == 0) {
            SharedPreferences sharedPreferences = this.f666z.getSharedPreferences("kongregate_analytics", 0);
            this.f660U = this.f659T;
            long currentTimeMillis = System.currentTimeMillis();
            this.f645F = currentTimeMillis;
            this.f644E = currentTimeMillis;
            long j = sharedPreferences.getLong("background_time", 0);
            currentTimeMillis = j != 0 ? this.f644E - j : 0;
            sharedPreferences.edit().remove("background_time").apply();
            if (currentTimeMillis == 0 || currentTimeMillis > 300000) {
                if (sharedPreferences.contains(Fields.SESSION_ID.fieldName())) {
                    boolean z;
                    Object obj;
                    Map hashMap = new HashMap();
                    if (currentTimeMillis == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    hashMap.put("did_crash", Boolean.valueOf(z));
                    hashMap.put("session_length_seconds", Integer.valueOf((int) (sharedPreferences.getLong("session_length_seconds", 0) / 1000)));
                    String str = "session_end_time";
                    if (z) {
                        obj = null;
                    } else {
                        obj = ((DateFormat) f632r.get()).format(new Date(j));
                    }
                    hashMap.put(str, obj);
                    m523a("session_ends", hashMap);
                }
                sharedPreferences.edit().remove("session_length_seconds").putString(Fields.SESSION_ID.fieldName(), UUID.randomUUID().toString()).putInt(Fields.NUM_SESSIONS.fieldName(), sharedPreferences.getInt(Fields.NUM_SESSIONS.fieldName(), 0) + 1).apply();
                Map hashMap2 = new HashMap();
                hashMap2.put(Fields.IS_FROM_BACKGROUND.fieldName(), Boolean.valueOf(this.f660U));
                m523a("session_starts", hashMap2);
            }
            if (this.f652M) {
                m527b().m858b(m541n());
            }
        }
    }

    /* renamed from: v */
    private void m510v() {
        C0626d.m970c();
        if (!m511w() && this.f665Z) {
            m540m();
            SharedPreferences sharedPreferences = this.f666z.getSharedPreferences("kongregate_analytics", 0);
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis - this.f644E;
            this.f645F = 0;
            this.f644E = 0;
            sharedPreferences.edit().putLong("background_time", currentTimeMillis).apply();
            if (this.f652M) {
                m527b().m858b(m541n());
            }
        }
    }

    /* renamed from: m */
    void m540m() {
        C0626d.m970c();
        if (!m511w() && this.f665Z) {
            SharedPreferences sharedPreferences = this.f666z.getSharedPreferences("kongregate_analytics", 0);
            long currentTimeMillis = System.currentTimeMillis();
            sharedPreferences.edit().putLong("session_length_seconds", (currentTimeMillis - this.f645F) + sharedPreferences.getLong("session_length_seconds", 0)).apply();
            this.f645F = currentTimeMillis;
        }
    }

    /* renamed from: b */
    protected void m529b(Map<String, Object> map) {
        if (!m511w() && this.f665Z) {
            m523a(Events.PLAYER_INFO.eventName(), (Map) map);
        }
    }

    /* renamed from: c */
    private boolean m492c(Map<String, Object> map) {
        Object format;
        Date l = C0666a.m1145a().m1182l();
        Date k = C0666a.m1145a().m1181k();
        map.put(Fields.PUR_LINK_DATE.fieldName(), k != null ? ((DateFormat) f632r.get()).format(k) : null);
        String fieldName = Fields.KONG_JOIN_DATE.fieldName();
        if (l != null) {
            format = ((DateFormat) f632r.get()).format(l);
        } else {
            format = null;
        }
        map.put(fieldName, format);
        String string = m472D().getString("player_info", null);
        JSONObject a = C0561i.m740a(string, new JSONObject());
        boolean z = false;
        for (Fields fields : f615a) {
            if (map.containsKey(fields.fieldName())) {
                try {
                    Object obj = map.get(fields.fieldName());
                    if (!m486a(obj, a.opt(fields.fieldName()))) {
                        a.put(fields.fieldName(), map.get(fields.fieldName()));
                        z = true;
                        if (Fields.KONG_USER_ID.fieldName().equals(fields.fieldName())) {
                            m481a(string, obj);
                        }
                    }
                } catch (JSONException e) {
                    C0562j.m759c("exception setting json: " + fields.fieldName() + " : " + map.get(fields.fieldName()));
                }
            } else {
                map.put(fields.fieldName(), a.opt(fields.fieldName()));
            }
        }
        if (z) {
            C0562j.m753a("updating player_info cache " + a.toString());
            m472D().edit().putString("player_info", a.toString()).apply();
        }
        return z;
    }

    /* renamed from: a */
    private void m481a(String str, Object obj) {
        if ((!C0487b.m424a(getAutoStringProperty(Fields.FIRST_SDK_VERSION.fieldName()), "1.2.1.2") && str == null) || !m486a(Integer.valueOf(0), obj)) {
        }
    }

    /* renamed from: a */
    private boolean m486a(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            if (((Number) obj).doubleValue() != ((Number) obj2).doubleValue()) {
                return false;
            }
            return true;
        } else if (obj == null || !obj.equals(obj2)) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: w */
    private boolean m511w() {
        return KongregateAPI.DISABLE_ALL.equals(this.f646G);
    }

    /* renamed from: x */
    private boolean m512x() {
        return KongregateAPI.CLOUD_GAME.equals(this.f646G);
    }

    /* renamed from: y */
    private boolean m513y() {
        return KongregateAPI.ENABLE_ALL.equals(this.f646G);
    }

    /* renamed from: z */
    private void m514z() {
        this.f642C = C0626d.m964a(1, 1, TimeUnit.MINUTES, new C05239(this));
    }

    /* renamed from: A */
    private Map<String, Object> m469A() {
        CommonPropertiesEvaluator commonPropertiesEvaluator = this.f640A;
        if (commonPropertiesEvaluator == null) {
            return this.f661V;
        }
        Map<String, Object> commonProperties = commonPropertiesEvaluator.getCommonProperties();
        if (commonProperties == null) {
            return f636v;
        }
        return commonProperties;
    }

    /* renamed from: a */
    public Object mo1188a(Fields fields) {
        NetworkInfo networkInfo = null;
        if (fields == null) {
            C0562j.m759c("attempting to get null field");
            return networkInfo;
        }
        m470B();
        String fieldName = fields.fieldName();
        if (this.f658S.containsKey(fieldName)) {
            return this.f658S.get(fieldName);
        }
        SharedPreferences sharedPreferences = this.f666z.getSharedPreferences("kongregate_analytics", 0);
        NativeAPI nativeAPI = (NativeAPI) APIBootstrap.getInstance();
        switch (fields) {
            case SESSION_ID:
                return sharedPreferences.getString(Fields.SESSION_ID.fieldName(), networkInfo);
            case NUM_SESSIONS:
                return Integer.valueOf(sharedPreferences.getInt(Fields.NUM_SESSIONS.fieldName(), 0));
            case KONG_USER_ID:
                return Long.valueOf(nativeAPI.services().getUserId());
            case KONG_USERNAME:
                return nativeAPI.services().getUsername();
            case KONG_PLUS:
                return Boolean.valueOf(nativeAPI.services().hasKongPlus());
            case DATA_CONNECTION_TYPE:
                ConnectivityManager connectivityManager = (ConnectivityManager) this.f666z.getSystemService("connectivity");
                if (connectivityManager != null) {
                    networkInfo = connectivityManager.getActiveNetworkInfo();
                }
                if (networkInfo == null) {
                    return ParametersKeys.ORIENTATION_NONE;
                }
                fieldName = networkInfo.getTypeName();
                CharSequence subtypeName = networkInfo.getSubtypeName();
                if (!StringUtils.m587c(subtypeName)) {
                    fieldName = fieldName + " " + subtypeName;
                }
                return fieldName;
            case DEVICE_EVENT_ID:
                return UUID.randomUUID().toString();
            case EVENT_TIME:
                return ((DateFormat) f632r.get()).format(new Date());
            case EVENT_TIME_OFFSET:
                return Double.valueOf(((double) TimeZone.getDefault().getOffset(System.currentTimeMillis())) / 3600000.0d);
            case EXTERNAL_IP_ADDRESS:
                return sharedPreferences.getString(Fields.EXTERNAL_IP_ADDRESS.fieldName(), networkInfo);
            case FIRST_SERVER_VERSION:
                return sharedPreferences.getString(Fields.FIRST_SERVER_VERSION.fieldName(), networkInfo);
            case PUR_TIER:
                return C0666a.m1145a().m1180j();
            case PUSH_NOTIFICATIONS:
                return C0580c.m840a(this.f666z);
            case IP_ADDRESS:
                fieldName = C0657a.m1139c();
                if (fieldName == null) {
                    fieldName = ParametersKeys.ORIENTATION_NONE;
                }
                try {
                    MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
                    instance.update(fieldName.getBytes());
                    byte[] digest = instance.digest();
                    BigInteger bigInteger = new BigInteger(1, digest);
                    fieldName = String.format("%0" + (digest.length << 1) + "x", new Object[]{bigInteger});
                } catch (NoSuchAlgorithmException e) {
                    C0562j.m759c("MD5 algorithm not found, not hashing IP");
                }
                return fieldName;
            case TIME_SKEW:
                Integer valueOf;
                if (sharedPreferences.contains(Fields.TIME_SKEW.fieldName())) {
                    valueOf = Integer.valueOf((int) sharedPreferences.getLong(Fields.TIME_SKEW.fieldName(), 0));
                } else {
                    Object obj = networkInfo;
                }
                return valueOf;
            case FILTER_TYPE:
                return sharedPreferences.getString(Fields.FILTER_TYPE.fieldName(), networkInfo);
            case IS_FROM_BACKGROUND:
                return Boolean.valueOf(this.f660U);
            case SITE_VISITOR_ID:
                return C0640a.m1055b().m1070c();
            case TOTAL_SPENT_IN_USD:
                return Double.valueOf(Double.longBitsToDouble(sharedPreferences.getLong(Fields.TOTAL_SPENT_IN_USD.fieldName(), 0)));
            case USD_SPENT_ON_KREDS:
                return C0666a.m1145a().m1183m();
            case DAYS_RETAINED:
                try {
                    Date parse = ((DateFormat) f632r.get()).parse(sharedPreferences.getString("first_play", ""));
                    return Integer.valueOf(Math.max(C0580c.m844b(parse.getTime(), C0525o.m473a(this.f666z, parse.getTime())), 0));
                } catch (ParseException e2) {
                    C0562j.m759c("days retained unknown");
                    return networkInfo;
                }
            case NUM_PURCHASES:
                return Integer.valueOf(sharedPreferences.getInt(Fields.NUM_PURCHASES.fieldName(), 0));
            case PRIVACY_POLICY_VERSION:
                return Integer.valueOf(C0666a.m1145a().m1185o());
            case PRIVACY_POLICY_ACCEPTED_AT:
                Date p = C0666a.m1145a().m1186p();
                if (p != null) {
                    return ((DateFormat) f632r.get()).format(p);
                }
                return networkInfo;
            default:
                C0562j.m759c("unrecognized field: " + fields);
                return networkInfo;
        }
    }

    /* renamed from: a */
    public static long m473a(Context context, long j) {
        return context.getSharedPreferences("kongregate_analytics", 0).getLong("first_play_time_offset", (long) TimeZone.getDefault().getOffset(j));
    }

    /* renamed from: B */
    private void m470B() {
        boolean z = true;
        if (this.f658S == null) {
            if (C0626d.m969b()) {
                Object valueOf;
                Info C = m471C();
                this.f666z.getResources();
                Locale locale = Resources.getSystem().getConfiguration().locale;
                if (locale == null) {
                    C0562j.m759c("Configuration has no locale. Use default.");
                    locale = Locale.getDefault();
                }
                SharedPreferences sharedPreferences = this.f666z.getSharedPreferences("kongregate_analytics", 0);
                this.f658S = new HashMap();
                Map map = this.f658S;
                String fieldName = Fields.AD_TRACKING.fieldName();
                if (C != null) {
                    if (C.isLimitAdTrackingEnabled()) {
                        z = false;
                    }
                    valueOf = Boolean.valueOf(z);
                } else {
                    valueOf = null;
                }
                map.put(fieldName, valueOf);
                this.f658S.put(Fields.BUNDLE_ID.fieldName(), this.f666z.getPackageName());
                this.f658S.put(Fields.CLIENT_OS_TYPE.fieldName(), TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
                this.f658S.put(Fields.CLIENT_OS_VERSION.fieldName(), VERSION.RELEASE);
                this.f658S.put(Fields.PLATFORM.fieldName(), TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
                this.f658S.put(Fields.COUNTRY_CODE.fieldName(), locale.getCountry());
                this.f658S.put(Fields.LANG_CODE.fieldName(), locale.getLanguage());
                this.f658S.put(Fields.DEVICE_TYPE.fieldName(), Build.MODEL);
                this.f658S.put(Fields.CLIENT_VERSION.fieldName(), C0563k.m769b(this.f666z));
                this.f658S.put(Fields.DEV_CLIENT_VERSION.fieldName(), Integer.toString(C0563k.m771d(this.f666z)));
                Object id = C != null ? C.getId() : null;
                if (id != null) {
                    try {
                        MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
                        instance.update(id.getBytes());
                        byte[] digest = instance.digest();
                        BigInteger bigInteger = new BigInteger(1, digest);
                        id = String.format("%0" + (digest.length << 1) + "x", new Object[]{bigInteger});
                    } catch (NoSuchAlgorithmException e) {
                        C0562j.m759c("MD5 algorithm not found, not hashing Google Ad ID");
                    }
                }
                this.f658S.put(Fields.GOOGLE_AD_ID.fieldName(), id);
                this.f658S.put(Fields.FIRST_CLIENT_VERSION.fieldName(), sharedPreferences.getString("first_version", ""));
                this.f658S.put(Fields.FIRST_PLAY_TIME.fieldName(), C0525o.m475a(sharedPreferences, "first_play"));
                this.f658S.put(Fields.FIRST_PLAY_TIME_OFFSET.fieldName(), Double.valueOf(sharedPreferences.contains("first_play_time_offset") ? ((double) sharedPreferences.getLong("first_play_time_offset", 0)) / 3600000.0d : 0.0d));
                this.f658S.put(Fields.SDK_VERSION.fieldName(), KongregateAPI.KONGREGATE_API_VERSION);
                this.f658S.put(Fields.FIRST_SDK_VERSION.fieldName(), sharedPreferences.getString("first_sdk_version", AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN));
                this.f658S.put(Fields.PKG_SRC.fieldName(), C0563k.m770c(this.f666z));
                this.f658S.put(Fields.GOOGLE_PLAY_SERVICES_ID.fieldName(), null);
                this.f658S.put(Fields.GOOGLE_PLAY_SERVICES_ALIAS.fieldName(), null);
                this.f658S.put(Fields.IDFA.fieldName(), null);
                this.f658S.put(Fields.GAMECENTER_ID.fieldName(), null);
                this.f658S.put(Fields.GAMECENTER_ALIAS.fieldName(), null);
                if (!m512x()) {
                    this.f658S.put(Fields.PLAYER_ID.fieldName(), sharedPreferences.getString("player_id", ""));
                }
                for (String remove : this.f647H) {
                    this.f658S.remove(remove);
                }
                return;
            }
            C0562j.m759c("Automatic properties are not available until the API is READY");
        }
    }

    public String getKongPropertiesJSON() {
        return new Gson().toJson(m541n());
    }

    /* renamed from: n */
    Map<String, Object> m541n() {
        Map hashMap = new HashMap();
        m470B();
        if (!(m511w() || this.f658S == null)) {
            hashMap.putAll(this.f658S);
            m484a(hashMap, f633s);
            if (m513y()) {
                m484a(hashMap, f634t);
            }
        }
        return hashMap;
    }

    /* renamed from: C */
    private Info m471C() {
        C0626d.m970c();
        Info info = null;
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(this.f666z);
        } catch (IOException e) {
            C0562j.m759c("Unabled to access advertising Id: " + e.getMessage());
        } catch (GooglePlayServicesRepairableException e2) {
            C0562j.m759c("Unabled to access advertising Id: " + e2.getMessage());
        } catch (GooglePlayServicesNotAvailableException e3) {
            C0562j.m759c("Unabled to access advertising Id: " + e3.getMessage());
        } catch (SecurityException e4) {
            C0562j.m759c("Not permitted to get advertising Id: " + e4.getMessage());
        }
        return info;
    }

    /* renamed from: a */
    private void m484a(Map<String, Object> map, Fields[] fieldsArr) {
        for (Fields fields : fieldsArr) {
            if (!this.f647H.contains(fields.fieldName())) {
                map.put(fields.fieldName(), mo1188a(fields));
            }
        }
    }

    /* renamed from: D */
    private SharedPreferences m472D() {
        return this.f666z.getSharedPreferences("kongregate_analytics", 0);
    }

    /* renamed from: a */
    private static String m475a(SharedPreferences sharedPreferences, String str) {
        String format;
        int i = 0;
        String string = sharedPreferences.getString(str, null);
        if (StringUtils.m580a((CharSequence) string)) {
            return null;
        }
        if (string.endsWith("Z")) {
            string = string.replace("Z", "+0000");
            sharedPreferences.edit().putString(str, string).apply();
        }
        try {
            ((DateFormat) f632r.get()).parse(string);
            return string;
        } catch (ParseException e) {
            SimpleDateFormat[] simpleDateFormatArr = new SimpleDateFormat[]{new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")};
            int length = simpleDateFormatArr.length;
            while (i < length) {
                DateFormat dateFormat = simpleDateFormatArr[i];
                try {
                    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    format = ((DateFormat) f632r.get()).format(dateFormat.parse(string));
                    sharedPreferences.edit().putString(str, format).apply();
                    C0562j.m756b("Updated timestamp " + string + " in field " + str + " to UTC format: " + format);
                    return format;
                } catch (ParseException e2) {
                    i++;
                }
            }
            format = ((DateFormat) f632r.get()).format(new Date());
            sharedPreferences.edit().putString(str, format).apply();
            C0562j.m756b("Updated timestamp " + string + " in field " + str + " to UTC format: " + format);
            return format;
        }
    }
}
