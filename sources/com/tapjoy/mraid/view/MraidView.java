package com.tapjoy.mraid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.sdk.constants.Constants.ForceClosePosition;
import com.tapjoy.TapjoyCache;
import com.tapjoy.TapjoyCachedAssetData;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.TapjoyHttpURLResponse;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyURLConnection;
import com.tapjoy.TapjoyUtil;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.mraid.controller.Abstract.Dimensions;
import com.tapjoy.mraid.controller.Abstract.PlayerProperties;
import com.tapjoy.mraid.controller.Utility;
import com.tapjoy.mraid.listener.MraidViewListener;
import com.tapjoy.mraid.listener.Player;
import com.tapjoy.mraid.util.MraidPlayer;
import com.tapjoy.mraid.util.Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

public class MraidView extends BasicWebView implements OnGlobalLayoutListener {
    public static final String ACTION_KEY = "action";
    public static final String DIMENSIONS = "expand_dimensions";
    public static final String EXPAND_URL = "expand_url";
    public static final int MRAID_ID = 102;
    public static final String PLAYER_PROPERTIES = "player_properties";
    /* renamed from: c */
    private static int[] f8348c = new int[]{16843039, 16843040};
    /* renamed from: d */
    private static final String[] f8349d = new String[]{".mp4", ".3gp", ".mpg"};
    /* renamed from: s */
    private static MraidPlayer f8350s;
    /* renamed from: A */
    private VideoView f8351A;
    /* renamed from: B */
    private CustomViewCallback f8352B;
    /* renamed from: C */
    private ProgressBar f8353C;
    /* renamed from: D */
    private Handler f8354D = new C30182(this);
    /* renamed from: E */
    private boolean f8355E;
    /* renamed from: a */
    WebViewClient f8356a = new C30193(this);
    /* renamed from: b */
    WebChromeClient f8357b = new C30234(this);
    /* renamed from: e */
    private customCloseState f8358e = customCloseState.UNKNOWN;
    /* renamed from: f */
    private boolean f8359f = false;
    /* renamed from: g */
    private boolean f8360g;
    /* renamed from: h */
    private Utility f8361h;
    /* renamed from: i */
    private float f8362i;
    /* renamed from: j */
    private int f8363j;
    /* renamed from: k */
    private boolean f8364k;
    /* renamed from: l */
    private int f8365l;
    /* renamed from: m */
    private int f8366m;
    /* renamed from: n */
    private int f8367n;
    /* renamed from: o */
    private int f8368o;
    /* renamed from: p */
    private PLACEMENT_TYPE f8369p;
    /* renamed from: q */
    private VIEW_STATE f8370q = VIEW_STATE.DEFAULT;
    /* renamed from: r */
    private MraidViewListener f8371r;
    /* renamed from: t */
    private int f8372t = 0;
    /* renamed from: u */
    private int f8373u = 0;
    /* renamed from: v */
    private Thread f8374v = null;
    /* renamed from: w */
    private boolean f8375w = false;
    /* renamed from: x */
    private int f8376x;
    /* renamed from: y */
    private Context f8377y;
    /* renamed from: z */
    private RelativeLayout f8378z;

    /* renamed from: com.tapjoy.mraid.view.MraidView$2 */
    class C30182 extends Handler {
        /* renamed from: a */
        final /* synthetic */ MraidView f8327a;

        C30182(MraidView mraidView) {
            this.f8327a = mraidView;
        }

        public final void handleMessage(Message msg) {
            Bundle data = msg.getData();
            String string;
            switch (msg.what) {
                case 1001:
                    switch (this.f8327a.f8370q) {
                        case DEFAULT:
                            if (this.f8327a.f8369p != PLACEMENT_TYPE.INLINE) {
                                MraidView.m8266f(this.f8327a);
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                case PointerIconCompat.TYPE_HELP /*1003*/:
                    this.f8327a.injectMraidJavaScript("window.mraidview.fireChangeEvent({ state: 'default' });");
                    this.f8327a.setVisibility(0);
                    break;
                case PointerIconCompat.TYPE_CELL /*1006*/:
                    this.f8327a.f8370q = VIEW_STATE.LEFT_BEHIND;
                    break;
                case PointerIconCompat.TYPE_CROSSHAIR /*1007*/:
                    this.f8327a.playVideoImpl(data);
                    break;
                case PointerIconCompat.TYPE_TEXT /*1008*/:
                    this.f8327a.playAudioImpl(data);
                    break;
                case PointerIconCompat.TYPE_VERTICAL_TEXT /*1009*/:
                    string = data.getString("message");
                    this.f8327a.injectMraidJavaScript("window.mraidview.fireErrorEvent(\"" + string + "\", \"" + data.getString("action") + "\")");
                    break;
                case PointerIconCompat.TYPE_ALIAS /*1010*/:
                    MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f8327a.getLayoutParams();
                    if (marginLayoutParams != null) {
                        this.f8327a.removeCloseImageButton();
                        marginLayoutParams.height = data.getInt("resize_height", marginLayoutParams.height);
                        marginLayoutParams.width = data.getInt("resize_width", marginLayoutParams.width);
                        string = "window.mraidview.fireChangeEvent({ state: '" + this.f8327a.getState() + "', size: { width: " + ((int) (((float) marginLayoutParams.width) / this.f8327a.f8362i)) + ", height: " + ((int) (((float) marginLayoutParams.height) / this.f8327a.f8362i)) + "}});";
                        TapjoyLog.m7126d("MRAIDView", "resize: injection: " + string);
                        this.f8327a.injectMraidJavaScript(string);
                        this.f8327a.requestLayout();
                        MraidView.m8263c(this.f8327a, data.getString("resize_customClosePostition"));
                        if (this.f8327a.f8369p != PLACEMENT_TYPE.INLINE && this.f8327a.f8358e == customCloseState.OPEN) {
                            this.f8327a.showCloseImageButton();
                        }
                    }
                    if (this.f8327a.f8371r != null) {
                        this.f8327a.f8371r.onResize();
                        break;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }

    /* renamed from: com.tapjoy.mraid.view.MraidView$3 */
    class C30193 extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ MraidView f8328a;

        C30193(MraidView mraidView) {
            this.f8328a = mraidView;
        }

        public final void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (this.f8328a.f8371r != null) {
                this.f8328a.f8371r.onPageStarted(view, url, favicon);
            }
        }

        public final void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            if (this.f8328a.f8371r != null) {
                this.f8328a.f8371r.onReceivedError(view, errorCode, description, failingUrl);
            }
            TapjoyLog.m7126d("MRAIDView", "error:" + description);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public final void onPageFinished(WebView view, String url) {
            if (this.f8328a.f8371r != null) {
                this.f8328a.f8371r.onPageFinished(view, url);
            }
            this.f8328a.f8365l = (int) (((float) this.f8328a.getHeight()) / this.f8328a.f8362i);
            this.f8328a.f8366m = (int) (((float) this.f8328a.getWidth()) / this.f8328a.f8362i);
            this.f8328a.f8361h.init(this.f8328a.f8362i);
            this.f8328a.createCloseImageButton();
            if (this.f8328a.f8369p == PLACEMENT_TYPE.INLINE) {
                this.f8328a.removeCloseImageButton();
            }
        }

        public final boolean shouldOverrideUrlLoading(WebView view, String url) {
            TapjoyLog.m7126d("MRAIDView", "shouldOverrideUrlLoading: " + url);
            if (this.f8328a.f8371r != null && this.f8328a.f8371r.shouldOverrideUrlLoading(view, url)) {
                return true;
            }
            Uri parse = Uri.parse(url);
            Intent intent;
            try {
                if (url.startsWith("mraid")) {
                    return super.shouldOverrideUrlLoading(view, url);
                }
                if (url.startsWith("tel:")) {
                    intent = new Intent("android.intent.action.DIAL", Uri.parse(url));
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                    this.f8328a.getContext().startActivity(intent);
                    return true;
                } else if (url.startsWith("mailto:")) {
                    intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                    this.f8328a.getContext().startActivity(intent);
                    return true;
                } else {
                    intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(parse);
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                    this.f8328a.getContext().startActivity(intent);
                    return true;
                }
            } catch (Exception e) {
                try {
                    intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(parse);
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                    this.f8328a.getContext().startActivity(intent);
                    return true;
                } catch (Exception e2) {
                    return false;
                }
            }
        }

        public final WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (TapjoyCache.getInstance() != null) {
                TapjoyCachedAssetData cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(url);
                if (cachedDataForURL == null) {
                    TapjoyLog.m7126d("MRAIDView", "No cached data for " + url);
                } else {
                    WebResourceResponse a = MraidView.m8259b(cachedDataForURL);
                    if (a != null) {
                        TapjoyLog.m7126d("MRAIDView", "Reading request for " + url + " from cache -- localPath: " + cachedDataForURL.getLocalFilePath());
                        return a;
                    }
                }
            }
            return super.shouldInterceptRequest(view, url);
        }

        public final void onLoadResource(WebView view, String url) {
        }
    }

    /* renamed from: com.tapjoy.mraid.view.MraidView$4 */
    class C30234 extends WebChromeClient {
        /* renamed from: a */
        final /* synthetic */ MraidView f8332a;

        /* renamed from: com.tapjoy.mraid.view.MraidView$4$1 */
        class C30201 implements OnPreparedListener {
            /* renamed from: a */
            final /* synthetic */ C30234 f8329a;

            C30201(C30234 c30234) {
                this.f8329a = c30234;
            }

            public final void onPrepared(MediaPlayer mp) {
                TapjoyLog.m7129i("MRAIDView", "** ON PREPARED **");
                TapjoyLog.m7129i("MRAIDView", "isPlaying: " + mp.isPlaying());
                if (!mp.isPlaying()) {
                    mp.start();
                }
            }
        }

        /* renamed from: com.tapjoy.mraid.view.MraidView$4$2 */
        class C30212 implements OnCompletionListener {
            /* renamed from: a */
            final /* synthetic */ C30234 f8330a;

            C30212(C30234 c30234) {
                this.f8330a = c30234;
            }

            public final void onCompletion(MediaPlayer mp) {
                TapjoyLog.m7129i("MRAIDView", "** ON COMPLETION **");
                this.f8330a.f8332a.videoViewCleanup();
            }
        }

        /* renamed from: com.tapjoy.mraid.view.MraidView$4$3 */
        class C30223 implements OnErrorListener {
            /* renamed from: a */
            final /* synthetic */ C30234 f8331a;

            C30223(C30234 c30234) {
                this.f8331a = c30234;
            }

            public final boolean onError(MediaPlayer mp, int what, int extra) {
                TapjoyLog.m7129i("MRAIDView", "** ON ERROR **");
                this.f8331a.f8332a.videoViewCleanup();
                return false;
            }
        }

        C30234(MraidView mraidView) {
            this.f8332a = mraidView;
        }

        public final boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            TapjoyLog.m7126d("MRAIDView", message);
            return false;
        }

        public final void onCloseWindow(WebView w) {
            super.onCloseWindow(w);
            MraidView.m8266f(this.f8332a);
        }

        public final void onShowCustomView(View view, CustomViewCallback callback) {
            TapjoyLog.m7126d("MRAIDView", "-- onShowCustomView --");
            super.onShowCustomView(view, callback);
            this.f8332a.f8352B = callback;
            if (view instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) view;
                if ((frameLayout.getFocusedChild() instanceof VideoView) && (this.f8332a.f8377y instanceof Activity)) {
                    Activity activity = (Activity) this.f8332a.f8377y;
                    this.f8332a.f8351A = (VideoView) frameLayout.getFocusedChild();
                    frameLayout.removeView(this.f8332a.f8351A);
                    if (this.f8332a.f8378z == null) {
                        this.f8332a.f8378z = new RelativeLayout(this.f8332a.f8377y);
                        this.f8332a.f8378z.setLayoutParams(new LayoutParams(-1, -1));
                        this.f8332a.f8378z.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(13);
                    this.f8332a.f8351A.setLayoutParams(layoutParams);
                    this.f8332a.f8353C = new ProgressBar(this.f8332a.f8377y, null, 16842874);
                    this.f8332a.f8353C.setVisibility(0);
                    layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams.addRule(13);
                    this.f8332a.f8353C.setLayoutParams(layoutParams);
                    this.f8332a.f8378z.addView(this.f8332a.f8351A);
                    this.f8332a.f8378z.addView(this.f8332a.f8353C);
                    activity.getWindow().addContentView(this.f8332a.f8378z, new LayoutParams(-1, -1));
                    new Thread(new C3030c(this.f8332a)).start();
                    this.f8332a.setVisibility(8);
                    this.f8332a.f8351A.setOnPreparedListener(new C30201(this));
                    this.f8332a.f8351A.setOnCompletionListener(new C30212(this));
                    this.f8332a.f8351A.setOnErrorListener(new C30223(this));
                    this.f8332a.f8351A.start();
                }
            }
        }

        public final void onHideCustomView() {
            super.onHideCustomView();
        }

        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (this.f8332a.f8371r != null) {
                return this.f8332a.f8371r.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* renamed from: com.tapjoy.mraid.view.MraidView$5 */
    class C30245 implements Player {
        /* renamed from: a */
        final /* synthetic */ MraidView f8333a;

        C30245(MraidView mraidView) {
            this.f8333a = mraidView;
        }

        public final void onPrepared() {
        }

        public final void onError() {
            onComplete();
        }

        public final void onComplete() {
            FrameLayout frameLayout = (FrameLayout) this.f8333a.getRootView().findViewById(101);
            ((ViewGroup) frameLayout.getParent()).removeView(frameLayout);
            this.f8333a.setVisibility(0);
        }
    }

    public enum Action {
        PLAY_AUDIO,
        PLAY_VIDEO
    }

    public enum PLACEMENT_TYPE {
        INLINE,
        INTERSTITIAL
    }

    public enum VIEW_STATE {
        DEFAULT,
        LEFT_BEHIND,
        OPENED
    }

    /* renamed from: com.tapjoy.mraid.view.MraidView$a */
    class C3026a extends AsyncTask {
        /* renamed from: a */
        TapjoyHttpURLResponse f8338a;
        /* renamed from: b */
        TapjoyURLConnection f8339b;
        /* renamed from: c */
        String f8340c;
        /* renamed from: d */
        final /* synthetic */ MraidView f8341d;

        private C3026a(MraidView mraidView) {
            this.f8341d = mraidView;
        }

        protected final /* synthetic */ Object doInBackground(Object[] objArr) {
            return m8247a((String[]) objArr);
        }

        /* renamed from: a */
        private Void m8247a(String... strArr) {
            this.f8340c = strArr[0];
            try {
                this.f8339b = new TapjoyURLConnection();
                this.f8338a = this.f8339b.getResponseFromURL(this.f8340c);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            try {
                if (this.f8338a.statusCode == 0 || this.f8338a.response == null) {
                    TapjoyLog.m7127e("MRAIDView", new TapjoyErrorMessage(ErrorType.NETWORK_ERROR, "Connection not properly established"));
                    if (this.f8341d.f8371r != null) {
                        this.f8341d.f8371r.onReceivedError(this.f8341d, 0, "Connection not properly established", this.f8340c);
                    }
                } else if (this.f8338a.statusCode != IronSourceConstants.OFFERWALL_AVAILABLE || this.f8338a.redirectURL == null || this.f8338a.redirectURL.length() <= 0) {
                    this.f8341d.loadDataWithBaseURL(this.f8340c, this.f8338a.response, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, this.f8340c);
                } else {
                    TapjoyLog.m7129i("MRAIDView", "302 redirectURL detected: " + this.f8338a.redirectURL);
                    this.f8341d.loadUrlStandard(this.f8338a.redirectURL);
                }
            } catch (Exception e) {
                TapjoyLog.m7131w("MRAIDView", "error in loadURL " + e);
                e.printStackTrace();
            }
        }
    }

    /* renamed from: com.tapjoy.mraid.view.MraidView$b */
    class C3027b implements Runnable {
        /* renamed from: a */
        final /* synthetic */ MraidView f8342a;

        public C3027b(MraidView mraidView) {
            this.f8342a = mraidView;
        }

        public final void run() {
            while (!this.f8342a.f8375w) {
                try {
                    Thread.sleep(250);
                    MraidView.m8273m(this.f8342a);
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: com.tapjoy.mraid.view.MraidView$c */
    class C3030c implements Runnable {
        /* renamed from: a */
        final /* synthetic */ MraidView f8346a;

        /* renamed from: com.tapjoy.mraid.view.MraidView$c$1 */
        class C30281 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C3030c f8343a;

            C30281(C3030c c3030c) {
                this.f8343a = c3030c;
            }

            public final void run() {
                if (this.f8343a.f8346a.f8353C != null) {
                    this.f8343a.f8346a.f8353C.setVisibility(8);
                }
                new Thread(new C3029a(this.f8343a)).start();
            }
        }

        /* renamed from: com.tapjoy.mraid.view.MraidView$c$a */
        class C3029a implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C3030c f8344a;
            /* renamed from: b */
            private boolean f8345b = false;

            public C3029a(C3030c c3030c) {
                this.f8344a = c3030c;
            }

            public final void run() {
                while (this.f8344a.f8346a.f8351A != null) {
                    try {
                        Thread.sleep(100);
                        if (this.f8345b != this.f8344a.f8346a.f8351A.isPlaying()) {
                            this.f8345b = this.f8344a.f8346a.f8351A.isPlaying();
                            this.f8344a.f8346a.loadUrl("javascript:try{Tapjoy.AdUnit.dispatchEvent('" + (this.f8345b ? "videoplay" : "videopause") + "')}catch(e){}");
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }

        public C3030c(MraidView mraidView) {
            this.f8346a = mraidView;
        }

        public final void run() {
            int i = 0;
            while (this.f8346a.f8351A != null && !this.f8346a.f8351A.isPlaying()) {
                try {
                    Thread.sleep(50);
                    i += 50;
                    if (i >= 10000) {
                        break;
                    }
                } catch (Exception e) {
                }
            }
            ((Activity) this.f8346a.f8377y).runOnUiThread(new C30281(this));
        }
    }

    public enum customCloseState {
        HIDDEN,
        OPEN,
        UNKNOWN
    }

    public MraidView(Context context, MraidViewListener listener) {
        super(context);
        setListener(listener);
        this.f8377y = context;
        initialize();
    }

    public void setListener(MraidViewListener listener) {
        this.f8371r = listener;
    }

    public void removeListener() {
        this.f8371r = null;
    }

    public MraidView(Context context) {
        super(context);
        this.f8377y = context;
        initialize();
    }

    public void setPlacementType(PLACEMENT_TYPE type) {
        if (type.equals(PLACEMENT_TYPE.INLINE) || type.equals(PLACEMENT_TYPE.INTERSTITIAL)) {
            this.f8369p = type;
        } else {
            TapjoyLog.m7126d("MRAIDView", "Incorrect placement type.");
        }
        if (!type.equals(PLACEMENT_TYPE.INLINE)) {
            return;
        }
        if (this.f8374v == null || !this.f8374v.isAlive()) {
            this.f8374v = new Thread(new C3027b(this));
            this.f8374v.start();
        }
    }

    public PLACEMENT_TYPE getPlacementType() {
        return this.f8369p;
    }

    public void createCloseImageButton() {
        injectMraidJavaScript("window.mraidview.createCss();");
        TapjoyLog.m7126d("MRAIDView", "Creating close button.");
    }

    public void removeCloseImageButton() {
        injectMraidJavaScript("document.getElementById(\"closeButton\").style.visibility=\"hidden\";");
        TapjoyLog.m7126d("MRAIDView", "Removing close button.");
        this.f8358e = customCloseState.HIDDEN;
    }

    public void showCloseImageButton() {
        injectMraidJavaScript("document.getElementById(\"closeButton\").style.visibility=\"visible\";");
        TapjoyLog.m7126d("MRAIDView", "Showing close button.");
        this.f8358e = customCloseState.OPEN;
    }

    public customCloseState getCloseButtonState() {
        return this.f8358e;
    }

    public boolean isMraid() {
        return this.f8359f;
    }

    public void setMaxSize(int w, int h) {
        this.f8361h.setMaxSize(w, h);
    }

    public void injectMraidJavaScript(String str) {
        if (str != null && this.f8359f) {
            loadUrl("javascript:" + str);
        }
    }

    public void loadUrl(final String url) {
        ((Activity) this.f8377y).runOnUiThread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ MraidView f8326b;

            public final void run() {
                if (!URLUtil.isValidUrl(url)) {
                    this.f8326b.loadDataWithBaseURL(null, "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Connection not Established</title></head><h2>Connection Not Properly Established</h2><body></body></html>", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                } else if (!url.startsWith("javascript:")) {
                    new C3026a().execute(new String[]{url});
                } else if (VERSION.SDK_INT >= 19) {
                    try {
                        super.evaluateJavascript(url.replaceFirst("javascript:", ""), null);
                    } catch (Exception e) {
                        TapjoyLog.m7128e("MRAIDView", "Exception in evaluateJavascript. Device not supported. " + e.toString());
                    }
                } else {
                    super.loadUrl(url);
                }
            }
        });
    }

    public void loadUrlStandard(String url) {
        super.loadUrl(url);
    }

    public boolean hasMraidTag(String html) {
        return Pattern.compile("<\\s*script[^>]+mraid\\.js").matcher(html).find();
    }

    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        if (data != null) {
            StringBuffer stringBuffer = new StringBuffer();
            int indexOf = data.indexOf("<html>");
            this.f8359f = false;
            int indexOf2 = data.indexOf("mraid.js");
            if (indexOf2 <= 0 || !hasMraidTag(data)) {
                stringBuffer.append(data);
            } else {
                this.f8359f = true;
                int i = indexOf2;
                while (i >= 0) {
                    if (data.substring(i, i + 7).equals("<script")) {
                        break;
                    }
                    i--;
                }
                i = indexOf2;
                int i2 = 0;
                while (i2 < data.length()) {
                    if (data.substring(indexOf2 + i2, (indexOf2 + i2) + 2).equalsIgnoreCase("/>")) {
                        indexOf2 = (indexOf2 + i2) + 2;
                        break;
                    } else if (data.substring(indexOf2 + i2, (indexOf2 + i2) + 9).equalsIgnoreCase("</script>")) {
                        indexOf2 = (indexOf2 + i2) + 9;
                        break;
                    } else {
                        i2++;
                    }
                }
                String str;
                if (indexOf < 0) {
                    TapjoyLog.m7126d("MRAIDView", "wrapping fragment");
                    stringBuffer.append("<html>");
                    stringBuffer.append("<head>");
                    stringBuffer.append("<meta name='viewport' content='user-scalable=no initial-scale=1.0' />");
                    stringBuffer.append("<title>Advertisement</title>");
                    stringBuffer.append("</head>");
                    stringBuffer.append("<body style=\"margin:0; padding:0; overflow:hidden; background-color:transparent;\">");
                    stringBuffer.append("<div align=\"center\"> ");
                    stringBuffer.append(data.substring(0, i));
                    stringBuffer.append("<script type=text/javascript>");
                    str = (String) TapjoyUtil.getResource("mraid.js");
                    if (str == null) {
                        str = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", getContext());
                    }
                    stringBuffer.append(str);
                    stringBuffer.append("</script>");
                    stringBuffer.append(data.substring(indexOf2));
                } else {
                    indexOf2 = data.indexOf("<head>");
                    if (indexOf2 != -1) {
                        str = (String) TapjoyUtil.getResource("mraid.js");
                        if (str == null) {
                            str = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", getContext());
                        }
                        stringBuffer.append(data.substring(0, indexOf2 + 6));
                        stringBuffer.append("<script type='text/javascript'>");
                        stringBuffer.append(str);
                        stringBuffer.append("</script>");
                        stringBuffer.append(data.substring(indexOf2 + 6));
                    }
                }
                TapjoyLog.m7126d("MRAIDView", "injected js/mraid.js");
            }
            super.loadDataWithBaseURL(baseUrl, stringBuffer.toString(), mimeType, encoding, historyUrl);
        }
    }

    public void clearView() {
        reset();
        super.clearView();
    }

    public void reset() {
        invalidate();
        this.f8361h.deleteOldAds();
        this.f8361h.stopAllListeners();
        LayoutParams layoutParams = getLayoutParams();
        if (this.f8355E) {
            layoutParams.height = this.f8367n;
            layoutParams.width = this.f8368o;
        }
        setVisibility(0);
        requestLayout();
    }

    public MraidView(Context context, AttributeSet set) {
        super(context, set);
        initialize();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(set, f8348c);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        if (dimensionPixelSize > 0 && dimensionPixelSize2 > 0) {
            this.f8361h.setMaxSize(dimensionPixelSize, dimensionPixelSize2);
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: b */
    private static WebResourceResponse m8259b(TapjoyCachedAssetData tapjoyCachedAssetData) {
        if (tapjoyCachedAssetData == null) {
            return null;
        }
        try {
            return new WebResourceResponse(tapjoyCachedAssetData.getMimeType(), "UTF-8", new FileInputStream(tapjoyCachedAssetData.getLocalFilePath()));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public boolean videoPlaying() {
        return this.f8351A != null;
    }

    public void videoViewCleanup() {
        if (this.f8378z != null) {
            ((ViewGroup) this.f8378z.getParent()).removeView(this.f8378z);
            this.f8378z.setVisibility(8);
            this.f8378z = null;
        }
        try {
            if (this.f8351A != null) {
                this.f8351A.stopPlayback();
            }
            if (this.f8352B != null) {
                this.f8352B.onCustomViewHidden();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f8351A = null;
        this.f8352B = null;
        if (this != null) {
            setVisibility(0);
        }
        loadUrl("javascript:try{Tapjoy.AdUnit.dispatchEvent('videoend')}catch(e){}");
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void initialize() {
        setPlacementType(PLACEMENT_TYPE.INTERSTITIAL);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.f8362i = displayMetrics.density;
        this.f8360g = false;
        this.f8361h = new Utility(this, getContext());
        addJavascriptInterface(this.f8361h, "MRAIDUtilityControllerBridge");
        setWebViewClient(this.f8356a);
        setWebChromeClient(this.f8357b);
        this.f8363j = getContentViewHeight();
        if (getViewTreeObserver() != null) {
            getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.f8372t = windowManager.getDefaultDisplay().getWidth();
        this.f8373u = windowManager.getDefaultDisplay().getHeight();
        if (getContext() instanceof Activity) {
            this.f8376x = ((Activity) getContext()).getRequestedOrientation();
        }
    }

    public void addJavascriptObject(Object obj, String name) {
        addJavascriptInterface(obj, name);
    }

    private int getContentViewHeight() {
        View findViewById = getRootView().findViewById(16908290);
        if (findViewById != null) {
            return findViewById.getHeight();
        }
        return -1;
    }

    public VIEW_STATE getViewState() {
        return this.f8370q;
    }

    public String getState() {
        return this.f8370q.toString().toLowerCase();
    }

    public void resizeOrientation(int width, int height, String customClosePosition, boolean allowOffScreen) {
        this.f8372t = width;
        this.f8373u = height;
        TapjoyLog.m7126d("MRAIDView", "resizeOrientation to dimensions: " + width + "x" + height);
        Message obtainMessage = this.f8354D.obtainMessage(PointerIconCompat.TYPE_ALIAS);
        Bundle bundle = new Bundle();
        bundle.putInt("resize_width", width);
        bundle.putInt("resize_height", height);
        bundle.putBoolean("resize_allowOffScreen", allowOffScreen);
        bundle.putString("resize_customClosePostition", customClosePosition);
        obtainMessage.setData(bundle);
        this.f8354D.sendMessage(obtainMessage);
    }

    public void close() {
        this.f8354D.sendEmptyMessage(1001);
    }

    public void show() {
        this.f8354D.sendEmptyMessage(PointerIconCompat.TYPE_HELP);
    }

    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getContext().getSystemService("connectivity");
    }

    public void open(String url, boolean back, boolean forward, boolean refresh) {
        boolean z;
        String str = null;
        if (m8257a(url)) {
            str = url;
            z = true;
        } else {
            TapjoyHttpURLResponse redirectFromURL = new TapjoyURLConnection().getRedirectFromURL(url);
            TapjoyLog.m7129i("MRAIDView", "redirect: " + redirectFromURL.redirectURL + ", " + redirectFromURL.statusCode);
            if (redirectFromURL == null || redirectFromURL.redirectURL == null || redirectFromURL.redirectURL.length() <= 0 || !m8257a(redirectFromURL.redirectURL)) {
                z = false;
            } else {
                str = redirectFromURL.redirectURL;
                z = true;
            }
        }
        if (z) {
            Dimensions dimensions = new Dimensions();
            dimensions.f8251x = 0;
            dimensions.f8252y = 0;
            dimensions.width = getWidth();
            dimensions.height = getHeight();
            playVideo(str, false, true, true, false, dimensions, Abstract.FULL_SCREEN, Abstract.EXIT);
            return;
        }
        TapjoyLog.m7126d("MRAIDView", "Mraid Browser open:" + url);
        Intent intent = new Intent(getContext(), Browser.class);
        intent.putExtra(Browser.URL_EXTRA, url);
        intent.putExtra(Browser.SHOW_BACK_EXTRA, back);
        intent.putExtra(Browser.SHOW_FORWARD_EXTRA, forward);
        intent.putExtra(Browser.SHOW_REFRESH_EXTRA, refresh);
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        getContext().startActivity(intent);
    }

    /* renamed from: a */
    private static boolean m8257a(String str) {
        for (String endsWith : f8349d) {
            if (str.endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    public void setOrientationProperties(boolean allowOrientationChange, String forceOrientation) {
        int i;
        if (allowOrientationChange) {
            i = -1;
        } else {
            i = forceOrientation.equals("landscape") ? 0 : 1;
        }
        ((Activity) getContext()).setRequestedOrientation(i);
    }

    public void openMap(String POI, boolean fullscreen) {
        TapjoyLog.m7126d("MRAIDView", "Opening Map Url " + POI);
        POI = Utils.convert(POI.trim());
        if (fullscreen) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(POI));
                intent.setFlags(DriveFile.MODE_READ_ONLY);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void playAudioImpl(Bundle data) {
        PlayerProperties playerProperties = (PlayerProperties) data.getParcelable(PLAYER_PROPERTIES);
        String string = data.getString(EXPAND_URL);
        View player = getPlayer();
        player.setPlayData(playerProperties, string);
        player.setLayoutParams(new LayoutParams(1, 1));
        ((ViewGroup) getParent()).addView(player);
        player.playAudio();
    }

    public void playAudio(String url, boolean autoPlay, boolean controls, boolean loop, boolean position, String startStyle, String stopStyle) {
        Object playerProperties = new PlayerProperties();
        playerProperties.setProperties(false, autoPlay, controls, position, loop, startStyle, stopStyle);
        Bundle bundle = new Bundle();
        bundle.putString("action", Action.PLAY_AUDIO.toString());
        bundle.putString(EXPAND_URL, url);
        bundle.putParcelable(PLAYER_PROPERTIES, playerProperties);
        if (playerProperties.isFullScreen()) {
            try {
                Intent intent = new Intent(getContext(), ActionHandler.class);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                return;
            }
        }
        Message obtainMessage = this.f8354D.obtainMessage(PointerIconCompat.TYPE_TEXT);
        obtainMessage.setData(bundle);
        this.f8354D.sendMessage(obtainMessage);
    }

    public void playVideoImpl(Bundle data) {
        PlayerProperties playerProperties = (PlayerProperties) data.getParcelable(PLAYER_PROPERTIES);
        Dimensions dimensions = (Dimensions) data.getParcelable(DIMENSIONS);
        String string = data.getString(EXPAND_URL);
        View player = getPlayer();
        player.setPlayData(playerProperties, string);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensions.width, dimensions.height);
        layoutParams.topMargin = dimensions.f8251x;
        layoutParams.leftMargin = dimensions.f8252y;
        player.setLayoutParams(layoutParams);
        View frameLayout = new FrameLayout(getContext());
        frameLayout.setId(101);
        frameLayout.setPadding(dimensions.f8251x, dimensions.f8252y, 0, 0);
        ((FrameLayout) getRootView().findViewById(16908290)).addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(player);
        setVisibility(4);
        player.setListener(new C30245(this));
        player.playVideo();
    }

    public void playVideo(String url, boolean audioMuted, boolean autoPlay, boolean controls, boolean loop, Dimensions d, String startStyle, String stopStyle) {
        Message obtainMessage = this.f8354D.obtainMessage(PointerIconCompat.TYPE_CROSSHAIR);
        Object playerProperties = new PlayerProperties();
        playerProperties.setProperties(audioMuted, autoPlay, controls, false, loop, startStyle, stopStyle);
        Bundle bundle = new Bundle();
        bundle.putString(EXPAND_URL, url);
        bundle.putString("action", Action.PLAY_VIDEO.toString());
        bundle.putParcelable(PLAYER_PROPERTIES, playerProperties);
        if (d != null) {
            bundle.putParcelable(DIMENSIONS, d);
        }
        if (playerProperties.isFullScreen()) {
            try {
                Intent intent = new Intent(getContext(), ActionHandler.class);
                intent.putExtras(bundle);
                intent.setFlags(DriveFile.MODE_READ_ONLY);
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else if (d != null) {
            obtainMessage.setData(bundle);
            this.f8354D.sendMessage(obtainMessage);
        }
    }

    public boolean isPageFinished() {
        return this.f8360g;
    }

    public void onGlobalLayout() {
        boolean z = this.f8364k;
        if (!this.f8364k && this.f8363j >= 0 && getContentViewHeight() >= 0 && this.f8363j != getContentViewHeight()) {
            z = true;
            injectMraidJavaScript("window.mraidview.fireChangeEvent({ keyboardState: true});");
        }
        if (this.f8364k && this.f8363j >= 0 && getContentViewHeight() >= 0 && this.f8363j == getContentViewHeight()) {
            z = false;
            injectMraidJavaScript("window.mraidview.fireChangeEvent({ keyboardState: false});");
        }
        if (this.f8363j < 0) {
            this.f8363j = getContentViewHeight();
        }
        this.f8364k = z;
    }

    public String getSize() {
        return "{ width: " + ((int) Math.ceil((double) (((float) getWidth()) / this.f8362i))) + ", height: " + ((int) Math.ceil((double) (((float) getHeight()) / this.f8362i))) + "}";
    }

    protected void onAttachedToWindow() {
        if (!this.f8355E) {
            LayoutParams layoutParams = getLayoutParams();
            this.f8367n = layoutParams.height;
            this.f8368o = layoutParams.width;
            this.f8355E = true;
        }
        this.f8375w = false;
        if (this.f8374v == null || !this.f8374v.isAlive()) {
            this.f8374v = new Thread(new C3027b(this));
            this.f8374v.start();
        }
        super.onAttachedToWindow();
    }

    public WebBackForwardList saveState(Bundle outState) {
        return super.saveState(outState);
    }

    public WebBackForwardList restoreState(Bundle savedInstanceState) {
        return super.restoreState(savedInstanceState);
    }

    public void raiseError(String strMsg, String action) {
        Message obtainMessage = this.f8354D.obtainMessage(PointerIconCompat.TYPE_VERTICAL_TEXT);
        Bundle bundle = new Bundle();
        bundle.putString("message", strMsg);
        bundle.putString("action", action);
        obtainMessage.setData(bundle);
        this.f8354D.sendMessage(obtainMessage);
    }

    protected void onDetachedFromWindow() {
        this.f8375w = true;
        this.f8361h.stopAllListeners();
        try {
            if (this.f8351A != null) {
                this.f8351A.stopPlayback();
            }
            if (this.f8352B != null) {
                this.f8352B.onCustomViewHidden();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDetachedFromWindow();
    }

    MraidPlayer getPlayer() {
        if (f8350s != null) {
            f8350s.releasePlayer();
        }
        MraidPlayer mraidPlayer = new MraidPlayer(getContext());
        f8350s = mraidPlayer;
        return mraidPlayer;
    }

    public void setContext(Context context) {
        this.f8377y = context;
    }

    /* renamed from: c */
    static /* synthetic */ void m8263c(MraidView mraidView, String str) {
        if (str != null) {
            String str2 = null;
            if (str.equals(ForceClosePosition.TOP_RIGHT)) {
                str2 = "document.getElementById(\"closeButton\").style.right = 1;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = mraid.getSize().width -36";
            } else if (str.equals("top-center")) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 - 18;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = mraid.getSize().width/2 -18";
            } else if (str.equals(ForceClosePosition.TOP_LEFT)) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = 1";
            } else if (str.equals("center")) {
                str2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 - 18;document.getElementById(\"closeButton\").style.top = mraid.getSize().height/2 -18;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height/2 -18;document.getElementById(\"closeButton\").style.left = mraid.getSize().width/2 -18";
            } else if (str.equals(ForceClosePosition.BOTTOM_RIGHT)) {
                str2 = "document.getElementById(\"closeButton\").style.right = 1;document.getElementById(\"closeButton\").style.top = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.left = mraid.getSize().width -36";
            } else if (str.equals(ForceClosePosition.BOTTOM_LEFT)) {
                str2 = "document.getElementById(\"closeButton\").style.left = 1;document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36;document.getElementById(\"closeButton\").style.top = mraid.getSize().height-36;";
            } else if (str.equals("bottom-center")) {
                str2 = "document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 -18;document.getElementById(\"closeButton\").style.top = mraid.getSize().height-36;";
            }
            if (str2 != null) {
                mraidView.injectMraidJavaScript(str2);
            } else {
                TapjoyLog.m7126d("MRAIDView", "Reposition of close button failed.");
            }
        }
    }

    /* renamed from: f */
    static /* synthetic */ void m8266f(MraidView mraidView) {
        try {
            if (mraidView.f8371r != null) {
                mraidView.f8371r.onClose();
            }
            ((ViewGroup) mraidView.getParent()).removeView(mraidView);
        } catch (Exception e) {
        }
    }

    /* renamed from: m */
    static /* synthetic */ void m8273m(MraidView mraidView) {
        WindowManager windowManager = (WindowManager) mraidView.getContext().getSystemService("window");
        int width = windowManager.getDefaultDisplay().getWidth();
        int height = windowManager.getDefaultDisplay().getHeight();
        if (!(width == mraidView.f8372t && height == mraidView.f8373u) && mraidView.getPlacementType() == PLACEMENT_TYPE.INTERSTITIAL) {
            mraidView.resizeOrientation(width, height, ForceClosePosition.TOP_RIGHT, true);
        }
    }
}
