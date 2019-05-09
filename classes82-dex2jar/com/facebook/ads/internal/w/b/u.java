// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Context;

public class u
{
    public static a a(final Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return a.a;
        }
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return a.b;
        }
        if (activeNetworkInfo.getType() != 0) {
            return a.c;
        }
        switch (activeNetworkInfo.getSubtype()) {
            default: {
                return a.a;
            }
            case 1:
            case 2:
            case 4:
            case 7:
            case 11: {
                return a.d;
            }
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15: {
                return a.e;
            }
            case 13: {
                return a.f;
            }
        }
    }
    
    public enum a
    {
        a(0), 
        b(0), 
        c(1), 
        d(2), 
        e(3), 
        f(4);
        
        public final int g;
        
        private a(final int g) {
            this.g = g;
        }
    }
}
