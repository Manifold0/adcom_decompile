package com.tapjoy.mraid.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.JavascriptInterface;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.controller.Defines.Events;
import com.tapjoy.mraid.view.MraidView;

@TargetApi(14)
public class Utility extends Abstract {
    /* renamed from: c */
    private Assets f8277c;
    /* renamed from: d */
    private Display f8278d;
    /* renamed from: e */
    private Network f8279e;
    /* renamed from: f */
    private MraidSensor f8280f;

    public Utility(MraidView adView, Context context) {
        super(adView, context);
        this.f8277c = new Assets(adView, context);
        this.f8278d = new Display(adView, context);
        this.f8279e = new Network(adView, context);
        this.f8280f = new MraidSensor(adView, context);
        adView.addJavascriptInterface(this.f8277c, "MRAIDAssetsControllerBridge");
        adView.addJavascriptInterface(this.f8278d, "MRAIDDisplayControllerBridge");
        adView.addJavascriptInterface(this.f8279e, "MRAIDNetworkControllerBridge");
        adView.addJavascriptInterface(this.f8280f, "MRAIDSensorControllerBridge");
    }

    public void init(float density) {
        StringBuilder append = new StringBuilder("window.mraidview.fireChangeEvent({ state: 'default', network: '").append(this.f8279e.getNetwork()).append("', size: ").append(this.f8278d.getSize()).append(", placement: '").append(this.a.getPlacementType()).append("', maxSize: ").append(this.f8278d.getMaxSize()).append(",expandProperties: ").append(this.f8278d.getMaxSize()).append(", screenSize: ").append(this.f8278d.getScreenSize()).append(", defaultPosition: { x:").append((int) (((float) this.a.getLeft()) / density)).append(", y: ").append((int) (((float) this.a.getTop()) / density)).append(", width: ").append((int) (((float) this.a.getWidth()) / density)).append(", height: ").append((int) (((float) this.a.getHeight()) / density)).append(" }, orientation:").append(this.f8278d.getOrientation()).append(",");
        String str = (("supports: [ 'level-1', 'level-2', 'screen', 'orientation', 'network'" + ", 'video'") + ", 'audio'") + ", 'map' ]";
        TapjoyLog.m7126d("MRAID Utility", "getSupports: " + str);
        String stringBuilder = append.append(str).append(",viewable:true });").toString();
        TapjoyLog.m7126d("MRAID Utility", "init: injection: " + stringBuilder);
        this.a.injectMraidJavaScript(stringBuilder);
        fireReadyEvent();
        fireViewableChange(true);
    }

    public void fireReadyEvent() {
        this.a.injectMraidJavaScript("mraid.signalReady();");
    }

    public void fireViewableChange(boolean visible) {
        this.a.injectMraidJavaScript("window.mraidview.fireChangeEvent({viewable:" + visible + "});");
    }

    public String copyTextFromJarIntoAssetDir(String alias, String source) {
        return this.f8277c.copyTextFromJarIntoAssetDir(alias, source);
    }

    public void setMaxSize(int w, int h) {
        this.f8278d.setMaxSize(w, h);
    }

    @JavascriptInterface
    public void activate(String event) {
        TapjoyLog.m7126d("MRAID Utility", "activate: " + event);
        if (event.equalsIgnoreCase(Events.NETWORK_CHANGE)) {
            this.f8279e.startNetworkListener();
        } else if (event.equalsIgnoreCase(Events.SHAKE)) {
            this.f8280f.startShakeListener();
        } else if (event.equalsIgnoreCase(Events.TILT_CHANGE)) {
            this.f8280f.startTiltListener();
        } else if (event.equalsIgnoreCase(Events.HEADING_CHANGE)) {
            this.f8280f.startHeadingListener();
        } else if (event.equalsIgnoreCase(Events.ORIENTATION_CHANGE)) {
            this.f8278d.startConfigurationListener();
        }
    }

    @JavascriptInterface
    public void deactivate(String event) {
        TapjoyLog.m7126d("MRAID Utility", "deactivate: " + event);
        if (event.equalsIgnoreCase(Events.NETWORK_CHANGE)) {
            this.f8279e.stopNetworkListener();
        } else if (event.equalsIgnoreCase(Events.SHAKE)) {
            this.f8280f.stopShakeListener();
        } else if (event.equalsIgnoreCase(Events.TILT_CHANGE)) {
            this.f8280f.stopTiltListener();
        } else if (event.equalsIgnoreCase(Events.HEADING_CHANGE)) {
            this.f8280f.stopHeadingListener();
        } else if (event.equalsIgnoreCase(Events.ORIENTATION_CHANGE)) {
            this.f8278d.stopConfigurationListener();
        }
    }

    public void deleteOldAds() {
        this.f8277c.deleteOldAds();
    }

    public void stopAllListeners() {
        try {
            this.f8277c.stopAllListeners();
            this.f8278d.stopAllListeners();
            this.f8279e.stopAllListeners();
            this.f8280f.stopAllListeners();
        } catch (Exception e) {
        }
    }

    @JavascriptInterface
    public void showAlert(String message) {
        TapjoyLog.m7128e("MRAID Utility", message);
    }
}
