package com.facebook.ads.internal.p025w.p072g;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* renamed from: com.facebook.ads.internal.w.g.b */
public class C2623b implements SensorEventListener {
    /* renamed from: a */
    private final Context f6492a;
    @Nullable
    /* renamed from: b */
    private SensorManager f6493b;
    /* renamed from: c */
    private int f6494c = 0;
    /* renamed from: d */
    private long f6495d;
    /* renamed from: e */
    private long f6496e;
    /* renamed from: f */
    private long f6497f;
    /* renamed from: g */
    private float f6498g = -1.0f;
    /* renamed from: h */
    private float f6499h = -1.0f;
    /* renamed from: i */
    private float f6500i = -1.0f;
    /* renamed from: j */
    private final Set<C2620a> f6501j = new CopyOnWriteArraySet();

    /* renamed from: com.facebook.ads.internal.w.g.b$a */
    public interface C2620a {
        /* renamed from: a */
        void mo5645a();
    }

    public C2623b(Context context) {
        this.f6492a = context;
    }

    /* renamed from: a */
    public void m6737a(C2620a c2620a) {
        if (this.f6501j.isEmpty()) {
            this.f6493b = (SensorManager) this.f6492a.getSystemService("sensor");
            if (this.f6493b == null) {
                Toast.makeText(this.f6492a, "Sensors not supported", 1).show();
            }
            boolean z = false;
            try {
                z = this.f6493b.registerListener(this, this.f6493b.getDefaultSensor(1), 3);
            } catch (Exception e) {
                Toast.makeText(this.f6492a, "Shaking not supported", 1).show();
            }
            if (!(z || this.f6493b == null)) {
                this.f6493b.unregisterListener(this);
            }
        } else if (this.f6501j.contains(c2620a)) {
            return;
        }
        this.f6501j.add(c2620a);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.f6497f > 500) {
                this.f6494c = 0;
            }
            if (elapsedRealtime - this.f6495d > 100) {
                if ((Math.abs(((((sensorEvent.values[0] + sensorEvent.values[1]) + sensorEvent.values[2]) - this.f6498g) - this.f6499h) - this.f6500i) / ((float) (elapsedRealtime - this.f6495d))) * 10000.0f > 800.0f) {
                    int i = this.f6494c + 1;
                    this.f6494c = i;
                    if (i >= 3 && elapsedRealtime - this.f6496e > 1000) {
                        this.f6496e = elapsedRealtime;
                        this.f6494c = 0;
                        for (C2620a a : this.f6501j) {
                            a.mo5645a();
                        }
                    }
                    this.f6497f = elapsedRealtime;
                }
                this.f6495d = elapsedRealtime;
                this.f6498g = sensorEvent.values[0];
                this.f6499h = sensorEvent.values[1];
                this.f6500i = sensorEvent.values[2];
            }
        }
    }
}
