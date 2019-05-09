// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.controller;

import com.tapjoy.TapjoyLog;
import android.content.Context;
import com.tapjoy.mraid.view.MraidView;
import com.tapjoy.mraid.listener.Accel;

public class MraidSensor extends Abstract
{
    final int c;
    private Accel d;
    private float e;
    private float f;
    private float g;
    
    public MraidSensor(final MraidView mraidView, final Context context) {
        super(mraidView, context);
        this.c = 1000;
        this.e = 0.0f;
        this.f = 0.0f;
        this.g = 0.0f;
        this.d = new Accel(context, this);
    }
    
    public float getHeading() {
        TapjoyLog.d("MRAID Sensor", "getHeading: " + this.d.getHeading());
        return this.d.getHeading();
    }
    
    public String getTilt() {
        final String string = "{ x : \"" + this.e + "\", y : \"" + this.f + "\", z : \"" + this.g + "\"}";
        TapjoyLog.d("MRAID Sensor", "getTilt: " + string);
        return string;
    }
    
    public void onHeadingChange(final float n) {
        final String string = "window.mraidview.fireChangeEvent({ heading: " + (int)(n * 57.29577951308232) + "});";
        TapjoyLog.d("MRAID Sensor", string);
        this.a.injectMraidJavaScript(string);
    }
    
    public void onShake() {
        this.a.injectMraidJavaScript("mraid.gotShake()");
    }
    
    public void onTilt(final float e, final float f, final float g) {
        this.e = e;
        this.f = f;
        this.g = g;
        final String string = "window.mraidview.fireChangeEvent({ tilt: " + this.getTilt() + "})";
        TapjoyLog.d("MRAID Sensor", string);
        this.a.injectMraidJavaScript(string);
    }
    
    public void startHeadingListener() {
        this.d.startTrackingHeading();
    }
    
    public void startShakeListener() {
        this.d.startTrackingShake();
    }
    
    public void startTiltListener() {
        this.d.startTrackingTilt();
    }
    
    @Override
    public void stopAllListeners() {
        this.d.stopAllListeners();
    }
    
    public void stopHeadingListener() {
        this.d.stopTrackingHeading();
    }
    
    public void stopShakeListener() {
        this.d.stopTrackingShake();
    }
    
    public void stopTiltListener() {
        this.d.stopTrackingTilt();
    }
}
