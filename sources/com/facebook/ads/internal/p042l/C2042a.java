package com.facebook.ads.internal.p042l;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.facebook.ads.internal.l.a */
public class C2042a {
    /* renamed from: a */
    private static SensorManager f4551a = null;
    /* renamed from: b */
    private static Sensor f4552b = null;
    /* renamed from: c */
    private static Sensor f4553c = null;
    /* renamed from: d */
    private static volatile float[] f4554d;
    /* renamed from: e */
    private static volatile float[] f4555e;
    /* renamed from: f */
    private static Map<String, String> f4556f = new ConcurrentHashMap();
    /* renamed from: g */
    private static String[] f4557g = new String[]{"x", "y", "z"};
    /* renamed from: h */
    private static SensorEventListener f4558h;
    /* renamed from: i */
    private static SensorEventListener f4559i;

    /* renamed from: com.facebook.ads.internal.l.a$a */
    private static class C2040a implements SensorEventListener {
        private C2040a() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            C2042a.f4554d = sensorEvent.values;
            C2042a.m4951d();
        }
    }

    /* renamed from: com.facebook.ads.internal.l.a$b */
    private static class C2041b implements SensorEventListener {
        private C2041b() {
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            C2042a.f4555e = sensorEvent.values;
            C2042a.m4952e();
        }
    }

    /* renamed from: a */
    public static Map<String, String> m4944a() {
        Map hashMap = new HashMap();
        hashMap.putAll(f4556f);
        C2042a.m4946a(hashMap);
        return hashMap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static synchronized void m4945a(android.content.Context r8) {
        /*
        r1 = 1;
        r2 = com.facebook.ads.internal.p042l.C2042a.class;
        monitor-enter(r2);
        r3 = new android.app.ActivityManager$MemoryInfo;	 Catch:{ all -> 0x00b4 }
        r3.<init>();	 Catch:{ all -> 0x00b4 }
        r0 = "activity";
        r0 = r8.getSystemService(r0);	 Catch:{ all -> 0x00b4 }
        r0 = (android.app.ActivityManager) r0;	 Catch:{ all -> 0x00b4 }
        r0.getMemoryInfo(r3);	 Catch:{ all -> 0x00b4 }
        r0 = f4556f;	 Catch:{ all -> 0x00b4 }
        r4 = "available_memory";
        r6 = r3.availMem;	 Catch:{ all -> 0x00b4 }
        r5 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x00b4 }
        r0.put(r4, r5);	 Catch:{ all -> 0x00b4 }
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ all -> 0x00b4 }
        r4 = 16;
        if (r0 < r4) goto L_0x0034;
    L_0x0027:
        r0 = f4556f;	 Catch:{ all -> 0x00b4 }
        r4 = "total_memory";
        r6 = r3.totalMem;	 Catch:{ all -> 0x00b4 }
        r3 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x00b4 }
        r0.put(r4, r3);	 Catch:{ all -> 0x00b4 }
    L_0x0034:
        r0 = android.os.Environment.getDataDirectory();	 Catch:{ all -> 0x00b4 }
        r3 = new android.os.StatFs;	 Catch:{ all -> 0x00b4 }
        r0 = r0.getPath();	 Catch:{ all -> 0x00b4 }
        r3.<init>(r0);	 Catch:{ all -> 0x00b4 }
        r0 = r3.getBlockSize();	 Catch:{ all -> 0x00b4 }
        r4 = (long) r0;	 Catch:{ all -> 0x00b4 }
        r0 = r3.getAvailableBlocks();	 Catch:{ all -> 0x00b4 }
        r6 = (long) r0;	 Catch:{ all -> 0x00b4 }
        r0 = f4556f;	 Catch:{ all -> 0x00b4 }
        r3 = "free_space";
        r4 = r4 * r6;
        r4 = java.lang.String.valueOf(r4);	 Catch:{ all -> 0x00b4 }
        r0.put(r3, r4);	 Catch:{ all -> 0x00b4 }
        r0 = 0;
        r3 = new android.content.IntentFilter;	 Catch:{ all -> 0x00b4 }
        r4 = "android.intent.action.BATTERY_CHANGED";
        r3.<init>(r4);	 Catch:{ all -> 0x00b4 }
        r0 = r8.registerReceiver(r0, r3);	 Catch:{ all -> 0x00b4 }
        if (r0 != 0) goto L_0x0079;
    L_0x0065:
        r0 = f4551a;	 Catch:{ all -> 0x00b4 }
        if (r0 != 0) goto L_0x00bd;
    L_0x0069:
        r0 = "sensor";
        r0 = r8.getSystemService(r0);	 Catch:{ all -> 0x00b4 }
        r0 = (android.hardware.SensorManager) r0;	 Catch:{ all -> 0x00b4 }
        f4551a = r0;	 Catch:{ all -> 0x00b4 }
        r0 = f4551a;	 Catch:{ all -> 0x00b4 }
        if (r0 != 0) goto L_0x00bd;
    L_0x0077:
        monitor-exit(r2);
        return;
    L_0x0079:
        r3 = "level";
        r4 = -1;
        r3 = r0.getIntExtra(r3, r4);	 Catch:{ all -> 0x00b4 }
        r4 = "scale";
        r5 = -1;
        r4 = r0.getIntExtra(r4, r5);	 Catch:{ all -> 0x00b4 }
        r5 = "status";
        r6 = -1;
        r0 = r0.getIntExtra(r5, r6);	 Catch:{ all -> 0x00b4 }
        r5 = 2;
        if (r0 == r5) goto L_0x0094;
    L_0x0091:
        r5 = 5;
        if (r0 != r5) goto L_0x00b7;
    L_0x0094:
        r0 = 0;
        if (r4 <= 0) goto L_0x009d;
    L_0x0097:
        r0 = (float) r3;	 Catch:{ all -> 0x00b4 }
        r3 = (float) r4;	 Catch:{ all -> 0x00b4 }
        r0 = r0 / r3;
        r3 = 1120403456; // 0x42c80000 float:100.0 double:5.53552857E-315;
        r0 = r0 * r3;
    L_0x009d:
        r3 = f4556f;	 Catch:{ all -> 0x00b4 }
        r4 = "battery";
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x00b4 }
        r3.put(r4, r0);	 Catch:{ all -> 0x00b4 }
        r3 = f4556f;	 Catch:{ all -> 0x00b4 }
        r4 = "charging";
        if (r1 == 0) goto L_0x00ba;
    L_0x00ae:
        r0 = "1";
    L_0x00b0:
        r3.put(r4, r0);	 Catch:{ all -> 0x00b4 }
        goto L_0x0065;
    L_0x00b4:
        r0 = move-exception;
        monitor-exit(r2);
        throw r0;
    L_0x00b7:
        r0 = 0;
        r1 = r0;
        goto L_0x0094;
    L_0x00ba:
        r0 = "0";
        goto L_0x00b0;
    L_0x00bd:
        r0 = f4552b;	 Catch:{ all -> 0x00b4 }
        if (r0 != 0) goto L_0x00ca;
    L_0x00c1:
        r0 = f4551a;	 Catch:{ all -> 0x00b4 }
        r1 = 1;
        r0 = r0.getDefaultSensor(r1);	 Catch:{ all -> 0x00b4 }
        f4552b = r0;	 Catch:{ all -> 0x00b4 }
    L_0x00ca:
        r0 = f4553c;	 Catch:{ all -> 0x00b4 }
        if (r0 != 0) goto L_0x00d7;
    L_0x00ce:
        r0 = f4551a;	 Catch:{ all -> 0x00b4 }
        r1 = 4;
        r0 = r0.getDefaultSensor(r1);	 Catch:{ all -> 0x00b4 }
        f4553c = r0;	 Catch:{ all -> 0x00b4 }
    L_0x00d7:
        r0 = f4558h;	 Catch:{ all -> 0x00b4 }
        if (r0 != 0) goto L_0x00f1;
    L_0x00db:
        r0 = new com.facebook.ads.internal.l.a$a;	 Catch:{ all -> 0x00b4 }
        r1 = 0;
        r0.<init>();	 Catch:{ all -> 0x00b4 }
        f4558h = r0;	 Catch:{ all -> 0x00b4 }
        r0 = f4552b;	 Catch:{ all -> 0x00b4 }
        if (r0 == 0) goto L_0x00f1;
    L_0x00e7:
        r0 = f4551a;	 Catch:{ all -> 0x00b4 }
        r1 = f4558h;	 Catch:{ all -> 0x00b4 }
        r3 = f4552b;	 Catch:{ all -> 0x00b4 }
        r4 = 3;
        r0.registerListener(r1, r3, r4);	 Catch:{ all -> 0x00b4 }
    L_0x00f1:
        r0 = f4559i;	 Catch:{ all -> 0x00b4 }
        if (r0 != 0) goto L_0x0077;
    L_0x00f5:
        r0 = new com.facebook.ads.internal.l.a$b;	 Catch:{ all -> 0x00b4 }
        r1 = 0;
        r0.<init>();	 Catch:{ all -> 0x00b4 }
        f4559i = r0;	 Catch:{ all -> 0x00b4 }
        r0 = f4553c;	 Catch:{ all -> 0x00b4 }
        if (r0 == 0) goto L_0x0077;
    L_0x0101:
        r0 = f4551a;	 Catch:{ all -> 0x00b4 }
        r1 = f4559i;	 Catch:{ all -> 0x00b4 }
        r3 = f4553c;	 Catch:{ all -> 0x00b4 }
        r4 = 3;
        r0.registerListener(r1, r3, r4);	 Catch:{ all -> 0x00b4 }
        goto L_0x0077;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.l.a.a(android.content.Context):void");
    }

    /* renamed from: a */
    private static void m4946a(Map<String, String> map) {
        int i;
        int i2 = 0;
        float[] fArr = f4554d;
        float[] fArr2 = f4555e;
        if (fArr != null) {
            int min = Math.min(f4557g.length, fArr.length);
            for (i = 0; i < min; i++) {
                map.put("accelerometer_" + f4557g[i], String.valueOf(fArr[i]));
            }
        }
        if (fArr2 != null) {
            i = Math.min(f4557g.length, fArr2.length);
            while (i2 < i) {
                map.put("rotation_" + f4557g[i2], String.valueOf(fArr2[i2]));
                i2++;
            }
        }
    }

    /* renamed from: d */
    private static synchronized void m4951d() {
        synchronized (C2042a.class) {
            if (f4551a != null) {
                f4551a.unregisterListener(f4558h);
            }
            f4558h = null;
        }
    }

    /* renamed from: e */
    private static synchronized void m4952e() {
        synchronized (C2042a.class) {
            if (f4551a != null) {
                f4551a.unregisterListener(f4559i);
            }
            f4559i = null;
        }
    }
}
