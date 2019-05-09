package com.tapjoy.mraid.listener;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.tapjoy.mraid.controller.MraidSensor;
import com.tonyodev.fetch.FetchConst;
import java.util.List;

public class Accel implements SensorEventListener {
    /* renamed from: a */
    MraidSensor f8281a;
    /* renamed from: b */
    int f8282b = 0;
    /* renamed from: c */
    int f8283c = 0;
    /* renamed from: d */
    int f8284d = 0;
    /* renamed from: e */
    private SensorManager f8285e;
    /* renamed from: f */
    private int f8286f = 3;
    /* renamed from: g */
    private long f8287g;
    /* renamed from: h */
    private int f8288h;
    /* renamed from: i */
    private long f8289i;
    /* renamed from: j */
    private long f8290j;
    /* renamed from: k */
    private float[] f8291k;
    /* renamed from: l */
    private float[] f8292l = new float[]{0.0f, 0.0f, 0.0f};
    /* renamed from: m */
    private boolean f8293m;
    /* renamed from: n */
    private boolean f8294n;
    /* renamed from: o */
    private float[] f8295o = new float[]{0.0f, 0.0f, 0.0f};
    /* renamed from: p */
    private float[] f8296p = new float[]{-1.0f, -1.0f, -1.0f};

    public Accel(Context ctx, MraidSensor sensorController) {
        this.f8281a = sensorController;
        this.f8285e = (SensorManager) ctx.getSystemService("sensor");
    }

    public void setSensorDelay(int delay) {
        this.f8286f = delay;
        if (this.f8282b > 0 || this.f8283c > 0) {
            stop();
            m8240a();
        }
    }

    public void startTrackingTilt() {
        if (this.f8282b == 0) {
            m8240a();
        }
        this.f8282b++;
    }

    public void stopTrackingTilt() {
        if (this.f8282b > 0) {
            int i = this.f8282b - 1;
            this.f8282b = i;
            if (i == 0) {
                stop();
            }
        }
    }

    public void startTrackingShake() {
        if (this.f8283c == 0) {
            setSensorDelay(1);
            m8240a();
        }
        this.f8283c++;
    }

    public void stopTrackingShake() {
        if (this.f8283c > 0) {
            int i = this.f8283c - 1;
            this.f8283c = i;
            if (i == 0) {
                setSensorDelay(3);
                stop();
            }
        }
    }

    public void startTrackingHeading() {
        if (this.f8284d == 0) {
            List sensorList = this.f8285e.getSensorList(2);
            if (sensorList.size() > 0) {
                this.f8285e.registerListener(this, (Sensor) sensorList.get(0), this.f8286f);
                m8240a();
            }
        }
        this.f8284d++;
    }

    public void stopTrackingHeading() {
        if (this.f8284d > 0) {
            int i = this.f8284d - 1;
            this.f8284d = i;
            if (i == 0) {
                stop();
            }
        }
    }

    /* renamed from: a */
    private void m8240a() {
        List sensorList = this.f8285e.getSensorList(1);
        if (sensorList.size() > 0) {
            this.f8285e.registerListener(this, (Sensor) sensorList.get(0), this.f8286f);
        }
    }

    public void stop() {
        if (this.f8284d == 0 && this.f8283c == 0 && this.f8282b == 0) {
            this.f8285e.unregisterListener(this);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case 1:
                this.f8295o = this.f8292l;
                this.f8292l = (float[]) event.values.clone();
                this.f8294n = true;
                break;
            case 2:
                this.f8291k = (float[]) event.values.clone();
                this.f8293m = true;
                break;
        }
        if (this.f8291k != null && this.f8292l != null && this.f8294n && this.f8293m) {
            this.f8294n = false;
            this.f8293m = false;
            float[] fArr = new float[9];
            SensorManager.getRotationMatrix(fArr, new float[9], this.f8292l, this.f8291k);
            this.f8296p = new float[3];
            SensorManager.getOrientation(fArr, this.f8296p);
            this.f8281a.onHeadingChange(this.f8296p[0]);
        }
        if (event.sensor.getType() == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.f8287g > 500) {
                this.f8288h = 0;
            }
            if (currentTimeMillis - this.f8289i > 100) {
                if ((Math.abs(((((this.f8292l[0] + this.f8292l[1]) + this.f8292l[2]) - this.f8295o[0]) - this.f8295o[1]) - this.f8295o[2]) / ((float) (currentTimeMillis - this.f8289i))) * 10000.0f > 1000.0f) {
                    int i = this.f8288h + 1;
                    this.f8288h = i;
                    if (i >= 2 && currentTimeMillis - this.f8290j > FetchConst.DEFAULT_ON_UPDATE_INTERVAL) {
                        this.f8290j = currentTimeMillis;
                        this.f8288h = 0;
                        this.f8281a.onShake();
                    }
                    this.f8287g = currentTimeMillis;
                }
                this.f8289i = currentTimeMillis;
                this.f8281a.onTilt(this.f8292l[0], this.f8292l[1], this.f8292l[2]);
            }
        }
    }

    public float getHeading() {
        return this.f8296p[0];
    }

    public void stopAllListeners() {
        this.f8282b = 0;
        this.f8283c = 0;
        this.f8284d = 0;
        try {
            stop();
        } catch (Exception e) {
        }
    }
}
