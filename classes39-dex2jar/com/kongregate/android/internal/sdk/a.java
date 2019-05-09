// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import android.content.Context;
import com.kongregate.android.api.KongregateAPI;
import java.util.Map;
import android.app.Activity;

public class a
{
    private static volatile h a;
    private static volatile f b;
    private static volatile NativeAPI c;
    
    static {
        com.kongregate.android.internal.sdk.a.a = null;
        com.kongregate.android.internal.sdk.a.b = null;
        com.kongregate.android.internal.sdk.a.c = null;
    }
    
    public static KongregateAPI a(final Activity activity, final long n, final String s, final Map<String, Object> map) {
        Label_0033: {
            if (com.kongregate.android.internal.sdk.a.c != null) {
                break Label_0033;
            }
            synchronized (NativeAPI.class) {
                if (com.kongregate.android.internal.sdk.a.c == null) {
                    com.kongregate.android.internal.sdk.a.c = new NativeAPI(activity, n, s, map);
                }
                return com.kongregate.android.internal.sdk.a.c;
            }
        }
    }
    
    public static KongregateAPI a(final Context context, final long n, final String s) {
        Label_0034: {
            if (com.kongregate.android.internal.sdk.a.a != null) {
                break Label_0034;
            }
            synchronized (h.class) {
                if (com.kongregate.android.internal.sdk.a.a == null) {
                    com.kongregate.android.internal.sdk.a.a = new h(context.getApplicationContext(), n, s);
                }
                return com.kongregate.android.internal.sdk.a.a;
            }
        }
    }
    
    public static KongregateAPI b(final Context context, final long n, final String s) {
        Label_0034: {
            if (com.kongregate.android.internal.sdk.a.b != null) {
                break Label_0034;
            }
            synchronized (f.class) {
                if (com.kongregate.android.internal.sdk.a.b == null) {
                    com.kongregate.android.internal.sdk.a.b = new f(context.getApplicationContext(), n, s);
                }
                return com.kongregate.android.internal.sdk.a.b;
            }
        }
    }
}
