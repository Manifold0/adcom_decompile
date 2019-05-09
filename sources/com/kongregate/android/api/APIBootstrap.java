package com.kongregate.android.api;

import android.app.Activity;
import com.kongregate.android.internal.sdk.C0486a;
import com.kongregate.android.internal.sdk.C0487b;
import com.kongregate.android.internal.util.C0562j;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class APIBootstrap {
    private static final AtomicReference<KongregateAPI> sInstance = new AtomicReference(null);

    public static KongregateAPI initializeNativeAPI(Activity activity, long j, String str) {
        return initializeNativeAPI(activity, j, str, (Map) null);
    }

    public static KongregateAPI initializeNativeAPI(Activity activity, long j, String str, Map<String, Object> map) {
        C0562j.m753a("API bootstrap - initialize native");
        KongregateAPI a = C0486a.m416a(activity, j, str, map);
        sInstance.compareAndSet(null, a);
        return a;
    }

    public static KongregateAPI initializeNativeAPI(Activity activity, long j, String str, String str2) {
        C0562j.m753a("API bootstrap - initialize native");
        KongregateAPI a = C0486a.m416a(activity, j, str, C0487b.m423a(str2));
        sInstance.compareAndSet(null, a);
        return a;
    }

    public static KongregateAPI getInstance() {
        KongregateAPI kongregateAPI = (KongregateAPI) sInstance.get();
        if (kongregateAPI != null) {
            return kongregateAPI;
        }
        throw new IllegalStateException("Must initialize Kongregate API before calling getInstance");
    }
}
