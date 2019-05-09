package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.chartboost.sdk.C1397c;
import com.chartboost.sdk.C1399d;
import com.chartboost.sdk.C1403e;
import com.chartboost.sdk.C1403e.C1402a;
import com.chartboost.sdk.C1405g;
import com.chartboost.sdk.C1409h;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1378f;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.facebook.ads.AudienceNetworkActivity;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

public class bf extends C1403e {
    /* renamed from: A */
    int f3149A = 0;
    /* renamed from: B */
    int f3150B = 0;
    /* renamed from: C */
    int f3151C = 0;
    /* renamed from: D */
    int f3152D = 0;
    /* renamed from: E */
    int f3153E = 0;
    /* renamed from: F */
    int f3154F = -1;
    /* renamed from: G */
    boolean f3155G = true;
    /* renamed from: H */
    int f3156H = -1;
    /* renamed from: I */
    private final C1378f f3157I;
    /* renamed from: J */
    private final ah f3158J;
    /* renamed from: K */
    private String f3159K = null;
    /* renamed from: L */
    private float f3160L = 0.0f;
    /* renamed from: M */
    private float f3161M = 0.0f;
    /* renamed from: N */
    private boolean f3162N = false;
    /* renamed from: O */
    private int f3163O = 0;
    /* renamed from: j */
    final C1391a f3164j;
    /* renamed from: k */
    final C1399d f3165k;
    /* renamed from: l */
    final SharedPreferences f3166l;
    /* renamed from: m */
    public String f3167m = "UNKNOWN";
    /* renamed from: n */
    String f3168n = null;
    /* renamed from: o */
    protected int f3169o = 1;
    /* renamed from: p */
    long f3170p = 0;
    /* renamed from: q */
    long f3171q = 0;
    /* renamed from: r */
    boolean f3172r = false;
    /* renamed from: s */
    int f3173s = 0;
    /* renamed from: t */
    int f3174t = 0;
    /* renamed from: u */
    int f3175u = 0;
    /* renamed from: v */
    int f3176v = 0;
    /* renamed from: w */
    int f3177w = 0;
    /* renamed from: x */
    int f3178x = 0;
    /* renamed from: y */
    int f3179y = 0;
    /* renamed from: z */
    int f3180z = 0;

    /* renamed from: com.chartboost.sdk.impl.bf$a */
    private class C1430a extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ bf f3141a;

        private C1430a(bf bfVar) {
            this.f3141a = bfVar;
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            this.f3141a.f3172r = true;
            this.f3141a.f3171q = System.currentTimeMillis();
            CBLogging.m3097a("CBWebViewProtocol", "Total web view load response time " + ((this.f3141a.f3171q - this.f3141a.f3170p) / 1000));
            Context context = view.getContext();
            if (context != null) {
                this.f3141a.m3496c(context);
                this.f3141a.m3500d(context);
                this.f3141a.m3511o();
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            this.f3141a.m3298a(CBImpressionError.WEB_VIEW_CLIENT_RECEIVED_ERROR);
            this.f3141a.f3172r = true;
            this.f3141a.f3165k.m3286d(this.f3141a.e);
            CBLogging.m3097a("CBWebViewProtocol", "Webview seems to have some issues loading html, onRecievedError callback triggered");
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bf$b */
    public class C1432b extends C1402a {
        /* renamed from: c */
        public be f3144c;
        /* renamed from: d */
        public bd f3145d;
        /* renamed from: e */
        public RelativeLayout f3146e;
        /* renamed from: f */
        public RelativeLayout f3147f;
        /* renamed from: g */
        final /* synthetic */ bf f3148g;

        public C1432b(final bf bfVar, Context context, String str) {
            this.f3148g = bfVar;
            super(bfVar, context);
            setFocusable(false);
            C1405g a = C1405g.m3317a();
            this.f3146e = (RelativeLayout) a.m3318a(new RelativeLayout(context));
            this.f3147f = (RelativeLayout) a.m3318a(new RelativeLayout(context));
            this.f3144c = (be) a.m3318a(new be(context));
            C1409h.m3325a(context, this.f3144c, bfVar.f3166l);
            this.f3144c.setWebViewClient((WebViewClient) a.m3318a(new C1430a()));
            this.f3145d = (bd) a.m3318a(new bd(this.f3146e, this.f3147f, null, this.f3144c, bfVar, bfVar.a));
            this.f3144c.setWebChromeClient(this.f3145d);
            if (C1454s.m3627a().m3630a(19)) {
                be beVar = this.f3144c;
                be.setWebContentsDebuggingEnabled(true);
            }
            this.f3144c.loadDataWithBaseURL(bfVar.f3168n, str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
            this.f3146e.addView(this.f3144c);
            this.f3144c.getSettings().setSupportZoom(false);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            this.f3146e.setLayoutParams(layoutParams);
            this.f3144c.setLayoutParams(layoutParams);
            this.f3144c.setBackgroundColor(0);
            this.f3147f.setVisibility(8);
            this.f3147f.setLayoutParams(layoutParams);
            addView(this.f3146e);
            addView(this.f3147f);
            bfVar.f3170p = System.currentTimeMillis();
            if (context instanceof Activity) {
                bfVar.f3154F = ((Activity) context).getRequestedOrientation();
            } else {
                bfVar.f3154F = -1;
            }
            C1450o.m3612a(this.f3144c, bfVar.e.f2770p.f2747s);
            bfVar.a.postDelayed(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C1432b f3143b;

                public void run() {
                    if (!this.f3143b.f3148g.f3172r) {
                        CBLogging.m3097a("CBWebViewProtocol", "Webview seems to be taking more time loading the html content, so closing the view.");
                        this.f3143b.f3148g.m3298a(CBImpressionError.WEB_VIEW_PAGE_LOAD_TIMEOUT);
                    }
                }
            }, 3000);
        }

        /* renamed from: a */
        protected void mo4296a(int i, int i2) {
        }
    }

    /* renamed from: e */
    public /* synthetic */ C1402a mo4300e() {
        return m3521y();
    }

    public bf(C1388c c1388c, C1378f c1378f, ah ahVar, SharedPreferences sharedPreferences, C1391a c1391a, Handler handler, C1397c c1397c, C1399d c1399d) {
        super(c1388c, handler, c1397c);
        this.f3157I = c1378f;
        this.f3158J = ahVar;
        this.f3164j = c1391a;
        this.f3165k = c1399d;
        this.f3166l = sharedPreferences;
    }

    /* renamed from: b */
    protected C1402a mo4298b(Context context) {
        return new C1432b(this, context, this.f3159K);
    }

    /* renamed from: a */
    public boolean mo4297a(JSONObject jSONObject) {
        File file = this.f3157I.m3140d().f2696a;
        if (file == null) {
            CBLogging.m3099b("CBWebViewProtocol", "External Storage path is unavailable or media not mounted");
            m3298a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.f3168n = "file://" + file.getAbsolutePath() + "/";
        if (C1454s.m3627a().m3631a(this.e.f2770p.f2733e)) {
            CBLogging.m3099b("CBWebViewProtocol", "Invalid adId being passed in the response");
            m3298a(CBImpressionError.ERROR_DISPLAYING_VIEW);
            return false;
        }
        String str = this.e.f2769o;
        if (str == null) {
            CBLogging.m3099b("CBWebViewProtocol", "No html data found in memory");
            m3298a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.f3159K = str;
        return true;
    }

    /* renamed from: h */
    public void mo4301h() {
        super.mo4301h();
        m3514r();
    }

    /* renamed from: b */
    public void m3495b(String str) {
        if (this.e.f2770p.f2742n != null && !TextUtils.isEmpty(str)) {
            List<String> list = (List) this.e.f2770p.f2742n.get(str);
            if (list != null) {
                for (String str2 : list) {
                    if (!str2.isEmpty()) {
                        this.f3158J.m3371a(new ad("GET", str2, 2, null));
                        CBLogging.m3097a("CBWebViewProtocol", "###### Sending VAST Tracking Event: " + str2);
                    }
                }
            }
        }
    }

    /* renamed from: c */
    public void m3497c(String str) {
        this.f3164j.m3216a(this.e.f2755a.m3538a(this.e.f2770p.f2730b), this.e.f2767m, this.e.m3189o(), str);
    }

    /* renamed from: d */
    public void m3501d(String str) {
        String str2 = C1454s.m3627a().m3631a((CharSequence) str) ? "Unknown Webview error" : str;
        this.f3164j.m3220a(this.e.f2755a.m3538a(this.e.f2770p.f2730b), this.e.f2767m, this.e.m3189o(), str2, true);
        CBLogging.m3099b("CBWebViewProtocol", "Webview error occurred closing the webview" + str2);
        m3298a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
        mo4301h();
    }

    /* renamed from: e */
    public void m3503e(String str) {
        if (C1454s.m3627a().m3631a((CharSequence) str)) {
            str = "Unknown Webview warning message";
        }
        this.f3164j.m3225b(this.e.f2755a.m3538a(this.e.f2770p.f2730b), this.e.f2767m, this.e.m3189o(), str);
        CBLogging.m3103d("CBWebViewProtocol", "Webview warning occurred closing the webview" + str);
    }

    /* renamed from: o */
    void m3511o() {
        C1432b y = m3521y();
        if (y == null || !this.f3172r) {
            this.f3150B = this.f3178x;
            this.f3151C = this.f3179y;
            this.f3152D = this.f3180z;
            this.f3153E = this.f3149A;
            return;
        }
        int[] iArr = new int[2];
        y.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1] - this.f3177w;
        int width = y.getWidth();
        int height = y.getHeight();
        this.f3178x = i;
        this.f3179y = i2;
        this.f3180z = i + width;
        this.f3149A = height + i2;
        this.f3150B = this.f3178x;
        this.f3151C = this.f3179y;
        this.f3152D = this.f3180z;
        this.f3153E = this.f3149A;
    }

    /* renamed from: p */
    public String m3512p() {
        return C1377e.m3130a(C1377e.m3128a("allowOrientationChange", Boolean.valueOf(this.f3155G)), C1377e.m3128a("forceOrientation", m3489a(this.f3156H))).toString();
    }

    /* renamed from: a */
    public String m3489a(int i) {
        switch (i) {
            case -1:
                return ParametersKeys.ORIENTATION_NONE;
            case 0:
                return "landscape";
            case 1:
                return "portrait";
            default:
                return "error";
        }
    }

    /* renamed from: f */
    public int m3504f(String str) {
        if (str.equals("portrait")) {
            return 1;
        }
        if (str.equals("landscape")) {
            return 0;
        }
        return -1;
    }

    /* renamed from: c */
    public void m3498c(JSONObject jSONObject) {
        this.f3155G = jSONObject.optBoolean("allowOrientationChange", this.f3155G);
        this.f3156H = m3504f(jSONObject.optString("forceOrientation", m3489a(this.f3156H)));
        m3513q();
    }

    /* renamed from: q */
    void m3513q() {
        int i = 1;
        Activity b = this.b.m3255b();
        if (b != null && !CBUtility.m3112a(b)) {
            if (this.f3156H != 1) {
                if (this.f3156H == 0) {
                    i = 0;
                } else if (this.f3155G) {
                    i = -1;
                } else {
                    int i2;
                    if (b.getResources().getConfiguration().orientation == 1) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    if (i2 == 0) {
                        i = 0;
                    }
                }
            }
            b.setRequestedOrientation(i);
        }
    }

    /* renamed from: r */
    void m3514r() {
        Activity b = this.b.m3255b();
        if (b != null && !CBUtility.m3112a(b)) {
            if (b.getRequestedOrientation() != this.f3154F) {
                b.setRequestedOrientation(this.f3154F);
            }
            this.f3155G = true;
            this.f3156H = -1;
        }
    }

    /* renamed from: c */
    void m3496c(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f3173s = displayMetrics.widthPixels;
        this.f3174t = displayMetrics.heightPixels;
    }

    /* renamed from: d */
    void m3500d(Context context) {
        if (context instanceof Activity) {
            Window window = ((Activity) context).getWindow();
            Rect rect = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            this.f3177w = m3488a(window);
            if (this.f3173s == 0 || this.f3174t == 0) {
                m3496c(context);
            }
            int width = rect.width();
            int i = this.f3174t - this.f3177w;
            if (width != this.f3175u || i != this.f3176v) {
                this.f3175u = width;
                this.f3176v = i;
            }
        }
    }

    /* renamed from: a */
    int m3488a(Window window) {
        return window.findViewById(16908290).getTop();
    }

    /* renamed from: s */
    public String m3515s() {
        return C1377e.m3130a(C1377e.m3128a("width", Integer.valueOf(this.f3175u)), C1377e.m3128a("height", Integer.valueOf(this.f3176v))).toString();
    }

    /* renamed from: t */
    public String m3516t() {
        return C1377e.m3130a(C1377e.m3128a("width", Integer.valueOf(this.f3173s)), C1377e.m3128a("height", Integer.valueOf(this.f3174t))).toString();
    }

    /* renamed from: u */
    public String m3517u() {
        m3511o();
        return C1377e.m3130a(C1377e.m3128a("x", Integer.valueOf(this.f3178x)), C1377e.m3128a("y", Integer.valueOf(this.f3179y)), C1377e.m3128a("width", Integer.valueOf(this.f3180z)), C1377e.m3128a("height", Integer.valueOf(this.f3149A))).toString();
    }

    /* renamed from: v */
    public String m3518v() {
        m3511o();
        return C1377e.m3130a(C1377e.m3128a("x", Integer.valueOf(this.f3150B)), C1377e.m3128a("y", Integer.valueOf(this.f3151C)), C1377e.m3128a("width", Integer.valueOf(this.f3152D)), C1377e.m3128a("height", Integer.valueOf(this.f3153E))).toString();
    }

    /* renamed from: l */
    public boolean mo4304l() {
        if (!(this.f3163O == 2 && this.e.f2755a.f3209a == 1)) {
            mo4299d();
            mo4301h();
        }
        return true;
    }

    /* renamed from: m */
    public void mo4305m() {
        super.mo4305m();
        final C1432b y = m3521y();
        if (y != null && y.f3144c != null) {
            this.a.post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ bf f3138b;

                public void run() {
                    if (y.f3144c != null) {
                        y.f3144c.onResume();
                    }
                }
            });
            this.f3164j.m3228d(this.f3167m, this.e.m3189o());
        }
    }

    /* renamed from: n */
    public void mo4306n() {
        super.mo4306n();
        final C1432b y = m3521y();
        if (y != null && y.f3144c != null) {
            this.a.post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ bf f3140b;

                public void run() {
                    if (y.f3144c != null) {
                        y.f3144c.onPause();
                    }
                }
            });
            this.f3164j.m3230e(this.f3167m, this.e.m3189o());
        }
    }

    /* renamed from: w */
    public void m3519w() {
        if (this.f3169o <= 1) {
            this.e.m3179e();
            this.f3169o++;
        }
    }

    /* renamed from: d */
    public void mo4299d() {
        C1450o.m3616d();
        C1432b y = m3521y();
        if (y != null) {
            if (y.f3144c != null) {
                CBLogging.m3097a("CBWebViewProtocol", "Destroying the webview object and cleaning up the references");
                y.f3144c.destroy();
                y.f3144c = null;
            }
            if (y.f3145d != null) {
                y.f3145d = null;
            }
            if (y.f3146e != null) {
                y.f3146e = null;
            }
            if (y.f3147f != null) {
                y.f3147f = null;
            }
        }
        super.mo4299d();
    }

    /* renamed from: x */
    public void m3520x() {
        this.f3164j.m3226c(this.f3167m, this.e.m3189o());
    }

    /* renamed from: b */
    public void m3494b(int i) {
        this.f3163O = i;
    }

    /* renamed from: y */
    public C1432b m3521y() {
        return (C1432b) super.mo4300e();
    }

    /* renamed from: a */
    public void m3490a(float f) {
        this.f3161M = f;
    }

    /* renamed from: b */
    public void m3493b(float f) {
        this.f3160L = f;
    }

    /* renamed from: j */
    public float mo4302j() {
        return this.f3160L;
    }

    /* renamed from: k */
    public float mo4303k() {
        return this.f3161M;
    }

    /* renamed from: z */
    public void m3522z() {
        if (this.e.f2766l == 2 && !this.f3162N) {
            this.f3164j.m3228d("", this.e.m3189o());
            this.e.m3190p();
            this.f3162N = true;
            C1450o.m3615c();
        }
    }
}
