package com.facebook.ads.internal.p036e;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.internal.p034c.C1972c;
import com.facebook.ads.internal.p034c.p035a.C1967c;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

@UiThread
/* renamed from: com.facebook.ads.internal.e.a */
public class C1992a implements C1967c {
    /* renamed from: a */
    private static final String f4399a = C1992a.class.getSimpleName();
    /* renamed from: b */
    private static C1992a f4400b;
    /* renamed from: c */
    private final LinkedHashMap<String, C1991a> f4401c = new LinkedHashMap();

    /* renamed from: com.facebook.ads.internal.e.a$a */
    public static class C1991a {
        /* renamed from: a */
        public final String f4396a;
        /* renamed from: b */
        public final Messenger f4397b;
        @Nullable
        /* renamed from: c */
        public C1972c f4398c;

        C1991a(String str, Messenger messenger) {
            this.f4396a = str;
            this.f4397b = messenger;
        }
    }

    private C1992a() {
    }

    /* renamed from: a */
    public static C1992a m4771a() {
        if (f4400b == null) {
            f4400b = new C1992a();
        }
        return f4400b;
    }

    /* renamed from: f */
    private static void m4772f(String str) {
        Log.d(f4399a, str);
    }

    @Nullable
    /* renamed from: a */
    public C1972c m4773a(String str) {
        C1991a c1991a = (C1991a) this.f4401c.get(str);
        return c1991a != null ? c1991a.f4398c : null;
    }

    /* renamed from: a */
    public void m4774a(int i, String str) {
        mo5445a(i, str, null);
    }

    /* renamed from: a */
    public void mo5445a(int i, String str, @Nullable Bundle bundle) {
        C1991a e = m4781e(str);
        if (e != null) {
            try {
                Message obtain = Message.obtain(null, i);
                obtain.getData().putString("STR_AD_ID_KEY", str);
                if (bundle != null) {
                    obtain.getData().putBundle("BUNDLE_EXTRAS_KEY", bundle);
                }
                e.f4397b.send(obtain);
            } catch (RemoteException e2) {
                m4778b(str);
            }
        }
        for (Entry value : this.f4401c.entrySet()) {
            e = (C1991a) value.getValue();
            try {
                e.f4397b.send(Message.obtain(null, 3));
            } catch (RemoteException e3) {
                m4778b(e.f4396a);
            }
        }
    }

    /* renamed from: a */
    public void m4776a(String str, Messenger messenger) {
        this.f4401c.put(str, new C1991a(str, messenger));
    }

    /* renamed from: b */
    public void m4777b() {
        Iterator it = this.f4401c.entrySet().iterator();
        while (it.hasNext()) {
            C1972c c1972c = ((C1991a) ((Entry) it.next()).getValue()).f4398c;
            if (c1972c != null) {
                c1972c.mo5449a();
            }
            it.remove();
        }
    }

    /* renamed from: b */
    public void m4778b(String str) {
        C1991a c1991a = (C1991a) this.f4401c.get(str);
        if (c1991a != null && c1991a.f4398c != null) {
            C1992a.m4772f("Destroyed Ad " + str);
            c1991a.f4398c.mo5449a();
            this.f4401c.remove(str);
        }
    }

    /* renamed from: c */
    public void m4779c(String str) {
        if (((C1991a) this.f4401c.get(str)) != null) {
            C1992a.m4772f("Removed Ad " + str);
            this.f4401c.remove(str);
        }
    }

    /* renamed from: d */
    public void m4780d(String str) {
        this.f4401c.remove(str);
    }

    @Nullable
    /* renamed from: e */
    public C1991a m4781e(String str) {
        return (C1991a) this.f4401c.get(str);
    }
}
