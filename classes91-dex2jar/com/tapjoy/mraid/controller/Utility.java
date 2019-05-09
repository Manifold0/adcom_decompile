// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.controller;

import android.webkit.JavascriptInterface;
import com.tapjoy.TapjoyLog;
import android.content.Context;
import com.tapjoy.mraid.view.MraidView;
import android.annotation.TargetApi;

@TargetApi(14)
public class Utility extends Abstract
{
    private Assets c;
    private Display d;
    private Network e;
    private MraidSensor f;
    
    public Utility(final MraidView mraidView, final Context context) {
        super(mraidView, context);
        this.c = new Assets(mraidView, context);
        this.d = new Display(mraidView, context);
        this.e = new Network(mraidView, context);
        this.f = new MraidSensor(mraidView, context);
        mraidView.addJavascriptInterface((Object)this.c, "MRAIDAssetsControllerBridge");
        mraidView.addJavascriptInterface((Object)this.d, "MRAIDDisplayControllerBridge");
        mraidView.addJavascriptInterface((Object)this.e, "MRAIDNetworkControllerBridge");
        mraidView.addJavascriptInterface((Object)this.f, "MRAIDSensorControllerBridge");
    }
    
    @JavascriptInterface
    public void activate(final String s) {
        TapjoyLog.d("MRAID Utility", "activate: " + s);
        if (s.equalsIgnoreCase("networkChange")) {
            this.e.startNetworkListener();
        }
        else {
            if (s.equalsIgnoreCase("shake")) {
                this.f.startShakeListener();
                return;
            }
            if (s.equalsIgnoreCase("tiltChange")) {
                this.f.startTiltListener();
                return;
            }
            if (s.equalsIgnoreCase("headingChange")) {
                this.f.startHeadingListener();
                return;
            }
            if (s.equalsIgnoreCase("orientationChange")) {
                this.d.startConfigurationListener();
            }
        }
    }
    
    public String copyTextFromJarIntoAssetDir(final String s, final String s2) {
        return this.c.copyTextFromJarIntoAssetDir(s, s2);
    }
    
    @JavascriptInterface
    public void deactivate(final String s) {
        TapjoyLog.d("MRAID Utility", "deactivate: " + s);
        if (s.equalsIgnoreCase("networkChange")) {
            this.e.stopNetworkListener();
        }
        else {
            if (s.equalsIgnoreCase("shake")) {
                this.f.stopShakeListener();
                return;
            }
            if (s.equalsIgnoreCase("tiltChange")) {
                this.f.stopTiltListener();
                return;
            }
            if (s.equalsIgnoreCase("headingChange")) {
                this.f.stopHeadingListener();
                return;
            }
            if (s.equalsIgnoreCase("orientationChange")) {
                this.d.stopConfigurationListener();
            }
        }
    }
    
    public void deleteOldAds() {
        this.c.deleteOldAds();
    }
    
    public void fireReadyEvent() {
        this.a.injectMraidJavaScript("mraid.signalReady();");
    }
    
    public void fireViewableChange(final boolean b) {
        this.a.injectMraidJavaScript("window.mraidview.fireChangeEvent({viewable:" + b + "});");
    }
    
    public void init(final float n) {
        final StringBuilder append = new StringBuilder("window.mraidview.fireChangeEvent({ state: 'default', network: '").append(this.e.getNetwork()).append("', size: ").append(this.d.getSize()).append(", placement: '").append(this.a.getPlacementType()).append("', maxSize: ").append(this.d.getMaxSize()).append(",expandProperties: ").append(this.d.getMaxSize()).append(", screenSize: ").append(this.d.getScreenSize()).append(", defaultPosition: { x:").append((int)(this.a.getLeft() / n)).append(", y: ").append((int)(this.a.getTop() / n)).append(", width: ").append((int)(this.a.getWidth() / n)).append(", height: ").append((int)(this.a.getHeight() / n)).append(" }, orientation:").append(this.d.getOrientation()).append(",");
        final String string = "supports: [ 'level-1', 'level-2', 'screen', 'orientation', 'network'" + ", 'video'" + ", 'audio'" + ", 'map' ]";
        TapjoyLog.d("MRAID Utility", "getSupports: " + string);
        final String string2 = append.append(string).append(",viewable:true });").toString();
        TapjoyLog.d("MRAID Utility", "init: injection: " + string2);
        this.a.injectMraidJavaScript(string2);
        this.fireReadyEvent();
        this.fireViewableChange(true);
    }
    
    public void setMaxSize(final int n, final int n2) {
        this.d.setMaxSize(n, n2);
    }
    
    @JavascriptInterface
    public void showAlert(final String s) {
        TapjoyLog.e("MRAID Utility", s);
    }
    
    @Override
    public void stopAllListeners() {
        try {
            this.c.stopAllListeners();
            this.d.stopAllListeners();
            this.e.stopAllListeners();
            this.f.stopAllListeners();
        }
        catch (Exception ex) {}
    }
}
