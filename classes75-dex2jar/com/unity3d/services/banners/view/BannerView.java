// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners.view;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import android.graphics.Rect;
import com.unity3d.services.core.webview.WebViewEventCategory;
import android.view.ViewParent;
import android.view.ViewGroup;
import com.unity3d.services.core.misc.ViewUtilities;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.log.DeviceLog;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import android.view.View$OnLayoutChangeListener;
import android.os.Build$VERSION;
import android.content.Context;
import com.unity3d.services.ads.webplayer.WebPlayer;
import java.util.List;
import org.json.JSONObject;
import android.widget.RelativeLayout;

public class BannerView extends RelativeLayout
{
    private static final String VIEW_BANNER = "banner";
    private static final String VIEW_BANNER_PLAYER = "bannerplayer";
    private static final String VIEW_WEB_VIEW = "webview";
    private static BannerView _instance;
    private static JSONObject _webPlayerEventSettings;
    private static JSONObject _webPlayerSettings;
    private static JSONObject _webSettings;
    private int _lastVisibility;
    private List<String> _views;
    private WebPlayer _webPlayer;
    private int height;
    private BannerPosition position;
    private int width;
    
    static {
        BannerView._webSettings = new JSONObject();
        BannerView._webPlayerSettings = new JSONObject();
        BannerView._webPlayerEventSettings = new JSONObject();
    }
    
    public BannerView(final Context context) {
        super(context);
        this._lastVisibility = -1;
        (this._webPlayer = new WebPlayer(context, "bannerplayer", BannerView._webSettings, BannerView._webPlayerSettings)).setEventSettings(BannerView._webPlayerEventSettings);
        if (Build$VERSION.SDK_INT >= 11) {
            this.addOnLayoutChangeListener((View$OnLayoutChangeListener)new View$OnLayoutChangeListener() {
                public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
                    BannerView.this.onLayoutChange(view, n, n2, n3, n4, n5, n6, n7, n8);
                }
            });
        }
    }
    
    private void addView(final String s) {
        final View viewForName = this.getViewForName(s);
        if (viewForName != null) {
            this.addView(viewForName, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            return;
        }
        DeviceLog.warning("No view defined for viewName: %s", s);
    }
    
    public static BannerView getInstance() {
        return BannerView._instance;
    }
    
    public static BannerView getOrCreateInstance() {
        if (BannerView._instance == null) {
            BannerView._instance = new BannerView(ClientProperties.getApplicationContext());
        }
        return BannerView._instance;
    }
    
    private View getViewForName(final String s) {
        Object webPlayer;
        if (s.equals("bannerplayer")) {
            webPlayer = this._webPlayer;
        }
        else {
            if (s.equals("webview")) {
                return (View)WebViewApp.getCurrentApp().getWebView();
            }
            webPlayer = this;
            if (!s.equals("banner")) {
                return null;
            }
        }
        return (View)webPlayer;
    }
    
    private void removeView(final String s) {
        final View viewForName = this.getViewForName(s);
        if (viewForName != null) {
            ViewUtilities.removeViewFromParent(viewForName);
        }
        switch (s) {
            default: {}
            case "bannerplayer": {
                this._webPlayer = null;
            }
        }
    }
    
    public static void setWebPlayerEventSettings(final JSONObject webPlayerEventSettings) {
        BannerView._webPlayerEventSettings = webPlayerEventSettings;
    }
    
    public static void setWebPlayerSettings(final JSONObject webSettings, final JSONObject webPlayerSettings) {
        BannerView._webSettings = webSettings;
        BannerView._webPlayerSettings = webPlayerSettings;
    }
    
    public void destroy() {
        this.removeAllViews();
        final ViewParent parent = this.getParent();
        if (parent != null && parent instanceof ViewGroup) {
            ((ViewGroup)parent).removeView((View)this);
        }
        this._webPlayer = null;
        BannerView._instance = null;
    }
    
    public WebPlayer getWebPlayer() {
        return this._webPlayer;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final WebViewApp currentApp = WebViewApp.getCurrentApp();
        if (currentApp != null) {
            currentApp.sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_ATTACHED, new Object[0]);
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        final WebViewApp currentApp = WebViewApp.getCurrentApp();
        if (currentApp != null) {
            currentApp.sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_DETACHED, new Object[0]);
        }
    }
    
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        float alpha = 1.0f;
        if (Build$VERSION.SDK_INT >= 11) {
            alpha = this.getAlpha();
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_RESIZED, n, n2, n3, n4, alpha);
        if (this.getParent() != null) {
            final Rect rect = new Rect();
            this.getHitRect(rect);
            if (this.getParent() instanceof View && !((View)this.getParent()).getLocalVisibleRect(rect)) {
                this.onVisibilityChanged((View)this, 8);
            }
        }
    }
    
    protected void onSizeChanged(final int n, final int n2, int left, int right) {
        if (left != 0 || right != 0) {
            left = this.getLeft();
            right = this.getRight();
            float alpha = 1.0f;
            if (Build$VERSION.SDK_INT >= 11) {
                alpha = this.getAlpha();
            }
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_RESIZED, left, right, n, n2, alpha);
            final Rect rect = new Rect();
            this.getHitRect(rect);
            if (((View)this.getParent()).getLocalVisibleRect(rect)) {
                this.onVisibilityChanged((View)this, 8);
            }
        }
    }
    
    protected void onVisibilityChanged(final View view, final int n) {
        if (view == this) {
            if (this._lastVisibility != -1) {
                if (n != 0 && this._lastVisibility == 0) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_VISIBILITY_CHANGED, n);
                }
                this._lastVisibility = n;
                return;
            }
            this._lastVisibility = n;
        }
    }
    
    public void setAlpha(final float alpha) {
        super.setAlpha(alpha);
        this.onLayoutChange((View)this, this.getLeft(), this.getTop(), this.getRight(), this.getBottom(), this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
    }
    
    public void setBannerDimensions(final int width, final int height, final BannerPosition position) {
        this.width = width;
        this.height = height;
        this.position = position;
    }
    
    public void setLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams != null) {
            viewGroup$LayoutParams.width = this.width;
            viewGroup$LayoutParams.height = this.height;
            super.setLayoutParams(LayoutParamsHelper.updateLayoutParamsForPosition(viewGroup$LayoutParams, this.position));
        }
    }
    
    public void setViewFrame(final String s, final Integer n, final Integer n2, final Integer n3, final Integer n4) {
        final View viewForName = this.getViewForName(s);
        if (viewForName == null) {
            return;
        }
        if (viewForName == this) {
            DeviceLog.warning("Not setting viewFrame for banner, use `setLayoutParams` instead.");
            return;
        }
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams((int)n3, (int)n4);
        layoutParams.setMargins((int)n, (int)n2, 0, 0);
        viewForName.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    public void setViews(final List<String> views) {
        final ArrayList<String> list = (ArrayList<String>)new ArrayList<Object>(views);
        final ArrayList<String> list2 = new ArrayList<String>();
        if (this._views != null) {
            list2.addAll((Collection<?>)this._views);
            list2.removeAll(views);
            list.removeAll(this._views);
        }
        this._views = views;
        final Iterator<Object> iterator = list2.iterator();
        while (iterator.hasNext()) {
            this.removeView(iterator.next());
        }
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            this.addView(iterator2.next());
        }
    }
}
