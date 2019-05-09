package com.kongregate.p000o.p003a;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.AnalyticsServices.Fields;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.internal.sdk.C0487b;
import com.kongregate.android.internal.sdk.C0525o;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p006c.C0626d;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.a.a */
public class C0578a {
    /* renamed from: a */
    public static final String f788a = "sale";
    /* renamed from: b */
    public static final String f789b = "session";
    /* renamed from: c */
    public static final String f790c = "install";
    /* renamed from: d */
    static final Set<String> f791d = new HashSet();
    /* renamed from: e */
    static List<String> f792e = Arrays.asList(new String[]{f788a});
    /* renamed from: f */
    private Context f793f;
    /* renamed from: g */
    private volatile Map<String, String> f794g;
    /* renamed from: h */
    private C0577a f795h = new C0577a(this);

    /* renamed from: com.kongregate.o.a.a$a */
    protected class C0577a {
        /* renamed from: a */
        final /* synthetic */ C0578a f787a;

        /* renamed from: com.kongregate.o.a.a$a$3 */
        class C05743 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C0577a f783a;

            C05743(C0577a c0577a) {
                this.f783a = c0577a;
            }

            public void run() {
                Adjust.onPause();
            }
        }

        /* renamed from: com.kongregate.o.a.a$a$4 */
        class C05754 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C0577a f784a;

            C05754(C0577a c0577a) {
                this.f784a = c0577a;
            }

            public void run() {
                Adjust.onResume();
            }
        }

        protected C0577a(C0578a c0578a) {
            this.f787a = c0578a;
        }

        /* renamed from: a */
        protected void m824a(final AdjustEvent adjustEvent) {
            C0626d.m971c(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C0577a f780b;

                public void run() {
                    Adjust.trackEvent(adjustEvent);
                }
            });
        }

        /* renamed from: a */
        protected void m823a(final AdjustConfig adjustConfig) {
            C0626d.m971c(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C0577a f782b;

                public void run() {
                    Adjust.onCreate(adjustConfig);
                }
            });
        }

        /* renamed from: a */
        protected void m821a() {
            C0626d.m971c(new C05743(this));
        }

        /* renamed from: b */
        protected void m825b() {
            C0626d.m971c(new C05754(this));
        }

        /* renamed from: a */
        protected void m822a(final Uri uri) {
            C0626d.m971c(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C0577a f786b;

                public void run() {
                    Adjust.appWillOpenUrl(uri);
                }
            });
        }
    }

    static {
        f791d.addAll(Arrays.asList(new String[]{Fields.DEVICE_EVENT_ID.fieldName(), Fields.PLAYER_ID.fieldName(), Fields.SITE_VISITOR_ID.fieldName(), Fields.TOTAL_SPENT_IN_USD.fieldName(), Fields.NUM_PURCHASES.fieldName(), Fields.EVENT_TIME_OFFSET.fieldName(), Fields.EVENT_TIME.fieldName(), Fields.FIRST_PLAY_TIME_OFFSET.fieldName(), Fields.FIRST_PLAY_TIME.fieldName(), Fields.NUM_SESSIONS.fieldName(), Fields.DAYS_RETAINED.fieldName(), Fields.DEVICE_TYPE.fieldName(), Fields.CLIENT_OS_TYPE.fieldName(), Fields.CLIENT_OS_VERSION.fieldName(), Fields.COUNTRY_CODE.fieldName(), Fields.LANG_CODE.fieldName(), Fields.DATA_CONNECTION_TYPE.fieldName(), Fields.IP_ADDRESS.fieldName(), Fields.EXTERNAL_IP_ADDRESS.fieldName(), Fields.KONG_USER_ID.fieldName(), Fields.KONG_USERNAME.fieldName(), Fields.KONG_PLUS.fieldName(), Fields.USD_SPENT_ON_KREDS.fieldName(), Fields.PUR_TIER.fieldName(), Fields.GAMECENTER_ID.fieldName(), Fields.GAMECENTER_ALIAS.fieldName(), Fields.SESSION_ID.fieldName(), Fields.IDFA.fieldName(), Fields.GOOGLE_AD_ID.fieldName(), Fields.AD_TRACKING.fieldName(), Fields.CLIENT_VERSION.fieldName(), Fields.DEV_CLIENT_VERSION.fieldName(), Fields.FIRST_CLIENT_VERSION.fieldName(), Fields.FIRST_SDK_VERSION.fieldName(), Fields.SDK_VERSION.fieldName(), Fields.PKG_SRC.fieldName(), Fields.TIME_SKEW.fieldName(), Fields.BUNDLE_ID.fieldName(), Fields.IS_FROM_BACKGROUND.fieldName(), C0525o.f628n, C0525o.f623i, C0525o.f624j, C0525o.f621g, C0525o.f622h, C0525o.f625k, C0525o.f616b, C0525o.f617c, C0525o.f618d, C0525o.f619e, C0525o.f620f, C0584f.HARD_CURRENCY_BALANCE.m866a(), C0584f.HARD_CURRENCY_BOUGHT.m866a(), C0584f.HARD_CURRENCY_SPENT.m866a(), C0584f.HARD_CURRENCY_EARNED.m866a(), C0584f.SOFT_CURRENCY_BALANCE.m866a(), C0584f.SOFT_CURRENCY_BOUGHT.m866a(), C0584f.SOFT_CURRENCY_SPENT.m866a(), C0584f.SOFT_CURRENCY_EARNED.m866a(), C0584f.SOFT_CURRENCY_2_BALANCE.m866a(), C0584f.SOFT_CURRENCY_2_BOUGHT.m866a(), C0584f.SOFT_CURRENCY_2_SPENT.m866a(), C0584f.SOFT_CURRENCY_2_EARNED.m866a(), C0584f.AB_TEST.m866a(), C0584f.PLAYER_LEVEL.m866a(), C0584f.TUTORIAL_COMPLETED.m866a(), C0584f.SERVER_PLAYER_ID.m866a(), C0584f.FILTER_TYPE.m866a(), C0584f.GAME_USERNAME.m866a(), C0584f.GOOGLE_PLAY_SERVICES_ID.m866a(), C0584f.GOOGLE_PLAY_SERVICES_ALIAS.m866a(), C0584f.LEGACY_USER.m866a(), C0584f.HARD_CURRENCY_CHANGE.m866a(), C0584f.SOFT_CURRENCY_CHANGE.m866a(), C0584f.TYPE.m866a(), C0584f.DISCOUNT_PERCENT.m866a(), C0584f.CONTEXT_OF_OFFER.m866a()}));
    }

    /* renamed from: a */
    public static boolean m827a(Context context, PackageInfo packageInfo) {
        boolean z = false;
        if (packageInfo.receivers == null) {
            C0562j.m759c("AdjustWrapper - no receivers specified");
        } else {
            for (ActivityInfo activityInfo : packageInfo.receivers) {
                if ("com.kongregate.android.api.receivers.InstallReceiver".equals(activityInfo.name)) {
                    C0562j.m753a("AdjustWrapper - receiver found: " + activityInfo.name);
                    if (activityInfo.metaData != null) {
                        boolean z2 = z;
                        for (String string : activityInfo.metaData.keySet()) {
                            String string2 = activityInfo.metaData.getString(string2);
                            if ("com.adjust.sdk.AdjustReferrerReceiver".equals(string2)) {
                                C0562j.m753a("AdjustWrapper - forward found: " + string2);
                                try {
                                    Class.forName(string2);
                                    z = true;
                                } catch (Throwable e) {
                                    C0562j.m760c("AdjustWrapper - referral receiver class not found: ", e);
                                }
                                z2 = z;
                            }
                            z = z2;
                            z2 = z;
                        }
                        z = z2;
                    }
                }
            }
        }
        return z;
    }

    public C0578a(Context context) {
        this.f793f = context.getApplicationContext();
    }

    /* renamed from: a */
    public void m831a(Map<String, Object> map, OnAttributionChangedListener onAttributionChangedListener) {
        C0562j.m753a("Adjust initialize");
        CharSequence a = C0487b.m421a((Map) map, KongregateAPI.KONGREGATE_OPTION_ADJUST_APP_TOKEN);
        if (StringUtils.m580a(a)) {
            C0562j.m759c("Adjust Initialization - unable to find AdjustAppToken. Not initializing Adjust.");
            return;
        }
        String a2 = m826a(map);
        if (a2 != null) {
            this.f794g = new HashMap();
            this.f794g.put(f788a, C0487b.m421a((Map) map, KongregateAPI.KONGREGATE_ADJUST_SALE_EVENT_TOKEN));
            this.f794g.put(f789b, C0487b.m421a((Map) map, KongregateAPI.KONGREGATE_ADJUST_SESSION_EVENT_TOKEN));
            this.f794g.put(f790c, C0487b.m421a((Map) map, KongregateAPI.KONGREGATE_ADJUST_INSTALL_EVENT_TOKEN));
            for (String str : this.f794g.keySet()) {
                if (StringUtils.m580a((CharSequence) this.f794g.get(str))) {
                    C0562j.m759c("KONGREGATE CONFIGURATION WARNING: missing Adjust event token for event: " + str);
                }
            }
            C0562j.m753a("Adjust initializing: " + a + " : " + a2);
            AdjustConfig adjustConfig = new AdjustConfig(this.f793f, a, a2);
            adjustConfig.setLogLevel(AdjustConfig.class.equals(a2) ? LogLevel.DEBUG : LogLevel.INFO);
            if (onAttributionChangedListener != null) {
                adjustConfig.setOnAttributionChangedListener(onAttributionChangedListener);
            }
            m836e().m823a(adjustConfig);
            m834c();
        }
    }

    /* renamed from: a */
    public void m829a() {
        if (m835d()) {
            C0562j.m753a("Adjust onPause");
            m836e().m821a();
            return;
        }
        C0562j.m759c("Adjust - onPause - not initialized");
    }

    /* renamed from: b */
    public void m832b() {
        if (m835d()) {
            C0562j.m753a("Adjust - onResume");
            m836e().m825b();
            return;
        }
        C0562j.m759c("Adjust - onResume - not initialized");
    }

    /* renamed from: a */
    public void m830a(String str, Map<String, Object> map) {
        if (m835d()) {
            CharSequence charSequence = (String) this.f794g.get(str);
            if (StringUtils.m587c(charSequence)) {
                C0562j.m759c("Adjust - no event token for event: " + str);
                return;
            }
            C0562j.m753a("firing Adjust event: " + str + " token: " + charSequence);
            AdjustEvent adjustEvent = new AdjustEvent(charSequence);
            if (f792e.contains(str)) {
                Double d;
                try {
                    d = (Double) map.get(C0525o.f621g);
                } catch (ClassCastException e) {
                    C0562j.m759c("unable to cast USD_COST to double: " + map.get(C0525o.f621g));
                    d = null;
                }
                if (d != null) {
                    C0562j.m753a("Adding revenue to adjust event: " + d);
                    adjustEvent.setRevenue(d.doubleValue(), "USD");
                } else {
                    C0562j.m759c("Unable to set usd cost: " + d);
                }
            }
            adjustEvent.addCallbackParameter("kong_props", m828b(map));
            m836e().m824a(adjustEvent);
            return;
        }
        C0562j.m759c("Adjust - not initialized. Not sending event: " + str);
    }

    /* renamed from: b */
    public void m833b(String str, Map<String, Object> map) {
        if (m835d()) {
            AdjustEvent adjustEvent = new AdjustEvent(str);
            adjustEvent.addCallbackParameter("kong_props", m828b(map));
            m836e().m824a(adjustEvent);
            return;
        }
        C0562j.m759c("Adjust - not initialized. Not sending event: " + str);
    }

    /* renamed from: c */
    public void m834c() {
        Uri openURL = APIBootstrap.getInstance().mobile().getOpenURL();
        if (m835d() && openURL != null) {
            C0562j.m753a("notify adjust of deep link launch: " + openURL);
            m836e().m822a(openURL);
        }
    }

    /* renamed from: d */
    protected boolean m835d() {
        return this.f794g != null;
    }

    /* renamed from: e */
    protected C0577a m836e() {
        return this.f795h;
    }

    /* renamed from: a */
    private String m826a(Map<String, Object> map) {
        String a = C0487b.m421a((Map) map, KongregateAPI.KONGREGATE_OPTION_ADJUST_ENVIRONMENT);
        if (AdjustConfig.ENVIRONMENT_SANDBOX.equals(a)) {
            return AdjustConfig.ENVIRONMENT_SANDBOX;
        }
        if (AdjustConfig.ENVIRONMENT_PRODUCTION.equals(a)) {
            return AdjustConfig.ENVIRONMENT_PRODUCTION;
        }
        C0562j.m759c("Adjust initialization - unable to find AdjustEnvironment " + a + ". Not initializing Adjust (expected " + AdjustConfig.ENVIRONMENT_PRODUCTION + " or " + AdjustConfig.ENVIRONMENT_SANDBOX + ").");
        return null;
    }

    /* renamed from: b */
    private String m828b(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            if (f791d.contains(str) && C0561i.m743a(map.get(str))) {
                try {
                    jSONObject.put(str, map.get(str));
                } catch (Throwable e) {
                    C0562j.m760c("Exception building custom data json: attempting to add field: " + str, e);
                }
            }
        }
        return jSONObject.toString();
    }
}
