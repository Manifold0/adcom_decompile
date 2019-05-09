package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.adjust.sdk.Constants;
import com.tapjoy.mraid.view.BasicWebView;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

public class TJSplitWebView extends RelativeLayout {
    /* renamed from: a */
    private BasicWebView f6986a;
    /* renamed from: b */
    private double[] f6987b = new double[]{0.0d, 0.0d, 0.0d, 0.0d};
    /* renamed from: c */
    private double[] f6988c = new double[]{0.0d, 0.0d, 0.0d, 0.0d};
    /* renamed from: d */
    private String f6989d;
    /* renamed from: e */
    private String f6990e;
    /* renamed from: f */
    private HashSet f6991f;
    /* renamed from: g */
    private TJAdUnitJSBridge f6992g;

    public TJSplitWebView(final Context context, JSONObject layout, JSONArray exitHosts, TJAdUnitJSBridge bridge) {
        super(context);
        this.f6992g = bridge;
        setLayoutOption(layout);
        setExitHosts(exitHosts);
        this.f6986a = new BasicWebView(context);
        this.f6986a.setBackgroundColor(-1);
        WebSettings settings = this.f6986a.getSettings();
        if (settings != null) {
            settings.setUseWideViewPort(true);
        }
        this.f6986a.setWebViewClient(new WebViewClient(this) {
            /* renamed from: b */
            final /* synthetic */ TJSplitWebView f6985b;

            public final boolean shouldOverrideUrlLoading(WebView view, String url) {
                TapjoyLog.m7126d("TJSplitWebView", "shouldOverrideUrlLoading: " + url);
                Uri parse = Uri.parse(url);
                if (parse != null) {
                    String host = parse.getHost();
                    String scheme = parse.getScheme();
                    if (!(scheme == null || host == null || ((!scheme.equals("http") && !scheme.equals(Constants.SCHEME)) || (this.f6985b.f6991f != null && this.f6985b.f6991f.contains(host))))) {
                        this.f6985b.f6990e = url;
                        return false;
                    }
                }
                try {
                    context.startActivity(new Intent("android.intent.action.VIEW", parse));
                    this.f6985b.m7086a();
                } catch (Exception e) {
                    TapjoyLog.m7128e("TJSplitWebView", e.getMessage());
                }
                return true;
            }

            public final void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                TapjoyLog.m7126d("TJSplitWebView", "onReceivedError: " + failingUrl + " firstUrl:" + this.f6985b.f6989d);
                if (failingUrl.equals(this.f6985b.f6989d)) {
                    this.f6985b.m7086a();
                }
            }
        });
        addView(this.f6986a);
    }

    public void applyLayoutOption(JSONObject layout) {
        setLayoutOption(layout);
        m7084a(getWidth(), getHeight());
    }

    public void setExitHosts(JSONArray array) {
        if (array == null) {
            this.f6991f = null;
            return;
        }
        this.f6991f = new HashSet();
        for (int i = 0; i <= array.length(); i++) {
            String optString = array.optString(i);
            if (optString != null) {
                this.f6991f.add(optString);
            }
        }
    }

    public void loadUrl(String url) {
        if (this.f6986a != null) {
            this.f6989d = url;
            this.f6990e = url;
            this.f6986a.loadUrl(url);
        }
    }

    public String getLastUrl() {
        return this.f6990e;
    }

    /* renamed from: a */
    protected final void m7086a() {
        this.f6992g.dismissSplitView(null, null);
    }

    protected void setLayoutOption(JSONObject layout) {
        if (layout != null) {
            JSONObject optJSONObject = layout.optJSONObject("landscape");
            if (optJSONObject != null) {
                this.f6988c[0] = optJSONObject.optDouble("width", 0.0d);
                this.f6988c[1] = optJSONObject.optDouble("height", 0.0d);
                this.f6988c[2] = optJSONObject.optDouble("left", 0.0d);
                this.f6988c[3] = optJSONObject.optDouble("top", 0.0d);
            }
            optJSONObject = layout.optJSONObject("portrait");
            if (optJSONObject != null) {
                this.f6987b[0] = optJSONObject.optDouble("width", 0.0d);
                this.f6987b[1] = optJSONObject.optDouble("height", 0.0d);
                this.f6987b[2] = optJSONObject.optDouble("left", 0.0d);
                this.f6987b[3] = optJSONObject.optDouble("top", 0.0d);
            }
        }
    }

    /* renamed from: a */
    private void m7084a(int i, int i2) {
        double[] dArr;
        if (i <= i2) {
            dArr = this.f6987b;
        } else {
            dArr = this.f6988c;
        }
        int i3 = (int) (((double) i) * dArr[0]);
        int i4 = (int) (((double) i2) * dArr[1]);
        int i5 = (int) (((double) i) * dArr[2]);
        int i6 = (int) (((double) i2) * dArr[3]);
        int i7 = (i - i3) - i5;
        int i8 = (i2 - i4) - i6;
        if (i3 == 0 || i4 == 0) {
            this.f6986a.setVisibility(4);
            return;
        }
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i5, i6, i7, i8);
        this.f6986a.setLayoutParams(layoutParams);
        this.f6986a.setVisibility(0);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        m7084a(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
