package com.kongregate.android.internal.sdk;

import android.app.Activity;
import android.content.Context;
import com.kongregate.android.api.KongregateAPI;
import java.util.Map;

/* renamed from: com.kongregate.android.internal.sdk.a */
public class C0486a {
    /* renamed from: a */
    private static volatile C0503h f453a = null;
    /* renamed from: b */
    private static volatile C0501f f454b = null;
    /* renamed from: c */
    private static volatile NativeAPI f455c = null;

    /* renamed from: a */
    public static KongregateAPI m416a(Activity activity, long j, String str, Map<String, Object> map) {
        if (f455c == null) {
            synchronized (NativeAPI.class) {
                if (f455c == null) {
                    f455c = new NativeAPI(activity, j, str, map);
                }
            }
        }
        return f455c;
    }

    /* renamed from: a */
    public static KongregateAPI m417a(Context context, long j, String str) {
        if (f453a == null) {
            synchronized (C0503h.class) {
                if (f453a == null) {
                    f453a = new C0503h(context.getApplicationContext(), j, str);
                }
            }
        }
        return f453a;
    }

    /* renamed from: b */
    public static KongregateAPI m418b(Context context, long j, String str) {
        if (f454b == null) {
            synchronized (C0501f.class) {
                if (f454b == null) {
                    f454b = new C0501f(context.getApplicationContext(), j, str);
                }
            }
        }
        return f454b;
    }
}
