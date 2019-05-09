package com.tapjoy.mraid.controller;

import android.content.Context;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.listener.Accel;
import com.tapjoy.mraid.view.MraidView;

public class MraidSensor extends Abstract {
    /* renamed from: c */
    final int f8267c = 1000;
    /* renamed from: d */
    private Accel f8268d;
    /* renamed from: e */
    private float f8269e = 0.0f;
    /* renamed from: f */
    private float f8270f = 0.0f;
    /* renamed from: g */
    private float f8271g = 0.0f;

    public MraidSensor(MraidView adView, Context context) {
        super(adView, context);
        this.f8268d = new Accel(context, this);
    }

    public void startTiltListener() {
        this.f8268d.startTrackingTilt();
    }

    public void startShakeListener() {
        this.f8268d.startTrackingShake();
    }

    public void stopTiltListener() {
        this.f8268d.stopTrackingTilt();
    }

    public void stopShakeListener() {
        this.f8268d.stopTrackingShake();
    }

    public void startHeadingListener() {
        this.f8268d.startTrackingHeading();
    }

    public void stopHeadingListener() {
        this.f8268d.stopTrackingHeading();
    }

    public void onShake() {
        this.a.injectMraidJavaScript("mraid.gotShake()");
    }

    public void onTilt(float x, float y, float z) {
        this.f8269e = x;
        this.f8270f = y;
        this.f8271g = z;
        String str = "window.mraidview.fireChangeEvent({ tilt: " + getTilt() + "})";
        TapjoyLog.m7126d("MRAID Sensor", str);
        this.a.injectMraidJavaScript(str);
    }

    public String getTilt() {
        String str = "{ x : \"" + this.f8269e + "\", y : \"" + this.f8270f + "\", z : \"" + this.f8271g + "\"}";
        TapjoyLog.m7126d("MRAID Sensor", "getTilt: " + str);
        return str;
    }

    public void onHeadingChange(float f) {
        String str = "window.mraidview.fireChangeEvent({ heading: " + ((int) (((double) f) * 57.29577951308232d)) + "});";
        TapjoyLog.m7126d("MRAID Sensor", str);
        this.a.injectMraidJavaScript(str);
    }

    public float getHeading() {
        TapjoyLog.m7126d("MRAID Sensor", "getHeading: " + this.f8268d.getHeading());
        return this.f8268d.getHeading();
    }

    public void stopAllListeners() {
        this.f8268d.stopAllListeners();
    }
}
