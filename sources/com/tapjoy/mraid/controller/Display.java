package com.tapjoy.mraid.controller;

import android.R.raw;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.controller.Abstract.Dimensions;
import com.tapjoy.mraid.util.ConfigBroadcastReceiver;
import com.tapjoy.mraid.view.MraidView;
import java.util.List;

public class Display extends Abstract {
    /* renamed from: c */
    private WindowManager f8260c;
    /* renamed from: d */
    private boolean f8261d = false;
    /* renamed from: e */
    private int f8262e = -1;
    /* renamed from: f */
    private int f8263f = -1;
    /* renamed from: g */
    private ConfigBroadcastReceiver f8264g;
    /* renamed from: h */
    private float f8265h;
    /* renamed from: i */
    private Context f8266i;

    public Display(MraidView adView, Context c) {
        super(adView, c);
        this.f8266i = c;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f8260c = (WindowManager) c.getSystemService("window");
        this.f8260c.getDefaultDisplay().getMetrics(displayMetrics);
        this.f8265h = displayMetrics.density;
    }

    @JavascriptInterface
    public void open(String url, boolean back, boolean forward, boolean refresh) {
        TapjoyLog.m7129i("MRAID Display", "open: url: " + url + " back: " + back + " forward: " + forward + " refresh: " + refresh);
        if (URLUtil.isValidUrl(url)) {
            this.a.open(url, back, forward, refresh);
            return;
        }
        TapjoyLog.m7129i("MRAID Display", "invalid URL");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        List queryIntentActivities = this.f8266i.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() == 1) {
            this.f8266i.startActivity(intent);
        } else if (queryIntentActivities.size() > 1) {
            ((Activity) this.f8266i).startActivity(Intent.createChooser(intent, TJAdUnitConstants.SHARE_CHOOSE_TITLE));
        } else {
            this.a.raiseError("Invalid url", "open");
        }
    }

    @JavascriptInterface
    public void useCustomClose(boolean input) {
        if (input) {
            this.a.removeCloseImageButton();
        } else if (!input) {
            this.a.showCloseImageButton();
        }
    }

    @JavascriptInterface
    public void openMap(String url, boolean fullscreen) {
        TapjoyLog.m7126d("MRAID Display", "openMap: url: " + url);
        this.a.openMap(url, fullscreen);
    }

    @JavascriptInterface
    public void setOrientationProperties(boolean allowOrientationChange, String forceOrientation) {
        TapjoyLog.m7126d("MRAID Display", "setOrientationProperties: allowOrientationChange: " + Boolean.toString(allowOrientationChange) + " forceOrientation: " + forceOrientation);
        this.a.setOrientationProperties(allowOrientationChange, forceOrientation);
    }

    @JavascriptInterface
    public void playAudio(String url, boolean autoPlay, boolean controls, boolean loop, boolean position, String startStyle, String stopStyle) {
        TapjoyLog.m7126d("MRAID Display", "playAudio: url: " + url + " autoPlay: " + autoPlay + " controls: " + controls + " loop: " + loop + " position: " + position + " startStyle: " + startStyle + " stopStyle: " + stopStyle);
        if (URLUtil.isValidUrl(url)) {
            this.a.playAudio(url, autoPlay, controls, loop, position, startStyle, stopStyle);
        } else {
            this.a.raiseError("Invalid url", "playAudio");
        }
    }

    @JavascriptInterface
    public void playVideo(String url, boolean audioMuted, boolean autoPlay, boolean controls, boolean loop, int[] position, String startStyle, String stopStyle) {
        TapjoyLog.m7126d("MRAID Display", "playVideo: url: " + url + " audioMuted: " + audioMuted + " autoPlay: " + autoPlay + " controls: " + controls + " loop: " + loop + " x: " + position[0] + " y: " + position[1] + " width: " + position[2] + " height: " + position[3] + " startStyle: " + startStyle + " stopStyle: " + stopStyle);
        Dimensions dimensions = null;
        if (position[0] != -1) {
            dimensions = new Dimensions();
            dimensions.f8251x = position[0];
            dimensions.f8252y = position[1];
            dimensions.width = position[2];
            dimensions.height = position[3];
            dimensions.width = (int) Math.ceil((double) (this.f8265h * ((float) dimensions.width)));
            dimensions.height = (int) Math.ceil((double) (this.f8265h * ((float) dimensions.height)));
            dimensions.f8251x = (int) (((float) dimensions.f8251x) * this.f8265h);
            dimensions.f8252y = (int) (((float) dimensions.f8252y) * this.f8265h);
            if (dimensions.height < 0) {
                dimensions.height = this.a.getHeight();
            }
            if (dimensions.width < 0) {
                dimensions.width = this.a.getWidth();
            }
            int[] iArr = new int[2];
            this.a.getLocationInWindow(iArr);
            if (dimensions.f8251x < 0) {
                dimensions.f8251x = iArr[0];
            }
            if (dimensions.f8252y < 0) {
                dimensions.f8252y = iArr[1] + 0;
            }
        }
        int i = 0;
        if (url.contains("android.resource")) {
            try {
                i = raw.class.getField(url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."))).getInt(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            url = "android.resource://" + this.b.getPackageName() + "/" + i;
        }
        this.a.playVideo(url, false, true, true, false, dimensions, Abstract.FULL_SCREEN, Abstract.EXIT);
    }

    @JavascriptInterface
    public void close() {
        TapjoyLog.m7126d("MRAID Display", String.CLOSE);
        this.a.close();
    }

    @JavascriptInterface
    public void show() {
        TapjoyLog.m7126d("MRAID Display", "show");
        this.a.show();
    }

    public boolean isVisible() {
        return this.a.getVisibility() == 0;
    }

    public String dimensions() {
        return "{ \"top\" :" + ((int) (((float) this.a.getTop()) / this.f8265h)) + ",\"left\" :" + ((int) (((float) this.a.getLeft()) / this.f8265h)) + ",\"bottom\" :" + ((int) (((float) this.a.getBottom()) / this.f8265h)) + ",\"right\" :" + ((int) (((float) this.a.getRight()) / this.f8265h)) + "}";
    }

    public int getOrientation() {
        int i = -1;
        switch (this.f8260c.getDefaultDisplay().getOrientation()) {
            case 0:
                i = 0;
                break;
            case 1:
                i = 90;
                break;
            case 2:
                i = 180;
                break;
            case 3:
                i = 270;
                break;
        }
        TapjoyLog.m7126d("MRAID Display", "getOrientation: " + i);
        return i;
    }

    public String getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f8260c.getDefaultDisplay().getMetrics(displayMetrics);
        return "{ width: " + ((int) Math.ceil((double) (((float) displayMetrics.widthPixels) / displayMetrics.density))) + ", height: " + ((int) Math.ceil((double) (((float) displayMetrics.heightPixels) / displayMetrics.density))) + "}";
    }

    public String getSize() {
        return this.a.getSize();
    }

    public String getMaxSize() {
        if (this.f8261d) {
            return "{ width: " + this.f8262e + ", height: " + this.f8263f + "}";
        }
        return getScreenSize();
    }

    public void setMaxSize(int w, int h) {
        TapjoyLog.m7126d("MRAID Display", "setMaxSize: " + w + "x" + h);
        this.f8261d = true;
        this.f8262e = w;
        this.f8263f = h;
    }

    public void onOrientationChanged(int orientation) {
        String str = "window.mraidview.fireChangeEvent({ orientation: " + orientation + "});";
        TapjoyLog.m7126d("MRAID Display", str);
        this.a.injectMraidJavaScript(str);
    }

    public void logHTML(String html) {
        TapjoyLog.m7126d("MRAID Display", html);
    }

    public void stopAllListeners() {
        stopConfigurationListener();
        this.f8264g = null;
    }

    public void stopConfigurationListener() {
        try {
            this.b.unregisterReceiver(this.f8264g);
        } catch (Exception e) {
        }
    }

    public void startConfigurationListener() {
        try {
            if (this.f8264g == null) {
                this.f8264g = new ConfigBroadcastReceiver(this);
            }
            this.b.registerReceiver(this.f8264g, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
        } catch (Exception e) {
        }
    }
}
