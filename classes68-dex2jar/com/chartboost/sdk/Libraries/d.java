// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import android.util.Base64;
import org.json.JSONObject;
import android.os.Looper;
import com.chartboost.sdk.impl.as;
import com.chartboost.sdk.i;
import android.os.Build;
import android.content.ContentResolver;
import android.provider.Settings$SettingNotFoundException;
import android.provider.Settings$Secure;
import com.chartboost.sdk.impl.ar;
import android.content.Context;

public class d
{
    private int a;
    private String b;
    private final String c;
    
    public d(final Context context) {
        this.a = -1;
        this.b = null;
        this.c = ar.b(context);
    }
    
    private void a(final Context context) {
        while (true) {
            int n = 1;
            while (true) {
                Label_0081: {
                    try {
                        final ContentResolver contentResolver = context.getContentResolver();
                        if (Settings$Secure.getInt(contentResolver, "limit_ad_tracking") == 0) {
                            break Label_0081;
                        }
                        if (n == 0) {
                            final String string = Settings$Secure.getString(contentResolver, "advertising_id");
                            if ("00000000-0000-0000-0000-000000000000".equals(string)) {
                                this.a = 1;
                                this.b = null;
                                return;
                            }
                            this.a = 0;
                            this.b = string;
                            return;
                        }
                    }
                    catch (Settings$SettingNotFoundException ex) {
                        this.a = -1;
                        this.b = null;
                        return;
                    }
                    break;
                }
                n = 0;
                continue;
            }
        }
        this.a = 1;
        this.b = null;
    }
    
    private static boolean b() {
        return !"Amazon".equalsIgnoreCase(Build.MANUFACTURER);
    }
    
    private void c() {
        if (!as.a(i.m)) {
            return;
        }
        final com.chartboost.sdk.Libraries.a a = new com.chartboost.sdk.Libraries.a(i.m);
        this.a = a.a;
        this.b = a.b;
    }
    
    public a a() {
    Label_0115_Outer:
        while (true) {
            while (true) {
            Label_0146:
                while (true) {
                    Label_0136: {
                        synchronized (this) {
                            a a;
                            if (Looper.myLooper() == Looper.getMainLooper() && !"robolectric".equals(Build.FINGERPRINT)) {
                                CBLogging.b("CBIdentity", "I must be called from a background thread");
                                a = null;
                            }
                            else {
                                if (!b()) {
                                    break Label_0136;
                                }
                                this.c();
                                final String b = this.b;
                                final JSONObject jsonObject = new JSONObject();
                                if (this.c != null && b == null) {
                                    e.a(jsonObject, "uuid", this.c);
                                }
                                if (b != null) {
                                    e.a(jsonObject, "gaid", b);
                                }
                                final int a2 = this.a;
                                final String encodeToString = Base64.encodeToString(jsonObject.toString().getBytes(), 0);
                                if (b == null) {
                                    break Label_0146;
                                }
                                final String c = "000000000";
                                a = new a(a2, encodeToString, c, b);
                            }
                            return a;
                        }
                    }
                    this.a(i.m);
                    continue Label_0115_Outer;
                }
                final String c = this.c;
                continue;
            }
        }
    }
    
    public static class a
    {
        public final int a;
        public final String b;
        public final String c;
        public final String d;
        
        public a(final int a, final String b, final String c, final String d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
    }
}
