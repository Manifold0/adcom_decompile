package com.facebook.ads.internal.p025w.p057e;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p042l.C2042a;
import com.facebook.ads.internal.p046v.p047a.C2138a;
import com.facebook.ads.internal.p046v.p047a.C2150n;
import com.facebook.ads.internal.p046v.p047a.C2152p;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.facebook.ads.internal.w.e.e */
public class C2613e extends AsyncTask<String, Void, C2614f> {
    /* renamed from: a */
    private static final String f6471a = C2613e.class.getSimpleName();
    /* renamed from: b */
    private static final Set<String> f6472b = new HashSet();
    /* renamed from: c */
    private Context f6473c;
    /* renamed from: d */
    private Map<String, String> f6474d;
    /* renamed from: e */
    private Map<String, String> f6475e;
    /* renamed from: f */
    private C2150n f6476f;
    /* renamed from: g */
    private C2358a f6477g;

    /* renamed from: com.facebook.ads.internal.w.e.e$a */
    public interface C2358a {
        /* renamed from: a */
        void mo5589a();

        /* renamed from: a */
        void mo5590a(C2614f c2614f);
    }

    static {
        f6472b.add("#");
        f6472b.add("null");
    }

    public C2613e(Context context) {
        this(context, null, null);
    }

    public C2613e(Context context, Map<String, String> map) {
        this(context, map, null);
    }

    public C2613e(Context context, Map<String, String> map, Map<String, String> map2) {
        Map map3 = null;
        this.f6473c = context;
        this.f6474d = map != null ? new HashMap(map) : null;
        if (map2 != null) {
            map3 = new HashMap(map2);
        }
        this.f6475e = map3;
    }

    /* renamed from: a */
    private String m6714a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return str;
        }
        return str + (str.contains("?") ? RequestParameters.AMPERSAND : "?") + str2 + RequestParameters.EQUAL + URLEncoder.encode(str3);
    }

    /* renamed from: a */
    private boolean m6715a(String str) {
        C2138a a = C2612d.m6709a(this.f6473c);
        try {
            if (this.f6475e == null || this.f6475e.size() == 0) {
                this.f6476f = a.m5459a(str, null);
            } else {
                C2152p c2152p = new C2152p();
                c2152p.m5508a(this.f6475e);
                this.f6476f = a.m5470b(str, c2152p);
            }
            return this.f6476f != null && this.f6476f.m5501a() == 200;
        } catch (Throwable e) {
            Log.e(f6471a, "Error opening url: " + str, e);
            return false;
        }
    }

    /* renamed from: b */
    private String m6716b(String str) {
        try {
            str = m6714a(str, "analog", C2581k.m6645a(C2042a.m4944a()));
        } catch (Exception e) {
        }
        return str;
    }

    /* renamed from: a */
    protected C2614f m6717a(String... strArr) {
        Object obj = strArr[0];
        if (TextUtils.isEmpty(obj) || f6472b.contains(obj)) {
            return null;
        }
        String b = m6716b(obj);
        if (!(this.f6474d == null || this.f6474d.isEmpty())) {
            String str = b;
            for (Entry entry : this.f6474d.entrySet()) {
                str = m6714a(str, (String) entry.getKey(), (String) entry.getValue());
            }
            b = str;
        }
        int i = 1;
        while (true) {
            int i2 = i + 1;
            if (i > 2) {
                return null;
            }
            if (m6715a(b)) {
                return new C2614f(this.f6476f);
            }
            i = i2;
        }
    }

    /* renamed from: a */
    public void m6718a(C2358a c2358a) {
        this.f6477g = c2358a;
    }

    /* renamed from: a */
    protected void m6719a(C2614f c2614f) {
        if (this.f6477g != null) {
            this.f6477g.mo5590a(c2614f);
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m6717a((String[]) objArr);
    }

    protected void onCancelled() {
        if (this.f6477g != null) {
            this.f6477g.mo5589a();
        }
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m6719a((C2614f) obj);
    }
}
