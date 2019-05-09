// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import java.util.Set;
import com.google.gson.Gson;
import java.util.TreeSet;
import android.text.TextUtils;
import android.net.Uri;
import android.os.Bundle;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.util.UUID;
import org.json.JSONException;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.receivers.InstallReceiver;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.OnAttributionChangedListener;
import android.content.SharedPreferences$Editor;
import com.kongregate.o.a.c;
import android.app.Activity;
import java.util.Date;
import java.text.ParseException;
import com.kongregate.android.internal.util.StringUtils;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.SharedPreferences;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.os.Build;
import android.os.Build$VERSION;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import android.content.res.Resources;
import com.kongregate.android.internal.util.j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Collections;
import java.util.HashMap;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import com.kongregate.o.a.e;
import com.kongregate.o.a.k;
import com.kongregate.o.a.i;
import com.kongregate.o.a.d;
import com.kongregate.o.a.a;
import java.util.Collection;
import java.util.concurrent.ScheduledFuture;
import com.kongregate.android.api.CommonPropertiesEvaluator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Map;
import java.text.DateFormat;
import android.annotation.TargetApi;
import com.kongregate.o.a.b;
import com.kongregate.android.api.AnalyticsServices;

@TargetApi(9)
public class o implements AnalyticsServices, b
{
    static final Fields[] a;
    public static final String b = "utm_source";
    public static final String c = "utm_medium";
    public static final String d = "utm_term";
    public static final String e = "utm_content";
    public static final String f = "utm_campaign";
    public static final String g = "usd_cost";
    public static final String h = "product_id";
    public static final String i = "local_currency_type";
    public static final String j = "local_currency_cost";
    public static final String k = "receipt_id";
    public static final String l = "receipt_data";
    public static final String m = "receipt_signature";
    public static final String n = "iap_id";
    public static final String o = "fail_reason";
    public static final String p = "success_reason";
    public static final String q = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final ThreadLocal<DateFormat> r;
    private static final Fields[] s;
    private static final Fields[] t;
    private static final Fields[] u;
    private static final Map<String, Object> v;
    private static final Map<String, String> w;
    private static final Pattern x;
    private static final List<String> y;
    private volatile CommonPropertiesEvaluator A;
    private volatile AutoEventListener B;
    private volatile ScheduledFuture C;
    private final Object D;
    private long E;
    private long F;
    private String G;
    private volatile Collection<String> H;
    private a I;
    private final d J;
    private final i K;
    private k L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private volatile Map<String, Object> Q;
    private e R;
    private volatile Map<String, Object> S;
    private volatile boolean T;
    private volatile boolean U;
    private volatile Map<String, Object> V;
    private volatile AtomicBoolean W;
    private volatile boolean X;
    private final AtomicBoolean Y;
    private boolean Z;
    private Context z;
    
    static {
        s = new Fields[] { Fields.SESSION_ID, Fields.KONG_USER_ID, Fields.KONG_USERNAME, Fields.DATA_CONNECTION_TYPE, Fields.EVENT_TIME, Fields.EVENT_TIME_OFFSET, Fields.IP_ADDRESS, Fields.TIME_SKEW, Fields.FIRST_SERVER_VERSION, Fields.FILTER_TYPE, Fields.DEVICE_EVENT_ID, Fields.NUM_SESSIONS, Fields.EXTERNAL_IP_ADDRESS, Fields.KONG_PLUS, Fields.PUR_TIER, Fields.USD_SPENT_ON_KREDS, Fields.DAYS_RETAINED, Fields.NUM_PURCHASES, Fields.SITE_VISITOR_ID, Fields.PUSH_NOTIFICATIONS, Fields.PRIVACY_POLICY_ACCEPTED_AT, Fields.PRIVACY_POLICY_VERSION };
        t = new Fields[] { Fields.TOTAL_SPENT_IN_USD };
        u = new Fields[] { Fields.FIRST_SERVER_VERSION };
        a = new Fields[] { Fields.EMAIL, Fields.FB_USER_ID, Fields.FB_USERNAME, Fields.FB_EMAIL, Fields.KONG_USERNAME, Fields.KONG_USER_ID, Fields.KONG_JOIN_DATE, Fields.PUR_TIER, Fields.PUR_LINK_DATE, Fields.SITE_VISITOR_ID, Fields.TWITTER_ID, Fields.USD_SPENT_ON_KREDS };
        v = Collections.unmodifiableMap((Map<? extends String, ?>)new HashMap<String, Object>(0));
        (w = new HashMap<String, String>()).put("installs", "install");
        com.kongregate.android.internal.sdk.o.w.put("session_starts", "session");
        com.kongregate.android.internal.sdk.o.w.put("iap_transactions", "sale");
        x = Pattern.compile("^[a-zA-Z0-9_]+$");
        r = new ThreadLocal<DateFormat>() {
            protected DateFormat a() {
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
                simpleDateFormat.setCalendar(Calendar.getInstance());
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat;
            }
        };
        y = Arrays.asList("session_starts", "session_ends", "installs", "invalid_states", "iap_attempts", "iap_fails", "iap_transactions");
    }
    
    public o(final Context context) {
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = new Object();
        this.E = 0L;
        this.F = 0L;
        this.G = "ENABLE_ALL";
        this.H = new ArrayList<String>();
        this.R = new e("panel_closes");
        this.S = null;
        this.T = false;
        this.U = false;
        this.V = com.kongregate.android.internal.sdk.o.v;
        this.W = new AtomicBoolean(false);
        this.Y = new AtomicBoolean(false);
        this.Z = false;
        this.z = context.getApplicationContext();
        this.I = new a(this.z);
        this.J = new d(this.z);
        this.K = new i(this.z);
        this.L = new k(this.z);
    }
    
    private Map<String, Object> A() {
        final CommonPropertiesEvaluator a = this.A;
        Map<String, Object> map;
        if (a == null) {
            map = this.V;
        }
        else if ((map = a.getCommonProperties()) == null) {
            return com.kongregate.android.internal.sdk.o.v;
        }
        return map;
    }
    
    private void B() {
        boolean b = true;
        if (this.S == null) {
            if (!com.kongregate.o.c.d.b()) {
                com.kongregate.android.internal.util.j.c("Automatic properties are not available until the API is READY");
                return;
            }
            final AdvertisingIdClient$Info c = this.C();
            this.z.getResources();
            Object o;
            if ((o = Resources.getSystem().getConfiguration().locale) == null) {
                com.kongregate.android.internal.util.j.c("Configuration has no locale. Use default.");
                o = Locale.getDefault();
            }
            final SharedPreferences sharedPreferences = this.z.getSharedPreferences("kongregate_analytics", 0);
            this.S = new HashMap<String, Object>();
            final Map<String, Object> s = this.S;
            final String fieldName = Fields.AD_TRACKING.fieldName();
        Label_0429:
            while (true) {
            Label_0809_Outer:
                while (true) {
                    Label_0339: {
                        while (true) {
                            Boolean value = null;
                            Label_0125: {
                                Label_0803: {
                                    if (c == null) {
                                        break Label_0803;
                                    }
                                    if (!c.isLimitAdTrackingEnabled()) {
                                        break Label_0803;
                                    }
                                    Label_0798: {
                                        break Label_0798;
                                        while (true) {
                                            while (true) {
                                                Label_0830: {
                                                    try {
                                                        final MessageDigest instance = MessageDigest.getInstance("MD5");
                                                        instance.update(((String)o).getBytes());
                                                        final byte[] digest = instance.digest();
                                                        final Object format = String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest));
                                                        this.S.put(Fields.GOOGLE_AD_ID.fieldName(), format);
                                                        this.S.put(Fields.FIRST_CLIENT_VERSION.fieldName(), sharedPreferences.getString("first_version", ""));
                                                        this.S.put(Fields.FIRST_PLAY_TIME.fieldName(), a(sharedPreferences, "first_play"));
                                                        o = this.S;
                                                        final String fieldName2 = Fields.FIRST_PLAY_TIME_OFFSET.fieldName();
                                                        if (sharedPreferences.contains("first_play_time_offset")) {
                                                            final double n = sharedPreferences.getLong("first_play_time_offset", 0L) / 3600000.0;
                                                            ((Map<String, Double>)o).put(fieldName2, Double.valueOf(n));
                                                            this.S.put(Fields.SDK_VERSION.fieldName(), "3.0.5");
                                                            this.S.put(Fields.FIRST_SDK_VERSION.fieldName(), sharedPreferences.getString("first_sdk_version", "Unknown"));
                                                            this.S.put(Fields.PKG_SRC.fieldName(), com.kongregate.android.internal.util.k.c(this.z));
                                                            this.S.put(Fields.GOOGLE_PLAY_SERVICES_ID.fieldName(), null);
                                                            this.S.put(Fields.GOOGLE_PLAY_SERVICES_ALIAS.fieldName(), null);
                                                            this.S.put(Fields.IDFA.fieldName(), null);
                                                            this.S.put(Fields.GAMECENTER_ID.fieldName(), null);
                                                            this.S.put(Fields.GAMECENTER_ALIAS.fieldName(), null);
                                                            if (!this.x()) {
                                                                this.S.put(Fields.PLAYER_ID.fieldName(), sharedPreferences.getString("player_id", ""));
                                                            }
                                                            o = this.H.iterator();
                                                            while (((Iterator)o).hasNext()) {
                                                                this.S.remove(((Iterator<String>)o).next());
                                                            }
                                                            return;
                                                        }
                                                        break Label_0830;
                                                        b = false;
                                                        break;
                                                        value = null;
                                                        break Label_0125;
                                                        o = null;
                                                        break Label_0339;
                                                    }
                                                    catch (NoSuchAlgorithmException ex) {
                                                        com.kongregate.android.internal.util.j.c("MD5 algorithm not found, not hashing Google Ad ID");
                                                        final Object format = o;
                                                        continue Label_0429;
                                                    }
                                                }
                                                final double n = 0.0;
                                                continue Label_0809_Outer;
                                            }
                                        }
                                    }
                                }
                                value = b;
                            }
                            s.put(fieldName, value);
                            this.S.put(Fields.BUNDLE_ID.fieldName(), this.z.getPackageName());
                            this.S.put(Fields.CLIENT_OS_TYPE.fieldName(), "android");
                            this.S.put(Fields.CLIENT_OS_VERSION.fieldName(), Build$VERSION.RELEASE);
                            this.S.put(Fields.PLATFORM.fieldName(), "android");
                            this.S.put(Fields.COUNTRY_CODE.fieldName(), ((Locale)o).getCountry());
                            this.S.put(Fields.LANG_CODE.fieldName(), ((Locale)o).getLanguage());
                            this.S.put(Fields.DEVICE_TYPE.fieldName(), Build.MODEL);
                            this.S.put(Fields.CLIENT_VERSION.fieldName(), com.kongregate.android.internal.util.k.b(this.z));
                            this.S.put(Fields.DEV_CLIENT_VERSION.fieldName(), Integer.toString(com.kongregate.android.internal.util.k.d(this.z)));
                            if (c == null) {
                                continue;
                            }
                            break;
                        }
                        o = c.getId();
                    }
                    final Object format = o;
                    if (o != null) {
                        continue Label_0809_Outer;
                    }
                    break;
                }
                continue Label_0429;
            }
        }
    }
    
    private AdvertisingIdClient$Info C() {
        com.kongregate.o.c.d.c();
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.z);
        }
        catch (IOException ex) {
            com.kongregate.android.internal.util.j.c("Unabled to access advertising Id: " + ex.getMessage());
            return null;
        }
        catch (GooglePlayServicesRepairableException ex2) {
            com.kongregate.android.internal.util.j.c("Unabled to access advertising Id: " + ex2.getMessage());
            return null;
        }
        catch (GooglePlayServicesNotAvailableException ex3) {
            com.kongregate.android.internal.util.j.c("Unabled to access advertising Id: " + ex3.getMessage());
            return null;
        }
        catch (SecurityException ex4) {
            com.kongregate.android.internal.util.j.c("Not permitted to get advertising Id: " + ex4.getMessage());
            return null;
        }
    }
    
    private SharedPreferences D() {
        return this.z.getSharedPreferences("kongregate_analytics", 0);
    }
    
    public static long a(final Context context, final long n) {
        return context.getSharedPreferences("kongregate_analytics", 0).getLong("first_play_time_offset", (long)TimeZone.getDefault().getOffset(n));
    }
    
    private Object a(final Object o) {
        Object o2;
        if (o instanceof JSONArray) {
            final JSONArray jsonArray = (JSONArray)o;
            o2 = new ArrayList<Object>(jsonArray.length());
            for (int i = 0; i < jsonArray.length(); ++i) {
                ((ArrayList<Object>)o2).add(this.a(jsonArray.opt(i)));
            }
        }
        else {
            if (o instanceof JSONObject) {
                final JSONObject jsonObject = (JSONObject)o;
                final HashMap<String, Object> hashMap = new HashMap<String, Object>();
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s = keys.next();
                    hashMap.put(s, this.a(jsonObject.opt(s)));
                }
                return hashMap;
            }
            if ((o2 = o) == JSONObject.NULL) {
                return null;
            }
        }
        return o2;
    }
    
    private static String a(final SharedPreferences sharedPreferences, final String s) {
        int i = 0;
        final String string = sharedPreferences.getString(s, (String)null);
        if (StringUtils.a((CharSequence)string)) {
            return null;
        }
        String replace = string;
        if (string.endsWith("Z")) {
            replace = string.replace("Z", "+0000");
            sharedPreferences.edit().putString(s, replace).apply();
        }
        try {
            com.kongregate.android.internal.sdk.o.r.get().parse(replace);
            return replace;
        }
        catch (ParseException ex) {
            final SimpleDateFormat[] array = { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ"), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ") };
            while (i < array.length) {
                final SimpleDateFormat simpleDateFormat = array[i];
                try {
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    final String format = com.kongregate.android.internal.sdk.o.r.get().format(simpleDateFormat.parse(replace));
                    sharedPreferences.edit().putString(s, format).apply();
                    com.kongregate.android.internal.util.j.b("Updated timestamp " + replace + " in field " + s + " to UTC format: " + format);
                    return format;
                }
                catch (ParseException ex2) {
                    ++i;
                    continue;
                }
                break;
            }
            final String format2 = com.kongregate.android.internal.sdk.o.r.get().format(new Date());
            sharedPreferences.edit().putString(s, format2).apply();
            com.kongregate.android.internal.util.j.b("Updated timestamp " + replace + " in field " + s + " to UTC format: " + format2);
            return format2;
        }
    }
    
    private Map<String, Object> a(final String s) {
        com.kongregate.android.internal.util.j.a("parsing JSON to analytics map: " + s);
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final JSONObject c = com.kongregate.android.internal.util.i.c(s);
        if (c == null) {
            com.kongregate.android.internal.util.j.c("event is not a valid json object: " + s);
            return hashMap;
        }
        return (Map<String, Object>)this.a((Object)c);
    }
    
    private void a(final String s, final Object o) {
        if ((com.kongregate.android.internal.sdk.b.a(this.getAutoStringProperty(Fields.FIRST_SDK_VERSION.fieldName()), "1.2.1.2") || s != null) && this.a(0, o)) {
            return;
        }
    }
    
    private void a(final String s, final Map<String, Object> map, final Map<String, Object> map2, final String s2, final String s3) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                if (!com.kongregate.android.internal.sdk.o.this.Z) {
                    com.kongregate.android.internal.util.j.c("Analytics not initialized. Not tracking sale: " + s);
                }
                final SharedPreferences e = com.kongregate.android.internal.sdk.o.this.D();
                final SharedPreferences$Editor edit = e.edit();
                if (com.kongregate.android.internal.sdk.o.this.y()) {
                    edit.putLong(Fields.TOTAL_SPENT_IN_USD.fieldName(), Double.doubleToLongBits(com.kongregate.o.a.c.b(s) + (double)com.kongregate.android.internal.sdk.o.this.a(Fields.TOTAL_SPENT_IN_USD)));
                }
                edit.putInt(Fields.NUM_PURCHASES.fieldName(), e.getInt(Fields.NUM_PURCHASES.fieldName(), 0) + 1);
                edit.apply();
                if (map != null || (map2 != null && map2.size() > 0)) {
                    com.kongregate.android.internal.util.j.b("iapTransaction: " + s);
                    if (map != null) {
                        map.put("receipt_data", s2);
                        map.put("receipt_signature", s3);
                    }
                    com.kongregate.android.internal.sdk.o.this.a("iap_transactions", s, map, map2, s2, s3);
                }
            }
        });
    }
    
    private void a(final String s, final Map<String, Object> map, final boolean b) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                if (!com.kongregate.android.internal.sdk.o.this.Z) {
                    com.kongregate.android.internal.util.j.c("addEvent called before analytics services were ready. Ignoring event: " + s);
                }
                else {
                    com.kongregate.android.internal.util.j.a("adding event to collection: " + s);
                    if (s.startsWith("swrve.") || s.startsWith("delta.")) {
                        com.kongregate.android.internal.sdk.o.this.b().a(s, map);
                        return;
                    }
                    final HashMap<Object, String> hashMap = new HashMap<Object, String>();
                    hashMap.putAll((Map<?, ?>)com.kongregate.android.internal.sdk.o.this.V);
                    if (com.kongregate.android.internal.sdk.o.this.W.getAndSet(false)) {
                        hashMap.put("kong_warning", "stale_common_props");
                    }
                    hashMap.putAll((Map<?, ?>)map);
                    hashMap.putAll((Map<?, ?>)com.kongregate.android.internal.sdk.o.this.n());
                    final Fields[] p = com.kongregate.android.internal.sdk.o.u;
                    for (int length = p.length, i = 0; i < length; ++i) {
                        final Fields fields = p[i];
                        if (hashMap.get(fields.fieldName()) == null) {
                            final String s = hashMap.get(fields.fieldName().replaceFirst("first_", ""));
                            if (s != null) {
                                com.kongregate.android.internal.sdk.o.this.D().edit().putString(fields.fieldName(), s).apply();
                                hashMap.put(fields.fieldName(), s);
                            }
                        }
                    }
                    if (Events.PLAYER_INFO.equals(Events.fromEventName(s)) && !com.kongregate.android.internal.sdk.o.this.c(hashMap)) {
                        com.kongregate.android.internal.util.j.a("player_info fields did not change. not firing event");
                        return;
                    }
                    if (com.kongregate.android.internal.sdk.o.w.containsKey(s)) {
                        com.kongregate.android.internal.sdk.o.this.d().a(com.kongregate.android.internal.sdk.o.w.get(s), (Map<String, Object>)hashMap);
                    }
                    if (s.startsWith("adjust.")) {
                        com.kongregate.android.internal.util.j.a("adding adjust only event: " + s);
                        com.kongregate.android.internal.sdk.o.this.d().b(s.substring("adjust.".length()), (Map<String, Object>)hashMap);
                        return;
                    }
                    com.kongregate.android.internal.sdk.o.this.b().a(s, (Map<String, Object>)hashMap);
                    com.kongregate.android.internal.sdk.o.this.c().a(s, (Map<String, Object>)hashMap);
                    com.kongregate.android.internal.sdk.o.this.T = true;
                    com.kongregate.android.internal.util.j.a("added event to collection: " + s);
                    final AutoEventListener f = com.kongregate.android.internal.sdk.o.this.B;
                    if (b && f != null) {
                        com.kongregate.android.internal.util.j.a("echo event to auto event listener: " + s);
                        com.kongregate.o.c.d.b(new Runnable() {
                            @Override
                            public void run() {
                                f.onAutoEvent(s, hashMap);
                            }
                        });
                    }
                }
            }
        });
    }
    
    private void a(final Map<String, Object> map, final Fields[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final Fields fields = array[i];
            if (!this.H.contains(fields.fieldName())) {
                map.put(fields.fieldName(), this.a(fields));
            }
        }
    }
    
    private boolean a(final Object o, final Object o2) {
        if (o != o2) {
            if (o instanceof Number && o2 instanceof Number) {
                if (((Number)o).doubleValue() != ((Number)o2).doubleValue()) {
                    return false;
                }
            }
            else if (o == null || !o.equals(o2)) {
                return false;
            }
        }
        return true;
    }
    
    private void b(final String s, final String s2) {
        if (this.w() || !this.Z) {
            return;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (s2 != null) {
            this.addFilterType(s2);
        }
        hashMap.put("reason", s);
        this.a("invalid_states", (Map<String, Object>)hashMap);
    }
    
    private void c(final Activity activity) {
        if (this.Y.getAndSet(true)) {
            com.kongregate.android.internal.util.j.c("Already starting AnalyticsServices, ignoring duplicate call");
            return;
        }
        com.kongregate.android.internal.util.j.b("Starting Analytics subsystem");
        if (this.M) {
            com.kongregate.android.internal.util.j.b("Initializing Delta");
            this.a(activity, this.Q, this.getAutoStringProperty(Fields.PLAYER_ID.fieldName()));
        }
        else {
            com.kongregate.android.internal.util.j.a("Delta disabled via remote configuration, not initializing");
        }
        if (this.N) {
            this.a(this.Q);
        }
        else {
            com.kongregate.android.internal.util.j.a("Kong Analytics Service disabled via remote configuration, not initializing");
        }
        if (this.O) {
            this.I.a(this.Q, (OnAttributionChangedListener)new OnAttributionChangedListener() {
                public void onAttributionChanged(final AdjustAttribution adjustAttribution) {
                    String string;
                    if (adjustAttribution != null) {
                        string = adjustAttribution.network + "." + adjustAttribution.campaign;
                    }
                    else {
                        string = "null.null";
                    }
                    com.kongregate.android.internal.util.j.a("adjust attribution changed: " + string);
                }
            });
        }
        else {
            com.kongregate.android.internal.util.j.a("Adjust disabled via remote configuration, not initializing");
        }
        com.kongregate.android.internal.util.j.a("Analytics services initialized");
        this.Z = true;
        if (this.P && this.y()) {
            final Map<String, Object> referrerFields = InstallReceiver.getReferrerFields(this.z);
            referrerFields.put("stub_field", 0);
            this.a("installs", referrerFields);
        }
        if (com.kongregate.android.internal.util.c.g()) {
            this.b("jailbroken", "jailbroken");
        }
        if (((NativeAPI)APIBootstrap.getInstance()).I()) {
            this.addFilterType("not_kong_signed");
        }
        this.d().b();
        this.u();
        this.z();
        this.B();
    }
    
    private boolean c(final Map<String, Object> map) {
        final Date l = com.kongregate.o.j.a.a().l();
        final Date k = com.kongregate.o.j.a.a().k();
        final String fieldName = Fields.PUR_LINK_DATE.fieldName();
        String format;
        if (k != null) {
            format = com.kongregate.android.internal.sdk.o.r.get().format(k);
        }
        else {
            format = null;
        }
        map.put(fieldName, format);
        final String fieldName2 = Fields.KONG_JOIN_DATE.fieldName();
        String format2;
        if (l != null) {
            format2 = com.kongregate.android.internal.sdk.o.r.get().format(l);
        }
        else {
            format2 = null;
        }
        map.put(fieldName2, format2);
        final String string = this.D().getString("player_info", (String)null);
        final JSONObject a = com.kongregate.android.internal.util.i.a(string, new JSONObject());
        final Fields[] a2 = com.kongregate.android.internal.sdk.o.a;
        final int length = a2.length;
        int i = 0;
        boolean b = false;
        while (i < length) {
            final Fields fields = a2[i];
            boolean b2;
            if (!map.containsKey(fields.fieldName())) {
                map.put(fields.fieldName(), a.opt(fields.fieldName()));
                b2 = b;
            }
            else {
                boolean b3 = b;
                try {
                    final String value = map.get(fields.fieldName());
                    b2 = b;
                    b3 = b;
                    if (!this.a((Object)value, a.opt(fields.fieldName()))) {
                        b3 = b;
                        a.put(fields.fieldName(), (Object)map.get(fields.fieldName()));
                        final boolean b4 = true;
                        b2 = true;
                        b3 = b4;
                        if (Fields.KONG_USER_ID.fieldName().equals(fields.fieldName())) {
                            b3 = b4;
                            this.a(string, (Object)value);
                            b2 = b2;
                        }
                    }
                }
                catch (JSONException ex) {
                    com.kongregate.android.internal.util.j.c("exception setting json: " + fields.fieldName() + " : " + (Object)map.get(fields.fieldName()));
                    b2 = b3;
                }
            }
            ++i;
            b = b2;
        }
        if (b) {
            com.kongregate.android.internal.util.j.a("updating player_info cache " + a.toString());
            this.D().edit().putString("player_info", a.toString()).apply();
        }
        return b;
    }
    
    private String s() {
        final String a = com.kongregate.android.internal.sdk.b.a(this.Q, "custom_player_id", null);
        if (StringUtils.b((CharSequence)a)) {
            com.kongregate.android.internal.util.j.c("Using custom player ID: " + a);
            return a;
        }
        return UUID.randomUUID().toString();
    }
    
    private void t() {
        if (com.kongregate.o.c.d.a()) {
            this.V = this.A();
            return;
        }
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    final HashMap hashMap = new HashMap();
                    com.kongregate.o.c.d.b(new Runnable() {
                        @Override
                        public void run() {
                            hashMap.putAll(com.kongregate.android.internal.sdk.o.this.A());
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        if (countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                            com.kongregate.android.internal.sdk.o.this.V = (Map<String, Object>)hashMap;
                            return;
                        }
                    }
                    catch (InterruptedException ex) {}
                    com.kongregate.android.internal.util.j.c("Failed to retrieve common properties, using stale ones");
                    if (com.kongregate.android.internal.sdk.o.this.V != com.kongregate.android.internal.sdk.o.v) {
                        break;
                    }
                    return;
                }
                com.kongregate.android.internal.sdk.o.this.W.set(true);
            }
        });
    }
    
    private void u() {
        com.kongregate.o.c.d.c();
        if (!this.w() && this.Z && this.E == 0L) {
            final SharedPreferences sharedPreferences = this.z.getSharedPreferences("kongregate_analytics", 0);
            this.U = this.T;
            final long currentTimeMillis = System.currentTimeMillis();
            this.F = currentTimeMillis;
            this.E = currentTimeMillis;
            final long long1 = sharedPreferences.getLong("background_time", 0L);
            long n;
            if (long1 != 0L) {
                n = this.E - long1;
            }
            else {
                n = 0L;
            }
            sharedPreferences.edit().remove("background_time").apply();
            if (n == 0L || n > 300000L) {
                if (sharedPreferences.contains(Fields.SESSION_ID.fieldName())) {
                    final HashMap<String, Object> hashMap = (HashMap<String, Object>)new HashMap<String, Integer>();
                    final boolean b = n == 0L;
                    hashMap.put("did_crash", b);
                    hashMap.put("session_length_seconds", (int)(sharedPreferences.getLong("session_length_seconds", 0L) / 1000L));
                    Serializable format;
                    if (b) {
                        format = null;
                    }
                    else {
                        format = com.kongregate.android.internal.sdk.o.r.get().format(new Date(long1));
                    }
                    hashMap.put("session_end_time", format);
                    this.a("session_ends", hashMap);
                }
                sharedPreferences.edit().remove("session_length_seconds").putString(Fields.SESSION_ID.fieldName(), UUID.randomUUID().toString()).putInt(Fields.NUM_SESSIONS.fieldName(), sharedPreferences.getInt(Fields.NUM_SESSIONS.fieldName(), 0) + 1).apply();
                final HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
                hashMap2.put(Fields.IS_FROM_BACKGROUND.fieldName(), this.U);
                this.a("session_starts", hashMap2);
            }
            if (this.M) {
                this.b().b(this.n());
            }
        }
    }
    
    private void v() {
        com.kongregate.o.c.d.c();
        if (!this.w() && this.Z) {
            this.m();
            final SharedPreferences sharedPreferences = this.z.getSharedPreferences("kongregate_analytics", 0);
            final long currentTimeMillis = System.currentTimeMillis();
            final long e = this.E;
            this.F = 0L;
            this.E = 0L;
            sharedPreferences.edit().putLong("background_time", currentTimeMillis).apply();
            if (this.M) {
                this.b().b(this.n());
            }
        }
    }
    
    private boolean w() {
        return "DISABLE_ALL".equals(this.G);
    }
    
    private boolean x() {
        return "CLOUD".equals(this.G);
    }
    
    private boolean y() {
        return "ENABLE_ALL".equals(this.G);
    }
    
    private void z() {
        this.C = com.kongregate.o.c.d.a(1L, 1L, TimeUnit.MINUTES, new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.sdk.o.this.m();
            }
        });
    }
    
    @Override
    public Object a(Fields fields) {
        final NetworkInfo networkInfo = null;
        if (fields == null) {
            com.kongregate.android.internal.util.j.c("attempting to get null field");
        }
        else {
            this.B();
            final String fieldName = fields.fieldName();
            if (this.S.containsKey(fieldName)) {
                return this.S.get(fieldName);
            }
            final SharedPreferences sharedPreferences = this.z.getSharedPreferences("kongregate_analytics", 0);
            final NativeAPI nativeAPI = (NativeAPI)APIBootstrap.getInstance();
            Label_0579: {
                switch (o$10.a[fields.ordinal()]) {
                    default: {
                        com.kongregate.android.internal.util.j.c("unrecognized field: " + fields);
                        return null;
                    }
                    case 1: {
                        return sharedPreferences.getString(Fields.SESSION_ID.fieldName(), (String)null);
                    }
                    case 2: {
                        return sharedPreferences.getInt(Fields.NUM_SESSIONS.fieldName(), 0);
                    }
                    case 3: {
                        return nativeAPI.services().getUserId();
                    }
                    case 4: {
                        return nativeAPI.services().getUsername();
                    }
                    case 5: {
                        return nativeAPI.services().hasKongPlus();
                    }
                    case 6: {
                        final ConnectivityManager connectivityManager = (ConnectivityManager)this.z.getSystemService("connectivity");
                        NetworkInfo activeNetworkInfo = networkInfo;
                        if (connectivityManager != null) {
                            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        }
                        if (activeNetworkInfo != null) {
                            final String typeName = activeNetworkInfo.getTypeName();
                            final String subtypeName = activeNetworkInfo.getSubtypeName();
                            String string = typeName;
                            if (!StringUtils.c((CharSequence)subtypeName)) {
                                string = typeName + " " + subtypeName;
                            }
                            return string;
                        }
                        return "none";
                    }
                    case 7: {
                        return UUID.randomUUID().toString();
                    }
                    case 8: {
                        return com.kongregate.android.internal.sdk.o.r.get().format(new Date());
                    }
                    case 9: {
                        return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 3600000.0;
                    }
                    case 10: {
                        return sharedPreferences.getString(Fields.EXTERNAL_IP_ADDRESS.fieldName(), (String)null);
                    }
                    case 11: {
                        return sharedPreferences.getString(Fields.FIRST_SERVER_VERSION.fieldName(), (String)null);
                    }
                    case 12: {
                        return com.kongregate.o.j.a.a().j();
                    }
                    case 13: {
                        return com.kongregate.o.a.c.a(this.z);
                    }
                    case 14: {
                        fields = (Fields)com.kongregate.o.i.a.c();
                        while (true) {
                            if (fields != null) {
                                try {
                                    while (true) {
                                        final MessageDigest instance = MessageDigest.getInstance("MD5");
                                        instance.update(((String)fields).getBytes());
                                        final byte[] digest = instance.digest();
                                        fields = (Fields)String.format("%0" + (digest.length << 1) + "x", new BigInteger(1, digest));
                                        return fields;
                                        fields = (Fields)"none";
                                        continue;
                                    }
                                }
                                catch (NoSuchAlgorithmException ex) {
                                    com.kongregate.android.internal.util.j.c("MD5 algorithm not found, not hashing IP");
                                    return fields;
                                }
                                break Label_0579;
                            }
                            continue;
                        }
                    }
                    case 15: {
                        Integer value;
                        if (sharedPreferences.contains(Fields.TIME_SKEW.fieldName())) {
                            value = (int)sharedPreferences.getLong(Fields.TIME_SKEW.fieldName(), 0L);
                        }
                        else {
                            value = null;
                        }
                        return value;
                    }
                    case 16: {
                        return sharedPreferences.getString(Fields.FILTER_TYPE.fieldName(), (String)null);
                    }
                    case 17: {
                        return this.U;
                    }
                    case 18: {
                        return com.kongregate.o.g.a.b().c();
                    }
                    case 19: {
                        return Double.longBitsToDouble(sharedPreferences.getLong(Fields.TOTAL_SPENT_IN_USD.fieldName(), 0L));
                    }
                    case 20: {
                        return com.kongregate.o.j.a.a().m();
                    }
                    case 21: {
                        try {
                            final Date parse = com.kongregate.android.internal.sdk.o.r.get().parse(sharedPreferences.getString("first_play", ""));
                            return Math.max(com.kongregate.o.a.c.b(parse.getTime(), a(this.z, parse.getTime())), 0);
                        }
                        catch (ParseException ex2) {
                            com.kongregate.android.internal.util.j.c("days retained unknown");
                            return null;
                        }
                    }
                    case 22: {
                        return sharedPreferences.getInt(Fields.NUM_PURCHASES.fieldName(), 0);
                    }
                    case 23: {
                        return com.kongregate.o.j.a.a().o();
                    }
                    case 24: {
                        final Date p = com.kongregate.o.j.a.a().p();
                        if (p != null) {
                            return com.kongregate.android.internal.sdk.o.r.get().format(p);
                        }
                        break;
                    }
                }
            }
        }
        return null;
    }
    
    public Map<String, Object> a() {
        this.t();
        final HashMap<Object, Object> hashMap = (HashMap<Object, Object>)new HashMap<String, Object>();
        hashMap.putAll(this.V);
        hashMap.putAll(this.n());
        return (Map<String, Object>)hashMap;
    }
    
    void a(final Activity activity) {
        this.d().b();
    }
    
    void a(final Activity activity, final Bundle bundle, final Uri uri) {
        this.b().a(activity);
    }
    
    public void a(final Activity activity, final Map<String, Object> q) {
        boolean p2 = true;
        this.Q = q;
        this.X = Boolean.TRUE.equals(this.Q.get("defer_analytics"));
        this.M = com.kongregate.android.internal.sdk.b.a(this.Q, "_delta_enabled_internal", true);
        this.N = com.kongregate.android.internal.sdk.b.a(this.Q, "_kong_analytics_enabled_internal", true);
        this.O = com.kongregate.android.internal.sdk.b.a(this.Q, "_adjust_enabled_internal", true);
        this.G = com.kongregate.android.internal.sdk.b.a(this.Q, "auto_analytics_mode", "ENABLE_ALL");
        if (this.w()) {
            com.kongregate.android.internal.util.j.b("Automatic analytics disabled");
        }
        else if (this.x()) {
            com.kongregate.android.internal.util.j.b("Game has a server. Will only track subset of automatic analytics");
        }
        else {
            com.kongregate.android.internal.util.j.b("Automatic analytics enabled");
            this.G = "ENABLE_ALL";
        }
        this.H = Arrays.asList(com.kongregate.android.internal.sdk.b.a(this.Q, "auto_analytics_filter", "").split("\\s*,\\s*"));
        com.kongregate.android.internal.util.j.b("filtering props: " + StringUtils.a(this.H.iterator(), ","));
        final SharedPreferences sharedPreferences = this.z.getSharedPreferences("kongregate_analytics", 0);
        if (sharedPreferences.getString("first_play", (String)null) != null) {
            p2 = false;
        }
        this.P = p2;
        if (this.P && !this.w()) {
            final SharedPreferences$Editor edit = sharedPreferences.edit();
            edit.putString("first_play", com.kongregate.android.internal.sdk.o.r.get().format(new Date()));
            edit.putLong("first_play_time_offset", (long)TimeZone.getDefault().getOffset(System.currentTimeMillis()));
            edit.putString("first_version", com.kongregate.android.internal.util.k.b(this.z));
            if (!this.x()) {
                edit.putString("player_id", this.s());
            }
            edit.putString("first_sdk_version", "3.0.5");
            edit.apply();
        }
        if (!sharedPreferences.contains("first_sdk_version")) {
            com.kongregate.android.internal.util.j.a("Upgrade from pre 1.1.0.0");
            sharedPreferences.edit().putString("first_sdk_version", "Unknown").putLong("first_play_time_offset", (long)TimeZone.getDefault().getOffset(System.currentTimeMillis())).apply();
        }
        this.B();
        com.kongregate.android.internal.util.j.b("AnalyticsServices initialized, deferred: " + this.X);
        if (!this.X) {
            this.start(activity);
        }
    }
    
    void a(final Activity activity, final Map<String, Object> map, final String s) {
        this.b().a(activity, activity.getApplication(), map, s);
    }
    
    public void a(final String s, final String s2) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                final SharedPreferences sharedPreferences = APIBootstrap.getInstance().getApplicationContext().getSharedPreferences("kongregate_analytics", 0);
                final Date a = com.kongregate.android.internal.util.i.a(s);
                if (StringUtils.c((CharSequence)s2)) {
                    com.kongregate.android.internal.util.j.c("external IP not available");
                    sharedPreferences.edit().remove(Fields.EXTERNAL_IP_ADDRESS.fieldName()).apply();
                }
                else {
                    sharedPreferences.edit().putString(Fields.EXTERNAL_IP_ADDRESS.fieldName(), s2).apply();
                }
                if (a == null) {
                    com.kongregate.android.internal.util.j.c("problem parsing server time: " + s);
                    sharedPreferences.edit().remove(Fields.TIME_SKEW.fieldName()).apply();
                }
                else {
                    final int n = (int)(new Date().getTime() - a.getTime()) / 1000;
                    sharedPreferences.edit().putLong(Fields.TIME_SKEW.fieldName(), (long)n).apply();
                    if (Math.abs(n) > 300) {
                        final o c = com.kongregate.android.internal.sdk.o.this;
                        String fieldName;
                        if (Math.abs(n) > 3900) {
                            fieldName = Fields.TIME_SKEW.fieldName();
                        }
                        else {
                            fieldName = null;
                        }
                        c.b("bad_timestamp", fieldName);
                    }
                }
            }
        });
    }
    
    void a(final String s, final String s2, final Map<String, Object> map, final Map<String, Object> map2, final String s3, final String s4) {
        com.kongregate.o.c.d.c();
        if (this.w() || !this.Z) {
            com.kongregate.android.internal.util.j.a("Auto analytics disabled or not initalized. Not sending event: " + s);
        }
        else {
            final HashMap<Object, Object> hashMap = (HashMap<Object, Object>)new HashMap<String, Double>();
            final double b = com.kongregate.o.a.c.b(s2);
            hashMap.put("usd_cost", b);
            final k.a a = this.L.a(s2);
            Double value;
            if (a != null) {
                value = a.b();
            }
            else {
                value = null;
            }
            hashMap.put("local_currency_cost", value);
            Serializable a2;
            if (a != null) {
                a2 = a.a();
            }
            else {
                a2 = null;
            }
            hashMap.put("local_currency_type", a2);
            hashMap.put("product_id", s2);
            if (map != null) {
                hashMap.putAll(map);
            }
            if (map2 != null) {
                hashMap.putAll(map2);
            }
            this.a(s, (Map<String, Object>)hashMap);
            if ("iap_transactions".equals(s)) {
                this.b().a(s2, b, (Map<String, Object>)hashMap, s3, s4);
                com.kongregate.android.internal.util.j.c("IAP FLOW STEP: completed: " + s);
            }
        }
    }
    
    void a(final String s, final Map<String, Object> map) {
        if (this.H.contains(s)) {
            com.kongregate.android.internal.util.j.b("suppressing filtered auto event: " + s);
            return;
        }
        this.t();
        this.a(s, map, true);
    }
    
    void a(final String s, final Map<String, Object> map, final Map<String, Object> map2) {
        com.kongregate.android.internal.util.j.b("iapFail: " + s);
        final HashMap<String, String> hashMap = new HashMap<String, String>((Map<? extends String, ? extends String>)map);
        hashMap.put("fail_reason", s);
        if (map2 != null) {
            hashMap.putAll((Map<?, ?>)map2);
        }
        this.a("iap_fails", (Map<String, Object>)hashMap);
        com.kongregate.android.internal.util.j.c("IAP FLOW STEP: completed: iap_fails : " + s);
    }
    
    void a(final Map<String, Object> map) {
        this.c().a(map);
    }
    
    public void a(final JSONObject jsonObject) {
        if (jsonObject == null) {
            com.kongregate.android.internal.util.j.c("updatePanelClosesEvent - empty payload");
            return;
        }
        final String optString = jsonObject.optString("event", (String)null);
        final Object opt = jsonObject.opt("value");
        final String optString2 = jsonObject.optString("type", (String)null);
        if (optString == null || optString2 == null) {
            com.kongregate.android.internal.util.j.c("updatePanelClosesEvent - invalid field name (" + optString + ")  or operator (" + optString2 + ")");
            return;
        }
        if ("replace".equals(optString2)) {
            this.R.a(optString, opt);
            return;
        }
        if (opt instanceof Number) {
            this.R.a(optString, (Number)opt, optString2);
            return;
        }
        com.kongregate.android.internal.util.j.c("updatePanelClosesEvent: invalid operator (" + optString2 + ") or value (" + opt + ")");
    }
    
    @Override
    public void addEvent(final String s, final String s2, final String commonProperties) {
        com.kongregate.android.internal.util.j.a("adding event from json: " + s);
        this.setCommonProperties(commonProperties);
        this.addEvent(s, this.a(s2));
    }
    
    @Override
    public void addEvent(final String s, Map<String, Object> hashMap) {
        if (!this.w() && !this.H.contains(s) && com.kongregate.android.internal.sdk.o.y.contains(s)) {
            com.kongregate.android.internal.util.j.c("ANALYTICS WARNING: Ignoring addEvent for " + s + " since this is a kong-automatic event");
            return;
        }
        this.t();
        if (hashMap == null) {
            hashMap = new HashMap<String, Object>(0);
        }
        this.a(s, hashMap, false);
    }
    
    @Override
    public void addFilterType(final String s) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                final SharedPreferences sharedPreferences = APIBootstrap.getInstance().getApplicationContext().getSharedPreferences("kongregate_analytics", 0);
                final String string = sharedPreferences.getString(Fields.FILTER_TYPE.fieldName(), "");
                if (!com.kongregate.android.internal.sdk.o.x.matcher(s).matches()) {
                    com.kongregate.android.internal.util.j.c("filter type must be alpha_numeric. not adding: " + s);
                    return;
                }
                com.kongregate.android.internal.util.j.b("adding filter type: " + s);
                final TreeSet set = new TreeSet<String>(Arrays.asList(TextUtils.split(string, ",")));
                set.add(s);
                sharedPreferences.edit().putString(Fields.FILTER_TYPE.fieldName(), TextUtils.join((CharSequence)",", set.toArray())).apply();
            }
        });
    }
    
    d b() {
        return this.J;
    }
    
    void b(final Activity activity) {
        this.b().b(activity);
    }
    
    protected void b(final Map<String, Object> map) {
        if (this.w() || !this.Z) {
            return;
        }
        this.a(Events.PLAYER_INFO.eventName(), map);
    }
    
    i c() {
        return this.K;
    }
    
    a d() {
        return this.I;
    }
    
    public void e() {
        this.R.b("panel_opened_time_local", com.kongregate.android.internal.sdk.o.r.get().format(new Date()));
    }
    
    public void f() {
        final Map<String, Object> a = this.R.a();
        if (!a.isEmpty()) {
            this.a(this.R.b(), a);
        }
    }
    
    @Override
    public void finishPromoAward(final String s) {
        this.b().c(s);
    }
    
    @Override
    public void finishPurchase(final IabResult iabResult, final String s, final Map<String, Object> map) {
        this.finishPurchase(iabResult, s, map, null);
    }
    
    @Override
    public void finishPurchase(final IabResult iabResult, final String s, final Map<String, Object> map, final String s2) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        com.kongregate.android.internal.util.j.c("IAP FLOW STEP: finishPurchase(): " + iabResult);
        JSONObject a;
        String a3;
        if (IabResult.SUCCESS.equals(iabResult) || IabResult.RECEIPT_FAIL.equals(iabResult)) {
            a = com.kongregate.android.internal.util.i.a(s, new JSONObject());
            final String a2 = com.kongregate.android.internal.util.i.a(a, "orderId", "unable to parse orderId from responseInfo in finishPurchase()");
            hashMap.put("receipt_id", a2);
            a3 = ((n)APIBootstrap.getInstance().mtx()).a(a2);
        }
        else {
            hashMap.put("receipt_id", null);
            a3 = null;
            a = null;
        }
        final SharedPreferences d = this.D();
        hashMap.put("iap_id", d.getString("iap_id", (String)null));
        d.edit().remove("iap_id").apply();
        if (IabResult.SUCCESS.equals(iabResult)) {
            final String a4 = com.kongregate.android.internal.util.i.a(a, "productId", "", "unable to parse productId from responseInfo in finishPurchase()");
            if (!TextUtils.isEmpty((CharSequence)a3)) {
                hashMap.put("success_reason", a3);
            }
            this.a(a4, (Map<String, Object>)hashMap, map, s, s2);
            return;
        }
        if (IabResult.RECEIPT_FAIL.equals(iabResult)) {
            this.a(a3, (Map<String, Object>)hashMap, map);
            return;
        }
        if (IabResult.FAIL.equals(iabResult)) {
            this.a(s, (Map<String, Object>)hashMap, map);
            return;
        }
        com.kongregate.android.internal.util.j.c("invalid result code passed to finishPurchase: " + iabResult);
    }
    
    @Override
    public void finishPurchase(final String s, final String s2, final String s3) {
        this.finishPurchase(s, s2, s3, null);
    }
    
    @Override
    public void finishPurchase(final String s, final String s2, final String s3, final String s4) {
        this.finishPurchase(IabResult.valueOf(s), s2, this.a(s3), s4);
    }
    
    public CommonPropertiesEvaluator g() {
        synchronized (this) {
            return this.A;
        }
    }
    
    @Override
    public void gameUserUpdate(final String s) {
        com.kongregate.android.internal.util.j.a("game user update from json: " + s);
        final JSONObject a = com.kongregate.android.internal.util.i.a(s, new JSONObject());
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final Iterator keys = a.keys();
        while (keys.hasNext()) {
            final String s2 = keys.next();
            hashMap.put(s2, a.optString(s2));
        }
        this.gameUserUpdate(hashMap);
    }
    
    @Override
    public void gameUserUpdate(final Map<String, String> map) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.sdk.o.this.b().a(map);
            }
        });
    }
    
    @Override
    public boolean getAutoBoolProperty(final String s) {
        final Object a = this.a(Fields.fromFieldName(s));
        if (a instanceof Boolean) {
            return (boolean)a;
        }
        com.kongregate.android.internal.util.j.c("Common props is not a bool: " + s + " : " + a);
        return false;
    }
    
    @Override
    public double getAutoDoubleProperty(final String s) {
        final Object a = this.a(Fields.fromFieldName(s));
        if (a instanceof Double) {
            return (double)a;
        }
        com.kongregate.android.internal.util.j.c("Common props is not a double: " + s + " : " + a);
        return 0.0;
    }
    
    @Override
    public int getAutoIntProperty(final String s) {
        final Object a = this.a(Fields.fromFieldName(s));
        if (a instanceof Integer) {
            return (int)a;
        }
        com.kongregate.android.internal.util.j.c("Common props is not an int: " + s + " : " + a);
        return 0;
    }
    
    @Override
    public long getAutoLongProperty(final String s) {
        final Object a = this.a(Fields.fromFieldName(s));
        if (a instanceof Long) {
            return (long)a;
        }
        com.kongregate.android.internal.util.j.c("Common props is not a long: " + s + " : " + a);
        return 0L;
    }
    
    @Override
    public String getAutoPropertiesJSON() {
        String string = null;
        final Map<String, Object> n = this.n();
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject((Map)n);
                if (jsonObject != null) {
                    string = jsonObject.toString();
                }
                return string;
            }
            catch (NullPointerException ex) {
                com.kongregate.android.internal.util.j.c("Exception building json string: ", ex);
                final JSONObject jsonObject = null;
                continue;
            }
            break;
        }
    }
    
    @Override
    public String getAutoStringProperty(final String s) {
        final Object a = this.a(Fields.fromFieldName(s));
        if (a instanceof String) {
            return (String)a;
        }
        com.kongregate.android.internal.util.j.c("Common props is not a string: " + s + " : " + a);
        return null;
    }
    
    @Override
    public String getAutoUTCProperty(final String s) {
        final Object a = this.a(Fields.fromFieldName(s));
        try {
            if (a instanceof String && com.kongregate.android.internal.sdk.o.r.get().parse((String)a) != null) {
                return (String)a;
            }
        }
        catch (ParseException ex) {
            com.kongregate.android.internal.util.j.c("exception parsing common prop: " + a);
        }
        com.kongregate.android.internal.util.j.c("Common prop is not a string: " + s + " : " + a);
        return null;
    }
    
    @Override
    public String getInstallReferrer() {
        return InstallReceiver.getInstallReferrer(this.z);
    }
    
    @Override
    public String getKongPropertiesJSON() {
        return new Gson().toJson((Object)this.n());
    }
    
    @Override
    public Set<String> getResouceNames() {
        final Map<String, Object> resources = this.getResources();
        if (resources == null) {
            return null;
        }
        return resources.keySet();
    }
    
    @Override
    public boolean getResourceAsBool(final String s, final String s2, final boolean b) {
        if (this.b().b(s) == null) {
            com.kongregate.android.internal.util.j.a("Delta resource not found: " + s);
        }
        return b;
    }
    
    @Override
    public float getResourceAsFloat(final String s, final String s2, final float n) {
        if (this.b().b(s) == null) {
            com.kongregate.android.internal.util.j.a("Delta resource not found: " + s);
        }
        return n;
    }
    
    @Override
    public int getResourceAsInt(final String s, final String s2, final int n) {
        if (this.b().b(s) == null) {
            com.kongregate.android.internal.util.j.a("Delta resource not found: " + s);
        }
        return n;
    }
    
    @Override
    public String getResourceAsString(final String s, final String s2, final String s3) {
        if (this.b().b(s) == null) {
            com.kongregate.android.internal.util.j.a("Delta resource not found: " + s);
        }
        return s3;
    }
    
    @Override
    public String getResourceNamesAsJson() {
        final Set<String> resouceNames = this.getResouceNames();
        JSONArray jsonArray;
        if (resouceNames != null) {
            jsonArray = new JSONArray((Collection)resouceNames);
        }
        else {
            jsonArray = new JSONArray();
        }
        return jsonArray.toString();
    }
    
    @Override
    public Map<String, Object> getResources() {
        return this.b().a();
    }
    
    void h() {
        this.d().a();
    }
    
    void i() {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                if (!com.kongregate.android.internal.sdk.o.this.Z) {
                    return;
                }
                com.kongregate.android.internal.sdk.o.this.v();
                synchronized (com.kongregate.android.internal.sdk.o.this.D) {
                    com.kongregate.android.internal.sdk.o.this.C.cancel(false);
                    // monitorexit(o.i(this.a))
                    com.kongregate.android.internal.sdk.o.this.K.a();
                }
            }
        });
    }
    
    void j() {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                if (!com.kongregate.android.internal.sdk.o.this.Z) {
                    return;
                }
                com.kongregate.android.internal.sdk.o.this.u();
                synchronized (com.kongregate.android.internal.sdk.o.this.D) {
                    if (com.kongregate.android.internal.sdk.o.this.C.isCancelled()) {
                        com.kongregate.android.internal.sdk.o.this.z();
                    }
                    // monitorexit(o.i(this.a))
                    com.kongregate.android.internal.sdk.o.this.K.b();
                }
            }
        });
    }
    
    void k() {
    }
    
    void l() {
        if (this.Z) {
            com.kongregate.o.c.d.a(new Runnable() {
                @Override
                public void run() {
                    com.kongregate.android.internal.sdk.o.this.d().c();
                }
            });
        }
    }
    
    void m() {
        com.kongregate.o.c.d.c();
        if (this.w() || !this.Z) {
            return;
        }
        final SharedPreferences sharedPreferences = this.z.getSharedPreferences("kongregate_analytics", 0);
        final long currentTimeMillis = System.currentTimeMillis();
        sharedPreferences.edit().putLong("session_length_seconds", currentTimeMillis - this.F + sharedPreferences.getLong("session_length_seconds", 0L)).apply();
        this.F = currentTimeMillis;
    }
    
    Map<String, Object> n() {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        this.B();
        if (!this.w() && this.S != null) {
            hashMap.putAll((Map<?, ?>)this.S);
            this.a(hashMap, com.kongregate.android.internal.sdk.o.s);
            if (this.y()) {
                this.a(hashMap, com.kongregate.android.internal.sdk.o.t);
                return hashMap;
            }
        }
        return hashMap;
    }
    
    @Override
    public void setAutoEventListener(final AutoEventListener b) {
        synchronized (this) {
            this.B = b;
        }
    }
    
    @Override
    public void setCommonProperties(final String s) {
        com.kongregate.android.internal.util.j.a("updating common props from json: " + s);
        this.setCommonProperties(this.a(s));
    }
    
    @Override
    public void setCommonProperties(final Map<String, Object> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_1        
        //     3: ifnull          13
        //     6: aload_1        
        //     7: getstatic       com/kongregate/android/internal/sdk/o.v:Ljava/util/Map;
        //    10: if_acmpne       28
        //    13: aload_0        
        //    14: getstatic       com/kongregate/android/internal/sdk/o.v:Ljava/util/Map;
        //    17: putfield        com/kongregate/android/internal/sdk/o.V:Ljava/util/Map;
        //    20: aload_0        
        //    21: aconst_null    
        //    22: invokevirtual   com/kongregate/android/internal/sdk/o.setCommonPropertiesEvaluator:(Lcom/kongregate/android/api/CommonPropertiesEvaluator;)V
        //    25: aload_0        
        //    26: monitorexit    
        //    27: return         
        //    28: new             Ljava/io/ByteArrayOutputStream;
        //    31: dup            
        //    32: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    35: astore_2       
        //    36: new             Ljava/io/ObjectOutputStream;
        //    39: dup            
        //    40: aload_2        
        //    41: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    44: aload_1        
        //    45: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    48: new             Ljava/io/ObjectInputStream;
        //    51: dup            
        //    52: new             Ljava/io/ByteArrayInputStream;
        //    55: dup            
        //    56: aload_2        
        //    57: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    60: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //    63: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    66: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
        //    69: astore_1       
        //    70: aload_1        
        //    71: instanceof      Ljava/util/Map;
        //    74: ifeq            125
        //    77: aload_0        
        //    78: aload_1        
        //    79: checkcast       Ljava/util/Map;
        //    82: putfield        com/kongregate/android/internal/sdk/o.V:Ljava/util/Map;
        //    85: goto            20
        //    88: astore_1       
        //    89: aload_0        
        //    90: monitorexit    
        //    91: aload_1        
        //    92: athrow         
        //    93: astore_1       
        //    94: ldc_w           "IOException cloning common properties"
        //    97: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   100: aload_1        
        //   101: invokestatic    com/kongregate/o/c/d.a:(Ljava/lang/Throwable;)V
        //   104: aconst_null    
        //   105: astore_1       
        //   106: goto            70
        //   109: astore_1       
        //   110: ldc_w           "IOException cloning common properties"
        //   113: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   116: aload_1        
        //   117: invokestatic    com/kongregate/o/c/d.a:(Ljava/lang/Throwable;)V
        //   120: aconst_null    
        //   121: astore_1       
        //   122: goto            70
        //   125: aload_0        
        //   126: getstatic       com/kongregate/android/internal/sdk/o.v:Ljava/util/Map;
        //   129: putfield        com/kongregate/android/internal/sdk/o.V:Ljava/util/Map;
        //   132: goto            20
        //    Signature:
        //  (Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      13     88     93     Any
        //  13     20     88     93     Any
        //  20     25     88     93     Any
        //  28     70     93     109    Ljava/io/IOException;
        //  28     70     109    125    Ljava/lang/ClassNotFoundException;
        //  28     70     88     93     Any
        //  70     85     88     93     Any
        //  94     104    88     93     Any
        //  110    120    88     93     Any
        //  125    132    88     93     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    @Override
    public void setCommonPropertiesEvaluator(final CommonPropertiesEvaluator a) {
        synchronized (this) {
            this.A = a;
        }
    }
    
    @Override
    public void setDeltaCustomButtonListener(final d.b b) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.sdk.o.this.b().a(b);
            }
        });
    }
    
    @Override
    public void setDeltaCustomParamListener(final d.c c) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.sdk.o.this.b().a(c);
            }
        });
    }
    
    @Deprecated
    @Override
    public void setFilterType(final String s) {
        this.addFilterType(s);
    }
    
    @Override
    public void setSwrveCustomButtonListener(final d.b b) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.sdk.o.this.b().a(b);
            }
        });
    }
    
    @Override
    public void start(final Activity activity) {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.sdk.o.this.c(activity);
            }
        });
    }
    
    @Override
    public void startPurchase(final String s, final String s2) {
        this.startPurchase(s, this.a(s2));
    }
    
    @Override
    public void startPurchase(final String s, final Map<String, Object> map) {
        com.kongregate.android.internal.util.j.b("IAP FLOW STEP: startPurchase() - " + s);
        final SharedPreferences d = this.D();
        if (d.contains("iap_id")) {
            com.kongregate.android.internal.util.j.c("startPurchase: invoked before active transaction finished. iap_ids may notmatch up");
        }
        final String string = UUID.randomUUID().toString();
        d.edit().putString("iap_id", string).apply();
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("iap_id", string);
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.sdk.o.this.a("iap_attempts", s, hashMap, map, null, null);
            }
        });
    }
    
    @Override
    public void trackPurchase(final String s) {
        this.trackPurchase(s, new HashMap<String, Object>(0));
    }
    
    @Override
    public void trackPurchase(final String s, final String s2) {
        this.trackPurchase(s, this.a(s2));
    }
    
    @Override
    public void trackPurchase(final String s, final Map<String, Object> map) {
        this.a(s, null, map, null, null);
    }
}
