// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.util.Log;
import android.database.ContentObserver;
import android.os.Handler;
import java.util.TreeMap;
import android.database.Cursor;
import java.util.Map;
import android.content.ContentResolver;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import android.net.Uri;

public class zzhe
{
    private static final Uri CONTENT_URI;
    private static final Uri zzjn;
    private static final Pattern zzjo;
    private static final Pattern zzjp;
    private static final AtomicBoolean zzjq;
    private static HashMap<String, String> zzjr;
    private static final HashMap<String, Boolean> zzjs;
    private static final HashMap<String, Integer> zzjt;
    private static final HashMap<String, Long> zzju;
    private static final HashMap<String, Float> zzjv;
    private static Object zzjw;
    private static boolean zzjx;
    private static String[] zzjy;
    
    static {
        CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
        zzjn = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzjo = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzjp = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzjq = new AtomicBoolean();
        zzjs = new HashMap<String, Boolean>();
        zzjt = new HashMap<String, Integer>();
        zzju = new HashMap<String, Long>();
        zzjv = new HashMap<String, Float>();
        zzhe.zzjy = new String[0];
    }
    
    private static <T> T zza(final HashMap<String, T> hashMap, final String s, final T t) {
        while (true) {
            synchronized (zzhe.class) {
                if (!hashMap.containsKey(s)) {
                    return null;
                }
                final T value = hashMap.get(s);
                if (value != null) {
                    return value;
                }
            }
            return t;
        }
    }
    
    private static String zza(final ContentResolver contentResolver, String s, String zzjy) {
    Label_0205_Outer:
        while (true) {
            final String s2 = null;
            zzjy = null;
            Object zzjy2 = null;
            // monitorexit(zzhe.class)
            Object zzjw = null;
            Label_0167: {
            Label_0162:
                while (true) {
                    int n = 0;
                    Label_0309: {
                        synchronized (zzhe.class) {
                            zza(contentResolver);
                            zzjw = zzhe.zzjw;
                            if (zzhe.zzjr.containsKey(s)) {
                                s = zzhe.zzjr.get(s);
                                String s3 = (String)zzjy2;
                                if (s != null) {
                                    s3 = s;
                                }
                                return s3;
                            }
                            zzjy2 = zzhe.zzjy;
                            final int length = ((String)zzjy2).length;
                            n = 0;
                            if (n >= length) {
                                break Label_0167;
                            }
                            if (!s.startsWith(zzjy2[n])) {
                                break Label_0309;
                            }
                            if (!zzhe.zzjx || zzhe.zzjr.isEmpty()) {
                                zzjy = (String)(Object)zzhe.zzjy;
                                zzhe.zzjr.putAll(zza(contentResolver, (String[])(Object)zzjy));
                                zzhe.zzjx = true;
                                if (zzhe.zzjr.containsKey(s)) {
                                    s = zzhe.zzjr.get(s);
                                    String s4 = s2;
                                    if (s != null) {
                                        s4 = s;
                                    }
                                    return s4;
                                }
                            }
                        }
                        break Label_0162;
                    }
                    ++n;
                    continue Label_0205_Outer;
                }
                return null;
            }
            // monitorexit(zzhe.class)
            final Cursor query = contentResolver.query(zzhe.CONTENT_URI, (String[])null, (String)null, new String[] { s }, (String)null);
            while (true) {
                if (query != null) {
                    try {
                        if (!query.moveToFirst()) {
                            zza(zzjw, s, null);
                            return null;
                        }
                        final String string = query.getString(1);
                        String s5;
                        if ((s5 = string) != null) {
                            s5 = string;
                            if (string.equals(null)) {
                                s5 = null;
                            }
                        }
                        zza(zzjw, s, s5);
                        s = zzjy;
                        if (s5 != null) {
                            s = s5;
                        }
                        return s;
                    }
                    finally {
                        if (query != null) {
                            query.close();
                        }
                    }
                    return;
                }
                continue;
            }
        }
    }
    
    private static Map<String, String> zza(ContentResolver query, final String... array) {
        query = (ContentResolver)query.query(zzhe.zzjn, (String[])null, (String)null, array, (String)null);
        final TreeMap<String, String> treeMap = new TreeMap<String, String>();
        if (query == null) {
            return treeMap;
        }
        try {
            while (((Cursor)query).moveToNext()) {
                treeMap.put(((Cursor)query).getString(0), ((Cursor)query).getString(1));
            }
        }
        finally {
            ((Cursor)query).close();
        }
        ((Cursor)query).close();
        return;
    }
    
    private static void zza(final ContentResolver contentResolver) {
        if (zzhe.zzjr == null) {
            zzhe.zzjq.set(false);
            zzhe.zzjr = new HashMap<String, String>();
            zzhe.zzjw = new Object();
            zzhe.zzjx = false;
            contentResolver.registerContentObserver(zzhe.CONTENT_URI, true, (ContentObserver)new zzhf(null));
        }
        else if (zzhe.zzjq.getAndSet(false)) {
            zzhe.zzjr.clear();
            zzhe.zzjs.clear();
            zzhe.zzjt.clear();
            zzhe.zzju.clear();
            zzhe.zzjv.clear();
            zzhe.zzjw = new Object();
            zzhe.zzjx = false;
        }
    }
    
    private static void zza(final Object o, final String s, final String s2) {
        synchronized (zzhe.class) {
            if (o == zzhe.zzjw) {
                zzhe.zzjr.put(s, s2);
            }
        }
    }
    
    public static boolean zza(final ContentResolver contentResolver, final String s, final boolean b) {
        final Object zzb = zzb(contentResolver);
        final Boolean b2 = zza(zzhe.zzjs, s, true);
        if (b2 != null) {
            return b2;
        }
        final String zza = zza(contentResolver, s, null);
        while (true) {
            Label_0095: {
                if (zza != null && !zza.equals("")) {
                    break Label_0095;
                }
                final Boolean b3 = b2;
                final boolean b4 = true;
                final HashMap<String, Boolean> zzjs = zzhe.zzjs;
                synchronized (zzhe.class) {
                    if (zzb == zzhe.zzjw) {
                        zzjs.put(s, b3);
                        zzhe.zzjr.remove(s);
                    }
                    return b4;
                }
            }
            if (zzhe.zzjo.matcher(zza).matches()) {
                final Boolean b3 = true;
                final boolean b4 = true;
                continue;
            }
            if (zzhe.zzjp.matcher(zza).matches()) {
                final Boolean b3 = false;
                final boolean b4 = false;
                continue;
            }
            Log.w("Gservices", "attempt to read gservices key " + s + " (value \"" + zza + "\") as boolean");
            final Boolean b3 = b2;
            final boolean b4 = true;
            continue;
        }
    }
    
    private static Object zzb(final ContentResolver contentResolver) {
        synchronized (zzhe.class) {
            zza(contentResolver);
            return zzhe.zzjw;
        }
    }
}
