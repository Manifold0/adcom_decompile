package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class zzaf {
    public static boolean DEBUG;
    private static String TAG;

    static class zza {
        public static final boolean zzbk = zzaf.DEBUG;
        private final List<zzag> zzbl = new ArrayList();
        private boolean zzbm = false;

        zza() {
        }

        protected final void finalize() throws Throwable {
            if (!this.zzbm) {
                zzc("Request on the loose");
                zzaf.m3913e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public final synchronized void zza(String str, long j) {
            if (this.zzbm) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.zzbl.add(new zzag(str, j, SystemClock.elapsedRealtime()));
        }

        public final synchronized void zzc(String str) {
            long j;
            this.zzbm = true;
            if (this.zzbl.size() == 0) {
                j = 0;
            } else {
                j = ((zzag) this.zzbl.get(this.zzbl.size() - 1)).time - ((zzag) this.zzbl.get(0)).time;
            }
            if (j > 0) {
                long j2 = ((zzag) this.zzbl.get(0)).time;
                zzaf.m3912d("(%-4d ms) %s", Long.valueOf(j), str);
                j = j2;
                for (zzag zzag : this.zzbl) {
                    zzaf.m3912d("(+%-4d) [%2d] %s", Long.valueOf(zzag.time - j), Long.valueOf(zzag.zzbn), zzag.name);
                    j = zzag.time;
                }
            }
        }
    }

    static {
        String str = "Volley";
        TAG = str;
        DEBUG = Log.isLoggable(str, 2);
    }

    /* renamed from: d */
    public static void m3912d(String str, Object... objArr) {
        Log.d(TAG, zza(str, objArr));
    }

    /* renamed from: e */
    public static void m3913e(String str, Object... objArr) {
        Log.e(TAG, zza(str, objArr));
    }

    /* renamed from: v */
    public static void m3914v(String str, Object... objArr) {
        if (DEBUG) {
            Log.v(TAG, zza(str, objArr));
        }
    }

    private static String zza(String str, Object... objArr) {
        String methodName;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str2 = "<unknown>";
        for (int i = 2; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClass().equals(zzaf.class)) {
                str2 = stackTrace[i].getClassName();
                str2 = str2.substring(str2.lastIndexOf(46) + 1);
                str2 = str2.substring(str2.lastIndexOf(36) + 1);
                methodName = stackTrace[i].getMethodName();
                methodName = new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(methodName).length()).append(str2).append(".").append(methodName).toString();
                break;
            }
        }
        methodName = str2;
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), methodName, str});
    }

    public static void zza(Throwable th, String str, Object... objArr) {
        Log.e(TAG, zza(str, objArr), th);
    }
}
