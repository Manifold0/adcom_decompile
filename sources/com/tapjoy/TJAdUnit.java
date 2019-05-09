package com.tapjoy;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.webkit.ConsoleMessage;
import android.webkit.WebView;
import android.widget.VideoView;
import com.applovin.sdk.AppLovinSdk;
import com.facebook.ads.AudienceNetworkActivity;
import com.tapjoy.TJAdUnitJSBridge.AdUnitAsyncTaskListner;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.ct;
import com.tapjoy.internal.ep;
import com.tapjoy.internal.et;
import com.tapjoy.internal.fi;
import com.tapjoy.internal.gq;
import com.tapjoy.mraid.listener.MraidViewListener;
import com.tapjoy.mraid.view.BasicWebView;
import com.tapjoy.mraid.view.MraidView;
import com.tonyodev.fetch.FetchConst;
import com.unity.purchasing.googleplay.IabHelper;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class TJAdUnit implements OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener {
    public static TJVideoListener publisherVideoListener;
    /* renamed from: A */
    private boolean f6788A;
    /* renamed from: B */
    private boolean f6789B;
    /* renamed from: C */
    private ep f6790C;
    /* renamed from: D */
    private final Runnable f6791D = new C27771(this);
    /* renamed from: E */
    private final Runnable f6792E = new C27826(this);
    /* renamed from: F */
    private final Runnable f6793F = new C27837(this);
    /* renamed from: a */
    TJAdUnitJSBridge f6794a;
    /* renamed from: b */
    BasicWebView f6795b;
    /* renamed from: c */
    MraidView f6796c;
    /* renamed from: d */
    VideoView f6797d;
    /* renamed from: e */
    volatile boolean f6798e;
    /* renamed from: f */
    private final Handler f6799f = new Handler(Looper.getMainLooper());
    /* renamed from: g */
    private TJAdUnitWebViewListener f6800g;
    /* renamed from: h */
    private TJAdUnitVideoListener f6801h;
    /* renamed from: i */
    private TJAdUnitActivity f6802i;
    /* renamed from: j */
    private MediaPlayer f6803j;
    /* renamed from: k */
    private int f6804k;
    /* renamed from: l */
    private boolean f6805l;
    /* renamed from: m */
    private boolean f6806m;
    /* renamed from: n */
    private boolean f6807n;
    /* renamed from: o */
    private ScheduledFuture f6808o;
    /* renamed from: p */
    private AudioManager f6809p;
    /* renamed from: q */
    private int f6810q = 0;
    /* renamed from: r */
    private int f6811r;
    /* renamed from: s */
    private boolean f6812s;
    /* renamed from: t */
    private boolean f6813t;
    /* renamed from: u */
    private boolean f6814u;
    /* renamed from: v */
    private boolean f6815v;
    /* renamed from: w */
    private boolean f6816w;
    /* renamed from: x */
    private boolean f6817x;
    /* renamed from: y */
    private boolean f6818y;
    /* renamed from: z */
    private int f6819z = -1;

    /* renamed from: com.tapjoy.TJAdUnit$1 */
    class C27771 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TJAdUnit f6765a;

        C27771(TJAdUnit tJAdUnit) {
            this.f6765a = tJAdUnit;
        }

        public final void run() {
            int streamVolume = this.f6765a.f6809p.getStreamVolume(3);
            if (this.f6765a.f6810q != streamVolume) {
                this.f6765a.f6810q = streamVolume;
                this.f6765a.f6794a.onVolumeChanged();
            }
        }
    }

    /* renamed from: com.tapjoy.TJAdUnit$6 */
    class C27826 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TJAdUnit f6779a;

        C27826(TJAdUnit tJAdUnit) {
            this.f6779a = tJAdUnit;
        }

        public final void run() {
            if (this.f6779a.f6797d.getCurrentPosition() != 0) {
                if (!this.f6779a.f6806m) {
                    this.f6779a.f6806m = true;
                }
                this.f6779a.f6794a.onVideoStarted(this.f6779a.f6804k);
                this.f6779a.f6793F.run();
            } else if (this.f6779a.f6788A) {
                this.f6779a.f6789B = true;
            } else {
                this.f6779a.f6799f.postDelayed(this.f6779a.f6792E, 200);
            }
        }
    }

    /* renamed from: com.tapjoy.TJAdUnit$7 */
    class C27837 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TJAdUnit f6780a;

        C27837(TJAdUnit tJAdUnit) {
            this.f6780a = tJAdUnit;
        }

        public final void run() {
            this.f6780a.f6794a.onVideoProgress(this.f6780a.f6797d.getCurrentPosition());
            this.f6780a.f6799f.postDelayed(this.f6780a.f6793F, 500);
        }
    }

    public interface TJAdUnitVideoListener {
        void onVideoCompleted();

        void onVideoError(String str);

        void onVideoStart();
    }

    public interface TJAdUnitWebViewListener {
        void onClosed();

        void onContentReady();
    }

    /* renamed from: com.tapjoy.TJAdUnit$a */
    class C2786a implements MraidViewListener {
        /* renamed from: a */
        final /* synthetic */ TJAdUnit f6787a;

        private C2786a(TJAdUnit tJAdUnit) {
            this.f6787a = tJAdUnit;
        }

        public final boolean onResizeClose() {
            return false;
        }

        public final boolean onResize() {
            return false;
        }

        public final boolean onReady() {
            return false;
        }

        public final boolean onEventFired() {
            return false;
        }

        public final boolean onClose() {
            TJAdUnitActivity r = this.f6787a.f6802i;
            if (r != null) {
                r.handleClose();
            }
            return false;
        }

        @TargetApi(8)
        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            int i = 0;
            if (this.f6787a.f6794a.closeRequested) {
                String[] strArr = new String[]{"Uncaught", "uncaught", "Error", "error", "not defined"};
                TapjoyLog.m7126d("TJAdUnit", "shouldClose...");
                TJAdUnitActivity r = this.f6787a.f6802i;
                if (r != null) {
                    while (i < 5) {
                        if (consoleMessage.message().contains(strArr[i])) {
                            r.handleClose();
                            break;
                        }
                        i++;
                    }
                }
            }
            return true;
        }

        public final void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            TJAdUnitActivity r = this.f6787a.f6802i;
            if (r != null) {
                r.showErrorDialog();
            }
        }

        public final void onPageStarted(WebView view, String url, Bitmap favicon) {
            TapjoyLog.m7126d("TJAdUnit", "onPageStarted: " + url);
            if (this.f6787a.f6794a != null) {
                this.f6787a.f6794a.allowRedirect = true;
                this.f6787a.f6794a.customClose = false;
                this.f6787a.f6794a.closeRequested = false;
                this.f6787a.m6992a();
            }
        }

        public final void onPageFinished(WebView view, String url) {
            TapjoyLog.m7126d("TJAdUnit", "onPageFinished: " + url);
            if (!(this.f6787a == null || this.f6787a.f6796c == null || !this.f6787a.f6796c.isMraid())) {
                this.f6787a.f6794a.allowRedirect = false;
            }
            TJAdUnitActivity r = this.f6787a.f6802i;
            if (r != null) {
                r.setProgressSpinnerVisibility(false);
            }
            this.f6787a.f6818y = true;
            if (this.f6787a.f6815v) {
                this.f6787a.f6794a.display();
            }
            this.f6787a.f6794a.flushMessageQueue();
        }

        @TargetApi(9)
        public final boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (m6988a()) {
                boolean z;
                TapjoyLog.m7126d("TJAdUnit", "interceptURL: " + url);
                if (this.f6787a == null || this.f6787a.f6796c == null || !this.f6787a.f6796c.isMraid() || !url.contains("mraid")) {
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    return false;
                }
                if (C2786a.m6989a(url)) {
                    TapjoyLog.m7126d("TJAdUnit", "Open redirecting URL:" + url);
                    ((MraidView) view).loadUrlStandard(url);
                    return true;
                } else if (this.f6787a.f6794a.allowRedirect) {
                    return false;
                } else {
                    view.loadUrl(url);
                    return true;
                }
            }
            TJAdUnitActivity r = this.f6787a.f6802i;
            if (r == null) {
                return true;
            }
            r.showErrorDialog();
            return true;
        }

        /* renamed from: a */
        private static boolean m6989a(String str) {
            try {
                CharSequence host = new URL(TapjoyConfig.TJC_SERVICE_URL).getHost();
                if ((host != null && str.contains(host)) || str.contains(TapjoyConnectCore.getRedirectDomain()) || str.contains(TapjoyUtil.getRedirectDomain(TapjoyConnectCore.getPlacementURL()))) {
                    return true;
                }
                return false;
            } catch (MalformedURLException e) {
                return false;
            }
        }

        /* renamed from: a */
        private boolean m6988a() {
            try {
                NetworkInfo activeNetworkInfo = this.f6787a.f6796c.getConnectivityManager().getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public boolean preload(TJPlacementData placementData, Context context) {
        if (this.f6816w || !placementData.isPrerenderingRequested() || !TJPlacementManager.canPreRenderPlacement() || TapjoyConnectCore.isViewOpen()) {
            fireContentReady();
            return false;
        }
        TapjoyLog.m7129i("TJAdUnit", "Pre-rendering ad unit for placement: " + placementData.getPlacementName());
        TJPlacementManager.incrementPlacementPreRenderCount();
        load(placementData, true, context);
        return true;
    }

    public void load(final TJPlacementData placementData, final boolean shouldPrerenderContent, final Context context) {
        this.f6816w = false;
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ TJAdUnit f6769d;

            public final void run() {
                TJAdUnit tJAdUnit = this.f6769d;
                Context context = context;
                if (!(Looper.myLooper() != Looper.getMainLooper() || tJAdUnit.f6798e || context == null)) {
                    TapjoyLog.m7126d("TJAdUnit", "Constructing ad unit");
                    tJAdUnit.f6798e = true;
                    tJAdUnit.f6795b = new BasicWebView(context);
                    tJAdUnit.f6795b.loadDataWithBaseURL(null, "<!DOCTYPE html><html><head><title>Tapjoy Background Webview</title></head></html>", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                    tJAdUnit.f6796c = new MraidView(context);
                    tJAdUnit.f6796c.setListener(new C2786a());
                    tJAdUnit.f6797d = new VideoView(context);
                    tJAdUnit.f6797d.setOnCompletionListener(tJAdUnit);
                    tJAdUnit.f6797d.setOnErrorListener(tJAdUnit);
                    tJAdUnit.f6797d.setOnPreparedListener(tJAdUnit);
                    tJAdUnit.f6797d.setVisibility(4);
                    tJAdUnit.f6794a = new TJAdUnitJSBridge(context, tJAdUnit);
                    if (context instanceof TJAdUnitActivity) {
                        tJAdUnit.setAdUnitActivity((TJAdUnitActivity) context);
                    }
                }
                if (tJAdUnit.f6798e) {
                    boolean z;
                    TapjoyLog.m7129i("TJAdUnit", "Loading ad unit content");
                    this.f6769d.f6816w = true;
                    if (ct.m7339c(placementData.getRedirectURL())) {
                        if (placementData.getBaseURL() == null || placementData.getHttpResponse() == null) {
                            TapjoyLog.m7127e("TJAdUnit", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error loading ad unit content"));
                            this.f6769d.f6816w = false;
                        } else {
                            this.f6769d.f6796c.loadDataWithBaseURL(placementData.getBaseURL(), placementData.getHttpResponse(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                        }
                    } else if (placementData.isPreloadDisabled()) {
                        this.f6769d.f6796c.postUrl(placementData.getRedirectURL(), null);
                    } else {
                        this.f6769d.f6796c.loadUrl(placementData.getRedirectURL());
                    }
                    TJAdUnit tJAdUnit2 = this.f6769d;
                    if (this.f6769d.f6816w && shouldPrerenderContent) {
                        z = true;
                    } else {
                        z = false;
                    }
                    tJAdUnit2.f6817x = z;
                }
            }
        });
    }

    public void resume(TJAdUnitSaveStateData saveStateData) {
        if (this.f6794a.didLaunchOtherActivity) {
            TapjoyLog.m7126d("TJAdUnit", "onResume bridge.didLaunchOtherActivity callbackID: " + this.f6794a.otherActivityCallbackID);
            this.f6794a.invokeJSCallback(this.f6794a.otherActivityCallbackID, Boolean.TRUE);
            this.f6794a.didLaunchOtherActivity = false;
        }
        this.f6788A = false;
        this.f6794a.setEnabled(true);
        if (saveStateData != null) {
            this.f6804k = saveStateData.seekTime;
            this.f6797d.seekTo(this.f6804k);
            if (this.f6803j != null) {
                this.f6812s = saveStateData.isVideoMuted;
            }
        }
        if (this.f6789B) {
            this.f6789B = false;
            this.f6799f.postDelayed(this.f6792E, 200);
        }
    }

    public void pause() {
        this.f6788A = true;
        this.f6794a.setEnabled(false);
        pauseVideo();
    }

    public void invokeBridgeCallback(String callbackID, Object... argArray) {
        if (this.f6794a != null && callbackID != null) {
            this.f6794a.invokeJSCallback(callbackID, argArray);
        }
    }

    public void destroy() {
        this.f6794a.destroy();
        if (this.f6795b != null) {
            this.f6795b.removeAllViews();
            this.f6795b = null;
        }
        if (this.f6796c != null) {
            this.f6796c.removeAllViews();
            this.f6796c = null;
        }
        m6996b();
        this.f6798e = false;
        this.f6815v = false;
        setAdUnitActivity(null);
        m6992a();
        this.f6803j = null;
        if (this.f6800g != null) {
            this.f6800g.onClosed();
        }
        resetContentLoadState();
    }

    public void resetContentLoadState() {
        this.f6816w = false;
        this.f6818y = false;
        this.f6817x = false;
        this.f6819z = -1;
        this.f6814u = false;
        this.f6812s = false;
    }

    public void setVisible(boolean visible) {
        this.f6815v = visible;
        if (this.f6815v && this.f6818y) {
            this.f6794a.display();
        }
    }

    public void fireContentReady() {
        if (this.f6800g != null) {
            this.f6800g.onContentReady();
        }
    }

    public void closeRequested(boolean shouldForceClose) {
        if (this.f6796c == null || !this.f6796c.videoPlaying()) {
            this.f6794a.closeRequested(Boolean.valueOf(shouldForceClose));
        } else {
            this.f6796c.videoViewCleanup();
        }
    }

    public void setOrientation(int requestedOrientation) {
        int i = 0;
        TJAdUnitActivity tJAdUnitActivity = this.f6802i;
        if (tJAdUnitActivity != null) {
            TJAdUnitActivity tJAdUnitActivity2 = this.f6802i;
            if (tJAdUnitActivity2 != null) {
                int rotation = tJAdUnitActivity2.getWindowManager().getDefaultDisplay().getRotation();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                tJAdUnitActivity2.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int i2 = displayMetrics.widthPixels;
                int i3 = displayMetrics.heightPixels;
                if (((rotation != 0 && rotation != 2) || i3 <= i2) && ((rotation != 1 && rotation != 3) || i2 <= i3)) {
                    switch (rotation) {
                        case 0:
                            break;
                        case 1:
                            i = 1;
                            break;
                        case 2:
                            i = 8;
                            break;
                        case 3:
                            i = 9;
                            break;
                        default:
                            TapjoyLog.m7131w("TJAdUnit", "Unknown screen orientation. Defaulting to landscape.");
                            break;
                    }
                }
                switch (rotation) {
                    case 0:
                        i = 1;
                        break;
                    case 1:
                        break;
                    case 2:
                        i = 9;
                        break;
                    case 3:
                        i = 8;
                        break;
                    default:
                        i = 1;
                        break;
                }
            }
            i = -1;
            if (this.f6819z != -1) {
                i = this.f6819z;
            }
            if ((m6993a(i) && m6993a(requestedOrientation)) || (m6997b(i) && m6997b(requestedOrientation))) {
                requestedOrientation = this.f6819z;
            }
            tJAdUnitActivity.setRequestedOrientation(requestedOrientation);
            this.f6819z = requestedOrientation;
            this.f6814u = true;
        }
    }

    public void unsetOrientation() {
        TJAdUnitActivity tJAdUnitActivity = this.f6802i;
        if (tJAdUnitActivity != null) {
            tJAdUnitActivity.setRequestedOrientation(-1);
        }
        this.f6819z = -1;
        this.f6814u = false;
    }

    /* renamed from: a */
    private void m6992a() {
        TapjoyLog.m7126d("TJAdUnit", "detachVolumeListener");
        if (this.f6808o != null) {
            this.f6808o.cancel(false);
            this.f6808o = null;
        }
        this.f6809p = null;
    }

    /* renamed from: a */
    private static boolean m6993a(int i) {
        return i == 0 || i == 8 || i == 6 || i == 11;
    }

    /* renamed from: b */
    private static boolean m6997b(int i) {
        return i == 1 || i == 9 || i == 7 || i == 12;
    }

    public void setAdUnitActivity(TJAdUnitActivity activity) {
        this.f6802i = activity;
        if (this.f6796c != null) {
            this.f6796c.setContext(this.f6802i);
        }
        if (this.f6794a != null) {
            this.f6794a.setAdUnitActivity(this.f6802i);
        }
    }

    public void setAdContentTracker(ep adContentTracker) {
        this.f6790C = adContentTracker;
    }

    public void setBackgroundColor(final String hexColor, final AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ TJAdUnit f6772c;

            public final void run() {
                try {
                    TapjoyLog.m7126d("TJAdUnit", "setBackgroundColor: " + hexColor);
                    this.f6772c.f6795b.setBackgroundColor(Color.parseColor(hexColor));
                    adUnitAsyncTaskListner.onComplete(true);
                } catch (Exception e) {
                    TapjoyLog.m7126d("TJAdUnit", "Error setting background color. backgroundWebView: " + this.f6772c.f6795b + ", hexColor: " + hexColor);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }

    public void setBackgroundContent(final String backgroundHTML, final AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ TJAdUnit f6775c;

            public final void run() {
                try {
                    TapjoyLog.m7126d("TJAdUnit", "setBackgroundContent: " + backgroundHTML);
                    this.f6775c.f6795b.loadDataWithBaseURL(null, backgroundHTML, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                    adUnitAsyncTaskListner.onComplete(true);
                } catch (Exception e) {
                    TapjoyLog.m7126d("TJAdUnit", "Error setting background content. backgroundWebView: " + this.f6775c.f6795b + ", content: " + backgroundHTML);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }

    public void setWebViewListener(TJAdUnitWebViewListener adUnitWebViewListener) {
        this.f6800g = adUnitWebViewListener;
    }

    public void setVideoListener(TJAdUnitVideoListener adUnitVideoListener) {
        this.f6801h = adUnitVideoListener;
    }

    public int getOrientation() {
        return this.f6819z;
    }

    public boolean hasCalledLoad() {
        return this.f6816w;
    }

    public boolean isPrerendered() {
        return this.f6817x;
    }

    public boolean isLockedOrientation() {
        return this.f6814u;
    }

    public BasicWebView getBackgroundWebView() {
        return this.f6795b;
    }

    public MraidView getWebView() {
        return this.f6796c;
    }

    public boolean getCloseRequested() {
        return this.f6794a.closeRequested;
    }

    public void loadVideoUrl(final String videoURL, final AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ TJAdUnit f6778c;

            public final void run() {
                if (this.f6778c.f6797d != null) {
                    TapjoyLog.m7129i("TJAdUnit", "loadVideoUrl: " + videoURL);
                    this.f6778c.f6797d.setVideoPath(videoURL);
                    this.f6778c.f6797d.setVisibility(0);
                    this.f6778c.f6797d.seekTo(0);
                    adUnitAsyncTaskListner.onComplete(true);
                    return;
                }
                adUnitAsyncTaskListner.onComplete(false);
            }
        });
    }

    public boolean playVideo() {
        TapjoyLog.m7129i("TJAdUnit", "playVideo");
        if (this.f6797d == null) {
            return false;
        }
        this.f6797d.start();
        this.f6807n = false;
        this.f6799f.postDelayed(this.f6792E, 200);
        return true;
    }

    public boolean pauseVideo() {
        m6996b();
        if (this.f6797d == null || !this.f6797d.isPlaying()) {
            return false;
        }
        this.f6797d.pause();
        this.f6804k = this.f6797d.getCurrentPosition();
        TapjoyLog.m7129i("TJAdUnit", "Video paused at: " + this.f6804k);
        this.f6794a.onVideoPaused(this.f6804k);
        return true;
    }

    public void clearVideo(final AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        if (this.f6797d != null) {
            m6996b();
            TapjoyUtil.runOnMainThread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ TJAdUnit f6782b;

                public final void run() {
                    this.f6782b.f6797d.setVisibility(4);
                    this.f6782b.f6797d.stopPlayback();
                    this.f6782b.f6806m = false;
                    this.f6782b.f6805l = false;
                    this.f6782b.f6804k = 0;
                    adUnitAsyncTaskListner.onComplete(true);
                }
            });
            return;
        }
        adUnitAsyncTaskListner.onComplete(false);
    }

    /* renamed from: a */
    final void m7021a(boolean z) {
        if (this.f6803j != null) {
            if (z) {
                this.f6803j.setVolume(0.0f, 0.0f);
            } else {
                this.f6803j.setVolume(1.0f, 1.0f);
            }
            if (this.f6813t != z) {
                this.f6813t = z;
                this.f6794a.onVolumeChanged();
                return;
            }
            return;
        }
        this.f6812s = z;
    }

    public void attachVolumeListener(boolean isAttached, int interval) {
        TapjoyLog.m7126d("TJAdUnit", "attachVolumeListener: isAttached=" + isAttached + "; interval=" + interval);
        m6992a();
        if (isAttached) {
            TJAdUnitActivity tJAdUnitActivity = this.f6802i;
            if (tJAdUnitActivity != null) {
                this.f6809p = (AudioManager) tJAdUnitActivity.getSystemService("audio");
                this.f6810q = this.f6809p.getStreamVolume(3);
                this.f6811r = this.f6809p.getStreamMaxVolume(3);
                this.f6808o = gq.f7972a.scheduleWithFixedDelay(this.f6791D, (long) interval, (long) interval, TimeUnit.MILLISECONDS);
            }
        }
    }

    public VideoView getVideoView() {
        return this.f6797d;
    }

    public int getVideoSeekTime() {
        return this.f6804k;
    }

    public boolean isVideoComplete() {
        return this.f6807n;
    }

    public void onPrepared(MediaPlayer mp) {
        TapjoyLog.m7129i("TJAdUnit", "video -- onPrepared");
        final int duration = this.f6797d.getDuration();
        final int measuredWidth = this.f6797d.getMeasuredWidth();
        final int measuredHeight = this.f6797d.getMeasuredHeight();
        this.f6803j = mp;
        if (this.f6812s) {
            m7021a(this.f6812s);
        }
        if (this.f6804k <= 0 || this.f6797d.getCurrentPosition() == this.f6804k) {
            this.f6794a.onVideoReady(duration, measuredWidth, measuredHeight);
        } else {
            this.f6803j.setOnSeekCompleteListener(new OnSeekCompleteListener(this) {
                /* renamed from: d */
                final /* synthetic */ TJAdUnit f6786d;

                public final void onSeekComplete(MediaPlayer mp) {
                    this.f6786d.f6794a.onVideoReady(duration, measuredWidth, measuredHeight);
                }
            });
        }
        this.f6803j.setOnInfoListener(this);
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        String str;
        TapjoyLog.m7127e("TJAdUnit", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error encountered when instantiating the VideoView: " + what + " - " + extra));
        this.f6805l = true;
        m6996b();
        switch (what) {
            case 100:
                str = "MEDIA_ERROR_SERVER_DIED";
                break;
            default:
                str = "MEDIA_ERROR_UNKNOWN";
                break;
        }
        str = str + " -- ";
        switch (extra) {
            case IabHelper.IABHELPER_INVALID_CONSUMPTION /*-1010*/:
                str = str + "MEDIA_ERROR_UNSUPPORTED";
                break;
            case IabHelper.IABHELPER_MISSING_TOKEN /*-1007*/:
                str = str + "MEDIA_ERROR_MALFORMED";
                break;
            case IabHelper.IABHELPER_SEND_INTENT_FAILED /*-1004*/:
                str = str + "MEDIA_ERROR_IO";
                break;
            case FetchConst.ERROR_SERVER_ERROR /*-110*/:
                str = str + "MEDIA_ERROR_TIMED_OUT";
                break;
            default:
                str = str + "MEDIA_ERROR_EXTRA_UNKNOWN";
                break;
        }
        this.f6794a.onVideoError(str);
        if (what == 1 || extra == IabHelper.IABHELPER_SEND_INTENT_FAILED) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private void m6996b() {
        this.f6799f.removeCallbacks(this.f6792E);
        this.f6799f.removeCallbacks(this.f6793F);
    }

    public void onCompletion(MediaPlayer mp) {
        TapjoyLog.m7129i("TJAdUnit", "video -- onCompletion");
        m6996b();
        this.f6807n = true;
        if (!this.f6805l) {
            this.f6794a.onVideoCompletion();
        }
        this.f6805l = false;
    }

    public void fireOnVideoStart() {
        TapjoyLog.m7130v("TJAdUnit", "Firing onVideoStart");
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoStart();
        }
        if (this.f6801h != null) {
            this.f6801h.onVideoStart();
        }
    }

    public void fireOnVideoError(String errorMessage) {
        TapjoyLog.m7128e("TJAdUnit", "Firing onVideoError with error: " + errorMessage);
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoError(3);
        }
        if (this.f6801h != null) {
            this.f6801h.onVideoError(errorMessage);
        }
    }

    public void fireOnVideoComplete() {
        TapjoyLog.m7130v("TJAdUnit", "Firing onVideoComplete");
        if (getPublisherVideoListener() != null) {
            getPublisherVideoListener().onVideoComplete();
        }
        if (this.f6801h != null) {
            this.f6801h.onVideoCompleted();
        }
    }

    public String getVolume() {
        double d = ((double) this.f6810q) / ((double) this.f6811r);
        return String.format("%.2f", new Object[]{Double.valueOf(d)});
    }

    public boolean isMuted() {
        return this.f6813t;
    }

    public void startAdContentTracking(String name, JSONObject adUnitParams) {
        if (this.f6790C != null) {
            this.f6790C.m7668a(name, adUnitParams);
        }
    }

    public void endAdContentTracking(String name, JSONObject adUnitParams) {
        if (this.f6790C != null) {
            m6999c();
            this.f6790C.m7669b(name, adUnitParams);
        }
    }

    public void sendAdContentTracking(String name, JSONObject adUnitParams) {
        if (this.f6790C != null) {
            m6999c();
            et etVar = this.f6790C;
            Map a = ep.m7666a(adUnitParams);
            fi.m7754e(name).m7738a(etVar.f7675a).m7738a(a).m7741b(ep.m7667b(adUnitParams)).m7742c();
        }
    }

    /* renamed from: c */
    private void m6999c() {
        if (this.f6790C != null) {
            this.f6790C.m7663a("prerendered", Boolean.valueOf(this.f6817x));
        }
    }

    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        String str = "";
        switch (what) {
            case 3:
                str = "MEDIA_INFO_VIDEO_RENDERING_START";
                break;
            case 700:
                str = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                break;
            case 701:
                str = "MEDIA_INFO_BUFFERING_START";
                break;
            case 702:
                str = "MEDIA_INFO_BUFFERING_END";
                break;
            case AppLovinSdk.VERSION_CODE /*801*/:
                str = "MEDIA_INFO_NOT_SEEKABLE";
                break;
        }
        this.f6794a.onVideoInfo(str);
        return false;
    }

    public TJVideoListener getPublisherVideoListener() {
        return publisherVideoListener;
    }
}
