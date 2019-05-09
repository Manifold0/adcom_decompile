package com.moat.analytics.mobile.tjy;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.moat.analytics.mobile.tjy.base.asserts.C2746a;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class bi implements bh, C2750m {
    /* renamed from: a */
    private View f6694a;
    /* renamed from: b */
    private final WebView f6695b;
    /* renamed from: c */
    private boolean f6696c;
    /* renamed from: d */
    private final C2758l f6697d;
    /* renamed from: e */
    private final C2742a f6698e;
    /* renamed from: f */
    private final ap f6699f;
    /* renamed from: g */
    private C2749a f6700g;

    bi(View view, WebView webView, boolean z, C2742a c2742a, ap apVar) {
        this(view, webView, z, new C2759n(webView.getContext(), apVar), c2742a, apVar);
    }

    bi(View view, WebView webView, boolean z, C2758l c2758l, C2742a c2742a, ap apVar) {
        C2746a.m6881a(view);
        C2746a.m6881a(webView);
        C2746a.m6881a(c2742a);
        C2746a.m6881a(c2758l);
        if (apVar.mo6105b()) {
            Log.d("MoatViewTracker", "In initialization method.");
        }
        this.f6698e = c2742a;
        this.f6694a = view;
        this.f6695b = webView;
        this.f6696c = z;
        this.f6697d = c2758l;
        this.f6699f = apVar;
        this.f6700g = C2749a.m6883a();
    }

    /* renamed from: a */
    private static String m6902a(Rect rect) {
        int i = rect.left;
        return String.valueOf(new StringBuilder("{\"x\":").append(i).append(',').append('\"').append("y\":").append(rect.top).append(',').append('\"').append("w\":").append(rect.right - rect.left).append(',').append('\"').append("h\":").append(rect.bottom - rect.top).append('}'));
    }

    /* renamed from: a */
    private static String m6903a(Map map, boolean z) {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (stringBuilder.length() > 1) {
                stringBuilder.append(',');
            }
            stringBuilder.append('\"').append(str).append('\"').append(':');
            if (z) {
                stringBuilder.append('\"').append(str2).append('\"');
            } else {
                stringBuilder.append(str2);
            }
        }
        stringBuilder.append("}");
        return String.valueOf(stringBuilder);
    }

    /* renamed from: a */
    private void m6904a(Map map, String str, Rect rect) {
        map.put(str, m6902a(m6905b(rect)));
    }

    /* renamed from: b */
    private Rect m6905b(Rect rect) {
        float f = m6910j().density;
        if (f == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
    }

    /* renamed from: c */
    private Rect m6906c(Rect rect) {
        Rect k = m6911k();
        if (!this.f6694a.getGlobalVisibleRect(k)) {
            k = m6911k();
        }
        k.left = Math.min(Math.max(0, k.left), rect.right);
        k.right = Math.min(Math.max(0, k.right), rect.right);
        k.top = Math.min(Math.max(0, k.top), rect.bottom);
        k.bottom = Math.min(Math.max(0, k.bottom), rect.bottom);
        return k;
    }

    /* renamed from: g */
    private String m6907g() {
        Exception e;
        if (this.f6700g.m6888c()) {
            return (String) this.f6700g.m6886b();
        }
        String str = "_unknown_";
        String charSequence;
        try {
            Context context = this.f6695b.getContext();
            charSequence = context.getPackageManager().getApplicationLabel(context.getApplicationContext().getApplicationInfo()).toString();
            try {
                this.f6700g = C2749a.m6884a(charSequence);
                return charSequence;
            } catch (Exception e2) {
                e = e2;
                C2747a.m6882a(e);
                return charSequence;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            charSequence = str;
            e = exception;
            C2747a.m6882a(e);
            return charSequence;
        }
    }

    /* renamed from: h */
    private boolean m6908h() {
        return this.f6694a.isShown() && !this.f6698e.mo6125a();
    }

    /* renamed from: i */
    private Rect m6909i() {
        DisplayMetrics j = m6910j();
        return new Rect(0, 0, j.widthPixels, j.heightPixels);
    }

    /* renamed from: j */
    private DisplayMetrics m6910j() {
        return this.f6694a.getContext().getResources().getDisplayMetrics();
    }

    /* renamed from: k */
    private Rect m6911k() {
        return new Rect(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    /* renamed from: a */
    public String mo6118a() {
        int i = 0;
        Map hashMap = new HashMap();
        try {
            Rect i2 = m6909i();
            Rect c = m6906c(i2);
            Rect e = mo6123e();
            m6904a(hashMap, "screen", i2);
            m6904a(hashMap, String.VISIBLE, c);
            m6904a(hashMap, "maybe", c);
            m6904a(hashMap, ParametersKeys.VIEW, e);
            if (m6908h()) {
                i = 1;
            }
            hashMap.put("inFocus", String.valueOf(i));
            hashMap.put("dr", m6910j().density);
            return m6903a(hashMap, false);
        } catch (Exception e2) {
            return "{}";
        }
    }

    /* renamed from: a */
    public void mo6119a(View view) {
        if (this.f6699f.mo6105b()) {
            Log.d("MoatViewTracker", "changing view to " + (view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null"));
        }
        this.f6694a = view;
    }

    /* renamed from: b */
    public String mo6120b() {
        try {
            return m6903a(m6918f(), true);
        } catch (Exception e) {
            return "{}";
        }
    }

    /* renamed from: c */
    public boolean mo6121c() {
        if (this.f6699f.mo6105b()) {
            Log.d("MoatViewTracker", "Attempting bridge installation.");
        }
        boolean a = this.f6697d.mo6129a(this.f6695b, this);
        if (this.f6699f.mo6105b()) {
            Log.d("MoatViewTracker", "Bridge " + (a ? "" : "not ") + "installed.");
        }
        return a;
    }

    /* renamed from: d */
    public void mo6122d() {
        this.f6697d.mo6128a();
    }

    /* renamed from: e */
    public Rect mo6123e() {
        int[] iArr = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.f6694a.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, this.f6694a.getWidth() + i, this.f6694a.getHeight() + i2);
    }

    /* renamed from: f */
    public Map m6918f() {
        Map hashMap = new HashMap();
        String g = m6907g();
        String num = Integer.toString(VERSION.SDK_INT);
        Object obj = this.f6696c ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
        hashMap.put("versionHash", "8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7");
        hashMap.put("appName", g);
        hashMap.put("namespace", "TJY");
        hashMap.put("version", "1.7.10");
        hashMap.put("deviceOS", num);
        hashMap.put("isNative", obj);
        return hashMap;
    }
}
