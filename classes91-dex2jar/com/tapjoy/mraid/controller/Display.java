// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.controller;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.R$raw;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.JavascriptInterface;
import com.tapjoy.TapjoyLog;
import android.util.DisplayMetrics;
import com.tapjoy.mraid.view.MraidView;
import android.content.Context;
import com.tapjoy.mraid.util.ConfigBroadcastReceiver;
import android.view.WindowManager;

public class Display extends Abstract
{
    private WindowManager c;
    private boolean d;
    private int e;
    private int f;
    private ConfigBroadcastReceiver g;
    private float h;
    private Context i;
    
    public Display(final MraidView mraidView, final Context i) {
        super(mraidView, i);
        this.d = false;
        this.e = -1;
        this.f = -1;
        this.i = i;
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        this.c = (WindowManager)i.getSystemService("window");
        this.c.getDefaultDisplay().getMetrics(displayMetrics);
        this.h = displayMetrics.density;
    }
    
    @JavascriptInterface
    public void close() {
        TapjoyLog.d("MRAID Display", "close");
        this.a.close();
    }
    
    public String dimensions() {
        return "{ \"top\" :" + (int)(this.a.getTop() / this.h) + ",\"left\" :" + (int)(this.a.getLeft() / this.h) + ",\"bottom\" :" + (int)(this.a.getBottom() / this.h) + ",\"right\" :" + (int)(this.a.getRight() / this.h) + "}";
    }
    
    public String getMaxSize() {
        if (this.d) {
            return "{ width: " + this.e + ", height: " + this.f + "}";
        }
        return this.getScreenSize();
    }
    
    public int getOrientation() {
        final int orientation = this.c.getDefaultDisplay().getOrientation();
        int n = -1;
        switch (orientation) {
            case 0: {
                n = 0;
                break;
            }
            case 1: {
                n = 90;
                break;
            }
            case 2: {
                n = 180;
                break;
            }
            case 3: {
                n = 270;
                break;
            }
        }
        TapjoyLog.d("MRAID Display", "getOrientation: " + n);
        return n;
    }
    
    public String getScreenSize() {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        this.c.getDefaultDisplay().getMetrics(displayMetrics);
        return "{ width: " + (int)Math.ceil(displayMetrics.widthPixels / displayMetrics.density) + ", height: " + (int)Math.ceil(displayMetrics.heightPixels / displayMetrics.density) + "}";
    }
    
    public String getSize() {
        return this.a.getSize();
    }
    
    public boolean isVisible() {
        return this.a.getVisibility() == 0;
    }
    
    public void logHTML(final String s) {
        TapjoyLog.d("MRAID Display", s);
    }
    
    public void onOrientationChanged(final int n) {
        final String string = "window.mraidview.fireChangeEvent({ orientation: " + n + "});";
        TapjoyLog.d("MRAID Display", string);
        this.a.injectMraidJavaScript(string);
    }
    
    @JavascriptInterface
    public void open(final String s, final boolean b, final boolean b2, final boolean b3) {
        TapjoyLog.i("MRAID Display", "open: url: " + s + " back: " + b + " forward: " + b2 + " refresh: " + b3);
        if (URLUtil.isValidUrl(s)) {
            this.a.open(s, b, b2, b3);
            return;
        }
        TapjoyLog.i("MRAID Display", "invalid URL");
        final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s));
        final List queryIntentActivities = this.i.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() == 1) {
            this.i.startActivity(intent);
            return;
        }
        if (queryIntentActivities.size() > 1) {
            ((Activity)this.i).startActivity(Intent.createChooser(intent, (CharSequence)"Select"));
            return;
        }
        this.a.raiseError("Invalid url", "open");
    }
    
    @JavascriptInterface
    public void openMap(final String s, final boolean b) {
        TapjoyLog.d("MRAID Display", "openMap: url: " + s);
        this.a.openMap(s, b);
    }
    
    @JavascriptInterface
    public void playAudio(final String s, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s2, final String s3) {
        TapjoyLog.d("MRAID Display", "playAudio: url: " + s + " autoPlay: " + b + " controls: " + b2 + " loop: " + b3 + " position: " + b4 + " startStyle: " + s2 + " stopStyle: " + s3);
        if (!URLUtil.isValidUrl(s)) {
            this.a.raiseError("Invalid url", "playAudio");
            return;
        }
        this.a.playAudio(s, b, b2, b3, b4, s2, s3);
    }
    
    @JavascriptInterface
    public void playVideo(String s, final boolean b, final boolean b2, final boolean b3, final boolean b4, int[] array, String s2, final String s3) {
        TapjoyLog.d("MRAID Display", "playVideo: url: " + s + " audioMuted: " + b + " autoPlay: " + b2 + " controls: " + b3 + " loop: " + b4 + " x: " + array[0] + " y: " + array[1] + " width: " + array[2] + " height: " + array[3] + " startStyle: " + s2 + " stopStyle: " + s3);
        s2 = null;
        if (array[0] != -1) {
            final ReflectedParcelable reflectedParcelable = new Dimensions();
            ((Dimensions)reflectedParcelable).x = array[0];
            ((Dimensions)reflectedParcelable).y = array[1];
            ((Dimensions)reflectedParcelable).width = array[2];
            ((Dimensions)reflectedParcelable).height = array[3];
            ((Dimensions)reflectedParcelable).width = (int)Math.ceil(this.h * ((Dimensions)reflectedParcelable).width);
            ((Dimensions)reflectedParcelable).height = (int)Math.ceil(this.h * ((Dimensions)reflectedParcelable).height);
            ((Dimensions)reflectedParcelable).x *= (int)this.h;
            ((Dimensions)reflectedParcelable).y *= (int)this.h;
            if (((Dimensions)reflectedParcelable).height < 0) {
                ((Dimensions)reflectedParcelable).height = this.a.getHeight();
            }
            if (((Dimensions)reflectedParcelable).width < 0) {
                ((Dimensions)reflectedParcelable).width = this.a.getWidth();
            }
            array = new int[2];
            this.a.getLocationInWindow(array);
            if (((Dimensions)reflectedParcelable).x < 0) {
                ((Dimensions)reflectedParcelable).x = array[0];
            }
            s2 = (String)reflectedParcelable;
            if (((Dimensions)reflectedParcelable).y < 0) {
                ((Dimensions)reflectedParcelable).y = array[1] + 0;
                s2 = (String)reflectedParcelable;
            }
        }
        int int1 = 0;
        String string = s;
        Label_0457: {
            if (!s.contains("android.resource")) {
                break Label_0457;
            }
            s = s.substring(s.lastIndexOf("/") + 1, s.lastIndexOf("."));
            while (true) {
                try {
                    int1 = R$raw.class.getField(s).getInt(null);
                    s = this.b.getPackageName();
                    string = "android.resource://" + s + "/" + int1;
                    this.a.playVideo(string, false, true, true, false, (Dimensions)s2, "fullscreen", "exit");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
    }
    
    public void setMaxSize(final int e, final int f) {
        TapjoyLog.d("MRAID Display", "setMaxSize: " + e + "x" + f);
        this.d = true;
        this.e = e;
        this.f = f;
    }
    
    @JavascriptInterface
    public void setOrientationProperties(final boolean b, final String s) {
        TapjoyLog.d("MRAID Display", "setOrientationProperties: allowOrientationChange: " + Boolean.toString(b) + " forceOrientation: " + s);
        this.a.setOrientationProperties(b, s);
    }
    
    @JavascriptInterface
    public void show() {
        TapjoyLog.d("MRAID Display", "show");
        this.a.show();
    }
    
    public void startConfigurationListener() {
        try {
            if (this.g == null) {
                this.g = new ConfigBroadcastReceiver(this);
            }
            this.b.registerReceiver((BroadcastReceiver)this.g, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void stopAllListeners() {
        this.stopConfigurationListener();
        this.g = null;
    }
    
    public void stopConfigurationListener() {
        try {
            this.b.unregisterReceiver((BroadcastReceiver)this.g);
        }
        catch (Exception ex) {}
    }
    
    @JavascriptInterface
    public void useCustomClose(final boolean b) {
        if (b) {
            this.a.removeCloseImageButton();
        }
        else if (!b) {
            this.a.showCloseImageButton();
        }
    }
}
