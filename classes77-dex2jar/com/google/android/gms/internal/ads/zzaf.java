// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.util.Log;

public class zzaf
{
    public static boolean DEBUG;
    private static String TAG;
    
    static {
        zzaf.TAG = "Volley";
        zzaf.DEBUG = Log.isLoggable("Volley", 2);
    }
    
    public static void d(final String s, final Object... array) {
        Log.d(zzaf.TAG, zza(s, array));
    }
    
    public static void e(final String s, final Object... array) {
        Log.e(zzaf.TAG, zza(s, array));
    }
    
    public static void v(final String s, final Object... array) {
        if (zzaf.DEBUG) {
            Log.v(zzaf.TAG, zza(s, array));
        }
    }
    
    private static String zza(String format, final Object... array) {
        if (array != null) {
            format = String.format(Locale.US, format, array);
        }
        final StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        for (int i = 2; i < stackTrace.length; ++i) {
            if (!stackTrace[i].getClass().equals(zzaf.class)) {
                final String className = stackTrace[i].getClassName();
                final String substring = className.substring(className.lastIndexOf(46) + 1);
                final String substring2 = substring.substring(substring.lastIndexOf(36) + 1);
                final String methodName = stackTrace[i].getMethodName();
                final String string = new StringBuilder(String.valueOf(substring2).length() + 1 + String.valueOf(methodName).length()).append(substring2).append(".").append(methodName).toString();
                return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
            }
        }
        final String string = "<unknown>";
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), string, format);
    }
    
    public static void zza(final Throwable t, final String s, final Object... array) {
        Log.e(zzaf.TAG, zza(s, array), t);
    }
    
    static final class zza
    {
        public static final boolean zzbk;
        private final List<zzag> zzbl;
        private boolean zzbm;
        
        static {
            zzbk = zzaf.DEBUG;
        }
        
        zza() {
            this.zzbl = new ArrayList<zzag>();
            this.zzbm = false;
        }
        
        @Override
        protected final void finalize() throws Throwable {
            if (!this.zzbm) {
                this.zzc("Request on the loose");
                zzaf.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }
        
        public final void zza(final String s, final long n) {
            synchronized (this) {
                if (this.zzbm) {
                    throw new IllegalStateException("Marker added to finished log");
                }
            }
            final String s2;
            this.zzbl.add(new zzag(s2, n, SystemClock.elapsedRealtime()));
        }
        // monitorexit(this)
        
        public final void zzc(final String s) {
            synchronized (this) {
                this.zzbm = true;
                long n;
                if (this.zzbl.size() == 0) {
                    n = 0L;
                }
                else {
                    n = this.zzbl.get(this.zzbl.size() - 1).time - this.zzbl.get(0).time;
                }
                if (n > 0L) {
                    final long time = this.zzbl.get(0).time;
                    zzaf.d("(%-4d ms) %s", n, s);
                    final Iterator<zzag> iterator = this.zzbl.iterator();
                    long n2 = time;
                    while (iterator.hasNext()) {
                        final zzag zzag = iterator.next();
                        final long time2 = zzag.time;
                        zzaf.d("(+%-4d) [%2d] %s", time2 - n2, zzag.zzbn, zzag.name);
                        n2 = time2;
                    }
                }
            }
        }
    }
}
