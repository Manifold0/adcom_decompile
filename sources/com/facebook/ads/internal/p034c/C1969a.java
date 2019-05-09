package com.facebook.ads.internal.p034c;

import android.content.Context;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AdSettings.IntegrationErrorMode;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p033b.C1954e;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdErrorType;
import java.util.HashMap;
import java.util.Locale;

@UiThread
/* renamed from: com.facebook.ads.internal.c.a */
public final class C1969a {
    /* renamed from: d */
    private static final HashMap<C1964a, C1964a> f4314d = new HashMap();
    /* renamed from: a */
    C1964a f4315a = C1964a.CREATED;
    /* renamed from: b */
    private final C1971b f4316b;
    /* renamed from: c */
    private final Context f4317c;

    /* renamed from: com.facebook.ads.internal.c.a$a */
    public enum C1964a {
        CREATED,
        LOADING,
        LOADED,
        SHOWING,
        SHOWN,
        DESTROYED,
        ERROR
    }

    static {
        f4314d.put(C1964a.CREATED, C1964a.LOADING);
        f4314d.put(C1964a.LOADING, C1964a.LOADED);
        f4314d.put(C1964a.LOADED, C1964a.SHOWING);
        f4314d.put(C1964a.SHOWING, C1964a.SHOWN);
        f4314d.put(C1964a.SHOWN, C1964a.LOADING);
        f4314d.put(C1964a.DESTROYED, C1964a.LOADING);
        f4314d.put(C1964a.ERROR, C1964a.LOADING);
    }

    C1969a(Context context, C1971b c1971b) {
        this.f4317c = context;
        this.f4316b = c1971b;
    }

    /* renamed from: a */
    public void m4664a(C1964a c1964a) {
        if (!C2078a.ab(this.f4317c)) {
            this.f4315a = c1964a;
        } else if (c1964a.equals(C1964a.DESTROYED) || c1964a.equals(C1964a.ERROR)) {
            this.f4315a = c1964a;
        } else {
            if (!c1964a.equals(f4314d.get(this.f4315a))) {
                C2625a.m6741b(this.f4317c, "api", C2626b.f6546k, new Exception("Wrong internal transition form " + this.f4315a + " to " + c1964a));
            }
            this.f4315a = c1964a;
        }
    }

    /* renamed from: a */
    public boolean m4665a(C1964a c1964a, String str) {
        if (c1964a.equals(f4314d.get(this.f4315a))) {
            this.f4315a = c1964a;
            return false;
        } else if (!C2078a.ab(this.f4317c)) {
            return false;
        } else {
            IntegrationErrorMode a = C1954e.m4636a(this.f4317c);
            String format = String.format(Locale.US, AdErrorType.INCORRECT_STATE_ERROR.getDefaultErrorMessage(), new Object[]{str, this.f4315a});
            switch (a) {
                case INTEGRATION_ERROR_CRASH_DEBUG_MODE:
                    throw new IllegalStateException(format + ". You can change Integration Error mode by setting AdSettings.setIntegrationErrorMode()");
                case INTEGRATION_ERROR_CALLBACK_MODE:
                    this.f4316b.mo5458d();
                    this.f4316b.m4668a(10, AdErrorType.INCORRECT_STATE_ERROR, format);
                    Log.e(AudienceNetworkAds.TAG, format);
                    C2625a.m6741b(this.f4317c, "api", C2626b.f6547l, new Exception(format));
                    return true;
                default:
                    Log.e(AudienceNetworkAds.TAG, format);
                    return true;
            }
        }
    }
}
