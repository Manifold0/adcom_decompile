// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.gl;
import com.tapjoy.internal.ee;
import com.tapjoy.internal.gk;
import org.json.JSONArray;
import android.os.SystemClock;
import com.tapjoy.internal.y;
import com.tapjoy.internal.fl;
import com.tapjoy.internal.el;
import com.tapjoy.internal.fd;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.et;
import com.tapjoy.internal.cg;
import java.io.IOException;
import com.tapjoy.internal.ey;
import com.tapjoy.internal.fy;
import com.tapjoy.internal.ex;
import com.tapjoy.internal.gh;
import java.io.InputStream;
import java.net.URI;
import java.io.ByteArrayInputStream;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.UUID;
import com.tapjoy.internal.d;
import java.util.HashMap;
import com.tapjoy.internal.hm;
import com.tapjoy.internal.ep;
import java.util.Map;
import com.tapjoy.internal.gj;
import com.tapjoy.internal.ez;
import android.content.Context;

public class TJCorePlacement
{
    static final String a;
    Context b;
    TJPlacementData c;
    String d;
    long e;
    final ez f;
    TJAdUnit g;
    boolean h;
    gj i;
    boolean j;
    volatile boolean k;
    volatile boolean l;
    volatile boolean m;
    String n;
    String o;
    String p;
    String q;
    private Map r;
    private Map s;
    private ep t;
    private boolean u;
    private hm v;
    private volatile boolean w;
    private TJAdUnit.TJAdUnitWebViewListener x;
    private TJAdUnit.TJAdUnitVideoListener y;
    
    static {
        a = TJCorePlacement.class.getSimpleName();
    }
    
    TJCorePlacement(final String placementName, final String s) {
        this.r = new HashMap();
        this.f = new ez();
        this.h = false;
        this.u = false;
        this.v = null;
        this.i = null;
        this.w = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.x = new TJAdUnit.TJAdUnitWebViewListener() {
            @Override
            public final void onClosed() {
                if (TJCorePlacement.this.h) {
                    TJPlacementManager.decrementPlacementCacheCount();
                    TJCorePlacement.this.h = false;
                }
                if (TJCorePlacement.this.u) {
                    TJPlacementManager.decrementPlacementPreRenderCount();
                    TJCorePlacement.this.u = false;
                }
            }
            
            @Override
            public final void onContentReady() {
                TJCorePlacement.this.c();
            }
        };
        this.y = new TJAdUnit.TJAdUnitVideoListener() {
            @Override
            public final void onVideoCompleted() {
                final TJPlacement a = TJCorePlacement.this.a("SHOW");
                if (a != null && a.getVideoListener() != null) {
                    a.getVideoListener().onVideoComplete(a);
                }
            }
            
            @Override
            public final void onVideoError(final String s) {
                final TJPlacement a = TJCorePlacement.this.a("SHOW");
                if (a != null && a.getVideoListener() != null) {
                    a.getVideoListener().onVideoError(a, s);
                }
            }
            
            @Override
            public final void onVideoStart() {
                final TJPlacement a = TJCorePlacement.this.a("SHOW");
                if (a != null && a.getVideoListener() != null) {
                    a.getVideoListener().onVideoStart(a);
                }
            }
        };
        this.b = (Context)com.tapjoy.internal.d.c();
        if (this.b == null) {
            TapjoyLog.d(TJCorePlacement.a, "getVisibleActivity() is NULL. Activity can be explicitly set via `Tapjoy.setActivity(Activity)`");
        }
        (this.c = new TJPlacementData(s, this.getPlacementContentUrl())).setPlacementName(placementName);
        this.d = UUID.randomUUID().toString();
        (this.g = new TJAdUnit()).setWebViewListener(this.x);
        this.g.setVideoListener(this.y);
    }
    
    static /* synthetic */ void a(final TJCorePlacement tjCorePlacement, final String s) {
        if (s != null) {
            try {
                TapjoyLog.d(TJCorePlacement.a, "Disable preload flag is set for placement " + tjCorePlacement.c.getPlacementName());
                tjCorePlacement.c.setRedirectURL(new JSONObject(s).getString("redirect_url"));
                tjCorePlacement.c.setPreloadDisabled(true);
                tjCorePlacement.c.setHasProgressSpinner(true);
                TapjoyLog.d(TJCorePlacement.a, "redirect_url:" + tjCorePlacement.c.getRedirectURL());
                return;
            }
            catch (JSONException ex) {
                throw new TapjoyException("TJPlacement request failed, malformed server response");
            }
        }
        throw new TapjoyException("TJPlacement request failed due to null response");
    }
    
    private boolean b(final String s) {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(s.getBytes());
        try {
            final hm.a a = (hm.a)this.v.a(URI.create(this.c.getUrl()), byteArrayInputStream);
            this.i = a.a;
            a.a.b();
            if (!a.a.c()) {
                TapjoyLog.e(TJCorePlacement.a, "Failed to load fiverocks placement");
                return false;
            }
            et a2 = null;
            if (this.i instanceof gh) {
                a2 = new ex(this.c.getPlacementName(), this.c.getPlacementType(), this.t);
            }
            else if (this.i instanceof fy) {
                a2 = new ey(this.c.getPlacementName(), this.c.getPlacementType(), this.t);
            }
            this.f.a = a2;
            return true;
        }
        catch (IOException ex) {
            TapjoyLog.e(TJCorePlacement.a, ex.toString());
            ex.printStackTrace();
            return false;
        }
        catch (cg cg) {
            TapjoyLog.e(TJCorePlacement.a, cg.toString());
            cg.printStackTrace();
            return false;
        }
    }
    
    static /* synthetic */ void i(final TJCorePlacement tjCorePlacement) {
        tjCorePlacement.t = new ep(tjCorePlacement.c.getPlacementName(), tjCorePlacement.c.getPlacementType());
        tjCorePlacement.g.setAdContentTracker(tjCorePlacement.t);
    }
    
    static /* synthetic */ void k(final TJCorePlacement tjCorePlacement) {
        tjCorePlacement.l = true;
        tjCorePlacement.a(tjCorePlacement.a("REQUEST"));
    }
    
    final TJPlacement a(final String s) {
        synchronized (this.r) {
            final TJPlacement tjPlacement = this.r.get(s);
            if (tjPlacement != null) {
                TapjoyLog.d(TJCorePlacement.a, "Returning " + s + " placement: " + tjPlacement.getGUID());
            }
            return tjPlacement;
        }
    }
    
    final void a() {
        synchronized (this) {
            String s = null;
            Label_0075: {
                if (!ct.c(s = this.c.getUrl())) {
                    break Label_0075;
                }
                s = this.getPlacementContentUrl();
                if (!ct.c(s)) {
                    this.c.updateUrl(s);
                    break Label_0075;
                }
                fi.b("TJPlacement.requestContent").a("TJPlacement is missing APP_ID").c();
                this.a(TapjoyErrorMessage.ErrorType.SDK_ERROR, new TJError(0, "TJPlacement is missing APP_ID"));
                return;
            }
            TapjoyLog.d(TJCorePlacement.a, "sendContentRequest -- URL: " + s + " name: " + this.c.getPlacementName());
            this.a(s, (Map)null);
        }
    }
    
    final void a(final TJPlacement tjPlacement) {
        final ez f = this.f;
        final String placementName = this.c.getPlacementName();
        final String placementType = this.c.getPlacementType();
        final String b = this.b();
        f.c = 0;
        (f.b = fi.e("PlacementContent.funnel").a().a("placement", placementName).a("placement_type", placementType).a("content_type", b).a("state", (Object)f.c)).c();
        if (!"none".equals(b)) {
            f.e = fi.e("PlacementContent.ready").a().a("placement", placementName).a("placement_type", placementType).a("content_type", b);
        }
        if (tjPlacement != null && tjPlacement.getListener() != null) {
            TapjoyLog.i(TJCorePlacement.a, "Content request delivered successfully for placement " + this.c.getPlacementName() + ", contentAvailable: " + this.isContentAvailable() + ", mediationAgent: " + this.p);
            tjPlacement.getListener().onRequestSuccess(tjPlacement);
        }
    }
    
    final void a(final TJPlacement tjPlacement, final TapjoyErrorMessage.ErrorType errorType, final TJError tjError) {
        TapjoyLog.e(TJCorePlacement.a, new TapjoyErrorMessage(errorType, "Content request failed for placement " + this.c.getPlacementName() + "; Reason= " + tjError.message));
        if (tjPlacement != null && tjPlacement.getListener() != null) {
            tjPlacement.getListener().onRequestFailure(tjPlacement, tjError);
        }
    }
    
    final void a(final TapjoyErrorMessage.ErrorType errorType, final TJError tjError) {
        this.a(this.a("REQUEST"), errorType, tjError);
    }
    
    final void a(final String s, final TJPlacement tjPlacement) {
        synchronized (this.r) {
            this.r.put(s, tjPlacement);
            if (tjPlacement != null) {
                TapjoyLog.d(TJCorePlacement.a, "Setting " + s + " placement: " + tjPlacement.getGUID());
            }
        }
    }
    
    final void a(final String s, Map el) {
    Label_0233_Outer:
        while (true) {
            String a = null;
            while (true) {
                gc a2 = null;
                Label_0378: {
                    synchronized (this) {
                        if (this.w) {
                            TapjoyLog.i(TJCorePlacement.a, "Placement " + this.c.getPlacementName() + " is already requesting content");
                            fi.b("TJPlacement.requestContent").b("already doing").c();
                        }
                        else {
                            this.c.resetPlacementRequestData();
                            Object o = this.f;
                            ((ez)o).b = null;
                            ((ez)o).d = null;
                            ((ez)o).a = null;
                            this.g.resetContentLoadState();
                            this.w = false;
                            this.k = false;
                            this.l = false;
                            this.m = false;
                            this.i = null;
                            this.v = null;
                            this.w = true;
                            o = this.a("REQUEST");
                            (this.s = TapjoyConnectCore.getGenericURLParams()).putAll(TapjoyConnectCore.getTimeStampAndVerifierParams());
                            TapjoyUtil.safePut(this.s, "event_name", this.c.getPlacementName(), true);
                            TapjoyUtil.safePut(this.s, "event_preload", "true", true);
                            TapjoyUtil.safePut(this.s, "debug", Boolean.toString(fz.a), true);
                            a2 = gc.a();
                            final Map s2 = this.s;
                            if (a2.b != null) {
                                break Label_0378;
                            }
                            TapjoyUtil.safePut(s2, "action_id_exclusion", a, true);
                            TapjoyUtil.safePut(this.s, "system_placement", String.valueOf(this.j), true);
                            TapjoyUtil.safePut(this.s, "push_id", ((TJPlacement)o).pushId, true);
                            TapjoyUtil.safePut(this.s, "mediation_source", this.n, true);
                            TapjoyUtil.safePut(this.s, "adapter_version", this.o, true);
                            if (el != null) {
                                this.s.putAll((Map)el);
                            }
                            el = new el(fd.b().c("placement_request_content_retry_timeout"));
                            new Thread() {
                                final /* synthetic */ fi.a a = fi.d("TJPlacement.requestContent");
                                final /* synthetic */ fl e = fd.b().d("placement_request_content_retry_backoff");
                                
                                private boolean a() {
                                    TapjoyLog.i(TJCorePlacement.a, "Sending content request for placement " + TJCorePlacement.this.c.getPlacementName());
                                    final TJCorePlacement f = TJCorePlacement.this;
                                    final gc a = gc.a();
                                    final String f2 = TJCorePlacement.this.c.getPlacementName();
                                    final Context g = TJCorePlacement.this.b;
                                    final gk a2 = a.a;
                                    final ee a3 = a2.a.a(false);
                                    f.v = new hm(a2.a, a3.d, a3.e, a3.f, f2, g);
                                    final TapjoyHttpURLResponse responseFromURL = new TapjoyURLConnection().getResponseFromURL(s, null, null, TJCorePlacement.this.s);
                                    TJCorePlacement.this.c.setHttpStatusCode(responseFromURL.statusCode);
                                    TJCorePlacement.this.c.setHttpResponse(responseFromURL.response);
                                    if (!responseFromURL.getHeaderFieldAsString("x-tapjoy-prerender").equals("0")) {
                                        TJCorePlacement.this.c.setPrerenderingRequested(true);
                                    }
                                    final String headerFieldAsString = responseFromURL.getHeaderFieldAsString("X-Tapjoy-Debug");
                                    if (headerFieldAsString != null) {
                                        TapjoyLog.v(TJCorePlacement.a, "Tapjoy-Server-Debug: " + headerFieldAsString);
                                    }
                                    if (responseFromURL.expires > 0L) {
                                        final long expires = responseFromURL.expires;
                                        long n;
                                        if (responseFromURL.date > 0L) {
                                            n = responseFromURL.date;
                                        }
                                        else {
                                            n = com.tapjoy.internal.y.b();
                                        }
                                        final long e = expires - n;
                                        if (e > 0L) {
                                            TJCorePlacement.this.e = e + SystemClock.elapsedRealtime();
                                        }
                                    }
                                    else {
                                        TJCorePlacement.this.e = 0L;
                                    }
                                    if (responseFromURL != null && ((TJPlacement)o).getListener() != null) {
                                        switch (responseFromURL.statusCode) {
                                            default: {
                                                fi.b("TJPlacement.requestContent").a("content_type", "none").a("code", (Object)responseFromURL.statusCode).c();
                                                TJCorePlacement.this.a(o);
                                                break;
                                            }
                                            case 200: {
                                                TJCorePlacement.i(TJCorePlacement.this);
                                                final String headerFieldAsString2 = responseFromURL.getHeaderFieldAsString("Content-Type");
                                                if (!ct.c(headerFieldAsString2) && headerFieldAsString2.contains("json")) {
                                                    if (responseFromURL.getHeaderFieldAsString("X-Tapjoy-Disable-Preload").equals("1")) {
                                                        try {
                                                            TJCorePlacement.a(TJCorePlacement.this, responseFromURL.response);
                                                            fi.b("TJPlacement.requestContent").a("content_type", "ad").c();
                                                            TJCorePlacement.this.f.a = TJCorePlacement.this.t;
                                                            TJCorePlacement.k(TJCorePlacement.this);
                                                            TJCorePlacement.this.c();
                                                        }
                                                        catch (TapjoyException ex) {
                                                            final String string = ex.getMessage() + " for placement " + TJCorePlacement.this.c.getPlacementName();
                                                            fi.b("TJPlacement.requestContent").a("server error").c();
                                                            TJCorePlacement.this.a(o, TapjoyErrorMessage.ErrorType.SERVER_ERROR, new TJError(responseFromURL.statusCode, string));
                                                        }
                                                        break;
                                                    }
                                                    if (TJCorePlacement.this.b(responseFromURL.response)) {
                                                        fi.b("TJPlacement.requestContent").a("content_type", "mm").c();
                                                        TJCorePlacement.k(TJCorePlacement.this);
                                                        TJCorePlacement.this.c();
                                                        break;
                                                    }
                                                    fi.b("TJPlacement.requestContent").a("asset error").c();
                                                    TJCorePlacement.this.a(o, TapjoyErrorMessage.ErrorType.SERVER_ERROR, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                                    break;
                                                }
                                                else {
                                                    fi.b("TJPlacement.requestContent").a("content_type", "ad").c();
                                                    TJCorePlacement.this.f.a = TJCorePlacement.this.t;
                                                    TJCorePlacement.k(TJCorePlacement.this);
                                                    final TJCorePlacement f3 = TJCorePlacement.this;
                                                    final TJCacheListener tjCacheListener = new TJCacheListener() {
                                                        @Override
                                                        public final void onCachingComplete(final int n) {
                                                            TJCorePlacement.this.u = TJCorePlacement.this.getAdUnit().preload(TJCorePlacement.this.c, TJCorePlacement.this.b);
                                                        }
                                                    };
                                                    TapjoyLog.i(TJCorePlacement.a, "Checking if there is content to cache for placement " + f3.c.getPlacementName());
                                                    final String headerFieldAsString3 = responseFromURL.getHeaderFieldAsString("x-tapjoy-cacheable-assets");
                                                    Label_0844: {
                                                        try {
                                                            if (TJPlacementManager.canCachePlacement()) {
                                                                break Label_0844;
                                                            }
                                                            TapjoyLog.i(TJCorePlacement.a, "Placement caching limit reached. No content will be cached for placement " + f3.c.getPlacementName());
                                                            tjCacheListener.onCachingComplete(2);
                                                        }
                                                        catch (JSONException ex2) {
                                                            tjCacheListener.onCachingComplete(2);
                                                        }
                                                        break;
                                                    }
                                                    final JSONArray jsonArray = new JSONArray(headerFieldAsString3);
                                                    if (jsonArray.length() > 0) {
                                                        TapjoyLog.i(TJCorePlacement.a, "Begin caching content for placement " + f3.c.getPlacementName());
                                                        TJPlacementManager.incrementPlacementCacheCount();
                                                        f3.h = true;
                                                        TapjoyCache.getInstance().cacheAssetGroup(jsonArray, new TJCacheListener() {
                                                            final /* synthetic */ TJCacheListener a = tjCacheListener;
                                                            
                                                            @Override
                                                            public final void onCachingComplete(final int n) {
                                                                this.a.onCachingComplete(n);
                                                            }
                                                        });
                                                        break;
                                                    }
                                                    tjCacheListener.onCachingComplete(1);
                                                    break;
                                                }
                                                break;
                                            }
                                            case 0: {
                                                if (el.a(this.e.e)) {
                                                    fi.b("TJPlacement.requestContent").a("network error").a("retry_timeout", (Object)el.b).c();
                                                    TJCorePlacement.this.a(o, TapjoyErrorMessage.ErrorType.NETWORK_ERROR, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                                    break;
                                                }
                                                final fl e2 = this.e;
                                                final long e3 = e2.e;
                                                final long n2 = (long)(e2.e * e2.d);
                                                // monitorenter(e2)
                                                Label_1087: {
                                                    if (n2 >= e2.b) {
                                                        break Label_1087;
                                                    }
                                                    long e = e2.b;
                                                    while (true) {
                                                        e2.e = e;
                                                        if (e3 <= 0L) {
                                                            return false;
                                                        }
                                                        try {
                                                            try {
                                                                e2.wait(e3);
                                                                return false;
                                                                e = e2.c;
                                                                continue Label_0233_Outer;
                                                                e = n2;
                                                            }
                                                            // iftrue(Label_1059:, n2 <= e2.c)
                                                            finally {
                                                            }
                                                            // monitorexit(e2)
                                                        }
                                                        catch (InterruptedException ex3) {
                                                            return false;
                                                        }
                                                        break;
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                                    TJCorePlacement.this.w = false;
                                    return true;
                                }
                                
                                @Override
                                public final void run() {
                                    fi.a("TJPlacement.requestContent", this.a);
                                    int n = 0;
                                    while (!this.a()) {
                                        final Map e = TJCorePlacement.this.s;
                                        ++n;
                                        e.put("retry", Integer.toString(n));
                                        if (n == 1) {
                                            this.a.a("retry_timeout", (Object)el.b);
                                        }
                                        this.a.a("retry_count", n);
                                    }
                                }
                            }.start();
                        }
                        return;
                    }
                }
                final gl b = a2.b;
                b.b();
                a = b.b.a();
                continue;
            }
        }
    }
    
    final String b() {
        if (this.i != null) {
            return "mm";
        }
        if (this.l) {
            return "ad";
        }
        return "none";
    }
    
    final void c() {
        if (!this.k) {
            this.m = true;
            TapjoyLog.i(TJCorePlacement.a, "Content is ready for placement " + this.c.getPlacementName());
            if (this.g.isPrerendered()) {
                final ez f = this.f;
                final Boolean value = true;
                final fi.a b = f.b;
                if (b != null) {
                    b.a("prerendered", value);
                }
                final fi.a e = f.e;
                if (e != null) {
                    e.a("prerendered", value);
                }
            }
            final ez f2 = this.f;
            final fi.a e2 = f2.e;
            if (e2 != null) {
                f2.e = null;
                e2.b().c();
            }
            final TJPlacement a = this.a("REQUEST");
            if (a != null && a.getListener() != null) {
                a.getListener().onContentReady(a);
                this.k = true;
            }
        }
    }
    
    public TJAdUnit getAdUnit() {
        return this.g;
    }
    
    public Context getContext() {
        return this.b;
    }
    
    public String getPlacementContentUrl() {
        final String appID = TapjoyConnectCore.getAppID();
        if (!ct.c(appID)) {
            return TapjoyConnectCore.getPlacementURL() + "v1/apps/" + appID + "/content?";
        }
        return "";
    }
    
    public TJPlacementData getPlacementData() {
        return this.c;
    }
    
    public boolean isContentAvailable() {
        return this.l;
    }
    
    public boolean isContentReady() {
        return this.m;
    }
    
    public void setContext(final Context b) {
        this.b = b;
    }
}
