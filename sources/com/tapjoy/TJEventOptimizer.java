package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import java.util.concurrent.CountDownLatch;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJEventOptimizer extends WebView {
    /* renamed from: a */
    private static String f6953a = "TJEventOptimizer";
    /* renamed from: b */
    private static TJEventOptimizer f6954b;
    /* renamed from: c */
    private static CountDownLatch f6955c;
    /* renamed from: d */
    private Context f6956d;
    /* renamed from: e */
    private TJAdUnitJSBridge f6957e;

    /* renamed from: com.tapjoy.TJEventOptimizer$a */
    class C2813a extends WebChromeClient {
        /* renamed from: a */
        final /* synthetic */ TJEventOptimizer f6951a;

        private C2813a(TJEventOptimizer tJEventOptimizer) {
            this.f6951a = tJEventOptimizer;
        }

        @TargetApi(8)
        public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            TapjoyLog.m7126d(TJEventOptimizer.f6953a, "JS CONSOLE: " + consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return true;
        }
    }

    /* renamed from: com.tapjoy.TJEventOptimizer$b */
    class C2814b extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ TJEventOptimizer f6952a;

        private C2814b(TJEventOptimizer tJEventOptimizer) {
            this.f6952a = tJEventOptimizer;
        }

        public final void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            TapjoyLog.m7127e(TJEventOptimizer.f6953a, new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Error encountered when instantiating a WebViewClient"));
        }

        public final void onPageFinished(WebView view, String url) {
            TapjoyLog.m7126d(TJEventOptimizer.f6953a, "boostrap html loaded successfully");
        }
    }

    private TJEventOptimizer(Context context) {
        super(context);
        this.f6956d = context;
        this.f6957e = new TJAdUnitJSBridge(this.f6956d, (WebView) this);
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new C2814b());
        setWebChromeClient(new C2813a());
        loadUrl(TapjoyConnectCore.getHostURL() + TJAdUnitConstants.EVENTS_PROXY_PATH + TapjoyUtil.convertURLParams(TapjoyConnectCore.getGenericURLParams(), true));
    }

    public static void init(final Context context) {
        TapjoyLog.m7126d(f6953a, "Initializing event optimizer");
        f6955c = new CountDownLatch(1);
        TapjoyUtil.runOnMainThread(new Runnable() {
            public final void run() {
                try {
                    TJEventOptimizer.f6954b = new TJEventOptimizer(context);
                } catch (Exception e) {
                    TapjoyLog.m7131w(TJEventOptimizer.f6953a, e.getMessage());
                }
                TJEventOptimizer.f6955c.countDown();
            }
        });
        f6955c.await();
        if (f6954b == null) {
            throw new RuntimeException("Failed to init TJEventOptimizer");
        }
    }

    public static TJEventOptimizer getInstance() {
        return f6954b;
    }
}
