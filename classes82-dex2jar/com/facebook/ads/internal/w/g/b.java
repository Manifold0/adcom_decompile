// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.g;

import java.util.Iterator;
import android.os.SystemClock;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.widget.Toast;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Set;
import android.support.annotation.Nullable;
import android.hardware.SensorManager;
import android.content.Context;
import android.hardware.SensorEventListener;

public class b implements SensorEventListener
{
    private final Context a;
    @Nullable
    private SensorManager b;
    private int c;
    private long d;
    private long e;
    private long f;
    private float g;
    private float h;
    private float i;
    private final Set<a> j;
    
    public b(final Context a) {
        this.c = 0;
        this.g = -1.0f;
        this.h = -1.0f;
        this.i = -1.0f;
        this.j = new CopyOnWriteArraySet<a>();
        this.a = a;
    }
    
    public void a(final a a) {
        while (true) {
            Label_0119: {
                if (!this.j.isEmpty()) {
                    break Label_0119;
                }
                this.b = (SensorManager)this.a.getSystemService("sensor");
                if (this.b == null) {
                    Toast.makeText(this.a, (CharSequence)"Sensors not supported", 1).show();
                }
                boolean registerListener = false;
                while (true) {
                    try {
                        registerListener = this.b.registerListener((SensorEventListener)this, this.b.getDefaultSensor(1), 3);
                        if (!registerListener && this.b != null) {
                            this.b.unregisterListener((SensorEventListener)this);
                        }
                        this.j.add(a);
                        return;
                    }
                    catch (Exception ex) {
                        Toast.makeText(this.a, (CharSequence)"Shaking not supported", 1).show();
                        continue;
                    }
                    break;
                }
            }
            if (this.j.contains(a)) {
                return;
            }
            continue;
        }
    }
    
    public void onAccuracyChanged(final Sensor sensor, final int n) {
    }
    
    public void onSensorChanged(final SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.f > 500L) {
                this.c = 0;
            }
            if (elapsedRealtime - this.d > 100L) {
                if (Math.abs(sensorEvent.values[0] + sensorEvent.values[1] + sensorEvent.values[2] - this.g - this.h - this.i) / (elapsedRealtime - this.d) * 10000.0f > 800.0f) {
                    final int c = this.c + 1;
                    this.c = c;
                    if (c >= 3 && elapsedRealtime - this.e > 1000L) {
                        this.e = elapsedRealtime;
                        this.c = 0;
                        final Iterator<a> iterator = this.j.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().a();
                        }
                    }
                    this.f = elapsedRealtime;
                }
                this.d = elapsedRealtime;
                this.g = sensorEvent.values[0];
                this.h = sensorEvent.values[1];
                this.i = sensorEvent.values[2];
            }
        }
    }
    
    public interface a
    {
        void a();
    }
}
