// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.e;

import java.util.Iterator;
import android.os.Messenger;
import android.os.RemoteException;
import java.util.Map;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.LinkedHashMap;
import android.support.annotation.UiThread;
import com.facebook.ads.internal.c.a.c;

@UiThread
public class a implements c
{
    private static final String a;
    private static a b;
    private final LinkedHashMap<String, a> c;
    
    static {
        a = a.class.getSimpleName();
    }
    
    private a() {
        this.c = new LinkedHashMap<String, a>();
    }
    
    public static a a() {
        if (com.facebook.ads.internal.e.a.b == null) {
            com.facebook.ads.internal.e.a.b = new a();
        }
        return com.facebook.ads.internal.e.a.b;
    }
    
    private static void f(final String s) {
        Log.d(com.facebook.ads.internal.e.a.a, s);
    }
    
    @Nullable
    public com.facebook.ads.internal.c.c a(final String s) {
        final a a = this.c.get(s);
        if (a != null) {
            return a.c;
        }
        return null;
    }
    
    public void a(final int n, final String s) {
        this.a(n, s, null);
    }
    
    @Override
    public void a(final int n, String iterator, @Nullable Bundle bundle) {
        final a e = this.e(iterator);
        while (true) {
            if (e != null) {
                try {
                    final Message obtain = Message.obtain((Handler)null, n);
                    obtain.getData().putString("STR_AD_ID_KEY", iterator);
                    if (bundle != null) {
                        obtain.getData().putBundle("BUNDLE_EXTRAS_KEY", bundle);
                    }
                    e.b.send(obtain);
                    iterator = (String)this.c.entrySet().iterator();
                    while (((Iterator)iterator).hasNext()) {
                        bundle = (Bundle)((Iterator<Map.Entry<K, a>>)iterator).next().getValue();
                        try {
                            ((a)bundle).b.send(Message.obtain((Handler)null, 3));
                        }
                        catch (RemoteException ex) {
                            this.b(((a)bundle).a);
                        }
                    }
                }
                catch (RemoteException ex2) {
                    this.b(iterator);
                    continue;
                }
                return;
            }
            continue;
        }
    }
    
    public void a(final String s, final Messenger messenger) {
        this.c.put(s, new a(s, messenger));
    }
    
    public void b() {
        final Iterator<Map.Entry<String, a>> iterator = this.c.entrySet().iterator();
        while (iterator.hasNext()) {
            final com.facebook.ads.internal.c.c c = iterator.next().getValue().c;
            if (c != null) {
                c.a();
            }
            iterator.remove();
        }
    }
    
    public void b(final String s) {
        final a a = this.c.get(s);
        if (a != null && a.c != null) {
            f("Destroyed Ad " + s);
            a.c.a();
            this.c.remove(s);
        }
    }
    
    public void c(final String s) {
        if (this.c.get(s) != null) {
            f("Removed Ad " + s);
            this.c.remove(s);
        }
    }
    
    public void d(final String s) {
        this.c.remove(s);
    }
    
    @Nullable
    public a e(final String s) {
        return this.c.get(s);
    }
    
    public static class a
    {
        public final String a;
        public final Messenger b;
        @Nullable
        public com.facebook.ads.internal.c.c c;
        
        a(final String a, final Messenger b) {
            this.a = a;
            this.b = b;
        }
    }
}
