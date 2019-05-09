package com.kongregate.android.internal.util;

import android.os.Debug;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.util.Map.Entry;

/* renamed from: com.kongregate.android.internal.util.j */
public class C0562j {
    /* renamed from: a */
    private static boolean f753a;
    /* renamed from: b */
    private static boolean f754b;

    /* renamed from: a */
    public static void m755a(boolean z) {
        f753a = z;
    }

    /* renamed from: b */
    public static void m758b(boolean z) {
        boolean z2 = z && C0543b.m616a();
        f754b = z2;
        C0562j.m753a("setting crashlyitcs logging to " + z + ", did enable: " + f754b);
    }

    /* renamed from: a */
    public static void m754a(String str, Throwable th) {
        C0562j.m752a(3, str, th);
    }

    /* renamed from: a */
    public static void m753a(String str) {
        C0562j.m752a(3, str, null);
    }

    /* renamed from: b */
    public static void m757b(String str, Throwable th) {
        C0562j.m752a(4, str, th);
    }

    /* renamed from: b */
    public static void m756b(String str) {
        C0562j.m752a(4, str, null);
    }

    /* renamed from: c */
    public static void m760c(String str, Throwable th) {
        C0562j.m752a(5, str, th);
    }

    /* renamed from: c */
    public static void m759c(String str) {
        C0562j.m752a(5, str, null);
    }

    /* renamed from: d */
    public static void m762d(String str, Throwable th) {
        C0562j.m752a(6, str, th);
    }

    /* renamed from: d */
    public static void m761d(String str) {
        C0562j.m752a(6, str, null);
    }

    /* renamed from: e */
    public static void m763e(String str) {
        if (f753a) {
            C0562j.m753a(str);
        }
    }

    /* renamed from: a */
    private static void m752a(int i, String str, Throwable th) {
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        if (f754b) {
            C0543b.m611a(i, "KONG", str);
        } else {
            Log.println(i, "KONG", str);
        }
    }

    /* renamed from: e */
    public static void m764e(String str, Throwable th) {
        if (f753a) {
            Log.d("KONG", str, th);
        }
    }

    /* renamed from: f */
    public static void m765f(String str) {
        long nativeHeapFreeSize = Debug.getNativeHeapFreeSize() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        long totalMemory = Runtime.getRuntime().totalMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        long maxMemory = Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        long freeMemory = Runtime.getRuntime().freeMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        C0562j.m753a("--------> NativeMem [" + str + "]: " + (Debug.getNativeHeapAllocatedSize() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) + "k/" + (Debug.getNativeHeapSize() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) + "k, [" + nativeHeapFreeSize + "k free], Heap: " + totalMemory + "k/" + maxMemory + "k, [" + freeMemory + "k free]");
    }

    /* renamed from: a */
    public static void m751a() {
        for (Entry entry : Thread.getAllStackTraces().entrySet()) {
            C0562j.m756b("Thread: " + ((Thread) entry.getKey()).toString());
            for (StackTraceElement stackTraceElement : (StackTraceElement[]) entry.getValue()) {
                C0562j.m756b(stackTraceElement.toString());
            }
            C0562j.m756b("----");
        }
    }
}
