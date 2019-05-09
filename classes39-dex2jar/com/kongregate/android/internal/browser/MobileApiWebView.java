// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.browser;

import android.widget.Toast;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.HttpAuthHandler;
import android.graphics.Bitmap;
import android.annotation.TargetApi;
import com.kongregate.android.internal.util.n;
import android.app.Activity;
import java.io.Serializable;
import android.os.Bundle;
import com.kongregate.android.internal.util.g;
import java.util.Map;
import java.util.HashMap;
import android.support.v4.content.LocalBroadcastManager;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.internal.sdk.o;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.o.c.d;
import com.kongregate.android.internal.util.i;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import com.kongregate.android.internal.util.j;
import android.webkit.WebView;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.AttributeSet;
import java.util.Set;
import android.content.Context;
import android.content.MutableContextWrapper;
import org.json.JSONArray;
import com.kongregate.android.api.activities.KongregatePanelActivity;
import com.kongregate.android.internal.util.f;

public class MobileApiWebView extends com.kongregate.android.internal.browser.a
{
    private static final int o;
    private volatile String A;
    private volatile boolean B;
    private volatile boolean C;
    private volatile boolean D;
    private volatile boolean E;
    private volatile boolean F;
    private volatile boolean G;
    private final com.kongregate.android.internal.browser.b H;
    private f I;
    private long p;
    private volatile KongregatePanelActivity q;
    private final String r;
    private final String s;
    private final String t;
    private final boolean u;
    private final boolean v;
    private final boolean w;
    private final JSONArray x;
    private final MutableContextWrapper y;
    private volatile String z;
    
    static {
        o = "sendToKongregate://".length();
    }
    
    public MobileApiWebView(final Context context, final com.kongregate.android.internal.browser.b h, final String r, final boolean u, final boolean v, final boolean w, final Set<String> set) {
        super(context, null);
        this.p = System.currentTimeMillis();
        this.z = null;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.I = new f((f.b)new f.b() {
            @Override
            public void a(final String s, final f.a a) {
                final JSONObject jsonObject = new JSONObject();
                while (true) {
                    try {
                        jsonObject.put("token", (Object)s);
                        jsonObject.put("result", (Object)a.a());
                        MobileApiWebView.this.a("fbconnect", jsonObject);
                    }
                    catch (JSONException ex) {
                        continue;
                    }
                    break;
                }
            }
        });
        if (!this.isInEditMode() && !(context instanceof MutableContextWrapper)) {
            throw new IllegalArgumentException("MobileApiWebView requires a MutableContextWrapper");
        }
        MutableContextWrapper y;
        if (context instanceof MutableContextWrapper) {
            y = (MutableContextWrapper)context;
        }
        else {
            y = null;
        }
        this.y = y;
        this.H = h;
        this.r = r;
        this.s = r + "sleep";
        this.t = r + "wake";
        this.u = u;
        this.v = v;
        this.w = w;
        this.x = new JSONArray((Collection)set);
        if (u && v) {
            throw new IllegalArgumentException("MobileApiWebView cannot have both persistent and cacheWarmer flags");
        }
        if (!this.isInEditMode()) {
            com.kongregate.android.internal.util.j.a("WebView user-agent: " + a((WebView)this));
            this.getSettings().setCacheMode(-1);
            this.setWebViewClient((WebViewClient)new a());
        }
        if (context instanceof KongregatePanelActivity) {
            this.q = (KongregatePanelActivity)context;
        }
        this.requestFocus(130);
        this.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 1: {
                        if (!view.hasFocus()) {
                            view.requestFocus();
                            break;
                        }
                        break;
                    }
                }
                return false;
            }
        });
    }
    
    public static String a(final WebView webView) {
        final String string = webView.getSettings().getUserAgentString() + " KongregateMobileApi/" + "3.0.5";
        webView.getSettings().setUserAgentString(string);
        return string;
    }
    
    private void b(String s, String s2) {
        while (true) {
            boolean a = true;
            Object optString = null;
            Object o = null;
            Label_0111: {
                synchronized (this) {
                    com.kongregate.android.internal.util.j.b("WebView API message: " + s + " data: " + s2);
                    o = this.q;
                    if ("android-log".equals(s)) {
                        com.kongregate.android.internal.util.j.b("__js__>>" + s2);
                    }
                    else {
                        if (!"close".equals(s)) {
                            break Label_0111;
                        }
                        com.kongregate.android.internal.util.j.c("Closing panel activity");
                        if (o != null) {
                            ((KongregatePanelActivity)o).finish();
                        }
                    }
                    return;
                }
            }
            final String s3;
            if ("login".equals(s3)) {
                com.kongregate.o.j.a.a().a(com.kongregate.android.internal.util.i.c(s2));
                return;
            }
            if ("logout".equals(s3)) {
                com.kongregate.o.c.d.a(new Runnable() {
                    @Override
                    public void run() {
                        com.kongregate.o.j.a.a().a(true);
                        MobileApiWebView.this.a(true);
                    }
                });
                return;
            }
            if ("initialized".equals(s3)) {
                this.E = true;
                if (this.D && !this.v && this.A != null && !"file:///android_res/raw/blank".equals(this.A)) {
                    com.kongregate.android.internal.util.j.c("WebView received initialized message, but panel is still loading. Forcing loadFinished on URL: " + this.A);
                    this.b(this.A);
                }
                this.s();
                this.m();
                com.kongregate.o.j.a.a().e();
                return;
            }
            if ("load-info".equals(s3)) {
                final JSONObject c = com.kongregate.android.internal.util.i.c(s2);
                if (c != null) {
                    com.kongregate.o.j.a.a().b(c.optJSONObject("data"), c.optString("token"));
                }
            }
            else if ("link".equals(s3)) {
                s2 = (String)com.kongregate.android.internal.util.i.c(s2);
                s = (String)optString;
                if (s2 != null) {
                    s = com.kongregate.android.internal.util.i.c((JSONObject)s2, "url");
                }
                a = StringUtils.a((CharSequence)s);
                if (!a && o != null) {
                    try {
                        ((KongregatePanelActivity)o).startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                    }
                    catch (ActivityNotFoundException ex) {
                        com.kongregate.android.internal.util.j.d("WebView Could not start activity for opening link (no browser installed?)", (Throwable)ex);
                    }
                }
            }
            else {
                if ("menu-state".equals(s3)) {
                    this.C = com.kongregate.android.internal.util.i.c(s2).optBoolean("visible", false);
                    this.r();
                    return;
                }
                if ("fbconnect".equals(s3)) {
                    this.l();
                    return;
                }
                if ("notification-info".equals(s3)) {
                    final JSONObject c2 = com.kongregate.android.internal.util.i.c(s2);
                    JSONObject optJSONObject;
                    if (c2 != null) {
                        optJSONObject = c2.optJSONObject("data");
                    }
                    else {
                        optJSONObject = null;
                    }
                    if (optJSONObject != null) {
                        o = optJSONObject.optJSONObject("params");
                        s2 = (String)com.kongregate.o.j.a.a();
                        final int optInt = optJSONObject.optInt("count", 0);
                        optString = optJSONObject.optString("type", (String)null);
                        if (o != null) {
                            s = ((JSONObject)o).optString("category");
                        }
                        else {
                            s = null;
                        }
                        ((com.kongregate.o.j.a)s2).a(optInt, (String)optString, s);
                    }
                }
                else if ("url-changed".equals(s3)) {
                    if (o != null) {
                        final JSONObject c3 = com.kongregate.android.internal.util.i.c(s2);
                        if (c3 == null || !c3.optBoolean("has_history", false)) {
                            a = false;
                        }
                        this.G = a;
                        this.r();
                    }
                }
                else if ("panel-close-analytics-event".equals(s3)) {
                    final JSONObject c4 = com.kongregate.android.internal.util.i.c(s2);
                    if (c4 != null) {
                        ((o)APIBootstrap.getInstance().analytics()).a(c4.optJSONObject("data"));
                    }
                }
                else {
                    if ("error".equals(s3)) {
                        com.kongregate.android.internal.util.j.c("WebView Error message received, clearing panel loaded flag");
                        this.E = false;
                        return;
                    }
                    if ("request-character-token".equals(s3)) {
                        final JSONObject c5 = com.kongregate.android.internal.util.i.c(s2);
                        if (c5 != null) {
                            s = c5.optString("invalid_token", "");
                        }
                        else {
                            s = "";
                        }
                        com.kongregate.o.j.a.a().b(s);
                        return;
                    }
                    if ("reload".equals(s3)) {
                        com.kongregate.android.internal.util.j.b("Reloading WebView");
                        this.a(false);
                        return;
                    }
                    if ("panel-version".equals(s3)) {
                        com.kongregate.android.internal.util.j.b("Panel version info received");
                        final JSONObject c6 = com.kongregate.android.internal.util.i.c(s2);
                        s = c6.optString("current", (String)null);
                        s2 = c6.optString("latest", (String)null);
                        this.H.a(s, s2);
                        return;
                    }
                    if ("api-event".equals(s3)) {
                        com.kongregate.android.internal.util.j.b("Panel fired and API event: closing panel and passing event to game: " + s2);
                        if (o != null) {
                            ((KongregatePanelActivity)o).finish();
                        }
                        final JSONObject c7 = com.kongregate.android.internal.util.i.c(s2);
                        s = com.kongregate.android.internal.util.i.c(c7, "name");
                        s2 = com.kongregate.android.internal.util.i.c(c7, "data");
                        optString = new Intent("com.kongregate.android.internal.sdk.BroadcastAPIEvent");
                        ((Intent)optString).putExtra("com.kongregate.android.internal.sdk.event", s);
                        ((Intent)optString).putExtra("com.kongregate.android.internal.sdk.data", s2);
                        LocalBroadcastManager.getInstance(this.l).sendBroadcast((Intent)optString);
                        return;
                    }
                    com.kongregate.android.internal.util.j.c("unhandled WebView API message: " + s3 + ": " + s2);
                }
            }
        }
    }
    
    private void c(final String s) {
        com.kongregate.o.c.d.b(new Runnable() {
            @Override
            public void run() {
                synchronized (MobileApiWebView.this) {
                    if (MobileApiWebView.this.E) {
                        MobileApiWebView.this.loadUrl("javascript:" + s);
                    }
                    else {
                        com.kongregate.android.internal.util.j.c("Dropping javascript message, not ready");
                    }
                }
            }
        });
    }
    
    private boolean d(final String s) {
        return s != null && this.r != null && s.startsWith(this.r);
    }
    
    private String getFacebookAppId() {
        return com.kongregate.o.d.b.a().d();
    }
    
    private void p() {
        com.kongregate.o.c.d.b(new Runnable() {
            @Override
            public void run() {
                synchronized (MobileApiWebView.this) {
                    final String e = MobileApiWebView.this.q();
                    if (e == null) {
                        com.kongregate.android.internal.util.j.a("WebView homeUrl returned null, not loading home");
                        return;
                    }
                    MobileApiWebView.this.E = false;
                    final HashMap<String, String> hashMap = new HashMap<String, String>();
                    if (MobileApiWebView.this.B) {
                        hashMap.put("Cache-Control", "max-age=0");
                        MobileApiWebView.this.B = false;
                    }
                    MobileApiWebView.this.loadUrl(e, (Map)hashMap);
                }
            }
        });
    }
    
    private String q() {
        if (this.v) {
            return this.r;
        }
        if (this.n()) {
            String s;
            if (StringUtils.d((CharSequence)this.z)) {
                s = this.r + "redirect?" + this.z;
            }
            else {
                s = this.r;
            }
            this.z = null;
            return s;
        }
        if (this.u) {
            return this.s;
        }
        return null;
    }
    
    private void r() {
        final KongregatePanelActivity q = this.q;
        if (q != null) {
            q.findViewById(com.kongregate.android.internal.util.g.a("kongregate_toolbar_menu", "id", this.l)).setEnabled(this.C);
            q.setHasHistory(this.G);
        }
    }
    
    private void s() {
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("native_menu_button", true);
                jsonObject.put("fb_connect", true);
                jsonObject.put("subtasks", true);
                jsonObject.put("file_chooser", (Object)new JSONArray());
                jsonObject.put("character_token", (Object)com.kongregate.o.j.a.a().w());
                jsonObject.put("reload", true);
                jsonObject.put("persistent", this.u);
                jsonObject.put("guild_chat", this.w);
                jsonObject.put("supported_events", (Object)this.x);
                jsonObject.put("inline_video", com.kongregate.android.internal.util.a.a(14));
                this.a("config", jsonObject);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void a(final KongregatePanelActivity kongregatePanelActivity) {
        // monitorenter(this)
        Label_0013: {
            if (kongregatePanelActivity != null) {
                break Label_0013;
            }
            Bundle extras;
            Serializable s;
            String string;
            String string2;
            Label_0038_Outer:Label_0107_Outer:
            while (true) {
            Label_0107:
                while (true) {
                    while (true) {
                    Label_0038:
                        while (true) {
                            try {
                                this.g();
                                return;
                                while (true) {
                                    com.kongregate.android.internal.util.j.a("WebView Panel is loading, setting reload flag");
                                    kongregatePanelActivity.showProgressSpinner();
                                    this.F = true;
                                    return;
                                    s = extras.getString("target");
                                    string = extras.getString("target_id");
                                    while (true) {
                                        Block_4: {
                                            break Block_4;
                                            this.q = kongregatePanelActivity;
                                            this.y.setBaseContext((Context)kongregatePanelActivity);
                                            extras = kongregatePanelActivity.getIntent().getExtras();
                                            continue Label_0038;
                                            string2 = "&target_id=" + string;
                                            break Label_0107;
                                        }
                                        s = new StringBuilder().append("target=").append((String)s);
                                        continue Label_0107_Outer;
                                    }
                                    this.z = ((StringBuilder)s).append(string2).toString();
                                    com.kongregate.android.internal.util.j.b("Attaching WebView to activity: " + kongregatePanelActivity + ", target: " + this.z + ", loading: " + this.D + ", loaded: " + this.E);
                                    this.r();
                                    continue Label_0038_Outer;
                                }
                            }
                            // iftrue(Label_0226:, !StringUtils.b((CharSequence)s))
                            // iftrue(Label_0215:, extras == null)
                            // iftrue(Label_0281:, !StringUtils.b((CharSequence)string))
                            // iftrue(Label_0234:, !this.D)
                            finally {
                            }
                            // monitorexit(this)
                            Label_0215: {
                                extras = new Bundle();
                            }
                            continue Label_0038;
                        }
                        Label_0226: {
                            this.z = null;
                        }
                        continue;
                    }
                    Label_0234: {
                        if (this.E) {
                            com.kongregate.android.internal.util.j.a("WebView Panel is already loaded, waking up");
                            this.i();
                            this.F = false;
                            kongregatePanelActivity.hideProgressSpinner();
                            return;
                        }
                    }
                    com.kongregate.android.internal.util.j.a("WebView Panel has never loaded, refreshing");
                    kongregatePanelActivity.showProgressSpinner();
                    this.a(true);
                    return;
                    Label_0281:
                    string2 = "";
                    continue Label_0107;
                }
            }
        }
    }
    
    public void a(final String s, final JSONObject jsonObject) {
        synchronized (this) {
            final String string = jsonObject.toString();
            final String string2 = "try{iosKonduit('" + s + "', " + string + ");}catch(e){}";
            com.kongregate.android.internal.util.j.b("Sending message to WebView - " + s + ": " + string);
            this.c(string2);
        }
    }
    
    public void a(final boolean b) {
        synchronized (this) {
            com.kongregate.android.internal.util.j.b("WebView reloading panel, allowCache=" + b);
            this.B = !b;
            com.kongregate.o.c.d.b(new Runnable() {
                @Override
                public void run() {
                    synchronized (MobileApiWebView.this) {
                        MobileApiWebView.this.b();
                    }
                }
            });
        }
    }
    
    protected void b(final String s) {
        while (true) {
            Label_0058: {
                synchronized (this) {
                    this.A = null;
                    if ("file:///android_res/raw/blank".equals(s)) {
                        com.kongregate.android.internal.util.j.b("WebView Blank page finished loading, reloading panel");
                        this.p();
                    }
                    else {
                        if (!this.v) {
                            break Label_0058;
                        }
                        com.kongregate.android.internal.util.j.b("Cache warmer finished loading, destroying WebView");
                        this.H.b();
                    }
                    return;
                }
            }
            final KongregatePanelActivity q = this.q;
            if (q != null) {
                q.hideProgressSpinner();
            }
            this.D = false;
            if (this.F) {
                com.kongregate.android.internal.util.j.b("WebView reload required");
                this.F = false;
                this.i();
            }
        }
    }
    
    public void g() {
        while (true) {
            Label_0123: {
                synchronized (this) {
                    if (this.n()) {
                        com.kongregate.android.internal.util.j.b("Detaching WebView from activity: " + this.q + ", loading: " + this.D + ", menuVisible: " + this.C);
                        this.q = null;
                        this.z = null;
                        this.F = false;
                        this.y.setBaseContext(this.l.getApplicationContext());
                        if (this.u) {
                            break Label_0123;
                        }
                        com.kongregate.android.internal.util.j.a("WebView Panel is not persistent, destroying");
                        this.H.b();
                    }
                    return;
                }
            }
            if (this.D) {
                com.kongregate.android.internal.util.j.a("WebView Panel is loading, setting reload needed flag");
                this.F = true;
                return;
            }
            if (this.E) {
                com.kongregate.android.internal.util.j.a("WebView Panel is loaded, going to sleep");
                this.loadUrl(this.s);
                return;
            }
            com.kongregate.android.internal.util.j.a("WebView Panel has not loaded, refreshing");
            this.a(true);
        }
    }
    
    public f getFacebookHelper() {
        return this.I;
    }
    
    public void h() {
        synchronized (this) {
            if (this.n()) {
                com.kongregate.android.internal.util.j.a("WebView is attached - not refreshing cache");
            }
            else {
                this.a(false);
            }
        }
    }
    
    public void i() {
        synchronized (this) {
            com.kongregate.o.c.d.b(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Label_0165: {
                                synchronized (MobileApiWebView.this) {
                                    if (!MobileApiWebView.this.n()) {
                                        com.kongregate.android.internal.util.j.a("WebView No longer attached while attempting to wake, going back to sleep");
                                        MobileApiWebView.this.loadUrl(MobileApiWebView.this.s);
                                    }
                                    else {
                                        com.kongregate.android.internal.util.j.a("WebView waking up with target: " + MobileApiWebView.this.z);
                                        final StringBuilder append = new StringBuilder().append(MobileApiWebView.this.t);
                                        if (!StringUtils.d((CharSequence)MobileApiWebView.this.z)) {
                                            break Label_0165;
                                        }
                                        final String string = "?" + MobileApiWebView.this.z;
                                        MobileApiWebView.this.loadUrl(append.append(string).toString());
                                        MobileApiWebView.this.z = null;
                                        MobileApiWebView.this.m();
                                    }
                                    return;
                                }
                            }
                            final String string = "";
                            continue;
                        }
                    }
                }
            });
        }
    }
    
    public boolean j() {
        return this.d(this.getUrl());
    }
    
    public boolean k() {
        return this.E;
    }
    
    public void l() {
        com.kongregate.android.internal.util.j.b("Starting FB connect flow");
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                MobileApiWebView.this.I.a(MobileApiWebView.this.q, MobileApiWebView.this.getFacebookAppId());
            }
        });
    }
    
    public void m() {
        com.kongregate.o.c.d.a(new Runnable() {
            @Override
            public void run() {
                final Map<String, Object> a = ((o)APIBootstrap.getInstance().analytics()).a();
                com.kongregate.android.internal.util.i.a(a);
                MobileApiWebView.this.a("analyticFields", new JSONObject((Map)a));
            }
        });
    }
    
    public boolean n() {
        return this.q != null;
    }
    
    @TargetApi(11)
    public void setPaused(final boolean b) {
        if (b) {
            com.kongregate.android.internal.util.j.a("Pausing WebView");
            if (com.kongregate.android.internal.util.a.a(11)) {
                this.onPause();
                return;
            }
            com.kongregate.android.internal.util.n.a(WebView.class, "onPause", this, null, new Object[0]);
        }
        else {
            if (com.kongregate.android.internal.util.a.a(11)) {
                this.onResume();
                return;
            }
            com.kongregate.android.internal.util.n.a(WebView.class, "onResume", this, null, new Object[0]);
        }
    }
    
    private class a extends b
    {
        @Override
        public void onPageFinished(final WebView webView, final String s) {
            super.onPageFinished(webView, s);
            if (s.equals(MobileApiWebView.this.A)) {
                MobileApiWebView.this.b(s);
                return;
            }
            com.kongregate.android.internal.util.j.c("WebView Ignoring onPageFinished for url we didn't start: " + s);
        }
        
        @Override
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            MobileApiWebView.this.D = true;
            MobileApiWebView.this.A = s;
            if (!MobileApiWebView.this.d(s)) {
                MobileApiWebView.this.G = false;
                MobileApiWebView.this.C = false;
                MobileApiWebView.this.E = false;
                MobileApiWebView.this.r();
            }
            MobileApiWebView.this.r();
        }
        
        @Override
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            super.onReceivedError(webView, n, s, s2);
            com.kongregate.android.internal.util.j.d("MobileApiWebView.onReceivedError for " + s2 + " errorCode: " + n + ": " + s);
            MobileApiWebView.this.a(MobileApiWebView.this.r);
        }
        
        @Override
        public void onReceivedHttpAuthRequest(final WebView webView, final HttpAuthHandler httpAuthHandler, final String s, final String s2) {
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, s, s2);
            httpAuthHandler.proceed("mobileapi", "NW6NrEdB7mHU");
        }
        
        @Override
        public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
            com.kongregate.android.internal.util.j.d("WebView SSL Error: " + sslError.toString());
            if (com.kongregate.o.d.b.a().k()) {
                sslErrorHandler.cancel();
                MobileApiWebView.this.a(MobileApiWebView.this.r);
                Toast.makeText(MobileApiWebView.this.l, (CharSequence)"Problem validating security cerificate.", 1).show();
                return;
            }
            sslErrorHandler.proceed();
        }
        
        @Override
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            if (s.toLowerCase().indexOf("sendToKongregate://".toLowerCase()) == 0) {
                final String i = StringUtils.i(s.substring(MobileApiWebView.o));
                String[] split;
                if (i != null) {
                    split = i.split(":##sendToApp##", 2);
                }
                else {
                    split = null;
                }
                if (split != null && split.length == 2) {
                    MobileApiWebView.this.b(split[0], split[1]);
                    return true;
                }
            }
            else if (s.toLowerCase().equals("about:blank") || s.equals("file:///android_res/raw/blank")) {
                com.kongregate.android.internal.util.j.b("WebView Ignoring blank page load: " + s);
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, s);
        }
    }
}
