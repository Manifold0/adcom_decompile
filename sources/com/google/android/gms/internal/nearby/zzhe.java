package com.google.android.gms.internal.nearby;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzhe {
    private static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri zzjn = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    private static final Pattern zzjo = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    private static final Pattern zzjp = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean zzjq = new AtomicBoolean();
    private static HashMap<String, String> zzjr;
    private static final HashMap<String, Boolean> zzjs = new HashMap();
    private static final HashMap<String, Integer> zzjt = new HashMap();
    private static final HashMap<String, Long> zzju = new HashMap();
    private static final HashMap<String, Float> zzjv = new HashMap();
    private static Object zzjw;
    private static boolean zzjx;
    private static String[] zzjy = new String[0];

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T zza(java.util.HashMap<java.lang.String, T> r2, java.lang.String r3, T r4) {
        /*
        r1 = com.google.android.gms.internal.nearby.zzhe.class;
        monitor-enter(r1);
        r0 = r2.containsKey(r3);	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0013;
    L_0x0009:
        r0 = r2.get(r3);	 Catch:{ all -> 0x0016 }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
    L_0x0010:
        return r0;
    L_0x0011:
        r0 = r4;
        goto L_0x000f;
    L_0x0013:
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        r0 = 0;
        goto L_0x0010;
    L_0x0016:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0016 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.nearby.zzhe.zza(java.util.HashMap, java.lang.String, java.lang.Object):T");
    }

    private static String zza(ContentResolver contentResolver, String str, String str2) {
        String str3 = null;
        synchronized (zzhe.class) {
            zza(contentResolver);
            Object obj = zzjw;
            String str4;
            if (zzjr.containsKey(str)) {
                str4 = (String) zzjr.get(str);
                if (str4 != null) {
                    str3 = str4;
                }
            } else {
                String[] strArr = zzjy;
                int length = strArr.length;
                int i = 0;
                while (i < length) {
                    if (str.startsWith(strArr[i])) {
                        if (!zzjx || zzjr.isEmpty()) {
                            zzjr.putAll(zza(contentResolver, zzjy));
                            zzjx = true;
                            if (zzjr.containsKey(str)) {
                                str4 = (String) zzjr.get(str);
                                if (str4 != null) {
                                    str3 = str4;
                                }
                            }
                        }
                    } else {
                        i++;
                    }
                }
                Cursor query = contentResolver.query(CONTENT_URI, null, null, new String[]{str}, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            str4 = query.getString(1);
                            if (str4 != null && str4.equals(null)) {
                                str4 = null;
                            }
                            zza(obj, str, str4);
                            if (str4 != null) {
                                str3 = str4;
                            }
                            if (query != null) {
                                query.close();
                            }
                        }
                    } catch (Throwable th) {
                        if (query != null) {
                            query.close();
                        }
                    }
                }
                zza(obj, str, null);
                if (query != null) {
                    query.close();
                }
            }
        }
        return str3;
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzjn, null, null, strArr, null);
        Map<String, String> treeMap = new TreeMap();
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    treeMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (zzjr == null) {
            zzjq.set(false);
            zzjr = new HashMap();
            zzjw = new Object();
            zzjx = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new zzhf(null));
        } else if (zzjq.getAndSet(false)) {
            zzjr.clear();
            zzjs.clear();
            zzjt.clear();
            zzju.clear();
            zzjv.clear();
            zzjw = new Object();
            zzjx = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzhe.class) {
            if (obj == zzjw) {
                zzjr.put(str, str2);
            }
        }
    }

    public static boolean zza(ContentResolver contentResolver, String str, boolean z) {
        Object zzb = zzb(contentResolver);
        Boolean bool = (Boolean) zza(zzjs, str, Boolean.valueOf(true));
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean z2;
        Object zza = zza(contentResolver, str, null);
        if (zza == null || zza.equals("")) {
            Object obj = bool;
            z2 = true;
        } else if (zzjo.matcher(zza).matches()) {
            r1 = Boolean.valueOf(true);
            z2 = true;
        } else if (zzjp.matcher(zza).matches()) {
            r1 = Boolean.valueOf(false);
            z2 = false;
        } else {
            Log.w("Gservices", "attempt to read gservices key " + str + " (value \"" + zza + "\") as boolean");
            r1 = bool;
            z2 = true;
        }
        HashMap hashMap = zzjs;
        synchronized (zzhe.class) {
            if (zzb == zzjw) {
                hashMap.put(str, obj);
                zzjr.remove(str);
            }
        }
        return z2;
    }

    private static Object zzb(ContentResolver contentResolver) {
        Object obj;
        synchronized (zzhe.class) {
            zza(contentResolver);
            obj = zzjw;
        }
        return obj;
    }
}
