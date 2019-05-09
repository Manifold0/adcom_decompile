package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TJAdUnit.TJAdUnitVideoListener;
import com.tapjoy.TJAdUnit.TJAdUnitWebViewListener;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.C2854d;
import com.tapjoy.internal.C2999y;
import com.tapjoy.internal.cg;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.ee;
import com.tapjoy.internal.el;
import com.tapjoy.internal.ep;
import com.tapjoy.internal.et;
import com.tapjoy.internal.ex;
import com.tapjoy.internal.ey;
import com.tapjoy.internal.ez;
import com.tapjoy.internal.fd;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.fi.C2926a;
import com.tapjoy.internal.fl;
import com.tapjoy.internal.fv;
import com.tapjoy.internal.fy;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.gh;
import com.tapjoy.internal.gj;
import com.tapjoy.internal.gk;
import com.tapjoy.internal.gl;
import com.tapjoy.internal.hm;
import com.tapjoy.internal.hm.C2978a;
import com.vungle.warren.ui.VungleActivity;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.hockeyapp.android.UpdateActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TJCorePlacement {
    /* renamed from: a */
    static final String f6912a = TJCorePlacement.class.getSimpleName();
    /* renamed from: b */
    Context f6913b = C2854d.m7352c();
    /* renamed from: c */
    TJPlacementData f6914c;
    /* renamed from: d */
    String f6915d;
    /* renamed from: e */
    long f6916e;
    /* renamed from: f */
    final ez f6917f = new ez();
    /* renamed from: g */
    TJAdUnit f6918g;
    /* renamed from: h */
    boolean f6919h = false;
    /* renamed from: i */
    gj f6920i = null;
    /* renamed from: j */
    boolean f6921j;
    /* renamed from: k */
    volatile boolean f6922k = false;
    /* renamed from: l */
    volatile boolean f6923l = false;
    /* renamed from: m */
    volatile boolean f6924m = false;
    /* renamed from: n */
    String f6925n;
    /* renamed from: o */
    String f6926o;
    /* renamed from: p */
    String f6927p;
    /* renamed from: q */
    String f6928q;
    /* renamed from: r */
    private Map f6929r = new HashMap();
    /* renamed from: s */
    private Map f6930s;
    /* renamed from: t */
    private ep f6931t;
    /* renamed from: u */
    private boolean f6932u = false;
    /* renamed from: v */
    private hm f6933v = null;
    /* renamed from: w */
    private volatile boolean f6934w = false;
    /* renamed from: x */
    private TJAdUnitWebViewListener f6935x = new C28021(this);
    /* renamed from: y */
    private TJAdUnitVideoListener f6936y = new C28032(this);

    /* renamed from: com.tapjoy.TJCorePlacement$1 */
    class C28021 implements TJAdUnitWebViewListener {
        /* renamed from: a */
        final /* synthetic */ TJCorePlacement f6898a;

        C28021(TJCorePlacement tJCorePlacement) {
            this.f6898a = tJCorePlacement;
        }

        public final void onContentReady() {
            this.f6898a.m7067c();
        }

        public final void onClosed() {
            if (this.f6898a.f6919h) {
                TJPlacementManager.decrementPlacementCacheCount();
                this.f6898a.f6919h = false;
            }
            if (this.f6898a.f6932u) {
                TJPlacementManager.decrementPlacementPreRenderCount();
                this.f6898a.f6932u = false;
            }
        }
    }

    /* renamed from: com.tapjoy.TJCorePlacement$2 */
    class C28032 implements TJAdUnitVideoListener {
        /* renamed from: a */
        final /* synthetic */ TJCorePlacement f6899a;

        C28032(TJCorePlacement tJCorePlacement) {
            this.f6899a = tJCorePlacement;
        }

        public final void onVideoStart() {
            TJPlacement a = this.f6899a.m7059a("SHOW");
            if (a != null && a.getVideoListener() != null) {
                a.getVideoListener().onVideoStart(a);
            }
        }

        public final void onVideoCompleted() {
            TJPlacement a = this.f6899a.m7059a("SHOW");
            if (a != null && a.getVideoListener() != null) {
                a.getVideoListener().onVideoComplete(a);
            }
        }

        public final void onVideoError(String errorMessage) {
            TJPlacement a = this.f6899a.m7059a("SHOW");
            if (a != null && a.getVideoListener() != null) {
                a.getVideoListener().onVideoError(a, errorMessage);
            }
        }
    }

    /* renamed from: com.tapjoy.TJCorePlacement$4 */
    class C28064 implements fv {
        /* renamed from: a */
        final /* synthetic */ String f6907a;
        /* renamed from: b */
        final /* synthetic */ TJCorePlacement f6908b;

        C28064(TJCorePlacement tJCorePlacement, String str) {
            this.f6908b = tJCorePlacement;
            this.f6907a = str;
        }

        /* renamed from: a */
        public final void mo6163a(Context context, String str, String str2) {
            if (str2 == null) {
                this.f6908b.f6914c.setRedirectURL(str);
            } else {
                this.f6908b.f6914c.setBaseURL(str);
                this.f6908b.f6914c.setHttpResponse(str2);
            }
            this.f6908b.f6914c.setHasProgressSpinner(true);
            this.f6908b.f6914c.setContentViewId(this.f6907a);
            Intent intent = new Intent(this.f6908b.f6913b, TJAdUnitActivity.class);
            intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, this.f6908b.f6914c);
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            context.startActivity(intent);
        }
    }

    /* renamed from: com.tapjoy.TJCorePlacement$5 */
    class C28075 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TJCorePlacement f6909a;

        C28075(TJCorePlacement tJCorePlacement) {
            this.f6909a = tJCorePlacement;
        }

        public final void run() {
            this.f6909a.f6920i.mo6290a(gc.m7831a().f7864p, this.f6909a.f6917f);
        }
    }

    /* renamed from: com.tapjoy.TJCorePlacement$6 */
    class C28086 implements TJCacheListener {
        /* renamed from: a */
        final /* synthetic */ TJCacheListener f6910a;
        /* renamed from: b */
        final /* synthetic */ TJCorePlacement f6911b;

        C28086(TJCorePlacement tJCorePlacement, TJCacheListener tJCacheListener) {
            this.f6911b = tJCorePlacement;
            this.f6910a = tJCacheListener;
        }

        public final void onCachingComplete(int status) {
            this.f6910a.onCachingComplete(status);
        }
    }

    TJCorePlacement(String placementName, String placementKey) {
        if (this.f6913b == null) {
            TapjoyLog.m7126d(f6912a, "getVisibleActivity() is NULL. Activity can be explicitly set via `Tapjoy.setActivity(Activity)`");
        }
        this.f6914c = new TJPlacementData(placementKey, getPlacementContentUrl());
        this.f6914c.setPlacementName(placementName);
        this.f6915d = UUID.randomUUID().toString();
        this.f6918g = new TJAdUnit();
        this.f6918g.setWebViewListener(this.f6935x);
        this.f6918g.setVideoListener(this.f6936y);
    }

    /* renamed from: a */
    final synchronized void m7060a() {
        String url = this.f6914c.getUrl();
        if (ct.m7339c(url)) {
            url = getPlacementContentUrl();
            if (ct.m7339c(url)) {
                url = "TJPlacement is missing APP_ID";
                fi.m7750b("TJPlacement.requestContent").m7735a(url).m7742c();
                m7063a(ErrorType.SDK_ERROR, new TJError(0, url));
            } else {
                this.f6914c.updateUrl(url);
            }
        }
        TapjoyLog.m7126d(f6912a, "sendContentRequest -- URL: " + url + " name: " + this.f6914c.getPlacementName());
        m7065a(url, null);
    }

    /* renamed from: a */
    final synchronized void m7065a(String str, Map map) {
        String str2 = null;
        synchronized (this) {
            if (this.f6934w) {
                TapjoyLog.m7129i(f6912a, "Placement " + this.f6914c.getPlacementName() + " is already requesting content");
                fi.m7750b("TJPlacement.requestContent").m7740b("already doing").m7742c();
            } else {
                this.f6914c.resetPlacementRequestData();
                ez ezVar = this.f6917f;
                ezVar.f7708b = null;
                ezVar.f7710d = null;
                ezVar.f7707a = null;
                this.f6918g.resetContentLoadState();
                this.f6934w = false;
                this.f6922k = false;
                this.f6923l = false;
                this.f6924m = false;
                this.f6920i = null;
                this.f6933v = null;
                this.f6934w = true;
                final TJPlacement a = m7059a("REQUEST");
                this.f6930s = TapjoyConnectCore.getGenericURLParams();
                this.f6930s.putAll(TapjoyConnectCore.getTimeStampAndVerifierParams());
                TapjoyUtil.safePut(this.f6930s, "event_name", this.f6914c.getPlacementName(), true);
                TapjoyUtil.safePut(this.f6930s, TJAdUnitConstants.PARAM_PLACEMENT_PRELOAD, "true", true);
                TapjoyUtil.safePut(this.f6930s, "debug", Boolean.toString(fz.f7823a), true);
                gc a2 = gc.m7831a();
                Map map2 = this.f6930s;
                String str3 = TJAdUnitConstants.PARAM_ACTION_ID_EXCLUSION;
                if (a2.f7850b != null) {
                    gl glVar = a2.f7850b;
                    glVar.m7970b();
                    str2 = glVar.f7925b.m8219a();
                }
                TapjoyUtil.safePut(map2, str3, str2, true);
                TapjoyUtil.safePut(this.f6930s, TJAdUnitConstants.PARAM_PLACEMENT_BY_SDK, String.valueOf(this.f6921j), true);
                TapjoyUtil.safePut(this.f6930s, TJAdUnitConstants.PARAM_PUSH_ID, a.pushId, true);
                TapjoyUtil.safePut(this.f6930s, TapjoyConstants.TJC_MEDIATION_SOURCE, this.f6925n, true);
                TapjoyUtil.safePut(this.f6930s, TapjoyConstants.TJC_ADAPTER_VERSION, this.f6926o, true);
                if (map != null) {
                    this.f6930s.putAll(map);
                }
                final el elVar = new el(fd.m7717b().m7710c("placement_request_content_retry_timeout"));
                final fl d = fd.m7717b().m7711d("placement_request_content_retry_backoff");
                final C2926a d2 = fi.m7753d("TJPlacement.requestContent");
                final String str4 = str;
                new Thread(this) {
                    /* renamed from: f */
                    final /* synthetic */ TJCorePlacement f6906f;

                    /* renamed from: com.tapjoy.TJCorePlacement$3$1 */
                    class C28041 implements TJCacheListener {
                        /* renamed from: a */
                        final /* synthetic */ C28053 f6900a;

                        C28041(C28053 c28053) {
                            this.f6900a = c28053;
                        }

                        public final void onCachingComplete(int status) {
                            this.f6900a.f6906f.f6932u = this.f6900a.f6906f.getAdUnit().preload(this.f6900a.f6906f.f6914c, this.f6900a.f6906f.f6913b);
                        }
                    }

                    public final void run() {
                        fi.m7745a("TJPlacement.requestContent", d2);
                        int i = 0;
                        while (!m7035a()) {
                            i++;
                            this.f6906f.f6930s.put(TapjoyConstants.TJC_RETRY, Integer.toString(i));
                            if (i == 1) {
                                d2.m7737a("retry_timeout", Long.valueOf(elVar.f7663b));
                            }
                            d2.m7736a("retry_count", (long) i);
                        }
                    }

                    /* renamed from: a */
                    private boolean m7035a() {
                        long b;
                        TapjoyLog.m7129i(TJCorePlacement.f6912a, "Sending content request for placement " + this.f6906f.f6914c.getPlacementName());
                        TJCorePlacement tJCorePlacement = this.f6906f;
                        gc a = gc.m7831a();
                        String f = this.f6906f.f6914c.getPlacementName();
                        Context g = this.f6906f.f6913b;
                        gk gkVar = a.f7849a;
                        ee a2 = gkVar.f7920a.m7838a(false);
                        tJCorePlacement.f6933v = new hm(gkVar.f7920a, a2.f7536d, a2.f7537e, a2.f7538f, f, g);
                        TapjoyHttpURLResponse responseFromURL = new TapjoyURLConnection().getResponseFromURL(str4, null, null, this.f6906f.f6930s);
                        this.f6906f.f6914c.setHttpStatusCode(responseFromURL.statusCode);
                        this.f6906f.f6914c.setHttpResponse(responseFromURL.response);
                        if (!responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_PRERENDER_HEADER).equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                            this.f6906f.f6914c.setPrerenderingRequested(true);
                        }
                        String headerFieldAsString = responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_DEBUG_HEADER);
                        if (headerFieldAsString != null) {
                            TapjoyLog.m7130v(TJCorePlacement.f6912a, "Tapjoy-Server-Debug: " + headerFieldAsString);
                        }
                        if (responseFromURL.expires > 0) {
                            b = responseFromURL.expires - (responseFromURL.date > 0 ? responseFromURL.date : C2999y.m8233b());
                            if (b > 0) {
                                this.f6906f.f6916e = b + SystemClock.elapsedRealtime();
                            }
                        } else {
                            this.f6906f.f6916e = 0;
                        }
                        if (!(responseFromURL == null || a.getListener() == null)) {
                            switch (responseFromURL.statusCode) {
                                case 0:
                                    if (elVar.m7650a(d.f7787e)) {
                                        fi.m7750b("TJPlacement.requestContent").m7735a("network error").m7737a("retry_timeout", Long.valueOf(elVar.f7663b)).m7742c();
                                        this.f6906f.m7062a(a, ErrorType.NETWORK_ERROR, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                        break;
                                    }
                                    fl flVar = d;
                                    long j = flVar.f7787e;
                                    b = (long) (((double) flVar.f7787e) * flVar.f7786d);
                                    if (b < flVar.f7784b) {
                                        b = flVar.f7784b;
                                    } else if (b > flVar.f7785c) {
                                        b = flVar.f7785c;
                                    }
                                    flVar.f7787e = b;
                                    if (j > 0) {
                                        synchronized (flVar) {
                                            try {
                                                flVar.wait(j);
                                            } catch (InterruptedException e) {
                                            }
                                        }
                                    }
                                    return false;
                                case 200:
                                    TJCorePlacement.m7054i(this.f6906f);
                                    headerFieldAsString = responseFromURL.getHeaderFieldAsString("Content-Type");
                                    if (!ct.m7339c(headerFieldAsString) && headerFieldAsString.contains(UpdateActivity.EXTRA_JSON)) {
                                        if (!responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_DISABLE_PRELOAD_HEADER).equals("1")) {
                                            if (!this.f6906f.m7046b(responseFromURL.response)) {
                                                fi.m7750b("TJPlacement.requestContent").m7735a("asset error").m7742c();
                                                this.f6906f.m7062a(a, ErrorType.SERVER_ERROR, new TJError(responseFromURL.statusCode, responseFromURL.response));
                                                break;
                                            }
                                            fi.m7750b("TJPlacement.requestContent").m7737a("content_type", (Object) "mm").m7742c();
                                            TJCorePlacement.m7056k(this.f6906f);
                                            this.f6906f.m7067c();
                                            break;
                                        }
                                        try {
                                            TJCorePlacement.m7042a(this.f6906f, responseFromURL.response);
                                            fi.m7750b("TJPlacement.requestContent").m7737a("content_type", (Object) "ad").m7742c();
                                            this.f6906f.f6917f.f7707a = this.f6906f.f6931t;
                                            TJCorePlacement.m7056k(this.f6906f);
                                            this.f6906f.m7067c();
                                            break;
                                        } catch (TapjoyException e2) {
                                            headerFieldAsString = e2.getMessage() + " for placement " + this.f6906f.f6914c.getPlacementName();
                                            fi.m7750b("TJPlacement.requestContent").m7735a("server error").m7742c();
                                            this.f6906f.m7062a(a, ErrorType.SERVER_ERROR, new TJError(responseFromURL.statusCode, headerFieldAsString));
                                            break;
                                        }
                                    }
                                    fi.m7750b("TJPlacement.requestContent").m7737a("content_type", (Object) "ad").m7742c();
                                    this.f6906f.f6917f.f7707a = this.f6906f.f6931t;
                                    TJCorePlacement.m7056k(this.f6906f);
                                    TJCorePlacement tJCorePlacement2 = this.f6906f;
                                    TJCacheListener c28041 = new C28041(this);
                                    TapjoyLog.m7129i(TJCorePlacement.f6912a, "Checking if there is content to cache for placement " + tJCorePlacement2.f6914c.getPlacementName());
                                    String headerFieldAsString2 = responseFromURL.getHeaderFieldAsString(TapjoyConstants.TAPJOY_CACHE_HEADER);
                                    try {
                                        if (!TJPlacementManager.canCachePlacement()) {
                                            TapjoyLog.m7129i(TJCorePlacement.f6912a, "Placement caching limit reached. No content will be cached for placement " + tJCorePlacement2.f6914c.getPlacementName());
                                            c28041.onCachingComplete(2);
                                            break;
                                        }
                                        JSONArray jSONArray = new JSONArray(headerFieldAsString2);
                                        if (jSONArray.length() <= 0) {
                                            c28041.onCachingComplete(1);
                                            break;
                                        }
                                        TapjoyLog.m7129i(TJCorePlacement.f6912a, "Begin caching content for placement " + tJCorePlacement2.f6914c.getPlacementName());
                                        TJPlacementManager.incrementPlacementCacheCount();
                                        tJCorePlacement2.f6919h = true;
                                        TapjoyCache.getInstance().cacheAssetGroup(jSONArray, new C28086(tJCorePlacement2, c28041));
                                        break;
                                    } catch (JSONException e3) {
                                        c28041.onCachingComplete(2);
                                        break;
                                    }
                                    break;
                                default:
                                    fi.m7750b("TJPlacement.requestContent").m7737a("content_type", ParametersKeys.ORIENTATION_NONE).m7737a("code", Integer.valueOf(responseFromURL.statusCode)).m7742c();
                                    this.f6906f.m7061a(a);
                                    break;
                            }
                        }
                        this.f6906f.f6934w = false;
                        return true;
                    }
                }.start();
            }
        }
    }

    /* renamed from: b */
    private boolean m7046b(String str) {
        try {
            C2978a c2978a = (C2978a) this.f6933v.mo6205a(URI.create(this.f6914c.getUrl()), new ByteArrayInputStream(str.getBytes()));
            this.f6920i = c2978a.f8130a;
            c2978a.f8130a.mo6291b();
            if (c2978a.f8130a.mo6292c()) {
                et etVar = null;
                if (this.f6920i instanceof gh) {
                    etVar = new ex(this.f6914c.getPlacementName(), this.f6914c.getPlacementType(), this.f6931t);
                } else if (this.f6920i instanceof fy) {
                    etVar = new ey(this.f6914c.getPlacementName(), this.f6914c.getPlacementType(), this.f6931t);
                }
                this.f6917f.f7707a = etVar;
                return true;
            }
            TapjoyLog.m7128e(f6912a, "Failed to load fiverocks placement");
            return false;
        } catch (IOException e) {
            TapjoyLog.m7128e(f6912a, e.toString());
            e.printStackTrace();
            return false;
        } catch (cg e2) {
            TapjoyLog.m7128e(f6912a, e2.toString());
            e2.printStackTrace();
            return false;
        }
    }

    public Context getContext() {
        return this.f6913b;
    }

    public void setContext(Context activityContext) {
        this.f6913b = activityContext;
    }

    public TJAdUnit getAdUnit() {
        return this.f6918g;
    }

    public TJPlacementData getPlacementData() {
        return this.f6914c;
    }

    public boolean isContentReady() {
        return this.f6924m;
    }

    public boolean isContentAvailable() {
        return this.f6923l;
    }

    public String getPlacementContentUrl() {
        String appID = TapjoyConnectCore.getAppID();
        if (ct.m7339c(appID)) {
            return "";
        }
        return TapjoyConnectCore.getPlacementURL() + "v1/apps/" + appID + "/content?";
    }

    /* renamed from: b */
    final String m7066b() {
        if (this.f6920i != null) {
            return "mm";
        }
        if (this.f6923l) {
            return "ad";
        }
        return ParametersKeys.ORIENTATION_NONE;
    }

    /* renamed from: a */
    final void m7064a(String str, TJPlacement tJPlacement) {
        synchronized (this.f6929r) {
            this.f6929r.put(str, tJPlacement);
            if (tJPlacement != null) {
                TapjoyLog.m7126d(f6912a, "Setting " + str + " placement: " + tJPlacement.getGUID());
            }
        }
    }

    /* renamed from: a */
    final TJPlacement m7059a(String str) {
        TJPlacement tJPlacement;
        synchronized (this.f6929r) {
            tJPlacement = (TJPlacement) this.f6929r.get(str);
            if (tJPlacement != null) {
                TapjoyLog.m7126d(f6912a, "Returning " + str + " placement: " + tJPlacement.getGUID());
            }
        }
        return tJPlacement;
    }

    /* renamed from: a */
    final void m7061a(TJPlacement tJPlacement) {
        ez ezVar = this.f6917f;
        Object placementName = this.f6914c.getPlacementName();
        Object placementType = this.f6914c.getPlacementType();
        Object b = m7066b();
        ezVar.f7709c = 0;
        ezVar.f7708b = fi.m7754e("PlacementContent.funnel").m7734a().m7737a(VungleActivity.PLACEMENT_EXTRA, placementName).m7737a("placement_type", placementType).m7737a("content_type", b).m7737a("state", Integer.valueOf(ezVar.f7709c));
        ezVar.f7708b.m7742c();
        if (!ParametersKeys.ORIENTATION_NONE.equals(b)) {
            ezVar.f7711e = fi.m7754e("PlacementContent.ready").m7734a().m7737a(VungleActivity.PLACEMENT_EXTRA, placementName).m7737a("placement_type", placementType).m7737a("content_type", b);
        }
        if (tJPlacement != null && tJPlacement.getListener() != null) {
            TapjoyLog.m7129i(f6912a, "Content request delivered successfully for placement " + this.f6914c.getPlacementName() + ", contentAvailable: " + isContentAvailable() + ", mediationAgent: " + this.f6927p);
            tJPlacement.getListener().onRequestSuccess(tJPlacement);
        }
    }

    /* renamed from: a */
    final void m7063a(ErrorType errorType, TJError tJError) {
        m7062a(m7059a("REQUEST"), errorType, tJError);
    }

    /* renamed from: a */
    final void m7062a(TJPlacement tJPlacement, ErrorType errorType, TJError tJError) {
        TapjoyLog.m7127e(f6912a, new TapjoyErrorMessage(errorType, "Content request failed for placement " + this.f6914c.getPlacementName() + "; Reason= " + tJError.message));
        if (tJPlacement != null && tJPlacement.getListener() != null) {
            tJPlacement.getListener().onRequestFailure(tJPlacement, tJError);
        }
    }

    /* renamed from: c */
    final void m7067c() {
        if (!this.f6922k) {
            ez ezVar;
            this.f6924m = true;
            TapjoyLog.m7129i(f6912a, "Content is ready for placement " + this.f6914c.getPlacementName());
            if (this.f6918g.isPrerendered()) {
                ezVar = this.f6917f;
                String str = "prerendered";
                Object valueOf = Boolean.valueOf(true);
                C2926a c2926a = ezVar.f7708b;
                if (c2926a != null) {
                    c2926a.m7737a(str, valueOf);
                }
                C2926a c2926a2 = ezVar.f7711e;
                if (c2926a2 != null) {
                    c2926a2.m7737a(str, valueOf);
                }
            }
            ezVar = this.f6917f;
            C2926a c2926a3 = ezVar.f7711e;
            if (c2926a3 != null) {
                ezVar.f7711e = null;
                c2926a3.m7739b().m7742c();
            }
            TJPlacement a = m7059a("REQUEST");
            if (a != null && a.getListener() != null) {
                a.getListener().onContentReady(a);
                this.f6922k = true;
            }
        }
    }

    /* renamed from: i */
    static /* synthetic */ void m7054i(TJCorePlacement tJCorePlacement) {
        tJCorePlacement.f6931t = new ep(tJCorePlacement.f6914c.getPlacementName(), tJCorePlacement.f6914c.getPlacementType());
        tJCorePlacement.f6918g.setAdContentTracker(tJCorePlacement.f6931t);
    }

    /* renamed from: a */
    static /* synthetic */ void m7042a(TJCorePlacement tJCorePlacement, String str) {
        if (str != null) {
            try {
                TapjoyLog.m7126d(f6912a, "Disable preload flag is set for placement " + tJCorePlacement.f6914c.getPlacementName());
                tJCorePlacement.f6914c.setRedirectURL(new JSONObject(str).getString(TapjoyConstants.TJC_REDIRECT_URL));
                tJCorePlacement.f6914c.setPreloadDisabled(true);
                tJCorePlacement.f6914c.setHasProgressSpinner(true);
                TapjoyLog.m7126d(f6912a, "redirect_url:" + tJCorePlacement.f6914c.getRedirectURL());
                return;
            } catch (JSONException e) {
                throw new TapjoyException("TJPlacement request failed, malformed server response");
            }
        }
        throw new TapjoyException("TJPlacement request failed due to null response");
    }

    /* renamed from: k */
    static /* synthetic */ void m7056k(TJCorePlacement tJCorePlacement) {
        tJCorePlacement.f6923l = true;
        tJCorePlacement.m7061a(tJCorePlacement.m7059a("REQUEST"));
    }
}
