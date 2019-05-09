// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api;

import com.kongregate.android.internal.sdk.a;
import com.kongregate.android.internal.sdk.b;
import com.kongregate.android.internal.util.j;
import java.util.Map;
import android.app.Activity;
import java.util.concurrent.atomic.AtomicReference;

public class APIBootstrap
{
    private static final AtomicReference<KongregateAPI> sInstance;
    
    static {
        sInstance = new AtomicReference<KongregateAPI>(null);
    }
    
    public static KongregateAPI getInstance() {
        final KongregateAPI kongregateAPI = APIBootstrap.sInstance.get();
        if (kongregateAPI == null) {
            throw new IllegalStateException("Must initialize Kongregate API before calling getInstance");
        }
        return kongregateAPI;
    }
    
    public static KongregateAPI initializeNativeAPI(final Activity activity, final long n, final String s) {
        return initializeNativeAPI(activity, n, s, (Map<String, Object>)null);
    }
    
    public static KongregateAPI initializeNativeAPI(final Activity activity, final long n, final String s, final String s2) {
        j.a("API bootstrap - initialize native");
        final KongregateAPI a = com.kongregate.android.internal.sdk.a.a(activity, n, s, b.a(s2));
        APIBootstrap.sInstance.compareAndSet(null, a);
        return a;
    }
    
    public static KongregateAPI initializeNativeAPI(final Activity activity, final long n, final String s, final Map<String, Object> map) {
        j.a("API bootstrap - initialize native");
        final KongregateAPI a = com.kongregate.android.internal.sdk.a.a(activity, n, s, map);
        APIBootstrap.sInstance.compareAndSet(null, a);
        return a;
    }
}
