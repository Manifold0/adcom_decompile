// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.webkit.WebSettings;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import java.util.HashSet;
import com.tapjoy.mraid.view.BasicWebView;
import android.widget.RelativeLayout;

public class TJSplitWebView extends RelativeLayout
{
    private BasicWebView a;
    private double[] b;
    private double[] c;
    private String d;
    private String e;
    private HashSet f;
    private TJAdUnitJSBridge g;
    
    public TJSplitWebView(final Context context, final JSONObject layoutOption, final JSONArray exitHosts, final TJAdUnitJSBridge g) {
        super(context);
        this.b = new double[] { 0.0, 0.0, 0.0, 0.0 };
        this.c = new double[] { 0.0, 0.0, 0.0, 0.0 };
        this.g = g;
        this.setLayoutOption(layoutOption);
        this.setExitHosts(exitHosts);
        (this.a = new BasicWebView(context)).setBackgroundColor(-1);
        final WebSettings settings = this.a.getSettings();
        if (settings != null) {
            settings.setUseWideViewPort(true);
        }
        this.a.setWebViewClient((WebViewClient)new WebViewClient() {
            public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
                TapjoyLog.d("TJSplitWebView", "onReceivedError: " + s2 + " firstUrl:" + TJSplitWebView.this.d);
                if (s2.equals(TJSplitWebView.this.d)) {
                    TJSplitWebView.this.a();
                }
            }
            
            public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
                TapjoyLog.d("TJSplitWebView", "shouldOverrideUrlLoading: " + s);
                final Uri parse = Uri.parse(s);
                if (parse != null) {
                    final String host = parse.getHost();
                    final String scheme = parse.getScheme();
                    if (scheme != null && host != null && (scheme.equals("http") || scheme.equals("https")) && (TJSplitWebView.this.f == null || !TJSplitWebView.this.f.contains(host))) {
                        TJSplitWebView.this.e = s;
                        return false;
                    }
                }
                try {
                    context.startActivity(new Intent("android.intent.action.VIEW", parse));
                    TJSplitWebView.this.a();
                    return true;
                }
                catch (Exception ex) {
                    TapjoyLog.e("TJSplitWebView", ex.getMessage());
                    return true;
                }
            }
        });
        this.addView((View)this.a);
    }
    
    private void a(final int n, final int n2) {
        double[] array;
        if (n <= n2) {
            array = this.b;
        }
        else {
            array = this.c;
        }
        final int n3 = (int)(n * array[0]);
        final int n4 = (int)(n2 * array[1]);
        final int n5 = (int)(n * array[2]);
        final int n6 = (int)(n2 * array[3]);
        if (n3 == 0 || n4 == 0) {
            this.a.setVisibility(4);
            return;
        }
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(n3, n4);
        layoutParams.setMargins(n5, n6, n - n3 - n5, n2 - n4 - n6);
        this.a.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.a.setVisibility(0);
    }
    
    protected final void a() {
        this.g.dismissSplitView(null, null);
    }
    
    public void applyLayoutOption(final JSONObject layoutOption) {
        this.setLayoutOption(layoutOption);
        this.a(this.getWidth(), this.getHeight());
    }
    
    public String getLastUrl() {
        return this.e;
    }
    
    public void loadUrl(final String s) {
        if (this.a != null) {
            this.d = s;
            this.e = s;
            this.a.loadUrl(s);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.a(View$MeasureSpec.getSize(n), View$MeasureSpec.getSize(n2));
        super.onMeasure(n, n2);
    }
    
    public void setExitHosts(final JSONArray jsonArray) {
        if (jsonArray == null) {
            this.f = null;
        }
        else {
            this.f = new HashSet();
            for (int i = 0; i <= jsonArray.length(); ++i) {
                final String optString = jsonArray.optString(i);
                if (optString != null) {
                    this.f.add(optString);
                }
            }
        }
    }
    
    protected void setLayoutOption(JSONObject optJSONObject) {
        if (optJSONObject != null) {
            final JSONObject optJSONObject2 = optJSONObject.optJSONObject("landscape");
            if (optJSONObject2 != null) {
                this.c[0] = optJSONObject2.optDouble("width", 0.0);
                this.c[1] = optJSONObject2.optDouble("height", 0.0);
                this.c[2] = optJSONObject2.optDouble("left", 0.0);
                this.c[3] = optJSONObject2.optDouble("top", 0.0);
            }
            optJSONObject = optJSONObject.optJSONObject("portrait");
            if (optJSONObject != null) {
                this.b[0] = optJSONObject.optDouble("width", 0.0);
                this.b[1] = optJSONObject.optDouble("height", 0.0);
                this.b[2] = optJSONObject.optDouble("left", 0.0);
                this.b[3] = optJSONObject.optDouble("top", 0.0);
            }
        }
    }
}
