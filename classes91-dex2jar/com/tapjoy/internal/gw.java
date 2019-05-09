// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.net.URL;
import java.util.Iterator;
import android.content.SharedPreferences$Editor;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.util.concurrent.ExecutorService;
import android.content.SharedPreferences;
import android.content.Context;

public final class gw
{
    public static final gw a;
    public Context b;
    public SharedPreferences c;
    public SharedPreferences d;
    ExecutorService e;
    private File f;
    
    static {
        a = new gw();
    }
    
    private gw() {
        this.c = null;
        this.d = null;
        this.e = new ThreadPoolExecutor(0, 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }
    
    public static boolean a(final long n) {
        return n >= 3600L;
    }
    
    private void c() {
        boolean b = false;
        HashMap<String, File> hashMap = null;
        int i = 0;
        Object edit = null;
        SharedPreferences$Editor edit2 = null;
        HashMap<Object, Object> hashMap2 = null;
    Label_0221_Outer:
        while (true) {
            b = true;
            while (true) {
            Label_0635:
                while (true) {
                    Label_0632: {
                        Label_0629: {
                            synchronized (this) {
                                final long b2 = y.b();
                                final File[] listFiles = this.b().listFiles();
                                hashMap = new HashMap<String, File>();
                                if (listFiles != null) {
                                    for (final int length = listFiles.length, i = 0; i < length; ++i) {
                                        edit = listFiles[i];
                                        hashMap.put(((File)edit).getName(), (File)edit);
                                    }
                                }
                                edit2 = this.c.edit();
                                edit = this.d.edit();
                                hashMap2 = new HashMap<Object, Object>(this.c.getAll());
                                Object o = new HashMap<Object, Object>(this.d.getAll());
                                Object iterator = hashMap2.entrySet().iterator();
                                i = 0;
                                if (((Iterator)iterator).hasNext()) {
                                    final String s = ((Iterator<Map.Entry<String, Long>>)iterator).next().getKey();
                                    if (!((Map)o).containsKey(s)) {
                                        ((Iterator)iterator).remove();
                                        edit2.remove(s);
                                        i = 1;
                                        break Label_0632;
                                    }
                                    break Label_0632;
                                }
                                else {
                                    o = ((Map<Object, Object>)o).entrySet().iterator();
                                    if (((Iterator)o).hasNext()) {
                                        iterator = ((Iterator<Map.Entry<String, V>>)o).next().getKey();
                                        if (!hashMap2.containsKey(iterator)) {
                                            ((Iterator)o).remove();
                                            ((SharedPreferences$Editor)edit).remove((String)iterator);
                                            i = 1;
                                            break Label_0635;
                                        }
                                        break Label_0629;
                                    }
                                    else {
                                        o = hashMap2.entrySet().iterator();
                                        while (((Iterator)o).hasNext()) {
                                            iterator = ((Iterator<Map.Entry<String, V>>)o).next();
                                            final String s2 = ((Map.Entry<String, V>)iterator).getKey();
                                            if (((Map.Entry<String, Long>)iterator).getValue() < b2) {
                                                ((Iterator)o).remove();
                                                edit2.remove(s2);
                                                ((SharedPreferences$Editor)edit).remove(s2);
                                                i = 1;
                                            }
                                            else {
                                                hashMap.remove(s2);
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                        break Label_0635;
                    }
                    continue Label_0221_Outer;
                }
                continue;
            }
        }
        final Iterator<Map.Entry<String, File>> iterator2 = hashMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            iterator2.next().getValue().delete();
        }
        int n2;
        if (hashMap2.size() > 30) {
            final int size = hashMap2.size();
            final LinkedList list = new LinkedList<Map.Entry<String, V>>(hashMap2.entrySet());
            Collections.sort((List<E>)list, new Comparator() {});
            final Iterator<Map.Entry<String, V>> iterator3 = list.iterator();
            int n = 0;
            while (true) {
                n2 = (b ? 1 : 0);
                if (n >= size - 30) {
                    break;
                }
                n2 = (b ? 1 : 0);
                if (!iterator3.hasNext()) {
                    break;
                }
                final Map.Entry<String, V> entry = iterator3.next();
                final String s3 = entry.getKey();
                final Long n3 = (Long)entry.getValue();
                edit2.remove(s3);
                ((SharedPreferences$Editor)edit).remove(s3);
                this.a(s3).delete();
                ++n;
            }
        }
        else {
            n2 = i;
        }
        if (n2 != 0) {
            edit2.commit();
            ((SharedPreferences$Editor)edit).commit();
        }
    }
    // monitorexit(this)
    
    final File a(final String s) {
        return new File(this.b(), s);
    }
    
    public final File a(final URL url) {
        if (this.b == null) {
            return null;
        }
        final File a;
        synchronized (this) {
            a = this.a(this.b(url));
            if (!a.exists()) {
                return null;
            }
        }
        final long b = y.b();
        final String s;
        final long long1 = this.c.getLong(s, 0L);
        if (long1 >= b) {
            // monitorexit(this)
            return a;
        }
        if (long1 != 0L) {
            this.c.edit().remove(s).commit();
            this.d.edit().remove(s).commit();
        }
        a.delete();
        // monitorexit(this)
        return null;
    }
    
    public final void a() {
        this.e.submit(new Runnable() {
            @Override
            public final void run() {
                if (gw.this.b == null) {
                    return;
                }
                gw.this.c();
            }
        });
    }
    
    final File b() {
        File f;
        if ((f = this.f) == null) {
            f = new File(this.b.getCacheDir(), "tapjoy_mm_cache");
            this.f = f;
        }
        if (!f.isDirectory()) {
            f.delete();
        }
        if (!f.exists()) {
            f.mkdirs();
        }
        return f;
    }
    
    final String b(final URL url) {
        while (true) {
            while (true) {
                Label_0176: {
                    synchronized (this) {
                        final String string = url.toString();
                        final String s = new String(ii.a(cm.a(string.getBytes())));
                        final String string2 = this.d.getString(s, (String)null);
                        String string3;
                        if (string2 == null) {
                            this.d.edit().putString(s, string).commit();
                            string3 = s;
                        }
                        else {
                            string3 = s;
                            if (!string2.equals(string)) {
                                int n = 0;
                                ++n;
                                string3 = s + "_" + n;
                                final String string4 = this.d.getString(string3, (String)null);
                                if (string4 != null && !string4.equals(string)) {
                                    break Label_0176;
                                }
                                this.d.edit().putString(string3, string).commit();
                            }
                        }
                        return string3;
                    }
                }
                continue;
            }
        }
    }
}
