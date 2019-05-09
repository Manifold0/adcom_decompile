// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.listener;

import android.hardware.SensorEvent;
import java.util.List;
import android.hardware.Sensor;
import android.content.Context;
import android.hardware.SensorManager;
import com.tapjoy.mraid.controller.MraidSensor;
import android.hardware.SensorEventListener;

public class Accel implements SensorEventListener
{
    MraidSensor a;
    int b;
    int c;
    int d;
    private SensorManager e;
    private int f;
    private long g;
    private int h;
    private long i;
    private long j;
    private float[] k;
    private float[] l;
    private boolean m;
    private boolean n;
    private float[] o;
    private float[] p;
    
    public Accel(final Context context, final MraidSensor a) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.f = 3;
        this.l = new float[] { 0.0f, 0.0f, 0.0f };
        this.o = new float[] { 0.0f, 0.0f, 0.0f };
        this.p = new float[] { -1.0f, -1.0f, -1.0f };
        this.a = a;
        this.e = (SensorManager)context.getSystemService("sensor");
    }
    
    private void a() {
        final List sensorList = this.e.getSensorList(1);
        if (sensorList.size() > 0) {
            this.e.registerListener((SensorEventListener)this, (Sensor)sensorList.get(0), this.f);
        }
    }
    
    public float getHeading() {
        return this.p[0];
    }
    
    public void onAccuracyChanged(final Sensor sensor, final int n) {
    }
    
    public void onSensorChanged(final SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case 2: {
                this.k = sensorEvent.values.clone();
                this.m = true;
                break;
            }
            case 1: {
                this.o = this.l;
                this.l = sensorEvent.values.clone();
                this.n = true;
                break;
            }
        }
        if (this.k != null && this.l != null && this.n && this.m) {
            this.n = false;
            this.m = false;
            final float[] array = new float[9];
            SensorManager.getRotationMatrix(array, new float[9], this.l, this.k);
            SensorManager.getOrientation(array, this.p = new float[3]);
            this.a.onHeadingChange(this.p[0]);
        }
        if (sensorEvent.sensor.getType() == 1) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.g > 500L) {
                this.h = 0;
            }
            if (currentTimeMillis - this.i > 100L) {
                if (Math.abs(this.l[0] + this.l[1] + this.l[2] - this.o[0] - this.o[1] - this.o[2]) / (currentTimeMillis - this.i) * 10000.0f > 1000.0f) {
                    final int h = this.h + 1;
                    this.h = h;
                    if (h >= 2 && currentTimeMillis - this.j > 2000L) {
                        this.j = currentTimeMillis;
                        this.h = 0;
                        this.a.onShake();
                    }
                    this.g = currentTimeMillis;
                }
                this.i = currentTimeMillis;
                this.a.onTilt(this.l[0], this.l[1], this.l[2]);
            }
        }
    }
    
    public void setSensorDelay(final int f) {
        this.f = f;
        if (this.b > 0 || this.c > 0) {
            this.stop();
            this.a();
        }
    }
    
    public void startTrackingHeading() {
        if (this.d == 0) {
            final List sensorList = this.e.getSensorList(2);
            if (sensorList.size() > 0) {
                this.e.registerListener((SensorEventListener)this, (Sensor)sensorList.get(0), this.f);
                this.a();
            }
        }
        ++this.d;
    }
    
    public void startTrackingShake() {
        if (this.c == 0) {
            this.setSensorDelay(1);
            this.a();
        }
        ++this.c;
    }
    
    public void startTrackingTilt() {
        if (this.b == 0) {
            this.a();
        }
        ++this.b;
    }
    
    public void stop() {
        if (this.d == 0 && this.c == 0 && this.b == 0) {
            this.e.unregisterListener((SensorEventListener)this);
        }
    }
    
    public void stopAllListeners() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        try {
            this.stop();
        }
        catch (Exception ex) {}
    }
    
    public void stopTrackingHeading() {
        if (this.d > 0 && --this.d == 0) {
            this.stop();
        }
    }
    
    public void stopTrackingShake() {
        if (this.c > 0 && --this.c == 0) {
            this.setSensorDelay(3);
            this.stop();
        }
    }
    
    public void stopTrackingTilt() {
        if (this.b > 0 && --this.b == 0) {
            this.stop();
        }
    }
}
