// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.annotation.TargetApi;
import android.webkit.ConsoleMessage;
import java.net.MalformedURLException;
import java.net.URL;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.graphics.Color;
import com.tapjoy.internal.fi;
import android.media.MediaPlayer$OnSeekCompleteListener;
import com.tapjoy.internal.ct;
import com.tapjoy.mraid.listener.MraidViewListener;
import android.content.Context;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;
import com.tapjoy.internal.gq;
import android.os.Looper;
import android.media.AudioManager;
import java.util.concurrent.ScheduledFuture;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.VideoView;
import com.tapjoy.mraid.view.MraidView;
import com.tapjoy.mraid.view.BasicWebView;
import com.tapjoy.internal.ep;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnInfoListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;

public class TJAdUnit implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnInfoListener, MediaPlayer$OnPreparedListener
{
    public static TJVideoListener publisherVideoListener;
    private boolean A;
    private boolean B;
    private ep C;
    private final Runnable D;
    private final Runnable E;
    private final Runnable F;
    TJAdUnitJSBridge a;
    BasicWebView b;
    MraidView c;
    VideoView d;
    volatile boolean e;
    private final Handler f;
    private TJAdUnitWebViewListener g;
    private TJAdUnitVideoListener h;
    private TJAdUnitActivity i;
    private MediaPlayer j;
    private int k;
    private boolean l;
    private boolean m;
    private boolean n;
    private ScheduledFuture o;
    private AudioManager p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private int z;
    
    public TJAdUnit() {
        this.f = new Handler(Looper.getMainLooper());
        this.q = 0;
        this.z = -1;
        this.D = new Runnable() {
            @Override
            public final void run() {
                final int streamVolume = TJAdUnit.this.p.getStreamVolume(3);
                if (TJAdUnit.this.q != streamVolume) {
                    TJAdUnit.this.q = streamVolume;
                    TJAdUnit.c(TJAdUnit.this);
                }
            }
        };
        this.E = new Runnable() {
            @Override
            public final void run() {
                if (TJAdUnit.this.d.getCurrentPosition() != 0) {
                    if (!TJAdUnit.this.m) {
                        TJAdUnit.this.m = true;
                    }
                    TJAdUnit.this.a.onVideoStarted(TJAdUnit.this.k);
                    TJAdUnit.this.F.run();
                    return;
                }
                if (!TJAdUnit.this.A) {
                    TJAdUnit.this.f.postDelayed(TJAdUnit.this.E, 200L);
                    return;
                }
                TJAdUnit.this.B = true;
            }
        };
        this.F = new Runnable() {
            @Override
            public final void run() {
                TJAdUnit.this.a.onVideoProgress(TJAdUnit.this.d.getCurrentPosition());
                TJAdUnit.this.f.postDelayed(TJAdUnit.this.F, 500L);
            }
        };
    }
    
    private void a() {
        TapjoyLog.d("TJAdUnit", "detachVolumeListener");
        if (this.o != null) {
            this.o.cancel(false);
            this.o = null;
        }
        this.p = null;
    }
    
    private static boolean a(final int n) {
        return n == 0 || n == 8 || n == 6 || n == 11;
    }
    
    private void b() {
        this.f.removeCallbacks(this.E);
        this.f.removeCallbacks(this.F);
    }
    
    private static boolean b(final int n) {
        return n == 1 || n == 9 || n == 7 || n == 12;
    }
    
    private void c() {
        if (this.C != null) {
            this.C.a("prerendered", this.x);
        }
    }
    
    static /* synthetic */ void c(final TJAdUnit tjAdUnit) {
        tjAdUnit.a.onVolumeChanged();
    }
    
    static /* synthetic */ void v(final TJAdUnit tjAdUnit) {
        tjAdUnit.a.display();
    }
    
    final void a(final boolean b) {
        if (this.j != null) {
            if (b) {
                this.j.setVolume(0.0f, 0.0f);
            }
            else {
                this.j.setVolume(1.0f, 1.0f);
            }
            if (this.t != b) {
                this.t = b;
                this.a.onVolumeChanged();
            }
            return;
        }
        this.s = b;
    }
    
    public void attachVolumeListener(final boolean b, final int n) {
        TapjoyLog.d("TJAdUnit", "attachVolumeListener: isAttached=" + b + "; interval=" + n);
        this.a();
        if (b) {
            final TJAdUnitActivity i = this.i;
            if (i != null) {
                this.p = (AudioManager)i.getSystemService("audio");
                this.q = this.p.getStreamVolume(3);
                this.r = this.p.getStreamMaxVolume(3);
                this.o = gq.a.scheduleWithFixedDelay(this.D, n, n, TimeUnit.MILLISECONDS);
            }
        }
    }
    
    public void clearVideo(final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        if (this.d != null) {
            this.b();
            TapjoyUtil.runOnMainThread(new Runnable() {
                @Override
                public final void run() {
                    TJAdUnit.this.d.setVisibility(4);
                    TJAdUnit.this.d.stopPlayback();
                    TJAdUnit.this.m = false;
                    TJAdUnit.this.l = false;
                    TJAdUnit.this.k = 0;
                    adUnitAsyncTaskListner.onComplete(true);
                }
            });
            return;
        }
        adUnitAsyncTaskListner.onComplete(false);
    }
    
    public void closeRequested(final boolean b) {
        if (this.c != null && this.c.videoPlaying()) {
            this.c.videoViewCleanup();
            return;
        }
        this.a.closeRequested(b);
    }
    
    public void destroy() {
        this.a.destroy();
        if (this.b != null) {
            this.b.removeAllViews();
            this.b = null;
        }
        if (this.c != null) {
            this.c.removeAllViews();
            this.c = null;
        }
        this.b();
        this.e = false;
        this.v = false;
        this.setAdUnitActivity(null);
        this.a();
        this.j = null;
        if (this.g != null) {
            this.g.onClosed();
        }
        this.resetContentLoadState();
    }
    
    public void endAdContentTracking(final String s, final JSONObject jsonObject) {
        if (this.C != null) {
            this.c();
            this.C.b(s, jsonObject);
        }
    }
    
    public void fireContentReady() {
        if (this.g != null) {
            this.g.onContentReady();
        }
    }
    
    public void fireOnVideoComplete() {
        TapjoyLog.v("TJAdUnit", "Firing onVideoComplete");
        if (this.getPublisherVideoListener() != null) {
            this.getPublisherVideoListener().onVideoComplete();
        }
        if (this.h != null) {
            this.h.onVideoCompleted();
        }
    }
    
    public void fireOnVideoError(final String s) {
        TapjoyLog.e("TJAdUnit", "Firing onVideoError with error: " + s);
        if (this.getPublisherVideoListener() != null) {
            this.getPublisherVideoListener().onVideoError(3);
        }
        if (this.h != null) {
            this.h.onVideoError(s);
        }
    }
    
    public void fireOnVideoStart() {
        TapjoyLog.v("TJAdUnit", "Firing onVideoStart");
        if (this.getPublisherVideoListener() != null) {
            this.getPublisherVideoListener().onVideoStart();
        }
        if (this.h != null) {
            this.h.onVideoStart();
        }
    }
    
    public BasicWebView getBackgroundWebView() {
        return this.b;
    }
    
    public boolean getCloseRequested() {
        return this.a.closeRequested;
    }
    
    public int getOrientation() {
        return this.z;
    }
    
    public TJVideoListener getPublisherVideoListener() {
        return TJAdUnit.publisherVideoListener;
    }
    
    public int getVideoSeekTime() {
        return this.k;
    }
    
    public VideoView getVideoView() {
        return this.d;
    }
    
    public String getVolume() {
        return String.format("%.2f", this.q / (double)this.r);
    }
    
    public MraidView getWebView() {
        return this.c;
    }
    
    public boolean hasCalledLoad() {
        return this.w;
    }
    
    public void invokeBridgeCallback(final String s, final Object... array) {
        if (this.a != null && s != null) {
            this.a.invokeJSCallback(s, array);
        }
    }
    
    public boolean isLockedOrientation() {
        return this.u;
    }
    
    public boolean isMuted() {
        return this.t;
    }
    
    public boolean isPrerendered() {
        return this.x;
    }
    
    public boolean isVideoComplete() {
        return this.n;
    }
    
    public void load(final TJPlacementData tjPlacementData, final boolean b, final Context context) {
        this.w = false;
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                final TJAdUnit d = TJAdUnit.this;
                final Context a = context;
                if (Looper.myLooper() == Looper.getMainLooper() && !d.e && a != null) {
                    TapjoyLog.d("TJAdUnit", "Constructing ad unit");
                    d.e = true;
                    (d.b = new BasicWebView(a)).loadDataWithBaseURL((String)null, "<!DOCTYPE html><html><head><title>Tapjoy Background Webview</title></head></html>", "text/html", "utf-8", (String)null);
                    (d.c = new MraidView(a)).setListener(new a((byte)0));
                    (d.d = new VideoView(a)).setOnCompletionListener((MediaPlayer$OnCompletionListener)d);
                    d.d.setOnErrorListener((MediaPlayer$OnErrorListener)d);
                    d.d.setOnPreparedListener((MediaPlayer$OnPreparedListener)d);
                    d.d.setVisibility(4);
                    d.a = new TJAdUnitJSBridge(a, d);
                    if (a instanceof TJAdUnitActivity) {
                        d.setAdUnitActivity((TJAdUnitActivity)a);
                    }
                }
                if (!d.e) {
                    return;
                }
                TapjoyLog.i("TJAdUnit", "Loading ad unit content");
                TJAdUnit.this.w = true;
                if (!ct.c(tjPlacementData.getRedirectURL())) {
                    if (tjPlacementData.isPreloadDisabled()) {
                        TJAdUnit.this.c.postUrl(tjPlacementData.getRedirectURL(), (byte[])null);
                    }
                    else {
                        TJAdUnit.this.c.loadUrl(tjPlacementData.getRedirectURL());
                    }
                }
                else if (tjPlacementData.getBaseURL() != null && tjPlacementData.getHttpResponse() != null) {
                    TJAdUnit.this.c.loadDataWithBaseURL(tjPlacementData.getBaseURL(), tjPlacementData.getHttpResponse(), "text/html", "utf-8", null);
                }
                else {
                    TapjoyLog.e("TJAdUnit", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error loading ad unit content"));
                    TJAdUnit.this.w = false;
                }
                TJAdUnit.this.x = (TJAdUnit.this.w && b);
            }
        });
    }
    
    public void loadVideoUrl(final String s, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                if (TJAdUnit.this.d != null) {
                    TapjoyLog.i("TJAdUnit", "loadVideoUrl: " + s);
                    TJAdUnit.this.d.setVideoPath(s);
                    TJAdUnit.this.d.setVisibility(0);
                    TJAdUnit.this.d.seekTo(0);
                    adUnitAsyncTaskListner.onComplete(true);
                    return;
                }
                adUnitAsyncTaskListner.onComplete(false);
            }
        });
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        TapjoyLog.i("TJAdUnit", "video -- onCompletion");
        this.b();
        this.n = true;
        if (!this.l) {
            this.a.onVideoCompletion();
        }
        this.l = false;
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        TapjoyLog.e("TJAdUnit", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error encountered when instantiating the VideoView: " + n + " - " + n2));
        this.l = true;
        this.b();
        String s = null;
        switch (n) {
            default: {
                s = "MEDIA_ERROR_UNKNOWN";
                break;
            }
            case 100: {
                s = "MEDIA_ERROR_SERVER_DIED";
                break;
            }
        }
        final String string = s + " -- ";
        String s2 = null;
        switch (n2) {
            default: {
                s2 = string + "MEDIA_ERROR_EXTRA_UNKNOWN";
                break;
            }
            case -1004: {
                s2 = string + "MEDIA_ERROR_IO";
                break;
            }
            case -1007: {
                s2 = string + "MEDIA_ERROR_MALFORMED";
                break;
            }
            case -1010: {
                s2 = string + "MEDIA_ERROR_UNSUPPORTED";
                break;
            }
            case -110: {
                s2 = string + "MEDIA_ERROR_TIMED_OUT";
                break;
            }
        }
        this.a.onVideoError(s2);
        return n == 1 || n2 == -1004;
    }
    
    public boolean onInfo(final MediaPlayer mediaPlayer, final int n, final int n2) {
        String s = "";
        switch (n) {
            case 700: {
                s = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                break;
            }
            case 3: {
                s = "MEDIA_INFO_VIDEO_RENDERING_START";
                break;
            }
            case 701: {
                s = "MEDIA_INFO_BUFFERING_START";
                break;
            }
            case 702: {
                s = "MEDIA_INFO_BUFFERING_END";
                break;
            }
            case 801: {
                s = "MEDIA_INFO_NOT_SEEKABLE";
                break;
            }
        }
        this.a.onVideoInfo(s);
        return false;
    }
    
    public void onPrepared(final MediaPlayer j) {
        TapjoyLog.i("TJAdUnit", "video -- onPrepared");
        final int duration = this.d.getDuration();
        final int measuredWidth = this.d.getMeasuredWidth();
        final int measuredHeight = this.d.getMeasuredHeight();
        this.j = j;
        if (this.s) {
            this.a(this.s);
        }
        if (this.k > 0 && this.d.getCurrentPosition() != this.k) {
            this.j.setOnSeekCompleteListener((MediaPlayer$OnSeekCompleteListener)new MediaPlayer$OnSeekCompleteListener() {
                public final void onSeekComplete(final MediaPlayer mediaPlayer) {
                    TJAdUnit.this.a.onVideoReady(duration, measuredWidth, measuredHeight);
                }
            });
        }
        else {
            this.a.onVideoReady(duration, measuredWidth, measuredHeight);
        }
        this.j.setOnInfoListener((MediaPlayer$OnInfoListener)this);
    }
    
    public void pause() {
        this.A = true;
        this.a.setEnabled(false);
        this.pauseVideo();
    }
    
    public boolean pauseVideo() {
        this.b();
        if (this.d != null && this.d.isPlaying()) {
            this.d.pause();
            this.k = this.d.getCurrentPosition();
            TapjoyLog.i("TJAdUnit", "Video paused at: " + this.k);
            this.a.onVideoPaused(this.k);
            return true;
        }
        return false;
    }
    
    public boolean playVideo() {
        TapjoyLog.i("TJAdUnit", "playVideo");
        if (this.d == null) {
            return false;
        }
        this.d.start();
        this.n = false;
        this.f.postDelayed(this.E, 200L);
        return true;
    }
    
    public boolean preload(final TJPlacementData tjPlacementData, final Context context) {
        if (this.w || !tjPlacementData.isPrerenderingRequested() || !TJPlacementManager.canPreRenderPlacement() || TapjoyConnectCore.isViewOpen()) {
            this.fireContentReady();
            return false;
        }
        TapjoyLog.i("TJAdUnit", "Pre-rendering ad unit for placement: " + tjPlacementData.getPlacementName());
        TJPlacementManager.incrementPlacementPreRenderCount();
        this.load(tjPlacementData, true, context);
        return true;
    }
    
    public void resetContentLoadState() {
        this.w = false;
        this.y = false;
        this.x = false;
        this.z = -1;
        this.u = false;
        this.s = false;
    }
    
    public void resume(final TJAdUnitSaveStateData tjAdUnitSaveStateData) {
        if (this.a.didLaunchOtherActivity) {
            TapjoyLog.d("TJAdUnit", "onResume bridge.didLaunchOtherActivity callbackID: " + this.a.otherActivityCallbackID);
            this.a.invokeJSCallback(this.a.otherActivityCallbackID, Boolean.TRUE);
            this.a.didLaunchOtherActivity = false;
        }
        this.A = false;
        this.a.setEnabled(true);
        if (tjAdUnitSaveStateData != null) {
            this.k = tjAdUnitSaveStateData.seekTime;
            this.d.seekTo(this.k);
            if (this.j != null) {
                this.s = tjAdUnitSaveStateData.isVideoMuted;
            }
        }
        if (this.B) {
            this.B = false;
            this.f.postDelayed(this.E, 200L);
        }
    }
    
    public void sendAdContentTracking(final String s, final JSONObject jsonObject) {
        if (this.C != null) {
            this.c();
            fi.e(s).a(this.C.a).a(ep.a(jsonObject)).b(ep.b(jsonObject)).c();
        }
    }
    
    public void setAdContentTracker(final ep c) {
        this.C = c;
    }
    
    public void setAdUnitActivity(final TJAdUnitActivity i) {
        this.i = i;
        if (this.c != null) {
            this.c.setContext((Context)this.i);
        }
        if (this.a != null) {
            this.a.setAdUnitActivity(this.i);
        }
    }
    
    public void setBackgroundColor(final String s, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                try {
                    TapjoyLog.d("TJAdUnit", "setBackgroundColor: " + s);
                    TJAdUnit.this.b.setBackgroundColor(Color.parseColor(s));
                    adUnitAsyncTaskListner.onComplete(true);
                }
                catch (Exception ex) {
                    TapjoyLog.d("TJAdUnit", "Error setting background color. backgroundWebView: " + TJAdUnit.this.b + ", hexColor: " + s);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }
    
    public void setBackgroundContent(final String s, final TJAdUnitJSBridge.AdUnitAsyncTaskListner adUnitAsyncTaskListner) {
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                try {
                    TapjoyLog.d("TJAdUnit", "setBackgroundContent: " + s);
                    TJAdUnit.this.b.loadDataWithBaseURL((String)null, s, "text/html", "utf-8", (String)null);
                    adUnitAsyncTaskListner.onComplete(true);
                }
                catch (Exception ex) {
                    TapjoyLog.d("TJAdUnit", "Error setting background content. backgroundWebView: " + TJAdUnit.this.b + ", content: " + s);
                    adUnitAsyncTaskListner.onComplete(false);
                }
            }
        });
    }
    
    public void setOrientation(final int n) {
        final int n2 = 0;
        final TJAdUnitActivity i = this.i;
        if (i != null) {
            final TJAdUnitActivity j = this.i;
            int z;
            if (j != null) {
                final int rotation = j.getWindowManager().getDefaultDisplay().getRotation();
                final DisplayMetrics displayMetrics = new DisplayMetrics();
                j.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                final int widthPixels = displayMetrics.widthPixels;
                final int heightPixels = displayMetrics.heightPixels;
                if (((rotation == 0 || rotation == 2) && heightPixels > widthPixels) || ((rotation == 1 || rotation == 3) && widthPixels > heightPixels)) {
                    z = n2;
                    switch (rotation) {
                        default: {
                            z = 1;
                            break;
                        }
                        case 3: {
                            z = 8;
                            break;
                        }
                        case 2: {
                            z = 9;
                            break;
                        }
                        case 0: {
                            z = 1;
                        }
                        case 1: {
                            break;
                        }
                    }
                }
                else {
                    z = n2;
                    switch (rotation) {
                        case 3: {
                            z = 9;
                            break;
                        }
                        case 2: {
                            z = 8;
                            break;
                        }
                        case 1: {
                            z = 1;
                        }
                        case 0: {
                            break;
                        }
                        default: {
                            TapjoyLog.w("TJAdUnit", "Unknown screen orientation. Defaulting to landscape.");
                            z = n2;
                            break;
                        }
                    }
                }
            }
            else {
                z = -1;
            }
            if (this.z != -1) {
                z = this.z;
            }
            int z2 = 0;
            Label_0196: {
                if (!a(z) || !a(n)) {
                    z2 = n;
                    if (!b(z)) {
                        break Label_0196;
                    }
                    z2 = n;
                    if (!b(n)) {
                        break Label_0196;
                    }
                }
                z2 = this.z;
            }
            i.setRequestedOrientation(z2);
            this.z = z2;
            this.u = true;
        }
    }
    
    public void setVideoListener(final TJAdUnitVideoListener h) {
        this.h = h;
    }
    
    public void setVisible(final boolean v) {
        this.v = v;
        if (this.v && this.y) {
            this.a.display();
        }
    }
    
    public void setWebViewListener(final TJAdUnitWebViewListener g) {
        this.g = g;
    }
    
    public void startAdContentTracking(final String s, final JSONObject jsonObject) {
        if (this.C != null) {
            this.C.a(s, jsonObject);
        }
    }
    
    public void unsetOrientation() {
        final TJAdUnitActivity i = this.i;
        if (i != null) {
            i.setRequestedOrientation(-1);
        }
        this.z = -1;
        this.u = false;
    }
    
    public interface TJAdUnitVideoListener
    {
        void onVideoCompleted();
        
        void onVideoError(final String p0);
        
        void onVideoStart();
    }
    
    public interface TJAdUnitWebViewListener
    {
        void onClosed();
        
        void onContentReady();
    }
    
    final class a implements MraidViewListener
    {
        private a() {
        }
        
        private boolean a() {
            final boolean b = false;
            try {
                final NetworkInfo activeNetworkInfo = TJAdUnit.this.c.getConnectivityManager().getActiveNetworkInfo();
                boolean b2 = b;
                if (activeNetworkInfo != null) {
                    b2 = b;
                    if (activeNetworkInfo.isAvailable()) {
                        final boolean connected = activeNetworkInfo.isConnected();
                        b2 = b;
                        if (connected) {
                            b2 = true;
                        }
                    }
                }
                return b2;
            }
            catch (Exception ex) {
                return false;
            }
        }
        
        private static boolean a(final String s) {
            boolean b = false;
            try {
                final String host = new URL("https://ws.tapjoyads.com/").getHost();
                if ((host != null && s.contains(host)) || s.contains(TapjoyConnectCore.getRedirectDomain()) || s.contains(TapjoyUtil.getRedirectDomain(TapjoyConnectCore.getPlacementURL()))) {
                    b = true;
                }
                return b;
            }
            catch (MalformedURLException ex) {
                return false;
            }
        }
        
        @Override
        public final boolean onClose() {
            final TJAdUnitActivity r = TJAdUnit.this.i;
            if (r != null) {
                r.handleClose();
            }
            return false;
        }
        
        @TargetApi(8)
        @Override
        public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
            int i = 0;
            if (TJAdUnit.this.a.closeRequested) {
                TapjoyLog.d("TJAdUnit", "shouldClose...");
                final TJAdUnitActivity r = TJAdUnit.this.i;
                if (r != null) {
                    while (i < 5) {
                        if (consoleMessage.message().contains((new String[] { "Uncaught", "uncaught", "Error", "error", "not defined" })[i])) {
                            r.handleClose();
                            break;
                        }
                        ++i;
                    }
                }
            }
            return true;
        }
        
        @Override
        public final boolean onEventFired() {
            return false;
        }
        
        @Override
        public final void onPageFinished(final WebView webView, final String s) {
            TapjoyLog.d("TJAdUnit", "onPageFinished: " + s);
            if (TJAdUnit.this != null && TJAdUnit.this.c != null && TJAdUnit.this.c.isMraid()) {
                TJAdUnit.this.a.allowRedirect = false;
            }
            final TJAdUnitActivity r = TJAdUnit.this.i;
            if (r != null) {
                r.setProgressSpinnerVisibility(false);
            }
            TJAdUnit.this.y = true;
            if (TJAdUnit.this.v) {
                TJAdUnit.v(TJAdUnit.this);
            }
            TJAdUnit.this.a.flushMessageQueue();
        }
        
        @Override
        public final void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            TapjoyLog.d("TJAdUnit", "onPageStarted: " + s);
            if (TJAdUnit.this.a != null) {
                TJAdUnit.this.a.allowRedirect = true;
                TJAdUnit.this.a.customClose = false;
                TJAdUnit.this.a.closeRequested = false;
                TJAdUnit.this.a();
            }
        }
        
        @Override
        public final boolean onReady() {
            return false;
        }
        
        @Override
        public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            final TJAdUnitActivity r = TJAdUnit.this.i;
            if (r != null) {
                r.showErrorDialog();
            }
        }
        
        @Override
        public final boolean onResize() {
            return false;
        }
        
        @Override
        public final boolean onResizeClose() {
            return false;
        }
        
        @TargetApi(9)
        @Override
        public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            if (!this.a()) {
                final TJAdUnitActivity r = TJAdUnit.this.i;
                if (r != null) {
                    r.showErrorDialog();
                }
                return true;
            }
            TapjoyLog.d("TJAdUnit", "interceptURL: " + s);
            int n;
            if (TJAdUnit.this != null && TJAdUnit.this.c != null && TJAdUnit.this.c.isMraid() && s.contains("mraid")) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                return false;
            }
            if (a(s)) {
                TapjoyLog.d("TJAdUnit", "Open redirecting URL:" + s);
                ((MraidView)webView).loadUrlStandard(s);
                return true;
            }
            if (TJAdUnit.this.a.allowRedirect) {
                return false;
            }
            webView.loadUrl(s);
            return true;
        }
    }
}
