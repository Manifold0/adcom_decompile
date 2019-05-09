package com.kongregate.android.internal.browser;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.Toast;
import com.applovin.sdk.AppLovinEventTypes;
import com.facebook.internal.NativeProtocol;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.api.activities.KongregatePanelActivity;
import com.kongregate.android.internal.browser.C0451a.C0449b;
import com.kongregate.android.internal.sdk.C0507l;
import com.kongregate.android.internal.sdk.C0525o;
import com.kongregate.android.internal.util.C0542a;
import com.kongregate.android.internal.util.C0555f;
import com.kongregate.android.internal.util.C0555f.C0439b;
import com.kongregate.android.internal.util.C0555f.C0554a;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0566n;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p001j.C0666a;
import com.kongregate.p000o.p006c.C0626d;
import com.kongregate.p000o.p007d.C0635b;
import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MobileApiWebView extends C0451a {
    /* renamed from: o */
    private static final int f325o = "sendToKongregate://".length();
    /* renamed from: A */
    private volatile String f326A;
    /* renamed from: B */
    private volatile boolean f327B = false;
    /* renamed from: C */
    private volatile boolean f328C = false;
    /* renamed from: D */
    private volatile boolean f329D = false;
    /* renamed from: E */
    private volatile boolean f330E = false;
    /* renamed from: F */
    private volatile boolean f331F = false;
    /* renamed from: G */
    private volatile boolean f332G = false;
    /* renamed from: H */
    private final C0462b f333H;
    /* renamed from: I */
    private C0555f f334I = new C0555f(new C04401(this));
    /* renamed from: p */
    private long f335p = System.currentTimeMillis();
    /* renamed from: q */
    private volatile KongregatePanelActivity f336q;
    /* renamed from: r */
    private final String f337r;
    /* renamed from: s */
    private final String f338s;
    /* renamed from: t */
    private final String f339t;
    /* renamed from: u */
    private final boolean f340u;
    /* renamed from: v */
    private final boolean f341v;
    /* renamed from: w */
    private final boolean f342w;
    /* renamed from: x */
    private final JSONArray f343x;
    /* renamed from: y */
    private final MutableContextWrapper f344y;
    /* renamed from: z */
    private volatile String f345z = null;

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$1 */
    class C04401 implements C0439b {
        /* renamed from: a */
        final /* synthetic */ MobileApiWebView f299a;

        C04401(MobileApiWebView mobileApiWebView) {
            this.f299a = mobileApiWebView;
        }

        /* renamed from: a */
        public void mo1122a(String str, C0554a c0554a) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("token", str);
                jSONObject.put("result", c0554a.m656a());
            } catch (JSONException e) {
            }
            this.f299a.m252a("fbconnect", jSONObject);
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$2 */
    class C04412 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ MobileApiWebView f300a;

        C04412(MobileApiWebView mobileApiWebView) {
            this.f300a = mobileApiWebView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                case 1:
                    if (!view.hasFocus()) {
                        view.requestFocus();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$4 */
    class C04434 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ MobileApiWebView f303a;

        C04434(MobileApiWebView mobileApiWebView) {
            this.f303a = mobileApiWebView;
        }

        public void run() {
            synchronized (this.f303a) {
                this.f303a.m216b();
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$5 */
    class C04445 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ MobileApiWebView f304a;

        C04445(MobileApiWebView mobileApiWebView) {
            this.f304a = mobileApiWebView;
        }

        public void run() {
            synchronized (this.f304a) {
                if (this.f304a.m262n()) {
                    C0562j.m753a("WebView waking up with target: " + this.f304a.f345z);
                    this.f304a.loadUrl(this.f304a.f339t + (StringUtils.m591d(this.f304a.f345z) ? "?" + this.f304a.f345z : ""));
                    this.f304a.f345z = null;
                    this.f304a.m261m();
                } else {
                    C0562j.m753a("WebView No longer attached while attempting to wake, going back to sleep");
                    this.f304a.loadUrl(this.f304a.f338s);
                }
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$6 */
    class C04456 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ MobileApiWebView f305a;

        C04456(MobileApiWebView mobileApiWebView) {
            this.f305a = mobileApiWebView;
        }

        public void run() {
            synchronized (this.f305a) {
                String e = this.f305a.m248q();
                if (e == null) {
                    C0562j.m753a("WebView homeUrl returned null, not loading home");
                    return;
                }
                this.f305a.f330E = false;
                Map hashMap = new HashMap();
                if (this.f305a.f327B) {
                    hashMap.put("Cache-Control", "max-age=0");
                    this.f305a.f327B = false;
                }
                this.f305a.loadUrl(e, hashMap);
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$7 */
    class C04467 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ MobileApiWebView f306a;

        C04467(MobileApiWebView mobileApiWebView) {
            this.f306a = mobileApiWebView;
        }

        public void run() {
            C0666a.m1145a().m1167a(true);
            this.f306a.m253a(true);
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$8 */
    class C04478 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ MobileApiWebView f307a;

        C04478(MobileApiWebView mobileApiWebView) {
            this.f307a = mobileApiWebView;
        }

        public void run() {
            this.f307a.f334I.m657a(this.f307a.f336q, this.f307a.getFacebookAppId());
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$9 */
    class C04489 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ MobileApiWebView f308a;

        C04489(MobileApiWebView mobileApiWebView) {
            this.f308a = mobileApiWebView;
        }

        public void run() {
            Map a = ((C0525o) APIBootstrap.getInstance().analytics()).m516a();
            C0561i.m742a(a);
            this.f308a.m252a("analyticFields", new JSONObject(a));
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.MobileApiWebView$a */
    private class C0450a extends C0449b {
        /* renamed from: b */
        final /* synthetic */ MobileApiWebView f310b;

        private C0450a(MobileApiWebView mobileApiWebView) {
            this.f310b = mobileApiWebView;
            super(mobileApiWebView);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            C0562j.m761d("MobileApiWebView.onReceivedError for " + str2 + " errorCode: " + i + ": " + str);
            this.f310b.m214a(this.f310b.f337r);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            C0562j.m761d("WebView SSL Error: " + sslError.toString());
            if (C0635b.m1001a().m1023k()) {
                sslErrorHandler.cancel();
                this.f310b.m214a(this.f310b.f337r);
                Toast.makeText(this.f310b.l, "Problem validating security cerificate.", 1).show();
                return;
            }
            sslErrorHandler.proceed();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.toLowerCase().indexOf("sendToKongregate://".toLowerCase()) == 0) {
                String i = StringUtils.m597i(str.substring(MobileApiWebView.f325o));
                String[] split = i != null ? i.split(":##sendToApp##", 2) : null;
                if (split != null && split.length == 2) {
                    this.f310b.m228b(split[0], split[1]);
                    return true;
                }
            } else if (str.toLowerCase().equals("about:blank") || str.equals(C0451a.f316f)) {
                C0562j.m756b("WebView Ignoring blank page load: " + str);
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            httpAuthHandler.proceed("mobileapi", "NW6NrEdB7mHU");
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (str.equals(this.f310b.f326A)) {
                this.f310b.m254b(str);
            } else {
                C0562j.m759c("WebView Ignoring onPageFinished for url we didn't start: " + str);
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.f310b.f329D = true;
            this.f310b.f326A = str;
            if (!this.f310b.m236d(str)) {
                this.f310b.f332G = false;
                this.f310b.f328C = false;
                this.f310b.f330E = false;
                this.f310b.m249r();
            }
            this.f310b.m249r();
        }
    }

    public MobileApiWebView(Context context, C0462b c0462b, String str, boolean z, boolean z2, boolean z3, Set<String> set) {
        super(context, null);
        if (isInEditMode() || (context instanceof MutableContextWrapper)) {
            this.f344y = context instanceof MutableContextWrapper ? (MutableContextWrapper) context : null;
            this.f333H = c0462b;
            this.f337r = str;
            this.f338s = str + "sleep";
            this.f339t = str + "wake";
            this.f340u = z;
            this.f341v = z2;
            this.f342w = z3;
            this.f343x = new JSONArray(set);
            if (z && z2) {
                throw new IllegalArgumentException("MobileApiWebView cannot have both persistent and cacheWarmer flags");
            }
            if (!isInEditMode()) {
                C0562j.m753a("WebView user-agent: " + m221a((WebView) this));
                getSettings().setCacheMode(-1);
                setWebViewClient(new C0450a());
            }
            if (context instanceof KongregatePanelActivity) {
                this.f336q = (KongregatePanelActivity) context;
            }
            requestFocus(130);
            setOnTouchListener(new C04412(this));
            return;
        }
        throw new IllegalArgumentException("MobileApiWebView requires a MutableContextWrapper");
    }

    /* renamed from: a */
    public synchronized void m251a(KongregatePanelActivity kongregatePanelActivity) {
        if (kongregatePanelActivity == null) {
            mo1129g();
        } else {
            this.f336q = kongregatePanelActivity;
            this.f344y.setBaseContext(kongregatePanelActivity);
            Bundle extras = kongregatePanelActivity.getIntent().getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            CharSequence string = extras.getString(KongregatePanelActivity.INTENT_EXTRA_TARGET);
            CharSequence string2 = extras.getString(KongregatePanelActivity.INTENT_EXTRA_TARGET_ID);
            if (StringUtils.m584b(string)) {
                this.f345z = "target=" + string + (StringUtils.m584b(string2) ? "&target_id=" + string2 : "");
            } else {
                this.f345z = null;
            }
            C0562j.m756b("Attaching WebView to activity: " + kongregatePanelActivity + ", target: " + this.f345z + ", loading: " + this.f329D + ", loaded: " + this.f330E);
            m249r();
            if (this.f329D) {
                C0562j.m753a("WebView Panel is loading, setting reload flag");
                kongregatePanelActivity.showProgressSpinner();
                this.f331F = true;
            } else if (this.f330E) {
                C0562j.m753a("WebView Panel is already loaded, waking up");
                m257i();
                this.f331F = false;
                kongregatePanelActivity.hideProgressSpinner();
            } else {
                C0562j.m753a("WebView Panel has never loaded, refreshing");
                kongregatePanelActivity.showProgressSpinner();
                m253a(true);
            }
        }
    }

    /* renamed from: g */
    public synchronized void mo1129g() {
        if (m262n()) {
            C0562j.m756b("Detaching WebView from activity: " + this.f336q + ", loading: " + this.f329D + ", menuVisible: " + this.f328C);
            this.f336q = null;
            this.f345z = null;
            this.f331F = false;
            this.f344y.setBaseContext(this.l.getApplicationContext());
            if (!this.f340u) {
                C0562j.m753a("WebView Panel is not persistent, destroying");
                this.f333H.m288b();
            } else if (this.f329D) {
                C0562j.m753a("WebView Panel is loading, setting reload needed flag");
                this.f331F = true;
            } else if (this.f330E) {
                C0562j.m753a("WebView Panel is loaded, going to sleep");
                loadUrl(this.f338s);
            } else {
                C0562j.m753a("WebView Panel has not loaded, refreshing");
                m253a(true);
            }
        }
    }

    @TargetApi(11)
    public void setPaused(boolean z) {
        if (z) {
            C0562j.m753a("Pausing WebView");
            if (C0542a.m606a(11)) {
                onPause();
            } else {
                C0566n.m775a(WebView.class, "onPause", (Object) this, null, new Object[0]);
            }
        } else if (C0542a.m606a(11)) {
            onResume();
        } else {
            C0566n.m775a(WebView.class, "onResume", (Object) this, null, new Object[0]);
        }
    }

    /* renamed from: a */
    public static String m221a(WebView webView) {
        String str = webView.getSettings().getUserAgentString() + " KongregateMobileApi/" + KongregateAPI.KONGREGATE_API_VERSION;
        webView.getSettings().setUserAgentString(str);
        return str;
    }

    /* renamed from: a */
    public synchronized void m252a(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        String str2 = "try{iosKonduit('" + str + "', " + jSONObject2 + ");}catch(e){}";
        C0562j.m756b("Sending message to WebView - " + str + ": " + jSONObject2);
        m231c(str2);
    }

    /* renamed from: c */
    private void m231c(final String str) {
        C0626d.m968b(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ MobileApiWebView f302b;

            public void run() {
                synchronized (this.f302b) {
                    if (this.f302b.f330E) {
                        this.f302b.loadUrl("javascript:" + str);
                    } else {
                        C0562j.m759c("Dropping javascript message, not ready");
                    }
                }
            }
        });
    }

    /* renamed from: h */
    public synchronized void m256h() {
        if (m262n()) {
            C0562j.m753a("WebView is attached - not refreshing cache");
        } else {
            m253a(false);
        }
    }

    /* renamed from: a */
    public synchronized void m253a(boolean z) {
        C0562j.m756b("WebView reloading panel, allowCache=" + z);
        this.f327B = !z;
        C0626d.m968b(new C04434(this));
    }

    /* renamed from: i */
    public synchronized void m257i() {
        C0626d.m968b(new C04445(this));
    }

    /* renamed from: p */
    private void m247p() {
        C0626d.m968b(new C04456(this));
    }

    /* renamed from: q */
    private String m248q() {
        if (this.f341v) {
            return this.f337r;
        }
        if (!m262n()) {
            return this.f340u ? this.f338s : null;
        } else {
            String str = StringUtils.m591d(this.f345z) ? this.f337r + "redirect?" + this.f345z : this.f337r;
            this.f345z = null;
            return str;
        }
    }

    /* renamed from: b */
    private synchronized void m228b(String str, String str2) {
        boolean z = true;
        CharSequence charSequence = null;
        synchronized (this) {
            C0562j.m756b("WebView API message: " + str + " data: " + str2);
            KongregatePanelActivity kongregatePanelActivity = this.f336q;
            if ("android-log".equals(str)) {
                C0562j.m756b("__js__>>" + str2);
            } else if (String.CLOSE.equals(str)) {
                C0562j.m759c("Closing panel activity");
                if (kongregatePanelActivity != null) {
                    kongregatePanelActivity.finish();
                }
            } else if (AppLovinEventTypes.USER_LOGGED_IN.equals(str)) {
                C0666a.m1145a().m1165a(C0561i.m749c(str2));
            } else if ("logout".equals(str)) {
                C0626d.m962a(new C04467(this));
            } else if ("initialized".equals(str)) {
                this.f330E = true;
                if (!(!this.f329D || this.f341v || this.f326A == null || C0451a.f316f.equals(this.f326A))) {
                    C0562j.m759c("WebView received initialized message, but panel is still loading. Forcing loadFinished on URL: " + this.f326A);
                    m254b(this.f326A);
                }
                m250s();
                m261m();
                C0666a.m1145a().m1175e();
            } else if ("load-info".equals(str)) {
                r0 = C0561i.m749c(str2);
                if (r0 != null) {
                    C0666a.m1145a().m1169b(r0.optJSONObject("data"), r0.optString("token"));
                }
            } else if ("link".equals(str)) {
                r0 = C0561i.m749c(str2);
                if (r0 != null) {
                    charSequence = C0561i.m748c(r0, "url");
                }
                if (!(StringUtils.m580a(charSequence) || kongregatePanelActivity == null)) {
                    try {
                        kongregatePanelActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(charSequence)));
                    } catch (Throwable e) {
                        C0562j.m762d("WebView Could not start activity for opening link (no browser installed?)", e);
                    }
                }
            } else if ("menu-state".equals(str)) {
                this.f328C = C0561i.m749c(str2).optBoolean(String.VISIBLE, false);
                m249r();
            } else if ("fbconnect".equals(str)) {
                m260l();
            } else if ("notification-info".equals(str)) {
                r0 = C0561i.m749c(str2);
                if (r0 != null) {
                    r0 = r0.optJSONObject("data");
                } else {
                    r0 = null;
                }
                if (r0 != null) {
                    r1 = r0.optJSONObject(NativeProtocol.WEB_DIALOG_PARAMS);
                    C0666a a = C0666a.m1145a();
                    int optInt = r0.optInt(NewHtcHomeBadger.COUNT, 0);
                    String optString = r0.optString("type", null);
                    if (r1 != null) {
                        r0 = r1.optString("category");
                    } else {
                        r0 = null;
                    }
                    a.m1162a(optInt, optString, r0);
                }
            } else if ("url-changed".equals(str)) {
                if (kongregatePanelActivity != null) {
                    JSONObject c = C0561i.m749c(str2);
                    if (c == null || !c.optBoolean("has_history", false)) {
                        z = false;
                    }
                    this.f332G = z;
                    m249r();
                }
            } else if ("panel-close-analytics-event".equals(str)) {
                r1 = C0561i.m749c(str2);
                if (r1 != null) {
                    ((C0525o) APIBootstrap.getInstance().analytics()).m526a(r1.optJSONObject("data"));
                }
            } else if ("error".equals(str)) {
                C0562j.m759c("WebView Error message received, clearing panel loaded flag");
                this.f330E = false;
            } else if ("request-character-token".equals(str)) {
                r0 = C0561i.m749c(str2);
                C0666a.m1145a().m1168b(r0 != null ? r0.optString("invalid_token", "") : "");
            } else if ("reload".equals(str)) {
                C0562j.m756b("Reloading WebView");
                m253a(false);
            } else if ("panel-version".equals(str)) {
                C0562j.m756b("Panel version info received");
                r0 = C0561i.m749c(str2);
                this.f333H.m286a(r0.optString("current", null), r0.optString("latest", null));
            } else if ("api-event".equals(str)) {
                C0562j.m756b("Panel fired and API event: closing panel and passing event to game: " + str2);
                if (kongregatePanelActivity != null) {
                    kongregatePanelActivity.finish();
                }
                r0 = C0561i.m749c(str2);
                String c2 = C0561i.m748c(r0, "name");
                r0 = C0561i.m748c(r0, "data");
                Intent intent = new Intent(C0507l.f551a);
                intent.putExtra(C0507l.f552b, c2);
                intent.putExtra(C0507l.f553c, r0);
                LocalBroadcastManager.getInstance(this.l).sendBroadcast(intent);
            } else {
                C0562j.m759c("unhandled WebView API message: " + str + ": " + str2);
            }
        }
    }

    /* renamed from: r */
    private void m249r() {
        KongregatePanelActivity kongregatePanelActivity = this.f336q;
        if (kongregatePanelActivity != null) {
            kongregatePanelActivity.findViewById(C0558g.m664a("kongregate_toolbar_menu", "id", this.l)).setEnabled(this.f328C);
            kongregatePanelActivity.setHasHistory(this.f332G);
        }
    }

    /* renamed from: d */
    private boolean m236d(String str) {
        return (str == null || this.f337r == null || !str.startsWith(this.f337r)) ? false : true;
    }

    /* renamed from: j */
    public boolean m258j() {
        return m236d(getUrl());
    }

    /* renamed from: k */
    public boolean m259k() {
        return this.f330E;
    }

    /* renamed from: l */
    public void m260l() {
        C0562j.m756b("Starting FB connect flow");
        C0626d.m962a(new C04478(this));
    }

    public C0555f getFacebookHelper() {
        return this.f334I;
    }

    private String getFacebookAppId() {
        return C0635b.m1001a().m1016d();
    }

    /* renamed from: s */
    private void m250s() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("native_menu_button", true);
            jSONObject.put("fb_connect", true);
            jSONObject.put("subtasks", true);
            jSONObject.put("file_chooser", new JSONArray());
            jSONObject.put("character_token", C0666a.m1145a().m1193w());
            jSONObject.put("reload", true);
            jSONObject.put("persistent", this.f340u);
            jSONObject.put("guild_chat", this.f342w);
            jSONObject.put("supported_events", this.f343x);
            jSONObject.put("inline_video", C0542a.m606a(14));
        } catch (JSONException e) {
        }
        m252a("config", jSONObject);
    }

    /* renamed from: m */
    public void m261m() {
        C0626d.m962a(new C04489(this));
    }

    /* renamed from: b */
    protected synchronized void m254b(String str) {
        this.f326A = null;
        if (C0451a.f316f.equals(str)) {
            C0562j.m756b("WebView Blank page finished loading, reloading panel");
            m247p();
        } else if (this.f341v) {
            C0562j.m756b("Cache warmer finished loading, destroying WebView");
            this.f333H.m288b();
        } else {
            KongregatePanelActivity kongregatePanelActivity = this.f336q;
            if (kongregatePanelActivity != null) {
                kongregatePanelActivity.hideProgressSpinner();
            }
            this.f329D = false;
            if (this.f331F) {
                C0562j.m756b("WebView reload required");
                this.f331F = false;
                m257i();
            }
        }
    }

    /* renamed from: n */
    public boolean m262n() {
        return this.f336q != null;
    }
}
