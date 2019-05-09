// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.view;

import com.tapjoy.TapjoyErrorMessage;
import android.os.AsyncTask;
import android.webkit.WebBackForwardList;
import com.tapjoy.mraid.listener.Player;
import android.widget.FrameLayout$LayoutParams;
import android.os.Parcelable;
import android.content.ActivityNotFoundException;
import com.tapjoy.mraid.util.Utils;
import com.tapjoy.TapjoyHttpURLResponse;
import com.tapjoy.mraid.controller.Abstract;
import com.tapjoy.TapjoyURLConnection;
import android.os.Build$VERSION;
import android.webkit.URLUtil;
import com.tapjoy.TapjoyUtil;
import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import java.util.regex.Pattern;
import android.net.ConnectivityManager;
import android.view.WindowManager;
import android.view.ViewGroup;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import android.webkit.ValueCallback;
import android.content.res.TypedArray;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer$OnPreparedListener;
import android.util.AttributeSet;
import android.widget.RelativeLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.app.Activity;
import android.widget.FrameLayout;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ConsoleMessage;
import android.content.Intent;
import android.net.Uri;
import com.tapjoy.TapjoyCachedAssetData;
import com.tapjoy.TapjoyCache;
import android.webkit.WebResourceResponse;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.os.Bundle;
import com.tapjoy.TapjoyLog;
import android.view.ViewGroup$MarginLayoutParams;
import android.os.Message;
import android.widget.RelativeLayout;
import android.content.Context;
import com.tapjoy.mraid.listener.MraidViewListener;
import com.tapjoy.mraid.controller.Utility;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.os.Handler;
import android.widget.ProgressBar;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.VideoView;
import com.tapjoy.mraid.util.MraidPlayer;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

public class MraidView extends BasicWebView implements ViewTreeObserver$OnGlobalLayoutListener
{
    public static final String ACTION_KEY = "action";
    public static final String DIMENSIONS = "expand_dimensions";
    public static final String EXPAND_URL = "expand_url";
    public static final int MRAID_ID = 102;
    public static final String PLAYER_PROPERTIES = "player_properties";
    private static int[] c;
    private static final String[] d;
    private static MraidPlayer s;
    private VideoView A;
    private WebChromeClient$CustomViewCallback B;
    private ProgressBar C;
    private Handler D;
    private boolean E;
    WebViewClient a;
    WebChromeClient b;
    private customCloseState e;
    private boolean f;
    private boolean g;
    private Utility h;
    private float i;
    private int j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private int o;
    private PLACEMENT_TYPE p;
    private VIEW_STATE q;
    private MraidViewListener r;
    private int t;
    private int u;
    private Thread v;
    private boolean w;
    private int x;
    private Context y;
    private RelativeLayout z;
    
    static {
        MraidView.c = new int[] { 16843039, 16843040 };
        d = new String[] { ".mp4", ".3gp", ".mpg" };
    }
    
    public MraidView(final Context y) {
        super(y);
        this.e = customCloseState.UNKNOWN;
        this.f = false;
        this.q = VIEW_STATE.DEFAULT;
        this.t = 0;
        this.u = 0;
        this.v = null;
        this.w = false;
        this.D = new Handler() {
            public final void handleMessage(final Message message) {
                final Bundle data = message.getData();
                Label_0064: {
                    switch (message.what) {
                        case 1010: {
                            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)MraidView.this.getLayoutParams();
                            if (viewGroup$MarginLayoutParams != null) {
                                MraidView.this.removeCloseImageButton();
                                viewGroup$MarginLayoutParams.height = data.getInt("resize_height", viewGroup$MarginLayoutParams.height);
                                viewGroup$MarginLayoutParams.width = data.getInt("resize_width", viewGroup$MarginLayoutParams.width);
                                final String string = "window.mraidview.fireChangeEvent({ state: '" + MraidView.this.getState() + "', size: { width: " + (int)(viewGroup$MarginLayoutParams.width / MraidView.this.i) + ", height: " + (int)(viewGroup$MarginLayoutParams.height / MraidView.this.i) + "}});";
                                TapjoyLog.d("MRAIDView", "resize: injection: " + string);
                                MraidView.this.injectMraidJavaScript(string);
                                MraidView.this.requestLayout();
                                MraidView.c(MraidView.this, data.getString("resize_customClosePostition"));
                                if (MraidView.this.p != PLACEMENT_TYPE.INLINE && MraidView.this.e == customCloseState.OPEN) {
                                    MraidView.this.showCloseImageButton();
                                }
                            }
                            if (MraidView.this.r != null) {
                                MraidView.this.r.onResize();
                                break;
                            }
                            break;
                        }
                        case 1001: {
                            switch (MraidView$6.a[MraidView.this.q.ordinal()]) {
                                default: {
                                    break Label_0064;
                                }
                                case 1: {
                                    if (MraidView.this.p != PLACEMENT_TYPE.INLINE) {
                                        MraidView.f(MraidView.this);
                                        break Label_0064;
                                    }
                                    break Label_0064;
                                }
                            }
                            break;
                        }
                        case 1003: {
                            MraidView.this.injectMraidJavaScript("window.mraidview.fireChangeEvent({ state: 'default' });");
                            MraidView.this.setVisibility(0);
                            break;
                        }
                        case 1006: {
                            MraidView.this.q = VIEW_STATE.LEFT_BEHIND;
                            break;
                        }
                        case 1008: {
                            MraidView.this.playAudioImpl(data);
                            break;
                        }
                        case 1007: {
                            MraidView.this.playVideoImpl(data);
                            break;
                        }
                        case 1009: {
                            MraidView.this.injectMraidJavaScript("window.mraidview.fireErrorEvent(\"" + data.getString("message") + "\", \"" + data.getString("action") + "\")");
                            break;
                        }
                    }
                }
                super.handleMessage(message);
            }
        };
        this.a = new WebViewClient() {
            public final void onLoadResource(final WebView webView, final String s) {
            }
            
            public final void onPageFinished(final WebView webView, final String s) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onPageFinished(webView, s);
                }
                MraidView.this.l = (int)(MraidView.this.getHeight() / MraidView.this.i);
                MraidView.this.m = (int)(MraidView.this.getWidth() / MraidView.this.i);
                MraidView.this.h.init(MraidView.this.i);
                MraidView.this.createCloseImageButton();
                if (MraidView.this.p == PLACEMENT_TYPE.INLINE) {
                    MraidView.this.removeCloseImageButton();
                }
            }
            
            public final void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onPageStarted(webView, s, bitmap);
                }
            }
            
            public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onReceivedError(webView, n, s, s2);
                }
                TapjoyLog.d("MRAIDView", "error:" + s);
                super.onReceivedError(webView, n, s, s2);
            }
            
            public final WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
                if (TapjoyCache.getInstance() != null) {
                    final TapjoyCachedAssetData cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(s);
                    if (cachedDataForURL == null) {
                        TapjoyLog.d("MRAIDView", "No cached data for " + s);
                    }
                    else {
                        final WebResourceResponse a = b(cachedDataForURL);
                        if (a != null) {
                            TapjoyLog.d("MRAIDView", "Reading request for " + s + " from cache -- localPath: " + cachedDataForURL.getLocalFilePath());
                            return a;
                        }
                    }
                }
                return super.shouldInterceptRequest(webView, s);
            }
            
            public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                TapjoyLog.d("MRAIDView", "shouldOverrideUrlLoading: " + s);
                if (MraidView.this.r != null && MraidView.this.r.shouldOverrideUrlLoading(webView, s)) {
                    return true;
                }
                final Uri parse = Uri.parse(s);
                try {
                    if (s.startsWith("mraid")) {
                        return super.shouldOverrideUrlLoading(webView, s);
                    }
                    if (s.startsWith("tel:")) {
                        final Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(s));
                        intent.addFlags(268435456);
                        MraidView.this.getContext().startActivity(intent);
                        return true;
                    }
                }
                catch (Exception ex) {
                    try {
                        final Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        intent2.setData(parse);
                        intent2.addFlags(268435456);
                        MraidView.this.getContext().startActivity(intent2);
                        return true;
                    }
                    catch (Exception ex2) {
                        return false;
                    }
                }
                if (s.startsWith("mailto:")) {
                    final Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(s));
                    intent3.addFlags(268435456);
                    MraidView.this.getContext().startActivity(intent3);
                    return true;
                }
                final Intent intent4 = new Intent();
                intent4.setAction("android.intent.action.VIEW");
                intent4.setData(parse);
                intent4.addFlags(268435456);
                MraidView.this.getContext().startActivity(intent4);
                return true;
            }
        };
        this.b = new WebChromeClient() {
            public final void onCloseWindow(final WebView webView) {
                super.onCloseWindow(webView);
                MraidView.f(MraidView.this);
            }
            
            public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
                if (MraidView.this.r != null) {
                    return MraidView.this.r.onConsoleMessage(consoleMessage);
                }
                return super.onConsoleMessage(consoleMessage);
            }
            
            public final void onHideCustomView() {
                super.onHideCustomView();
            }
            
            public final boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
                TapjoyLog.d("MRAIDView", s2);
                return false;
            }
            
            public final void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
                TapjoyLog.d("MRAIDView", "-- onShowCustomView --");
                super.onShowCustomView(view, webChromeClient$CustomViewCallback);
                MraidView.this.B = webChromeClient$CustomViewCallback;
                if (view instanceof FrameLayout) {
                    final FrameLayout frameLayout = (FrameLayout)view;
                    if (frameLayout.getFocusedChild() instanceof VideoView && MraidView.this.y instanceof Activity) {
                        final Activity activity = (Activity)MraidView.this.y;
                        MraidView.this.A = (VideoView)frameLayout.getFocusedChild();
                        frameLayout.removeView((View)MraidView.this.A);
                        if (MraidView.this.z == null) {
                            MraidView.this.z = new RelativeLayout(MraidView.this.y);
                            MraidView.this.z.setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
                            MraidView.this.z.setBackgroundColor(-16777216);
                        }
                        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
                        layoutParams.addRule(13);
                        MraidView.this.A.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                        MraidView.this.C = new ProgressBar(MraidView.this.y, (AttributeSet)null, 16842874);
                        MraidView.this.C.setVisibility(0);
                        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
                        layoutParams2.addRule(13);
                        MraidView.this.C.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
                        MraidView.this.z.addView((View)MraidView.this.A);
                        MraidView.this.z.addView((View)MraidView.this.C);
                        activity.getWindow().addContentView((View)MraidView.this.z, new ViewGroup$LayoutParams(-1, -1));
                        new Thread(new c()).start();
                        MraidView.this.setVisibility(8);
                        MraidView.this.A.setOnPreparedListener((MediaPlayer$OnPreparedListener)new MediaPlayer$OnPreparedListener() {
                            public final void onPrepared(final MediaPlayer mediaPlayer) {
                                TapjoyLog.i("MRAIDView", "** ON PREPARED **");
                                TapjoyLog.i("MRAIDView", "isPlaying: " + mediaPlayer.isPlaying());
                                if (!mediaPlayer.isPlaying()) {
                                    mediaPlayer.start();
                                }
                            }
                        });
                        MraidView.this.A.setOnCompletionListener((MediaPlayer$OnCompletionListener)new MediaPlayer$OnCompletionListener() {
                            public final void onCompletion(final MediaPlayer mediaPlayer) {
                                TapjoyLog.i("MRAIDView", "** ON COMPLETION **");
                                MraidView.this.videoViewCleanup();
                            }
                        });
                        MraidView.this.A.setOnErrorListener((MediaPlayer$OnErrorListener)new MediaPlayer$OnErrorListener() {
                            public final boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
                                TapjoyLog.i("MRAIDView", "** ON ERROR **");
                                MraidView.this.videoViewCleanup();
                                return false;
                            }
                        });
                        MraidView.this.A.start();
                    }
                }
            }
        };
        this.y = y;
        this.initialize();
    }
    
    public MraidView(final Context context, final AttributeSet set) {
        super(context, set);
        this.e = customCloseState.UNKNOWN;
        this.f = false;
        this.q = VIEW_STATE.DEFAULT;
        this.t = 0;
        this.u = 0;
        this.v = null;
        this.w = false;
        this.D = new Handler() {
            public final void handleMessage(final Message message) {
                final Bundle data = message.getData();
                Label_0064: {
                    switch (message.what) {
                        case 1010: {
                            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)MraidView.this.getLayoutParams();
                            if (viewGroup$MarginLayoutParams != null) {
                                MraidView.this.removeCloseImageButton();
                                viewGroup$MarginLayoutParams.height = data.getInt("resize_height", viewGroup$MarginLayoutParams.height);
                                viewGroup$MarginLayoutParams.width = data.getInt("resize_width", viewGroup$MarginLayoutParams.width);
                                final String string = "window.mraidview.fireChangeEvent({ state: '" + MraidView.this.getState() + "', size: { width: " + (int)(viewGroup$MarginLayoutParams.width / MraidView.this.i) + ", height: " + (int)(viewGroup$MarginLayoutParams.height / MraidView.this.i) + "}});";
                                TapjoyLog.d("MRAIDView", "resize: injection: " + string);
                                MraidView.this.injectMraidJavaScript(string);
                                MraidView.this.requestLayout();
                                MraidView.c(MraidView.this, data.getString("resize_customClosePostition"));
                                if (MraidView.this.p != PLACEMENT_TYPE.INLINE && MraidView.this.e == customCloseState.OPEN) {
                                    MraidView.this.showCloseImageButton();
                                }
                            }
                            if (MraidView.this.r != null) {
                                MraidView.this.r.onResize();
                                break;
                            }
                            break;
                        }
                        case 1001: {
                            switch (MraidView$6.a[MraidView.this.q.ordinal()]) {
                                default: {
                                    break Label_0064;
                                }
                                case 1: {
                                    if (MraidView.this.p != PLACEMENT_TYPE.INLINE) {
                                        MraidView.f(MraidView.this);
                                        break Label_0064;
                                    }
                                    break Label_0064;
                                }
                            }
                            break;
                        }
                        case 1003: {
                            MraidView.this.injectMraidJavaScript("window.mraidview.fireChangeEvent({ state: 'default' });");
                            MraidView.this.setVisibility(0);
                            break;
                        }
                        case 1006: {
                            MraidView.this.q = VIEW_STATE.LEFT_BEHIND;
                            break;
                        }
                        case 1008: {
                            MraidView.this.playAudioImpl(data);
                            break;
                        }
                        case 1007: {
                            MraidView.this.playVideoImpl(data);
                            break;
                        }
                        case 1009: {
                            MraidView.this.injectMraidJavaScript("window.mraidview.fireErrorEvent(\"" + data.getString("message") + "\", \"" + data.getString("action") + "\")");
                            break;
                        }
                    }
                }
                super.handleMessage(message);
            }
        };
        this.a = new WebViewClient() {
            public final void onLoadResource(final WebView webView, final String s) {
            }
            
            public final void onPageFinished(final WebView webView, final String s) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onPageFinished(webView, s);
                }
                MraidView.this.l = (int)(MraidView.this.getHeight() / MraidView.this.i);
                MraidView.this.m = (int)(MraidView.this.getWidth() / MraidView.this.i);
                MraidView.this.h.init(MraidView.this.i);
                MraidView.this.createCloseImageButton();
                if (MraidView.this.p == PLACEMENT_TYPE.INLINE) {
                    MraidView.this.removeCloseImageButton();
                }
            }
            
            public final void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onPageStarted(webView, s, bitmap);
                }
            }
            
            public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onReceivedError(webView, n, s, s2);
                }
                TapjoyLog.d("MRAIDView", "error:" + s);
                super.onReceivedError(webView, n, s, s2);
            }
            
            public final WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
                if (TapjoyCache.getInstance() != null) {
                    final TapjoyCachedAssetData cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(s);
                    if (cachedDataForURL == null) {
                        TapjoyLog.d("MRAIDView", "No cached data for " + s);
                    }
                    else {
                        final WebResourceResponse a = b(cachedDataForURL);
                        if (a != null) {
                            TapjoyLog.d("MRAIDView", "Reading request for " + s + " from cache -- localPath: " + cachedDataForURL.getLocalFilePath());
                            return a;
                        }
                    }
                }
                return super.shouldInterceptRequest(webView, s);
            }
            
            public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                TapjoyLog.d("MRAIDView", "shouldOverrideUrlLoading: " + s);
                if (MraidView.this.r != null && MraidView.this.r.shouldOverrideUrlLoading(webView, s)) {
                    return true;
                }
                final Uri parse = Uri.parse(s);
                try {
                    if (s.startsWith("mraid")) {
                        return super.shouldOverrideUrlLoading(webView, s);
                    }
                    if (s.startsWith("tel:")) {
                        final Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(s));
                        intent.addFlags(268435456);
                        MraidView.this.getContext().startActivity(intent);
                        return true;
                    }
                }
                catch (Exception ex) {
                    try {
                        final Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        intent2.setData(parse);
                        intent2.addFlags(268435456);
                        MraidView.this.getContext().startActivity(intent2);
                        return true;
                    }
                    catch (Exception ex2) {
                        return false;
                    }
                }
                if (s.startsWith("mailto:")) {
                    final Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(s));
                    intent3.addFlags(268435456);
                    MraidView.this.getContext().startActivity(intent3);
                    return true;
                }
                final Intent intent4 = new Intent();
                intent4.setAction("android.intent.action.VIEW");
                intent4.setData(parse);
                intent4.addFlags(268435456);
                MraidView.this.getContext().startActivity(intent4);
                return true;
            }
        };
        this.b = new WebChromeClient() {
            public final void onCloseWindow(final WebView webView) {
                super.onCloseWindow(webView);
                MraidView.f(MraidView.this);
            }
            
            public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
                if (MraidView.this.r != null) {
                    return MraidView.this.r.onConsoleMessage(consoleMessage);
                }
                return super.onConsoleMessage(consoleMessage);
            }
            
            public final void onHideCustomView() {
                super.onHideCustomView();
            }
            
            public final boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
                TapjoyLog.d("MRAIDView", s2);
                return false;
            }
            
            public final void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
                TapjoyLog.d("MRAIDView", "-- onShowCustomView --");
                super.onShowCustomView(view, webChromeClient$CustomViewCallback);
                MraidView.this.B = webChromeClient$CustomViewCallback;
                if (view instanceof FrameLayout) {
                    final FrameLayout frameLayout = (FrameLayout)view;
                    if (frameLayout.getFocusedChild() instanceof VideoView && MraidView.this.y instanceof Activity) {
                        final Activity activity = (Activity)MraidView.this.y;
                        MraidView.this.A = (VideoView)frameLayout.getFocusedChild();
                        frameLayout.removeView((View)MraidView.this.A);
                        if (MraidView.this.z == null) {
                            MraidView.this.z = new RelativeLayout(MraidView.this.y);
                            MraidView.this.z.setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
                            MraidView.this.z.setBackgroundColor(-16777216);
                        }
                        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
                        layoutParams.addRule(13);
                        MraidView.this.A.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                        MraidView.this.C = new ProgressBar(MraidView.this.y, (AttributeSet)null, 16842874);
                        MraidView.this.C.setVisibility(0);
                        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
                        layoutParams2.addRule(13);
                        MraidView.this.C.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
                        MraidView.this.z.addView((View)MraidView.this.A);
                        MraidView.this.z.addView((View)MraidView.this.C);
                        activity.getWindow().addContentView((View)MraidView.this.z, new ViewGroup$LayoutParams(-1, -1));
                        new Thread(new c()).start();
                        MraidView.this.setVisibility(8);
                        MraidView.this.A.setOnPreparedListener((MediaPlayer$OnPreparedListener)new MediaPlayer$OnPreparedListener() {
                            public final void onPrepared(final MediaPlayer mediaPlayer) {
                                TapjoyLog.i("MRAIDView", "** ON PREPARED **");
                                TapjoyLog.i("MRAIDView", "isPlaying: " + mediaPlayer.isPlaying());
                                if (!mediaPlayer.isPlaying()) {
                                    mediaPlayer.start();
                                }
                            }
                        });
                        MraidView.this.A.setOnCompletionListener((MediaPlayer$OnCompletionListener)new MediaPlayer$OnCompletionListener() {
                            public final void onCompletion(final MediaPlayer mediaPlayer) {
                                TapjoyLog.i("MRAIDView", "** ON COMPLETION **");
                                MraidView.this.videoViewCleanup();
                            }
                        });
                        MraidView.this.A.setOnErrorListener((MediaPlayer$OnErrorListener)new MediaPlayer$OnErrorListener() {
                            public final boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
                                TapjoyLog.i("MRAIDView", "** ON ERROR **");
                                MraidView.this.videoViewCleanup();
                                return false;
                            }
                        });
                        MraidView.this.A.start();
                    }
                }
            }
        };
        this.initialize();
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, MraidView.c);
        final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        final int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        if (dimensionPixelSize > 0 && dimensionPixelSize2 > 0) {
            this.h.setMaxSize(dimensionPixelSize, dimensionPixelSize2);
        }
        obtainStyledAttributes.recycle();
    }
    
    public MraidView(final Context y, final MraidViewListener listener) {
        super(y);
        this.e = customCloseState.UNKNOWN;
        this.f = false;
        this.q = VIEW_STATE.DEFAULT;
        this.t = 0;
        this.u = 0;
        this.v = null;
        this.w = false;
        this.D = new Handler() {
            public final void handleMessage(final Message message) {
                final Bundle data = message.getData();
                Label_0064: {
                    switch (message.what) {
                        case 1010: {
                            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)MraidView.this.getLayoutParams();
                            if (viewGroup$MarginLayoutParams != null) {
                                MraidView.this.removeCloseImageButton();
                                viewGroup$MarginLayoutParams.height = data.getInt("resize_height", viewGroup$MarginLayoutParams.height);
                                viewGroup$MarginLayoutParams.width = data.getInt("resize_width", viewGroup$MarginLayoutParams.width);
                                final String string = "window.mraidview.fireChangeEvent({ state: '" + MraidView.this.getState() + "', size: { width: " + (int)(viewGroup$MarginLayoutParams.width / MraidView.this.i) + ", height: " + (int)(viewGroup$MarginLayoutParams.height / MraidView.this.i) + "}});";
                                TapjoyLog.d("MRAIDView", "resize: injection: " + string);
                                MraidView.this.injectMraidJavaScript(string);
                                MraidView.this.requestLayout();
                                MraidView.c(MraidView.this, data.getString("resize_customClosePostition"));
                                if (MraidView.this.p != PLACEMENT_TYPE.INLINE && MraidView.this.e == customCloseState.OPEN) {
                                    MraidView.this.showCloseImageButton();
                                }
                            }
                            if (MraidView.this.r != null) {
                                MraidView.this.r.onResize();
                                break;
                            }
                            break;
                        }
                        case 1001: {
                            switch (MraidView$6.a[MraidView.this.q.ordinal()]) {
                                default: {
                                    break Label_0064;
                                }
                                case 1: {
                                    if (MraidView.this.p != PLACEMENT_TYPE.INLINE) {
                                        MraidView.f(MraidView.this);
                                        break Label_0064;
                                    }
                                    break Label_0064;
                                }
                            }
                            break;
                        }
                        case 1003: {
                            MraidView.this.injectMraidJavaScript("window.mraidview.fireChangeEvent({ state: 'default' });");
                            MraidView.this.setVisibility(0);
                            break;
                        }
                        case 1006: {
                            MraidView.this.q = VIEW_STATE.LEFT_BEHIND;
                            break;
                        }
                        case 1008: {
                            MraidView.this.playAudioImpl(data);
                            break;
                        }
                        case 1007: {
                            MraidView.this.playVideoImpl(data);
                            break;
                        }
                        case 1009: {
                            MraidView.this.injectMraidJavaScript("window.mraidview.fireErrorEvent(\"" + data.getString("message") + "\", \"" + data.getString("action") + "\")");
                            break;
                        }
                    }
                }
                super.handleMessage(message);
            }
        };
        this.a = new WebViewClient() {
            public final void onLoadResource(final WebView webView, final String s) {
            }
            
            public final void onPageFinished(final WebView webView, final String s) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onPageFinished(webView, s);
                }
                MraidView.this.l = (int)(MraidView.this.getHeight() / MraidView.this.i);
                MraidView.this.m = (int)(MraidView.this.getWidth() / MraidView.this.i);
                MraidView.this.h.init(MraidView.this.i);
                MraidView.this.createCloseImageButton();
                if (MraidView.this.p == PLACEMENT_TYPE.INLINE) {
                    MraidView.this.removeCloseImageButton();
                }
            }
            
            public final void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onPageStarted(webView, s, bitmap);
                }
            }
            
            public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
                if (MraidView.this.r != null) {
                    MraidView.this.r.onReceivedError(webView, n, s, s2);
                }
                TapjoyLog.d("MRAIDView", "error:" + s);
                super.onReceivedError(webView, n, s, s2);
            }
            
            public final WebResourceResponse shouldInterceptRequest(final WebView webView, final String s) {
                if (TapjoyCache.getInstance() != null) {
                    final TapjoyCachedAssetData cachedDataForURL = TapjoyCache.getInstance().getCachedDataForURL(s);
                    if (cachedDataForURL == null) {
                        TapjoyLog.d("MRAIDView", "No cached data for " + s);
                    }
                    else {
                        final WebResourceResponse a = b(cachedDataForURL);
                        if (a != null) {
                            TapjoyLog.d("MRAIDView", "Reading request for " + s + " from cache -- localPath: " + cachedDataForURL.getLocalFilePath());
                            return a;
                        }
                    }
                }
                return super.shouldInterceptRequest(webView, s);
            }
            
            public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                TapjoyLog.d("MRAIDView", "shouldOverrideUrlLoading: " + s);
                if (MraidView.this.r != null && MraidView.this.r.shouldOverrideUrlLoading(webView, s)) {
                    return true;
                }
                final Uri parse = Uri.parse(s);
                try {
                    if (s.startsWith("mraid")) {
                        return super.shouldOverrideUrlLoading(webView, s);
                    }
                    if (s.startsWith("tel:")) {
                        final Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(s));
                        intent.addFlags(268435456);
                        MraidView.this.getContext().startActivity(intent);
                        return true;
                    }
                }
                catch (Exception ex) {
                    try {
                        final Intent intent2 = new Intent();
                        intent2.setAction("android.intent.action.VIEW");
                        intent2.setData(parse);
                        intent2.addFlags(268435456);
                        MraidView.this.getContext().startActivity(intent2);
                        return true;
                    }
                    catch (Exception ex2) {
                        return false;
                    }
                }
                if (s.startsWith("mailto:")) {
                    final Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(s));
                    intent3.addFlags(268435456);
                    MraidView.this.getContext().startActivity(intent3);
                    return true;
                }
                final Intent intent4 = new Intent();
                intent4.setAction("android.intent.action.VIEW");
                intent4.setData(parse);
                intent4.addFlags(268435456);
                MraidView.this.getContext().startActivity(intent4);
                return true;
            }
        };
        this.b = new WebChromeClient() {
            public final void onCloseWindow(final WebView webView) {
                super.onCloseWindow(webView);
                MraidView.f(MraidView.this);
            }
            
            public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
                if (MraidView.this.r != null) {
                    return MraidView.this.r.onConsoleMessage(consoleMessage);
                }
                return super.onConsoleMessage(consoleMessage);
            }
            
            public final void onHideCustomView() {
                super.onHideCustomView();
            }
            
            public final boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
                TapjoyLog.d("MRAIDView", s2);
                return false;
            }
            
            public final void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
                TapjoyLog.d("MRAIDView", "-- onShowCustomView --");
                super.onShowCustomView(view, webChromeClient$CustomViewCallback);
                MraidView.this.B = webChromeClient$CustomViewCallback;
                if (view instanceof FrameLayout) {
                    final FrameLayout frameLayout = (FrameLayout)view;
                    if (frameLayout.getFocusedChild() instanceof VideoView && MraidView.this.y instanceof Activity) {
                        final Activity activity = (Activity)MraidView.this.y;
                        MraidView.this.A = (VideoView)frameLayout.getFocusedChild();
                        frameLayout.removeView((View)MraidView.this.A);
                        if (MraidView.this.z == null) {
                            MraidView.this.z = new RelativeLayout(MraidView.this.y);
                            MraidView.this.z.setLayoutParams(new ViewGroup$LayoutParams(-1, -1));
                            MraidView.this.z.setBackgroundColor(-16777216);
                        }
                        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
                        layoutParams.addRule(13);
                        MraidView.this.A.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                        MraidView.this.C = new ProgressBar(MraidView.this.y, (AttributeSet)null, 16842874);
                        MraidView.this.C.setVisibility(0);
                        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
                        layoutParams2.addRule(13);
                        MraidView.this.C.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
                        MraidView.this.z.addView((View)MraidView.this.A);
                        MraidView.this.z.addView((View)MraidView.this.C);
                        activity.getWindow().addContentView((View)MraidView.this.z, new ViewGroup$LayoutParams(-1, -1));
                        new Thread(new c()).start();
                        MraidView.this.setVisibility(8);
                        MraidView.this.A.setOnPreparedListener((MediaPlayer$OnPreparedListener)new MediaPlayer$OnPreparedListener() {
                            public final void onPrepared(final MediaPlayer mediaPlayer) {
                                TapjoyLog.i("MRAIDView", "** ON PREPARED **");
                                TapjoyLog.i("MRAIDView", "isPlaying: " + mediaPlayer.isPlaying());
                                if (!mediaPlayer.isPlaying()) {
                                    mediaPlayer.start();
                                }
                            }
                        });
                        MraidView.this.A.setOnCompletionListener((MediaPlayer$OnCompletionListener)new MediaPlayer$OnCompletionListener() {
                            public final void onCompletion(final MediaPlayer mediaPlayer) {
                                TapjoyLog.i("MRAIDView", "** ON COMPLETION **");
                                MraidView.this.videoViewCleanup();
                            }
                        });
                        MraidView.this.A.setOnErrorListener((MediaPlayer$OnErrorListener)new MediaPlayer$OnErrorListener() {
                            public final boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
                                TapjoyLog.i("MRAIDView", "** ON ERROR **");
                                MraidView.this.videoViewCleanup();
                                return false;
                            }
                        });
                        MraidView.this.A.start();
                    }
                }
            }
        };
        this.setListener(listener);
        this.y = y;
        this.initialize();
    }
    
    static /* synthetic */ void a(final MraidView mraidView, final String s) {
        mraidView.evaluateJavascript(s, (ValueCallback)null);
    }
    
    private static boolean a(final String s) {
        final boolean b = false;
        final String[] d = MraidView.d;
        final int length = d.length;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= length) {
                break;
            }
            if (s.endsWith(d[n])) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    private static WebResourceResponse b(final TapjoyCachedAssetData tapjoyCachedAssetData) {
        if (tapjoyCachedAssetData == null) {
            return null;
        }
        try {
            return new WebResourceResponse(tapjoyCachedAssetData.getMimeType(), "UTF-8", (InputStream)new FileInputStream(tapjoyCachedAssetData.getLocalFilePath()));
        }
        catch (FileNotFoundException ex) {
            return null;
        }
    }
    
    static /* synthetic */ void b(final MraidView mraidView, final String s) {
        mraidView.loadUrl(s);
    }
    
    static /* synthetic */ void c(final MraidView mraidView, final String s) {
        if (s != null) {
            String s2 = null;
            if (s.equals("top-right")) {
                s2 = "document.getElementById(\"closeButton\").style.right = 1;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = mraid.getSize().width -36";
            }
            else if (s.equals("top-center")) {
                s2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 - 18;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = mraid.getSize().width/2 -18";
            }
            else if (s.equals("top-left")) {
                s2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36;document.getElementById(\"closeButton\").style.top = 1;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.left = 1";
            }
            else if (s.equals("center")) {
                s2 = "document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 - 18;document.getElementById(\"closeButton\").style.top = mraid.getSize().height/2 -18;document.getElementById(\"closeButton\").style.bottom = mraid.getSize().height/2 -18;document.getElementById(\"closeButton\").style.left = mraid.getSize().width/2 -18";
            }
            else if (s.equals("bottom-right")) {
                s2 = "document.getElementById(\"closeButton\").style.right = 1;document.getElementById(\"closeButton\").style.top = mraid.getSize().height -36;document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.left = mraid.getSize().width -36";
            }
            else if (s.equals("bottom-left")) {
                s2 = "document.getElementById(\"closeButton\").style.left = 1;document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36;document.getElementById(\"closeButton\").style.top = mraid.getSize().height-36;";
            }
            else if (s.equals("bottom-center")) {
                s2 = "document.getElementById(\"closeButton\").style.bottom = 1;document.getElementById(\"closeButton\").style.right = mraid.getSize().width -36document.getElementById(\"closeButton\").style.right = mraid.getSize().width/2 -18;document.getElementById(\"closeButton\").style.top = mraid.getSize().height-36;";
            }
            if (s2 == null) {
                TapjoyLog.d("MRAIDView", "Reposition of close button failed.");
                return;
            }
            mraidView.injectMraidJavaScript(s2);
        }
    }
    
    static /* synthetic */ void f(final MraidView mraidView) {
        try {
            if (mraidView.r != null) {
                mraidView.r.onClose();
            }
            ((ViewGroup)mraidView.getParent()).removeView((View)mraidView);
        }
        catch (Exception ex) {}
    }
    
    private int getContentViewHeight() {
        final View viewById = this.getRootView().findViewById(16908290);
        if (viewById != null) {
            return viewById.getHeight();
        }
        return -1;
    }
    
    static /* synthetic */ void m(final MraidView mraidView) {
        final WindowManager windowManager = (WindowManager)mraidView.getContext().getSystemService("window");
        final int width = windowManager.getDefaultDisplay().getWidth();
        final int height = windowManager.getDefaultDisplay().getHeight();
        if ((width != mraidView.t || height != mraidView.u) && mraidView.getPlacementType() == PLACEMENT_TYPE.INTERSTITIAL) {
            mraidView.resizeOrientation(width, height, "top-right", true);
        }
    }
    
    public void addJavascriptObject(final Object o, final String s) {
        this.addJavascriptInterface(o, s);
    }
    
    public void clearView() {
        this.reset();
        super.clearView();
    }
    
    public void close() {
        this.D.sendEmptyMessage(1001);
    }
    
    public void createCloseImageButton() {
        this.injectMraidJavaScript("window.mraidview.createCss();");
        TapjoyLog.d("MRAIDView", "Creating close button.");
    }
    
    public customCloseState getCloseButtonState() {
        return this.e;
    }
    
    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager)this.getContext().getSystemService("connectivity");
    }
    
    public PLACEMENT_TYPE getPlacementType() {
        return this.p;
    }
    
    MraidPlayer getPlayer() {
        if (MraidView.s != null) {
            MraidView.s.releasePlayer();
        }
        return MraidView.s = new MraidPlayer(this.getContext());
    }
    
    public String getSize() {
        return "{ width: " + (int)Math.ceil(this.getWidth() / this.i) + ", height: " + (int)Math.ceil(this.getHeight() / this.i) + "}";
    }
    
    public String getState() {
        return this.q.toString().toLowerCase();
    }
    
    public VIEW_STATE getViewState() {
        return this.q;
    }
    
    public boolean hasMraidTag(final String s) {
        return Pattern.compile("<\\s*script[^>]+mraid\\.js").matcher(s).find();
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    public void initialize() {
        this.setPlacementType(PLACEMENT_TYPE.INTERSTITIAL);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)this.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.i = displayMetrics.density;
        this.g = false;
        this.addJavascriptInterface((Object)(this.h = new Utility(this, this.getContext())), "MRAIDUtilityControllerBridge");
        this.setWebViewClient(this.a);
        this.setWebChromeClient(this.b);
        this.j = this.getContentViewHeight();
        if (this.getViewTreeObserver() != null) {
            this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
        }
        final WindowManager windowManager = (WindowManager)this.getContext().getSystemService("window");
        this.t = windowManager.getDefaultDisplay().getWidth();
        this.u = windowManager.getDefaultDisplay().getHeight();
        if (this.getContext() instanceof Activity) {
            this.x = ((Activity)this.getContext()).getRequestedOrientation();
        }
    }
    
    public void injectMraidJavaScript(final String s) {
        if (s != null && this.f) {
            this.loadUrl("javascript:" + s);
        }
    }
    
    public boolean isMraid() {
        return this.f;
    }
    
    public boolean isPageFinished() {
        return this.g;
    }
    
    public void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        if (s2 == null) {
            return;
        }
        final StringBuffer sb = new StringBuffer();
        final int index = s2.indexOf("<html>");
        this.f = false;
        final int index2 = s2.indexOf("mraid.js");
        Label_0307: {
            if (index2 > 0 && this.hasMraidTag(s2)) {
                this.f = true;
                while (true) {
                    for (int i = index2; i >= 0; --i) {
                        if (s2.substring(i, i + 7).equals("<script")) {
                            final int n = i;
                            int n2 = 0;
                            while (true) {
                                Block_10: {
                                    int n3;
                                    while (true) {
                                        n3 = index2;
                                        if (n2 >= s2.length()) {
                                            break;
                                        }
                                        if (s2.substring(index2 + n2, index2 + n2 + 2).equalsIgnoreCase("/>")) {
                                            n3 = index2 + n2 + 2;
                                            break;
                                        }
                                        if (s2.substring(index2 + n2, index2 + n2 + 9).equalsIgnoreCase("</script>")) {
                                            break Block_10;
                                        }
                                        ++n2;
                                    }
                                    if (index < 0) {
                                        TapjoyLog.d("MRAIDView", "wrapping fragment");
                                        sb.append("<html>");
                                        sb.append("<head>");
                                        sb.append("<meta name='viewport' content='user-scalable=no initial-scale=1.0' />");
                                        sb.append("<title>Advertisement</title>");
                                        sb.append("</head>");
                                        sb.append("<body style=\"margin:0; padding:0; overflow:hidden; background-color:transparent;\">");
                                        sb.append("<div align=\"center\"> ");
                                        sb.append(s2.substring(0, n));
                                        sb.append("<script type=text/javascript>");
                                        String copyTextFromJarIntoString;
                                        if ((copyTextFromJarIntoString = (String)TapjoyUtil.getResource("mraid.js")) == null) {
                                            copyTextFromJarIntoString = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", this.getContext());
                                        }
                                        sb.append(copyTextFromJarIntoString);
                                        sb.append("</script>");
                                        sb.append(s2.substring(n3));
                                    }
                                    else {
                                        final int index3 = s2.indexOf("<head>");
                                        if (index3 != -1) {
                                            String copyTextFromJarIntoString2;
                                            if ((copyTextFromJarIntoString2 = (String)TapjoyUtil.getResource("mraid.js")) == null) {
                                                copyTextFromJarIntoString2 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", this.getContext());
                                            }
                                            sb.append(s2.substring(0, index3 + 6));
                                            sb.append("<script type='text/javascript'>");
                                            sb.append(copyTextFromJarIntoString2);
                                            sb.append("</script>");
                                            sb.append(s2.substring(index3 + 6));
                                        }
                                    }
                                    TapjoyLog.d("MRAIDView", "injected js/mraid.js");
                                    break Label_0307;
                                }
                                int n3 = index2 + n2 + 9;
                                continue;
                            }
                        }
                    }
                    final int n = index2;
                    continue;
                }
            }
            sb.append(s2);
        }
        super.loadDataWithBaseURL(s, sb.toString(), s3, s4, s5);
    }
    
    public void loadUrl(final String s) {
        ((Activity)this.y).runOnUiThread((Runnable)new Runnable() {
            @Override
            public final void run() {
                if (!URLUtil.isValidUrl(s)) {
                    MraidView.this.loadDataWithBaseURL(null, "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\"><title>Connection not Established</title></head><h2>Connection Not Properly Established</h2><body></body></html>", "text/html", "utf-8", null);
                    return;
                }
                if (s.startsWith("javascript:")) {
                    if (Build$VERSION.SDK_INT >= 19) {
                        try {
                            MraidView.a(MraidView.this, s.replaceFirst("javascript:", ""));
                            return;
                        }
                        catch (Exception ex) {
                            TapjoyLog.e("MRAIDView", "Exception in evaluateJavascript. Device not supported. " + ex.toString());
                            return;
                        }
                    }
                    MraidView.b(MraidView.this, s);
                    return;
                }
                new MraidView.a((byte)0).execute((Object[])new String[] { s });
            }
        });
    }
    
    public void loadUrlStandard(final String s) {
        super.loadUrl(s);
    }
    
    protected void onAttachedToWindow() {
        if (!this.E) {
            final ViewGroup$LayoutParams layoutParams = this.getLayoutParams();
            this.n = layoutParams.height;
            this.o = layoutParams.width;
            this.E = true;
        }
        this.w = false;
        if (this.v == null || !this.v.isAlive()) {
            (this.v = new Thread(new b())).start();
        }
        super.onAttachedToWindow();
    }
    
    protected void onDetachedFromWindow() {
        this.w = true;
        this.h.stopAllListeners();
        while (true) {
            try {
                if (this.A != null) {
                    this.A.stopPlayback();
                }
                if (this.B != null) {
                    this.B.onCustomViewHidden();
                }
                super.onDetachedFromWindow();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public void onGlobalLayout() {
        boolean k;
        final boolean b = k = this.k;
        if (!this.k) {
            k = b;
            if (this.j >= 0) {
                k = b;
                if (this.getContentViewHeight() >= 0) {
                    k = b;
                    if (this.j != this.getContentViewHeight()) {
                        k = true;
                        this.injectMraidJavaScript("window.mraidview.fireChangeEvent({ keyboardState: true});");
                    }
                }
            }
        }
        boolean i = k;
        if (this.k) {
            i = k;
            if (this.j >= 0) {
                i = k;
                if (this.getContentViewHeight() >= 0) {
                    i = k;
                    if (this.j == this.getContentViewHeight()) {
                        i = false;
                        this.injectMraidJavaScript("window.mraidview.fireChangeEvent({ keyboardState: false});");
                    }
                }
            }
        }
        if (this.j < 0) {
            this.j = this.getContentViewHeight();
        }
        this.k = i;
    }
    
    public void open(final String s, final boolean b, final boolean b2, final boolean b3) {
        String redirectURL = null;
        int n;
        if (a(s)) {
            redirectURL = s;
            n = 1;
        }
        else {
            final TapjoyHttpURLResponse redirectFromURL = new TapjoyURLConnection().getRedirectFromURL(s);
            TapjoyLog.i("MRAIDView", "redirect: " + redirectFromURL.redirectURL + ", " + redirectFromURL.statusCode);
            if (redirectFromURL != null && redirectFromURL.redirectURL != null && redirectFromURL.redirectURL.length() > 0 && a(redirectFromURL.redirectURL)) {
                redirectURL = redirectFromURL.redirectURL;
                n = 1;
            }
            else {
                n = 0;
            }
        }
        if (n != 0) {
            final Abstract.Dimensions dimensions = new Abstract.Dimensions();
            dimensions.x = 0;
            dimensions.y = 0;
            dimensions.width = this.getWidth();
            dimensions.height = this.getHeight();
            this.playVideo(redirectURL, false, true, true, false, dimensions, "fullscreen", "exit");
            return;
        }
        TapjoyLog.d("MRAIDView", "Mraid Browser open:" + s);
        final Intent intent = new Intent(this.getContext(), (Class)Browser.class);
        intent.putExtra("extra_url", s);
        intent.putExtra("open_show_back", b);
        intent.putExtra("open_show_forward", b2);
        intent.putExtra("open_show_refresh", b3);
        intent.addFlags(268435456);
        this.getContext().startActivity(intent);
    }
    
    public void openMap(String convert, final boolean b) {
        TapjoyLog.d("MRAIDView", "Opening Map Url " + convert);
        convert = Utils.convert(convert.trim());
        if (!b) {
            return;
        }
        try {
            final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(convert));
            intent.setFlags(268435456);
            this.getContext().startActivity(intent);
        }
        catch (ActivityNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public void playAudio(final String s, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s2, final String s3) {
        final Abstract.PlayerProperties playerProperties = new Abstract.PlayerProperties();
        playerProperties.setProperties(false, b, b2, b4, b3, s2, s3);
        final Bundle data = new Bundle();
        data.putString("action", Action.PLAY_AUDIO.toString());
        data.putString("expand_url", s);
        data.putParcelable("player_properties", (Parcelable)playerProperties);
        if (playerProperties.isFullScreen()) {
            try {
                final Intent intent = new Intent(this.getContext(), (Class)ActionHandler.class);
                intent.putExtras(data);
                this.getContext().startActivity(intent);
                return;
            }
            catch (ActivityNotFoundException ex) {
                ex.printStackTrace();
                return;
            }
        }
        final Message obtainMessage = this.D.obtainMessage(1008);
        obtainMessage.setData(data);
        this.D.sendMessage(obtainMessage);
    }
    
    public void playAudioImpl(final Bundle bundle) {
        final Abstract.PlayerProperties playerProperties = (Abstract.PlayerProperties)bundle.getParcelable("player_properties");
        final String string = bundle.getString("expand_url");
        final MraidPlayer player = this.getPlayer();
        player.setPlayData(playerProperties, string);
        player.setLayoutParams(new ViewGroup$LayoutParams(1, 1));
        ((ViewGroup)this.getParent()).addView((View)player);
        player.playAudio();
    }
    
    public void playVideo(final String s, final boolean b, final boolean b2, final boolean b3, final boolean b4, final Abstract.Dimensions dimensions, final String s2, final String s3) {
        final Message obtainMessage = this.D.obtainMessage(1007);
        final Abstract.PlayerProperties playerProperties = new Abstract.PlayerProperties();
        playerProperties.setProperties(b, b2, b3, false, b4, s2, s3);
        final Bundle data = new Bundle();
        data.putString("expand_url", s);
        data.putString("action", Action.PLAY_VIDEO.toString());
        data.putParcelable("player_properties", (Parcelable)playerProperties);
        if (dimensions != null) {
            data.putParcelable("expand_dimensions", (Parcelable)dimensions);
        }
        Label_0143: {
            if (!playerProperties.isFullScreen()) {
                break Label_0143;
            }
            try {
                final Intent intent = new Intent(this.getContext(), (Class)ActionHandler.class);
                intent.putExtras(data);
                intent.setFlags(268435456);
                this.getContext().startActivity(intent);
                return;
            }
            catch (ActivityNotFoundException ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (dimensions != null) {
            obtainMessage.setData(data);
            this.D.sendMessage(obtainMessage);
        }
    }
    
    public void playVideoImpl(final Bundle bundle) {
        final Abstract.PlayerProperties playerProperties = (Abstract.PlayerProperties)bundle.getParcelable("player_properties");
        final Abstract.Dimensions dimensions = (Abstract.Dimensions)bundle.getParcelable("expand_dimensions");
        final String string = bundle.getString("expand_url");
        final MraidPlayer player = this.getPlayer();
        player.setPlayData(playerProperties, string);
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(dimensions.width, dimensions.height);
        layoutParams.topMargin = dimensions.x;
        layoutParams.leftMargin = dimensions.y;
        player.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        final FrameLayout frameLayout = new FrameLayout(this.getContext());
        frameLayout.setId(101);
        frameLayout.setPadding(dimensions.x, dimensions.y, 0, 0);
        ((FrameLayout)this.getRootView().findViewById(16908290)).addView((View)frameLayout, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        frameLayout.addView((View)player);
        this.setVisibility(4);
        player.setListener(new Player() {
            @Override
            public final void onComplete() {
                final FrameLayout frameLayout = (FrameLayout)MraidView.this.getRootView().findViewById(101);
                ((ViewGroup)frameLayout.getParent()).removeView((View)frameLayout);
                MraidView.this.setVisibility(0);
            }
            
            @Override
            public final void onError() {
                this.onComplete();
            }
            
            @Override
            public final void onPrepared() {
            }
        });
        player.playVideo();
    }
    
    public void raiseError(final String s, final String s2) {
        final Message obtainMessage = this.D.obtainMessage(1009);
        final Bundle data = new Bundle();
        data.putString("message", s);
        data.putString("action", s2);
        obtainMessage.setData(data);
        this.D.sendMessage(obtainMessage);
    }
    
    public void removeCloseImageButton() {
        this.injectMraidJavaScript("document.getElementById(\"closeButton\").style.visibility=\"hidden\";");
        TapjoyLog.d("MRAIDView", "Removing close button.");
        this.e = customCloseState.HIDDEN;
    }
    
    public void removeListener() {
        this.r = null;
    }
    
    public void reset() {
        this.invalidate();
        this.h.deleteOldAds();
        this.h.stopAllListeners();
        final ViewGroup$LayoutParams layoutParams = this.getLayoutParams();
        if (this.E) {
            layoutParams.height = this.n;
            layoutParams.width = this.o;
        }
        this.setVisibility(0);
        this.requestLayout();
    }
    
    public void resizeOrientation(final int t, final int u, final String s, final boolean b) {
        this.t = t;
        this.u = u;
        TapjoyLog.d("MRAIDView", "resizeOrientation to dimensions: " + t + "x" + u);
        final Message obtainMessage = this.D.obtainMessage(1010);
        final Bundle data = new Bundle();
        data.putInt("resize_width", t);
        data.putInt("resize_height", u);
        data.putBoolean("resize_allowOffScreen", b);
        data.putString("resize_customClosePostition", s);
        obtainMessage.setData(data);
        this.D.sendMessage(obtainMessage);
    }
    
    public WebBackForwardList restoreState(final Bundle bundle) {
        return super.restoreState(bundle);
    }
    
    public WebBackForwardList saveState(final Bundle bundle) {
        return super.saveState(bundle);
    }
    
    public void setContext(final Context y) {
        this.y = y;
    }
    
    public void setListener(final MraidViewListener r) {
        this.r = r;
    }
    
    public void setMaxSize(final int n, final int n2) {
        this.h.setMaxSize(n, n2);
    }
    
    public void setOrientationProperties(final boolean b, final String s) {
        int requestedOrientation;
        if (!b) {
            if (s.equals("landscape")) {
                requestedOrientation = 0;
            }
            else {
                requestedOrientation = 1;
            }
        }
        else {
            requestedOrientation = -1;
        }
        ((Activity)this.getContext()).setRequestedOrientation(requestedOrientation);
    }
    
    public void setPlacementType(final PLACEMENT_TYPE p) {
        if (p.equals(PLACEMENT_TYPE.INLINE) || p.equals(PLACEMENT_TYPE.INTERSTITIAL)) {
            this.p = p;
        }
        else {
            TapjoyLog.d("MRAIDView", "Incorrect placement type.");
        }
        if (p.equals(PLACEMENT_TYPE.INLINE) && (this.v == null || !this.v.isAlive())) {
            (this.v = new Thread(new b())).start();
        }
    }
    
    public void show() {
        this.D.sendEmptyMessage(1003);
    }
    
    public void showCloseImageButton() {
        this.injectMraidJavaScript("document.getElementById(\"closeButton\").style.visibility=\"visible\";");
        TapjoyLog.d("MRAIDView", "Showing close button.");
        this.e = customCloseState.OPEN;
    }
    
    public boolean videoPlaying() {
        return this.A != null;
    }
    
    public void videoViewCleanup() {
        if (this.z != null) {
            ((ViewGroup)this.z.getParent()).removeView((View)this.z);
            this.z.setVisibility(8);
            this.z = null;
        }
        while (true) {
            try {
                if (this.A != null) {
                    this.A.stopPlayback();
                }
                if (this.B != null) {
                    this.B.onCustomViewHidden();
                }
                this.A = null;
                this.B = null;
                if (this != null) {
                    this.setVisibility(0);
                }
                this.loadUrl("javascript:try{Tapjoy.AdUnit.dispatchEvent('videoend')}catch(e){}");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public enum Action
    {
        PLAY_AUDIO("PLAY_AUDIO", 0), 
        PLAY_VIDEO("PLAY_VIDEO", 1);
        
        private Action(final String s, final int n) {
        }
    }
    
    public enum PLACEMENT_TYPE
    {
        INLINE("INLINE", 0), 
        INTERSTITIAL("INTERSTITIAL", 1);
        
        private PLACEMENT_TYPE(final String s, final int n) {
        }
    }
    
    public enum VIEW_STATE
    {
        DEFAULT("DEFAULT", 0), 
        LEFT_BEHIND("LEFT_BEHIND", 1), 
        OPENED("OPENED", 2);
        
        private VIEW_STATE(final String s, final int n) {
        }
    }
    
    final class a extends AsyncTask
    {
        TapjoyHttpURLResponse a;
        TapjoyURLConnection b;
        String c;
        
        private a() {
        }
        
        private Void a(final String... array) {
            this.c = array[0];
            try {
                this.b = new TapjoyURLConnection();
                this.a = this.b.getResponseFromURL(this.c);
                return null;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
    
    final class b implements Runnable
    {
        public b() {
        }
        
        @Override
        public final void run() {
            while (!MraidView.this.w) {
                try {
                    Thread.sleep(250L);
                    MraidView.m(MraidView.this);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    final class c implements Runnable
    {
        public c() {
        }
        
        @Override
        public final void run() {
            int n = 0;
            while (true) {
                Label_0045: {
                    if (MraidView.this.A == null || MraidView.this.A.isPlaying()) {
                        break Label_0045;
                    }
                    try {
                        Thread.sleep(50L);
                        if ((n += 50) >= 10000) {
                            ((Activity)MraidView.this.y).runOnUiThread((Runnable)new Runnable() {
                                @Override
                                public final void run() {
                                    if (MraidView.this.C != null) {
                                        MraidView.this.C.setVisibility(8);
                                    }
                                    new Thread(new a()).start();
                                }
                            });
                            return;
                        }
                        continue;
                    }
                    catch (Exception ex) {}
                }
            }
        }
        
        final class a implements Runnable
        {
            private boolean b;
            
            public a() {
                this.b = false;
            }
            
            @Override
            public final void run() {
                while (true) {
                    if (MraidView.this.A == null) {
                        return;
                    }
                    try {
                        Thread.sleep(100L);
                        if (this.b != MraidView.this.A.isPlaying()) {
                            this.b = MraidView.this.A.isPlaying();
                            String s;
                            if (this.b) {
                                s = "videoplay";
                            }
                            else {
                                s = "videopause";
                            }
                            MraidView.this.loadUrl("javascript:try{Tapjoy.AdUnit.dispatchEvent('" + s + "')}catch(e){}");
                            continue;
                        }
                        continue;
                    }
                    catch (Exception ex) {}
                }
            }
        }
    }
    
    public enum customCloseState
    {
        HIDDEN("HIDDEN", 0), 
        OPEN("OPEN", 1), 
        UNKNOWN("UNKNOWN", 2);
        
        private customCloseState(final String s, final int n) {
        }
    }
}
