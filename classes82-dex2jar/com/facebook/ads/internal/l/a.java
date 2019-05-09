// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.l;

import android.hardware.SensorEvent;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.StatFs;
import android.os.Environment;
import android.os.Build$VERSION;
import android.app.ActivityManager;
import android.app.ActivityManager$MemoryInfo;
import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import android.hardware.SensorEventListener;
import java.util.Map;
import android.hardware.Sensor;
import android.hardware.SensorManager;

public class a
{
    private static SensorManager a;
    private static Sensor b;
    private static Sensor c;
    private static volatile float[] d;
    private static volatile float[] e;
    private static Map<String, String> f;
    private static String[] g;
    private static SensorEventListener h;
    private static SensorEventListener i;
    
    static {
        com.facebook.ads.internal.l.a.a = null;
        com.facebook.ads.internal.l.a.b = null;
        com.facebook.ads.internal.l.a.c = null;
        com.facebook.ads.internal.l.a.f = new ConcurrentHashMap<String, String>();
        com.facebook.ads.internal.l.a.g = new String[] { "x", "y", "z" };
    }
    
    public static Map<String, String> a() {
        final HashMap<Object, Object> hashMap = (HashMap<Object, Object>)new HashMap<String, String>();
        hashMap.putAll(com.facebook.ads.internal.l.a.f);
        a((Map<String, String>)hashMap);
        return (Map<String, String>)hashMap;
    }
    
    public static void a(final Context context) {
    Label_0267_Outer:
        while (true) {
            final boolean b = true;
        Label_0300:
            while (true) {
            Label_0293:
                while (true) {
                    Label_0288: {
                        synchronized (a.class) {
                            final ActivityManager$MemoryInfo activityManager$MemoryInfo = new ActivityManager$MemoryInfo();
                            ((ActivityManager)context.getSystemService("activity")).getMemoryInfo(activityManager$MemoryInfo);
                            com.facebook.ads.internal.l.a.f.put("available_memory", String.valueOf(activityManager$MemoryInfo.availMem));
                            if (Build$VERSION.SDK_INT >= 16) {
                                com.facebook.ads.internal.l.a.f.put("total_memory", String.valueOf(activityManager$MemoryInfo.totalMem));
                            }
                            final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                            com.facebook.ads.internal.l.a.f.put("free_space", String.valueOf(statFs.getBlockSize() * (long)statFs.getAvailableBlocks()));
                            final Intent registerReceiver = context.registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                            if (registerReceiver != null) {
                                final int intExtra = registerReceiver.getIntExtra("level", -1);
                                final int intExtra2 = registerReceiver.getIntExtra("scale", -1);
                                final int intExtra3 = registerReceiver.getIntExtra("status", -1);
                                int n = b ? 1 : 0;
                                if (intExtra3 != 2) {
                                    if (intExtra3 != 5) {
                                        break Label_0288;
                                    }
                                    n = (b ? 1 : 0);
                                }
                                float n2 = 0.0f;
                                if (intExtra2 > 0) {
                                    n2 = intExtra / (float)intExtra2 * 100.0f;
                                }
                                com.facebook.ads.internal.l.a.f.put("battery", String.valueOf(n2));
                                final Map<String, String> f = com.facebook.ads.internal.l.a.f;
                                if (n == 0) {
                                    break Label_0293;
                                }
                                final String s = "1";
                                f.put("charging", s);
                            }
                            if (com.facebook.ads.internal.l.a.a != null) {
                                break Label_0300;
                            }
                            com.facebook.ads.internal.l.a.a = (SensorManager)context.getSystemService("sensor");
                            if (com.facebook.ads.internal.l.a.a == null) {
                                return;
                            }
                            break Label_0300;
                        }
                    }
                    int n = 0;
                    continue Label_0267_Outer;
                }
                final String s = "0";
                continue;
            }
            if (com.facebook.ads.internal.l.a.b == null) {
                com.facebook.ads.internal.l.a.b = com.facebook.ads.internal.l.a.a.getDefaultSensor(1);
            }
            if (com.facebook.ads.internal.l.a.c == null) {
                com.facebook.ads.internal.l.a.c = com.facebook.ads.internal.l.a.a.getDefaultSensor(4);
            }
            if (com.facebook.ads.internal.l.a.h == null) {
                com.facebook.ads.internal.l.a.h = (SensorEventListener)new a();
                if (com.facebook.ads.internal.l.a.b != null) {
                    com.facebook.ads.internal.l.a.a.registerListener(com.facebook.ads.internal.l.a.h, com.facebook.ads.internal.l.a.b, 3);
                }
            }
            if (com.facebook.ads.internal.l.a.i != null) {
                return;
            }
            com.facebook.ads.internal.l.a.i = (SensorEventListener)new b();
            if (com.facebook.ads.internal.l.a.c != null) {
                com.facebook.ads.internal.l.a.a.registerListener(com.facebook.ads.internal.l.a.i, com.facebook.ads.internal.l.a.c, 3);
            }
        }
    }
    
    private static void a(final Map<String, String> map) {
        final int n = 0;
        final float[] d = com.facebook.ads.internal.l.a.d;
        final float[] e = com.facebook.ads.internal.l.a.e;
        if (d != null) {
            for (int min = Math.min(com.facebook.ads.internal.l.a.g.length, d.length), i = 0; i < min; ++i) {
                map.put("accelerometer_" + com.facebook.ads.internal.l.a.g[i], String.valueOf(d[i]));
            }
        }
        if (e != null) {
            for (int min2 = Math.min(com.facebook.ads.internal.l.a.g.length, e.length), j = n; j < min2; ++j) {
                map.put("rotation_" + com.facebook.ads.internal.l.a.g[j], String.valueOf(e[j]));
            }
        }
    }
    
    private static void d() {
        synchronized (a.class) {
            if (com.facebook.ads.internal.l.a.a != null) {
                com.facebook.ads.internal.l.a.a.unregisterListener(com.facebook.ads.internal.l.a.h);
            }
            com.facebook.ads.internal.l.a.h = null;
        }
    }
    
    private static void e() {
        synchronized (a.class) {
            if (com.facebook.ads.internal.l.a.a != null) {
                com.facebook.ads.internal.l.a.a.unregisterListener(com.facebook.ads.internal.l.a.i);
            }
            com.facebook.ads.internal.l.a.i = null;
        }
    }
    
    private static class a implements SensorEventListener
    {
        public void onAccuracyChanged(final Sensor sensor, final int n) {
        }
        
        public void onSensorChanged(final SensorEvent sensorEvent) {
            com.facebook.ads.internal.l.a.d = sensorEvent.values;
            d();
        }
    }
    
    private static class b implements SensorEventListener
    {
        public void onAccuracyChanged(final Sensor sensor, final int n) {
        }
        
        public void onSensorChanged(final SensorEvent sensorEvent) {
            com.facebook.ads.internal.l.a.e = sensorEvent.values;
            e();
        }
    }
}
