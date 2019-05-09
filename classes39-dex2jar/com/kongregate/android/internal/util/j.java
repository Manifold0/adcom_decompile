// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.os.Debug;
import android.util.Log;
import java.util.Iterator;
import java.util.Map;

public class j
{
    private static boolean a;
    private static boolean b;
    
    public static void a() {
        for (final Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            b("Thread: " + entry.getKey().toString());
            final StackTraceElement[] array = entry.getValue();
            for (int length = array.length, i = 0; i < length; ++i) {
                b(array[i].toString());
            }
            b("----");
        }
    }
    
    private static void a(final int n, final String s, final Throwable t) {
        String string = s;
        if (t != null) {
            string = s + '\n' + Log.getStackTraceString(t);
        }
        if (j.b) {
            com.kongregate.android.internal.util.b.a(n, "KONG", string);
            return;
        }
        Log.println(n, "KONG", string);
    }
    
    public static void a(final String s) {
        a(3, s, null);
    }
    
    public static void a(final String s, final Throwable t) {
        a(3, s, t);
    }
    
    public static void a(final boolean a) {
        j.a = a;
    }
    
    public static void b(final String s) {
        a(4, s, null);
    }
    
    public static void b(final String s, final Throwable t) {
        a(4, s, t);
    }
    
    public static void b(final boolean b) {
        j.b = (b && b.a());
        a("setting crashlyitcs logging to " + b + ", did enable: " + j.b);
    }
    
    public static void c(final String s) {
        a(5, s, null);
    }
    
    public static void c(final String s, final Throwable t) {
        a(5, s, t);
    }
    
    public static void d(final String s) {
        a(6, s, null);
    }
    
    public static void d(final String s, final Throwable t) {
        a(6, s, t);
    }
    
    public static void e(final String s) {
        if (j.a) {
            a(s);
        }
    }
    
    public static void e(final String s, final Throwable t) {
        if (j.a) {
            Log.d("KONG", s, t);
        }
    }
    
    public static void f(final String s) {
        a("--------> NativeMem [" + s + "]: " + Debug.getNativeHeapAllocatedSize() / 1024L + "k/" + Debug.getNativeHeapSize() / 1024L + "k, [" + Debug.getNativeHeapFreeSize() / 1024L + "k free], Heap: " + Runtime.getRuntime().totalMemory() / 1024L + "k/" + Runtime.getRuntime().maxMemory() / 1024L + "k, [" + Runtime.getRuntime().freeMemory() / 1024L + "k free]");
    }
}
